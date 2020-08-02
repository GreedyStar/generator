package com.greedystar.generator.invoker;

import com.greedystar.generator.invoker.base.AbstractBuilder;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.utils.GeneratorUtil;
import com.greedystar.generator.utils.StringUtil;

/**
 * @author GreedyStar
 * @since 2018/9/5
 */
public class SingleInvoker extends AbstractInvoker {

    private SingleInvoker() {

    }

    @Override
    protected void queryTableMeta() throws Exception {
        tableInfos = connectionUtil.getMetaData(tableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.initSingleTasks(this);
    }

    public static class Builder extends AbstractBuilder {

        public Builder() {
            invoker = new SingleInvoker();
        }

        public Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        @Override
        public void checkBeforeBuild() throws Exception {
            if (StringUtil.isEmpty(invoker.getTableName())) {
                throw new Exception("Expect table's name, but get an empty String.");
            }
            if (StringUtil.isEmpty(invoker.getClassName())) {
                invoker.setClassName(GeneratorUtil.generateClassName(invoker.getTableName()));
            }
        }
    }

}
