package com.ylz.common.zhutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhBeanUtils {
    /*
     * @Description: List对象拷贝
     * @Author: Nomark
     * @Date: 2019/9/23 22:42
     * @Param: [list]
     * @Return: java.util.List
     */
    public static <T> List copyList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(list), list.get(0).getClass());
    }

    /*
     * @Description: Map 对象拷贝
     * @Author: Nomark
     * @Date: 2019/9/23 22:43
     * @Param: [map]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> copyMap(Map map) {
        return JSON.parseObject(JSON.toJSONString(map));
    }

    /*
     * @Description: 对象拷贝
     * @Author: Nomark
     * @Date: 2019/9/25 10:44
     * @Param: [obj, clazz]
     * @Return: T
     */
    public static <T> T copyObject(T obj, Class<T> clazz) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(obj));
        return JSONObject.parseObject(jsonObject.toJSONString(), clazz);
    }
}
