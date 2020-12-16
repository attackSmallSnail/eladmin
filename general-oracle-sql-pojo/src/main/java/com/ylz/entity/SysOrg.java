package com.ylz.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by Shidehao on 2017/7/31.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysOrg {
    private String orgId;
    private String orgCode;
    private String orgName;
    private String parentId;
    private String parentName;
    private String deptId;
    private String deptName;
    private String userId;
    private String userName;
    private String typeValue;
    private List orgList;
    /**
    * @author jianglf
    * @Desciption: 初始化新增系统用户中的机构权限显示
    * @fileName: SysOrg
    * @method getOrgId
    * @date 2017/10/17 8:43
    * @return java.lang.String
    * @version V1.0
    */
    private String orgLevel;//机构级别（1，2，3级医院）
    private String orgType;//机构类型

}
