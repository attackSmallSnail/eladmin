package com.ylz.common.enums;

public enum ShowProgressEnum {
    T("true"), F(""), P("percent");
    private String value;

    ShowProgressEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
