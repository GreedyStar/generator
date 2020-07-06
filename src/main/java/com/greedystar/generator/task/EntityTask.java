package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Constant;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class EntityTask extends AbstractTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public EntityTask(String tableName, String className, List<ColumnInfo> infos) {
        this(tableName, className, null, null, infos);
    }

    /**
     * 一对多关系生成主表实体
     */
    public EntityTask(String tableName, String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos) {
        this(tableName, className, parentClassName, null, foreignKey, null, tableInfos);
    }

    /**
     * 多对多关系生成主表实体
     */
    public EntityTask(String tableName, String className, String parentClassName, String relationalTableName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(tableName, className, parentClassName, relationalTableName, foreignKey, parentForeignKey, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Entity填充数据
        Map<String, Object> entityData = new HashMap<>();
        entityData.put("configuration", ConfigUtil.getConfiguration());
        entityData.put("ClassName", ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className));
        entityData.put("Remarks", tableInfos.get(0).getTableRemarks());
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(tableInfos, parentClassName, relationalTableName, foreignKey, parentForeignKey));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(tableInfos, parentClassName));
        } else if (!StringUtil.isBlank(foreignKey)) { // 多对一：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(tableInfos, parentClassName, foreignKey));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(tableInfos, parentClassName, foreignKey));
        } else { // 单表关系
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(tableInfos));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(tableInfos));
        }
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getEntity());
        String fileName = ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className) + ".java";
        // 生成Entity文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_ENTITY, entityData, filePath, fileName);
    }
}
