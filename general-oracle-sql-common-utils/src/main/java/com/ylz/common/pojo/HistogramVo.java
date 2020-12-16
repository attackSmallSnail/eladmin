package com.ylz.common.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HistogramVo {
    private String x;   //x轴

    private Object y;    //y轴 柱状

    private Object yLeft;  //y

    private Object yRight;  //y

    private String group;  //分组

    public HistogramVo(){};

    public HistogramVo(String x, Object y, String group) {
        this.x = x;
        this.y = y;
        this.group = group;
    }

    public HistogramVo(String x, Object y, Object yLeft, String group) {
        this.x = x;
        this.y = y;
        this.group = group;
        this.yLeft = yLeft;
    }

    public HistogramVo(String x, Object y, Object yLeft, Object yRight, String group) {
        this.x = x;
        this.y = y;
        this.group = group;
        this.yLeft = yLeft;
        this.yRight = yRight;
    }

    public HistogramVo(String x, Object y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Object getY() {
        return y;
    }

    public void setY(Object y) {
        this.y = y;
    }

    public Object getyLeft() {
        return yLeft;
    }

    public void setyLeft(Object yLeft) {
        this.yLeft = yLeft;
    }

    public Object getyRight() {
        return yRight;
    }

    public void setyRight(Object yRight) {
        this.yRight = yRight;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
