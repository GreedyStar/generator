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
 * Date   2019/1/24
 */
public class InterfaceTask extends AbstractTask {

    public InterfaceTask(String className) {
        super(className);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Service接口填充数据
        Map<String, String> interfaceData = new HashMap<>();
        interfaceData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        interfaceData.put("InterfacePackageName", ConfigUtil.getConfiguration().getPath().getInterf());
        interfaceData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        interfaceData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        interfaceData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        interfaceData.put("ClassName", ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className));
        interfaceData.put("EntityName", StringUtil.firstToLowerCase(ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className)));
        interfaceData.put("InterfaceClassName", ConfigUtil.getConfiguration().getName().getInterf().replace(Constant.PLACEHOLDER, className));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getInterf());
        String fileName = ConfigUtil.getConfiguration().getName().getInterf().replace(Constant.PLACEHOLDER, className) + ".java";
        // 生成Service接口文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_INTERFACE, interfaceData, filePath, fileName);
    }
}
