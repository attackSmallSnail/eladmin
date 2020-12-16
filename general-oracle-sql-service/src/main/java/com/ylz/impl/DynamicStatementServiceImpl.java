package com.ylz.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ylz.DynamicStatementService;
import com.ylz.common.enums.ColumnEnum;
import com.ylz.common.enums.LastParamEnum;
import com.ylz.common.objutils.ObjectsUtil;
import com.ylz.common.pojo.Column;
import com.ylz.common.pojo.HeadColumnVo;
import com.ylz.common.pojo.OverviewObjectVo;
import com.ylz.common.pojo.TableObjectReq;
import com.ylz.common.zhutil.ChartUtil;
import com.ylz.common.zhutil.ZhBeanUtils;
import com.ylz.mapper.DynamicStatementMapper;
import com.ylz.mapper.WarningIndicatorsMapper;
import com.ylz.entity.WarningIndicatorsDto;
import com.ylz.entity.WarningIntervalDto;
import com.ylz.req.CommonSqlReq;
import com.ylz.req.FamilyDoctorInfoReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @Description: 动态语句
 * @Author: Nomark
 * @CreateDate: 2019/9/1 15:29
 * @Version: v1.0
 */
@Service
@Slf4j
public class DynamicStatementServiceImpl implements DynamicStatementService {

    @Resource
    DynamicStatementMapper dynamicStatementMapper;
    @Resource
    WarningIndicatorsMapper warningIndicatorsMapper;

    @Override
//    @TargetDataSource("slave1")
    public String getSingleStringValue(String sql) {
        return dynamicStatementMapper.getSingleStringValue(sql);
    }

    @Override
//    @TargetDataSource("slave1")
    public Integer queryInterfaceIfExists(CommonSqlReq commonReq) {
        return dynamicStatementMapper.queryInterfaceIfExists(commonReq);
    }

    @Override
//    @TargetDataSource("slave1")
    public boolean insertInterface(CommonSqlReq commonReq) {
        return dynamicStatementMapper.insertInterface(commonReq);
    }

    @Override
//    @TargetDataSource("slave1")
    public boolean updateInterface(CommonSqlReq commonReq) {
        return dynamicStatementMapper.updateInterface(commonReq);
    }

    /*
     * @Description: 公共概览接口
     * @Author: Nomark
     * @Date: 2019/9/9 0:06
     * @Param: [param, resultMap, tables, fields]
     * @Return: java.util.Map
     */
    @Override
    public Map commonOverview(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields) {
        //生成查询语句
        StringBuffer sql = generateQuerySQL(param, resultMap, tables, fields, new ArrayList(), new ArrayList(),false, false);

        Map map = dynamicStatementMapper.getSingleObject(sql.toString());
        //批量查询预警消息
        List<String> paramList = Lists.newArrayList();
        Map<String, WarningIndicatorsDto> warningIndicatorsDtoMaps = Maps.newHashMap();
        //如果为1 的时候就需要预警
        if ("1".equals(param.getNeedWaringFlag())){
            warningIndicatorsDtoMaps = listWarningIndicatorsDto(param, resultMap,paramList);
        }
        //批量查询预警消息规则
        Map<String,List<WarningIntervalDto>> waringMaps = getWarningIntervalByWarningIds(paramList);

        //填充数据
        for(String key:resultMap.keySet()) {
            if(!ObjectUtils.isEmpty(map) && map.containsKey(key)) {
                resultMap.get(key).setData(map.get(key));
                //如果是同比字段自动设值到指定的同比字段
                if (resultMap.get(key).isAnFlag()){
                    String anKey = key.substring(0, key.length() - 2);
                    resultMap.get(anKey).setAn(Double.valueOf(map.get(key).toString()));
                }
                //如果有分子或者分母,需要把数值提取出来
                if (ObjectsUtil.isNotEmpty(resultMap.get(key).getMolecularStr())){
                    String molecularStr = resultMap.get(key).getMolecularStr();
                    String denominatorStr = resultMap.get(key).getDenominatorStr();
                    String molecularUnit = ObjectsUtil.isNotEmpty(resultMap.get(molecularStr)) ? resultMap.get(molecularStr).getUnit() : "";
                    String denominatorUnit = ObjectsUtil.isNotEmpty(resultMap.get(denominatorStr)) ? resultMap.get(denominatorStr).getUnit() : "";

                    StringBuffer composition = new StringBuffer("\n[");
                    if (map.containsKey(resultMap.get(key).getMolecularStr())){
                        composition.append("分子数值:").append(map.get(molecularStr)).append("(").append(molecularUnit).append(")");
                    } else {
                        composition.append("分子数值:0").append("(").append(molecularUnit).append(")");
                    }
                    if (map.containsKey(resultMap.get(key).getDenominatorStr())){
                        composition.append("；分母数值:").append(map.get(denominatorStr)).append("(").append(denominatorUnit).append(")");
                    } else {
                        composition.append("；分母数值:0").append("(").append(denominatorUnit).append(")");
                    }
                    composition.append("]");
                    resultMap.get(key).appendCaliber(composition.toString());
                }

                //查找指标预警
                if (!ObjectUtils.isEmpty(warningIndicatorsDtoMaps)){
                    WarningIndicatorsDto warningIndicatorsDto = warningIndicatorsDtoMaps.get(key);
                    if (!ObjectUtils.isEmpty(warningIndicatorsDto)) {
                        double data = Double.parseDouble(map.get(key).toString());
                        OverviewObjectVo overviewObjectVo = resultMap.get(key);
                        overviewObjectVo.setStatus("1");
                        if (warningIndicatorsDto.getIntervalMin() != null && data < warningIndicatorsDto.getIntervalMin()) {
                            overviewObjectVo.setStatus("2");
                        }
                        if (warningIndicatorsDto.getIntervalMax() != null && data > warningIndicatorsDto.getIntervalMax()) {
                            overviewObjectVo.setStatus("2");
                        }
                        List<WarningIntervalDto> warningIntervalDtoList = ObjectUtils.isEmpty(waringMaps.get(warningIndicatorsDto.getWarningId())) ? Lists.newArrayList() : waringMaps.get(warningIndicatorsDto.getWarningId());
                        for (WarningIntervalDto warningIntervalDto : warningIntervalDtoList) {
                            boolean eq = "=".equals(warningIntervalDto.getWarningRule()) && data == warningIntervalDto.getWarningNumber();
                            boolean not = eq || ("!=".equals(warningIntervalDto.getWarningRule()) && data != warningIntervalDto.getWarningNumber());
                            boolean lt = not || ("<".equals(warningIntervalDto.getWarningRule()) && data < warningIntervalDto.getWarningNumber());
                            boolean gt = lt || (">".equals(warningIntervalDto.getWarningRule()) && data > warningIntervalDto.getWarningNumber());
                            boolean lteq = gt || ("<=".equals(warningIntervalDto.getWarningRule()) && data <= warningIntervalDto.getWarningNumber());
                            boolean gteq = lteq || (">=".equals(warningIntervalDto.getWarningRule()) && data >= warningIntervalDto.getWarningNumber());
                            if (gteq) {
                                overviewObjectVo.setStatus("2");
                                break;
                            }
                        }
                    }
                }

            } else {
                resultMap.get(key).setData(0);
                if (resultMap.get(key).isAnFlag()){
                    String anKey = key.substring(0, key.length() - 2);
                    resultMap.get(anKey).setAn(0d);
                }
            }
        }
        return resultMap;
    }

