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

    public void initSingleTasks(String className, String tableName, List<ColumnInfo> tableInfos) {
        initCommonTasks(className);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(className, tableName, tableInfos));
        }
    }

    public void initOne2ManyTasks(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        initCommonTasks(className);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, parentClassName, foreignKey, tableInfos));
            taskQueue.add(new EntityTask(parentClassName, parentTableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(tableName, className, parentTableName, parentClassName, foreignKey, tableInfos, parentTableInfos));
        }
    }

    public void initMany2ManyTasks(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        initCommonTasks(className);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, parentClassName, foreignKey, parentForeignKey, tableInfos));
            taskQueue.add(new EntityTask(parentClassName, parentTableInfos));
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
