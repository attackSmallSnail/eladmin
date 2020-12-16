package com.ylz.common.enums;


public enum ColumnEnum {
    NUM_TYPE("N","0","自定义数字类型"),
    STRING_TYPE("T","''","字符类型"),
    DATE_TYPE("S","sysdate","时间类型"),
    DOUBLE_TYPE("D",0.0,"小数类型");

    private String code;

    private Object value;

    private String description;

    ColumnEnum(String code, Object value, String description) {
        this.code = code;
        this.value = value;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
