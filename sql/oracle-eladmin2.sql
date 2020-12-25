/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2020/12/23 17:30:24                          */
/*==============================================================*/


/**
-- Type package declaration
create or replace package PDTypes
as
    TYPE ref_cursor IS REF CURSOR;
end;

-- Integrity package declaration
create or replace package IntegrityPackage AS
 procedure InitNestLevel;
 function GetNestLevel return number;
 procedure NextNestLevel;
 procedure PreviousNestLevel;
 end IntegrityPackage;
/

-- Integrity package definition
create or replace package body IntegrityPackage AS
 NestLevel number;

-- Procedure to initialize the trigger nest level
 procedure InitNestLevel is
 begin
 NestLevel := 0;
 end;


-- Function to return the trigger nest level
 function GetNestLevel return number is
 begin
 if NestLevel is null then
     NestLevel := 0;
 end if;
 return(NestLevel);
 end;

-- Procedure to increase the trigger nest level
 procedure NextNestLevel is
 begin
 if NestLevel is null then
     NestLevel := 0;
 end if;
 NestLevel := NestLevel + 1;
 end;

-- Procedure to decrease the trigger nest level
 procedure PreviousNestLevel is
 begin
 NestLevel := NestLevel - 1;
 end;

 end IntegrityPackage;
/
**/

drop trigger COMPOUNDDELETETRIGGER_CODE_COL
/

drop trigger COMPOUNDINSERTTRIGGER_CODE_COL
/

drop trigger COMPOUNDUPDATETRIGGER_CODE_COL
/

drop trigger TIB_CODE_COLUMN_CONFIG
/

drop trigger COMPOUNDDELETETRIGGER_CODE_GEN
/

drop trigger COMPOUNDINSERTTRIGGER_CODE_GEN
/

drop trigger COMPOUNDUPDATETRIGGER_CODE_GEN
/

drop trigger TIB_CODE_GEN_CONFIG
/

drop trigger COMPOUNDDELETETRIGGER_MNT_APP
/

drop trigger COMPOUNDINSERTTRIGGER_MNT_APP
/

drop trigger COMPOUNDUPDATETRIGGER_MNT_APP
/

drop trigger TIB_MNT_APP
/

drop trigger COMPOUNDDELETETRIGGER_MNT_DATA
/

drop trigger COMPOUNDINSERTTRIGGER_MNT_DATA
/

drop trigger COMPOUNDUPDATETRIGGER_MNT_DATA
/

drop trigger COMPOUNDDELETETRIGGER_MNT_DEPL
/

drop trigger COMPOUNDINSERTTRIGGER_MNT_DEPL
/

drop trigger COMPOUNDUPDATETRIGGER_MNT_DEPL
/

drop trigger TIB_MNT_DEPLOY
/

drop trigger COMPOUNDDELETETRIGGER_MNT_DEPL
/

drop trigger COMPOUNDINSERTTRIGGER_MNT_DEPL
/

drop trigger COMPOUNDUPDATETRIGGER_MNT_DEPL
/

drop trigger COMPOUNDDELETETRIGGER_MNT_DEPL
/

drop trigger COMPOUNDINSERTTRIGGER_MNT_DEPL
/

drop trigger COMPOUNDUPDATETRIGGER_MNT_DEPL
/

drop trigger COMPOUNDDELETETRIGGER_MNT_SERV
/

drop trigger COMPOUNDINSERTTRIGGER_MNT_SERV
/

drop trigger COMPOUNDUPDATETRIGGER_MNT_SERV
/

drop trigger TIB_MNT_SERVER
/

drop trigger COMPOUNDDELETETRIGGER_SYS_DEPT
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_DEPT
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_DEPT
/

drop trigger TIB_SYS_DEPT
/

drop trigger COMPOUNDDELETETRIGGER_SYS_DICT
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_DICT
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_DICT
/

drop trigger TIB_SYS_DICT
/

drop trigger COMPOUNDDELETETRIGGER_SYS_DICT
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_DICT
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_DICT
/

drop trigger TIB_SYS_DICT_DETAIL
/

drop trigger COMPOUNDDELETETRIGGER_SYS_JOB
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_JOB
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_JOB
/

drop trigger TIB_SYS_JOB
/

drop trigger COMPOUNDDELETETRIGGER_SYS_LOG
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_LOG
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_LOG
/

drop trigger TIB_SYS_LOG
/

drop trigger COMPOUNDDELETETRIGGER_SYS_MENU
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_MENU
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_MENU
/

drop trigger TIB_SYS_MENU
/

drop trigger COMPOUNDDELETETRIGGER_SYS_QUAR
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_QUAR
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_QUAR
/

drop trigger TIB_SYS_QUARTZ_JOB
/

drop trigger COMPOUNDDELETETRIGGER_SYS_QUAR
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_QUAR
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_QUAR
/

drop trigger TIB_SYS_QUARTZ_LOG
/

drop trigger COMPOUNDDELETETRIGGER_SYS_ROLE
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_ROLE
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_ROLE
/

drop trigger TIB_SYS_ROLE
/

drop trigger COMPOUNDDELETETRIGGER_SYS_ROLE
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_ROLE
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_ROLE
/

drop trigger COMPOUNDDELETETRIGGER_SYS_ROLE
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_ROLE
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_ROLE
/

drop trigger COMPOUNDDELETETRIGGER_SYS_USER
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_USER
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_USER
/

drop trigger TIB_SYS_USER
/

drop trigger COMPOUNDDELETETRIGGER_SYS_USER
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_USER
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_USER
/

drop trigger COMPOUNDDELETETRIGGER_SYS_USER
/

drop trigger COMPOUNDINSERTTRIGGER_SYS_USER
/

drop trigger COMPOUNDUPDATETRIGGER_SYS_USER
/

drop trigger COMPOUNDDELETETRIGGER_TOOL_ALI
/

drop trigger COMPOUNDINSERTTRIGGER_TOOL_ALI
/

drop trigger COMPOUNDUPDATETRIGGER_TOOL_ALI
/

drop trigger COMPOUNDDELETETRIGGER_TOOL_EMA
/

drop trigger COMPOUNDINSERTTRIGGER_TOOL_EMA
/

drop trigger COMPOUNDUPDATETRIGGER_TOOL_EMA
/

drop trigger COMPOUNDDELETETRIGGER_TOOL_LOC
/

drop trigger COMPOUNDINSERTTRIGGER_TOOL_LOC
/

drop trigger COMPOUNDUPDATETRIGGER_TOOL_LOC
/

drop trigger TIB_TOOL_LOCAL_STORAGE
/

drop trigger COMPOUNDDELETETRIGGER_TOOL_QIN
/

drop trigger COMPOUNDINSERTTRIGGER_TOOL_QIN
/

drop trigger COMPOUNDUPDATETRIGGER_TOOL_QIN
/

drop trigger COMPOUNDDELETETRIGGER_TOOL_QIN
/

drop trigger COMPOUNDINSERTTRIGGER_TOOL_QIN
/

drop trigger COMPOUNDUPDATETRIGGER_TOOL_QIN
/

drop trigger TIB_TOOL_QINIU_CONTENT
/

drop table CODE_COLUMN_CONFIG cascade constraints
/

drop table CODE_GEN_CONFIG cascade constraints
/

drop table MNT_APP cascade constraints
/

drop table MNT_DATABASE cascade constraints
/

drop table MNT_DEPLOY cascade constraints
/

drop table MNT_DEPLOY_HISTORY cascade constraints
/

drop table MNT_DEPLOY_SERVER cascade constraints
/

drop table MNT_SERVER cascade constraints
/

drop table SYS_DEPT cascade constraints
/

drop table SYS_DICT cascade constraints
/

drop table SYS_DICT_DETAIL cascade constraints
/

drop table SYS_JOB cascade constraints
/

drop table SYS_LOG cascade constraints
/

drop table SYS_MENU cascade constraints
/

drop table SYS_QUARTZ_JOB cascade constraints
/

drop table SYS_QUARTZ_LOG cascade constraints
/

drop table SYS_ROLE cascade constraints
/

drop table SYS_ROLES_DEPTS cascade constraints
/

drop table SYS_ROLES_MENUS cascade constraints
/

drop table SYS_USER cascade constraints
/

drop table SYS_USERS_JOBS cascade constraints
/

drop table SYS_USERS_ROLES cascade constraints
/

drop table TOOL_ALIPAY_CONFIG cascade constraints
/

drop table TOOL_EMAIL_CONFIG cascade constraints
/

drop table TOOL_LOCAL_STORAGE cascade constraints
/

drop table TOOL_QINIU_CONFIG cascade constraints
/

drop table TOOL_QINIU_CONTENT cascade constraints
/

drop sequence S_CODE_COLUMN_CONFIG
/

drop sequence S_CODE_GEN_CONFIG
/

drop sequence S_MNT_APP
/

drop sequence S_MNT_DEPLOY
/

drop sequence S_MNT_SERVER
/

