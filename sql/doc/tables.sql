/*
 Navicat Premium Data Transfer

 Source Server         : 121.204.183.50(ylz-zh)
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 121.204.183.50:1621
 Source Schema         : ZHYGJC

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 14/12/2020 14:04:13
*/


-- ----------------------------
-- Table structure for SYS_ORG
-- ----------------------------
DROP TABLE "SYS_ORG";
CREATE TABLE "SYS_ORG" (
  "ORG_ID" VARCHAR2(96 BYTE) ,
  "ORG_NAME" VARCHAR2(300 BYTE) ,
  "SHORT_NAME" VARCHAR2(300 BYTE) ,
  "ORG_DESC" VARCHAR2(300 BYTE) ,
  "PARENT_ID" VARCHAR2(96 BYTE) ,
  "ADDRESS" VARCHAR2(300 BYTE) ,
  "PRINCIPAL" VARCHAR2(150 BYTE) ,
  "LINKMAN" VARCHAR2(96 BYTE) ,
  "TEL" VARCHAR2(96 BYTE) ,
  "ORGKIND" VARCHAR2(96 BYTE) ,
  "ORG_CODE" VARCHAR2(96 BYTE) ,
  "ORDER_NO" VARCHAR2(96 BYTE) ,
  "SOCIAL_ORG_CODE" VARCHAR2(96 BYTE) ,
  "STAFF_ORG_CODE" VARCHAR2(96 BYTE) ,
  "RURAL_ORG_CODE" VARCHAR2(96 BYTE) ,
  "ORG_TYPE" VARCHAR2(96 BYTE) ,
  "ORG_PROPERTY" VARCHAR2(96 BYTE) ,
  "ORG_LEVEL" VARCHAR2(96 BYTE) ,
  "IFGLJG" VARCHAR2(96 BYTE) ,
  "IFQSJG" VARCHAR2(96 BYTE) ,
  "IFGG00" VARCHAR2(96 BYTE) ,
  "IFQSJC" VARCHAR2(96 BYTE) ,
  "IFZKJG" VARCHAR2(96 BYTE) ,
  "ORG_DB_USER" VARCHAR2(96 BYTE) ,
  "ORG_DB_TABLESPACE" VARCHAR2(96 BYTE) ,
  "CONNECT_STATUS" VARCHAR2(96 BYTE) ,
  "JMJKDA_ORGID" VARCHAR2(96 BYTE) ,
  "ORG_CATEGORY" VARCHAR2(96 BYTE) ,
  "ORG_POSTLEVEL" VARCHAR2(96 BYTE) ,
  "IN_FLAG" VARCHAR2(6 BYTE) ,
  "BELONG_COUNTY_ID" VARCHAR2(30 BYTE) ,
  "AREA_ID" VARCHAR2(32 BYTE) ,
  "AREA_NAME" VARCHAR2(225 BYTE) ,
  "CREATE_DATE" DATE ,
  "IS_DELETE" VARCHAR2(10 BYTE) ,
  "ORG_GRADE" VARCHAR2(10 BYTE) ,
  "ORG_ID_GW" VARCHAR2(96 BYTE) ,
  "FOUNDING_TIME" DATE ,
  "ORG_ID_FY" VARCHAR2(100 BYTE) ,
  "ORG_ID_WJW" VARCHAR2(100 BYTE) ,
  "ORG_ID_JKZX" VARCHAR2(64 BYTE) ,
  "ADDRESS_PROVINCE" VARCHAR2(255 BYTE) ,
  "ADDRESS_CITY" VARCHAR2(255 BYTE) ,
  "ADDRESS_COUNTY" VARCHAR2(255 BYTE) ,
  "ZIP_CODE" VARCHAR2(255 BYTE) ,
  "HOS_ORDER" VARCHAR2(255 BYTE) ,
  "CHARGE_OFFICER" VARCHAR2(255 BYTE) ,
  "SID" VARCHAR2(255 BYTE)
);
COMMENT ON COLUMN "SYS_ORG"."ORG_ID" IS '机构id';
COMMENT ON COLUMN "SYS_ORG"."ORG_NAME" IS '机构名称';
COMMENT ON COLUMN "SYS_ORG"."SHORT_NAME" IS '机构简称';
COMMENT ON COLUMN "SYS_ORG"."ORG_DESC" IS '机构描述';
COMMENT ON COLUMN "SYS_ORG"."PARENT_ID" IS '父机构id';
COMMENT ON COLUMN "SYS_ORG"."ADDRESS" IS '机构地址';
COMMENT ON COLUMN "SYS_ORG"."PRINCIPAL" IS '机构负责人';
COMMENT ON COLUMN "SYS_ORG"."LINKMAN" IS '联系人';
COMMENT ON COLUMN "SYS_ORG"."TEL" IS '电话';
COMMENT ON COLUMN "SYS_ORG"."ORGKIND" IS '机构类别,1机构,2部门';
COMMENT ON COLUMN "SYS_ORG"."ORG_CODE" IS '机构代码';
COMMENT ON COLUMN "SYS_ORG"."ORDER_NO" IS '在同一级机构中的序号';
COMMENT ON COLUMN "SYS_ORG"."SOCIAL_ORG_CODE" IS '社保机构代码';
COMMENT ON COLUMN "SYS_ORG"."STAFF_ORG_CODE" IS '职工医保机构代码';
COMMENT ON COLUMN "SYS_ORG"."RURAL_ORG_CODE" IS '新农合机构代码';
COMMENT ON COLUMN "SYS_ORG"."ORG_TYPE" IS '机构类型:1 市属 2 县级 3 基层 0 其他 5 村医';
COMMENT ON COLUMN "SYS_ORG"."ORG_PROPERTY" IS '机构性质:1 中医院
2妇幼保健院
3综合医院
4 专科医院
99其他(对应sys_code 的org_property)';
COMMENT ON COLUMN "SYS_ORG"."ORG_LEVEL" IS '机构等级(对应sys_code的org_level)';
COMMENT ON COLUMN "SYS_ORG"."IFGLJG" IS '是否公立机构（1是，0否）对应aa10（T_YesNo）';
COMMENT ON COLUMN "SYS_ORG"."IFQSJG" IS '是否全市机构（1是，0否）对应aa10（T_YesNo）';
COMMENT ON COLUMN "SYS_ORG"."IFGG00" IS '是否改革（1是，0否）对应aa10（T_YesNo）';
COMMENT ON COLUMN "SYS_ORG"."IFQSJC" IS '是否全市所有基层医疗机构(1是，0否)对应aa10（T_YesNo）';
COMMENT ON COLUMN "SYS_ORG"."IFZKJG" IS '是否专科机构（1是，0否）';
COMMENT ON COLUMN "SYS_ORG"."ORG_DB_USER" IS '机构对应数据库用户';
COMMENT ON COLUMN "SYS_ORG"."ORG_DB_TABLESPACE" IS '机构对应数据库表空间名称';
COMMENT ON COLUMN "SYS_ORG"."CONNECT_STATUS" IS '数据接入状态';
COMMENT ON COLUMN "SYS_ORG"."JMJKDA_ORGID" IS '居民健康档案机构id';
COMMENT ON COLUMN "SYS_ORG"."ORG_CATEGORY" IS '医院类型（对应基卫机构表的JGLB00）01:卫生管理部门；
02:医疗机构;
03:妇幼保健院;
04综合医院;
05:村卫生所
06:卫生服务站
07:民营医院';
COMMENT ON COLUMN "SYS_ORG"."ORG_POSTLEVEL" IS '机构级别（市 1 区县 2 医院/卫生院 3 卫生所4 卫生室5）';
COMMENT ON COLUMN "SYS_ORG"."IN_FLAG" IS '1已经接入 0未接入';
COMMENT ON COLUMN "SYS_ORG"."BELONG_COUNTY_ID" IS '属于区县的id';
COMMENT ON COLUMN "SYS_ORG"."AREA_ID" IS '区域id';
COMMENT ON COLUMN "SYS_ORG"."AREA_NAME" IS '区域名称';
COMMENT ON COLUMN "SYS_ORG"."CREATE_DATE" IS '创建时间';
COMMENT ON COLUMN "SYS_ORG"."IS_DELETE" IS '是否删除';
COMMENT ON COLUMN "SYS_ORG"."ORG_GRADE" IS '机构等级(1=一级/2=二级/3=三级)';
COMMENT ON COLUMN "SYS_ORG"."ORG_ID_GW" IS '公卫机构id';
COMMENT ON COLUMN "SYS_ORG"."FOUNDING_TIME" IS '开业时间';
COMMENT ON COLUMN "SYS_ORG"."ORG_ID_FY" IS '妇幼机构ID';
COMMENT ON COLUMN "SYS_ORG"."ORG_ID_WJW" IS '卫健委机构id';
COMMENT ON COLUMN "SYS_ORG"."ORG_ID_JKZX" IS '疾控中心机构id';
COMMENT ON COLUMN "SYS_ORG"."ADDRESS_PROVINCE" IS '单位地址 省';
COMMENT ON COLUMN "SYS_ORG"."ADDRESS_CITY" IS '单位地址 地市';
COMMENT ON COLUMN "SYS_ORG"."ADDRESS_COUNTY" IS '单位地址 区县';
COMMENT ON COLUMN "SYS_ORG"."ZIP_CODE" IS '邮编';
COMMENT ON COLUMN "SYS_ORG"."HOS_ORDER" IS '医院等次，1、甲等，2、乙等，3、丙等，9、未评等';
COMMENT ON COLUMN "SYS_ORG"."CHARGE_OFFICER" IS '分管负责人';
COMMENT ON COLUMN "SYS_ORG"."SID" IS '机构编号';
COMMENT ON TABLE "SYS_ORG" IS '机构编码表';

