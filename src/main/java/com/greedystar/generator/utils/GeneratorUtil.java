package com.greedystar.generator.utils;

import com.greedystar.generator.entity.ColumnInfo;

import java.sql.Types;
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
        for (ColumnInfo info : infos) {
            sb.append("private ").append(TypeUtil.parseTypeFormSqlType(info.getType())).append(" ").append(info.getName()).append(";\n");
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
        sb.append("private List<").append(parentClassName).append(">").append(" ").append(parentClassName.toLowerCase()).append("s; \n");
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
        for (ColumnInfo info : infos) {
            if (!info.getName().equals(foreignKey)) {
                sb.append("private ").append(TypeUtil.parseTypeFormSqlType(info.getType())).append(" ").append(info.getName()).append("; \n");
            }
        }
        // 外键为父表实体引用
        sb.append("private ").append(parentClassName).append(" ").append(parentClassName.toLowerCase()).append("; \n");
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
        for (ColumnInfo info : infos) {
            sb.append("public void set").append(StringUtil.firstToUpperCase(info.getName())).append(" (").append(TypeUtil.parseTypeFormSqlType(info.getType())).append(" ").append(info.getName()).append(") {this.").append(info.getName()).append(" = ").append(info.getName()).append(";} \n");
            if (info.getType() == Types.BIT || info.getType() == Types.TINYINT) {
                sb.append("public ").append(TypeUtil.parseTypeFormSqlType(info.getType())).append(" is").append(StringUtil.firstToUpperCase(info.getName())).append("(){ return ").append(info.getName()).append(";} \n");
            } else {
                sb.append("public ").append(TypeUtil.parseTypeFormSqlType(info.getType())).append(" get").append(StringUtil.firstToUpperCase(info.getName())).append("(){ return ").append(info.getName()).append(";} \n");
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
        sb.append("public void set" + parentClassName + "s (List<" + parentClassName + "> " + parentClassName.toLowerCase() + "s) { \n this." + parentClassName.toLowerCase() + "s = " + parentClassName.toLowerCase() + "s;\n} \n");
        sb.append("public List<" + parentClassName + "> get" + parentClassName + "s(){ return this." + parentClassName.toLowerCase() + "s;} \n");
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
        for (ColumnInfo info : infos) {
            if (!info.getName().equals(foreignKey)) {
                sb.append("public void set").append(StringUtil.firstToUpperCase(info.getName())).append(" (").append(TypeUtil.parseTypeFormSqlType(info.getType())).append(" ").append(info.getName()).append(") {this.").append(info.getName()).append(" = ").append(info.getName()).append(";} \n");
                if (info.getType() == Types.BIT || info.getType() == Types.TINYINT) {
                    sb.append("public ").append(TypeUtil.parseTypeFormSqlType(info.getType())).append(" is").append(StringUtil.firstToUpperCase(info.getName())).append("(){ return ").append(info.getName()).append(";} \n");
                } else {
                    sb.append("public ").append(TypeUtil.parseTypeFormSqlType(info.getType())).append(" get").append(StringUtil.firstToUpperCase(info.getName())).append("(){ return ").append(info.getName()).append(";} \n");
                }
            }
        }
        // 外键为存取父表实体引用
        sb.append("public void set").append(parentClassName).append(" (").append(parentClassName).append(" ").append(parentClassName.toLowerCase()).append(") {this.").append(parentClassName.toLowerCase()).append(" = ").append(parentClassName.toLowerCase()).append(";} \n");
        sb.append("public ").append(parentClassName).append(" get").append(parentClassName).append("(){ return this.").append(parentClassName.toLowerCase()).append(";} \n");
        return sb.toString();
    }

    /**
     * 生成Mapper ColumnMap字段，单表
     */
    public static String generateMapperColumnMap(String tableName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            sb.append(tableName).append(".").append(info.getName()).append(" AS ").append("\"").append(info.getName()).append("\",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 生成Mapper ColumnMap字段，一对多
     */
    public static String generateMapperColumnMap(String tableName, String parentTableName, List<ColumnInfo> infos, List<ColumnInfo> parentInfos, String parentEntityName, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (!info.getName().equals(foreignKey)) {
                sb.append(tableName).append(".").append(info.getName()).append(" AS ").append("\"").append(info.getName()).append("\",");
            }
        }
        for (ColumnInfo info : parentInfos) {
            sb.append(parentTableName).append(".").append(info.getName()).append(" AS ").append("\"").append(parentEntityName).append(".").append(info.getName()).append("\",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 生成Mapper ColumnMap字段，多对多
     */
    public static String generateMapperColumnMap(String tableName, String parentTableName, List<ColumnInfo> infos, List<ColumnInfo> parentInfos, String parentEntityName) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            sb.append(tableName).append(".").append(info.getName()).append(" AS ").append("\"").append(info.getName()).append("\",");
        }
        for (ColumnInfo info : parentInfos) {
            sb.append(parentTableName).append(".").append(info.getName()).append(" AS ").append("\"").append(parentEntityName).append("s.").append(info.getName()).append("\",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static String generateMapperResultMap(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.getName().equals("id")) {
                sb.append("<id column=\"id\" property=\"id\"/> \n");
            } else {
                sb.append("<result column=\"").append(info.getName()).append("\" property=\"").append(info.getName()).append("\"/> \n");
            }
        }
        return sb.toString();
    }

    public static String generateMapperParentResultMap(String parentClassName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.getName().equals("id")) {
                sb.append("<id property=\"id\" column=\"").append(parentClassName.toLowerCase()).append("s.id\"/> \n");
            } else {
                sb.append("<result property=\"").append(info.getName()).append("\" column=\"").append(parentClassName.toLowerCase()).append("s.").append(info.getName()).append("\"/> \n");
            }
        }
        return sb.toString();
    }

    /**
     * 生成Mapper Joins字段（一对多关系）
     */
    public static String generateMapperJoins(String tableName, String parentTableName, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("LEFT JOIN ").append(parentTableName).append(" on ").append(parentTableName).append(".id").append(" = ").append(tableName).append(".").append(foreignKey);
        return sb.toString();
    }

    /**
     * 生成Mapper Joins字段（多对多关系）
     */
    public static String generateMapperJoins(String tableName, String parentTableName, String relationTableName, String foreignKey, String parentForeignKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("LEFT JOIN ").append(relationTableName).append(" on ").append(relationTableName).append(".").append(foreignKey).append(" = ").append(tableName).append(".id ")
                .append("LEFT JOIN ").append(parentTableName).append(" on ").append(parentTableName).append(".id").append(" = ").append(relationTableName).append(".").append(parentForeignKey);
        return sb.toString();
    }

    /**
     * 生成Mapper 插入列名字段
     */
    public static String generateMapperInsertProperties(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            sb.append(info.getName() + ",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 生成Mapper 插入属性字段
     */
    public static String generateMapperInsertValues(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            sb.append("#{").append(info.getName()).append("},");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 生成Mapper 插入属性字段
     */
    public static String generateMapperInsertValues(List<ColumnInfo> infos, String parentEntityName, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.getName().equals(foreignKey)) {
                sb.append("#{").append(parentEntityName).append(".id},");
            } else {
                sb.append("#{").append(info.getName()).append("},");
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 生成Mapper 更新属性字段
     */
    public static String generateMapperUpdateProperties(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            sb.append(info.getName()).append(" = #{").append(info.getName()).append("},");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 生成Mapper 更新属性字段
     */
    public static String generateMapperUpdateProperties(List<ColumnInfo> infos, String parentEntityName, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.getName().equals(foreignKey)) {
                sb.append(info.getName()).append(" = #{").append(parentEntityName).append(".id},");
            } else {
                sb.append(info.getName()).append(" = #{").append(info.getName()).append("},");
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
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
