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
package com.ylz.base.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author lcw
* @date 2020-12-25
**/
@Data
public class SysAreaFjzlDto implements Serializable {

    /** 区域名称 */
    private String areaName;

    /** id */
    private String areaId;

    /** 父级id */
    private String parentId;

    /** 规则 */
    private String urbanRural;

    /** 区域负责人 */
    private Integer areaPerson;

    /** 是否使用 */
    private String inUse;

    /** 区域简称 */
    private String areaShort;

    /** 区域类型 */
    private String areaType;
}