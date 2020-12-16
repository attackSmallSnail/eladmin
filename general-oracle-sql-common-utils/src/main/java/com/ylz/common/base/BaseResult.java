package com.ylz.common.base;

import com.ylz.common.enums.BaseResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class  BaseResult<T> implements Serializable {

    private Integer code;

    private boolean success;

    private String message;

    private T entity;

    public boolean isSuccess() {
        return BaseResultEnum.SUCCESS.getCode() == this.code;
    }

}
