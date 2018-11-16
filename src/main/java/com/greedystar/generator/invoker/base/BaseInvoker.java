package com.greedystar.generator.invoker.base;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.task.base.BaseTask;
import com.greedystar.generator.utils.ConnectionUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public abstract class BaseInvoker implements Invoker {
    protected String database;
    protected String username;
    protected String password;
    protected String tableName;
    protected String className;
    protected String parentTableName;
    protected String parentClassName;
    protected String foreignKey;
    protected String relationalTableName;
    protected String parentForeignKey;
    protected List<ColumnInfo> tableInfos;
    protected List<ColumnInfo> parentTableInfos;
    protected ConnectionUtil connectionUtil = new ConnectionUtil();
    protected Queue<BaseTask> taskQueue = new LinkedList<>();

    private void initDataSource() throws Exception {
        if (!this.connectionUtil.initConnection(this.database, this.username, this.password)) {
            throw new Exception("Failed to connect to database:" + this.database);
        }
        getTableInfos();
    }

    protected abstract void getTableInfos();

    protected abstract void initTasks();

    @Override
    public void execute() throws Exception {
        initDataSource();
        initTasks();
        while (!taskQueue.isEmpty()) {
            taskQueue.poll().run();
        }
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setParentTableName(String parentTableName) {
        this.parentTableName = parentTableName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public void setRelationalTableName(String relationalTableName) {
        this.relationalTableName = relationalTableName;
    }

    public void setParentForeignKey(String parentForeignKey) {
        this.parentForeignKey = parentForeignKey;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTableName() {
        return tableName;
    }

    public String getClassName() {
        return className;
    }

    public String getParentTableName() {
        return parentTableName;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public String getRelationalTableName() {
        return relationalTableName;
    }

    public String getParentForeignKey() {
        return parentForeignKey;
    }
}
