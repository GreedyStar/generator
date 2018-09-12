package com.greedystar.generator.utils;

/**
 * Author GreedyStar
 * Date   2018-09-10
 */
public class StringUtil {

    public static boolean isBlank(String string) {
        if (string == null || string.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 首字母大写
     */
    public static String firstToUpperCase(String string) {
        StringBuilder sb = new StringBuilder();
        sb.append(string.substring(0, 1).toUpperCase()).append(string.substring(1));
        return sb.toString();
    }

}