/*
 Navicat Premium Data Transfer

 Source Server         : 121.204.183.50(ylz-zh)
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 121.204.183.50:1621
 Source Schema         : ZHYGJC

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 14/12/2020 14:04:26
*/


-- ----------------------------
-- Table structure for SYS_AREA_FJZL
-- ----------------------------
DROP TABLE "SYS_AREA_FJZL";
CREATE TABLE "SYS_AREA_FJZL" (
  "AREA_ID" VARCHAR2(32 BYTE) NOT NULL ,
  "AREA_NAME" VARCHAR2(200 BYTE) ,
  "AREA_TYPE" VARCHAR2(20 BYTE) ,
  "PARENT_ID" VARCHAR2(32 BYTE) ,
  "AREA_PERSON" NUMBER ,
  "URBAN_RURAL" VARCHAR2(5 BYTE) ,
  "IN_USE" VARCHAR2(2 BYTE) ,
  "AREA_SHORT" VARCHAR2(50 BYTE)
);

/*
 Navicat Premium Data Transfer

 Source Server         : 121.204.183.50(ylz-zh)
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 121.204.183.50:1621
 Source Schema         : ZHYGJC

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 14/12/2020 14:09:40
*/


-- ----------------------------
-- Table structure for DEPT
-- ----------------------------
DROP TABLE "DEPT";
CREATE TABLE "DEPT" (
  "ORG_ID" VARCHAR2(32 BYTE) ,
  "ORG_NAME" VARCHAR2(100 BYTE) ,
  "DEPT_ID" VARCHAR2(100 BYTE) ,
  "DEPT_NAME" VARCHAR2(100 BYTE) ,
  "DEPT_TYPE1" VARCHAR2(32 BYTE) ,
  "DEPT_TYPE2" VARCHAR2(32 BYTE) ,
  "HOSPITAL_ID" VARCHAR2(32 BYTE) ,
  "SORT_CODE" VARCHAR2(100 BYTE) ,
  "IS_DELETE" VARCHAR2(10 BYTE)
);
COMMENT ON COLUMN "DEPT"."ORG_ID" IS '机构id';
COMMENT ON COLUMN "DEPT"."ORG_NAME" IS '机构名称';
COMMENT ON COLUMN "DEPT"."DEPT_ID" IS '科室代码';
COMMENT ON COLUMN "DEPT"."DEPT_NAME" IS '科室名称';
COMMENT ON COLUMN "DEPT"."DEPT_TYPE1" IS '科室类型 1-门诊科室 2-住院科室 3-护理病区 9-其他';
COMMENT ON COLUMN "DEPT"."DEPT_TYPE2" IS '临床科室分类 1-内科 2-外科 3-妇产 4-儿科 5-中医科 6-ICU  9-其他';
COMMENT ON COLUMN "DEPT"."HOSPITAL_ID" IS '分院';
COMMENT ON COLUMN "DEPT"."SORT_CODE" IS '排序码';
COMMENT ON TABLE "DEPT" IS '科室（病区）';

