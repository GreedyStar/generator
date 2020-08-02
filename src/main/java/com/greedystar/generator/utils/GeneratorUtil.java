package com.greedystar.generator.utils;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Constant;

import java.util.List;


/**
 * @author GreedyStar
 * @since 2018/4/19
 */
public class GeneratorUtil {

    /**
     * 为实体属性生成注释
     *
     * @param sb   StringBuilder对象
     * @param info 列属性
     */
    public static void generateRemarks(StringBuilder sb, ColumnInfo info) {
        sb.append("/**").append("\n");
        sb.append(Constant.SPACE_4).append(" * ").append(info.getRemarks()).append("\n");
        sb.append(Constant.SPACE_4).append(" */").append("\n");
    }

    /**
     * 为实体属性生成swagger注解
     *
     * @param sb   StringBuilder对象
     * @param info 列属性
     */
    public static void generateSwaggerAnnotation(StringBuilder sb, ColumnInfo info) {
        sb.append(Constant.SPACE_4).append("@ApiModelProperty(value = \"").append(info.getRemarks()).append("\", dataType = \"").append(info.getPropertyType()).append("\")");
        sb.append("\n");
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
