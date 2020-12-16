package com.ylz.common.pojo;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ylz.common.enums.CoordinateEnum;
import com.ylz.common.objutils.ObjectsUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OverviewObjectVo<T> {
    private String caliber; //统计口径
    private String title;   //标签
    private T data;    //数据值
    private String unit;   //单位
    private Double an;    //同比
    private String formula; //公式
    private String formula1; //公式1
    private String denominator; //如果有除法，分母是什么
    private boolean graphicUnit;  //图形单位
    private boolean important = false;  //是否重点字段
    private String describe; //描述
    private boolean customFormula = false;  //是否自定义公式
    private boolean isAnFlag = false;//同比是否开启
    private boolean orderBy = false;  //是否是排序字段
    private boolean groupBy = false;  //是否GroupBy字段
    private CoordinateEnum coordinate;  // 在图形返回的坐标轴
    //饼状图形
    private boolean isXPercent = false;//是否第二列
    private boolean isTotal = false;//是否总计
    private boolean isTotalAn = false;//是否是总计同比
    private String circleName;//饼图名称
    private Map<?, ?> object;
    private String status;
    private boolean isAvgData;//是否是人均值
    //分子公式
    private String molecularStr;
    //分母公式
    private String denominatorStr;

    public OverviewObjectVo(String title, String unit, String formula) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
    }

    public OverviewObjectVo(String title, String unit, String formula, Map<?, ?> object) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.object = object;
        if (ObjectsUtil.isNotEmpty(object) && object.containsKey("caliber")){
            CaliberVo map = new CaliberVo();
            BeanUtil.copyProperties(object,map);
            this.caliber = map.getCaliber();
            this.molecularStr = map.getMolecularStr();
            this.denominatorStr = map.getDenominatorStr();
        }
    }
    public OverviewObjectVo(String title, String unit, boolean isAnFlag, String formula) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.isAnFlag = isAnFlag;
    }

    public OverviewObjectVo(String title, String unit, boolean isAnFlag, String formula, boolean customFormula) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.isAnFlag = isAnFlag;
        this.customFormula = customFormula;
    }

    public OverviewObjectVo(String title, String unit, String formula, String denominator) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.denominator = denominator;
    }

    public OverviewObjectVo(String title, String unit, String formula, String denominator, Map<?, ?> object) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.denominator = denominator;
        this.object = object;
        if (ObjectsUtil.isNotEmpty(object) && object.containsKey("caliber")){
            CaliberVo map = new CaliberVo();
            BeanUtil.copyProperties(object,map);
            this.caliber = map.getCaliber();
            this.molecularStr = map.getMolecularStr();
            this.denominatorStr = map.getDenominatorStr();
        }
    }

    public OverviewObjectVo(String title, String unit, String formula, String denominator, boolean isAnFlag) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.denominator = denominator;
        this.isAnFlag = isAnFlag;
    }

    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.customFormula = customFormula;
    }

    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula, Map<?, ?> object) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.customFormula = customFormula;
        if (ObjectsUtil.isNotEmpty(object) && object.containsKey("caliber")){
            CaliberVo map = new CaliberVo();
            BeanUtil.copyProperties(object,map);
            this.caliber = map.getCaliber();
            this.molecularStr = map.getMolecularStr();
            this.denominatorStr = map.getDenominatorStr();
        }
        if (ObjectsUtil.isNotEmpty(object) && object.containsKey("groupBy")){
            this.groupBy = (Boolean) object.get("groupBy");
        }
    }

    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula,boolean orderBy) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.customFormula = customFormula;
        this.orderBy = orderBy;
    }

    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula, CoordinateEnum coordinate) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.customFormula = customFormula;
        this.coordinate = coordinate;
    }

    public OverviewObjectVo(String title, String unit, String formula, String denominator,String describe) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.denominator = denominator;
        this.describe = describe;
    }

    public OverviewObjectVo(String title, String unit, String formula, String denominator,String describe,Map<?, ?> object) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.denominator = denominator;
        this.describe = describe;
        if (ObjectsUtil.isNotEmpty(object) && object.containsKey("caliber")){
            CaliberVo map = new CaliberVo();
            BeanUtil.copyProperties(object,map);
            this.caliber = map.getCaliber();
            this.molecularStr = map.getMolecularStr();
            this.denominatorStr = map.getDenominatorStr();
        }
    }

    public OverviewObjectVo(String title, String unit, String formula, String denominator, boolean important, CoordinateEnum coordinate) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.denominator = denominator;
        this.important = important;
        this.coordinate =  coordinate;
    }

    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula, boolean important, CoordinateEnum coordinate,Map<?, ?> object) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.customFormula = customFormula;
        this.important = important;
        this.coordinate =  coordinate;
        this.object = object;
        if (ObjectsUtil.isNotEmpty(object) && object.containsKey("caliber")){
            CaliberVo map = new CaliberVo();
            BeanUtil.copyProperties(object,map);
            this.caliber = map.getCaliber();
            this.molecularStr = map.getMolecularStr();
            this.denominatorStr = map.getDenominatorStr();
        }
    }
    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula, boolean important, CoordinateEnum coordinate) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.customFormula = customFormula;
        this.important = important;
        this.coordinate =  coordinate;
    }

    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula, boolean important, boolean graphicUnit, CoordinateEnum coordinate) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.customFormula = customFormula;
        this.important = important;
        this.graphicUnit = graphicUnit;
        this.coordinate = coordinate;
    }

    public OverviewObjectVo(String title, String unit, String formula, String denominator, boolean important, boolean orderBy, boolean groupBy, CoordinateEnum coordinate) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.denominator = denominator;
        this.important = important;
        this.orderBy = orderBy;
        this.groupBy = groupBy;
        this.coordinate = coordinate;
    }

    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula, boolean important, boolean orderBy, boolean groupBy, CoordinateEnum coordinate) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.customFormula = customFormula;
        this.important = important;
        this.orderBy = orderBy;
        this.groupBy = groupBy;
        this.coordinate = coordinate;
    }

    public OverviewObjectVo(String title, String unit, String formula, String denominator, boolean important, boolean orderBy, boolean groupBy, boolean graphicUnit, CoordinateEnum coordinate) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.denominator = denominator;
        this.important = important;
        this.orderBy = orderBy;
        this.groupBy = groupBy;
        this.graphicUnit = graphicUnit;
        this.coordinate = coordinate;
    }

