package com.greedystar.generator.utils;

import com.greedystar.generator.entity.ColumnInfo;

import java.sql.Types;
import java.util.Iterator;
import java.util.List;


/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class GeneratorUtil {


    /**
     * 生成实体类属性字段（基本数据类型，用于单表关系）
     *
     * @param infos 表结构
     * @return
     */
    public static String generateEntityProperties(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("    ");
            }
            sb.append("private ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append(infos.get(i).getPropertyName()).append(";\n");
        }
        return sb.toString();
    }


    /**
     * 生成实体类属性字段（列表，用于多对多关系）
     *
     * @param parentClassName 父表类名
     * @param infos           表结构
     * @return
     */
    public static String generateEntityProperties(String parentClassName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder(generateEntityProperties(infos));
        sb.append("    ").append("private List<").append(parentClassName).append(">").append(" ").append(StringUtil.firstToLowerCase(parentClassName)).append("s; \n");
        return sb.toString();
    }

    /**
     * 生成实体类属性字段（引用，用于一对多关系）
     *
     * @param parentClassName 父表类名
     * @param infos           表结构
     * @param foreignKey      外键
     * @return
     */
    public static String generateEntityProperties(String parentClassName, List<ColumnInfo> infos, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).isPrimaryKey() || !infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("    ");
                }
                sb.append("private ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append(infos.get(i).getPropertyName()).append("; \n");
            }
        }
        // 外键为父表实体引用
        sb.append("    ").append("private ").append(parentClassName).append(" ").append(StringUtil.firstToLowerCase(parentClassName)).append("; \n");
        return sb.toString();
    }


    /**
     * 生成实体类存取方法（用于单表关系）
     *
     * @param infos 表结构
     * @return
     */
    public static String generateEntityMethods(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("    ");
            }
            sb.append("public void set").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append(" (").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append(infos.get(i).getPropertyName()).append(") {this.").append(infos.get(i).getPropertyName()).append(" = ").append(infos.get(i).getPropertyName()).append(";} \n");
            if (infos.get(i).getType() == Types.BIT || infos.get(i).getType() == Types.TINYINT) {
                sb.append("    ").append("public ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" is").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append("(){ return ").append(infos.get(i).getPropertyName()).append(";} \n");
            } else {
                sb.append("    ").append("public ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" get").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append("(){ return ").append(infos.get(i).getPropertyName()).append(";} \n");
            }
        }
        return sb.toString();
    }

    /**
     * 生成实体类存取方法（用于多对多关系）
     *
     * @param parentClassName
     * @param infos
     * @return
     */
    public static String generateEntityMethods(String parentClassName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder(generateEntityMethods(infos));
        sb.append("    ").append("public void set" + parentClassName + "s (List<" + parentClassName + "> " + StringUtil.firstToLowerCase(parentClassName) + "s) { \n this." + StringUtil.firstToLowerCase(parentClassName) + "s = " + StringUtil.firstToLowerCase(parentClassName) + "s;\n} \n");
        sb.append("    ").append("public List<" + parentClassName + "> get" + parentClassName + "s(){ return this." + StringUtil.firstToLowerCase(parentClassName) + "s;} \n");
        return sb.toString();
    }

    /**
     * 生成实体类存取方法（用于一对多关系）
     *
     * @param parentClassName 父表类名
     * @param infos           表结构
     * @param foreignKey      外键
     * @return
     */
    public static String generateEntityMethods(String parentClassName, List<ColumnInfo> infos, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).isPrimaryKey() || !infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("    ");
                }
                sb.append("public void set").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append(" (").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" ").append(infos.get(i).getPropertyName()).append(") {this.").append(infos.get(i).getPropertyName()).append(" = ").append(infos.get(i).getPropertyName()).append(";} \n");
                if (infos.get(i).getType() == Types.BIT || infos.get(i).getType() == Types.TINYINT) {
                    sb.append("    ").append("public ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" is").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append("(){ return ").append(infos.get(i).getPropertyName()).append(";} \n");
                } else {
                    sb.append("    ").append("public ").append(TypeUtil.parseTypeFormSqlType(infos.get(i).getType())).append(" get").append(StringUtil.firstToUpperCase(infos.get(i).getPropertyName())).append("(){ return ").append(infos.get(i).getPropertyName()).append(";} \n");
                }
            }
        }
        // 外键为存取父表实体引用
        sb.append("    ").append("public void set").append(parentClassName).append(" (").append(parentClassName).append(" ").append(StringUtil.firstToLowerCase(parentClassName)).append(") {this.").append(StringUtil.firstToLowerCase(parentClassName)).append(" = ").append(StringUtil.firstToLowerCase(parentClassName)).append(";} \n");
        sb.append("    ").append("public ").append(parentClassName).append(" get").append(parentClassName).append("(){ return this.").append(StringUtil.firstToLowerCase(parentClassName)).append(";} \n");
        return sb.toString();
    }

    /**
     * 生成Mapper ColumnMap字段，单表
     */
    public static String generateMapperColumnMap(String tableName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("        ");
            }
            sb.append(tableName).append(".").append(infos.get(i).getColumnName()).append(" AS ").append("\"").append(infos.get(i).getPropertyName()).append("\",\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper ColumnMap字段，一对多
     */
    public static String generateMapperColumnMap(String tableName, String parentTableName, List<ColumnInfo> infos, List<ColumnInfo> parentInfos, String parentEntityName, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).isPrimaryKey() || !infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("        ");
                }
                sb.append(tableName).append(".").append(infos.get(i).getColumnName()).append(" AS ").append("\"").append(infos.get(i).getPropertyName()).append("\",\n");
            }
        }
        for (ColumnInfo info : parentInfos) {
            sb.append("        ").append(parentTableName).append(".").append(info.getColumnName()).append(" AS ").append("\"").append(parentEntityName).append(".").append(info.getPropertyName()).append("\",\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper ColumnMap字段，多对多
     */
    public static String generateMapperColumnMap(String tableName, String parentTableName, List<ColumnInfo> infos, List<ColumnInfo> parentInfos, String parentEntityName) {
        StringBuilder sb = new StringBuilder(generateMapperColumnMap(tableName, infos));
        sb.append(",\n");
        for (ColumnInfo info : parentInfos) {
            sb.append("        ").append(parentTableName).append(".").append(info.getColumnName()).append(" AS ").append("\"").append(parentEntityName).append("s.").append(info.getPropertyName()).append("\",\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 对应模板文件${ResultMap}字段 用于 single、one2many、many2many
     *
     * @param infos
     * @return
     */
    public static String generateMapperResultMap(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.isPrimaryKey()) {
                sb.append("<id column=\"").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            } else {
                sb.append("        ").append("<result column=\"").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            }
        }
        return sb.toString();
    }

    /**
     * 对应模板文件${Association}字段
     * 用于 one2many
     *
     * @param parentInfos
     * @param parentClassName
     * @param parentClassPackage
     * @return
     */
    public static String generateMapperAssociation(List<ColumnInfo> parentInfos, String parentClassName, String parentClassPackage) {
        StringBuilder sb = new StringBuilder();
        sb.append("<association property=\"").append(StringUtil.firstToLowerCase(parentClassName)).append("\" javaType=\"").append(parentClassPackage).append(".").append(parentClassName).append("\">\n");
        for (ColumnInfo info : parentInfos) {
            if (info.isPrimaryKey()) {
                sb.append("            ").append("<id column=\"").append(StringUtil.firstToLowerCase(parentClassName)).append(".").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            } else {
                sb.append("            ").append("<result column=\"").append(StringUtil.firstToLowerCase(parentClassName)).append(".").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            }
        }
        sb.append("        ").append("</association>");
        return sb.toString();
    }


    /**
     * 对应模板文件${Collection}字段
     * 用于 many2many
     *
     * @param parentInfos
     * @param parentClassName
     * @param parentClassPackage
     * @return
     */
    public static String generateMapperCollection(List<ColumnInfo> parentInfos, String parentClassName, String parentClassPackage) {
        StringBuilder sb = new StringBuilder();
        sb.append("<collection property=\"").append(StringUtil.firstToLowerCase(parentClassName)).append("s\" ofType=\"").append(parentClassPackage).append(".").append(parentClassName).append("\">\n");
        for (ColumnInfo info : parentInfos) {
            if (info.isPrimaryKey()) {
                sb.append("            ").append("<id column=\"").append(StringUtil.firstToLowerCase(parentClassName)).append("s").append(".").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            } else {
                sb.append("            ").append("<result column=\"").append(StringUtil.firstToLowerCase(parentClassName)).append("s").append(".").append(info.getPropertyName()).append("\" property=\"").append(info.getPropertyName()).append("\"/> \n");
            }
        }
        sb.append("        ").append("</collection>");
        return sb.toString();
    }


    /**
     * 生成Mapper Joins字段（一对多关系）
     *
     * @param tableName
     * @param parentTableName
     * @param foreignKey
     * @param parentPrimaryKey
     * @return
     */
    public static String generateMapperJoins(String tableName, String parentTableName, String foreignKey, String parentPrimaryKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("LEFT JOIN ").append(parentTableName).append(" on ").append(parentTableName).append(".").append(parentPrimaryKey).append(" = ").append(tableName).append(".").append(foreignKey);
        return sb.toString();
    }


    /**
     * 生成Mapper Joins字段（多对多关系）
     *
     * @param tableName
     * @param parentTableName
     * @param relationTableName
     * @param foreignKey
     * @param parentForeignKey
     * @param primaryKey
     * @param parentPrimaryKey
     * @return
     */
    public static String generateMapperJoins(String tableName, String parentTableName, String relationTableName, String foreignKey, String parentForeignKey, String primaryKey, String parentPrimaryKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("LEFT JOIN ").append(relationTableName).append(" on ").append(relationTableName).append(".").append(foreignKey).append(" = ").append(tableName).append(".").append(primaryKey).append(" \n")
                .append("        ").append("LEFT JOIN ").append(parentTableName).append(" on ").append(parentTableName).append(".").append(parentPrimaryKey).append(" = ").append(relationTableName).append(".").append(parentForeignKey);
        return sb.toString();
    }


    /**
     * 生成Mapper 插入列名字段（所有关系皆用）
     *
     * @param infos
     * @return
     */
    public static String generateMapperInsertProperties(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("            ");
            }
            sb.append(infos.get(i).getColumnName() + ",\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 插入属性字段（单表，多对多）
     */
    public static String generateMapperInsertValues(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("            ");
            }
            sb.append("#{").append(infos.get(i).getPropertyName()).append("},\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 批量插入属性字段（单表，多对多）
     */
    public static String generateMapperInsertBatchValues(List<ColumnInfo> infos, String entityName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("            ");
            }
            sb.append("#{").append(entityName).append(".").append(infos.get(i).getPropertyName()).append("},\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 插入属性字段（一对多）
     */
    public static String generateMapperInsertValues(List<ColumnInfo> infos, String parentEntityName, String foreignKey, String primaryKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("            ");
                }
                sb.append("#{").append(parentEntityName).append(".").append(primaryKey).append("},\n"); // 此处id需要修改为primarykey
            } else {
                if (i != 0) {
                    sb.append("            ");
                }
                sb.append("#{").append(infos.get(i).getPropertyName()).append("},\n");
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 批量插入属性字段（一对多）
     */
    public static String generateMapperInsertBatchValues(List<ColumnInfo> infos, String entityName, String parentEntityName, String foreignKey, String primaryKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("            ");
                }
                sb.append("#{").append(entityName).append(".").append(parentEntityName).append(".").append(primaryKey).append("},\n"); // 此处id需要修改为primarykey
            } else {
                if (i != 0) {
                    sb.append("            ");
                }
                sb.append("#{").append(entityName).append(".").append(infos.get(i).getPropertyName()).append("},\n");
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 更新属性字段
     */
    public static String generateMapperUpdateProperties(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (i != 0) {
                sb.append("        ");
            }
            sb.append(infos.get(i).getColumnName()).append(" = #{").append(infos.get(i).getPropertyName()).append("},\n");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 生成Mapper 更新属性字段
     */
    public static String generateMapperUpdateProperties(List<ColumnInfo> infos, String parentEntityName, String foreignKey, String primaryKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("        ");
                }
                sb.append(infos.get(i).getColumnName()).append(" = #{").append(parentEntityName).append(".").append(primaryKey).append("},\n");
            } else {
                if (i != 0) {
                    sb.append("        ");
                }
                sb.append(infos.get(i).getColumnName()).append(" = #{").append(infos.get(i).getPropertyName()).append("},\n");
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    /**
     * 以驼峰命名法生成类名，用于未指定类名时自动生成类名，如sys_user自动生成类名SysUser
     *
     * @param tableName
     * @return
     */
    public static String generateClassName(String tableName) {
        String[] nameStrs = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String string : nameStrs) {
            sb.append(string.substring(0, 1).toUpperCase()).append(string.substring(1));
        }
        return sb.toString();
    }

}
