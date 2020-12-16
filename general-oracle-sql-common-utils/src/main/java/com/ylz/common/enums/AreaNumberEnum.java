package com.ylz.common.enums;

import org.springframework.util.ObjectUtils;

public enum AreaNumberEnum {

    AREA_PROVIDE("1","00000000",4,"省级"),
    AREA_CITY("2","000000",6,"市级"),
    AREA_COUNTY("3","000",9,"县级"),
    AREA_TOWN_SHIP("4","0",11,"乡镇"),
    AREA_ROAD("5","",12,"街道")
    ;

    private String code;

    private String value;

    private Integer num;

    private String note;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    AreaNumberEnum(String code, String value, Integer num, String note) {
        this.code = code;
        this.value = value;
        this.note = note;
        this.num = num;
    }

    public static AreaNumberEnum getAreaSub(String code){
        //返回不截取字段
        if (ObjectUtils.isEmpty(code)){
            return AreaNumberEnum.AREA_TOWN_SHIP;
        }
        for (AreaNumberEnum areaNumberEnum: AreaNumberEnum.values()){
            if (areaNumberEnum.code.equals(code)){
                return areaNumberEnum;
            }
        }
        //返回不截取字段
        return AreaNumberEnum.AREA_TOWN_SHIP;
    }


}
