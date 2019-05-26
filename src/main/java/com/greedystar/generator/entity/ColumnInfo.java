package com.greedystar.generator.entity;

import com.greedystar.generator.utils.StringUtil;

import java.io.Serializable;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class ColumnInfo implements Serializable {
    private String columnName; // 列名
    private String type; // 类型代码
    private String remarks; // 列备注
    private String tableRemarks; // 表注释
    private String propertyName; // 属性名
    private boolean isPrimaryKey; // 是否主键

    public ColumnInfo() {

    }

    public ColumnInfo(String columnName, String type, String remarks, String tableRemarks, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.type = type;
        this.remarks = remarks;
        this.tableRemarks = tableRemarks;
        this.propertyName = StringUtil.columnName2PropertyName(columnName);
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTableRemarks() {
        return tableRemarks;
    }

    public void setTableRemarks(String tableRemarks) {
        this.tableRemarks = tableRemarks;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }
}
