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
        mapperData.put("EntityName", StringUtil.firstToLowerCase(className));
        mapperData.put("TableName", tableName);
        mapperData.put("InsertProperties", GeneratorUtil.generateMapperInsertProperties(tableInfos));
        mapperData.put("PrimaryKey", getPrimaryKeyColumnInfo(tableInfos).getColumnName());
        mapperData.put("WhereId", "#{" + getPrimaryKeyColumnInfo(tableInfos).getPropertyName() + "}");
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className)));
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos));
            mapperData.put("ParentResultMap", GeneratorUtil.generateMapperParentResultMap(parentClassName, parentTableInfos));
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, StringUtil.firstToLowerCase(parentClassName)));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos));
            mapperData.put("Joins", GeneratorUtil.generateMapperJoins(tableName, parentTableName, relationalTableName, foreignKey, parentForeignKey, getPrimaryKeyColumnInfo(tableInfos).getColumnName(), getPrimaryKeyColumnInfo(parentTableInfos).getColumnName()));
            mapperData.put("ParentEntityName", StringUtil.firstToLowerCase(parentClassName));
            mapperData.put("ParentClassName", parentClassName);
        } else if (!StringUtil.isBlank(foreignKey)) { // 一对多
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className), StringUtil.firstToLowerCase(parentClassName), foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getPropertyName()));
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getPropertyName()));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey));
            mapperData.put("Joins", GeneratorUtil.generateMapperJoins(tableName, parentTableName, foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getColumnName()));
        } else { // 单表
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className)));
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, tableInfos));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos));
            mapperData.put("Joins", "");
        }
        String filePath = FileUtil.getResourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getMapper());
        String fileName = className + "Mapper.xml";
        // 生成Mapper文件
        if (!StringUtil.isBlank(parentForeignKey)) {
            FileUtil.generateToJava(FreemarketConfigUtils.TYPE_MAPPER_M2M, mapperData, filePath + fileName);
        } else {
            FileUtil.generateToJava(FreemarketConfigUtils.TYPE_MAPPER, mapperData, filePath + fileName);
        }
    }

    private ColumnInfo getPrimaryKeyColumnInfo(List<ColumnInfo> list) {
        for (ColumnInfo columnInfo : list) {
            if (columnInfo.isPrimaryKey()) {
                return columnInfo;
            }
        }
        return null;
    }

}
