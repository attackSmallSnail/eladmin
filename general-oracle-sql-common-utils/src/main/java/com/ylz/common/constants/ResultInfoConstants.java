package com.ylz.common.constants;

public class ResultInfoConstants {

    public static final String NULL_DTO = "参数为空";
    public static final String NULL_PARAMS = "参数错误";
    public static final String NULL_FILE = "请选择文件";
    public static final String NULL_LOCK_STACT = "禁用状态为空";
    public static final String LOCK_STACT_NO_MATCH = "禁用状态是不是一位数";
    public static final String LOCK_STACT = "账户被锁定";
    public static final String NULL_USERID = "用户ID为空";
    public static final String NULL_DATE_END = "截止日期为空";
    public static final String NULL_DATE_START = "起始日期为空";
    public static final String NULL_DATA_RANGENULL = "类型选择为空";
    public static final String NULL_REPORT_NAME = "报表名称为空";
    public static final String NULL_ORGID = "机构类型为空";
    public static final String NULL_USERNAME= "用户名为空";
    public static final String NULL_TYPE = "TYPE类型错误";
    public static final String NULL_PASSWORD = "密码为空";
    public static final String ERROR_LOGIN = "账号或密码错误";
    public static final String ERROR_FILE_XLS = "仅支持.xls后缀";
    public static final String ERROR_SQL = "业务运行异常";
    public static final String FAIL_UPDATE_SQL = "修改信息失败";
    public static final String FAIL_INSERT_SQL = "新增信息失败";
    public static final String FAIL_INSERT_SQL_DATA_REP = "新增信息失败,数据重复";
    public static final String SUCCESS_UPDATE_SQL = "修改信息成功";
    public static final String SUCCESS_INSERT_SQL = "信息新增成功";
    public static final String FAIL_UPDATE_PERSON_PHOTO_SQL = "保存人员图片信息失败";
    public static final String NULL_DATA = "{}";
    public static final String NULL_DEPT_ID = "无科室ID(DEPTID)";
    public static final String NULL_DATA_START = "开始日期不能为空";
    public static final String ERROR_DATA_START = "开始日期格式错误";
    public static final String ERROR_DATA_START_YYYY_MM = "开始日期格式错误(YYYY-MM)";
    public static final String NULL_DATA_END = "结束日期不能为空";
    public static final String ERROR_DATA_END = "结束日期格式错误";
    public static final String ERROR_DATA_END_YYYY_MM = "结束日期格式错误(YYYY-MM)";
    public static final String NULL_WECHAT_ID = "微信号为空";
    public static final String FAIL_IMPORT_DATA = "导入失败";
    public static final String SUCCESS_IMPORT_DATA = "导入成功";
    public static final String SUCCESS_EXPORT_DATA = "导出成功";
    public static final int PAGE_VIEW = 1;
    public static final int ROWS_VIEW = 10;
    public static final String FAIL_DELETE_SQL="删除信息失败";
    public static final String SUCCESS_DELETE_SQL = "删除信息成功";
    public static final String FAIL_ADD_MSG_PUSH_USERID = "该用户已定制指标";
    public static final String EXCEPTION_DURING_PASSWORD_EDIT="密码修改出现异常";
    public static final String  RESCODE_NOAUTH="无操作权限";//无操作权限
    public static final String SUCCESS_DELETE_USER = "删除用户成功";
    public static final String FAIL_DELETE_USER = "删除用户失败";
    public static final String NO_THIS_USER="账号可用";
    public static final String HAS_THIS_USER="账号重复";

    public static final String NOT_RELATE_INDEXES="当前关联的不是该操作指标文件，请刷新页面";
    public static final String NOT_DATA_INDEXS_EQUIPEMENT="当前条件没有数据可展示";
    public static final String HAVE_INVASIVE_SQL="存在侵入式sql";
    public static final String HAS_SAME_REPORT="自定义报表存在重复";
    public static final String NOT_FOUND_FROM_DB="数据库中不存在查询数据";
    public static final String SAME_PASSWD="密码与原密码相同";



    public static  final  String SAULT_TOKEN_COMPANY="ylz";

    // 指标统计时间跨度
    public static final Integer YEAR_RANGE = 6;
}