//    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula, boolean important, boolean orderBy, boolean groupBy) {
//        this.title = title;
//        this.unit = unit;
//        this.formula = formula;
//        this.customFormula = customFormula;
//        this.important = important;
//        this.orderBy = orderBy;
//        this.groupBy = groupBy;
//    }
//
    public OverviewObjectVo(String title, String unit, String formula, boolean customFormula, boolean important, boolean orderBy, boolean groupBy, boolean graphicUnit, CoordinateEnum coordinate) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.customFormula = customFormula;
        this.important = important;
        this.orderBy = orderBy;
        this.groupBy = groupBy;
        this.graphicUnit = graphicUnit;
        this.coordinate = coordinate;
    }
    /*
      饼图封装方法
     */
    public OverviewObjectVo(String title, String unit, boolean isAnFlag, String formula, boolean customFormula,boolean isXPercent,boolean isTotal) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.isAnFlag = isAnFlag;
        this.customFormula = customFormula;
        this.isXPercent = isXPercent;
        this.isTotal = isTotal;
    }

    public OverviewObjectVo(String title, String unit, boolean isAnFlag, String formula, boolean customFormula,boolean isXPercent,boolean isTotal,boolean isTotalAn) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.isAnFlag = isAnFlag;
        this.customFormula = customFormula;
        this.isXPercent = isXPercent;
        this.isTotal = isTotal;
        this.isTotalAn = isTotalAn;
    }

    public OverviewObjectVo(String title, String unit, boolean isAnFlag, String formula, boolean customFormula,boolean isXPercent,boolean isTotal,boolean isTotalAn,boolean isAvgData) {
        this.title = title;
        this.unit = unit;
        this.formula = formula;
        this.isAnFlag = isAnFlag;
        this.customFormula = customFormula;
        this.isXPercent = isXPercent;
        this.isTotal = isTotal;
        this.isTotalAn = isTotalAn;
        this.isAvgData = isAvgData;
    }

    public Map<String, Object> dataToPercent() {
        Map<String, Object> result = new HashMap<>();
        result.put("percent", this.data);
        result.put("title", this.title);
        result.put("caliber", this.caliber);
        result.put("an", null != this.an ? this.an : 0);
        return result;
    }

    public void appendCaliber(String data){
        StringBuffer newCaliber = new StringBuffer(this.caliber);
        newCaliber.append(data);
        this.caliber = newCaliber.toString();
    }
}
