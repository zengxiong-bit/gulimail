package com.atguigu.gulimail.common.enue;

public enum ThirdPartyLoginType {

    GITEE(1, "gitee");

    private final int code;

    private final String description;

    ThirdPartyLoginType(int code, String description){

        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
