package com.greedystar.generator.utils;

import com.greedystar.generator.task.*;

import java.util.LinkedList;

/**
 * Author GreedyStar
 * Date   2018-11-27
 */
public class TaskQueue<E> extends LinkedList<E> {

    /**
     * 根据类型检查是否配置了相应的代码路径，未配置则不添加任务
     *
     * @param task 任务
     * @return
     */
    @Override
    public boolean add(E task) {
        if (task instanceof ControllerTask) {
            if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getController())) {
                return false;
            }
        }
        if (task instanceof ServiceTask) {
            if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getService())) {
                return false;
            }
        }
        if (task instanceof DaoTask) {
            if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getDao())) {
                return false;
            }
        }
        if (task instanceof EntityTask) {
            if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
                return false;
            }
        }
        if (task instanceof MapperTask) {
            if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
                return false;
            }
        }
        return super.add(task);
    }
}
