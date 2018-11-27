package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.task.base.BaseTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author GreedyStar
 * Date   2018/4/20
 */
public class EntityTask extends BaseTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public EntityTask(String className, List<ColumnInfo> infos) {
        this(className, null, null, infos);
    }

    /**
     * 一对多关系生成主表实体
     */
    public EntityTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos) {
        this(className, parentClassName, foreignKey, null, tableInfos);
    }

    /**
     * 多对多关系生成主表实体
     */
    public EntityTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Entity填充数据
        System.out.println("Generating " + className + ".java");
        Map<String, String> entityData = new HashMap<>();
        entityData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        entityData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        entityData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        entityData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        entityData.put("ClassName", className);
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos));
        } else if (!StringUtil.isBlank(foreignKey)) { // 多对一：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos, foreignKey));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos, foreignKey));
        } else { // 单表关系
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(tableInfos));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(tableInfos));
        }
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getEntity());
        String fileName = className + ".java";
        // 生成Entity文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_ENTITY, entityData, filePath + fileName);
    }
}
