package com.greedystar.generator.task;

import com.greedystar.generator.entity.ClassType;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.FreemarkerConfigUtil;
import com.greedystar.generator.utils.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class DtoTask extends AbstractTask {

    public DtoTask(AbstractInvoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void run() throws IOException, TemplateException {
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName())
                + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getDto());
        String className = invoker.getClassName();
        // 生成CreateDTO文件
        Map<String, Object> createDate = new HashMap<>();
        createDate.put("Configuration", ConfigUtil.getConfiguration());
        createDate.put("ClassName", className);
        createDate.put("ClassType", ClassType.DTO_CREATE.getCode());
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_DTO, createDate, filePath, className + ClassType.DTO_CREATE.getCode() + "DTO.java");
        // 生成UpdateDTO文件
        Map<String, Object> updateDate = new HashMap<>();
        updateDate.put("Configuration", ConfigUtil.getConfiguration());
        updateDate.put("ClassName", className);
        updateDate.put("ClassType", ClassType.DTO_UPDATE.getCode());
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_DTO, updateDate, filePath, className + ClassType.DTO_UPDATE.getCode() + "DTO.java");
        // 生成QueryDTO文件
        Map<String, Object> queryDate = new HashMap<>();
        queryDate.put("Configuration", ConfigUtil.getConfiguration());
        queryDate.put("ClassName", className);
        queryDate.put("ClassType", ClassType.DTO_QUERY.getCode());
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_DTO, queryDate, filePath, className + ClassType.DTO_QUERY.getCode() + "DTO.java");
    }

}
