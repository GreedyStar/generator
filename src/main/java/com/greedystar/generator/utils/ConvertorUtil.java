package com.greedystar.generator.utils;

import com.greedystar.generator.utils.convertor.DefaultConvertor;
import com.greedystar.generator.utils.convertor.TypeConvertor;

import java.sql.JDBCType;

/**
 * 类型转换器工具类
 *
 * @author GreedyStar
 * @since 2018/4/19
 */
public class ConvertorUtil {
    /**
     * 类型转换器
     */
    private volatile static TypeConvertor convertor;

    /**
     * 将数据库数据类型转换为Java数据类型
     *
     * @param type
     * @return
     */
    public static String parseTypeFormSqlType(JDBCType type) {
        /*
         * 用户配置了错误的TypeConvertor会导致convertor为null
         * 在生成多表关系代码时，会有两个EntityTask并发执行，防止创建多个实例，采用double-check的单例模式
         */
        if (convertor == null) {
            synchronized (ConvertorUtil.class) {
                if (convertor == null) {
                    convertor = newInstance();
                }
            }
        }
        return convertor.convertType(type);
    }

    private static TypeConvertor newInstance() {
        TypeConvertor convertor;
        String convertorClass = ConfigUtil.getConfiguration().getConvertor();
        if (StringUtil.isBlank(convertorClass)) { // 用户未配置类型转换器，使用默认转换器
            convertor = new DefaultConvertor();
        } else {
            // 加载用户定义的类型转换器
            try {
                Class clazz = Class.forName(ConfigUtil.getConfiguration().getConvertor());
                convertor = (TypeConvertor) clazz.newInstance();
            } catch (Exception e) {
                System.err.println("未找到" + convertorClass + ", 使用默认类型转换器, 请检查配置文件");
                convertor = new DefaultConvertor();
            }
        }
        return convertor;
    }

}