/*
 Navicat Premium Data Transfer

 Source Server         : 121.204.183.50(ylz-zh)
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 121.204.183.50:1621
 Source Schema         : ZHYGJC

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 15/12/2020 09:48:44
*/


-- ----------------------------
-- Table structure for WARNING_INDICATORS
-- ----------------------------
DROP TABLE "WARNING_INDICATORS";
CREATE TABLE "WARNING_INDICATORS" (
  "WARNING_ID" VARCHAR2(32 BYTE) ,
  "WARNING_NAME" VARCHAR2(100 BYTE) ,
  "INDICATORS_KEY" VARCHAR2(50 BYTE) ,
  "INDICATORS_MODULE" VARCHAR2(100 BYTE) ,
  "INDICATORS_NAME" VARCHAR2(100 BYTE) ,
  "DATE_TYPE" VARCHAR2(10 BYTE) ,
  "AREA_ID" VARCHAR2(30 BYTE) ,
  "CREATE_USER_ID" VARCHAR2(50 BYTE) ,
  "CREATE_USER_NAME" VARCHAR2(100 BYTE) ,
  "USED_STATUS" VARCHAR2(3 BYTE) ,
  "INTERVAL_MIN" NUMBER ,
  "INTERVAL_MAX" NUMBER ,
  "IS_DEL" VARCHAR2(3 BYTE) ,
  "IS_SINGLE_ORG" VARCHAR2(3 BYTE) ,
  "CREATE_TIME" DATE
);
COMMENT ON COLUMN "WARNING_INDICATORS"."WARNING_ID" IS '预警id';
COMMENT ON COLUMN "WARNING_INDICATORS"."WARNING_NAME" IS '预警名称';
COMMENT ON COLUMN "WARNING_INDICATORS"."INDICATORS_KEY" IS '需要预警指标的key';
COMMENT ON COLUMN "WARNING_INDICATORS"."INDICATORS_MODULE" IS '指标所在模块';
COMMENT ON COLUMN "WARNING_INDICATORS"."INDICATORS_NAME" IS '指标名称';
COMMENT ON COLUMN "WARNING_INDICATORS"."DATE_TYPE" IS '预警周期，month：月度quarter：季度year：年度';
COMMENT ON COLUMN "WARNING_INDICATORS"."AREA_ID" IS '区域id';
COMMENT ON COLUMN "WARNING_INDICATORS"."CREATE_USER_ID" IS '创建者id';
COMMENT ON COLUMN "WARNING_INDICATORS"."CREATE_USER_NAME" IS '创建者名称';
COMMENT ON COLUMN "WARNING_INDICATORS"."USED_STATUS" IS '开始状态 1开启，0关闭';
COMMENT ON COLUMN "WARNING_INDICATORS"."INTERVAL_MIN" IS '预警区间最小值';
COMMENT ON COLUMN "WARNING_INDICATORS"."INTERVAL_MAX" IS '预警区间最大值';
COMMENT ON COLUMN "WARNING_INDICATORS"."IS_DEL" IS '1删除，0正常';
COMMENT ON COLUMN "WARNING_INDICATORS"."IS_SINGLE_ORG" IS '任意单机构匹配';
COMMENT ON COLUMN "WARNING_INDICATORS"."CREATE_TIME" IS '创建时间';
COMMENT ON TABLE "WARNING_INDICATORS" IS '指标预警表';

