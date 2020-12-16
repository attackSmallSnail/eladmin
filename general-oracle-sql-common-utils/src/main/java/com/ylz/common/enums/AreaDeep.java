package com.ylz.common.enums;

public enum AreaDeep {
    PROVINCE(1),CITY(2), COUNTY(3),TOWN(4),VILLAGE(5);
    private Integer deep;

    AreaDeep(Integer deep) {
        this.deep = deep;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }
}
