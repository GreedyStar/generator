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
        serviceData.put("DaoClassName", ConfigUtil.getConfiguration().getName().getDao().replace(Constant.PLACEHOLDER, className));
        serviceData.put("DaoEntityName", StringUtil.firstToLowerCase(ConfigUtil.getConfiguration().getName().getDao().replace(Constant.PLACEHOLDER, className)));
        serviceData.put("ClassName", ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className));
        serviceData.put("EntityName", StringUtil.firstToLowerCase(ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className)));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getService());
        String fileName;
        if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getInterf())) { // 表示不生成Service接口文件
            serviceData.put("ServiceClassName", ConfigUtil.getConfiguration().getName().getService().replace(Constant.PLACEHOLDER, className));
            serviceData.put("Implements", "");
            serviceData.put("Override", "");
            serviceData.put("InterfaceImport", "");
            fileName = ConfigUtil.getConfiguration().getName().getService().replace(Constant.PLACEHOLDER, className) + ".java";
        } else {
            String serviceClassName = ConfigUtil.getConfiguration().getName().getService().contains("Impl") ?
                    ConfigUtil.getConfiguration().getName().getService().replace(Constant.PLACEHOLDER, className) : ConfigUtil.getConfiguration().getName().getService().replace(Constant.PLACEHOLDER, className) + "Impl";
            serviceData.put("ServiceClassName", serviceClassName);
            serviceData.put("Implements", "implements " + ConfigUtil.getConfiguration().getName().getInterf().replace(Constant.PLACEHOLDER, className));
            serviceData.put("Override", "\n    @Override");
            serviceData.put("InterfaceImport", "import " + ConfigUtil.getConfiguration().getPackageName() + "." + ConfigUtil.getConfiguration().getPath().getInterf() + "." + ConfigUtil.getConfiguration().getName().getInterf().replace(Constant.PLACEHOLDER, className) + ";");
            fileName = serviceClassName + ".java";
        }
        // 生成Service文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_SERVICE, serviceData, filePath, fileName);
    }
}
