package com.greedystar.generator.task;

import com.greedystar.generator.entity.Configuration;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.FileUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.greedystar.generator.utils.FreemarkerConfigUtil.*;
import static com.greedystar.generator.utils.StringUtil.package2Path;
import static java.io.File.separator;

/**
 * 项目框架
 */
public class ProjectStructureTask extends AbstractTask {
    @Override
    public void run() throws IOException, TemplateException {
        //1. 生成pom
        Map<String, Object> data = new HashMap<>();
        Configuration configuration = ConfigUtil.getConfiguration();
        data.put("Configuration", configuration);
        String filePath = configuration.getProjectPath() + configuration.getProjectName() + separator;

        // 生成文件
        FileUtil.generateToJava(TYPE_POM, data, filePath, "pom.xml");

        //2. 生成yml配置
        String resourcesPath = FileUtil.getResourcePath();
        FileUtil.generateToJava(TYPE_APPLICATION_FILE, data, resourcesPath, "application.yml");

        //3. 生成swagger
        String rootPath = FileUtil.getSourcePath() + package2Path(configuration.getPackageName()) + separator;
        FileUtil.generateToJava(TYPE_SWAGGER_CONFIG, data, rootPath, "SwaggerConfig.java");

        //4. 生成Main class
        FileUtil.generateToJava(TYPE_BOOTSTRAP_CLASS, data, rootPath, "Application.java");

        System.out.println("生成工程结束，api接口页面访问：http://localhost:port/swagger-ui/index.html");
    }
}
