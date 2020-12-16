package com.ylz.common.pojo;


import com.ylz.common.enums.ColumnEnum;

public class Column {
    private String field;  //列名
    private String alias;  //别名
    private String name;  //注释
    private boolean customFlag = false;  //自定义列名
    private boolean global = false;   //全局字段－主要使用于拼接用，最外层传参谨慎使用
    private ColumnEnum columnEnum = ColumnEnum.NUM_TYPE;//列类型,字符串  时间  数字
    public Column() {
    }

    public Column(String field, String alias, String name) {
        this.field = field;
        this.alias = alias;
        this.name = name;
    }

    public Column(String field, String alias, String name,boolean customFlag) {
        this.field = field;
        this.alias = alias;
        this.name = name;
        this.customFlag = customFlag;
    }

    public Column(String field, String alias, String name,boolean customFlag,ColumnEnum columnEnum) {
        this.field = field;
        this.alias = alias;
        this.name = name;
        this.customFlag = customFlag;
        this.columnEnum = columnEnum;
    }

    public Column(String field, String alias, String name,boolean customFlag, boolean global) {
        this.field = field;
        this.alias = alias;
        this.name = name;
        this.customFlag = customFlag;
        this.global = global;
    }

    public boolean isCustomFlag() {
        return customFlag;
    }

    public void setCustomFlag(boolean customFlag) {
        this.customFlag = customFlag;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public ColumnEnum getColumnEnum() {
        return columnEnum;
    }

    public void setColumnEnum(ColumnEnum columnEnum) {
        this.columnEnum = columnEnum;
    }
}
