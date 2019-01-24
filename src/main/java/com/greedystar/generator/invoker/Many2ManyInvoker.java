package com.greedystar.generator.invoker;

import com.greedystar.generator.invoker.base.AbstractBuilder;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.invoker.base.Invoker;
import com.greedystar.generator.task.*;
import com.greedystar.generator.utils.GeneratorUtil;
import com.greedystar.generator.utils.StringUtil;

import java.sql.SQLException;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public class Many2ManyInvoker extends AbstractInvoker {

    @Override
    protected void getTableInfos() throws SQLException {
        tableInfos = connectionUtil.getMetaData(tableName);
        parentTableInfos = connectionUtil.getMetaData(parentTableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.initMany2ManyTasks(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos);
    }

    public static class Builder extends AbstractBuilder {
        private Many2ManyInvoker invoker = new Many2ManyInvoker();

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
        public Invoker build() {
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
            if (StringUtil.isBlank(invoker.getParentTableName())) {
                throw new Exception("Expect parent table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getRelationalTableName())) {
                throw new Exception("Expect relational table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getForeignKey())) {
                throw new Exception("Expect foreign key, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getParentForeignKey())) {
                throw new Exception("Expect parent foreign key, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getClassName())) {
                invoker.setClassName(GeneratorUtil.generateClassName(invoker.getTableName()));
            }
            if (StringUtil.isBlank(invoker.getParentClassName())) {
                invoker.setParentClassName(GeneratorUtil.generateClassName(invoker.getParentTableName()));
            }
        }
    }

}