drop sequence S_SYS_DEPT
/

drop sequence S_SYS_DICT
/

drop sequence S_SYS_DICT_DETAIL
/

drop sequence S_SYS_JOB
/

drop sequence S_SYS_LOG
/

drop sequence S_SYS_MENU
/

drop sequence S_SYS_QUARTZ_JOB
/

drop sequence S_SYS_QUARTZ_LOG
/

drop sequence S_SYS_ROLE
/

drop sequence S_SYS_USER
/

drop sequence S_TOOL_LOCAL_STORAGE
/

drop sequence S_TOOL_QINIU_CONTENT
/

create sequence S_CODE_COLUMN_CONFIG
/

create sequence S_CODE_GEN_CONFIG
/

create sequence S_MNT_APP
/

create sequence S_MNT_DEPLOY
/

create sequence S_MNT_SERVER
/

create sequence S_SYS_DEPT
/

create sequence S_SYS_DICT
/

create sequence S_SYS_DICT_DETAIL
/

create sequence S_SYS_JOB
/

create sequence S_SYS_LOG
/

create sequence S_SYS_MENU
/

create sequence S_SYS_QUARTZ_JOB
/

create sequence S_SYS_QUARTZ_LOG
/

create sequence S_SYS_ROLE
/

create sequence S_SYS_USER
/

create sequence S_TOOL_LOCAL_STORAGE
/

create sequence S_TOOL_QINIU_CONTENT
/

/*==============================================================*/
/* Table: CODE_COLUMN_CONFIG                                    */
/*==============================================================*/
create table CODE_COLUMN_CONFIG
(
   COLUMN_ID            VARCHAR2(64)            not null,
   TABLE_NAME           VARCHAR2(255)        default NULL,
   COLUMN_NAME          VARCHAR2(255)        default NULL,
   COLUMN_TYPE          VARCHAR2(255)        default NULL,
   DICT_NAME            VARCHAR2(255)        default NULL,
   EXTRA                VARCHAR2(255)        default NULL,
   FORM_SHOW            SMALLINT             default NULL,
   FORM_TYPE            VARCHAR2(255)        default NULL,
   KEY_TYPE             VARCHAR2(255)        default NULL,
   LIST_SHOW            SMALLINT             default NULL,
   NOT_NULL             SMALLINT             default NULL,
   QUERY_TYPE           VARCHAR2(255)        default NULL,
   REMARK               VARCHAR2(255)        default NULL,
   DATE_ANNOTATION      VARCHAR2(255)        default NULL,
   constraint PK_CODE_COLUMN_CONFIG primary key (COLUMN_ID)
)
/

comment on column CODE_COLUMN_CONFIG.COLUMN_ID is
'ID'
/

/*==============================================================*/
/* Table: CODE_GEN_CONFIG                                       */
/*==============================================================*/
create table CODE_GEN_CONFIG
(
   CONFIG_ID            VARCHAR2(64)            not null,
   TABLE_NAME           VARCHAR2(255)        default NULL,
   AUTHOR               VARCHAR2(255)        default NULL,
   COVER                SMALLINT             default NULL,
   MODULE_NAME          VARCHAR2(255)        default NULL,
   PACK                 VARCHAR2(255)        default NULL,
   PATH                 VARCHAR2(255)        default NULL,
   API_PATH             VARCHAR2(255)        default NULL,
   PREFIX               VARCHAR2(255)        default NULL,
   API_ALIAS            VARCHAR2(255)        default NULL,
   constraint PK_CODE_GEN_CONFIG primary key (CONFIG_ID)
)
/

comment on column CODE_GEN_CONFIG.CONFIG_ID is
'ID'
/

comment on column CODE_GEN_CONFIG.TABLE_NAME is
'表名'
/

comment on column CODE_GEN_CONFIG.AUTHOR is
'作者'
/

comment on column CODE_GEN_CONFIG.COVER is
'是否覆盖'
/

comment on column CODE_GEN_CONFIG.MODULE_NAME is
'模块名称'
/

comment on column CODE_GEN_CONFIG.PACK is
'至于哪个包下'
/

comment on column CODE_GEN_CONFIG.PATH is
'前端代码生成的路径'
/

comment on column CODE_GEN_CONFIG.API_PATH is
'前端Api文件路径'
/

comment on column CODE_GEN_CONFIG.PREFIX is
'表前缀'
/

comment on column CODE_GEN_CONFIG.API_ALIAS is
'接口名称'
/

/*==============================================================*/
/* Table: MNT_APP                                               */
/*==============================================================*/
create table MNT_APP
(
   APP_ID               NUMBER(6)            not null,
   NAME                 VARCHAR2(255)        default NULL,
   UPLOAD_PATH          VARCHAR2(255)        default NULL,
   DEPLOY_PATH          VARCHAR2(255)        default NULL,
   BACKUP_PATH          VARCHAR2(255)        default NULL,
   PORT                 INTEGER              default NULL,
   START_SCRIPT         VARCHAR2(4000)       default NULL,
   DEPLOY_SCRIPT        VARCHAR2(4000)       default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_MNT_APP primary key (APP_ID)
)
/

comment on column MNT_APP.APP_ID is
'ID'
/

comment on column MNT_APP.NAME is
'应用名称'
/

comment on column MNT_APP.UPLOAD_PATH is
'上传目录'
/

comment on column MNT_APP.DEPLOY_PATH is
'部署路径'
/

comment on column MNT_APP.BACKUP_PATH is
'备份路径'
/

comment on column MNT_APP.PORT is
'应用端口'
/

comment on column MNT_APP.START_SCRIPT is
'启动脚本'
/

comment on column MNT_APP.DEPLOY_SCRIPT is
'部署脚本'
/

comment on column MNT_APP.CREATE_BY is
'创建者'
/

comment on column MNT_APP.UPDATE_BY is
'更新者'
/

comment on column MNT_APP.CREATE_TIME is
'创建日期'
/

comment on column MNT_APP.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: MNT_DATABASE                                          */
/*==============================================================*/
create table MNT_DATABASE
(
   DB_ID                VARCHAR2(50)         not null,
   NAME                 VARCHAR2(255)        not null,
   JDBC_URL             VARCHAR2(255)        not null,
   USER_NAME            VARCHAR2(255)        not null,
   PWD                  VARCHAR2(255)        not null,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_MNT_DATABASE primary key (DB_ID)
)
/

comment on column MNT_DATABASE.DB_ID is
'ID'
/

comment on column MNT_DATABASE.NAME is
'名称'
/

comment on column MNT_DATABASE.JDBC_URL is
'jdbc连接'
/

comment on column MNT_DATABASE.USER_NAME is
'账号'
/

comment on column MNT_DATABASE.PWD is
'密码'
/

comment on column MNT_DATABASE.CREATE_BY is
'创建者'
/

comment on column MNT_DATABASE.UPDATE_BY is
'更新者'
/

comment on column MNT_DATABASE.CREATE_TIME is
'创建时间'
/

comment on column MNT_DATABASE.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: MNT_DEPLOY                                            */
/*==============================================================*/
create table MNT_DEPLOY
(
   DEPLOY_ID            NUMBER(6)            not null,
   APP_ID               INTEGER              default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_MNT_DEPLOY primary key (DEPLOY_ID),
   constraint FK6sy157pseoxx4fmcqr1vnvvhy unique (APP_ID)
)
/

comment on column MNT_DEPLOY.DEPLOY_ID is
'ID'
/

comment on column MNT_DEPLOY.APP_ID is
'应用编号'
/

comment on column MNT_DEPLOY.CREATE_BY is
'创建者'
/

comment on column MNT_DEPLOY.UPDATE_BY is
'更新者'
/

comment on column MNT_DEPLOY.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: MNT_DEPLOY_HISTORY                                    */
/*==============================================================*/
create table MNT_DEPLOY_HISTORY
(
   HISTORY_ID           VARCHAR2(50)         not null,
   APP_NAME             VARCHAR2(255)        not null,
   DEPLOY_DATE          DATE                 not null,
   DEPLOY_USER          VARCHAR2(50)         not null,
   IP                   VARCHAR2(20)         not null,
   DEPLOY_ID            INTEGER              default NULL,
   constraint PK_MNT_DEPLOY_HISTORY primary key (HISTORY_ID)
)
/

comment on column MNT_DEPLOY_HISTORY.HISTORY_ID is
'ID'
/

comment on column MNT_DEPLOY_HISTORY.APP_NAME is
'应用名称'
/

comment on column MNT_DEPLOY_HISTORY.DEPLOY_DATE is
'部署日期'
/

comment on column MNT_DEPLOY_HISTORY.DEPLOY_USER is
'部署用户'
/

comment on column MNT_DEPLOY_HISTORY.IP is
'服务器IP'
/

comment on column MNT_DEPLOY_HISTORY.DEPLOY_ID is
'部署编号'
/

