package com.greedystar.generator.entity;

import java.io.Serializable;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class ColumnInfo implements Serializable {
    private String name; // 列名
    private int type; // 类型代码

    public ColumnInfo() {

    }

    public ColumnInfo(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
