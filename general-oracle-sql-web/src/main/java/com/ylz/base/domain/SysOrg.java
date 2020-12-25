/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package com.ylz.base.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
* @website https://el-admin.vip
* @description /
* @author lcw
* @date 2020-12-25
**/
@Entity
@Data
@Table(name="SYS_ORG")
public class SysOrg implements Serializable {

    @Column(name = "IS_DELETE")
    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    @Column(name = "SOCIAL_ORG_CODE")
    @ApiModelProperty(value = "社保机构代码")
    private String socialOrgCode;

    @Column(name = "ORG_CODE")
    @ApiModelProperty(value = "机构代码")
    private String orgCode;

    @Column(name = "ORG_POSTLEVEL")
    @ApiModelProperty(value = "机构级别（市 1 区县 2 医院/卫生院 3 卫生所4 卫生室5）")
    private String orgPostlevel;

    @Id
    @Column(name = "ORG_ID")
    @ApiModelProperty(value = "机构id")
    private String orgId;

    @Column(name = "AREA_ID")
    @ApiModelProperty(value = "区域id")
    private String areaId;

    @Column(name = "ADDRESS_CITY")
    @ApiModelProperty(value = "单位地址 地市")
    private String addressCity;

    @Column(name = "IFQSJG")
    @ApiModelProperty(value = "是否全市机构（1是，0否）对应aa10（T_YesNo）")
    private String ifqsjg;

    @Column(name = "ORG_PROPERTY")
    @ApiModelProperty(value = "机构性质:1 中医院 2妇幼保健院 3综合医院 4 专科医院 99其他(对应sys_code 的org_property)")
    private String orgProperty;

    @Column(name = "CONNECT_STATUS")
    @ApiModelProperty(value = "数据接入状态")
    private String connectStatus;

    @Column(name = "RURAL_ORG_CODE")
    @ApiModelProperty(value = "新农合机构代码")
    private String ruralOrgCode;

    @Column(name = "CREATE_DATE")
    @ApiModelProperty(value = "创建时间")
    private Timestamp createDate;

    @Column(name = "IFGLJG")
    @ApiModelProperty(value = "是否公立机构（1是，0否）对应aa10（T_YesNo）")
    private String ifgljg;

    @Column(name = "LINKMAN")
    @ApiModelProperty(value = "联系人")
    private String linkman;

    @Column(name = "IFGG00")
    @ApiModelProperty(value = "是否改革（1是，0否）对应aa10（T_YesNo）")
    private String ifgg00;

    @Column(name = "PARENT_ID")
    @ApiModelProperty(value = "父机构id")
    private String parentId;

    @Column(name = "ADDRESS")
    @ApiModelProperty(value = "机构地址")
    private String address;

    @Column(name = "IFQSJC")
    @ApiModelProperty(value = "是否全市所有基层医疗机构(1是，0否)对应aa10（T_YesNo）")
    private String ifqsjc;

    @Column(name = "ORG_ID_WJW")
    @ApiModelProperty(value = "卫健委机构id")
    private String orgIdWjw;

    @Column(name = "ADDRESS_PROVINCE")
    @ApiModelProperty(value = "单位地址 省")
    private String addressProvince;

    @Column(name = "ORG_LEVEL")
    @ApiModelProperty(value = "机构等级(对应sys_code的org_level)")
    private String orgLevel;

    @Column(name = "SHORT_NAME")
    @ApiModelProperty(value = "机构简称")
    private String shortName;

    @Column(name = "TEL")
    @ApiModelProperty(value = "电话")
    private String tel;

    @Column(name = "IN_FLAG")
    @ApiModelProperty(value = "1已经接入 0未接入")
    private String inFlag;

    @Column(name = "STAFF_ORG_CODE")
    @ApiModelProperty(value = "职工医保机构代码")
    private String staffOrgCode;

    @Column(name = "ORG_DESC")
    @ApiModelProperty(value = "机构描述")
    private String orgDesc;

    @Column(name = "SID")
    @ApiModelProperty(value = "机构编号")
    private String sid;

    @Column(name = "ORG_ID_FY")
    @ApiModelProperty(value = "妇幼机构ID")
    private String orgIdFy;

    @Column(name = "ORG_CATEGORY")
    @ApiModelProperty(value = "医院类型（对应基卫机构表的JGLB00）01:卫生管理部门； 02:医疗机构; 03:妇幼保健院; 04综合医院; 05:村卫生所 06:卫生服务站 07:民营医院")
    private String orgCategory;

    @Column(name = "ORDER_NO")
    @ApiModelProperty(value = "在同一级机构中的序号")
    private String orderNo;

    @Column(name = "IFZKJG")
    @ApiModelProperty(value = "是否专科机构（1是，0否）")
    private String ifzkjg;

    @Column(name = "ORG_TYPE")
    @ApiModelProperty(value = "机构类型:1 市属 2 县级 3 基层 0 其他 5 村医")
    private String orgType;

    @Column(name = "ORG_GRADE")
    @ApiModelProperty(value = "机构等级(1=一级/2=二级/3=三级)")
    private String orgGrade;

    @Column(name = "HOS_ORDER")
    @ApiModelProperty(value = "医院等次，1、甲等，2、乙等，3、丙等，9、未评等")
    private String hosOrder;

    @Column(name = "ORG_NAME")
    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @Column(name = "BELONG_COUNTY_ID")
    @ApiModelProperty(value = "属于区县的id")
    private String belongCountyId;

    @Column(name = "ORG_DB_USER")
    @ApiModelProperty(value = "机构对应数据库用户")
    private String orgDbUser;

    @Column(name = "ORGKIND")
    @ApiModelProperty(value = "机构类别,1机构,2部门")
    private String orgkind;

    @Column(name = "JMJKDA_ORGID")
    @ApiModelProperty(value = "居民健康档案机构id")
    private String jmjkdaOrgid;

    @Column(name = "AREA_NAME")
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @Column(name = "ORG_ID_GW")
    @ApiModelProperty(value = "公卫机构id")
    private String orgIdGw;

    @Column(name = "ORG_ID_JKZX")
    @ApiModelProperty(value = "疾控中心机构id")
    private String orgIdJkzx;

    @Column(name = "PRINCIPAL")
    @ApiModelProperty(value = "机构负责人")
    private String principal;

    @Column(name = "ADDRESS_COUNTY")
    @ApiModelProperty(value = "单位地址 区县")
    private String addressCounty;

    @Column(name = "FOUNDING_TIME")
    @ApiModelProperty(value = "开业时间")
    private Timestamp foundingTime;

    @Column(name = "CHARGE_OFFICER")
    @ApiModelProperty(value = "分管负责人")
    private String chargeOfficer;

    @Column(name = "ORG_DB_TABLESPACE")
    @ApiModelProperty(value = "机构对应数据库表空间名称")
    private String orgDbTablespace;

    @Column(name = "ZIP_CODE")
    @ApiModelProperty(value = "邮编")
    private String zipCode;

    public void copy(SysOrg source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