/*==============================================================*/
/* Table: MNT_DEPLOY_SERVER                                     */
/*==============================================================*/
create table MNT_DEPLOY_SERVER
(
   DEPLOY_ID            INTEGER              not null,
   SERVER_ID            INTEGER              not null,
   constraint PK_MNT_DEPLOY_SERVER primary key (DEPLOY_ID, SERVER_ID),
   constraint FKeaaha7jew9a02b3bk9ghols53 unique (SERVER_ID)
)
/

comment on column MNT_DEPLOY_SERVER.DEPLOY_ID is
'部署ID'
/

comment on column MNT_DEPLOY_SERVER.SERVER_ID is
'服务ID'
/

/*==============================================================*/
/* Table: MNT_SERVER                                            */
/*==============================================================*/
create table MNT_SERVER
(
   SERVER_ID            NUMBER(6)            not null,
   ACCOUNT              VARCHAR2(50)         default NULL,
   IP                   VARCHAR2(20)         default NULL,
   NAME                 VARCHAR2(100)        default NULL,
   PASSWORD             VARCHAR2(100)        default NULL,
   PORT                 INTEGER              default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_MNT_SERVER primary key (SERVER_ID),
   constraint idx_ip unique (IP)
)
/

comment on column MNT_SERVER.SERVER_ID is
'ID'
/

comment on column MNT_SERVER.ACCOUNT is
'账号'
/

comment on column MNT_SERVER.IP is
'IP地址'
/

comment on column MNT_SERVER.NAME is
'名称'
/

comment on column MNT_SERVER.PASSWORD is
'密码'
/

comment on column MNT_SERVER.PORT is
'端口'
/

comment on column MNT_SERVER.CREATE_BY is
'创建者'
/

comment on column MNT_SERVER.UPDATE_BY is
'更新者'
/

comment on column MNT_SERVER.CREATE_TIME is
'创建时间'
/

comment on column MNT_SERVER.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: SYS_DEPT                                              */
/*==============================================================*/
create table SYS_DEPT
(
   DEPT_ID              VARCHAR2(255)            not null,
   PID                  VARCHAR2(255)             default NULL,
   SUB_COUNT            INTEGER              default 0,
   NAME                 VARCHAR2(255)        not null,
   DEPT_SORT            INTEGER              default 999,
   ENABLED              VARCHAR2(255)             not null,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_SYS_DEPT primary key (DEPT_ID)
)
/

comment on column SYS_DEPT.DEPT_ID is
'ID'
/

comment on column SYS_DEPT.PID is
'上级部门'
/

comment on column SYS_DEPT.SUB_COUNT is
'子部门数目'
/

comment on column SYS_DEPT.NAME is
'名称'
/

comment on column SYS_DEPT.DEPT_SORT is
'排序'
/

comment on column SYS_DEPT.ENABLED is
'状态'
/

comment on column SYS_DEPT.CREATE_BY is
'创建者'
/

comment on column SYS_DEPT.UPDATE_BY is
'更新者'
/

comment on column SYS_DEPT.CREATE_TIME is
'创建日期'
/

comment on column SYS_DEPT.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: SYS_DICT                                              */
/*==============================================================*/
create table SYS_DICT
(
   DICT_ID              VARCHAR2(255)           not null,
   NAME                 VARCHAR2(255)        not null,
   DESCRIPTION          VARCHAR2(255)        default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_SYS_DICT primary key (DICT_ID)
)
/

comment on column SYS_DICT.DICT_ID is
'ID'
/

comment on column SYS_DICT.NAME is
'字典名称'
/

comment on column SYS_DICT.DESCRIPTION is
'描述'
/

comment on column SYS_DICT.CREATE_BY is
'创建者'
/

comment on column SYS_DICT.UPDATE_BY is
'更新者'
/

comment on column SYS_DICT.CREATE_TIME is
'创建日期'
/

comment on column SYS_DICT.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: SYS_DICT_DETAIL                                       */
/*==============================================================*/
create table SYS_DICT_DETAIL
(
   DETAIL_ID            VARCHAR2(255)          not null,
   DICT_ID              VARCHAR2(255)              default NULL,
   LABEL                VARCHAR2(255)        not null,
   VALUE                VARCHAR2(255)        not null,
   DICT_SORT            INTEGER              default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_SYS_DICT_DETAIL primary key (DETAIL_ID),
   constraint FK5tpkputc6d9nboxojdbgnpmyb unique (DICT_ID)
)
/

comment on column SYS_DICT_DETAIL.DETAIL_ID is
'ID'
/

comment on column SYS_DICT_DETAIL.DICT_ID is
'字典id'
/

comment on column SYS_DICT_DETAIL.LABEL is
'字典标签'
/

comment on column SYS_DICT_DETAIL.VALUE is
'字典值'
/

comment on column SYS_DICT_DETAIL.DICT_SORT is
'排序'
/

comment on column SYS_DICT_DETAIL.CREATE_BY is
'创建者'
/

comment on column SYS_DICT_DETAIL.UPDATE_BY is
'更新者'
/

comment on column SYS_DICT_DETAIL.CREATE_TIME is
'创建日期'
/

comment on column SYS_DICT_DETAIL.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: SYS_JOB                                               */
/*==============================================================*/
create table SYS_JOB
(
   JOB_ID               VARCHAR2(255)           not null,
   NAME                 VARCHAR2(255)        not null,
   ENABLED              SMALLINT             not null,
   JOB_SORT             INTEGER              default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_SYS_JOB primary key (JOB_ID)
)
/

comment on column SYS_JOB.JOB_ID is
'ID'
/

comment on column SYS_JOB.NAME is
'岗位名称'
/

comment on column SYS_JOB.ENABLED is
'岗位状态'
/

comment on column SYS_JOB.JOB_SORT is
'排序'
/

comment on column SYS_JOB.CREATE_BY is
'创建者'
/

comment on column SYS_JOB.UPDATE_BY is
'更新者'
/

comment on column SYS_JOB.CREATE_TIME is
'创建日期'
/

comment on column SYS_JOB.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: SYS_LOG                                               */
/*==============================================================*/
create table SYS_LOG
(
   LOG_ID               NUMBER(6)            not null,
   DESCRIPTION          VARCHAR2(255)        default NULL,
   LOG_TYPE             VARCHAR2(255)        default NULL,
   METHOD               VARCHAR2(255)        default NULL,
   PARAMS               CLOB                 default NULL,
   REQUEST_IP           VARCHAR2(255)        default NULL,
   TIME                 INTEGER              default NULL,
   USERNAME             VARCHAR2(255)        default NULL,
   ADDRESS              VARCHAR2(255)        default NULL,
   BROWSER              VARCHAR2(255)        default NULL,
   EXCEPTION_DETAIL     CLOB                 default NULL,
   CREATE_TIME          DATE                 default NULL,
   constraint PK_SYS_LOG primary key (LOG_ID),
   constraint log_create_time_index unique (CREATE_TIME),
   constraint inx_log_type unique (LOG_TYPE)
)
/

comment on column SYS_LOG.LOG_ID is
'ID'
/

/*==============================================================*/
/* Table: SYS_MENU                                              */
/*==============================================================*/
create table SYS_MENU
(
   MENU_ID              VARCHAR2(255)             not null,
   PID                  VARCHAR2(255)              default NULL,
   SUB_COUNT            INTEGER              default 0,
   TYPE                 INTEGER              default NULL,
   TITLE                VARCHAR2(255)        default NULL,
   NAME                 VARCHAR2(255)        default NULL,
   COMPONENT            VARCHAR2(255)        default NULL,
   MENU_SORT            INTEGER              default NULL,
   ICON                 VARCHAR2(255)        default NULL,
   PATH                 VARCHAR2(255)        default NULL,
   I_FRAME              VARCHAR2(255)              default NULL,
   CACHE                VARCHAR2(255)              default 'b',
   HIDDEN               VARCHAR2(255)              default 'b',
   PERMISSION           VARCHAR2(255)        default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_SYS_MENU primary key (MENU_ID)
)
/

comment on column SYS_MENU.MENU_ID is
'ID'
/

comment on column SYS_MENU.PID is
'上级菜单ID'
/

comment on column SYS_MENU.SUB_COUNT is
'子菜单数目'
/

comment on column SYS_MENU.TYPE is
'菜单类型'
/

comment on column SYS_MENU.TITLE is
'菜单标题'
/

comment on column SYS_MENU.NAME is
'组件名称'
/

comment on column SYS_MENU.COMPONENT is
'组件'
/

comment on column SYS_MENU.MENU_SORT is
'排序'
/

comment on column SYS_MENU.ICON is
'图标'
/

comment on column SYS_MENU.PATH is
'链接地址'
/

comment on column SYS_MENU.I_FRAME is
'是否外链'
/

comment on column SYS_MENU.PERMISSION is
'权限'
/

comment on column SYS_MENU.CREATE_BY is
'创建者'
/

comment on column SYS_MENU.UPDATE_BY is
'更新者'
/

comment on column SYS_MENU.CREATE_TIME is
'创建日期'
/

