package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Constant;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class EntityTask extends AbstractTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public EntityTask(String tableName, String className, List<ColumnInfo> infos) {
        this(tableName, className, null, null, infos);
    }

    /**
     * 一对多关系生成主表实体
     */
    public EntityTask(String tableName, String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos) {
        this(tableName, className, parentClassName, null, foreignKey, null, tableInfos);
    }

    /**
     * 多对多关系生成主表实体
     */
    public EntityTask(String tableName, String className, String parentClassName, String relationalTableName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(tableName, className, parentClassName, relationalTableName, foreignKey, parentForeignKey, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Entity填充数据
        Map<String, String> entityData = new HashMap<>();
        entityData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        entityData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        entityData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        entityData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        entityData.put("ClassName", ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className));
        entityData.put("Remarks", tableInfos.get(0).getTableRemarks());
        StringBuilder importStringBuilder = new StringBuilder(); // import相关字段
        StringBuilder annotationStringBuilder = new StringBuilder(); // 实体类上标注的注解字段
        if (ConfigUtil.getConfiguration().isLombokEnable()) { // lombok
            importStringBuilder.append("import lombok.Data;\n");
            annotationStringBuilder.append("@Data\n");
        }
        entityData.put("Import", importStringBuilder.length() == 0 ? "" : importStringBuilder.substring(0, importStringBuilder.length() - 1));
        entityData.put("Annotation", annotationStringBuilder.length() == 0 ? "" : annotationStringBuilder.substring(0, annotationStringBuilder.length() - 1));
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, relationalTableName, foreignKey, parentForeignKey, tableInfos));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos));
        } else if (!StringUtil.isBlank(foreignKey)) { // 多对一：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos, foreignKey));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos, foreignKey));
        } else { // 单表关系
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(tableInfos));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(tableInfos));
        }
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getEntity());
        String fileName = ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className) + ".java";
        // 生成Entity文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_ENTITY, entityData, filePath, fileName);
    }
}
