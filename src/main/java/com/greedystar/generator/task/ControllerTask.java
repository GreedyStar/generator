package com.greedystar.generator.task;

import com.greedystar.generator.task.base.BaseTask;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.FreemarketConfigUtils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author GreedyStar
 * Date   2018/4/20
 */
public class ControllerTask extends BaseTask {

    public ControllerTask(String className) {
        super(className);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Controller填充数据
        System.out.println("Generating " + className + "Controller.java");
        Map<String, String> controllerData = new HashMap<>();
        controllerData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        controllerData.put("ModulePackageName", ConfigUtil.getConfiguration().getPath().getController());
        controllerData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        controllerData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        controllerData.put("ClassName", className);
        controllerData.put("EntityName", className.toLowerCase());
        String filePath = FileUtil.getSourcePath() + ConfigUtil.getConfiguration().getPackagePath() + ConfigUtil.getConfiguration().getPath().getController() + File.separator;
        String fileName = className + "Controller.java";
        createFilePathIfNotExists(filePath);
        // 生成Controller文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_CONTROLLER, controllerData, filePath + fileName);
    }
}
