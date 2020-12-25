/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package com.ylz.base.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author lcw
* @date 2020-12-25
**/
@Entity
@Data
@Table(name="SYS_AREA_FJZL")
public class SysAreaFjzl implements Serializable {

    @Column(name = "AREA_SHORT")
    @ApiModelProperty(value = "区域简称")
    private String areaShort;

    @Column(name = "AREA_TYPE")
    @ApiModelProperty(value = "区域类型")
    private String areaType;

    @Column(name = "AREA_PERSON")
    @ApiModelProperty(value = "区域负责人")
    private Integer areaPerson;

    @Column(name = "IN_USE")
    @ApiModelProperty(value = "是否使用")
    private String inUse;

    @Column(name = "AREA_NAME")
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @Column(name = "URBAN_RURAL")
    @ApiModelProperty(value = "规则")
    private String urbanRural;

    @Column(name = "PARENT_ID")
    @ApiModelProperty(value = "父级id")
    private String parentId;

    @Id
    @Column(name = "AREA_ID",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "区域id")
    private String areaId;

    public void copy(SysAreaFjzl source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
