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
public class VoTask extends AbstractTask {

    public VoTask(AbstractInvoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void run() throws IOException, TemplateException {
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName())
                + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getVo());
        String className = invoker.getClassName();
        // 生成ListVO
        Map<String, Object> listData = new HashMap<>();
        listData.put("Configuration", ConfigUtil.getConfiguration());
        listData.put("ClassName", className);
        listData.put("ClassType", ClassType.VO_LIST.getCode());
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_VO, listData, filePath, className + ClassType.VO_LIST.getCode() + "VO.java");
        // 生成DetailVO
        Map<String, Object> detailData = new HashMap<>();
        detailData.put("Configuration", ConfigUtil.getConfiguration());
        detailData.put("ClassName", className);
        detailData.put("ClassType", ClassType.VO_DETAIL.getCode());
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_VO, detailData, filePath, className + ClassType.VO_DETAIL.getCode() + "VO.java");
    }

}
