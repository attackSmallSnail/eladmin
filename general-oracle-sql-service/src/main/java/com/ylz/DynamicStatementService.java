package com.ylz;


import com.ylz.common.pojo.Column;
import com.ylz.common.pojo.HeadColumnVo;
import com.ylz.common.pojo.OverviewObjectVo;
import com.ylz.common.pojo.TableObjectReq;
import com.ylz.req.CommonSqlReq;
import com.ylz.req.FamilyDoctorInfoReq;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Nomark
 * @CreateDate: 2019/9/1 15:28
 * @Version: v1.0
 */
public interface DynamicStatementService {
    String getSingleStringValue(String sql);

    Integer queryInterfaceIfExists(CommonSqlReq commonReq);

    boolean insertInterface(CommonSqlReq commonReq);

    boolean updateInterface(CommonSqlReq commonReq);

    Map commonOverview(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields);
    /**
     * @Description 查询饼图
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map queryCircleDate(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields);
    /**
     * @Description 单值map查询
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map getSingleObject(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields);
    /**
     * @Description 查询折线图 + 表格接口
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map commonDataQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos);
    /**
     * @Description  单独 查询柱状图和折线图
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map commonPolyAndCol(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos);

    /**
     * @Description 单独 查询柱状图和折线图 有加自定义的 group by 和 order by 列
     * @Author lcw
     * @Date 2020/4/29
     * @Param
     * @Return
     */
    Map commonPolyAndCol(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos, List<Column> orderByFields, List<Column> groupByFields);
    /**
     * @Description 返回单个表格
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map commonTableData(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos);
    /**
     * @Description 查询折线图 + 表格接口 增加自定义聚合函数
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map commonDataQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos);
    /**
     * @Description 查询折线图 + 表格接口 折线图是否按照年份查询
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map commonDataQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos, boolean isTimePoint);

    Map privatelyOrg(FamilyDoctorInfoReq param, Map<String,OverviewObjectVo> queryMap, List<TableObjectReq> tableObjectReqs, List<Column> columns, List<HeadColumnVo> headColumns);
    /**
     * @Description 单独返回一个表格
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map commonCustomQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos);
    /**
     * @Description 单独返回一个表格 增加排序参数
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map commonCustomQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos,boolean isOrderByDesc);
    /**
     * @Description 查询表格合计列,聚合函数参数 和 排序参数 都放置为空
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    List commonCustomQueryTotal(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos,boolean isOrderByDesc);

    /**
     * @Description 默认是 按照区域分组
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    Map commonCustomQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos);
    /**
     * @Description 详情列表接口，用于各项数据以列表、分组方法返回数据 单个表格
     * @Author lcw
     * @Date 2019/12/1
     * @Param
     * @Return
     */
    List<Map> constDetailListQuery(FamilyDoctorInfoReq param, Map<String,OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields);

}
