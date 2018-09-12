package com.greedystar.generator.invoker;

import com.greedystar.generator.invoker.base.BaseBuilder;
import com.greedystar.generator.invoker.base.BaseInvoker;
import com.greedystar.generator.task.*;
import com.greedystar.generator.utils.GeneratorUtil;
import com.greedystar.generator.utils.StringUtil;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public class SingleInvoker extends BaseInvoker {

    @Override
    protected void getTableInfos() {
        tableInfos = connectionUtil.getMetaData(tableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.add(new DaoTask(className));
        taskQueue.add(new ServiceTask(className));
        taskQueue.add(new ControllerTask(className));
        taskQueue.add(new EntityTask(className, tableInfos));
        taskQueue.add(new MapperTask(className, tableName, tableInfos));
    }

    public static class Builder extends BaseBuilder {
        private SingleInvoker invoker = new SingleInvoker();

        public Builder setDatabase(String database) {
            invoker.setDatabase(database);
            return this;
        }

        public Builder setUsername(String username) {
            invoker.setUsername(username);
            return this;
        }

        public Builder setPassword(String password) {
            invoker.setPassword(password);
            return this;
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
        public BaseInvoker build() throws Exception {
            if (!isParamtersValid(invoker)) {
                return null;
            }
            return invoker;
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
