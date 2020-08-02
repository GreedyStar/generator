package com.greedystar.generator.task.base;

import com.greedystar.generator.invoker.base.AbstractInvoker;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public abstract class AbstractTask implements Serializable {
    protected AbstractInvoker invoker;

    public AbstractTask() {
    }

    /**
     * 执行任务
     *
     * @throws IOException
     * @throws TemplateException
     */
    public abstract void run() throws IOException, TemplateException;

}
