package com.ylz.common.enums;

import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Getter
public enum BaseResultEnum {
    SUCCESS(200, "请求成功"),
    FAIL(400, "操作失败"),
    NOT_FOUND(404, "请求不存在"),
    SERVER_ERROR(500, "服务异常"),
    NOT_AUTH(401,"未授权");

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    BaseResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T extends Enum<T>> T getByIntegerTypeCode(Class<T> clazz, String getTypeCodeMethodName, Integer typeCode){
        T result = null;
        try{
            //Enum接口中没有values()方法，我们仍然可以通过Class对象取得所有的enum实例
            T[] arr = clazz.getEnumConstants();
            //获取定义的方法
            Method targetMethod = clazz.getDeclaredMethod(getTypeCodeMethodName);
            Integer typeCodeVal = null;
            //遍历枚举定义
            for(T entity:arr){
                //获取传过来方法的
                typeCodeVal = Integer.valueOf(targetMethod.invoke(entity).toString());
                //执行的方法的值等于参数传过来要判断的值
                if(typeCodeVal.equals(typeCode)){
                    //返回这个枚举
                    result = entity;
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }
}