/*
 Navicat Premium Data Transfer

 Source Server         : 121.204.183.50(ylz-zh)
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 121.204.183.50:1621
 Source Schema         : ZHYGJC

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 15/12/2020 09:49:41
*/


-- ----------------------------
-- Table structure for WARNING_INTERVAL
-- ----------------------------
DROP TABLE "WARNING_INTERVAL";
CREATE TABLE "WARNING_INTERVAL" (
  "WARNING_ID" VARCHAR2(32 BYTE) ,
  "WARNING_GRADE" VARCHAR2(32 BYTE) ,
  "WARNING_RULE" VARCHAR2(5 BYTE) ,
  "WARNING_NUMBER" NUMBER ,
  "INDICATORS_KEY" VARCHAR2(100 BYTE)
);
COMMENT ON COLUMN "WARNING_INTERVAL"."WARNING_ID" IS '预警id';
COMMENT ON COLUMN "WARNING_INTERVAL"."WARNING_GRADE" IS '预警等级';
COMMENT ON COLUMN "WARNING_INTERVAL"."WARNING_RULE" IS '预警规则';
COMMENT ON COLUMN "WARNING_INTERVAL"."WARNING_NUMBER" IS '限定值';
COMMENT ON COLUMN "WARNING_INTERVAL"."INDICATORS_KEY" IS '指标key';
COMMENT ON TABLE "WARNING_INTERVAL" IS '预警规则限制表';

