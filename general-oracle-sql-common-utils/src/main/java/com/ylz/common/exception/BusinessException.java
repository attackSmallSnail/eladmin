package com.ylz.common.exception;

import com.ylz.common.enums.BaseResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private int errorCode = BaseResultEnum.SERVER_ERROR.getCode();
    private String errorMessage;

    public BusinessException(String errorMessage) {
        this.errorCode = BaseResultEnum.SERVER_ERROR.getCode();
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorCode = BaseResultEnum.SERVER_ERROR.getCode();
    }

    public BusinessException(int errorCode, String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorCode = errorCode;
    }

}
