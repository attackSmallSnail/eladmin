package com.ylz.dto;

public class TabColumnDto {
    private String fieldName;
    private String alias;

    public TabColumnDto(){}

    public TabColumnDto(String fieldName, String alias) {
        this.fieldName = fieldName;
        this.alias = alias;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
