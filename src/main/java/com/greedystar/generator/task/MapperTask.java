package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.task.base.BaseTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author GreedyStar
 * Date   2018/4/20
 */
public class MapperTask extends BaseTask {

    /**
     * 单表Mapper
     */
    public MapperTask(String className, String tableName, List<ColumnInfo> infos) {
        this(tableName, className, null, null, null, infos, null);
    }

    /**
     * 一对多Mapper
     */
    public MapperTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        this(tableName, className, parentTableName, parentClassName, foreignKey, null, null, tableInfos, parentTableInfos);
    }

    /**
     * 多对多Mapper
     */
    public MapperTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        super(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Mapper填充数据
        System.out.println("Generating " + className + "Mapper.xml");
        Map<String, String> mapperData = new HashMap<>();
        mapperData.put("PackageName", ConfigUtil.getConfiguration().getPackageName() + "." + ConfigUtil.getConfiguration().getPath().getDao());
        mapperData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        mapperData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        mapperData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        mapperData.put("ClassName", className);
        mapperData.put("EntityName", className.toLowerCase());
        mapperData.put("TableName", tableName);
        mapperData.put("InsertProperties", GeneratorUtil.generateMapperInsertProperties(tableInfos));
        mapperData.put("WhereId", "#{id}");
        if (!StringUtil.isBlank(parentForeignKey)) { // 多表
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos));
            mapperData.put("ParentResultMap", GeneratorUtil.generateMapperParentResultMap(parentClassName, parentTableInfos));
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, parentClassName.toLowerCase()));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos, parentClassName.toLowerCase(), foreignKey));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos, parentClassName.toLowerCase(), foreignKey));
            mapperData.put("Joins", GeneratorUtil.generateMapperJoins(tableName, parentTableName, relationalTableName, foreignKey, parentForeignKey));
            mapperData.put("ParentEntityName", parentClassName.toLowerCase());
            mapperData.put("ParentClassName", parentClassName);
        } else if (!StringUtil.isBlank(foreignKey)) {
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, parentClassName.toLowerCase(), foreignKey));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos, parentClassName.toLowerCase(), foreignKey));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos, parentClassName.toLowerCase(), foreignKey));
            mapperData.put("Joins", GeneratorUtil.generateMapperJoins(tableName, parentTableName, foreignKey));
        } else { // 单表
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, tableInfos));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos));
            mapperData.put("Joins", "");
        }
        String filePath = FileUtil.getResourcePath() + ConfigUtil.getConfiguration().getPath().getMapper() + File.separator;
        String fileName = className + "Mapper.xml";
        createFilePathIfNotExists(filePath);
        // 生成Mapper文件
        if (!StringUtil.isBlank(parentForeignKey)) {
            FileUtil.generateToJava(FreemarketConfigUtils.TYPE_MAPPER_M2M, mapperData, filePath + fileName);
        } else {
            FileUtil.generateToJava(FreemarketConfigUtils.TYPE_MAPPER, mapperData, filePath + fileName);
        }
    }
}
