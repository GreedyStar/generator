package com.greedystar.generator.utils;

import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @author GreedyStar
 * @since 2018/4/19
 */
public class FreemarkerConfigUtil {
    /**
     * 模板路径
     */
    private static String path = new File(FreemarkerConfigUtil.class.getClassLoader().getResource("ftls").getFile()).getPath();
    /**
     * freemarker配置
     */
    private static volatile Configuration configuration;
    public final static int TYPE_ENTITY = 0;
    public final static int TYPE_DAO = 1;
    public final static int TYPE_SERVICE = 2;
    public final static int TYPE_CONTROLLER = 3;
    public final static int TYPE_MAPPER = 4;
    public final static int TYPE_INTERFACE = 5;
    public final static int TYPE_DTO = 6;
    public final static int TYPE_VO = 7;

    public static Configuration getInstance() {
        if (null == configuration) {
            synchronized (FreemarkerConfigUtil.class) {
                if (null == configuration) {
                    configuration = new Configuration(Configuration.VERSION_2_3_23);
                    try {
                        if (path.contains("jar")) {
                            configuration.setClassForTemplateLoading(FreemarkerConfigUtil.class, "/ftls");
                        } else {
                            configuration.setDirectoryForTemplateLoading(new File(path));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    configuration.setEncoding(Locale.CHINA, "utf-8");
                }
            }
        }
        return configuration;
    }
}
