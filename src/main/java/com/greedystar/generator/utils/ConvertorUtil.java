package com.greedystar.generator.utils;

import com.greedystar.generator.utils.convertor.DefaultConvertor;
import com.greedystar.generator.utils.convertor.TypeConvertor;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class ConvertorUtil {
    private static TypeConvertor converter;

    static {
        String convertorClass = ConfigUtil.getConfiguration().getConvertor();
        if (StringUtil.isBlank(convertorClass)) { // 用户未配置类型转换器，使用默认转换器
            converter = new DefaultConvertor();
        } else { // 加载用户定义的类型转换器
            try {
                Class clazz = Class.forName(ConfigUtil.getConfiguration().getConvertor());
                converter = (TypeConvertor) clazz.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

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
