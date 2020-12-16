package com.ylz.common.enums;

public enum CoordinateEnum {
    //单轴
    Y("y", "单轴y"),
    //双轴左
    DL("yLeft", "X轴"),
    //双抽右
    DR("yRight", "Y轴");
    private String name;
    private String desc;

    CoordinateEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
