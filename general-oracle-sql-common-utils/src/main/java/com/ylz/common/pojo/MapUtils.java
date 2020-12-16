package com.ylz.common.pojo;


import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public Map map;


    public synchronized MapUtils put(String str,Object value) {
        if(this.map == null) {
            this.map = new HashMap<>();
        }
        this.map.put(str, value);
        return this;
    }

    public Map toMap(){
        return this.map;
    }

}
