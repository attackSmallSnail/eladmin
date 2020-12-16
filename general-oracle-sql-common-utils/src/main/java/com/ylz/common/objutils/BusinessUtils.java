package com.ylz.common.objutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.ylz.common.constants.ResultInfoConstants;
import com.ylz.common.enums.AreaDeep;
import com.ylz.common.enums.DateTypeEnum;
import com.ylz.common.enums.ShowProgressEnum;
import org.assertj.core.util.Lists;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import rufus.lzstring4java.LZString;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class BusinessUtils {


    private static Pattern pattern = Pattern.compile("(^[\\-0-9][0-9]*(.[0-9]+)?)$");

    public static Map<String, String> getYearRange(String dateEnd) {
        Integer year = null;
        if (dateEnd.length() > 4) {
            year = Integer.parseInt(dateEnd.substring(0, 4));
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            year = calendar.get(Calendar.YEAR);
        }
        Map<String, String> yearMap = new HashMap<>();
        for (int i = 0; i < ResultInfoConstants.YEAR_RANGE; i++) {
            yearMap.put("year_" + i, (year - i) + "");
        }
        return yearMap;
    }

    public static List<String> buildYearListRange(String dateEnd,Integer range) {
        List<String> yearList = new ArrayList<>(5);
        Integer year = null;
        if (dateEnd.length() > 4) {
            year = Integer.parseInt(dateEnd.substring(0, 4));
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            year = calendar.get(Calendar.YEAR);
        }
        for (int i = 0; i < range; i++) {
            yearList.add((year - i) + "");
        }
        return yearList;
    }

    public static List<Map> buildLackYear(List<Map> yearMap, String[] keys, String xKey, Integer range, String dateEnd, Object nullValue) {
        Integer year = null;
        if (dateEnd.length() > 4) {
            year = Integer.parseInt(dateEnd.substring(0, 4));
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            year = calendar.get(Calendar.YEAR);
        }
        List<Integer> xKeys = new ArrayList<>();
        try {
            for (Map ym : yearMap) {
                if (ym.containsKey(xKey) && ym.get(xKey) != null) {
                    xKeys.add(Integer.parseInt(ym.get(xKey).toString()));
                }
            }
        } catch (Exception e) {

        }
        List<Map> reList = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            Integer nYear = year - i;
            if (!xKeys.contains(nYear)) {
                Map m = new HashMap();
                m.put(xKey, nYear + "");
                for (String k : keys) {
                    m.put(k, nullValue);
                }
                reList.add(m);
            }
        }
        reList.addAll(yearMap);
        Collections.sort(reList, new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                return new BigDecimal(o1.get(xKey).toString()).compareTo(new BigDecimal(o2.get(xKey).toString()));
            }
        });
        return reList;
    }

    public static Map<String, String> getSameRatioRange(final String dateEnd) {
        Integer year = null;
        if (dateEnd.length() > 4) {
            year = Integer.parseInt(dateEnd.substring(0, 4));
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            year = calendar.get(Calendar.YEAR);
        }
        Map<String, String> yearMap = new HashMap<>();
        yearMap.put("currentYear", year + "");
        yearMap.put("lastOneYear", (year - 1) + "");
        return yearMap;
    }


    public static List<Map<String, String>> buildHeaderColumns(String[] columnNames) {
        List<Map<String, String>> columns = new ArrayList<>();
        for (String cn : columnNames) {
            Map<String, String> mp = new HashMap<>();
            cn = cn.replaceAll(" ","");
            String[] cns = cn.split("=");
            String title = cns[1];
            if (cns[1].indexOf("#") >= 0){
                title = title.replace("#","");
                mp.put("isRatio", "true");
            }
            mp.put("title", title);
            mp.put("dataIndex", cns[0]);
            mp.put("key", cns[0]);
            columns.add(mp);
        }
        return columns;
    }

    public static List<Map<String, Object>> buildHeaderChildrenColumns(String[] columnNames) {
        List<Map<String, Object>> columns = new ArrayList<>();
        int i = 0;
        for (String cn : columnNames) {
            if (StringUtils.isNotEmpty(cn) && cn.indexOf("=") > 0) {
                if (cn.indexOf("@") > 0) {
                    cn = cn.replaceAll(" ","");
                    String[] cStrs = cn.split("@");
                    Map<String, Object> column = new HashMap<>();
                    column.put("title", cStrs[0]);
                    String[] childrenStr = cStrs[1].split(":");
                    List<Map<String, Object>> children = new ArrayList<>();
                    for (String cs : childrenStr) {
                        Map<String, Object> child = new HashMap<>();
                        String[] childStrs = cs.split("=");
                        String title = childStrs[1];
                        if (childStrs[1].indexOf("#") >= 0){
                            title = title.replace("#","");
                            child.put("isRatio", "true");
                        }
                        child.put("title", title);
                        child.put("dataIndex", childStrs[0]);
                        child.put("key", childStrs[0]);
                        children.add(child);
                    }
                    column.put("children", children);
                    columns.add(column);
                } else {
                    Map<String, Object> column = new HashMap<>();
                    cn = cn.replaceAll(" ","");
                    String[] cns = cn.split("=");
                    column.put("title", cns[1]);
                    column.put("dataIndex", cns[0]);
                    column.put("key", cns[0]);
                    if (i == 2){
                        column.put("sorter",true);
                        column.put("showProgress", ShowProgressEnum.T.getValue());
                    } else {
                        column.put("sorter", false);
                        column.put("showProgress", ShowProgressEnum.F.getValue());
                        column.put("isRatio", false);
                    }
                    if (i == 3) {
                        column.put("isAn",true);
                    } else {
                        column.put("isAn",false);
                    }
                    columns.add(column);
                }
            }
            i++;
        }
        return columns;
    }
    public static List<Map<String, Object>> buildHeaderChildrenColumns(String[] columnNames,Map<String,String> sparMap) {
        List<Map<String, Object>> columns = new ArrayList<>();
        sparMap.keySet().forEach(smk -> {
            if (StringUtils.isNotEmpty(sparMap.get(smk))) {
                sparMap.put(smk,sparMap.get(smk) + ",");
            }
        });
        int i = 0;
        Set<String> sparKeySet = (sparMap != null && sparMap.size() > 0) ? sparMap.keySet() : new HashSet<>();
        for (String cn : columnNames) {
            if (StringUtils.isNotEmpty(cn) && cn.indexOf("=") > 0) {
                if (cn.indexOf("@") > 0) {
                    cn = cn.replaceAll(" ","");
                    String[] cStrs = cn.split("@");
                    Map<String, Object> column = new HashMap<>();
                    column.put("title", cStrs[0]);
                    String[] childrenStr = cStrs[1].split(":");
                    List<Map<String, Object>> children = new ArrayList<>();
                    for (String cs : childrenStr) {
                        Map<String, Object> child = new HashMap<>();
                        String[] childStrs = cs.split("=");
                        String title = childStrs[1];
                        if (childStrs[1].indexOf("#") >= 0){
                            title = title.replace("#","");
                            child.put("isRatio", "true");
                        }
                        child.put("title", title);
                        child.put("dataIndex", childStrs[0]);
                        child.put("key", childStrs[0]);
                        int finalI = i;
                        sparKeySet.forEach(sks -> {
                            if ("sorter".equals(sks)) {
                                if (sparMap.get("sorter") != null && sparMap.get("sorter").toString().indexOf(finalI +",") >= 0) {
                                    child.put("sorter",true);
                                }
                            } else if ("showProgress".equals(sks)) {
                                if (sparMap.get("showProgress") != null && sparMap.get("showProgress").toString().indexOf(finalI +",") >= 0) {
                                    child.put("showProgress",true);
                                }
                            } else if ("isRatio".equals(sks)) {
                                if (sparMap.get("isRatio") != null && sparMap.get("isRatio").toString().indexOf(finalI +",") >= 0) {
                                    child.put("isRatio",true);
                                }
                            } else if ("isAn".equals(sks)) {
                                if (sparMap.get("isAn") != null && sparMap.get("isAn").toString().indexOf(finalI +",") >= 0) {
                                    child.put("isAn",true);
                                }
                            }
                        });
                        children.add(child);
                    }
                    column.put("children", children);
                    columns.add(column);
                } else {
                    Map<String, Object> column = new HashMap<>();
                    cn = cn.replaceAll(" ","");
                    String[] cns = cn.split("=");
                    column.put("title", cns[1]);
                    column.put("dataIndex", cns[0]);
                    int finalI = i;
                    sparKeySet.forEach(sks -> {
                        if ("sorter".equals(sks)) {
                            if (sparMap.get("sorter") != null && sparMap.get("sorter").toString().indexOf(finalI +",") >= 0) {
                                column.put("sorter",true);
                            }
                        } else if ("showProgress".equals(sks)) {
                            if (sparMap.get("showProgress") != null && sparMap.get("showProgress").toString().indexOf(finalI +",") >= 0) {
                                column.put("showProgress","percent");
                            }
                        } else if ("isRatio".equals(sks)) {
                            if (sparMap.get("isRatio") != null && sparMap.get("isRatio").toString().indexOf(finalI +",") >= 0) {
                                column.put("isRatio",true);
                            }
                        } else if ("isAn".equals(sks)) {
                            if (sparMap.get("isAn") != null && sparMap.get("isAn").toString().indexOf(finalI +",") >= 0) {
                                column.put("isAn",true);
                            }
                        }
                    });
                    columns.add(column);
                }
            }
            i++;
        }
        return columns;
    }

    public static List<Map<String, Object>> buildHeaderChildrenColumns(String[] columnNames,String showProgress) {
        List<Map<String, Object>> columns = new ArrayList<>();
        int i = 0;
        for (String cn : columnNames) {
            if (StringUtils.isNotEmpty(cn) && cn.indexOf("=") > 0) {
                if (cn.indexOf("@") > 0) {
                    cn = cn.replaceAll(" ","");
                    String[] cStrs = cn.split("@");
                    Map<String, Object> column = new HashMap<>();
                    column.put("title", cStrs[0]);
                    String[] childrenStr = cStrs[1].split(":");
                    List<Map<String, Object>> children = new ArrayList<>();
                    for (String cs : childrenStr) {
                        Map<String, Object> child = new HashMap<>();
                        String[] childStrs = cs.split("=");
                        String title = childStrs[1];
                        if (childStrs[1].indexOf("#") >= 0){
                            title = title.replace("#","");
                            child.put("isRatio", "true");
                        }
                        child.put("title", title);
                        child.put("dataIndex", childStrs[0]);
                        child.put("key", childStrs[0]);
                        children.add(child);
                    }
                    column.put("children", children);
                    columns.add(column);
                } else {
                    Map<String, Object> column = new HashMap<>();
                    cn = cn.replaceAll(" ","");
                    String[] cns = cn.split("=");
                    column.put("title", cns[1]);
                    column.put("dataIndex", cns[0]);
                    column.put("key", cns[0]);
                    if (i == 2){
                        column.put("sorter",true);
                        column.put("showProgress",showProgress);
                    } else {
                        column.put("sorter", false);
                        column.put("showProgress", ShowProgressEnum.F.getValue());
                        column.put("isRatio", false);
                    }
                    if (i == 3) {
                        column.put("isAn",true);
                    } else {
                        column.put("isAn",false);
                    }
                    columns.add(column);
                }
            }
            i++;
        }
        return columns;
    }

    //获取去年的时间参数
    public static <T> T getLastYearParam(T obj, Class<T> clazz) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(obj));
        //去年参数
        String startTime = jsonObject.getString("dateStart");
        String endTime = jsonObject.getString("dateEnd");
        jsonObject.put("dateStart", DateUtil.getLastYearDate(startTime));
        jsonObject.put("dateEnd", DateUtil.getLastYearDate(endTime));
        return JSONObject.parseObject(jsonObject.toJSONString(), clazz);
    }


    //除法封装
    public static BigDecimal divideMethod(BigDecimal thisBeiChuShu, BigDecimal lastChuShu) {
        if (ObjectUtils.isEmpty(lastChuShu) || ObjectUtils.isEmpty(thisBeiChuShu)) {
            return BigDecimal.ZERO;
        }
        //今年有数据 去年没数据还是0
        if (thisBeiChuShu.compareTo(BigDecimal.ZERO) == 1 && lastChuShu.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }
        if (lastChuShu.compareTo(BigDecimal.ZERO) == 1) {
            BigDecimal tonBi = (thisBeiChuShu.subtract(lastChuShu)).multiply(new BigDecimal(100))
                    .divide(lastChuShu, 2, BigDecimal.ROUND_HALF_UP);
            return tonBi;
        }
        return BigDecimal.ZERO;
    }

    public static <T> Map sortDataAndTonBi(List<T> thisYearData, List<T> lastYearData
            , String areaKeyName, String areaNumber, Class<T> cls, Map<String, String> tonBiMap) {
        Map<String, JSONObject> lastYearMap = new HashMap<>();

        List lastEmptyList = Lists.newArrayList();
        String areaNameMapKey = "areaName";
        //今年数据量 少于去年数据量的时候,给今年数据加空值
        boolean thisLessLastFlag = false;
        if (thisYearData.size() < lastYearData.size() && thisYearData.size() <= 1) {
            thisLessLastFlag = true;
        }
        for (Object vo : lastYearData) {
            JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(vo));
            lastYearMap.put(obj.getString(areaKeyName), obj);
            //创建空的今年对象
            if (thisLessLastFlag && !areaNumber.equals(obj.getString(areaKeyName))) {
                Set<String> keys = obj.keySet();
                JSONObject dataJson = new JSONObject();
                dataJson.put(areaKeyName, obj.get(areaKeyName));
                dataJson.put(areaNameMapKey, obj.get(areaNameMapKey));
                for (String key : keys) {
                    if (!areaKeyName.equals(key) && !areaNameMapKey.equals(key) && !"sort".equals(key)) {
                        dataJson.put(key, 0);
                    }
                }
                lastEmptyList.add(JSONObject.parseObject(dataJson.toJSONString(), cls));
            }
        }
        if (thisLessLastFlag) {
            thisYearData.addAll(lastEmptyList);
        }
        if (ObjectUtils.isEmpty(areaNumber)) {
            areaNumber = "";
        }
        List list = Lists.newArrayList();
        List totalRecord = Lists.newArrayList();
        //对象计算同比
        int sort = 0;
        for (Object vo : thisYearData) {
            JSONObject thisObj = JSONObject.parseObject(JSONObject.toJSONString(vo));
            String thisYearAreaId = thisObj.getString(areaKeyName);
            JSONObject lastObj = lastYearMap.get(thisYearAreaId);
            thisObj.put("key", thisYearAreaId);
            if (ObjectUtils.isEmpty(lastObj)) {
                for (String tonBiKey : tonBiMap.keySet()) {
                    String tonBiKeyStr = tonBiMap.get(tonBiKey);
                    thisObj.put(tonBiKeyStr, BigDecimal.ZERO);
//                    if (thisObj.getBigDecimal(tonBiKey).compareTo(BigDecimal.ZERO) == 1) {
//                        thisObj.put(tonBiKeyStr, new BigDecimal(100));
//                    }else if (thisObj.getBigDecimal(tonBiKey).compareTo(BigDecimal.ZERO) == 0){
//                        thisObj.put(tonBiKeyStr, BigDecimal.ZERO);
//                    }else {
//                        thisObj.put(tonBiKeyStr, new BigDecimal(-100));
//                    }
                }
                if (areaNumber.equals(thisYearAreaId)) {
                    thisObj.put("key","total");
                    totalRecord.add(JSONObject.parseObject(thisObj.toJSONString(), cls));
                } else {
                    sort++;
                    thisObj.put("sort", sort);
                    list.add(JSONObject.parseObject(thisObj.toJSONString(), cls));
                }
                continue;
            }
            //处理同比
            for (String tonBiKey : tonBiMap.keySet()) {
                String tonBiKeyStr = tonBiMap.get(tonBiKey);
                thisObj.put(tonBiKeyStr, divideMethod(thisObj.getBigDecimal(tonBiKey), lastObj.getBigDecimal(tonBiKey)));
            }
            if (areaNumber.equals(thisYearAreaId)) {
                JSONObject.parseObject(thisObj.toJSONString(), cls);
                thisObj.put("key","total");
                totalRecord.add(JSONObject.parseObject(thisObj.toJSONString(), cls));
            } else {
                sort++;
                thisObj.put("sort", sort);
                list.add(JSONObject.parseObject(thisObj.toJSONString(), cls));
            }
        }

        Map<String, Object> result = new HashMap<>();
        if (ObjectUtils.isEmpty(list)) {
            //判断总计的数据是否都是0,如果都是0则修改总计属性值
            if (totalRecord.size() > 0) {
                JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(totalRecord.get(0)));
                JSONObject otherObj = obj;
                boolean flag = false;
                for (String keyName : obj.keySet()) {
                    if (keyName.equals("sort") || keyName.equals(areaKeyName)
                            || keyName.equals("key") || ObjectUtils.isEmpty(obj.get(keyName))) {
                        continue;
                    }
                    //判断是否是数字正则
                    String keyValue = obj.get(keyName).toString();
                    otherObj.put(keyName,"—");
                    if (pattern.matcher(keyValue).matches()) {
                        Double value = Double.parseDouble(keyValue);
                        if (value != 0) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    result.put("totalRecord", totalRecord);
                } else {
                    totalRecord = Lists.newArrayList();
                    totalRecord.add(otherObj);
                    result.put("totalRecord", totalRecord);
                }
            } else {
                result.put("totalRecord", totalRecord);
            }
        } else {
            result.put("totalRecord", totalRecord);
        }
        result.put("list", list);
        return result;
    }

    public static List<Map<String,Object>> sortAndTonbi(List<Map> fromList, List<Map> toList, Map<String,String> tonBiMap, String ymField, DateTypeEnum dateTypeEnum) {
        List<Map<String,Object>> list = new ArrayList<>();
        if (toList.size() == 0 && DateTypeEnum.YEAR.compareTo(dateTypeEnum) != 0) {
            fromList.forEach(fm -> {
                tonBiMap.keySet().forEach(tk -> {
                    fm.put(tonBiMap.get(tk),BigDecimal.ZERO.doubleValue());
                    list.add(fm);
                });
            });
        } else {
            fromList.forEach(fm -> {
                tonBiMap.keySet().forEach(ek -> {
                    if (fm.containsKey(ek) && fm.get(ek) != null) {
                        BigDecimal ekValue = new BigDecimal(fm.get(ek).toString());
                        Map<String,Object> lastFm = DateTypeEnum.YEAR.compareTo(dateTypeEnum) != 0 ? getLastMap(fm.get(ymField).toString(),ymField,toList):getLastMap((Integer.parseInt(fm.get(ymField).toString())) + "",ymField,fromList);
                        if (lastFm != null && StringUtils.isNotEmpty(lastFm.get(ek).toString())) {
                            BigDecimal lastEkValue = new BigDecimal(lastFm.get(ek).toString());
                            //BigDecimal tonBiValue = BigDecimal.ZERO.compareTo(lastEkValue) == 0 ? BigDecimal.ZERO:ekValue.subtract(lastEkValue).multiply(new BigDecimal(100)).divide(lastEkValue,2);
                            fm.put(tonBiMap.get(ek),lastEkValue);
                        } else {
                            fm.put(tonBiMap.get(ek),BigDecimal.ZERO);
                        }
                    } else {
                        fm.put(tonBiMap.get(ek),BigDecimal.ZERO);
                    }
                });
                list.add(fm);
            });
        }
        return list;
    }

    private static Map<String, Object> getLastMap(String ymValue,String ymField, List<Map> toList) {
        for (Map<String,Object> tm:toList) {
            if(tm.containsKey(ymField) && StringUtils.isNotEmpty(ymField) && ymValue.equals(tm.get(ymField).toString())) {
                return tm;
            }
        }
        return null;
    }

    public static Map<String,Object> familyPayTonBi(List thisYearData,List lastYearData
            ,String areaKeyName,String areaNumber,Class cls,Map<String,String> tonBiMap){
        Map<String,JSONObject> lastYearMap = new HashMap<>();
        for (Object vo: lastYearData){
            JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(vo));
            lastYearMap.put(obj.getString(areaKeyName),obj);
        }
        if (ObjectUtils.isEmpty(areaNumber)){
            areaNumber = "";
        }
        List list = Lists.newArrayList();
        List totalRecord = Lists.newArrayList();
        //对象计算
        int sort = 0;
        for (Object vo: thisYearData){
            JSONObject thisObj = JSONObject.parseObject(JSONObject.toJSONString(vo));
            String thisYearAreaId = thisObj.getString(areaKeyName);
            JSONObject lastObj = lastYearMap.get(thisYearAreaId);
            thisObj.put("key",thisYearAreaId);
            if (ObjectUtils.isEmpty(lastObj)){
                for (String tonBiKey: tonBiMap.keySet()){
                    String tonBiKeyStr = tonBiMap.get(tonBiKey);
                    thisObj.put(tonBiKeyStr,BigDecimal.ZERO);
                }
                if (areaNumber.equals(thisYearAreaId)){
                    totalRecord.add(JSONObject.parseObject(thisObj.toJSONString(),cls));
                }else{
                    sort ++;
                    thisObj.put("sort",sort);
                    thisObj.put("ybpm",sort);
                    thisObj.put("jcwspm",sort);
                    thisObj.put("grzfpm",sort);
                    list.add(JSONObject.parseObject(thisObj.toJSONString(),cls));
                }
                continue;
            }
            //处理同比
            for (String tonBiKey : tonBiMap.keySet()) {
                String tonBiKeyStr = tonBiMap.get(tonBiKey);
                thisObj.put(tonBiKeyStr, divideMethod(thisObj.getBigDecimal(tonBiKey), lastObj.getBigDecimal(tonBiKey)));
            }
            if (areaNumber.equals(thisYearAreaId)){
                JSONObject.parseObject(thisObj.toJSONString(),cls);
                totalRecord.add(JSONObject.parseObject(thisObj.toJSONString(),cls));
            }else{
                sort ++;
                thisObj.put("sort",sort);
                list.add(JSONObject.parseObject(thisObj.toJSONString(),cls));
            }
        }

        Map<String,Object> result = new HashMap<>();
        result.put("totalRecord",totalRecord);
        result.put("list",list);
        return result;
    }

    //获取 totalRecord
    public static <T> Map sortData(List<T> thisYearData
            , String areaKeyName, String areaNumber, Class<T> cls) {
        return sortDataAndTonBi(thisYearData, Lists.newArrayList(), areaKeyName, areaNumber,
                cls, new HashMap<>());
    }

    public static String rtrim(final String str, final String character) {
        if (null == str || "".equals(str)) {
            return str;
        }
        int i = str.length() - 1;
        while (i >= 0 && character.equals(String.valueOf(str.charAt(i)))) {
            i--;
        }
        return str.substring(0, i + 1);
    }

    public static String ltrim(final String str, final String character) {
        if (null == str || "".equals(str)) {
            return str;
        }
        int i = 0;
        while (i < str.length() && character.equals(String.valueOf(str.charAt(i)))) {
            i++;
        }
        return str.substring(i);
    }

    public static String countName(final Integer areaNumberLength) {
        switch (areaNumberLength) {
            case 2:
                return "全省";
            case 4:
                return "全市";
            case 5 | 6:
                return "全县区";
            case 7 | 8:
                return "全乡镇";
            case 9 | 10:
                return "全村";
            default:
        }
        return "";
    }

    public static Integer getAreaType(final Integer areaNumberLength) {
        switch (areaNumberLength) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 3;
            case 7:
                return 4;
            case 8:
                return 4;
            case 9:
                return 4;
            case 10:
                return 5;
            default:
                return 5;
        }
    }

    public static boolean checkIsCurrentYear(final String dateEnd) {
        if (StringUtils.isEmpty(dateEnd) || dateEnd.length() < 4) {
            return true;
        }
        Integer year = Integer.parseInt(dateEnd.substring(0, 4));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Integer currentYear = calendar.get(Calendar.YEAR);
        if (currentYear.equals(year)) {
            return true;
        }
        return false;
    }

    public static List extractTotalRecord(List list) {
        List totalRecordList = new ArrayList<>();
        if (list.size() > 0) {
            totalRecordList.add(list.get(list.size() - 1));
            list.remove(list.size() - 1);
        }
        return totalRecordList;
    }

    public static List extractTotalRecordAndSelf(List list, String areaNumber, String key) {
        List totalRecordList = new ArrayList<>();
        if (list.size() > 0) {
            totalRecordList.add(list.get(list.size() - 1));
            list.remove(list.size() - 1);
        }
        return totalRecordList;
    }

    public static List<Map> convertMatchRatio(List<Map> list, String[] keys) {
        for (Map lm : list) {
            for (String k : keys) {
/*                Integer molecule = 0;
                Integer denominator = 0;
                if(1 > Double.valueOf(lm.get("k").toString())) {

                }*/
                if (lm.containsKey(k) && null != lm.get(k)) {
                    StringBuilder sBuilder = new StringBuilder("1:").append(lm.get(k).toString());
                    lm.put(k, sBuilder.toString());
                }
            }
        }
        return list;
    }

    public static List<JSONObject> addEmptyChildren(List list, List<Map> areaList, AreaDeep areaDeep) {
        return addEmptyChildrenIndicateKeys(list, areaList, areaDeep, "", "",false,"—");
    }

    public static List<JSONObject> addEmptyChildrenOfNullList(List list, List<Map> areaList, AreaDeep areaDeep) {
        return addEmptyChildrenIndicateKeys(list, areaList, areaDeep, "", "",true,"—");
    }

    public static List<JSONObject> addImageEmptyChildrenOfNullList(List list, List<Map> areaList, AreaDeep areaDeep) {
        return addEmptyChildrenIndicateKeys(list, areaList, areaDeep, "", "",true,"0");
    }

    public static  List<JSONObject> addEmptyChildrenIndicateKeys(List list, List<Map> areaList, AreaDeep areaDeep, String areaNameKey,
                                                                    String areaIdKey,boolean ofNull,String nullValue) {
        if (list.size() == 0 || areaList.size() == 0 || list.size() == areaList.size() || areaDeep.getDeep() < Integer.parseInt(areaList.get(0).get("areaType").toString())) {
            return list;
        }
        int size = list.size();
        List<String> childrenAreaIds = new ArrayList<>();
        List<JSONObject> dataList = new ArrayList<>();
        Set<String> keys = new HashSet<>();
        int k = 0;
        int kk = 1;
        String parentId = areaList.get(0).get("parentId").toString();
        for (Object ol : list) {
            JSONObject dataJson = (JSONObject) JSON.toJSON(ol);
            if (dataJson.containsKey("key")) {
                childrenAreaIds.add(dataJson.getString("key"));
            }
            if (dataJson.containsKey("key")) {
                if (StringUtils.isNotEmpty(parentId) && parentId.equals(dataJson.getString("key"))) {

                } else {
                    dataList.add(dataJson);
                    dataJson.put("sort", kk);
                    kk++;
                }
            }
            if (k == 0) {
                keys = dataJson.keySet();
            }
            k++;
        }
        if (dataList.size() == 0 && !ofNull) {
            return new ArrayList<>();
        }
        int sort = size;
        String areaNameMapKey = "areaName";
        String areaIdMapKey = "key";
        if (StringUtils.isNotEmpty(areaNameKey)) {
            areaNameMapKey = areaNameKey;
        }
        if (StringUtils.isNotEmpty(areaIdKey)) {
            areaIdMapKey = areaIdKey;
        }
        for (Map map : areaList) {
            if (!childrenAreaIds.contains(map.get("areaId"))) {
                sort++;
                JSONObject dataJson = new JSONObject();
                dataJson.put("sort", sort);
                dataJson.put(areaIdMapKey, map.get("areaId"));
                dataJson.put(areaNameMapKey, map.get("areaName"));
                for (String key : keys) {
                    if (!areaIdMapKey.equals(key) && !areaNameMapKey.equals(key) && !"sort".equals(key)) {
                        dataJson.put(key, nullValue);
                    }
                }
                dataList.add(dataJson);
            }
        }
        return dataList;

    }
    public static <T> T buildQueryParam(T t, AreaDeep deep) {
        JSONObject tJSON = (JSONObject) JSON.toJSON(t);
        String areaNumber = tJSON.getString("areaNumber");
        if (StringUtils.isNotEmpty(areaNumber) && areaNumber.length() < 12) {
            areaNumber = areaNumber + "000000";
            tJSON.put("areaNumber", areaNumber);
        }
        Integer areaType = getAreaType(rtrim(areaNumber, "0").length());
        tJSON.put("areaType", areaType);
        if (deep.getDeep() <= areaType) {
            tJSON.put("orgDeep", "1");
        }
        switch (areaType) {
            case 1:
                tJSON.put("subLen", 4);
                tJSON.put("suffixZero", "00000000");
                break;
            case 2:
                tJSON.put("subLen", 6);
                tJSON.put("suffixZero", "000000");
                break;
            case 3:
                tJSON.put("subLen", 9);
                tJSON.put("suffixZero", "000");
                break;
            case 4:
                tJSON.put("subLen", 11);
                tJSON.put("suffixZero", "0");
                break;
            default:
                tJSON.put("strLen", 12);
                tJSON.put("suffixZero", "");
        }
        return (T) JSONObject.parseObject(tJSON.toJSONString(), t.getClass());
    }

    public static String compressDataUseLZString(String content) {
        return LZString.compressToBase64(content);
    }

    public static String decompressDataUseLZString(String content) {
        return LZString.decompressFromBase64(content);
    }

    public static String generateMd5StrByObject(Object o) {
        String className = new Exception().getStackTrace()[1].getClassName();
        String methodName = new Exception().getStackTrace()[1].getMethodName();
        System.out.println(className + ":" + methodName);
        JSONObject oJSON = (JSONObject) JSON.toJSON(o);
        oJSON.put("className",className);
        oJSON.put("methodName",methodName);
        return DigestUtils.md5DigestAsHex(oJSON.toJSONString().getBytes());
    }

    public static String generateMd5StrByObjectAndClassAndMethod(Object o,String className,String methodName) {
        JSONObject oJSON = (JSONObject) JSON.toJSON(o);
        oJSON.put("className",className);
        oJSON.put("methodName",methodName);
        return DigestUtils.md5DigestAsHex(oJSON.toJSONString().getBytes());
    }

    public static List<String> getAllRangeMonths(String startDate,String endDate) {
        List<String> monthList = Lists.newArrayList();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            //当前月份
            Date d1 = new SimpleDateFormat("yyyy-MM").parse(startDate);
            //定义起始日期
            Date d2 = new SimpleDateFormat("yyyy-MM").parse(endDate);
            //定义结束日期  可以去当前月也可以手动写日期。
            Calendar dd = Calendar.getInstance();
            //定义日期实例
            dd.setTime(d1);
            //设置日期起始时间
            while (dd.getTime().before(d2)) {
                //判断是否到结束日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                String str = sdf.format(dd.getTime());
                monthList.add(str);
                //System.out.println(str);
                //输出日期结果
                dd.add(Calendar.MONTH, 1);
                //进行当前日期月份加1
            }
            monthList.add(format.format(d2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return monthList;
    }

    public static List<String> getAllMonthsOfYear(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date d1 = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d1);
            calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-11);
            System.out.println(sdf.format(calendar.getTime()));
            return getAllRangeMonths(sdf.format(calendar.getTime()),date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static BigDecimal calculateUtil(BigDecimal a, BigDecimal b){
        BigDecimal percent =
                b == null ? BigDecimal.valueOf(0) :
                        b.compareTo(new BigDecimal(0)) == 0 ? BigDecimal.valueOf(0) :
                                a == null ? BigDecimal.valueOf(0) :
                                        a.multiply(new BigDecimal(100)).divide(b,2,BigDecimal.ROUND_HALF_UP);
        return percent;
    }

    public static List<Map> fillingMissingMonths(List<Map> months,List<Map> months2, String dateStart, String dateEnd, String group, String dateType) {
        List<Map> list = new ArrayList<>();
        for (String date = Dateformat(dateStart, dateType); date.compareTo(Dateformat(dateEnd, dateType)) <= 0 ; date = nextMonth(date, dateType)) {
            boolean flage = true, flage2 = true;
            Map map1 = null,map2 = null;
            for (Map month: months) {
                if (date.equals(month.get("x"))) {
                    flage = false;
                    map1 = month;
                    break;
                }
            }
            for (Map month2: months2) {
                if (lastYear(date).equals(month2.get("x"))) {
                    flage2 = false;
                    month2.put("x",date);
                    BigDecimal n = BigDecimal.ZERO;
                    if (!flage){
                        n = new BigDecimal(map1.get("yLeft").toString());
                    }
                    BigDecimal q;
                    if (month2.get("yRight")!=null && (q = new BigDecimal(month2.get("yRight").toString())).compareTo(BigDecimal.ZERO) != 0) {
                        DecimalFormat df = new DecimalFormat("#.00");
                        month2.put("yRight",n.subtract(q).multiply(new BigDecimal(100)).divide(q,2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    } else {
                        month2.put("yRight",0.);
                    }
                    map2 = month2;
                    break;
                }
            }
            if (flage) {
                map1 = new HashMap();
                map1.put("x", date);
                map1.put("yLeft", 0.);
                map1.put("group", group);
                list.add(map1);
            } else {
                list.add(map1);
            }

            if (flage2) {
                map2 = new HashMap();
                map2.put("x", date);
                map2.put("yRight", 0.);
                map2.put("group", "同比");
                list.add(map2);
            } else {
                list.add(map2);
            }
        }
        return list;
    }

    public static List<Map> fillingMissingYqm(List<Map> months, String dateStart, String dateEnd, String group, String dateType, String y) {
        List<Map> list = new ArrayList<>();
        for (String date = Dateformat(dateStart, dateType); date.compareTo(Dateformat(dateEnd, dateType)) <= 0 ; date = nextMonth(date, dateType)) {
            boolean flage = true;
            Map map1 = null;
            for (Map month: months) {
                if (date.equals(month.get("x"))) {
                    flage = false;
                    map1 = month;
                    break;
                }
            }
            if (flage) {
                map1 = new HashMap();
                map1.put("x", date);
                map1.put(y, 0.);
                map1.put("group", group);
                list.add(map1);
            } else {
                list.add(map1);
            }
        }
        return list;
    }

    private static String Dateformat(String date, String dateType){
        if ("year".equals(dateType)) {
            return date.substring(0,4);
        }
        if ("quarter".equals(dateType)){
            String[] dates = date.split("-");
            return dates[0] + "-" + ((Integer.parseInt(dates[1]) - 1)/3 + 1);
        }
        return date.substring(0,7);
    }


    //获取下一个月份，2019-01 -> 2019-02
    private static String nextMonth(String date, String dateType){
        String[] dates = date.split("-");
        if ("year".equals(dateType)) {
            return (Integer.parseInt(dates[0])+1) + "";
        }
        if (dates.length<2) {
            throw new RuntimeException("日期格式错误,日期为:" + date);
        }
        if ("quarter".equals(dateType)){
            return (Integer.parseInt(dates[0]) + (Integer.parseInt(dates[1])+1)/5) + "-" + ((Integer.parseInt(dates[1]))%4 + 1);
        }
        int n = Integer.parseInt(dates[0]) + (Integer.parseInt(dates[1])+1)/13;
        int y = Integer.parseInt(dates[1])%12 + 1;
        return n + "-" + String.format("%02d",y);
    }

    public static String subtractTime(DateTypeEnum dateTypeEnum,String dateStart) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(dateStart);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            switch (dateTypeEnum) {
                case MONTH:
                    calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-11);
                    break;
                case QUARTER:
                    calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-8);
                    break;
                case YEAR:
                    calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-4);
                    break;
                    default:
            }
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStart;
    }

    private static String lastYear(String date) {
        String[] dates = date.split("-");
        String re = (Integer.parseInt(dates[0])-1) + "";
        for (int i=1; i<dates.length; i++){
            re = re + "-" +dates[i];
        }
        return re;
    }

}
