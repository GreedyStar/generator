package com.greedystar.generator.invoker.base;

import com.greedystar.generator.db.ConnectionUtil;
import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.TaskQueue;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public abstract class AbstractInvoker implements Invoker {
    /**
     * 主表名
     */
    protected String tableName;
    /**
     * 主类名
     */
    protected String className;
    /**
     * 父表名
     */
    protected String parentTableName;
    /**
     * 父类名
     */
    protected String parentClassName;
    /**
     * 外键列名
     */
    protected String foreignKey;
    /**
     * 关系表名
     */
    protected String relationalTableName;
    /**
     * 父表外键列名
     */
    protected String parentForeignKey;
    /**
     * 主表元数据
     */
    protected List<ColumnInfo> tableInfos;
    /**
     * 父表元数据
     */
    protected List<ColumnInfo> parentTableInfos;
    /**
     * 数据库连接工具
     */
    protected ConnectionUtil connectionUtil = new ConnectionUtil();
    /**
     * 任务队列
     */
    protected TaskQueue taskQueue = new TaskQueue();
    /**
     * 线程池
     */
    private ExecutorService executorPool = Executors.newFixedThreadPool(6);

    /**
     * 初始化数据源
     *
     * @throws Exception
     */
    private void initDataSource() throws Exception {
        if (!this.connectionUtil.initConnection()) {
            throw new Exception("Failed to connect to database at url:" + ConfigUtil.getConfiguration().getDb().getUrl());
        }
        getTableInfos();
        connectionUtil.closeConnection();
    }

    /**
     * 获取表元数据，模板方法，由子类实现
     *
     * @throws Exception
     */
    protected abstract void getTableInfos() throws Exception;

    /**
     * 初始化代码生成任务，模板方法，由子类实现
     */
    protected abstract void initTasks();

    /**
     * 开始生成代码
     */
    @Override
    public void execute() {
        try {
            initDataSource();
            initTasks();
            while (!taskQueue.isEmpty()) {
                AbstractTask task = taskQueue.poll();
                executorPool.execute(() -> {
                    try {
                        task.run();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TemplateException e) {
                        e.printStackTrace();
                    }
                });
            }
            executorPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
