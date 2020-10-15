package com.greedystar.generator.entity;

/**
 * @author GreedyStar
 * @date 2020/10/15
 */
public enum IdStrategy {
    AUTO("auto"),
    UUID("uuid");
    
    private String value;

    IdStrategy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