    //新的预警方法，但是与上面不兼容，数值型通过对各个单机构进行相加，占比型进行平均
    //assessMapper.queryOrg(param);
    private void setResultStatus(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields,Map map){
        int num = param.getOrgIdList()!=null ? param.getOrgIdList().size() : 0;
        WarningIndicatorsDto warningParam = new WarningIndicatorsDto();
        if (param.getOrgIdList()!=null && param.getOrgIdList().size()==1) {
            warningParam.setIsSingleOrg("1");
        } else {
            warningParam.setIsSingleOrg("0");
        }
        long lo = DateUtil.betweenMonth(DateUtil.parse(param.getDateStart()),DateUtil.parse(param.getDateEnd()),true);
        lo += 1;
        warningParam.setLonMonth((int) lo, param.getDateType());
        warningParam.setAreaId(param.getCounty());
        warningParam.setOrgIdList(param.getOrgIdList());
        warningParam.setOrgGradeList(param.getOrgGradeList());
        warningParam.setIndicatorsKeyList(new ArrayList<String>(resultMap.keySet()));
        List<WarningIndicatorsDto> list = warningIndicatorsMapper.getWarningByIndicatorsName2(warningParam);
        Map<String, WarningIndicatorsDto> warningIndicatorsDtoMaps = new HashMap();
        list.forEach(warningIndicatorsDto -> {
            warningIndicatorsDtoMaps.put(warningIndicatorsDto.getIndicatorsKey(),warningIndicatorsDto);
        });
        List<WarningIntervalDto> warningIntervalDtos =  warningIndicatorsMapper.getWarningIntervalByWarnings(list);
        Map<String, List<WarningIntervalDto>> waringMaps = new HashMap<>();
        warningIntervalDtos.forEach(warningIntervalDto -> {
            if (waringMaps.containsKey(warningIntervalDto.getIndicatorsKey())) {
                waringMaps.get(warningIntervalDto.getIndicatorsKey()).add(warningIntervalDto);
            } else {
                waringMaps.put(warningIntervalDto.getIndicatorsKey(),new ArrayList<WarningIntervalDto>(){{
                    add(warningIntervalDto);
                }});
            }
        });
        List<String> rates = new ArrayList<String>(){{
            add("avgLos");add("outAverageFee");add("inAverageFee");
            add("drugProportion");add("inDrugProportion");add("outDrugProportion");
        }};
        for(String key:resultMap.keySet()) {
            if(!ObjectUtils.isEmpty(map) && map.containsKey(key)) {
                resultMap.get(key).setData(map.get(key));
                //如果是同比字段自动设值到指定的同比字段
                if (resultMap.get(key).isAnFlag()){
                    String anKey = key.substring(0, key.length() - 2);
                    resultMap.get(anKey).setAn(Double.valueOf(map.get(key).toString()));
                }

                //查找指标预警
                if (!ObjectUtils.isEmpty(warningIndicatorsDtoMaps)){
                    WarningIndicatorsDto warningIndicatorsDto = warningIndicatorsDtoMaps.get(key);
                    if (!ObjectUtils.isEmpty(warningIndicatorsDto)) {
                        double data = Double.parseDouble(map.get(key).toString());
                        OverviewObjectVo overviewObjectVo = resultMap.get(key);
                        overviewObjectVo.setStatus("1");
                        List<WarningIntervalDto> warningIntervalDtoList = ObjectUtils.isEmpty(waringMaps.get(key)) ? Lists.newArrayList() : waringMaps.get(key);
                        for (WarningIntervalDto warningIntervalDto : warningIntervalDtoList) {
                            if (!warningIntervalDto.getIndicatorsKey().endsWith("Rate") && rates.indexOf(warningIntervalDto.getIndicatorsKey())!=-1) {
                                warningIntervalDto.setWarningNumber(warningIntervalDto.getWarningNumber() * num);
                            }
                            boolean eq = "=".equals(warningIntervalDto.getWarningRule()) && data == warningIntervalDto.getWarningNumber();
                            boolean not = eq || ("!=".equals(warningIntervalDto.getWarningRule()) && data != warningIntervalDto.getWarningNumber());
                            boolean lt = not || ("<".equals(warningIntervalDto.getWarningRule()) && data < warningIntervalDto.getWarningNumber());
                            boolean gt = lt || (">".equals(warningIntervalDto.getWarningRule()) && data > warningIntervalDto.getWarningNumber());
                            boolean lteq = gt || ("<=".equals(warningIntervalDto.getWarningRule()) && data <= warningIntervalDto.getWarningNumber());
                            boolean gteq = lteq || (">=".equals(warningIntervalDto.getWarningRule()) && data >= warningIntervalDto.getWarningNumber());
                            if (gteq) {
                                overviewObjectVo.setStatus("2");
                                break;
                            }
                        }
                    }
                }

            } else {
                resultMap.get(key).setData(0);
                if (resultMap.get(key).isAnFlag()){
                    String anKey = key.substring(0, key.length() - 2);
                    resultMap.get(anKey).setAn(0d);
                }
            }
        }
    }

    /*
    查询饼图
     */
    @Override
    public Map queryCircleDate(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields) {
        //生成查询语句
        StringBuffer sql = generateQuerySQL(param, resultMap, tables, fields, new ArrayList(), new ArrayList(),false, false);

        Map map = dynamicStatementMapper.getSingleObject(sql.toString());

        return ChartUtil.pieChart(map, resultMap);
    }

    /**
     * 单值map查询
     */
    @Override
    public Map getSingleObject(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields) {
        //生成查询语句
        StringBuffer sql = generateQuerySQL(param, resultMap, tables, fields, new ArrayList(), new ArrayList(),false, false);

        Map map = dynamicStatementMapper.getSingleObject(sql.toString());
        //填充数据
        for(String key:resultMap.keySet()) {
            if(!ObjectUtils.isEmpty(map) && map.containsKey(key)) {
                resultMap.get(key).setData(map.get(key));
                //如果是同比字段自动设值到指定的同比字段
                if (resultMap.get(key).isAnFlag()){
                    String anKey = key.substring(0, key.length() - 2);
                    resultMap.get(anKey).setAn(Double.valueOf(map.get(key).toString()));
                }
            } else {
                resultMap.get(key).setData(0);
                if (resultMap.get(key).isAnFlag()){
                    String anKey = key.substring(0, key.length() - 2);
                    resultMap.get(anKey).setAn(0d);
                }
            }
        }
        return resultMap;
    }

