package com.greedystar.generator.utils;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.task.*;
import com.greedystar.generator.task.base.AbstractTask;

import java.util.LinkedList;
import java.util.List;

/**
 * Author GreedyStar
 * Date   2018-11-27
 */
public class TaskQueue {

    private LinkedList<AbstractTask> taskQueue = new LinkedList<>();

    /**
     * 初始化共性任务，包括Controller、ServiceImpl、Service、Dao任务
     *
     * @param className 类名
     */
    private void initCommonTasks(String className) {
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getController())) {
            taskQueue.add(new ControllerTask(className));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getService())) {
            taskQueue.add(new ServiceTask(className));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getInterf())) {
            taskQueue.add(new InterfaceTask(className));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getDao())) {
            taskQueue.add(new DaoTask(className));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param tableName  表名
     * @param className  类名
     * @param tableInfos 表元数据
     */
    public void initSingleTasks(String tableName, String className, List<ColumnInfo> tableInfos) {
        initCommonTasks(className);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(tableName, className, tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(className, tableName, tableInfos));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param tableName        主表名
     * @param className        主类名
     * @param parentTableName  父表名
     * @param parentClassName  父类名
     * @param foreignKey       外键列
     * @param tableInfos       主表元数据
     * @param parentTableInfos 父表元数据
     */
    public void initOne2ManyTasks(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        initCommonTasks(className);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(tableName, className, parentClassName, foreignKey, tableInfos));
            taskQueue.add(new EntityTask(tableName, parentClassName, parentTableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(tableName, className, parentTableName, parentClassName, foreignKey, tableInfos, parentTableInfos));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param tableName           主表名
     * @param className           主类名
     * @param parentTableName     父表名
     * @param parentClassName     父类名
     * @param foreignKey          主表外键列
     * @param parentForeignKey    父表外键列
     * @param relationalTableName 关系表名
     * @param tableInfos          主表元数据
     * @param parentTableInfos    父表元数据
     */
    public void initMany2ManyTasks(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        initCommonTasks(className);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(tableName, className, parentClassName, relationalTableName, foreignKey, parentForeignKey, tableInfos));
            taskQueue.add(new EntityTask(tableName, parentClassName, parentTableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos));
        }
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public AbstractTask poll() {
        return taskQueue.poll();
    }

}
