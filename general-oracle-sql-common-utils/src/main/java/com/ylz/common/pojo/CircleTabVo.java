package com.ylz.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CircleTabVo {
    private String x;   //x轴
    private Object y = 0d;    //y轴
    private String xPercent;//第二列x轴
    private Object yPercent = 0d;//第二列y轴
    private Object an = 0d;//同比字段
    private String formula;
    private Object avgData;//平均字段
    private String avgFormula;//平均值公式

    public CircleTabVo(String x, Object y, String xPercent, Object yPercent, Object an) {
        this.x = x;
        this.y = y;
        this.xPercent = xPercent;
        this.yPercent = yPercent;
        this.an = an;
    }
    public CircleTabVo(String x, Object y, String xPercent, Object yPercent) {
        this.x = x;
        this.y = y;
        this.xPercent = xPercent;
        this.yPercent = yPercent;
    }

    public CircleTabVo(Object avgData,String avgFormula){
        this.avgData = avgData;
        this.avgFormula = avgFormula;
    }

    public CircleTabVo(Object an) {
        this.an = an;
    }

    public CircleTabVo(String xPercent, Object yPercent) {
        this.xPercent = xPercent;
        this.yPercent = yPercent;
    }

    public CircleTabVo(String x, Object y, String formula) {
        this.x = x;
        this.y = y;
        this.formula = formula;
    }

    public CircleTabVo(){

    }
}
