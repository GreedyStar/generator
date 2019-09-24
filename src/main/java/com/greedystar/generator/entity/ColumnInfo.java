package com.greedystar.generator.entity;

import com.greedystar.generator.utils.StringUtil;

import java.io.Serializable;
import java.sql.JDBCType;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class ColumnInfo implements Serializable {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 类型代码
     */
    private JDBCType type;
    /**
     * 列备注
     */
    private String remarks;
    /**
     * 表备注
     */
    private String tableRemarks;
    /**
     * 属性名
     */
    private String propertyName;
    /**
     * 是否主键
     */
    private boolean isPrimaryKey;

    public ColumnInfo(String columnName, int type, String remarks, String tableRemarks, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.type = JDBCType.valueOf(type);
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

    public JDBCType getType() {
        return type;
    }

    public void setType(JDBCType type) {
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
