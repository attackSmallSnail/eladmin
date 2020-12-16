package com.ylz.common.constants;

public class ResultConstants {
    //公共
    public static final String NULL_PARAM="参数为空";
    public static final String NULL_PARAM_NOENOGH="参数不全";
    public static final String NULL_RESULT="返回结果为空";
    public static final String SUCCESS_DONE="操作成功";
    public static final String FAILURE_DONE="操作失败";
    public static final String FAILURE_FOR_EXCEPTION="操作异常，请联系管理员";
    public static final String REPEAT_DATA="重复插入";
    public static final String UPDATEFRE_AGAIN_INDEX_FILE="再分配的指标文件不可修改！";
    //用户登录
    public static final String LOCK_STACT = "账户被锁定";
    public static final String NULL_USERNAME= "用户名为空";
    public static final String NULL_PASSWORD = "密码为空";
    public static final String ERROR_LOGIN = "账号或密码错误";
    public static final String AREA_NOT="用户区域代码为空";
    public static final String NOT_FOUND_USER="用户信息获取失败，请重新登录";
    public static final String OUT_TIME_lOGIN="登录失效，请重新登录";
    //机构设置
    /*public static final String SAME_ORG="机构同名";
    public static final String SAME_DEPT="部门同名";*/
    //用户设置
    public static final String USER_NULL_AREA = "该账号行政区域权限为空";
    public static final String IS_NULL_PASSWORD = "密码不能传空";
    public static final String USER_HAS_DISK = "用户当前仍有未完成任务";
    public static final String USERNAME_NO_ONLY = "数据库已有该用户名";
    public static final String NOT_USERNAME_NO_ONLY = "没有重名可以使用";
    public static final String PASSWORD_UPDATE_SUCCESS = "用户密码修改成功";
    public static final String USER_INDEXT= "用户有分配任务";
    public static final String USER_INDEXT_NULL = "用户没有分配任务";
    //医改综合工作台
    public static final String INDEXS_NAME="指标文件名称为空";
    public static final String INDEX_ID="指标id为空";
    public static final String INDEXS_NAME_TWICE="指标文件名存在";
    public static final String INDEXS_NAME_DUPLICATED="该指标文件名已存在，不能重复使用\n";
    public static final String INDEX_NAME_TWICE="指标名存在";
    public static final String ERROR_SQL="查询出错";
    public static final String INDEXS_ID="指标文件ID不能为空";
    public static final String DELETE_INDEXS_FAIL="删除指标文件失败";
    public static final String CUSTOM_INDEX_PROP_NULL="自定义指标属性为空";
    public static final String CUSTOM_INDEX_KEYWORD_NULL="自定义指标关键字为空";
    public static final String CATALOG_ID="分类ID不能为空";
    public static final String INDEX_FILE_NULL="操作失败，请检查指标附件ID是否正确";
    public static final String DELETE_INDEX_FILE_ERROR="删除指标附件失败";
    public static final String INDEX_FILE_ERROR="指标附件不存在";
    public static final String INDEX_IS_NOT_EXIST="指标不存在";
    public static final String INDEX_UPDATE_FRE="政策指标更新频率不能为空";
    //消息中心
    public static final String MESSAGE_BASE_URL="http://www.3mylz.com";
    public static final String MESSAGE_CONTENT="您收到{ORGNAME}{POST}{NAME}发来的一份{INDEXSFILE}{TASKNAME}任务。";
    public static final String MESSAGE_URGE="{ORGNAME}{POST}{NAME}催您完成{INDEXSFILE}{TASKNAME}任务，快去完成吧~";
    //医改综合工作台--责任分配
    public static final String PLEASE_RETURN_DUTY_PERSON="责任人列表,指标文件id或者指标id为空";
    public static final String HAVEN_INSERT_DUTY="条数据已经插入";
    public static final String NEVER_INSERT_DUTY="条数据插入失败";
    public static final String DOWNLOAD_FALURE="下载失败";
    public static final String DOWNLOAD_SUCCEED="下载成功";
    public static final String SOURCE_FILE_INSERT_ERROR="指标政策文件上传异常";
    public static final String INDEX_FILE_INSERT_ERROR="指标附件上传异常";
    public static final String ALLOT_PERSON_HASDESSMISION="已有该指标的分配任务，请确认后再进行分配";
    public static final String HAS_NOT_ALLOT_INDEX="指标文件存在未分配指标，请确认后再到下一步";

    //医改综合工作台--任务发布
    public static final String INDEXSID_FAIL="操作失败，请检查指标文件ID是否正确";
    public static final String ISALLOCED_FAIL="操作失败，请检查指标文件是否已分配";

    //医改综合工作台--数据处理
    public static final String REPEAT_INSERT = "不允许重复上报数据";
    public static final String CAN_GET_REALVALUE = "数据报错，无法自动汇总";
    public static final String NEVER_ALLOT_YOUR = "该指标文件在当前指标类型下，没有属于您的指标任务";
    public static final String NOT_REPORT_TIME = "存在未填写的填报时间";
    public static final String FORMAT_ERROR_REPORT_TIME = "填报时间填写格式不正确";
    public static final String FORMULA_FAIL = "从下级获取指标数据错误，请检查指标公式是否正式。";
    public static final String HAVEN_UPLOAD_CANNOT_DEAL = "已上报无法进行数据填报";
    public static final String EXIST_ERROR_DATE="存在不正确的填报时间请检查，填报时间是否正确";





    public static final String  INDEX_UNABSORBED= "未分配";
    public static final String  INDEX_UN_UPLOAD= "未上传";
    public static final String  INDEX_FILE_UNABSORBED= "该指标文件未分配给其他用户";
    public static final String  CONDITION_RANGE="range";//多月 多季度 多年
    public static final String  CONDITION_CURRENT="current";//单月 单季度 单年

    public static final String  INDEX_BACK_RETURN= "已退回";

    public static final String  WAIT_UPLOAD_INDEX="2";//等待上传的指标数据

    public static final String  BACK_UPLOAD_INDEX="3";//等待上传的指标数据



    public static final String  ASSIGNED_TO_ME_INDEX="1";//等待上传的指标数据


    //权限管理
    public static final String NULL_REQUIREPARAM = "未正确提供参数";
    public static final String YES_ASSIGNED = "1"; //已分配
    public static final String NO_ASSIGNED = "0"; //未分配

    //校验唯一性
    public static final String NAME_ALREADY_EXIST = "该名称已被使用";
    public static final String DELETE_STATUS_SAVE = "0"; //保留
    public static final String DELETE_STATUS_REMOVE = "1"; //逻辑删除

    //获取默认指标文件属性的key值
    public static final String IINDEXS_PROPERTY_KEY = "40f953f97bd341439c66d26da815f20f";

    public static final String SAULT_TOKEN_KEY="ylz";
    public static final String USER_NO_EXIT="用户不存在";
    public static final String APP_NO_EXIT="应用不存在";
    //激活
    public static final int USER_STATE_ACTIVATE=0;
    //冻结
    public static final int USER_STATE_FREEZE=1;
    //填报时限默认值
    public static final String DEFAULT_MONTH_NO="15";
    //到时提醒默认值
    public static final String DEFAULT_ADVANCE_DAYS="3";

    public static final Integer MAX_COLUMN=75;
}