comment on column SYS_MENU.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: SYS_QUARTZ_JOB                                        */
/*==============================================================*/
create table SYS_QUARTZ_JOB
(
   JOB_ID               VARCHAR2(255)           not null,
   BEAN_NAME            VARCHAR2(255)        default NULL,
   CRON_EXPRESSION      VARCHAR2(255)        default NULL,
   IS_PAUSE             VARCHAR2(5)             default NULL,
   JOB_NAME             VARCHAR2(255)        default NULL,
   METHOD_NAME          VARCHAR2(255)        default NULL,
   PARAMS               VARCHAR2(255)        default NULL,
   DESCRIPTION          VARCHAR2(255)        default NULL,
   PERSON_IN_CHARGE     VARCHAR2(100)        default NULL,
   EMAIL                VARCHAR2(100)        default NULL,
   SUB_TASK             VARCHAR2(100)        default NULL,
   PAUSE_AFTER_FAILURE  VARCHAR2(5)             default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_SYS_QUARTZ_JOB primary key (JOB_ID),
)
/

comment on column SYS_QUARTZ_JOB.JOB_ID is
'ID'
/

comment on column SYS_QUARTZ_JOB.BEAN_NAME is
'Spring Bean名称'
/

comment on column SYS_QUARTZ_JOB.CRON_EXPRESSION is
'cron 表达式'
/

comment on column SYS_QUARTZ_JOB.IS_PAUSE is
'状态：1暂停、0启用'
/

comment on column SYS_QUARTZ_JOB.JOB_NAME is
'任务名称'
/

comment on column SYS_QUARTZ_JOB.METHOD_NAME is
'方法名称'
/

comment on column SYS_QUARTZ_JOB.PARAMS is
'参数'
/

comment on column SYS_QUARTZ_JOB.DESCRIPTION is
'备注'
/

comment on column SYS_QUARTZ_JOB.PERSON_IN_CHARGE is
'负责人'
/

comment on column SYS_QUARTZ_JOB.EMAIL is
'报警邮箱'
/

comment on column SYS_QUARTZ_JOB.SUB_TASK is
'子任务ID'
/

comment on column SYS_QUARTZ_JOB.PAUSE_AFTER_FAILURE is
'任务失败后是否暂停'
/

comment on column SYS_QUARTZ_JOB.CREATE_BY is
'创建者'
/

comment on column SYS_QUARTZ_JOB.UPDATE_BY is
'更新者'
/

comment on column SYS_QUARTZ_JOB.CREATE_TIME is
'创建日期'
/

comment on column SYS_QUARTZ_JOB.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: SYS_QUARTZ_LOG                                        */
/*==============================================================*/
create table SYS_QUARTZ_LOG
(
   LOG_ID               NUMBER(6)            not null,
   BEAN_NAME            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   CRON_EXPRESSION      VARCHAR2(255)        default NULL,
   EXCEPTION_DETAIL     CLOB                 default NULL,
   IS_SUCCESS           SMALLINT             default NULL,
   JOB_NAME             VARCHAR2(255)        default NULL,
   METHOD_NAME          VARCHAR2(255)        default NULL,
   PARAMS               VARCHAR2(255)        default NULL,
   TIME                 INTEGER              default NULL,
   constraint PK_SYS_QUARTZ_LOG primary key (LOG_ID)
)
/

comment on column SYS_QUARTZ_LOG.LOG_ID is
'ID'
/

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE
(
   ROLE_ID              VARCHAR2(32)             not null,
   NAME                 VARCHAR2(255)        not null,
   LEVEL_NAME                NUMBER               default NULL,
   DESCRIPTION          VARCHAR2(255)        default NULL,
   DATA_SCOPE           VARCHAR2(255)        default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   "LEVEL"              VARCHAR2(255) DEFAULT NULL,
   constraint PK_SYS_ROLE primary key (ROLE_ID)
)
/

comment on column SYS_ROLE.ROLE_ID is
'ID'
/

comment on column SYS_ROLE.NAME is
'名称'
/

comment on column SYS_ROLE.LEVEL_NAME is
'角色级别'
/

comment on column SYS_ROLE."LEVEL" is
'角色级别'
/

comment on column SYS_ROLE.DESCRIPTION is
'描述'
/

comment on column SYS_ROLE.DATA_SCOPE is
'数据权限'
/

comment on column SYS_ROLE.CREATE_BY is
'创建者'
/

comment on column SYS_ROLE.UPDATE_BY is
'更新者'
/

comment on column SYS_ROLE.CREATE_TIME is
'创建日期'
/

comment on column SYS_ROLE.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: SYS_ROLES_DEPTS                                       */
/*==============================================================*/
create table SYS_ROLES_DEPTS
(
   ROLE_ID              VARCHAR2(255)              not null,
   DEPT_ID              VARCHAR2(255)              not null,
   constraint PK_SYS_ROLES_DEPTS primary key (ROLE_ID, DEPT_ID),
   constraint FK7qg6itn5ajdoa9h9o78v9ksur unique (DEPT_ID)
)
/

/*==============================================================*/
/* Table: SYS_ROLES_MENUS                                       */
/*==============================================================*/
create table SYS_ROLES_MENUS
(
   MENU_ID              VARCHAR2(255)              not null,
   ROLE_ID              VARCHAR2(255)              not null,
   constraint PK_SYS_ROLES_MENUS primary key (MENU_ID, ROLE_ID)
)
/

comment on column SYS_ROLES_MENUS.MENU_ID is
'菜单ID'
/

comment on column SYS_ROLES_MENUS.ROLE_ID is
'角色ID'
/

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
   USER_ID              VARCHAR2(255)            not null,
   DEPT_ID              VARCHAR2(255)              default NULL,
   USERNAME             VARCHAR2(255)        default NULL,
   NICK_NAME            VARCHAR2(255)        default NULL,
   GENDER               VARCHAR2(5)          default NULL,
   PHONE                VARCHAR2(255)        default NULL,
   EMAIL                VARCHAR2(255)        default NULL,
   AVATAR_NAME          VARCHAR2(255)        default NULL,
   AVATAR_PATH          VARCHAR2(255)        default NULL,
   PASSWORD             VARCHAR2(255)        default NULL,
   IS_ADMIN             VARCHAR2(255)             default 'b',
   ENABLED              VARCHAR2(255)              default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   PWD_RESET_TIME       DATE                 default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_SYS_USER primary key (USER_ID)
)
/

comment on column SYS_USER.USER_ID is
'ID'
/

comment on column SYS_USER.DEPT_ID is
'部门名称'
/

comment on column SYS_USER.USERNAME is
'用户名'
/

comment on column SYS_USER.NICK_NAME is
'昵称'
/

comment on column SYS_USER.GENDER is
'性别'
/

comment on column SYS_USER.PHONE is
'手机号码'
/

comment on column SYS_USER.EMAIL is
'邮箱'
/

comment on column SYS_USER.AVATAR_NAME is
'头像地址'
/

comment on column SYS_USER.AVATAR_PATH is
'头像真实路径'
/

comment on column SYS_USER.PASSWORD is
'密码'
/

comment on column SYS_USER.ENABLED is
'状态：1启用、0禁用'
/

comment on column SYS_USER.CREATE_BY is
'创建者'
/

comment on column SYS_USER.UPDATE_BY is
'更新着'
/

comment on column SYS_USER.PWD_RESET_TIME is
'修改密码的时间'
/

comment on column SYS_USER.CREATE_TIME is
'创建日期'
/

comment on column SYS_USER.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: SYS_USERS_JOBS                                        */
/*==============================================================*/
create table SYS_USERS_JOBS
(
   USER_ID              VARCHAR2(255)               not null,
   JOB_ID               VARCHAR2(255)               not null,
   constraint PK_SYS_USERS_JOBS primary key (USER_ID, JOB_ID)
)
/

comment on column SYS_USERS_JOBS.USER_ID is
'用户ID'
/

comment on column SYS_USERS_JOBS.JOB_ID is
'岗位ID'
/

/*==============================================================*/
/* Table: SYS_USERS_ROLES                                       */
/*==============================================================*/
create table SYS_USERS_ROLES
(
   USER_ID              VARCHAR2(255)              not null,
   ROLE_ID              VARCHAR2(255)              not null,
   constraint PK_SYS_USERS_ROLES primary key (USER_ID, ROLE_ID),
   constraint FKq4eq273l04bpu4efj0jd0jb98 unique (ROLE_ID)
)
/

comment on column SYS_USERS_ROLES.USER_ID is
'用户ID'
/

comment on column SYS_USERS_ROLES.ROLE_ID is
'角色ID'
/

