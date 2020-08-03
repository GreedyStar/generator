package com.greedystar.generator.invoker;

import com.greedystar.generator.invoker.base.AbstractBuilder;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.utils.StringUtil;

/**
 * @author GreedyStar
 * @date 2020/7/31
 */
public class Many2OneInvoker extends AbstractInvoker {

    private Many2OneInvoker() {

    }

    @Override
    protected void queryTableMeta() throws Exception {
        tableInfos = connectionUtil.getMetaData(tableName);
        parentTableInfos = connectionUtil.getMetaData(parentTableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.initMany2OneTasks(this);
    }

    public static class Builder extends AbstractBuilder {

        public Builder() {
            invoker = new Many2OneInvoker();
        }

        public Many2OneInvoker.Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Many2OneInvoker.Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        public Many2OneInvoker.Builder setParentTableName(String parentTableName) {
            invoker.setParentTableName(parentTableName);
            return this;
        }

        public Many2OneInvoker.Builder setParentClassName(String parentClassName) {
            invoker.setParentClassName(parentClassName);
            return this;
        }

        public Many2OneInvoker.Builder setForeignKey(String foreignKey) {
            invoker.setForeignKey(foreignKey);
            return this;
        }

        @Override
        public void checkBeforeBuild() throws Exception {
            if (StringUtil.isEmpty(invoker.getTableName())) {
                throw new Exception("Expect table's name, but get an empty String.");
            }
            if (StringUtil.isEmpty(invoker.getParentTableName())) {
                throw new Exception("Expect parent table's name, but get an empty String.");
            }
            if (StringUtil.isEmpty(invoker.getForeignKey())) {
                throw new Exception("Expect foreign key, but get an empty String.");
            }
            if (StringUtil.isEmpty(invoker.getClassName())) {
                invoker.setClassName(StringUtil.tableName2ClassName(invoker.getTableName()));
            }
            if (StringUtil.isEmpty(invoker.getParentClassName())) {
                invoker.setParentClassName(StringUtil.tableName2ClassName(invoker.getParentTableName()));
            }
        }
    }

}
