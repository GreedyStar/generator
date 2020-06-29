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
 * @author GreedyStar
 * @since 2018/4/20
 */
public class DaoTask extends AbstractTask {

    public DaoTask(String className) {
        super(className);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Dao填充数据
        Map<String, String> daoData = new HashMap<>();
        daoData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        daoData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        daoData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        daoData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        daoData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        daoData.put("ClassName", ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className));
        daoData.put("EntityName", StringUtil.firstToLowerCase(ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className)));
        daoData.put("DaoClassName", ConfigUtil.getConfiguration().getName().getDao().replace(Constant.PLACEHOLDER, className));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getDao());
        String fileName = ConfigUtil.getConfiguration().getName().getDao().replace(Constant.PLACEHOLDER, className) + ".java";
        // 生成dao文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_DAO, daoData, filePath, fileName);
    }
}
