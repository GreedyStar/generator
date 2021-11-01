package com.greedystar.generator.entity;

/**
 * @author GreedyStar
 * @date 2021-11-1
 */
public enum ClassType {
    DTO_CREATE("Create"),
    DTO_UPDATE("Update"),
    DTO_QUERY("Query"),
    VO_LIST("List"),
    VO_DETAIL("Detail"),
    ;

    private String code;

    ClassType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
