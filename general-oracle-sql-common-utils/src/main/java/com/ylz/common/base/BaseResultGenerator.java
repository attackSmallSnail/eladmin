package com.ylz.common.base;


import com.ylz.common.constants.ResultConstants;
import com.ylz.common.enums.BaseResultEnum;
import com.ylz.common.exception.BusinessException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class BaseResultGenerator {

    public static <T> BaseResult<T> generate(final int code, final String message, T data) {
        return new BaseResult<>(code, false, message, data);
    }


    public static <T> BaseResult<T> success(final String message, final T data) {
        return new BaseResult<>(BaseResultEnum.SUCCESS.getCode(), true, message, data);
    }

    public static <T> BaseResult<T> success(final T data) {
        return new BaseResult<>(BaseResultEnum.SUCCESS.getCode(), true, BaseResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> BaseResult<T> success4Message(final String message,T data) {
        return new BaseResult<>(BaseResultEnum.SUCCESS.getCode(), true, message, data);
    }

    public static <T> BaseResult<T> failure() {
        BaseResult<T> tBaseResult =(BaseResult<T>) new BaseResult<>(BaseResultEnum.FAIL.getCode(), false, BaseResultEnum.FAIL.getMessage(), new HashMap<>());
        return tBaseResult;
    }
    public static <T> BaseResult<T> failure(String message) {
        BaseResult<T> tBaseResult = (BaseResult<T>) new BaseResult<>(BaseResultEnum.FAIL.getCode(), false, message, new HashMap<>());
        return tBaseResult;
    }

    public static <T> BaseResult<T> failure(final int code, final String message) {
         BaseResult<T> tBaseResult = (BaseResult<T>)  new BaseResult<>(code, false, message, new HashMap<>());
        return tBaseResult;
    }

    public static <T> BaseResult<T> failureLoginOut() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(BaseResultEnum.NOT_AUTH.getCode());
        BaseResult<T> tBaseResult = (BaseResult<T>)  new BaseResult<>(BaseResultEnum.NOT_AUTH.getCode(), false, ResultConstants.NOT_FOUND_USER, new HashMap<>());
        return tBaseResult;
    }

    public static <T> BaseResult<T> failure(final BaseResultEnum baseResultEnum,T data) {
        return new BaseResult<>(baseResultEnum.getCode(), false, baseResultEnum.getMessage(), data);
    }

    public static <T> BaseResult<T> failure(final String message,T data) {
        return new BaseResult<>(BaseResultEnum.FAIL.getCode(), false, message, data);
    }

    public static <T> BaseResult<T> error(String message) {
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//        response.setStatus(BaseResultEnum.SERVER_ERROR.getCode());
        BaseResult<T> tBaseResult = (BaseResult<T>) new BaseResult<>(BaseResultEnum.SERVER_ERROR.getCode(), false,  message, new HashMap<>());
        return tBaseResult;
    }
    public static <T> BaseResult<T> error() {
        BaseResult<T> tBaseResult = (BaseResult<T>) new BaseResult<>(BaseResultEnum.SERVER_ERROR.getCode(), false,BaseResultEnum.SERVER_ERROR.getMessage(), new HashMap<>());
        return tBaseResult;
    }
    public static <T> BaseResult<T> error(final int code, final String message,T data) {
        return new BaseResult<>(code, false, message, data);
    }

    public static <T> BaseResult<T> error(final BaseResultEnum baseResultEnum,T data) {
        return new BaseResult<>(baseResultEnum.getCode(), false, baseResultEnum.getMessage(), data);
    }

    public static <T> BaseResult<T> error(final BusinessException be, T data) {
        return new BaseResult<>(BaseResultEnum.SERVER_ERROR.getCode(), false, be.getErrorMessage(), data);
    }

    public static <T> BaseResult<T> error(final String message,T data) {
        return new BaseResult<>(BaseResultEnum.SERVER_ERROR.getCode(), false, message, data);
    }

}
