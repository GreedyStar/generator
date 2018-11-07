package com.greedystar.generator.utils;

import com.greedystar.generator.entity.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.net.URL;

/**
 * Author GreedyStar
 * Date   2018/9/7
 */
public class ConfigUtil {
    private static Configuration configuration;

    private static void initConfig() {
        URL url = ConfigUtil.class.getClassLoader().getResource("generator.yaml");
        InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream("generator.yaml");
        if (null == url) {
            configuration = initWithDefaultParams();
        } else {
            Yaml yaml = new Yaml();
            configuration = yaml.loadAs(inputStream, Configuration.class);
        }
    }

    private static Configuration initWithDefaultParams() {
        Configuration configuration = new Configuration();
        configuration.setAuthor("unknown");
        configuration.setPackageName("");
        configuration.setPath(new Configuration.Path("controller", "service", "dao", "entity", "mappers"));
        configuration.setDb(new Configuration.Db("", "", ""));
        return configuration;
    }

    public static Configuration getConfiguration() {
        if (null == configuration) {
            initConfig();
        }
        return configuration;
    }

}
