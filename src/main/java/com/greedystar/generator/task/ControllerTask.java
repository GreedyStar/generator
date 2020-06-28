package com.greedystar.generator.task;

import com.greedystar.generator.entity.Constant;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author GreedyStar
 * Date   2018/4/20
 */
public class ControllerTask extends AbstractTask {

    public ControllerTask(String className) {
        super(className);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Controller填充数据
        Map<String, String> controllerData = new HashMap<>();
        controllerData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        controllerData.put("ControllerPackageName", ConfigUtil.getConfiguration().getPath().getController());
        String serviceClassName;
        if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getInterf())) {
            controllerData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getService());
            serviceClassName = ConfigUtil.getConfiguration().getName().getService().replace(Constant.PLACEHOLDER, className);
        } else {
            controllerData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getInterf());
            serviceClassName = ConfigUtil.getConfiguration().getName().getInterf().replace(Constant.PLACEHOLDER, className);
        }
        controllerData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        controllerData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        controllerData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        controllerData.put("ServiceClassName", serviceClassName);
        controllerData.put("ServiceEntityName", StringUtil.firstToLowerCase(serviceClassName));
        controllerData.put("ControllerClassName", ConfigUtil.getConfiguration().getName().getController().replace(Constant.PLACEHOLDER, className));
        controllerData.put("ClassName", ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className));
        controllerData.put("EntityName", StringUtil.firstToLowerCase(ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className)));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getController());
        String fileName = ConfigUtil.getConfiguration().getName().getController().replace(Constant.PLACEHOLDER, className) + ".java";
        // 生成Controller文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_CONTROLLER, controllerData, filePath, fileName);
    }
}
