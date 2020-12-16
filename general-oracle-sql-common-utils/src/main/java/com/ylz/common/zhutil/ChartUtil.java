package com.ylz.common.zhutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ylz.common.enums.CoordinateEnum;
import com.ylz.common.pojo.HistogramVo;
import com.ylz.common.pojo.OverviewObjectVo;
import com.ylz.common.pojo.CircleTabVo;
import org.assertj.core.util.Lists;
import org.springframework.util.ObjectUtils;

import java.util.*;

public class ChartUtil {

    /*
     * @Description: 日期分组柱状图、条状图、折线图组件
     * @Author: Nomark
     * @Date: 2019/9/26 10:12
     * @Param: [dateGroupList, resultMap]
     * @Return: java.util.List<com.ylz.common.pojo.HistogramVo>
     */
    public static List<HistogramVo> dateGroupChart(List<Map> dateGroupList, Map<String, OverviewObjectVo> resultMap) {
        List chartList = new ArrayList();
        Map chartMap = new HashMap();
        List<HistogramVo> dataList = new ArrayList();
        String orderType = "0";
        for(String key:resultMap.keySet()) {
            Map<String,String> obj = resultMap.get(key).getObject();
            if (!ObjectUtils.isEmpty(obj) && obj.containsKey("orderType")){
                orderType = obj.get("orderType");
                break;
            }
        }
        //类型1的排序方式r
        if ("0".equals(orderType)){
            Set<String> ts = new TreeSet<>(resultMap.keySet());
            for (Map map : dateGroupList) {
                //遍历keySet需要有序，可以使 x 排在 xTb（其同比）前面
                for(String key: ts) {
                    if(resultMap.get(key).isImportant()) {
                        if(resultMap.get(key).getCoordinate() == CoordinateEnum.Y) {
                            dataList.add(new HistogramVo(map.get("groupDate").toString(), map.get(key), resultMap.get(key).getTitle()));
                        }
                        if(resultMap.get(key).getCoordinate() == CoordinateEnum.DL) {
                            dataList.add(new HistogramVo(map.get("groupDate").toString(), null, map.get(key), resultMap.get(key).getTitle()));
                        }
                        if(resultMap.get(key).getCoordinate() == CoordinateEnum.DR) {
                            dataList.add(new HistogramVo(map.get("groupDate").toString(), null, null, map.get(key), resultMap.get(key).getTitle()));
                        }

                    }
                    if(resultMap.get(key).isGraphicUnit()) {
                        chartMap.put("unit", resultMap.get(key).getUnit());
                    }
                }
            }
        }else if ("1".equals(orderType)){
            for(String key:resultMap.keySet()) {
                for (Map map : dateGroupList) {
                    if(resultMap.get(key).isImportant()) {
                        if(resultMap.get(key).getCoordinate() == CoordinateEnum.Y) {
                            dataList.add(new HistogramVo(map.get("groupDate").toString(), map.get(key), resultMap.get(key).getTitle()));
                        }
                        if(resultMap.get(key).getCoordinate() == CoordinateEnum.DL) {
                            dataList.add(new HistogramVo(map.get("groupDate").toString(), null, map.get(key), resultMap.get(key).getTitle()));
                        }
                        if(resultMap.get(key).getCoordinate() == CoordinateEnum.DR) {
                            dataList.add(new HistogramVo(map.get("groupDate").toString(), null, null, map.get(key), resultMap.get(key).getTitle()));
                        }
                    }
                }
                if(resultMap.get(key).isGraphicUnit()) {
                    chartMap.put("unit", resultMap.get(key).getUnit());
                }
            }
        }
        chartMap.put("data", dataList);
        if(!chartMap.containsKey("unit")) {
            chartMap.put("unit", "");
        }
        chartList.add(chartMap);
        return chartList;
    }

    /*
     * @Description: 以区域、机构为Group的详情列表
     * @Author: Nomark
     * @Date: 2019/9/26 10:17
     * @Param: [chartDatas, resultMap, headColumnVos]
     * @Return: java.util.Map
     */
    public static List<Map> detailChart(List<Map> chartDatas, Map<String, OverviewObjectVo> resultMap) {
        List list = new ArrayList();
        for (int i = 0; i < chartDatas.size(); i++) {
            Map map = chartDatas.get(i);
            Map columnMap = new HashMap();
            columnMap.put("areaId", map.get("parentId"));
            columnMap.put("areaName", map.get("parentName"));
            for(String key:resultMap.keySet()) {
                columnMap.put(key, map.get(key));
            }
            columnMap.put("sort", String.valueOf(i+1));
            columnMap.put("key", map.get("parentId"));
            list.add(columnMap);
        }
        return list;
    }