/*==============================================================*/
/* Table: TOOL_ALIPAY_CONFIG                                    */
/*==============================================================*/
create table TOOL_ALIPAY_CONFIG
(
   CONFIG_ID            INTEGER              not null,
   APP_ID               VARCHAR2(255)        default NULL,
   CHARSET              VARCHAR2(255)        default NULL,
   FORMAT               VARCHAR2(255)        default NULL,
   GATEWAY_URL          VARCHAR2(255)        default NULL,
   NOTIFY_URL           VARCHAR2(255)        default NULL,
   PRIVATE_KEY          CLOB                 default NULL,
   PUBLIC_KEY           CLOB                 default NULL,
   RETURN_URL           VARCHAR2(255)        default NULL,
   SIGN_TYPE            VARCHAR2(255)        default NULL,
   SYS_SERVICE_PROVIDER_ID VARCHAR2(255)        default NULL,
   constraint PK_TOOL_ALIPAY_CONFIG primary key (CONFIG_ID)
)
/

comment on column TOOL_ALIPAY_CONFIG.CONFIG_ID is
'ID'
/

comment on column TOOL_ALIPAY_CONFIG.APP_ID is
'应用ID'
/

comment on column TOOL_ALIPAY_CONFIG.CHARSET is
'编码'
/

comment on column TOOL_ALIPAY_CONFIG.FORMAT is
'类型 固定格式json'
/

comment on column TOOL_ALIPAY_CONFIG.GATEWAY_URL is
'网关地址'
/

comment on column TOOL_ALIPAY_CONFIG.NOTIFY_URL is
'异步回调'
/

comment on column TOOL_ALIPAY_CONFIG.PRIVATE_KEY is
'私钥'
/

comment on column TOOL_ALIPAY_CONFIG.PUBLIC_KEY is
'公钥'
/

comment on column TOOL_ALIPAY_CONFIG.RETURN_URL is
'回调地址'
/

comment on column TOOL_ALIPAY_CONFIG.SIGN_TYPE is
'签名方式'
/

comment on column TOOL_ALIPAY_CONFIG.SYS_SERVICE_PROVIDER_ID is
'商户号'
/

/*==============================================================*/
/* Table: TOOL_EMAIL_CONFIG                                     */
/*==============================================================*/
create table TOOL_EMAIL_CONFIG
(
   CONFIG_ID            INTEGER              not null,
   FROM_USER            VARCHAR2(255)        default NULL,
   HOST                 VARCHAR2(255)        default NULL,
   PASS                 VARCHAR2(255)        default NULL,
   PORT                 VARCHAR2(255)        default NULL,
   "USER"               VARCHAR2(255)        default NULL,
   constraint PK_TOOL_EMAIL_CONFIG primary key (CONFIG_ID)
)
/

comment on column TOOL_EMAIL_CONFIG.CONFIG_ID is
'ID'
/

comment on column TOOL_EMAIL_CONFIG.FROM_USER is
'收件人'
/

comment on column TOOL_EMAIL_CONFIG.HOST is
'邮件服务器SMTP地址'
/

comment on column TOOL_EMAIL_CONFIG.PASS is
'密码'
/

comment on column TOOL_EMAIL_CONFIG.PORT is
'端口'
/

comment on column TOOL_EMAIL_CONFIG."USER" is
'发件者用户名'
/

/*==============================================================*/
/* Table: TOOL_LOCAL_STORAGE                                    */
/*==============================================================*/
create table TOOL_LOCAL_STORAGE
(
   STORAGE_ID           NUMBER(6)            not null,
   REAL_NAME            VARCHAR2(255)        default NULL,
   NAME                 VARCHAR2(255)        default NULL,
   SUFFIX               VARCHAR2(255)        default NULL,
   PATH                 VARCHAR2(255)        default NULL,
   TYPE                 VARCHAR2(255)        default NULL,
   LOCAL_SIZE               VARCHAR2(100)        default NULL,
   CREATE_BY            VARCHAR2(255)        default NULL,
   UPDATE_BY            VARCHAR2(255)        default NULL,
   CREATE_TIME          DATE                 default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_TOOL_LOCAL_STORAGE primary key (STORAGE_ID)
)
/

comment on column TOOL_LOCAL_STORAGE.STORAGE_ID is
'ID'
/

comment on column TOOL_LOCAL_STORAGE.REAL_NAME is
'文件真实的名称'
/

comment on column TOOL_LOCAL_STORAGE.NAME is
'文件名'
/

comment on column TOOL_LOCAL_STORAGE.SUFFIX is
'后缀'
/

comment on column TOOL_LOCAL_STORAGE.PATH is
'路径'
/

comment on column TOOL_LOCAL_STORAGE.TYPE is
'类型'
/

comment on column TOOL_LOCAL_STORAGE."SIZE" is
'大小'
/

comment on column TOOL_LOCAL_STORAGE.CREATE_BY is
'创建者'
/

comment on column TOOL_LOCAL_STORAGE.UPDATE_BY is
'更新者'
/

comment on column TOOL_LOCAL_STORAGE.CREATE_TIME is
'创建日期'
/

comment on column TOOL_LOCAL_STORAGE.UPDATE_TIME is
'更新时间'
/

/*==============================================================*/
/* Table: TOOL_QINIU_CONFIG                                     */
/*==============================================================*/
create table TOOL_QINIU_CONFIG
(
   CONFIG_ID            INTEGER              not null,
   ACCESS_KEY           CLOB                 default NULL,
   BUCKET               VARCHAR2(255)        default NULL,
   HOST                 VARCHAR2(255)        not null,
   SECRET_KEY           CLOB                 default NULL,
   TYPE                 VARCHAR2(255)        default NULL,
   ZONE                 VARCHAR2(255)        default NULL,
   constraint PK_TOOL_QINIU_CONFIG primary key (CONFIG_ID)
)
/

comment on column TOOL_QINIU_CONFIG.CONFIG_ID is
'ID'
/

comment on column TOOL_QINIU_CONFIG.ACCESS_KEY is
'accessKey'
/

comment on column TOOL_QINIU_CONFIG.BUCKET is
'Bucket 识别符'
/

comment on column TOOL_QINIU_CONFIG.HOST is
'外链域名'
/

comment on column TOOL_QINIU_CONFIG.SECRET_KEY is
'secretKey'
/

comment on column TOOL_QINIU_CONFIG.TYPE is
'空间类型'
/

comment on column TOOL_QINIU_CONFIG.ZONE is
'机房'
/

/*==============================================================*/
/* Table: TOOL_QINIU_CONTENT                                    */
/*==============================================================*/
create table TOOL_QINIU_CONTENT
(
   CONTENT_ID           NUMBER(6)            not null,
   BUCKET               VARCHAR2(255)        default NULL,
   NAME                 VARCHAR2(255)        default NULL,
   QINIU_SIZE               VARCHAR2(255)        default NULL,
   TYPE                 VARCHAR2(255)        default NULL,
   URL                  VARCHAR2(255)        default NULL,
   SUFFIX               VARCHAR2(255)        default NULL,
   UPDATE_TIME          DATE                 default NULL,
   constraint PK_TOOL_QINIU_CONTENT primary key (CONTENT_ID),
   constraint uniq_name unique (NAME)
)
/

comment on column TOOL_QINIU_CONTENT.CONTENT_ID is
'ID'
/

comment on column TOOL_QINIU_CONTENT.BUCKET is
'Bucket 识别符'
/

comment on column TOOL_QINIU_CONTENT.NAME is
'文件名称'
/

comment on column TOOL_QINIU_CONTENT.QINIU_SIZE is
'文件大小'
/

comment on column TOOL_QINIU_CONTENT.TYPE is
'文件类型：私有或公开'
/

comment on column TOOL_QINIU_CONTENT.URL is
'文件url'
/

comment on column TOOL_QINIU_CONTENT.SUFFIX is
'文件后缀'
/

comment on column TOOL_QINIU_CONTENT.UPDATE_TIME is
'上传或同步的时间'
/

COMMENT ON TABLE CODE_COLUMN_CONFIG IS '代码生成字段信息存储';
COMMENT ON TABLE CODE_GEN_CONFIG IS '代码生成器配置';
COMMENT ON TABLE MNT_APP IS '应用管理';
COMMENT ON TABLE MNT_DATABASE IS '数据库管理';
COMMENT ON TABLE MNT_DEPLOY IS '部署管理';
COMMENT ON TABLE MNT_DEPLOY_HISTORY IS '部署历史管理';
COMMENT ON TABLE MNT_DEPLOY_SERVER IS '应用与服务器关联';
COMMENT ON TABLE MNT_SERVER IS '服务器管理';
COMMENT ON TABLE SYS_DEPT IS '部门';
COMMENT ON TABLE SYS_DICT IS '数据字典';
COMMENT ON TABLE SYS_DICT_DETAIL IS '数据字典详情';
COMMENT ON TABLE SYS_JOB IS '岗位';
COMMENT ON TABLE SYS_LOG IS '系统日志';
COMMENT ON TABLE SYS_MENU IS '系统菜单';
COMMENT ON TABLE SYS_QUARTZ_JOB IS '定时任务';
COMMENT ON TABLE SYS_QUARTZ_LOG IS '定时任务日志';
COMMENT ON TABLE SYS_ROLE IS '角色表';
COMMENT ON TABLE SYS_ROLES_DEPTS IS '角色部门关联';
COMMENT ON TABLE SYS_ROLES_MENUS IS '角色菜单关联';
COMMENT ON TABLE SYS_USER IS '系统用户';
COMMENT ON TABLE SYS_USERS_JOBS IS '';
COMMENT ON TABLE SYS_USERS_ROLES IS '用户角色关联';
COMMENT ON TABLE TEST IS '';
COMMENT ON TABLE TOOL_ALIPAY_CONFIG IS '支付宝配置类';
COMMENT ON TABLE TOOL_EMAIL_CONFIG IS '邮箱配置';
COMMENT ON TABLE TOOL_LOCAL_STORAGE IS '本地存储';
COMMENT ON TABLE TOOL_QINIU_CONFIG IS '七牛云配置';
COMMENT ON TABLE TOOL_QINIU_CONTENT IS '七牛云文件存储';


