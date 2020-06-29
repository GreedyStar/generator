package com.greedystar.generator.invoker;

import com.greedystar.generator.invoker.base.AbstractBuilder;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.invoker.base.Invoker;
import com.greedystar.generator.task.*;
import com.greedystar.generator.utils.GeneratorUtil;
import com.greedystar.generator.utils.StringUtil;

import java.sql.SQLException;

/**
 * @author GreedyStar
 * @since 2018/9/5
 */
public class SingleInvoker extends AbstractInvoker {

    private SingleInvoker() {

    }

    @Override
    protected void getTableInfos() throws Exception {
        tableInfos = connectionUtil.getMetaData(tableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.initSingleTasks(tableName, className, tableInfos);
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
            if (StringUtil.isBlank(invoker.getTableName())) {
                throw new Exception("Expect table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getClassName())) {
                invoker.setClassName(GeneratorUtil.generateClassName(invoker.getTableName()));
            }
        }
    }

}
