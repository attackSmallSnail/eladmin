package com.ylz.base.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class HistoryPK implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * 主键-deptId
     */
    private String deptId;

    /*
     * 主键-历史版本号，保存格式年份_版本号，例如2018_1
     */
    private String orgId;

}
