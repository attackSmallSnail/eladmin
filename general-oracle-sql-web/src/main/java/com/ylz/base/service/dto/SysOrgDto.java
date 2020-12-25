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
package com.ylz.base.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author lcw
* @date 2020-12-25
**/
@Data
public class SysOrgDto implements Serializable {

    /** 是否删除 */
    private String isDelete;

    /** 社保机构代码 */
    private String socialOrgCode;

    /** 机构代码 */
    private String orgCode;

    /** 机构级别（市 1 区县 2 医院/卫生院 3 卫生所4 卫生室5） */
    private String orgPostlevel;

    /** 机构id */
    private String orgId;

    /** 区域id */
    private String areaId;

    /** 单位地址 地市 */
    private String addressCity;

    /** 是否全市机构（1是，0否）对应aa10（T_YesNo） */
    private String ifqsjg;

    /** 机构性质:1 中医院
2妇幼保健院
3综合医院
4 专科医院
99其他(对应sys_code 的org_property) */
    private String orgProperty;

    /** 数据接入状态 */
    private String connectStatus;

    /** 新农合机构代码 */
    private String ruralOrgCode;

    /** 创建时间 */
    private Timestamp createDate;

    /** 是否公立机构（1是，0否）对应aa10（T_YesNo） */
    private String ifgljg;

    /** 联系人 */
    private String linkman;

    /** 是否改革（1是，0否）对应aa10（T_YesNo） */
    private String ifgg00;

    /** 父机构id */
    private String parentId;

    /** 机构地址 */
    private String address;

    /** 是否全市所有基层医疗机构(1是，0否)对应aa10（T_YesNo） */
    private String ifqsjc;

    /** 卫健委机构id */
    private String orgIdWjw;

    /** 单位地址 省 */
    private String addressProvince;

    /** 机构等级(对应sys_code的org_level) */
    private String orgLevel;

    /** 机构简称 */
    private String shortName;

    /** 电话 */
    private String tel;

    /** 1已经接入 0未接入 */
    private String inFlag;

    /** 职工医保机构代码 */
    private String staffOrgCode;

    /** 机构描述 */
    private String orgDesc;

    /** 机构编号 */
    private String sid;

    /** 妇幼机构ID */
    private String orgIdFy;

    /** 医院类型（对应基卫机构表的JGLB00）01:卫生管理部门；
02:医疗机构;
03:妇幼保健院;
04综合医院;
05:村卫生所
06:卫生服务站
07:民营医院 */
    private String orgCategory;

    /** 在同一级机构中的序号 */
    private String orderNo;

    /** 是否专科机构（1是，0否） */
    private String ifzkjg;

    /** 机构类型:1 市属 2 县级 3 基层 0 其他 5 村医 */
    private String orgType;

    /** 机构等级(1=一级/2=二级/3=三级) */
    private String orgGrade;

    /** 医院等次，1、甲等，2、乙等，3、丙等，9、未评等 */
    private String hosOrder;

    /** 机构名称 */
    private String orgName;

    /** 属于区县的id */
    private String belongCountyId;

    /** 机构对应数据库用户 */
    private String orgDbUser;

    /** 机构类别,1机构,2部门 */
    private String orgkind;

    /** 居民健康档案机构id */
    private String jmjkdaOrgid;

    /** 区域名称 */
    private String areaName;

    /** 公卫机构id */
    private String orgIdGw;

    /** 疾控中心机构id */
    private String orgIdJkzx;

    /** 机构负责人 */
    private String principal;

    /** 单位地址 区县 */
    private String addressCounty;

    /** 开业时间 */
    private Timestamp foundingTime;

    /** 分管负责人 */
    private String chargeOfficer;

    /** 机构对应数据库表空间名称 */
    private String orgDbTablespace;

    /** 邮编 */
    private String zipCode;
}