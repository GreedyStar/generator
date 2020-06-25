package com.greedystar.generator.utils;

import com.greedystar.generator.entity.Configuration;
import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Author GreedyStar
 * Date   2018/9/7
 */
public class ConfigUtil {
    private static Configuration configuration;

    static {
        URL url = ConfigUtil.class.getClassLoader().getResource("generator.yaml");
        if (url.getPath().contains("jar")) { // 用户未提供配置文件
            System.err.println("Can not find file named 'generator.yaml' at resources path, please make sure that you have defined that file.");
            System.exit(0);
        } else {
            try {
                String configStr = StringUtil.line2Camel(IOUtils.toString((InputStream) url.getContent()));
                InputStream inputStream = IOUtils.toInputStream(configStr, StandardCharsets.UTF_8.name());
                Yaml yaml = new Yaml();
                configuration = yaml.loadAs(inputStream, Configuration.class);
                if (null == configuration.getDb() || null == configuration.getPath()) {
                    throw new Exception("Can not find attributes named 'db' and 'path' in generator.yml, please make sure that you have configured those attributes.");
                }
                if (StringUtil.isBlank(configuration.getDb().getUrl()) || StringUtil.isBlank(configuration.getDb().getUsername())) {
                    throw new Exception("Please configure the correct db connection parameters in generator.yml, i.e. url, username and password.");
                }
                // 用户未配置类名后缀，那么添加一个默认的空对象
                if (configuration.getSuffix() == null) {
                    configuration.setSuffix(new Configuration.Suffix());
                }
            } catch (Exception e) {
                System.err.println("Syntax error in 'generator.yaml', please check it out.");
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

}