INSERT INTO sys_user(user_id, dept_id, username, nick_name, gender, phone, email, avatar_name, avatar_path, password, is_admin, enabled, create_by, update_by, pwd_reset_time, create_time, update_time) VALUES ('1', '2', 'admin', '管理员', '男', '18888888888', '201507802@qq.com', 'avatar-20200806032259161.png', '/Users/jie/Documents/work/me/admin/eladmin/~/avatar/avatar-20200806032259161.png', '$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa', '1', '1', NULL, 'admin',  to_date('2010-10-01 10:10:10' , 'yyyy-mm-dd hh24:mi:ss'),  to_date('2010-01-01 10:10:10' , 'yyyy-mm-dd hh24:mi:ss'),  to_date('2010-01-01 10:10:10' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_user(user_id, dept_id, username, nick_name, gender, phone, email, avatar_name, avatar_path, password, is_admin, enabled, create_by, update_by, pwd_reset_time, create_time, update_time) VALUES ('2', '2', 'test', '测试', '男', '19999999999', '231@qq.com', NULL, NULL, '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', '0', '1', 'admin', 'admin',  to_date('2010-01-01 10:10:10' , 'yyyy-mm-dd hh24:mi:ss'),  to_date('2010-01-01 10:10:10' , 'yyyy-mm-dd hh24:mi:ss'),  to_date('2010-01-01 10:10:10' , 'yyyy-mm-dd hh24:mi:ss'));

INSERT INTO sys_dept(dept_id, pid, sub_count, name, dept_sort, enabled, create_by, update_by, create_time, update_time) VALUES (2, 7, 1, '研发部', 3, '1', 'admin', 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_dept(dept_id, pid, sub_count, name, dept_sort, enabled, create_by, update_by, create_time, update_time) VALUES (5, 7, 0, '运维部', 4, '1', 'admin', 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_dept(dept_id, pid, sub_count, name, dept_sort, enabled, create_by, update_by, create_time, update_time) VALUES (6, 8, 0, '测试部', 6, '1', 'admin', 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_dept(dept_id, pid, sub_count, name, dept_sort, enabled, create_by, update_by, create_time, update_time) VALUES (7, NULL, 2, '华南分部', 0, '1', 'admin', 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_dept(dept_id, pid, sub_count, name, dept_sort, enabled, create_by, update_by, create_time, update_time) VALUES (8, NULL, 2, '华北分部', 1, '1', 'admin', 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_dept(dept_id, pid, sub_count, name, dept_sort, enabled, create_by, update_by, create_time, update_time) VALUES (15, 8, 0, 'UI部门', 7, '1', 'admin', 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_dept(dept_id, pid, sub_count, name, dept_sort, enabled, create_by, update_by, create_time, update_time) VALUES (17, 2, 0, '研发一组', 999, '1', 'admin', 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));

INSERT INTO sys_dict VALUES (1, 'user_status', '用户状态', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_dict VALUES (4, 'dept_status', '部门状态', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_dict VALUES (5, 'job_status', '岗位状态', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);

INSERT INTO sys_dict_detail VALUES (1, 1, '激活', 'true', 1, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_dict_detail VALUES (2, 1, '禁用', 'false', 2, NULL, NULL, NULL, NULL);
INSERT INTO sys_dict_detail VALUES (3, 4, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO sys_dict_detail VALUES (4, 4, '停用', 'false', 2, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_dict_detail VALUES (5, 5, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO sys_dict_detail VALUES (6, 5, '停用', 'false', 2, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);

INSERT INTO sys_job VALUES (8, '人事专员', '1', 3, NULL, NULL,  to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_job VALUES (10, '产品经理', '1', 4, NULL, NULL,  to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_job VALUES (11, '全栈开发', '1', 2, NULL, 'admin',  to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'),  to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_job VALUES (12, '软件测试', '1', 5, NULL, 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'),  to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));

INSERT INTO sys_users_jobs VALUES (1, 11);
INSERT INTO sys_users_jobs VALUES (2, 12);

INSERT INTO sys_users_roles VALUES (1, 1);
INSERT INTO sys_users_roles VALUES (2, 2);

INSERT INTO tool_alipay_config VALUES (1, '2016091700532697', 'utf-8', 'JSON', 'https://openapi.alipaydev.com/gateway.do', 'http://api.auauz.net/api/aliPay/notify', 'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5js8sInU10AJ0cAQ8UMMyXrQ+oHZEkVt5lBwsStmTJ7YikVYgbskx1YYEXTojRsWCb+SH/kDmDU4pK/u91SJ4KFCRMF2411piYuXU/jF96zKrADznYh/zAraqT6hvAIVtQAlMHN53nx16rLzZ/8jDEkaSwT7+HvHiS+7sxSojnu/3oV7BtgISoUNstmSe8WpWHOaWv19xyS+Mce9MY4BfseFhzTICUymUQdd/8hXA28/H6osUfAgsnxAKv7Wil3aJSgaJczWuflYOve0dJ3InZkhw5Cvr0atwpk8YKBQjy5CdkoHqvkOcIB+cYHXJKzOE5tqU7inSwVbHzOLQ3XbnAgMBAAECggEAVJp5eT0Ixg1eYSqFs9568WdetUNCSUchNxDBu6wxAbhUgfRUGZuJnnAll63OCTGGck+EGkFh48JjRcBpGoeoHLL88QXlZZbC/iLrea6gcDIhuvfzzOffe1RcZtDFEj9hlotg8dQj1tS0gy9pN9g4+EBH7zeu+fyv+qb2e/v1l6FkISXUjpkD7RLQr3ykjiiEw9BpeKb7j5s7Kdx1NNIzhkcQKNqlk8JrTGDNInbDM6inZfwwIO2R1DHinwdfKWkvOTODTYa2MoAvVMFT9Bec9FbLpoWp7ogv1JMV9svgrcF9XLzANZ/OQvkbe9TV9GWYvIbxN6qwQioKCWO4GPnCAQKBgQDgW5MgfhX8yjXqoaUy/d1VjI8dHeIyw8d+OBAYwaxRSlCfyQ+tieWcR2HdTzPca0T0GkWcKZm0ei5xRURgxt4DUDLXNh26HG0qObbtLJdu/AuBUuCqgOiLqJ2f1uIbrz6OZUHns+bT/jGW2Ws8+C13zTCZkZt9CaQsrp3QOGDx5wKBgQDTul39hp3ZPwGNFeZdkGoUoViOSd5Lhowd5wYMGAEXWRLlU8z+smT5v0POz9JnIbCRchIY2FAPKRdVTICzmPk2EPJFxYTcwaNbVqL6lN7J2IlXXMiit5QbiLauo55w7plwV6LQmKm9KV7JsZs5XwqF7CEovI7GevFzyD3w+uizAQKBgC3LY1eRhOlpWOIAhpjG6qOoohmeXOphvdmMlfSHq6WYFqbWwmV4rS5d/6LNpNdL6fItXqIGd8I34jzql49taCmi+A2nlR/E559j0mvM20gjGDIYeZUz5MOE8k+K6/IcrhcgofgqZ2ZED1ksHdB/E8DNWCswZl16V1FrfvjeWSNnAoGAMrBplCrIW5xz+J0Hm9rZKrs+AkK5D4fUv8vxbK/KgxZ2KaUYbNm0xv39c+PZUYuFRCz1HDGdaSPDTE6WeWjkMQd5mS6ikl9hhpqFRkyh0d0fdGToO9yLftQKOGE/q3XUEktI1XvXF0xyPwNgUCnq0QkpHyGVZPtGFxwXiDvpvgECgYA5PoB+nY8iDiRaJNko9w0hL4AeKogwf+4TbCw+KWVEn6jhuJa4LFTdSqp89PktQaoVpwv92el/AhYjWOl/jVCm122f9b7GyoelbjMNolToDwe5pF5RnSpEuDdLy9MfE8LnE3PlbE7E5BipQ3UjSebkgNboLHH/lNZA5qvEtvbfvQ==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAut9evKRuHJ/2QNfDlLwvN/S8l9hRAgPbb0u61bm4AtzaTGsLeMtScetxTWJnVvAVpMS9luhEJjt+Sbk5TNLArsgzzwARgaTKOLMT1TvWAK5EbHyI+eSrc3s7Awe1VYGwcubRFWDm16eQLv0k7iqiw+4mweHSz/wWyvBJVgwLoQ02btVtAQErCfSJCOmt0Q/oJQjj08YNRV4EKzB19+f5A+HQVAKy72dSybTzAK+3FPtTtNen/+b5wGeat7c32dhYHnGorPkPeXLtsqqUTp1su5fMfd4lElNdZaoCI7osZxWWUo17vBCZnyeXc9fk0qwD9mK6yRAxNbrY72Xx5VqIqwIDAQAB', 'http://api.auauz.net/api/aliPay/return', 'RSA2', '2088102176044281');

INSERT INTO sys_menu VALUES (1, NULL, 7, 0, '系统管理', NULL, NULL, 1, 'system', 'system', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (2, 1, 3, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', 'user', '0', '0', '0', 'user:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (3, 1, 3, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', 'role', '0', '0', '0', 'roles:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (5, 1, 3, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', 'menu', '0', '0', '0', 'menu:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (6, NULL, 5, 0, '系统监控', NULL, NULL, 10, 'monitor', 'monitor', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (7, 6, 0, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', 'logs', '0', '1', '0', NULL, NULL, 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_menu VALUES (9, 6, 0, 1, 'SQL监控', 'Sql', 'monitor/sql/index', 18, 'sqlMonitor', 'druid', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (10, NULL, 5, 0, '组件管理', NULL, NULL, 50, 'zujian', 'components', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (11, 10, 0, 1, '图标库', 'Icons', 'components/icons/index', 51, 'icon', 'icon', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (14, 36, 0, 1, '邮件工具', 'Email', 'tools/email/index', 35, 'email', 'email', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (15, 10, 0, 1, '富文本', 'Editor', 'components/Editor', 52, 'fw', 'tinymce', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (18, 36, 3, 1, '存储管理', 'Storage', 'tools/storage/index', 34, 'qiniu', 'storage', '0', '0', '0', 'storage:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (19, 36, 0, 1, '支付宝工具', 'AliPay', 'tools/aliPay/index', 37, 'alipay', 'aliPay', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (21, NULL, 2, 0, '多级菜单', NULL, '', 900, 'menu', 'nested', '0', '0', '0', NULL, NULL, 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_menu VALUES (22, 21, 2, 1, '二级菜单1', NULL, 'nested/menu1/index', 999, 'menu', 'menu1', '0', '0', '0', NULL, NULL, 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_menu VALUES (23, 21, 0, 1, '二级菜单2', NULL, 'nested/menu2/index', 999, 'menu', 'menu2', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (24, 22, 0, 1, '三级菜单1', NULL, 'nested/menu1/menu1-1', 999, 'menu', 'menu1-1', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (27, 22, 0, 1, '三级菜单2', NULL, 'nested/menu1/menu1-2', 999, 'menu', 'menu1-2', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (28, 1, 3, 1, '任务调度', 'Timing', 'system/timing/index', 999, 'timing', 'timing', '0', '0', '0', 'timing:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (30, 36, 0, 1, '代码生成', 'GeneratorIndex', 'generator/index', 32, 'dev', 'generator', '0', '1', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (32, 6, 0, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', 'errorLog', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (33, 10, 0, 1, 'Markdown', 'Markdown', 'components/MarkDown', 53, 'markdown', 'markdown', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (34, 10, 0, 1, 'Yaml编辑器', 'YamlEdit', 'components/YamlEdit', 54, 'dev', 'yaml', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (35, 1, 3, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', 'dept', '0', '0', '0', 'dept:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (36, NULL, 7, 0, '系统工具', NULL, '', 30, 'sys-tools', 'sys-tools', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (37, 1, 3, 1, '岗位管理', 'Jo', 'system/job/index', 7, 'Steve-Jobs', 'jo', '0', '0', '0', 'job:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (38, 36, 0, 1, '接口文档', 'Swagger', 'tools/swagger/index', 36, 'swagger', 'swagger2', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (39, 1, 3, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', 'dict', '0', '0', '0', 'dict:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (41, 6, 0, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', 'online', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (44, 2, 0, 2, '用户新增', NULL, '', 2, '', '', '0', '0', '0', 'user:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (45, 2, 0, 2, '用户编辑', NULL, '', 3, '', '', '0', '0', '0', 'user:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (46, 2, 0, 2, '用户删除', NULL, '', 4, '', '', '0', '0', '0', 'user:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (48, 3, 0, 2, '角色创建', NULL, '', 2, '', '', '0', '0', '0', 'roles:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (49, 3, 0, 2, '角色修改', NULL, '', 3, '', '', '0', '0', '0', 'roles:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (50, 3, 0, 2, '角色删除', NULL, '', 4, '', '', '0', '0', '0', 'roles:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (52, 5, 0, 2, '菜单新增', NULL, '', 2, '', '', '0', '0', '0', 'menu:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (53, 5, 0, 2, '菜单编辑', NULL, '', 3, '', '', '0', '0', '0', 'menu:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (54, 5, 0, 2, '菜单删除', NULL, '', 4, '', '', '0', '0', '0', 'menu:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (56, 35, 0, 2, '部门新增', NULL, '', 2, '', '', '0', '0', '0', 'dept:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (57, 35, 0, 2, '部门编辑', NULL, '', 3, '', '', '0', '0', '0', 'dept:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (58, 35, 0, 2, '部门删除', NULL, '', 4, '', '', '0', '0', '0', 'dept:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (60, 37, 0, 2, '岗位新增', NULL, '', 2, '', '', '0', '0', '0', 'job:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (61, 37, 0, 2, '岗位编辑', NULL, '', 3, '', '', '0', '0', '0', 'job:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (62, 37, 0, 2, '岗位删除', NULL, '', 4, '', '', '0', '0', '0', 'job:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (64, 39, 0, 2, '字典新增', NULL, '', 2, '', '', '0', '0', '0', 'dict:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (65, 39, 0, 2, '字典编辑', NULL, '', 3, '', '', '0', '0', '0', 'dict:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (66, 39, 0, 2, '字典删除', NULL, '', 4, '', '', '0', '0', '0', 'dict:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (73, 28, 0, 2, '任务新增', NULL, '', 2, '', '', '0', '0', '0', 'timing:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (74, 28, 0, 2, '任务编辑', NULL, '', 3, '', '', '0', '0', '0', 'timing:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (75, 28, 0, 2, '任务删除', NULL, '', 4, '', '', '0', '0', '0', 'timing:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (77, 18, 0, 2, '上传文件', NULL, '', 2, '', '', '0', '0', '0', 'storage:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (78, 18, 0, 2, '文件编辑', NULL, '', 3, '', '', '0', '0', '0', 'storage:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (79, 18, 0, 2, '文件删除', NULL, '', 4, '', '', '0', '0', '0', 'storage:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (80, 6, 0, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', '0', '0', '0', 'monitor:list', NULL, 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_menu VALUES (82, 36, 0, 1, '生成配置', 'GeneratorConfig', 'generator/config', 33, 'dev', 'generator/config/:tableName', '0', '1', '1', '', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (83, 10, 0, 1, '图表库', 'Echarts', 'components/Echarts', 50, 'chart', 'echarts', '0', '1', '0', '', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (90, NULL, 5, 1, '运维管理', 'Mnt', '', 20, 'mnt', 'mnt', '0', '0', '0', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (92, 90, 3, 1, '服务器', 'ServerDeploy', 'mnt/server/index', 22, 'server', 'mnt/serverDeploy', '0', '0', '0', 'serverDeploy:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (93, 90, 3, 1, '应用管理', 'App', 'mnt/app/index', 23, 'app', 'mnt/app', '0', '0', '0', 'app:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (94, 90, 3, 1, '部署管理', 'Deploy', 'mnt/deploy/index', 24, 'deploy', 'mnt/deploy', '0', '0', '0', 'deploy:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (97, 90, 1, 1, '部署备份', 'DeployHistory', 'mnt/deployHistory/index', 25, 'backup', 'mnt/deployHistory', '0', '0', '0', 'deployHistory:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (98, 90, 3, 1, '数据库管理', 'Database', 'mnt/database/index', 26, 'database', 'mnt/database', '0', '0', '0', 'database:list', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (102, 97, 0, 2, '删除', NULL, '', 999, '', '', '0', '0', '0', 'deployHistory:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (103, 92, 0, 2, '服务器新增', NULL, '', 999, '', '', '0', '0', '0', 'serverDeploy:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (104, 92, 0, 2, '服务器编辑', NULL, '', 999, '', '', '0', '0', '0', 'serverDeploy:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (105, 92, 0, 2, '服务器删除', NULL, '', 999, '', '', '0', '0', '0', 'serverDeploy:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (106, 93, 0, 2, '应用新增', NULL, '', 999, '', '', '0', '0', '0', 'app:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (107, 93, 0, 2, '应用编辑', NULL, '', 999, '', '', '0', '0', '0', 'app:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (108, 93, 0, 2, '应用删除', NULL, '', 999, '', '', '0', '0', '0', 'app:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (109, 94, 0, 2, '部署新增', NULL, '', 999, '', '', '0', '0', '0', 'deploy:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (110, 94, 0, 2, '部署编辑', NULL, '', 999, '', '', '0', '0', '0', 'deploy:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (111, 94, 0, 2, '部署删除', NULL, '', 999, '', '', '0', '0', '0', 'deploy:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (112, 98, 0, 2, '数据库新增', NULL, '', 999, '', '', '0', '0', '0', 'database:add', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (113, 98, 0, 2, '数据库编辑', NULL, '', 999, '', '', '0', '0', '0', 'database:edit', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (114, 98, 0, 2, '数据库删除', NULL, '', 999, '', '', '0', '0', '0', 'database:del', NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);
INSERT INTO sys_menu VALUES (116, 36, 0, 1, '生成预览', 'Preview', 'generator/preview', 999, 'java', 'generator/preview/:tableName', '0', '1', '1', NULL, NULL, NULL, to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), NULL);


INSERT INTO sys_quartz_job VALUES (2, 'testTask', '0/5 * * * * ?', '1', '测试1', 'run1', 'test', '带参测试，多参使用json', '测试', NULL, NULL, NULL, NULL, 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_quartz_job VALUES (3, 'testTask', '0/5 * * * * ?', '1', '测试', 'run', '', '不带参测试', 'Zheng Jie', '', '5,6', '1', NULL, 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_quartz_job VALUES (5, 'Test', '0/5 * * * * ?', '1', '任务告警测试', 'run', NULL, '测试', 'test', '', NULL, '1', 'admin', 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));
INSERT INTO sys_quartz_job VALUES (6, 'testTask', '0/5 * * * * ?', '1', '测试3', 'run2', NULL, '测试3', 'Zheng Jie', '', NULL, '1', 'admin', 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'));


INSERT INTO sys_role VALUES (1, '超级管理员', 1, '-', '全部', NULL, 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'),1);
INSERT INTO sys_role VALUES (2, '普通用户', 2, '-', '本级', NULL, 'admin', to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'), to_date('2020-08-02 14:49:07' , 'yyyy-mm-dd hh24:mi:ss'),2);

INSERT INTO sys_roles_menus VALUES (1, 1);
INSERT INTO sys_roles_menus VALUES (2, 1);
INSERT INTO sys_roles_menus VALUES (3, 1);
INSERT INTO sys_roles_menus VALUES (5, 1);
INSERT INTO sys_roles_menus VALUES (6, 1);
INSERT INTO sys_roles_menus VALUES (7, 1);
INSERT INTO sys_roles_menus VALUES (9, 1);
INSERT INTO sys_roles_menus VALUES (10, 1);
INSERT INTO sys_roles_menus VALUES (11, 1);
INSERT INTO sys_roles_menus VALUES (14, 1);
INSERT INTO sys_roles_menus VALUES (15, 1);
INSERT INTO sys_roles_menus VALUES (18, 1);
INSERT INTO sys_roles_menus VALUES (19, 1);
INSERT INTO sys_roles_menus VALUES (21, 1);
INSERT INTO sys_roles_menus VALUES (22, 1);
INSERT INTO sys_roles_menus VALUES (23, 1);
INSERT INTO sys_roles_menus VALUES (24, 1);
INSERT INTO sys_roles_menus VALUES (27, 1);
INSERT INTO sys_roles_menus VALUES (28, 1);
INSERT INTO sys_roles_menus VALUES (30, 1);
INSERT INTO sys_roles_menus VALUES (32, 1);
INSERT INTO sys_roles_menus VALUES (33, 1);
INSERT INTO sys_roles_menus VALUES (34, 1);
INSERT INTO sys_roles_menus VALUES (35, 1);
INSERT INTO sys_roles_menus VALUES (36, 1);
INSERT INTO sys_roles_menus VALUES (37, 1);
INSERT INTO sys_roles_menus VALUES (38, 1);
INSERT INTO sys_roles_menus VALUES (39, 1);
INSERT INTO sys_roles_menus VALUES (41, 1);
INSERT INTO sys_roles_menus VALUES (44, 1);
INSERT INTO sys_roles_menus VALUES (45, 1);
INSERT INTO sys_roles_menus VALUES (46, 1);
INSERT INTO sys_roles_menus VALUES (48, 1);
INSERT INTO sys_roles_menus VALUES (49, 1);
INSERT INTO sys_roles_menus VALUES (50, 1);
INSERT INTO sys_roles_menus VALUES (52, 1);
INSERT INTO sys_roles_menus VALUES (53, 1);
INSERT INTO sys_roles_menus VALUES (54, 1);
INSERT INTO sys_roles_menus VALUES (56, 1);
INSERT INTO sys_roles_menus VALUES (57, 1);
INSERT INTO sys_roles_menus VALUES (58, 1);
INSERT INTO sys_roles_menus VALUES (60, 1);
INSERT INTO sys_roles_menus VALUES (61, 1);
INSERT INTO sys_roles_menus VALUES (62, 1);
INSERT INTO sys_roles_menus VALUES (64, 1);
INSERT INTO sys_roles_menus VALUES (65, 1);
INSERT INTO sys_roles_menus VALUES (66, 1);
INSERT INTO sys_roles_menus VALUES (73, 1);
INSERT INTO sys_roles_menus VALUES (74, 1);
INSERT INTO sys_roles_menus VALUES (75, 1);
INSERT INTO sys_roles_menus VALUES (77, 1);
INSERT INTO sys_roles_menus VALUES (78, 1);
INSERT INTO sys_roles_menus VALUES (79, 1);
INSERT INTO sys_roles_menus VALUES (80, 1);
INSERT INTO sys_roles_menus VALUES (82, 1);
INSERT INTO sys_roles_menus VALUES (83, 1);
INSERT INTO sys_roles_menus VALUES (90, 1);
INSERT INTO sys_roles_menus VALUES (92, 1);
INSERT INTO sys_roles_menus VALUES (93, 1);
INSERT INTO sys_roles_menus VALUES (94, 1);
INSERT INTO sys_roles_menus VALUES (97, 1);
INSERT INTO sys_roles_menus VALUES (98, 1);
INSERT INTO sys_roles_menus VALUES (102, 1);
INSERT INTO sys_roles_menus VALUES (103, 1);
INSERT INTO sys_roles_menus VALUES (104, 1);
INSERT INTO sys_roles_menus VALUES (105, 1);
INSERT INTO sys_roles_menus VALUES (106, 1);
INSERT INTO sys_roles_menus VALUES (107, 1);
INSERT INTO sys_roles_menus VALUES (108, 1);
INSERT INTO sys_roles_menus VALUES (109, 1);
INSERT INTO sys_roles_menus VALUES (110, 1);
INSERT INTO sys_roles_menus VALUES (111, 1);
INSERT INTO sys_roles_menus VALUES (112, 1);
INSERT INTO sys_roles_menus VALUES (113, 1);
INSERT INTO sys_roles_menus VALUES (114, 1);
INSERT INTO sys_roles_menus VALUES (116, 1);
INSERT INTO sys_roles_menus VALUES (120, 1);
INSERT INTO sys_roles_menus VALUES (1, 2);
INSERT INTO sys_roles_menus VALUES (2, 2);
INSERT INTO sys_roles_menus VALUES (6, 2);
INSERT INTO sys_roles_menus VALUES (7, 2);
INSERT INTO sys_roles_menus VALUES (9, 2);
INSERT INTO sys_roles_menus VALUES (10, 2);
INSERT INTO sys_roles_menus VALUES (11, 2);
INSERT INTO sys_roles_menus VALUES (14, 2);
INSERT INTO sys_roles_menus VALUES (15, 2);
INSERT INTO sys_roles_menus VALUES (19, 2);
INSERT INTO sys_roles_menus VALUES (21, 2);
INSERT INTO sys_roles_menus VALUES (22, 2);
INSERT INTO sys_roles_menus VALUES (23, 2);
INSERT INTO sys_roles_menus VALUES (24, 2);
INSERT INTO sys_roles_menus VALUES (27, 2);
INSERT INTO sys_roles_menus VALUES (30, 2);
INSERT INTO sys_roles_menus VALUES (32, 2);
INSERT INTO sys_roles_menus VALUES (33, 2);
INSERT INTO sys_roles_menus VALUES (34, 2);
INSERT INTO sys_roles_menus VALUES (36, 2);
INSERT INTO sys_roles_menus VALUES (80, 2);
INSERT INTO sys_roles_menus VALUES (82, 2);
INSERT INTO sys_roles_menus VALUES (83, 2);
INSERT INTO sys_roles_menus VALUES (116, 2);
commit;
