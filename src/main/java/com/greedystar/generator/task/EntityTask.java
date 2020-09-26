package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Constant;
import com.greedystar.generator.entity.Mode;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class EntityTask extends AbstractTask {
    /**
     * 业务表元数据
     */
    private List<ColumnInfo> tableInfos;
    /**
     * 任务模式
     */
    private Mode mode;

    public EntityTask(Mode mode, AbstractInvoker invoker) {
        this.mode = mode;
        this.invoker = invoker;
        if (Mode.ENTITY_MAIN.equals(mode)) {
            this.tableInfos = invoker.getTableInfos();
        } else if (Mode.ENTITY_PARENT.equals(mode)) {
            this.tableInfos = invoker.getParentTableInfos();
        }
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Entity填充数据
        String className = null;
        String remarks = null;
        if (Mode.ENTITY_MAIN.equals(mode)) {
            className = ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, invoker.getClassName());
            remarks = invoker.getTableInfos().get(0).getTableRemarks();
        } else if (Mode.ENTITY_PARENT.equals(mode)) {
            className = ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, invoker.getParentClassName());
            remarks = invoker.getParentTableInfos().get(0).getTableRemarks();
        }
        Map<String, Object> entityData = new HashMap<>();
        entityData.put("Configuration", ConfigUtil.getConfiguration());
        entityData.put("TableName", invoker.getTableName());
        entityData.put("ClassName", className);
        entityData.put("Remarks", remarks);
        entityData.put("Properties", entityProperties(invoker));
        entityData.put("Methods", entityMethods(invoker));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName())
                + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getEntity());
        String fileName = ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className) + ".java";
        // 生成Entity文件
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_ENTITY, entityData, filePath, fileName);
    }

    /**
     * 生成实体类属性字段
     *
     * @return
     */
    public String entityProperties(AbstractInvoker invoker) {
        StringBuilder sb = new StringBuilder();
        tableInfos.forEach(ForEachUtil.withIndex((info, index) -> {
            if (info.getColumnName().equals(invoker.getForeignKey())) {
                return;
            }
            sb.append(index == 0 ? "" : Constant.SPACE_4);
            generateRemarks(sb, info);
            generateORMAnnotation(sb, info);
            sb.append(Constant.SPACE_4).append(String.format("private %s %s;\n", info.getPropertyType(), info.getPropertyName()));
            sb.append("\n");
        }));
        // 生成父表实体类时，直接截断后续生成依赖关系的代码
        if (Mode.ENTITY_PARENT.equals(mode)) {
            return sb.toString();
        }
        if (!StringUtil.isEmpty(invoker.getRelationalTableName()) || !StringUtil.isEmpty(invoker.getParentForeignKey())) {
            // 多对多 or 一对多
            sb.append(Constant.SPACE_4).append(String.format("private List<%s> %ss;\n", invoker.getParentClassName(),
                    StringUtil.firstToLowerCase(invoker.getParentClassName())));
            sb.append("\n");
        } else if (!StringUtil.isEmpty(invoker.getForeignKey())) {
            // 多对一
            sb.append(Constant.SPACE_4).append(String.format("private %s %s;\n", invoker.getParentClassName(),
                    StringUtil.firstToLowerCase(invoker.getParentClassName())));
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 生成实体类存取方法
     *
     * @return
     */
    public String entityMethods(AbstractInvoker invoker) {
        if (ConfigUtil.getConfiguration().isLombokEnable()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        tableInfos.forEach(ForEachUtil.withIndex((info, index) -> {
            if (info.getColumnName().equals(invoker.getForeignKey())) {
                return;
            }
            String setter = String.format("public void set%s (%s %s) { this.%s = %s; } \n\n", StringUtil.firstToUpperCase(info.getPropertyName()),
                    info.getPropertyType(), info.getPropertyName(), info.getPropertyName(), info.getPropertyName());
            sb.append(index == 0 ? "" : Constant.SPACE_4).append(setter);
            String getter = null;
            if (info.getPropertyType().equals("boolean") || info.getPropertyType().equals("Boolean")) {
                getter = String.format("public %s is%s () { return this.%s; } \n\n", info.getPropertyType(),
                        StringUtil.firstToUpperCase(info.getPropertyName()), info.getPropertyName());
            } else {
                getter = String.format("public %s get%s () { return this.%s; } \n\n", info.getPropertyType(),
                        StringUtil.firstToUpperCase(info.getPropertyName()), info.getPropertyName());
            }
            sb.append(Constant.SPACE_4).append(getter);
        }));
        // 生成父表实体类时，直接截断后续生成依赖关系的代码
        if (Mode.ENTITY_PARENT.equals(mode)) {
            return sb.toString();
        }
        if (!StringUtil.isEmpty(invoker.getRelationalTableName()) || !StringUtil.isEmpty(invoker.getParentForeignKey())) {
            // 多对多
            String setter = String.format("public void set%ss (List<%s> %ss) { this.%ss = %ss; }\n\n", invoker.getParentClassName(),
                    invoker.getParentClassName(), StringUtil.firstToLowerCase(invoker.getParentClassName()),
                    StringUtil.firstToLowerCase(invoker.getParentClassName()), StringUtil.firstToLowerCase(invoker.getParentClassName()));
            sb.append(Constant.SPACE_4).append(setter);
            String getter = String.format("public List<%s> get%ss () { return this.%ss; }\n\n", invoker.getParentClassName(),
                    invoker.getParentClassName(), StringUtil.firstToLowerCase(invoker.getParentClassName()));
            sb.append(Constant.SPACE_4).append(getter);
        } else if (!StringUtil.isEmpty(invoker.getForeignKey())) {
            // 多对一
            String setter = String.format("public void set%s (%s %s) { this.%s = %s; }\n\n", invoker.getParentClassName(),
                    invoker.getParentClassName(), StringUtil.firstToLowerCase(invoker.getParentClassName()),
                    StringUtil.firstToLowerCase(invoker.getParentClassName()), StringUtil.firstToLowerCase(invoker.getParentClassName()));
            sb.append(Constant.SPACE_4).append(setter);
            String getter = String.format("public %s get%s () { return this.%s; }\n\n", invoker.getParentClassName(), invoker.getParentClassName(),
                    StringUtil.firstToLowerCase(invoker.getParentClassName()));
            sb.append(Constant.SPACE_4).append(getter);
        }
        return sb.toString();
    }

    /**
     * 为实体属性生成注释
     *
     * @param sb   StringBuilder对象
     * @param info 列属性
     */
    public void generateRemarks(StringBuilder sb, ColumnInfo info) {
        sb.append("/**").append("\n");
        sb.append(Constant.SPACE_4).append(" * ").append(info.getRemarks()).append("\n");
        sb.append(Constant.SPACE_4).append(" */").append("\n");
    }

    /**
     * 为实体属性生成swagger注解
     * 我们不建议在entity（vo）中使用swagger注解，在dto和vo中使用swagger注解更为优雅
     *
     * @param sb   StringBuilder对象
     * @param info 列属性
     */
    public void generateSwaggerAnnotation(StringBuilder sb, ColumnInfo info) {
        if (!ConfigUtil.getConfiguration().isSwaggerEnable()) {
            return;
        }
        sb.append(String.format("@ApiModelProperty(value = \"%s\", dataType = \"%s\")",
                info.getRemarks(), info.getPropertyType()));
        sb.append("\n");
    }

    /**
     * 为实体属性生成Orm框架（jpa/mybatis-plus）注解
     *
     * @param sb
     * @param info
     */
    public void generateORMAnnotation(StringBuilder sb, ColumnInfo info) {
        if (ConfigUtil.getConfiguration().isMybatisPlusEnable()) {
            if (info.isPrimaryKey()) {
                sb.append(Constant.SPACE_4).append(String.format("@TableId(value = \"%s\", type = IdType.ASSIGN_UUID)\n", info.getColumnName()));
            } else {
                sb.append(Constant.SPACE_4).append(String.format("@TableField(value = \"%s\")\n", info.getColumnName()));
            }
        } else if (ConfigUtil.getConfiguration().isJpaEnable()) {
            if (info.isPrimaryKey()) {
                sb.append(Constant.SPACE_4).append("@Id\n");
            } else {
                sb.append(Constant.SPACE_4).append(String.format("@Column(name = \"%s\")\n", info.getColumnName()));
            }
        }
    }

}
