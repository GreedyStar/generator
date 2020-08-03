package com.greedystar.generator.invoker;

import com.greedystar.generator.invoker.base.AbstractBuilder;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.utils.StringUtil;

/**
 * @author GreedyStar
 * @since 2018/9/5
 */
public class Many2ManyInvoker extends AbstractInvoker {

    private Many2ManyInvoker() {

    }

    @Override
    protected void queryTableMeta() throws Exception {
        tableInfos = connectionUtil.getMetaData(tableName);
        parentTableInfos = connectionUtil.getMetaData(parentTableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.initMany2ManyTasks(this);
    }

    public static class Builder extends AbstractBuilder {

        public Builder() {
            invoker = new Many2ManyInvoker();
        }

        public Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        public Builder setParentTableName(String parentTableName) {
            invoker.setParentTableName(parentTableName);
            return this;
        }

        public Builder setParentClassName(String parentClassName) {
            invoker.setParentClassName(parentClassName);
            return this;
        }

        public Builder setForeignKey(String foreignKey) {
            invoker.setForeignKey(foreignKey);
            return this;
        }

        public Builder setRelationTableName(String relationTableName) {
            invoker.setRelationalTableName(relationTableName);
            return this;
        }

        public Builder setParentForeignKey(String parentForeignKey) {
            invoker.setParentForeignKey(parentForeignKey);
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
            if (StringUtil.isEmpty(invoker.getRelationalTableName())) {
                throw new Exception("Expect relational table's name, but get an empty String.");
            }
            if (StringUtil.isEmpty(invoker.getForeignKey())) {
                throw new Exception("Expect foreign key, but get an empty String.");
            }
            if (StringUtil.isEmpty(invoker.getParentForeignKey())) {
                throw new Exception("Expect parent foreign key, but get an empty String.");
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
