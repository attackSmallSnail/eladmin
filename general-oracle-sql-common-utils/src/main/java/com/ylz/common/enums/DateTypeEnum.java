package com.ylz.common.enums;

import lombok.Getter;

@Getter
public enum DateTypeEnum {
    DATE("date","yyyy-MM-dd"),MONTH("month","yyyy-MM"),QUARTER("quarter","yyyy-Q"),YEAR("year","yyyy");
    private String name;
    private String dateFormat;
    DateTypeEnum(String name, String dateFormat) {
        this.name = name;
        this.dateFormat = dateFormat;
    }
}