/*
 Navicat Premium Data Transfer

 Source Server         : 121.204.183.50(ylz-zh)
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 121.204.183.50:1621
 Source Schema         : ZHYGJC

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 15/12/2020 09:56:31
*/


-- ----------------------------
-- Table structure for WARNING_SYS_GRADE
-- ----------------------------
DROP TABLE "WARNING_SYS_GRADE";
CREATE TABLE "WARNING_SYS_GRADE" (
  "WARNING_ID" VARCHAR2(32 BYTE) ,
  "ORG_GRADE" VARCHAR2(10 BYTE)
);
COMMENT ON COLUMN "WARNING_SYS_GRADE"."WARNING_ID" IS '预警id';
COMMENT ON COLUMN "WARNING_SYS_GRADE"."ORG_GRADE" IS '机构等级';
COMMENT ON TABLE "WARNING_SYS_GRADE" IS '预警和机构等级关联表';

/*
 Navicat Premium Data Transfer

 Source Server         : 121.204.183.50(ylz-zh)
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 121.204.183.50:1621
 Source Schema         : ZHYGJC

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 15/12/2020 09:56:42
*/


-- ----------------------------
-- Table structure for WARNING_SYS_ORG
-- ----------------------------
DROP TABLE "WARNING_SYS_ORG";
CREATE TABLE "WARNING_SYS_ORG" (
  "WARNING_ID" VARCHAR2(32 BYTE) ,
  "ORG_ID" VARCHAR2(50 BYTE) ,
  "ORG_NAME" VARCHAR2(200 BYTE) ,
  "INDICATORS_KEY" VARCHAR2(100 BYTE)
);
COMMENT ON COLUMN "WARNING_SYS_ORG"."WARNING_ID" IS '预警id';
COMMENT ON COLUMN "WARNING_SYS_ORG"."ORG_ID" IS '机构id';
COMMENT ON COLUMN "WARNING_SYS_ORG"."ORG_NAME" IS '机构名称';
COMMENT ON COLUMN "WARNING_SYS_ORG"."INDICATORS_KEY" IS '指标key';
COMMENT ON TABLE "WARNING_SYS_ORG" IS '预警和机构关联表';