    /*
     * @Description: 总计列
     * @Author: Nomark
     * @Date: 2019/9/26 10:38
     * @Param: [chartData, resultMap, param]
     * @Return: java.util.Map
     */
    public static <T> Map totalChart(Map chartData, Map<String, OverviewObjectVo> resultMap, T param) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(param));
        Map totalRecordMap = new HashMap();
        if(jsonObject.containsKey("county") && jsonObject.get("county") != null) {
            totalRecordMap.put("areaId", jsonObject.get("county"));
        } else {
            totalRecordMap.put("areaId", "-");
        }
        for(String key:resultMap.keySet()) {
            if(chartData != null && chartData.containsKey(key)) {
                totalRecordMap.put(key, chartData.get(key));
            } else {
                totalRecordMap.put(key, "0");
            }
        }
        totalRecordMap.put("areaName", "合计");
        totalRecordMap.put("key", "total");
        totalRecordMap.put("sort", "-");
        return totalRecordMap;
    }

    /*
     * @Description: 饼图方法
     * @Author: Nomark
     * @Date: 2019/9/26 10:38
     * @Param: [chartData, resultMap]
     * @Return: java.util.List
     */
    public static Map pieChart(Map chartData, Map<String, OverviewObjectVo> resultMap) {
        Map<String, CircleTabVo> dataList = new LinkedHashMap<>();
        Map result = new HashMap();
        //填充数据
        for(String key:resultMap.keySet()) {
            if(!ObjectUtils.isEmpty(chartData) && chartData.containsKey(key)) {
                //如果是同比字段自动设值到指定的同比字段
                if (resultMap.get(key).isAnFlag()){
                    String anKey = key.substring(0, key.length() - 2);
                    if (dataList.containsKey(anKey)){
                        dataList.get(anKey).setAn(chartData.get(key));
                    }else {
                        dataList.put(anKey,new CircleTabVo(chartData.get(key)));
                    }
                    //如果是总计
                }else if (resultMap.get(key).isTotal()){
                    result.put("total",chartData.get(key));
                    result.put("unit",resultMap.get(key).getUnit());
                    //如果是同比
                }else if (resultMap.get(key).isTotalAn()){
                    result.put("totalAn",chartData.get(key));
                    //如果是平均值
                }else if (resultMap.get(key).isAvgData()){
                    String avgKey = key.substring(0, key.length() - 2);
                    if (dataList.containsKey(avgKey)){
                        dataList.get(avgKey).setAvgData(chartData.get(key));
                    }else {
                        dataList.put(avgKey,new CircleTabVo(chartData.get(key),resultMap.get(key).getFormula()));
                    }
                    //如果是总计
                }else if (resultMap.get(key).isXPercent()){
                    String anKey = key.substring(0, key.length() - 2);
                    if (dataList.containsKey(anKey)){
                        dataList.get(anKey).setYPercent(chartData.get(key));
                        dataList.get(anKey).setXPercent(resultMap.get(key).getTitle());
                    }else {
                        dataList.put(anKey,new CircleTabVo(resultMap.get(key).getTitle(),chartData.get(key)));
                    }
                    //最后一个默认值
                }else{
                    if (dataList.containsKey(key)){
                        dataList.get(key).setY(chartData.get(key));
                        dataList.get(key).setX(resultMap.get(key).getTitle());
                    }else {
                        dataList.put(key,new CircleTabVo(resultMap.get(key).getTitle(),chartData.get(key),resultMap.get(key).getFormula()));
                    }
                }
                //查询值为空
            } else {
                //如果是总计
                if (resultMap.get(key).isAnFlag()){
                    String anKey = key.substring(0, key.length() - 2);
                    if (dataList.containsKey(anKey)){
                        dataList.get(anKey).setAn(0d);
                    }
                    //如果是总计
                } else if (resultMap.get(key).isTotal()){
                    result.put("total",0);
                    result.put("unit",resultMap.get(key).getUnit());
                    //如果是同比
                }else if (resultMap.get(key).isTotalAn()){
                    result.put("totalAn",0);
                    //如果是平均值
                }else if (resultMap.get(key).isAvgData()){
                    String avgKey = key.substring(0, key.length() - 2);
                    if (dataList.containsKey(avgKey)){
                        dataList.get(avgKey).setAvgData(0);
                    }
                    //如果是总计
                }else if (resultMap.get(key).isXPercent()){
                    String anKey = key.substring(0, key.length() - 2);
                    if (dataList.containsKey(anKey)){
                        dataList.get(anKey).setYPercent(0d);
                        dataList.get(anKey).setXPercent(resultMap.get(key).getTitle());
                    }
                    //最后一个默认值
                }else{
                    if (dataList.containsKey(key)){
                        dataList.get(key).setY(0d);
                        dataList.get(key).setX(resultMap.get(key).getTitle());
                    }else {
                        dataList.put(key,new CircleTabVo(resultMap.get(key).getTitle(),0d,resultMap.get(key).getFormula()));
                    }
                }
            }
        }
        List<CircleTabVo> list = Lists.newArrayList();
        for (String key: dataList.keySet()){
            list.add(dataList.get(key));
        }
        result.put("data",list);
        return result;
    }
}
