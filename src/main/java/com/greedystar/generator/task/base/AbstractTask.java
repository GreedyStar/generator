package com.greedystar.generator.task.base;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.StringUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Author GreedyStar
 * Date   2018/4/20
 */
public abstract class AbstractTask implements Serializable {
    /**
     * 业务表名
     */
    protected String tableName;
    /**
     * 业务表对应实体类名
     */
    protected String className;
    /**
     * 父表名
     */
    protected String parentTableName;
    /**
     * 父表对应实体类名
     */
    protected String parentClassName;
    /**
     * 业务表中参照父表主键的外键（关系表中参照业务表主键的外键）
     */
    protected String foreignKey;
    /**
     * 关系表名
     */
    protected String relationalTableName;
    /**
     * 关系表中参照父表主键的外键
     */
    protected String parentForeignKey;
    /**
     * 业务表元数据
     */
    protected List<ColumnInfo> tableInfos;
    /**
     * 父表元数据
     */
    protected List<ColumnInfo> parentTableInfos;

    /**
     * Controller、Service、Dao
     *
     * @param className
     */
    public AbstractTask(String className) {
        this.className = className;
    }

    /**
     * Entity
     *
     * @param className
     * @param parentClassName
     * @param foreignKey
     * @param tableInfos
     */
    public AbstractTask(String tableName, String className, String parentClassName, String relationalTableName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        this.tableName = tableName;
        this.className = className;
        this.parentClassName = parentClassName;
        this.relationalTableName = relationalTableName;
        this.foreignKey = foreignKey;
        this.parentForeignKey = parentForeignKey;
        this.tableInfos = tableInfos;
    }


    /**
     * Mapper
     *
     * @param tableName
     * @param className
     * @param parentTableName
     * @param parentClassName
     * @param foreignKey
     * @param parentForeignKey
     * @param tableInfos
     * @param parentTableInfos
     */
    public AbstractTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        this.tableName = tableName;
        this.className = className;
        this.parentTableName = parentTableName;
        this.parentClassName = parentClassName;
        this.foreignKey = foreignKey;
        this.parentForeignKey = parentForeignKey;
        this.relationalTableName = relationalTableName;
        this.tableInfos = tableInfos;
        this.parentTableInfos = parentTableInfos;
    }

    public abstract void run() throws IOException, TemplateException;

    @Deprecated
    protected void createFilePathIfNotExists(String filePath) {
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPackageName())) { // 用户配置了包名，不进行检测
            return;
        }
        File file = new File(filePath);
        if (!file.exists()) { // 检测文件路径是否存在，不存在则创建
            file.mkdir();
        }
    }

}
