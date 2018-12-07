package com.greedystar.generator.invoker;

import com.greedystar.generator.invoker.base.BaseBuilder;
import com.greedystar.generator.invoker.base.BaseInvoker;
import com.greedystar.generator.task.*;
import com.greedystar.generator.utils.GeneratorUtil;
import com.greedystar.generator.utils.StringUtil;

import java.sql.SQLException;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public class SingleInvoker extends BaseInvoker {

    @Override
    protected void getTableInfos() throws SQLException {
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

        public Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        @Override
        public BaseInvoker build(){
            if (!isParamtersValid()) {
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
