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
            sb.append("private " + TypeUtil.parseTypeFormSqlType(info.getType()) + " " + info.getName() + "; \n");
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
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            sb.append("private " + TypeUtil.parseTypeFormSqlType(info.getType()) + " " + info.getName() + "; \n");
        }
        sb.append("private List<" + parentClassName + "> " + parentClassName.toLowerCase() + "s; \n");
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
                sb.append("private " + TypeUtil.parseTypeFormSqlType(info.getType()) + " " + info.getName() + "; \n");
            }
        }
        // 外键为父表实体引用
        sb.append("private " + parentClassName + " " + parentClassName.toLowerCase() + "; \n");
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
            sb.append("public void set" + StringUtil.firstToUpperCase(info.getName()) + " (" + TypeUtil.parseTypeFormSqlType(info.getType()) + " " + info.getName() + ") {this." + info.getName() + " = " + info.getName() + ";} \n");
            if (info.getType() == Types.BIT || info.getType() == Types.TINYINT) {
                sb.append("public " + TypeUtil.parseTypeFormSqlType(info.getType()) + " is" + StringUtil.firstToUpperCase(info.getName()) + "(){ return " + info.getName() + ";} \n");
            } else {
                sb.append("public " + TypeUtil.parseTypeFormSqlType(info.getType()) + " get" + StringUtil.firstToUpperCase(info.getName()) + "(){ return " + info.getName() + ";} \n");
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
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            sb.append("public void set" + StringUtil.firstToUpperCase(info.getName()) + " (" + TypeUtil.parseTypeFormSqlType(info.getType()) + " " + info.getName() + ") {this." + info.getName() + " = " + info.getName() + ";} \n");
            if (info.getType() == Types.BIT || info.getType() == Types.TINYINT) {
                sb.append("public " + TypeUtil.parseTypeFormSqlType(info.getType()) + " is" + StringUtil.firstToUpperCase(info.getName()) + "(){ return " + info.getName() + ";} \n");
            } else {
                sb.append("public " + TypeUtil.parseTypeFormSqlType(info.getType()) + " get" + StringUtil.firstToUpperCase(info.getName()) + "(){ return " + info.getName() + ";} \n");
            }
        }
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
                sb.append("public void set" + StringUtil.firstToUpperCase(info.getName()) + " (" + TypeUtil.parseTypeFormSqlType(info.getType()) + " " + info.getName() + ") {this." + info.getName() + " = " + info.getName() + ";} \n");
                if (info.getType() == Types.BIT || info.getType() == Types.TINYINT) {
                    sb.append("public " + TypeUtil.parseTypeFormSqlType(info.getType()) + " is" + StringUtil.firstToUpperCase(info.getName()) + "(){ return " + info.getName() + ";} \n");
                } else {
                    sb.append("public " + TypeUtil.parseTypeFormSqlType(info.getType()) + " get" + StringUtil.firstToUpperCase(info.getName()) + "(){ return " + info.getName() + ";} \n");
                }
            }
        }
        // 外键为存取父表实体引用
        sb.append("public void set" + parentClassName + " (" + parentClassName + " " + parentClassName.toLowerCase() + ") {this." + parentClassName.toLowerCase() + " = " + parentClassName.toLowerCase() + ";} \n");
        sb.append("public " + parentClassName + " get" + parentClassName + "(){ return this." + parentClassName.toLowerCase() + ";} \n");
        return sb.toString();
    }

    /**
     * 生成Mapper ColumnMap字段，单表
     */
    public static String generateMapperColumnMap(String tableName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            sb.append(tableName + "." + info.getName() + " AS " + "\"" + info.getName() + "\",");
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
                sb.append(tableName + "." + info.getName() + " AS " + "\"" + info.getName() + "\",");
            }
        }
        for (ColumnInfo info : parentInfos) {
            sb.append(parentTableName + "." + info.getName() + " AS " + "\"" + parentEntityName + "." + info.getName() + "\",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 生成Mapper ColumnMap字段，单表
     */
    public static String generateMapperColumnMap(String tableName, String parentTableName, List<ColumnInfo> infos, List<ColumnInfo> parentInfos, String parentEntityName) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            sb.append(tableName + "." + info.getName() + " AS " + "\"" + info.getName() + "\",");
        }
        for (ColumnInfo info : parentInfos) {
            sb.append(parentTableName + "." + info.getName() + " AS " + "\"" + parentEntityName + "s." + info.getName() + "\",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static String generateMapperResultMap(List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.getName().equals("id")) {
                sb.append("<id column=\"id\" property=\"id\"/> \n");
            } else {
                sb.append("<result column=\"" + info.getName() + "\" property=\"" + info.getName() + "\"/> \n");
            }
        }
        return sb.toString();
    }

    public static String generateMapperParentResultMap(String parentClassName, List<ColumnInfo> infos) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo info : infos) {
            if (info.getName().equals("id")) {
                sb.append("<id property=\"id\" column=\"" + parentClassName.toLowerCase() + "s.id\"/> \n");
            } else {
                sb.append("<result property=\"" + info.getName() + "\" column=\"" + parentClassName.toLowerCase() + "s." + info.getName() + "\"/> \n");
            }
        }
        return sb.toString();
    }

    /**
     * 生成Mapper Tables字段（一对多关系）
     */
    public static String generateMapperTables(String parentTableName) {
        StringBuilder sb = new StringBuilder();
        sb.append(", " + parentTableName);
        return sb.toString();
    }

    /**
     * 生成Mapper Tables字段（多对多关系）
     */
    public static String generateMapperTables(String parentTableName, String relationTableName) {
        StringBuilder sb = new StringBuilder();
        sb.append(", " + parentTableName);
        sb.append(", " + relationTableName);
        return sb.toString();
    }

    /**
     * 生成Mapper Joins字段（一对多关系）
     */
    public static String generateMapperJoins(String tableName, String parentTableName, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        sb.append(tableName + "." + foreignKey + " = " + parentTableName + ".id");
        return sb.toString();
    }

    /**
     * 生成Mapper Joins字段（多对多关系）
     */
    public static String generateMapperJoins(String tableName, String parentTableName, String relationTableName, String foreignKey, String parentForeignKey) {
        StringBuilder sb = new StringBuilder();
        sb.append(relationTableName + "." + foreignKey + " = " + tableName + ".id");
        sb.append(" AND ");
        sb.append(relationTableName + "." + parentForeignKey + " = " + parentTableName + ".id");
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
            sb.append("#{" + info.getName() + "},");
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
                sb.append("#{" + parentEntityName + ".id},");
            } else {
                sb.append("#{" + info.getName() + "},");
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
            sb.append(info.getName() + " = #{" + info.getName() + "},");
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
                sb.append(info.getName() + " = #{" + parentEntityName + ".id},");
            } else {
                sb.append(info.getName() + " = #{" + info.getName() + "},");
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
