package com.greedystar.generator.utils;

import java.io.File;

/**
 * @author GreedyStar
 * @since 2018-09-10
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (string == null || string.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 首字母大写
     *
     * @param string
     * @return
     */
    public static String firstToUpperCase(String string) {
        StringBuilder sb = new StringBuilder();
        sb.append(string.substring(0, 1).toUpperCase()).append(string.substring(1));
        return sb.toString();
    }


    /**
     * 首字母小写
     *
     * @param string
     * @return
     */
    public static String firstToLowerCase(String string) {
        StringBuilder sb = new StringBuilder();
        sb.append(string.substring(0, 1).toLowerCase()).append(string.substring(1));
        return sb.toString();
    }

    /**
     * 数据库列名转换为实体的属性名
     *
     * @param columnName 列名
     * @return 转换后的实体属性名
     */
    public static String columnName2PropertyName(String columnName) {
        if (isEmpty(columnName)) {
            return "";
        }
        if (!columnName.contains("_")) { // 列名中不包含 “_”
            if (isAllUpperCase(columnName)) {
                return columnName.toLowerCase();
            }
            return columnName;
        }
        StringBuilder sb = new StringBuilder();
        String[] str = columnName.toLowerCase().split("_");
        sb.append(str[0]);
        for (int i = 1; i < str.length; i++) {
            sb.append(firstToUpperCase(str[i]));
        }
        return sb.toString();
    }

    /**
     * 以驼峰命名法生成类名，用于未指定类名时自动生成类名，如sys_user自动生成类名SysUser
     *
     * @param tableName
     * @return
     */
    public static String tableName2ClassName(String tableName) {
        String[] nameStrs = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String string : nameStrs) {
            sb.append(string.substring(0, 1).toUpperCase()).append(string.substring(1));
        }
        return sb.toString();
    }

    /**
     * 给定字符串除特定符号外的字符是否全部大写
     *
     * @param string
     * @return
     */
    public static boolean isAllUpperCase(String string) {
        for (Character c : string.replace("_", "").toCharArray()) {
            if (!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 包名转换为文件系统路径
     *
     * @param packageName
     * @return
     */
    public static String package2Path(String packageName) {
        if (StringUtil.isEmpty(packageName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] packages = packageName.split("\\.");
        for (String str : packages) {
            sb.append(str).append(File.separator);
        }
        return sb.toString();
    }

    /**
     * 短横线转驼峰
     *
     * @param str
     * @return
     */
    public static String line2Camel(String str) {
        return str.replace("package-name", "packageName")
                .replace("lombok-enable", "lombokEnable")
                .replace("mapper-under-source", "mapperUnderSource")
                .replace("swagger-enable", "swaggerEnable")
                .replace("mybatis-plus-enable", "mybatisPlusEnable")
                .replace("jpa-enable", "jpaEnable")
                .replace("id-strategy", "idStrategy")
                .replace("file-override", "fileOverride");
    }

}
