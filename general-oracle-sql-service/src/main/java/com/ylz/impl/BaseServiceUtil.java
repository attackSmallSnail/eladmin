package com.ylz.impl;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.collect.Lists;
import com.ylz.common.enums.AreaNumberEnum;
import com.ylz.common.objutils.BusinessUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @className: BaseServiceUtil
 * @author: lcw
 * @date: 2019/6/17 9:37
 * @Desc: :描述该类的主要功能
 * @Version: 1.0
 */
@Component
@Order
public class BaseServiceUtil {


    public static  <T> T setParamType(T obj,Class<T> clazz,String keyName){
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(obj), Feature.OrderedField);
        String areaNumber = jsonObject.getString(keyName);
        if (StringUtils.isNotEmpty(areaNumber) && areaNumber.length() < 12) {
            areaNumber = areaNumber + "000000";
            jsonObject.put(keyName, areaNumber);
        }
        String areaType = BusinessUtils.getAreaType(BusinessUtils.rtrim(areaNumber, "0").length()).toString();
        jsonObject.put("areaType", areaType);
        AreaNumberEnum areaNumberEnum = AreaNumberEnum.getAreaSub(areaType);
        jsonObject.put("subString",areaNumberEnum.getValue());
        jsonObject.put("subNum",areaNumberEnum.getNum());
        jsonObject.put("areaType",areaType);
        String parentName = "(select area_name from SYS_AREA_FJZL where area_id = '"+areaNumber+"') ";
        jsonObject.put("parentName",parentName);
        obj = JSONObject.parseObject(jsonObject.toJSONString(),clazz, Feature.OrderedField);
        return obj;
    }



    /**
    * @author: lcw
    * @Description: 倒序和拼接年份
    * @Params:
    * @createDate: 2019/7/1
    * @remark:
    */
    public static <T> void sortAndAppendYearData(List<T> yearVoList, List<String> yearStrs,Class<T> cls,List<String> keyNames,String yearKeyName){
        if (ObjectUtils.isEmpty(yearKeyName)){
            yearKeyName = "years";
        }
        List<String> haveYears = Lists.newArrayList();
        for (Object vo: yearVoList){
            JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(vo));
            haveYears.add(String.valueOf(obj.get(yearKeyName)));
        }
        List<String> allYears = Lists.newArrayList();
        allYears.addAll(yearStrs);
        allYears.removeAll(haveYears);
        List<T> lostData = Lists.newArrayList();
        for (String str: allYears){
            JSONObject vo = new JSONObject();
            vo.put(yearKeyName,Long.valueOf(str));
            for (String key: keyNames){
                vo.put(key,0);
            }
            T tVo = JSONObject.parseObject(vo.toJSONString(),cls);
            lostData.add(tVo);
        }
        yearVoList.addAll(lostData);
        final String keyName = yearKeyName;
        Collections.sort(yearVoList, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                //降序
                JSONObject json2 = JSONObject.parseObject(JSONObject.toJSONString(o2));
                JSONObject json1 = JSONObject.parseObject(JSONObject.toJSONString(o1));
                return json2.getLong(keyName).compareTo(json1.getLong(keyName));
            }
        });
    }

    /**
     * @description:  拼接年份数据和升序年份
     * @param:
     * @return:
     * @auther: lcw
     * @date: 2019/7/22 14:07
     */
    public static <T> void sortAndAppendYearDataOrder(List<T> yearVoList, List<String> yearStrs,Class<T> cls,List<String> keyNames,String yearKeyName){
        if (ObjectUtils.isEmpty(yearKeyName)){
            yearKeyName = "years";
        }
        List<String> haveYears = Lists.newArrayList();
        for (Object vo: yearVoList){
            JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(vo));
            haveYears.add(String.valueOf(obj.get(yearKeyName)));
        }
        List<String> allYears = Lists.newArrayList();
        allYears.addAll(yearStrs);
        allYears.removeAll(haveYears);
        List<T> lostData = Lists.newArrayList();
        for (String str: allYears){
            JSONObject vo = new JSONObject();
            vo.put(yearKeyName,Long.valueOf(str));
            for (String key: keyNames){
                vo.put(key,0);
            }
            T tVo = JSONObject.parseObject(vo.toJSONString(),cls);
            lostData.add(tVo);
        }
        yearVoList.addAll(lostData);
        final String keyName = yearKeyName;
        Collections.sort(yearVoList, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                //升序
                JSONObject json2 = JSONObject.parseObject(JSONObject.toJSONString(o1));
                JSONObject json1 = JSONObject.parseObject(JSONObject.toJSONString(o2));
                return json2.getLong(keyName).compareTo(json1.getLong(keyName));
            }
        });
    }

}
