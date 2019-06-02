package com.greedystar.generator.utils;

import com.greedystar.generator.utils.convertor.DefaultConvertor;
import com.greedystar.generator.utils.convertor.TypeConvertor;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class ConvertorUtil {
    private static TypeConvertor converter = new DefaultConvertor();

    public static void setConverter(TypeConvertor converter) {
        ConvertorUtil.converter = converter;
    }

    /**
     * 将数据库数据类型转换为Java数据类型
     *
     * @param typeName
     * @return
     */
    protected static String parseTypeFormSqlType(String typeName) {
        return converter.convertType(typeName);
    }

}