    /*
     * @Description: 公共查询数据接口(没有传orderByFields, groupByFields)
     * @Author: Nomark
     * @Date: 2019/9/16 16:22
     * @Param: [param, resultMap, tables, fields, headColumnVos]
     * @Return: java.util.Map
     */
    @Override
    public Map commonDataQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos) {
        return commonDataQuery(param, resultMap, tables, fields, headColumnVos, false);
    }

    /*
     * @Description: 公共查询数据接口(没有传orderByFields, groupByFields)
     * @Author: Nomark
     * @Date: 2019/9/16 16:22
     * @Param: [param, resultMap, tables, fields, headColumnVos, isTimePoint]
     * @Return: java.util.Map
     */

    @Override
    public Map commonDataQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos, boolean isTimePoint) {
        Map<String,List> byList = getOrderAndGroup(resultMap);
        List orderByFields = byList.get("orderByFields");
        List groupByFields = byList.get("groupByFields");
        return commonDataQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos, isTimePoint);
    }

    /**
     * @Description  封装group by 和order by表达式
     * @Author lcw
     * @Date 2019/9/26
     * @Param
     * @Return
     */
    private Map<String,List> getOrderAndGroup(Map<String, OverviewObjectVo> resultMap){
        List orderByFields = new ArrayList();
        List groupByFields = new ArrayList();
        for(String key : resultMap.keySet()) {
            OverviewObjectVo vo = resultMap.get(key);
            if(vo.isGroupBy()) {
                Column c = new Column(vo.getFormula(), key, vo.getDescribe());
                groupByFields.add(c);
            }
            if(vo.isOrderBy()) {
                StringBuffer formula =  new StringBuffer();
                if(vo.isCustomFormula() || vo.getDenominator() == null || "".equals(vo.getDenominator())) {
                    formula.append(vo.getFormula());
                } else {
                    formula.append("decode(").append(vo.getDenominator()).append(", 0, 0, ").append(vo.getFormula()).append(")");
                }
                Column c = new Column(formula.toString(), key, vo.getDescribe());
                orderByFields.add(c);
            }
        }
        Map<String,List> result = Maps.newHashMap();
        result.put("orderByFields",orderByFields);
        result.put("groupByFields",groupByFields);
        return result;
    }

    /*
     * @Description: 公共查询数据接口  查询柱状图和折线图
     * @Author: Nomark
     * @Date: 2019/9/16 16:22
     * @Param: [param, resultMap, tables, fields, headColumnVos]
     * @Return: java.util.Map
     */
    @Override
    public Map commonPolyAndCol(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos){
        Map<String,List> byList = getOrderAndGroup(resultMap);
        List orderByFields = byList.get("orderByFields");
        List groupByFields = byList.get("groupByFields");
        Map result = new HashMap(2);
        //图形
        List<Map> dateGroupList = dateGroupQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,false);
        result.put("chartList", ChartUtil.dateGroupChart(dateGroupList, resultMap));
        return result;
    }

    @Override
    public Map commonPolyAndCol(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos, List<Column> orderByFields, List<Column> groupByFields) {
        Map<String,List> byList = getOrderAndGroup(resultMap);
        Map result = new HashMap(2);
        //图形
        List<Map> dateGroupList = dateGroupQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,false);
        result.put("chartList", ChartUtil.dateGroupChart(dateGroupList, resultMap));
        return result;
    }

    /**
     * @Description 返回单个表格
     * @Author lcw
     * @Date 2019/9/26
     * @Param
     * @Return
     */
    @Override
    public Map commonTableData(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos){
        Map<String,List> byList = getOrderAndGroup(resultMap);
        List orderByFields = byList.get("orderByFields");
        List groupByFields = byList.get("groupByFields");
        Map result = new HashMap(2);
        //生成数据列
        Map dataMap = new HashMap<>();
        List<Map> detailList = detailListQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,false);
        dataMap.put("list", ChartUtil.detailChart(detailList, resultMap));
        dataMap.put("columns", headColumnVos);  //表头
        //生成合计列
        dataMap.put("totalRecord", Lists.newArrayList());
        result.put("tableData", dataMap);
        return result;
    }


    /*
     * @Description: 公共查询数据接口
     * @Author: Nomark
     * @Date: 2019/9/16 16:23
     * @Param: [param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos]
     * @Return: java.util.Map
     */
    @Override
    public Map commonDataQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos) {
        return commonDataQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos, false);
    }
    /**
     * @Description 单独返回一个表格
     * @Author lcw
     * @Date 2019/9/24
     * @Param
     * @Return
     */
    @Override
    public Map commonCustomQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos){
        return commonCustomQuery(param,resultMap,tables,fields,orderByFields,groupByFields,headColumnVos,true);
    }
    /**
     * @Description 单独返回一个表格,没有合计列
     * @Author lcw
     * @Date 2019/9/24
     * @Param
     * @Return
     */
    @Override
    public Map commonCustomQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos,boolean isOrderByDesc){
        //生成数据列
        Map dataMap = new HashMap<>();
        //表格列
        StringBuffer groupAreaSQL = generateQuerySQL(param, resultMap, tables, fields, orderByFields, groupByFields,false, false, isOrderByDesc);
        List<Map> list2 = dynamicStatementMapper.commonQueryList(groupAreaSQL.toString());
        //生成数据列
        dataMap.put("list", ChartUtil.detailChart(list2, resultMap));
        dataMap.put("columns", headColumnVos);  //表头
        dataMap.put("totalRecord", Lists.newArrayList());
        //增加返回格式
        Map result = Maps.newHashMap();
        result.put("tableData", dataMap);
        return result;
    }

    @Override
    public List commonCustomQueryTotal(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos,boolean isOrderByDesc){
        //生成合计列
        Map countMap = singleValueQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,false);
        List totalRecordList = new ArrayList();
        totalRecordList.add(ChartUtil.totalChart(countMap, resultMap, param));
        return totalRecordList;
    }

    @Override
    public Map commonCustomQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos){
        List<Column> orderByFields = Lists.newArrayList();
        List<Column> groupByFields = Lists.newArrayList();
        //生成数据列
        Map dataMap = new HashMap<>();
        //表格列
        StringBuffer groupAreaSQL = generateQuerySQL(param, resultMap, tables, fields, orderByFields, groupByFields,true, false, false);
        List<Map> list2 = dynamicStatementMapper.commonQueryList(groupAreaSQL.toString());
        //生成数据列
        dataMap.put("list", ChartUtil.detailChart(list2, resultMap));
        dataMap.put("columns", headColumnVos);  //表头
        //增加返回格式
        dataMap.put("totalRecord",Lists.newArrayList());
        Map result = Maps.newHashMap();
        result.put("tableData", dataMap);
        return result;
    }

    /*
     * @Description: 公共查询数据接口
     * @Author: Nomark
     * @Date: 2019/9/16 16:23
     * @Param: [param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos, isTimePoint]
     * @Return: java.util.Map
     */
    public Map commonDataQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos, boolean isTimePoint) {
        Map returnMap = new HashMap(2);

        //图形
        List<Map> dateGroupList = dateGroupQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,isTimePoint);
        returnMap.put("chartList", ChartUtil.dateGroupChart(dateGroupList, resultMap));

        //生成数据列
        Map dataMap = new HashMap<>();
        List<Map> detailList = detailListQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,isTimePoint);
        dataMap.put("list", ChartUtil.detailChart(detailList, resultMap));
        dataMap.put("columns", headColumnVos);  //表头
        returnMap.put("tableData", dataMap);

        //生成合计列
        Map countMap = singleValueQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,isTimePoint);
        List totalRecordList = new ArrayList();

        totalRecordList.add(ChartUtil.totalChart(countMap, resultMap, param));
        dataMap.put("totalRecord", totalRecordList);

        return returnMap;
    }
    /**
     *
     * @Description: 5个图标的数据查询
     * @param  param 请求参数
     * @param  resultMap 返回数据
     * @param  tables 查询的表
     * @param  fields 查询的字段
     * @param  orderByFields 排序字段
     * @param  groupByFields 分组字段
     * @param  headColumnVos 表格头部信息
     * @param  isTimePoint 是否按照时间聚合
     * @author BushRo
     * @return java.util.Map
     * @date 2019-10-16 11:18
     * @version 1.0
     */

    public Map commonDataQueryFive(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos, boolean isTimePoint) {
        Map returnMap = new HashMap(2);

        //图形
        List<Map> dateGroupList = dateGroupQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,isTimePoint);
        returnMap.put("chartList", ChartUtil.dateGroupChart(dateGroupList, resultMap));

        //生成数据列
        Map dataMap = new HashMap<>();
        List<Map> detailList = detailListQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,isTimePoint);
        dataMap.put("list", ChartUtil.detailChart(detailList, resultMap));
        dataMap.put("columns", headColumnVos);  //表头
        returnMap.put("tableData", dataMap);

        //生成合计列
        Map countMap = singleValueQuery(param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos,isTimePoint);
        List totalRecordList = new ArrayList();

        totalRecordList.add(ChartUtil.totalChart(countMap, resultMap, param));
        dataMap.put("totalRecord", totalRecordList);

        return returnMap;
    }

    /*
     * @Description: 查询按日期列表数值方法,主要用于日期图形接口
     * @Author: Nomark
     * @Date: 2019/9/26 9:46
     * @Param: [param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos, isTimePoint]
     * @Return: java.util.List<java.util.Map>
     */
    private List<Map> dateGroupQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos, boolean isTimePoint) {
        //如果传入为时间点，修改开始时间和结束时间
        FamilyDoctorInfoReq groupDateParam = new FamilyDoctorInfoReq();
        if("current".equals(param.getSearchRange()) || isTimePoint) {
            groupDateParam = ZhBeanUtils.copyObject(param, FamilyDoctorInfoReq.class);
            groupDateParam.setGraphDateStart();
        } else {
            groupDateParam = param;
        }

        StringBuffer groupDateSQL = generateQuerySQL(groupDateParam, resultMap, tables, fields, new ArrayList<>(), groupByFields,false, true);
        List<Map> dateGroupList = dynamicStatementMapper.commonQueryList(groupDateSQL.toString());
        return dateGroupList;
    }

    /*
     * @Description: 详情列表接口，用于各项数据以列表、分组方法返回数据
     * @Author: Nomark
     * @Date: 2019/9/26 9:52
     * @Param: [param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos, isTimePoint]
     * @Return: java.util.List<java.util.Map>
     */
    private List<Map> detailListQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos, boolean isTimePoint) {
        //表格列
        StringBuffer groupAreaSQL = generateQuerySQL(param, resultMap, tables, fields, orderByFields, new ArrayList<>(),true, false, true);
        List<Map> detailList = dynamicStatementMapper.commonQueryList(groupAreaSQL.toString());
        return detailList;
    }

    /*
     * @Description: 详情列表接口，用于各项数据以列表、分组方法返回数据
     * @Author: Nomark
     * @Date: 2019/9/26 9:52
     * @Param: [param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos, isTimePoint]
     * @Return: java.util.List<java.util.Map>
     */
    @Override
    public List<Map> constDetailListQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields) {
        //表格列
        StringBuffer groupAreaSQL = generateQuerySQL(param, resultMap, tables, fields, orderByFields, groupByFields,false, false, true);
        List<Map> detailList = dynamicStatementMapper.commonQueryList(groupAreaSQL.toString());
        return detailList;
    }

    /*
     * @Description: 单值查询，可用于查询概览页、饼图、所有获取单值的部分
     * @Author: Nomark
     * @Date: 2019/9/26 9:58
     * @Param: [param, resultMap, tables, fields, orderByFields, groupByFields, headColumnVos, isTimePoint]
     * @Return: java.util.Map
     */
    private Map singleValueQuery(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, List<HeadColumnVo> headColumnVos, boolean isTimePoint) {
        StringBuffer sql2 = generateQuerySQL(param, resultMap, tables, fields, orderByFields, groupByFields, false, false);
        Map singleValueMap = dynamicStatementMapper.getSingleObject(sql2.toString());
        return singleValueMap;
    }

    @Override
    public Map privatelyOrg(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> queryMap, List<TableObjectReq> tables, List<Column> fields, List<HeadColumnVo> headColumnVos) {
        Map returnMap = new HashMap(2);

        //生成数据列
        Map dataMap = new HashMap<>();
        //表格列
        Map<String, OverviewObjectVo> areaQueryMap = new HashMap();
        //生成区县/机构统计列
        areaQueryMap.put("orgIds", new OverviewObjectVo<Double>("机构数量", "", "count(orgId)", true));
        StringBuffer totalGroupSQL = generateQuerySQL(param, areaQueryMap, tables, fields, new ArrayList(), new ArrayList(),true, false, false, false, true);
        List<Map> areaList = dynamicStatementMapper.commonQueryList(totalGroupSQL.toString());
        Map<String, Map> areasMap = new HashMap(1);

        for (int i = 0; i < areaList.size(); i++) {
            Map area = areaList.get(0);
            if(area.containsKey("parentId")) {
                areasMap.put(area.get("parentId").toString(), area);
            }
        }

        //机构列表
        StringBuffer groupAreaSQL = generateQuerySQL(param, queryMap, tables, fields, new ArrayList(), new ArrayList(),true, false);
        List<Map> orgList = dynamicStatementMapper.commonQueryList(groupAreaSQL.toString());

        List list = new ArrayList();
        orgList.forEach(org-> {
            Map columnMap = new HashMap();
            columnMap.put("areaId", org.get("parentId"));
            columnMap.put("areaName", org.get("parentName"));

            if(areasMap.containsKey(org.get("parentId"))) {
                if(areasMap.get("parentId").containsKey("orgIds")) {
                    columnMap.put("orgSum", areasMap.get("parentId").get("orgIds"));
                } else {
                    columnMap.put("orgSum", 0);
                }
                if(areasMap.get("parentId").containsKey("sort")) {
                    columnMap.put("orgSum", areasMap.get("parentId").get("orgIds"));
                } else {
                    columnMap.put("orgSum", 0);
                }
            }
        });

        dataMap.put("list", list);
        dataMap.put("columns", headColumnVos);  //表头

        //生成合计列
        StringBuffer sql2 = generateQuerySQL(param, queryMap, tables, fields, new ArrayList(), new ArrayList(),false, false);
        Map countMap = dynamicStatementMapper.getSingleObject(sql2.toString());
        List totalRecordList = new ArrayList();
        Map totalRecordMap = new HashMap();
        if(param.getCounty() != null) {
            totalRecordMap.put("areaId", param.getCounty());
        } else {
            totalRecordMap.put("areaId", "-");
        }
        totalRecordMap.put("areaName", "合计");
        totalRecordMap.put("sort", "-");

        for(String key:queryMap.keySet()) {
            if(totalRecordMap != null && totalRecordMap.containsKey(key)) {
                totalRecordMap.put(key, countMap.get(key));
            } else {
                totalRecordMap.put(key, "0");
            }
        }
        totalRecordList.add(totalRecordMap);
        dataMap.put("totalRecord", totalRecordList);
        returnMap.put("tableData", dataMap);

        return returnMap;
    }


    /*
     * @Description: 生成查询语句
     * @Author: Nomark
     * @Date: 2019/9/11 18:44
     * @Param: [param, resultMap, tables, fields, isGroupByArea-按area字段group, isGroupByDate, isComplementArea-是否补全地区, isComplementDate-是否补全日期]
     * @Return: java.lang.StringBuffer
     */
    public StringBuffer generateQuerySQL(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, boolean isGroupByArea, boolean isGroupByDate,  boolean isComplementArea, boolean isComplementDate, boolean isOrderByDesc) {
        List<Column> queryOrderByFields = new ArrayList<>();
        if(orderByFields != null) {
            queryOrderByFields = Lists.newArrayList(orderByFields);
        }

        List<Column> queryGroupByFields = new ArrayList<>();
        if(groupByFields != null) {
            queryGroupByFields = Lists.newArrayList(groupByFields);
        }

        if(isGroupByDate) {
            queryOrderByFields.add(new Column(generateTruncSQL(param, "groupDate", "varchar2").toString(), "groupDate", "按日期排序"));
            queryGroupByFields.add(new Column(generateTruncSQL(param, "groupDate", "varchar2").toString(), "groupDate", "按日期分组"));
        }

        if(isGroupByArea) {
            queryGroupByFields.add(new Column("parentId", "parentId", "按parentId分组"));
            queryGroupByFields.add(new Column("parentName", "parentName", "按parentId分组"));
        }

        param = BaseServiceUtil.setParamType(param, FamilyDoctorInfoReq.class, "county");

        //如果到县就查询机构
        JSONObject filterJSON = JSONObject.parseObject(JSON.toJSONString(param), Feature.OrderedField);
        if (filterJSON.containsKey("areaType") && "3".equals(filterJSON.getString("areaType"))) {
            filterJSON.put("orgFlag","1");
        }
        if (filterJSON.containsKey("orgId") && StringUtils.isNotEmpty(filterJSON.getString("orgId"))) {
            filterJSON.put("orgFlag","1");
            filterJSON.put("deptFlag","1");
        }

        StringBuffer sb = new StringBuffer();
        sb.append("select ");
        for(String key : resultMap.keySet()) {
            OverviewObjectVo vo = resultMap.get(key);
            if(vo.getFormula() != null && !vo.getFormula().equals("")) {
                if(!vo.isCustomFormula()) {
                    if ("%".equals(vo.getUnit())) {
                        sb.append("decode(");
                        sb.append("nvl(").append(vo.getDenominator()).append(", 0)");
                        sb.append(", 0, 0,");
                        sb.append(vo.getFormula());
                        sb.append(")");
                    } else {
                        sb.append("nvl (").append(vo.getFormula()).append(", 0)");
                    }
                } else {
                    sb.append(vo.getFormula());
                }
            } else {
                sb.append("0");
//                if (vo.isCustomFormula()){
//                    sb.append("null");
//                }else{
//                    sb.append("0");
//                }
            }
            sb.append(" as ");
            sb.append("\"").append(key).append("\"");
            sb.append(",");
        }

        if(queryGroupByFields != null && queryGroupByFields.size() > 0) {
            for (int i = 0; i < queryGroupByFields.size(); i++) {
                Column groupField = queryGroupByFields.get(i);
                if(!groupField.isCustomFlag()) {
                    sb.append(groupField.getField()).append(" as ").append("\"").append(groupField.getAlias()).append("\"");
                    sb.append(",");
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);

        sb.append(" from (");
        for (int i = 0; i < tables.size(); i++) {
            TableObjectReq table = tables.get(i);

            //  ----时间字段拼接---------
            String dateColumnName = "statistics_date";
            if (table.getDateColumn() != null) {
                dateColumnName = table.getDateColumn();
            }

            //如果表格是常量表，并且查询需要拼接所有时间
            if(!table.isNotConstTable()) {
                sb.append("select ");
                sb.append(generateFieldsSQL(table, fields, true)).append(",");
                if(isGroupByDate) {
                    sb.append("d.groupDate,");
                }
                sb.append("a.parentId,");
                sb.append("a.parentName");
                sb.append(" from ");
                sb.append("(").append(generateUnionTableSQL(param, table, fields, filterJSON, dateColumnName, isGroupByDate)).append(") a,");

                String tempDateType = param.getDateType();
                if(table.isNotPointOfTime()) {
                    param.setDateType("date");
                    sb.append("(").append(generateDateCompletionSQL(param, fields, filterJSON)).append(") d");
                    param.setDateType(tempDateType);
                } else {
                    if(isGroupByDate) {
                        sb.append("(").append(generateDateCompletionSQL(param, fields, filterJSON)).append(") d");
                    } else {
                        sb.append("(select to_date(").append(param.getDateEnd()).append(", 'yyyy-MM-dd') as groupDate from dual) d");
                    }
                }

            } else {
                sb.append(generateUnionTableSQL(param, table, fields, filterJSON, dateColumnName, isGroupByDate));
            }
            if(i<tables.size()-1) {
                sb.append(" union all ");
            }
        }

        if(isComplementDate) {
            sb.append(" union all ");
            sb.append(generateDateCompletionSQL(param, fields, filterJSON));
        }
        if(isComplementArea && !"4".equals(param.getRank())) {
            sb.append(" union all ");
            String orgIdColumn = "org_id";
            if (ObjectsUtil.isNotEmpty(tables)) {
                orgIdColumn = tables.get(0).getOrgIdColumn();
            }
            sb.append(generateUnionAreaSQL(param, fields, filterJSON, orgIdColumn));
        }
        sb.append(")");


        // Group by 字段拼接
        if(queryGroupByFields != null && queryGroupByFields.size() > 0) {
            sb.append(" group by ");
            for (int i = 0; i < queryGroupByFields.size(); i++) {
                Column groupField = queryGroupByFields.get(i);
                sb.append(groupField.getField());
                if(i<queryGroupByFields.size() - 1) {
                    sb.append(",");
                }
            }
        }

        // order by 字段拼接
        if(queryOrderByFields != null && queryOrderByFields.size() > 0) {
            sb.append(" order by ");
            for (int i = 0; i < queryOrderByFields.size(); i++) {
                sb.append(queryOrderByFields.get(i).getField());
                if(i < queryOrderByFields.size() - 1) {
                    sb.append(",");
                }
            }
            if(isOrderByDesc) {
                sb.append(" desc ");
            }
        }

//        System.out.println(sb.toString());
        log.info(sb.toString());
        return sb;
    }

    /*
     * @Description: 生成
     * @Author: Nomark
     * @Date: 2019/9/11 18:39
     * @Param: [param, resultMap, tables, fields, isGroupByArea, isGroupByDate]
     * @Return: java.lang.StringBuffer
     */
    public StringBuffer generateQuerySQL(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, boolean isGroupByArea, boolean isGroupByDate) {
        return generateQuerySQL(param, resultMap, tables, fields, orderByFields, groupByFields, isGroupByArea, isGroupByDate, isGroupByArea, isGroupByDate, false);
    }

    /*
     * @Description: 生成
     * @Author: Nomark
     * @Date: 2019/9/11 18:39
     * @Param: [param, resultMap, tables, fields, isGroupByArea, isGroupByDate]
     * @Return: java.lang.StringBuffer
     */
    public StringBuffer generateQuerySQL(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap, List<TableObjectReq> tables, List<Column> fields, List<Column> orderByFields, List<Column> groupByFields, boolean isGroupByArea, boolean isGroupByDate, boolean isOrderByDesc) {
        return generateQuerySQL(param, resultMap, tables, fields, orderByFields, groupByFields, isGroupByArea, isGroupByDate, isGroupByArea, isGroupByDate, isOrderByDesc);
    }

    /*
     * @Description: 为了补全时间显示
     * @Author: Nomark
     * @Date: 2019/9/9 11:10
     * @Param: [param, fields, filterJSON]
     * @Return: java.lang.StringBuffer
     */
    private StringBuffer generateDateCompletionSQL(FamilyDoctorInfoReq param, List<Column> fields, JSONObject filterJSON) {
        StringBuffer dateCompletionSQL = new StringBuffer();
        dateCompletionSQL.append("select ");
        fields.forEach(field-> {
            if (ColumnEnum.NUM_TYPE.equals(field.getColumnEnum())){
                dateCompletionSQL.append(ColumnEnum.NUM_TYPE.getValue());
            }else if (ColumnEnum.STRING_TYPE.equals(field.getColumnEnum())){
                dateCompletionSQL.append(ColumnEnum.STRING_TYPE.getValue());
            }else if (ColumnEnum.DATE_TYPE.equals(field.getColumnEnum())){
                dateCompletionSQL.append(ColumnEnum.DATE_TYPE.getValue());
            }else {
                dateCompletionSQL.append(ColumnEnum.NUM_TYPE.getValue());
            }
//            dateCompletionSQL.append("0");
            dateCompletionSQL.append(" as ");
            dateCompletionSQL.append(field.getAlias());
            dateCompletionSQL.append(",");
        });

        dateCompletionSQL.append(generateTruncSQL(param, "groupDate", "date")).append(" as groupDate,");

        dateCompletionSQL.append("'' as parentId,");
        dateCompletionSQL.append("'' as parentName");
        if ("1".equals(param.getAllTableHaveDrugNameFlag())){
            dateCompletionSQL.append(", '' as drugCode,");
            dateCompletionSQL.append(" '' as drugName");
        }

        // dateCompletionSQL.deleteCharAt(fields.size()-1);

        dateCompletionSQL.append(" from ");
        dateCompletionSQL.append("(select ").append(generateTruncSQL(param, "groupDate", "date")).append(" as groupDate ");
        dateCompletionSQL.append(" from (select ");
        dateCompletionSQL.append("to_date('").append(filterJSON.getString("dateStart")).append("', 'yyyy-MM-dd')").append("+ rownum groupDate");
        dateCompletionSQL.append(" from dual ");
        dateCompletionSQL.append("connect by rownum <=");
        dateCompletionSQL.append("to_date('").append(filterJSON.getString("dateEnd")).append("', 'yyyy-MM-dd')");
        dateCompletionSQL.append("-");
        dateCompletionSQL.append("to_date('").append(filterJSON.getString("dateStart")).append("', 'yyyy-MM-dd')").append(")");
        dateCompletionSQL.append(" group by ").append(generateTruncSQL(param, "groupDate", "date"));
        dateCompletionSQL.append(") a");
        return dateCompletionSQL;
    }

    /*
     * @Description: 生成地区/机构area,为了补全机构显示
     * @Author: Nomark
     * @Date: 2019/9/9 11:10
     * @Param: [param, fields, filterJSON]
     * @Return: java.lang.StringBuffer
     */
    private StringBuffer generateUnionAreaSQL(FamilyDoctorInfoReq param, List<Column> fields, JSONObject filterJSON, String orgIdColumn) {
        StringBuffer unionAreaSQL = new StringBuffer();
        unionAreaSQL.append("select ");
        List<Column> newFields = ZhBeanUtils.copyList(fields);
        newFields.forEach(field-> {
            field.setField("0");
            field.setCustomFlag(true);
        });
        newFields.add(new Column("og.parent_id", "parentId", "区域Id", true, true));
        newFields.add(new Column("og.parent_name", "parentName", "区域名称", true, true));
        unionAreaSQL.append(generateFieldsSQL(null, newFields, false));

        unionAreaSQL.append(" from ");
        unionAreaSQL.append(buildOrgByType(filterJSON, true, orgIdColumn));  //3为org_id取公共卫生机构org_id_gw
        unionAreaSQL.append(" og");

        return unionAreaSQL;
    }

    /*
     * @Description: 生成union表列表
     * @Author: Nomark
     * @Date: 2019/9/9 10:03
     * @Param: [param, table, fields, filterJSON, dateColumnName]
     * @Return: java.lang.StringBuffer
     */
    public StringBuffer generateUnionTableSQL(FamilyDoctorInfoReq param, TableObjectReq table, List<Column> fields, JSONObject filterJSON, String dateColumnName, boolean isGroupByDate) {
        StringBuffer querySql = new StringBuffer();
        querySql.append("select ");
        List<Column> newFields = ZhBeanUtils.copyList(fields);
//        querySql.append(generateFieldsSQL(table, newFields));
        //是否不为空表
        if(table.isNotNullTable()) {

            //按时间排序，并且不是常量表
            if (isGroupByDate && table.isNotConstTable()) {
                if(table.isQueryLastYear()) {
                    // 如果是其他月份的同比需要改变
                    String addMonthNum = "12";
                    if ("month".equals(filterJSON.get("dateType"))){
                        addMonthNum = "1";
                    }else if ("quarter".equals(filterJSON.get("dateType"))){
                        addMonthNum = "3";
                    }
                    newFields.add(new Column("add_months(a." + dateColumnName + ", " + addMonthNum + ")", "groupDate", "日期字段", true, true));
                } else {
                    newFields.add(new Column("a."+ dateColumnName, "groupDate" , "日期字段", true, true));
                }
            }
            newFields.add(new Column("og.parent_id", "parentId", "地区Id", true, true));
            newFields.add(new Column("og.parent_name", "parentName", "地区名称", true, true));
            if ("1".equals(param.getAllTableHaveDrugNameFlag())){
                newFields.add(new Column("a.DRUG_CODE", "drugCode", "药品编码", true, true));
                newFields.add(new Column("a.DRUG_NAME", "drugName", "药品名称", true, true));
            }

            querySql.append(generateFieldsSQL(table, newFields, false));

            querySql.append(" from ").append(table.getTableName()).append(" a, ");

            querySql.append(buildOrgByType(filterJSON, table.isNotToDept(), table.getOrgIdColumn()));  //3为org_id取公共卫生机构org_id_gw
            querySql.append(" og");
            querySql.append(" where ");
            String orgIdColumnName = "org_id";
            if (table.getOrgIdColumn() != null) {
                orgIdColumnName = table.getOrgIdColumn();
            }
            String deptIdColumnName = "dept_id";
            if (table.getDeptIdColumn() != null) {
                deptIdColumnName = table.getDeptIdColumn();
            }
            if (!table.isNotToDept()) {
                querySql.append(" a.").append(orgIdColumnName).append(" = ").append("og.org_id");
                querySql.append(" and  a.").append(deptIdColumnName).append(" = ").append("og.dept_id");
            } else {
                querySql.append(" a.").append(orgIdColumnName).append(" = ").append("og.org_id");
            }
            //输入自定义条件
            if (StringUtils.isNotEmpty(table.getConditionCustom())){
                querySql.append(" and ").append(table.getConditionCustom()).append(" ");
            }
            if (ObjectsUtil.isNotEmpty(param.getFilterSql()) && param.getFilterSql().containsKey(table.getTableName())){
                querySql.append(" and ").append(param.getFilterSql().get(table.getTableName())).append(" ");
            }

            //处理时间类型
            if(table.isNotConstTable()) {
                querySql.append(" and ");
                String dateTempStr = "";
                if (table.getDateColumnType() != null && "".equals(table.getDateColumnType())) {
                    String dateType = "date";
                    if (table.getDateColumnType() != null) {
                        dateType = table.getDateColumnType();
                    }
                    String dateFormat = "yyyy-MM-dd";
                    if ("varchar2".equals(dateType)) {
                        if (table.getDataFormat() != null) {
                            dateFormat = table.getDataFormat().trim();
                        }
                        dateTempStr = " to_date(a." + dateColumnName + ", '" + dateFormat + "')";

                    } else {
                        dateTempStr = " a." + dateColumnName + " ";

                    }
                } else {
                    dateTempStr = " a." + dateColumnName + " ";
                }

                // 请求时间处理
                String dateStartParam = "dateStart";
                String dateEndParam = "dateEnd";
                //去年
                if (table.isQueryLastYear()){
                    dateStartParam = LastParamEnum.LAST_YEAR.getDateStart();
                    dateEndParam = LastParamEnum.LAST_YEAR.getDateEnd();
                //上一季/上一月
                }else if (table.isQueryLast()){
                    dateStartParam = LastParamEnum.LAST_DAY.getDateStart();
                    dateEndParam = LastParamEnum.LAST_DAY.getDateEnd();
                //自定义时间
                }else if (table.isQueryCustom()){
                    dateStartParam = LastParamEnum.LAST_CUSTOM.getDateStart();
                    dateEndParam = LastParamEnum.LAST_CUSTOM.getDateEnd();
                }

                // 是否查询时间点数据
                if (!table.isNotPointOfTime()) {
                    //时间节点表  取用最大时间
                    if (table.isCustomPointOfTimeFlag()){
                        String dateFormatType = param.getDateFormatType();
                        //查询最大时间
                        StringBuffer pointDateParam = new StringBuffer();
                        pointDateParam.append("( SELECT ").append(dateColumnName).append(" from ");
                        pointDateParam.append("(select max(").append(dateColumnName).append(") as ").append(dateColumnName).append(" , ");
                        pointDateParam.append(" trunc ( ").append(dateColumnName).append(" , '").append(dateFormatType).append("' ) AS yearDate ").append(" from ");
                        pointDateParam.append(table.getTableName()).append(" WHERE ").append(dateColumnName).append(" <= ").append("to_date('");
                        pointDateParam.append(filterJSON.getString(dateEndParam));
                        pointDateParam.append("'").append(", '").append("yyyy-MM-dd') ");
                        pointDateParam.append(" and ").append(dateColumnName).append(" >= ").append("to_date('");
                        pointDateParam.append(filterJSON.getString(dateStartParam));
                        pointDateParam.append("'").append(", '").append("yyyy-MM-dd')");
                        pointDateParam.append(" GROUP BY trunc ( ").append(dateColumnName).append(" , '").append(dateFormatType).append("' )))");
                        querySql.append(dateTempStr).append(" IN ").append(pointDateParam);
                    } else if(!ObjectUtils.isEmpty(table.getTimeCondition())){
                        querySql.append(dateTempStr).append(" ").append(table.getTimeCondition()).append(" trunc(to_date('").append(filterJSON.getString(dateEndParam)).append("'").append(", '").append("yyyy-MM-dd') + 1, 'mm')");
                    } else {
                        // 时间点表，因传入了是结束月份的最后一天，实际记录时间是+1天
                        //TODO : 需要修改或者添加新的判断条件，增加.append(" <= ") dateEnd;
                        querySql.append(dateTempStr).append(" = ").append("trunc(to_date('").append(filterJSON.getString(dateEndParam)).append("'").append(", '").append("yyyy-MM-dd') + 1, 'mm')");
                    }
                } else {
                    querySql.append(dateTempStr).append(" >= ").append("to_date('");
                    querySql.append(filterJSON.getString(dateStartParam));
                    querySql.append("'").append(", '").append("yyyy-MM-dd')");
                    querySql.append(" and ");
                    querySql.append(dateTempStr).append(" <= ").append("to_date('");
                    querySql.append(filterJSON.getString(dateEndParam));
                    querySql.append("'").append(", '").append("yyyy-MM-dd')");
                }
            }
        } else {
            if (isGroupByDate) {
                newFields.add(new Column("sysdate", "groupDate", "日期", true));
            }
            newFields.add(new Column("''", "parentId", "填充ID", true));
            newFields.add(new Column("''", "parentName", "填充名称", true));
            if ("1".equals(param.getAllTableHaveDrugNameFlag())){
                newFields.add(new Column("''", "drugCode", "药品编码", true, true));
                newFields.add(new Column("''", "drugName", "药品名称", true, true));
            }
            querySql.append(generateFieldsSQL(table, newFields, false));
            querySql.append(" from ").append(table.getTableName()).append(" where 0=1");
        }
        return querySql;
    }

    /*
     * @Description: 生成日期groupby字段
     * @Author: Nomark
     * @Date: 2019/9/9 9:51
     * @Param: [param, dateField]
     * @Return: java.lang.String
     */
    public StringBuffer generateTruncSQL(FamilyDoctorInfoReq param, String dateField, String generateType) {
        StringBuffer truncSQL = new StringBuffer();

        if("year".equals(param.getDateType())) {
            if("varchar2".equals(generateType)) {
                truncSQL.append("to_char(").append(dateField).append(", 'yyyy')");
            } else {
                truncSQL.append("trunc(").append(dateField).append(", 'y')");
            }

        }
        if("quarter".equals(param.getDateType())) {
            if("varchar2".equals(generateType)) {
                truncSQL.append("to_char(").append(dateField).append(", 'yyyy-q')").toString();
            } else {
                truncSQL.append("trunc(").append(dateField).append(", 'q')");
            }
        }
        if("month".equals(param.getDateType())) {
            if("varchar2".equals(generateType)) {
                truncSQL.append("to_char(").append(dateField).append(", 'yyyy-mm')").toString();
            } else {
                truncSQL.append("trunc(").append(dateField).append(", 'mm')");
            }
        }

        if("date".equals(param.getDateType())) {
            if("varchar2".equals(generateType)) {
                truncSQL.append("to_char(").append(dateField).append(", 'yyyy-mm-dd')").toString();
            } else {
                truncSQL.append(dateField);
            }
        }
        return truncSQL;
    }

    /*
     * @Description: 补全内层指标字段
     * @Author: Nomark
     * @Date: 2019/9/24 18:36
     * @Param: [table, fields, takeFieldAlias-主要是常量表]
     * @Return: java.lang.StringBuffer
     */
    public StringBuffer generateFieldsSQL(TableObjectReq table, List<Column> fields, boolean takeFieldAlias) {
        StringBuffer fieldsSQL = new StringBuffer();
        for (int i = 0; i < fields.size(); i++) {
            Column field = fields.get(i);
            if(field.isGlobal()) { //判断是否全局使用字段
                fieldsSQL.append(field.getField());
            } else if(table != null && table.getColumns().containsKey(field.getAlias())) {  //判断字段列表中是否在表中
                //字段是否自定义计算公式
                if(takeFieldAlias){
                    fieldsSQL.append("a.").append(field.getAlias());
                } else if (field.isCustomFlag()) {
                    fieldsSQL.append(field.getField());
                } else {
                    fieldsSQL.append("nvl(a.").append(field.getField()).append(", 0)");
                }
            } else {
                if (ColumnEnum.NUM_TYPE.equals(field.getColumnEnum())){
                    fieldsSQL.append(ColumnEnum.NUM_TYPE.getValue());
                }else if (ColumnEnum.STRING_TYPE.equals(field.getColumnEnum())){
                    fieldsSQL.append(ColumnEnum.STRING_TYPE.getValue());
                }else if (ColumnEnum.DATE_TYPE.equals(field.getColumnEnum())){
                    fieldsSQL.append(ColumnEnum.DATE_TYPE.getValue());
                }else {
                    fieldsSQL.append(ColumnEnum.NUM_TYPE.getValue());
                }
            }
            fieldsSQL.append(" as ");
//            if(table != null && !table.isNotConstTable() && table.getColumns().containsKey(field.getAlias())) {
//                fieldsSQL.append(field.getField());
//            } else {
            fieldsSQL.append(field.getAlias());
//            }

            if(i < fields.size()-1) {
                fieldsSQL.append(",");
            }
        }
        return fieldsSQL;
    }

    /*
     * @Description: 根据机构类型生成tables
     * @Author: Nomark
     * @Date: 2019/9/7 18:45
     * @Param: [filterJson, notToDept]
     * @Return: java.lang.String
     */
    public static String buildOrgByType(JSONObject filterJson,boolean notToDept, String orgIdColumn) {
        if(orgIdColumn == null) {
            orgIdColumn = "org_id";
        }
        StringBuilder sqlOrgBuilder = new StringBuilder();
        sqlOrgBuilder.append(" ( ");
        sqlOrgBuilder.append(" SELECT ");
        sqlOrgBuilder.append(" b.area_id, ");
        sqlOrgBuilder.append(" b.area_name, ");
        sqlOrgBuilder.append(" b.area_type, ");
        if (!notToDept && filterJson.containsKey("deptFlag") && StringUtils.isNotEmpty(filterJson.getString("deptFlag")) && "1".equals(filterJson.getString("deptFlag"))) {
            sqlOrgBuilder.append(" e.dept_id as parent_id, ");
            sqlOrgBuilder.append(" e.dept_name as parent_name, ");
        } else if (filterJson.containsKey("orgFlag") && StringUtils.isNotEmpty(filterJson.getString("orgFlag")) && "1".equals(filterJson.getString("orgFlag"))) {
            sqlOrgBuilder.append(" s.").append(orgIdColumn).append(" as parent_id, ");
            sqlOrgBuilder.append(" s.org_name as parent_name, ");
        } else {
            sqlOrgBuilder.append(" b.parent_id, ");
            sqlOrgBuilder.append(" d.area_name as parent_name, ");
        }
        sqlOrgBuilder.append("s.").append(orgIdColumn).append(" as org_id,");
        sqlOrgBuilder.append("s.org_name,");
        sqlOrgBuilder.append("s.IFGG00,");
        sqlOrgBuilder.append("s.IFGLJG,");
        sqlOrgBuilder.append("s.address,");
        sqlOrgBuilder.append("s.founding_time,");
        sqlOrgBuilder.append("s.ORG_POSTLEVEL,");
        sqlOrgBuilder.append("s.ORG_TYPE,");
        sqlOrgBuilder.append("s.IFQSJC,");
        if (!notToDept) {
            sqlOrgBuilder.append("s.ORG_GRADE,");
            sqlOrgBuilder.append("e.dept_id ,");
            sqlOrgBuilder.append("e.dept_name");
        } else {
            sqlOrgBuilder.append("s.ORG_GRADE ");
        }
        sqlOrgBuilder.append(" FROM ");
        if (!notToDept) {
            sqlOrgBuilder.append("dept e  join sys_org s on s.org_id=e.org_id");
        } else {
            sqlOrgBuilder.append("sys_org s ");
        }
        sqlOrgBuilder.append(" join ( ");
        sqlOrgBuilder.append(" SELECT ");
        sqlOrgBuilder.append(" A.area_id, ");
        sqlOrgBuilder.append(" A.area_name, ");
        sqlOrgBuilder.append(" A.area_type, ");
        sqlOrgBuilder.append(" (SUBSTR(A.AREA_ID, 0,");
        sqlOrgBuilder.append(filterJson.getString("subNum") + ")");
        sqlOrgBuilder.append(" || ");
        sqlOrgBuilder.append("'" + filterJson.getString("subString") + "'");
        sqlOrgBuilder.append(" ) AS parent_id  ");
        sqlOrgBuilder.append(" FROM ");
        sqlOrgBuilder.append(" sys_area_fjzl A ");
        sqlOrgBuilder.append(" WHERE ");
        sqlOrgBuilder.append(" TO_NUMBER (A.AREA_TYPE) <= 5 START WITH A.area_id = ");
        sqlOrgBuilder.append("'" + filterJson.getString("county") + "'");
        sqlOrgBuilder.append(" CONNECT BY PRIOR A.AREA_ID = A.PARENT_ID ");
        sqlOrgBuilder.append(" ) b on b.area_id = s.area_id ");
        sqlOrgBuilder.append(" join SYS_AREA_FJZL d on b.parent_id = d.AREA_ID ");
        sqlOrgBuilder.append(" WHERE ");
        sqlOrgBuilder.append(" s.IS_DELETE = '0' ");
        //私立机构条件,把私立和公立医院一起查询出来
        if (filterJson.containsKey("privateOrgFlag") && "1".equals(filterJson.getString("privateOrgFlag"))){
            sqlOrgBuilder.append(" and s.IFGLJG = '0' ");
        } else if (filterJson.containsKey("privateAndPublicOrgFlag") && "1".equals(filterJson.getString("privateAndPublicOrgFlag"))){
            //公立医院和私立医院一起查

        }else {
            //公立机构
            sqlOrgBuilder.append(" and s.IFGLJG = '1' ");
        }
        if (filterJson.containsKey("deptFlag") && "1".equals(filterJson.getString("deptFlag"))) {
            sqlOrgBuilder.append( " and ");
            sqlOrgBuilder.append(" s.org_id = '").append(filterJson.getString("orgId")).append("' ");
        } else {
            StringBuilder orgGradesStr = new StringBuilder();
            if (filterJson.containsKey("orgGradeList") && null != filterJson.get("orgGradeList")) {
                JSONArray orgGradeJSONArray = filterJson.getJSONArray("orgGradeList");
                if (orgGradeJSONArray.size() > 0 ) {
                    List<String> orgGrades = new ArrayList<>();
                    AtomicBoolean specialOrgFlag = new AtomicBoolean(false);
                    orgGradeJSONArray.forEach(oj -> {
                        if (StringUtils.isNotEmpty(oj.toString())) {
                            if ("9".equals(oj.toString())){
                                specialOrgFlag.set(true);
                            }
                            orgGrades.add(oj.toString());
                        }
                    });
                    orgGradesStr.append(" ( s.org_grade in (").append(orgGrades.stream().collect(Collectors.joining(","))).append(") ");
                    if (specialOrgFlag.get()){
//                        orgGradesStr.append(" or s.ORG_PROPERTY like  '%4%' ");
                        orgGradesStr.append(" or s.IFZKJG = '1' ");
                    }
                    orgGradesStr.append(" ) ");
                }
            }
            StringBuilder orgIdListStr = new StringBuilder();
            if (filterJson.containsKey("orgIdList") && null != filterJson.get("orgIdList")) {
                JSONArray orgIdJSONArray = filterJson.getJSONArray("orgIdList");
                if (orgIdJSONArray.size() > 0 ) {
                    List<String> orgIds = new ArrayList<>();
                    StringBuilder orgIdsBuilder = new StringBuilder();
                    orgIdJSONArray.forEach(oj -> {
                        if (StringUtils.isNotEmpty(oj.toString())) {
                            orgIds.add(oj.toString());
                            orgIdsBuilder.append("'").append(oj.toString()).append("',");
                        }
                    });
                    if (orgIdsBuilder.length() > 0) {
                        orgIdListStr.append("  s.org_id in (").append(orgIdsBuilder.substring(0,orgIdsBuilder.lastIndexOf(","))).append(") ");
                    }
                }
            }
            //如果同时有选择 机构等级  和其他单家机构  使用or条件
            if (ObjectsUtil.isNotEmpty(orgGradesStr) && ObjectsUtil.isNotEmpty(orgIdListStr)){
                sqlOrgBuilder.append(" and ( ").append(orgGradesStr).append(" or ").append(orgIdListStr).append(" ) ");
            } else if (ObjectsUtil.isNotEmpty(orgGradesStr)){
                sqlOrgBuilder.append(" and ").append(orgGradesStr);
            } else if (ObjectsUtil.isNotEmpty(orgIdListStr)){{
                sqlOrgBuilder.append(" and ").append(orgIdListStr);
            }
            }
        }


        if (filterJson.containsKey("orgFilter") && StringUtils.isNotEmpty(filterJson.getString("filterJson"))) {
            sqlOrgBuilder.append(" ").append(filterJson.getString("orgFilter"));
        }

        sqlOrgBuilder.append(" ) ");
        return sqlOrgBuilder.toString();
    }

    //查询预警消息
    private Map<String,WarningIndicatorsDto> listWarningIndicatorsDto(FamilyDoctorInfoReq param, Map<String, OverviewObjectVo> resultMap,List<String> paramList){
        WarningIndicatorsDto warningParam = new WarningIndicatorsDto();
        if (param.getOrgIdList()!=null && param.getOrgIdList().size()==1) {
            warningParam.setIsSingleOrg("1");
        } else {
            warningParam.setIsSingleOrg("0");
        }
        long lo = DateUtil.betweenMonth(DateUtil.parse(param.getDateStart()),DateUtil.parse(param.getDateEnd()),true);
        lo += 1;
        warningParam.setLonMonth((int) lo, param.getDateType());
        warningParam.setAreaId(param.getCounty());
        warningParam.setOrgIdList(param.getOrgIdList());
        warningParam.setOrgGradeList(param.getOrgGradeList());
        warningParam.setIndicatorsKeyList(new ArrayList<String>(resultMap.keySet()));
        List<WarningIndicatorsDto> list = warningIndicatorsMapper.getWarningByIndicatorsName(warningParam);
        Map<String,WarningIndicatorsDto> result = Maps.newHashMap();
        list.forEach(warningIndicatorsDto -> {
            WarningIndicatorsDto s = result.get(warningIndicatorsDto.getIndicatorsKey());
            if (s != null) {
                warningIndicatorsDto.setCountIntervalNum(s.getCountIntervalNum() + 1);
            }
            result.put(warningIndicatorsDto.getIndicatorsKey(),warningIndicatorsDto);
            paramList.add(warningIndicatorsDto.getWarningId());
        });
        return result;
    }
    //查询预警消息规格
    private Map<String,List<WarningIntervalDto>> getWarningIntervalByWarningIds(List<String> paramList){
        Map<String,List<WarningIntervalDto>> waringMaps = Maps.newHashMap();
        if (ObjectUtils.isEmpty(paramList)){
            return waringMaps;
        }
        List<WarningIntervalDto> warningIntervalDtoLists = warningIndicatorsMapper.getWarningIntervalByWarningIds(paramList);
        warningIntervalDtoLists.forEach(warningIntervalDto -> {
            if (ObjectUtils.isEmpty(waringMaps.get(warningIntervalDto.getWarningId()))){
                List<WarningIntervalDto> dtoList = Lists.newArrayList();
                dtoList.add(warningIntervalDto);
                waringMaps.put(warningIntervalDto.getWarningId(),dtoList);
            }else {
                waringMaps.get(warningIntervalDto.getWarningId()).add(warningIntervalDto);
            }
        });
        return waringMaps;
    }

}
