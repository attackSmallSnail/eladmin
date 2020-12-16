package com.ylz.req;

/**
 * @Description:
 * @Author: Nomark
 * @CreateDate: 2019/9/5 14:35
 * @Version: v1.0
 */
public class CommonSqlReq {
    private String queryStr;   // 查询条件
    private String interfaceAddress;  // 接口地址或表
    private String insertFields;  //插入字段
    private String insertDatas;  //插入数据
    private String updateDatas;  //更新字段

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public String getInterfaceAddress() {
        return interfaceAddress;
    }

    public void setInterfaceAddress(String interfaceAddress) {
        this.interfaceAddress = interfaceAddress;
    }

    public String getInsertFields() {
        return insertFields;
    }

    public void setInsertFields(String insertFields) {
        this.insertFields = insertFields;
    }

    public String getInsertDatas() {
        return insertDatas;
    }

    public void setInsertDatas(String insertDatas) {
        this.insertDatas = insertDatas;
    }

    public String getUpdateDatas() {
        return updateDatas;
    }

    public void setUpdateDatas(String updateDatas) {
        this.updateDatas = updateDatas;
    }
}
