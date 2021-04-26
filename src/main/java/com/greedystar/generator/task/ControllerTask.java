package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Constant;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class ControllerTask extends AbstractTask {

    public ControllerTask(AbstractInvoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Controller填充数据
        Map<String, Object> controllerData = new HashMap<>();
        controllerData.put("Configuration", ConfigUtil.getConfiguration());
        String serviceClassName;
        String serviceImport;
        if (StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getInterf())) {
            serviceClassName = ConfigUtil.getConfiguration().getName().getService().replace(Constant.PLACEHOLDER, invoker.getClassName());
            serviceImport = String.format("import %s.%s.%s;", ConfigUtil.getConfiguration().getPackageName(),
                    ConfigUtil.getConfiguration().getPath().getService(), serviceClassName);
        } else {
            serviceClassName = ConfigUtil.getConfiguration().getName().getInterf().replace(Constant.PLACEHOLDER, invoker.getClassName());
            serviceImport = String.format("import %s.%s.%s;", ConfigUtil.getConfiguration().getPackageName(),
                    ConfigUtil.getConfiguration().getPath().getInterf(), serviceClassName);
        }
        controllerData.put("ServiceImport", serviceImport);
        controllerData.put("ServiceClassName", serviceClassName);
        controllerData.put("ServiceEntityName", StringUtil.firstToLowerCase(serviceClassName));
        controllerData.put("ControllerClassName", ConfigUtil.getConfiguration().getName().getController()
                .replace(Constant.PLACEHOLDER, invoker.getClassName()));
        controllerData.put("ClassName", ConfigUtil.getConfiguration().getName().getEntity()
                .replace(Constant.PLACEHOLDER, invoker.getClassName()));
        controllerData.put("EntityName", StringUtil.firstToLowerCase(invoker.getClassName()));
        controllerData.put("pkType", getPrimaryKeyType(invoker.getTableInfos()));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) +
                StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getController());
        String fileName = ConfigUtil.getConfiguration().getName().getController().replace(Constant.PLACEHOLDER, invoker.getClassName()) + ".java";
        // 生成Controller文件
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_CONTROLLER, controllerData, filePath, fileName);
    }

    /**
     * 获取主键列对应的属性类型
     *
     * @param columnInfos
     * @return
     */
    private String getPrimaryKeyType(List<ColumnInfo> columnInfos) {
        if (!ConfigUtil.getConfiguration().isJpaEnable()) {
            return "Serializable";
        }
        for (ColumnInfo info : columnInfos) {
            if (info.isPrimaryKey()) {
                return info.getPropertyType();
            }
        }
        return "Serializable";
    }

}
