package com.greedystar.generator.application;

import com.greedystar.generator.invoker.Many2ManyInvoker;
import com.greedystar.generator.invoker.One2ManyInvoker;
import com.greedystar.generator.invoker.SingleInvoker;
import com.greedystar.generator.invoker.base.Invoker;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public class Main {

    public static void main(String[] args) throws Exception {
        single();
    }

    public static void many2many() throws Exception {
        Invoker invoker = new Many2ManyInvoker.Builder()
                .setDatabase("generator-demo")
                .setUsername("root")
                .setPassword(null)
                .setTableName("user")
                .setClassName("User")
                .setParentTableName("role")
                .setParentClassName("Role")
                .setRelationTableName("user_role")
                .setForeignKey("userId")
                .setParentForeignKey("roleId")
                .build();
        invoker.execute();
    }

    public static void one2many() throws Exception {
        Invoker invoker = new One2ManyInvoker.Builder()
                .setDatabase("generator-demo")
                .setUsername("root")
                .setTableName("user")
                .setClassName("User")
                .setParentTableName("office")
                .setParentClassName("Office")
                .setForeignKey("officeId")
                .build();
        invoker.execute();
    }

    public static void single() throws Exception {
        Invoker invoker = new SingleInvoker.Builder()
                .setDatabase("generator-demo")
                .setUsername("root")
                .setPassword(null)
                .setTableName("role")
                .setClassName("Role")
                .build();
        invoker.execute();
    }

}
