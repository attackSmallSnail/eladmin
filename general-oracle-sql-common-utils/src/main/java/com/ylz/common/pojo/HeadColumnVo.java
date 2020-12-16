package com.ylz.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ylz.common.enums.ShowProgressEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HeadColumnVo {
    private String dataIndex;  //列唯一标识，对应数据的字段名
    private String title;  //列标题
    private String unit;  //单位

    @JsonProperty("isRatio")
    private boolean isRatio = false; //是否是配比字段（接口只需返回冒号后面的数据，前端会拼接上"1:"）

    @JsonProperty("isAn")
    private boolean isAn = false; //是否是同比字段
    private String showProgress = ""; //是否需要展示进度条（值为"percent"时代表百分比数据）
    private boolean sorter = false;  //是否需要排序
    private String merge = "";   //合并列，取值为需要根据哪个字段合并的字段名
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<HeadColumnVo> children;  //分组

    public HeadColumnVo(String dataIndex, String title, String unit) {
        this.dataIndex = dataIndex;
        this.title = title;
        this.unit = unit;
    }

    public HeadColumnVo() {
    }

    public HeadColumnVo(String dataIndex, String title, String unit, ShowProgressEnum spEnum) {
        this.dataIndex = dataIndex;
        this.title = title;
        this.unit = unit;
        this.showProgress = spEnum.getValue();
    }

    public HeadColumnVo(String title, List<HeadColumnVo> children) {
        this.title = title;
        this.children = children;
    }

    public HeadColumnVo(String dataIndex, String title, String unit, String merge) {
        this.dataIndex = dataIndex;
        this.title = title;
        this.unit = unit;
        this.merge = merge;
    }

    public HeadColumnVo(String dataIndex, String title, String unit, boolean isRatio, boolean isAn) {
        this.dataIndex = dataIndex;
        this.title = title;
        this.unit = unit;
        this.isRatio = isRatio;
        this.isAn = isAn;
    }

    public HeadColumnVo(String dataIndex, String title, String unit, boolean isRatio, boolean isAn, String merge) {
        this.dataIndex = dataIndex;
        this.title = title;
        this.unit = unit;
        this.isRatio = isRatio;
        this.isAn = isAn;
        this.merge = merge;
    }


    public HeadColumnVo(String dataIndex, String title, String unit, boolean isRatio, boolean isAn, ShowProgressEnum spEnum, boolean sorter) {
        this.dataIndex = dataIndex;
        this.title = title;
        this.unit = unit;
        this.isRatio = isRatio;
        this.isAn = isAn;
        this.showProgress = spEnum.getValue();
        this.sorter = sorter;
    }

    public HeadColumnVo(String dataIndex, String title, String unit, boolean isRatio, boolean isAn, ShowProgressEnum spEnum, boolean sorter, String merge) {
        this.dataIndex = dataIndex;
        this.title = title;
        this.unit = unit;
        this.isRatio = isRatio;
        this.isAn = isAn;
        this.showProgress = spEnum.getValue();
        this.sorter = sorter;
        this.merge = merge;
    }

    @JsonProperty("isRatio")
    public boolean isRatio() {
        return isRatio;
    }

    public void setRatio(boolean ratio) {
        isRatio = ratio;
    }

    @JsonProperty("isAn")
    public boolean isAn() {
        return isAn;
    }

    public void setAn(boolean an) {
        isAn = an;
    }
}
