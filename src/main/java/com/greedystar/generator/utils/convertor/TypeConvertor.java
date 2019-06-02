package com.greedystar.generator.utils.convertor;

/**
 * Author GreedyStar
 * Date   2019/6/2
 */
public interface TypeConvertor {

    /**
     * 将JDBC类型转换为Java类型
     *
     * @param typeName JDBC类型名
     */
    String convertType(String typeName);

}
