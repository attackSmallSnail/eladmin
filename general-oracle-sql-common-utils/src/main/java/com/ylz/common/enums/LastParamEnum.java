package com.ylz.common.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum LastParamEnum {

    LAST_YEAR("LastYear","Ly","lastDateStart","lastDateEnd","year","查询去年同比"),
    LAST_DAY("LastDay","Ld","lastMonthDateStart","lastMonthDateEnd","month/quarter","查询月份/季度环比"),
    LAST_CUSTOM("LastCustom","Lc","lastCustomDateStart","lastCustomDateEnd","custom","自定义时间月份比");
    //表类型
    private String code;
    //字段类型
    private String columnType;
    //开始时间
    private String dateStart;
    //结束时间
    private String dateEnd;
    //时间类型
    private String dateType;
    //描述
    private String decript;

    LastParamEnum(String code, String columnType, String dateStart, String dateEnd, String dateType, String decript) {
        this.code = code;
        this.columnType = columnType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateType = dateType;
        this.decript = decript;
    }

    public static String resultLastYearTable(String tableName){
        StringBuffer tableStr = new StringBuffer(tableName);
        tableStr.append(LAST_YEAR.getCode());
        return tableStr.toString();
    }

    public static String resultLastDayTable(String tableName){
        StringBuffer tableStr = new StringBuffer(tableName);
        tableStr.append(LAST_DAY.getCode());
        return tableStr.toString();
    }
    public static String resultLastCustomTable(String tableName){
        StringBuffer tableStr = new StringBuffer(tableName);
        tableStr.append(LAST_CUSTOM.getCode());
        return tableStr.toString();
    }
    public static void resultLastCustomTable(String[] tableArray, int start, int end) {
        for (int i = start; i < end; i++){
            tableArray[i] = resultLastCustomTable(tableArray[i]);
        }
    }

    public static String[] addLastEnum(String[] tableArray, LastParamEnum... lastParamEnums){
        List<String> listTable = new ArrayList<String>(Arrays.asList(tableArray));
        for (LastParamEnum lastParamEnum: lastParamEnums) {
            for (String tableName: tableArray) {
                if (LAST_CUSTOM.equals(lastParamEnum)) {
                    listTable.add(resultLastCustomTable(tableName));
                } else if (LAST_YEAR.equals(lastParamEnum)) {
                    listTable.add(resultLastYearTable(tableName));
                } else if (LAST_DAY.equals(lastParamEnum)) {
                    listTable.add(resultLastDayTable(tableName));
                }
            }
        }
        return listTable.toArray(tableArray);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getDecript() {
        return decript;
    }

    public void setDecript(String decript) {
        this.decript = decript;
    }
}
