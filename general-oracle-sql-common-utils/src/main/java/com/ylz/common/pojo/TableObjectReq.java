package com.ylz.common.pojo;

import java.util.HashMap;
import java.util.Map;

public class TableObjectReq {
    private String tableName;  // 表名
    private String tableChineseName;//表中文名称
    private boolean notToDept = false;   // 没有部门数据
    private boolean notFilter = false;   // 没有条件字段表
    private boolean notPointOfTime = true; //时间点
    private boolean queryLastYear = false; //是否查询上一年度
    private boolean queryLast = false; //是否查询上一年度
    private boolean queryCustom = false; //是否查询上一年度
    private boolean notNullTable = true;  //是否空表
    private boolean notConstTable = true;  //是常量表 常量表没有时间条件
    private boolean areaTable = true;   //是否有区域或机构的表
    private String orgIdColumn;  //机构字段
    private String deptIdColumn;  //部门字段
    private String dateColumn;  //日期字段
    private String dateColumnType;  // 日期字段类型
    private String dataFormat;  //日期格式
    private Map<String, Column> columns;  // 列Map
    private String conditionCustom;//where 输入自定义条件 中间用and 拼接
    private boolean customPointOfTimeFlag = false;//表自定义时间节点属性,查出最大时间的一条记录
    private String timeCondition;//自定义时间条件  时间 >= 或者 <= 条件

    public TableObjectReq() {
    }

    public TableObjectReq(String tableName) {
        this.tableName = tableName;
        this.columns = new HashMap<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, Column> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Column> columns) {
        this.columns = columns;
    }

    public boolean isNotToDept() {
        return notToDept;
    }

    public void setNotToDept(boolean notToDept) {
        this.notToDept = notToDept;
    }

    public boolean isNotFilter() {
        return notFilter;
    }

    public void setNotFilter(boolean notFilter) {
        this.notFilter = notFilter;
    }

    public String getOrgIdColumn() {
        return orgIdColumn;
    }

    public void setOrgIdColumn(String orgIdColumn) {
        this.orgIdColumn = orgIdColumn;
    }

    public String getDeptIdColumn() {
        return deptIdColumn;
    }

    public void setDeptIdColumn(String deptIdColumn) {
        this.deptIdColumn = deptIdColumn;
    }

    public String getDateColumn() {
        return dateColumn;
    }

    public void setDateColumn(String dateColumn) {
        this.dateColumn = dateColumn;
    }

    public String getDateColumnType() {
        return dateColumnType;
    }

    public void setDateColumnType(String dateColumnType) {
        this.dateColumnType = dateColumnType;
    }

    public boolean isNotPointOfTime() {
        return notPointOfTime;
    }

    public void setNotPointOfTime(boolean notPointOfTime) {
        this.notPointOfTime = notPointOfTime;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public boolean isQueryLastYear() {
        return queryLastYear;
    }

    public void setQueryLastYear(boolean queryLastYear) {
        this.queryLastYear = queryLastYear;
    }

    public boolean isQueryLast() {
        return queryLast;
    }

    public void setQueryLast(boolean queryLast) {
        this.queryLast = queryLast;
    }

    public boolean isQueryCustom() {
        return queryCustom;
    }

    public void setQueryCustom(boolean queryCustom) {
        this.queryCustom = queryCustom;
    }

    public boolean isNotNullTable() {
        return notNullTable;
    }

    public void setNotNullTable(boolean notNullTable) {
        this.notNullTable = notNullTable;
    }

    public boolean isNotConstTable() {
        return notConstTable;
    }

    public void setNotConstTable(boolean notConstTable) {
        this.notConstTable = notConstTable;
    }

    public boolean isAreaTable() {
        return areaTable;
    }

    public void setAreaTable(boolean areaTable) {
        this.areaTable = areaTable;
    }

    public String getConditionCustom() {
        return conditionCustom;
    }

    public void setConditionCustom(String conditionCustom) {
        this.conditionCustom = conditionCustom;
    }

    public String getTableChineseName() {
        return tableChineseName;
    }

    public void setTableChineseName(String tableChineseName) {
        this.tableChineseName = tableChineseName;
    }

    public boolean isCustomPointOfTimeFlag() {
        return customPointOfTimeFlag;
    }

    public void setCustomPointOfTimeFlag(boolean customPointOfTimeFlag) {
        this.customPointOfTimeFlag = customPointOfTimeFlag;
    }

    public String getTimeCondition() {
        return timeCondition;
    }

    public void setTimeCondition(String timeCondition) {
        this.timeCondition = timeCondition;
    }
}

