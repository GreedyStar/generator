package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Constant;
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
public class MapperTask extends AbstractTask {

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
        // 构造Mapper填充数据
        Map<String, Object> mapperData = new HashMap<>();
        mapperData.put("configuration", ConfigUtil.getConfiguration());
        mapperData.put("ClassName", ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className));
        mapperData.put("EntityName", StringUtil.firstToLowerCase(ConfigUtil.getConfiguration().getName().getEntity().replace(Constant.PLACEHOLDER, className)));
        mapperData.put("DaoClassName", ConfigUtil.getConfiguration().getName().getDao().replace(Constant.PLACEHOLDER, className));
        mapperData.put("TableName", tableName);
        mapperData.put("PrimaryKey", getPrimaryKeyColumnInfo(tableInfos).getColumnName());
        mapperData.put("WhereId", "#{" + getPrimaryKeyColumnInfo(tableInfos).getPropertyName() + "}");
//        mapperData.put("Id", "#{id}");
        mapperData.put("PrimaryColumn", getPrimaryKeyColumnInfo(tableInfos));
        mapperData.put("InsertProperties", GeneratorUtil.generateMapperInsertProperties(tableInfos));
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, StringUtil.firstToLowerCase(parentClassName)));
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos));
            mapperData.put("Association", "");
            mapperData.put("Collection", GeneratorUtil.generateMapperCollection(parentTableInfos, parentClassName, ConfigUtil.getConfiguration().getPackageName() + "." + ConfigUtil.getConfiguration().getPath().getEntity()));
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className)));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos));
            mapperData.put("Joins", GeneratorUtil.generateMapperJoins(tableName, parentTableName, relationalTableName, foreignKey, parentForeignKey, getPrimaryKeyColumnInfo(tableInfos).getColumnName(), getPrimaryKeyColumnInfo(parentTableInfos).getColumnName()));
        } else if (!StringUtil.isBlank(foreignKey)) { // 一对多
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey));
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos, foreignKey));
            mapperData.put("Association", GeneratorUtil.generateMapperAssociation(parentTableInfos, parentClassName, ConfigUtil.getConfiguration().getPackageName() + "." + ConfigUtil.getConfiguration().getPath().getEntity()));
            mapperData.put("Collection", "");
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className), StringUtil.firstToLowerCase(parentClassName), foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getPropertyName()));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getPropertyName()));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getPropertyName()));
            mapperData.put("Joins", GeneratorUtil.generateMapperJoins(tableName, parentTableName, foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getColumnName()));
        } else { // 单表
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, tableInfos));
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos));
            mapperData.put("Association", "");
            mapperData.put("Collection", "");
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className)));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos));
            mapperData.put("Joins", "");
        }
        String filePath;
        if (ConfigUtil.getConfiguration().isMapperUnderSource()) {
            // mapper-under-source = true，表示将Mapper映射文件放在源文件目录下
            filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName())
                    + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getMapper());
        } else {
            // 默认情况下，将Mapper映射文件放在resources下
            filePath = FileUtil.getResourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getMapper());
        }
        String fileName = ConfigUtil.getConfiguration().getName().getMapper().replace(Constant.PLACEHOLDER, className) + ".xml";
        // 生成Mapper文件
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_MAPPER, mapperData, filePath, fileName);
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
