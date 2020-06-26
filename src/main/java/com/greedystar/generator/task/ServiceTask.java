package com.greedystar.generator.task;

import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.FreemarketConfigUtil;
import com.greedystar.generator.utils.StringUtil;
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
public class ServiceTask extends AbstractTask {

    public ServiceTask(String className) {
        super(className);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Service填充数据
        Map<String, String> serviceData = new HashMap<>();
        serviceData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        serviceData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getService());
        serviceData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        serviceData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        serviceData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        serviceData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        serviceData.put("DaoClassName", className + ConfigUtil.getConfiguration().getSuffix().getDao());
        serviceData.put("DaoEntityName", StringUtil.firstToLowerCase(className) + ConfigUtil.getConfiguration().getSuffix().getDao());
        serviceData.put("ClassName", className + ConfigUtil.getConfiguration().getSuffix().getEntity());
        serviceData.put("EntityName", StringUtil.firstToLowerCase(className) + ConfigUtil.getConfiguration().getSuffix().getEntity());
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getService());
        String fileName;
        if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getInterf())) { // 表示不生成Service接口文件
            serviceData.put("ServiceClassName", className + ConfigUtil.getConfiguration().getSuffix().getService());
            serviceData.put("Implements", "");
            serviceData.put("Override", "");
            serviceData.put("InterfaceImport", "");
            fileName = className + ConfigUtil.getConfiguration().getSuffix().getService() + ".java";
        } else {
            serviceData.put("ServiceClassName", className + ConfigUtil.getConfiguration().getSuffix().getService() + "Impl");
            serviceData.put("Implements", "implements " + className + ConfigUtil.getConfiguration().getSuffix().getService());
            serviceData.put("Override", "@Override");
            serviceData.put("InterfaceImport", "import " + ConfigUtil.getConfiguration().getPackageName() + ConfigUtil.getConfiguration().getPath().getInterf() + "." + className + ConfigUtil.getConfiguration().getSuffix().getService() + ";");
            fileName = className + ConfigUtil.getConfiguration().getSuffix().getService() + "Impl.java";
        }
        // 生成Service文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_SERVICE, serviceData, filePath, fileName);
    }
}
