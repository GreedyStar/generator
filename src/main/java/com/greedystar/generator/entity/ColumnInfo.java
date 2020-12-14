package com.greedystar.generator.entity;

import com.greedystar.generator.utils.ConvertorUtil;
import com.greedystar.generator.utils.StringUtil;

import java.io.Serializable;
import java.sql.JDBCType;

/**
 * 数据列实体
 *
 * @author GreedyStar
 * @since 2018/4/19
 */
public class ColumnInfo implements Serializable {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列名 -- 属性名
     */
    private String propertyName;
    /**
     * 数据列类型
     */
    private JDBCType columnType;
    /**
     * 数据列类型 -- Java类型
     */
    private String propertyType;
    /**
     * 列备注
     */
    private String remarks;
    /**
     * 表备注
     */
    private String tableRemarks;
    /**
     * 是否主键
     */
    private boolean isPrimaryKey;

    public ColumnInfo(String columnName, int columnType, String remarks, String tableRemarks, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.propertyName = StringUtil.columnName2PropertyName(columnName);
        this.columnType = JDBCType.valueOf(columnType);
        this.propertyType = ConvertorUtil.parseTypeFormSqlType(JDBCType.valueOf(columnType));
        this.remarks = remarks;
        this.tableRemarks = tableRemarks;
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public JDBCType getColumnType() {
        return columnType;
    }

    public void setColumnType(JDBCType columnType) {
        this.columnType = columnType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
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

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }
}
