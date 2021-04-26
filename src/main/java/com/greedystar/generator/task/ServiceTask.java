package com.greedystar.generator.task;

import com.greedystar.generator.entity.Constant;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class ServiceTask extends AbstractTask {

    public ServiceTask(AbstractInvoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Service填充数据
        Map<String, Object> serviceData = new HashMap<>();
        serviceData.put("Configuration", ConfigUtil.getConfiguration());
        serviceData.put("ClassName", ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, invoker.getClassName()));
        serviceData.put("EntityName", StringUtil.firstToLowerCase(invoker.getClassName()));
        serviceData.put("DaoClassName", ConfigUtil.getConfiguration().getName().getDao().replace(Constant.PLACEHOLDER, invoker.getClassName()));
        serviceData.put("DaoEntityName", StringUtil.firstToLowerCase(ConfigUtil.getConfiguration().getName().getDao()
                .replace(Constant.PLACEHOLDER, invoker.getClassName())));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName())
                + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getService());
        String fileName;
        /*
         * 根据用户是否配置了path节点下的interf属性来判断是否采用接口+实现类的方式
         */
        String serviceClassName = ConfigUtil.getConfiguration().getName().getService().replace(Constant.PLACEHOLDER, invoker.getClassName());
        if (StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getInterf())) {
            serviceData.put("ServiceClassName", serviceClassName);
            serviceData.put("Implements", "");
            serviceData.put("InterfaceImport", "");
            serviceData.put("Override", "");
            fileName = ConfigUtil.getConfiguration().getName().getService().replace(Constant.PLACEHOLDER, invoker.getClassName()) + ".java";
        } else {
            // Service接口实现类默认由Impl结尾
            serviceClassName = serviceClassName.contains("Impl") ? serviceClassName : serviceClassName + "Impl";
            serviceData.put("ServiceClassName", serviceClassName);
            serviceData.put("Implements", "implements " + ConfigUtil.getConfiguration().getName().getInterf()
                    .replace(Constant.PLACEHOLDER, invoker.getClassName()));
            serviceData.put("InterfaceImport", "import " + ConfigUtil.getConfiguration().getPackageName() + "."
                    + ConfigUtil.getConfiguration().getPath().getInterf() + "."
                    + ConfigUtil.getConfiguration().getName().getInterf().replace(Constant.PLACEHOLDER, invoker.getClassName()) + ";");
            serviceData.put("Override", "\n    @Override");
            fileName = serviceClassName + ".java";
        }
        // 生成Service文件
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_SERVICE, serviceData, filePath, fileName);
    }
}
