package com.greedystar.generator.utils;

import com.greedystar.generator.entity.Mode;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.task.*;
import com.greedystar.generator.task.base.AbstractTask;

import java.util.LinkedList;

/**
 * @author GreedyStar
 * @since 2018-11-27
 */
public class TaskQueue {

    /**
     * 任务队列
     */
    private LinkedList<AbstractTask> taskQueue = new LinkedList<>();

    /**
     * 初始化共性任务，包括Controller、ServiceImpl、Service、Dao、Mapper任务
     *
     * @param invoker 执行器
     */
    private void initCommonTasks(AbstractInvoker invoker) {
        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getController())) {
            taskQueue.add(new ControllerTask(invoker));
        }
        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getService())) {
            taskQueue.add(new ServiceTask(invoker));
        }
        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getInterf())) {
            taskQueue.add(new InterfaceTask(invoker));
        }
        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getDao())) {
            taskQueue.add(new DaoTask(invoker));
        }
        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(invoker));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param invoker 执行器
     */
    public void initSingleTasks(AbstractInvoker invoker) {
        initCommonTasks(invoker);
        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(Mode.ENTITY_MAIN, invoker));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param invoker 执行器
     */
    public void initMany2OneTasks(AbstractInvoker invoker) {
        initCommonTasks(invoker);
        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(Mode.ENTITY_MAIN, invoker));
            taskQueue.add(new EntityTask(Mode.ENTITY_PARENT, invoker));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param invoker 执行器
     */
    public void initOne2ManyTasks(AbstractInvoker invoker) {
        initCommonTasks(invoker);
        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(Mode.ENTITY_MAIN, invoker));
            taskQueue.add(new EntityTask(Mode.ENTITY_PARENT, invoker));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param invoker 执行器
     */
    public void initMany2ManyTasks(AbstractInvoker invoker) {
        initCommonTasks(invoker);
        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(Mode.ENTITY_MAIN, invoker));
            taskQueue.add(new EntityTask(Mode.ENTITY_PARENT, invoker));
        }
    }

    /**
     * 任务队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    /**
     * 取出一个任务
     *
     * @return
     */
    public AbstractTask poll() {
        return taskQueue.poll();
    }

}
