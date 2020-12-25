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
public class DeptSysDto implements Serializable {

    /** 科室名称 */
    private String deptName;

    /** 机构名称 */
    private String orgName;

    /** 机构id */
    private String orgId;

    /** 排序码 */
    private String sortCode;

    /** 分院 */
    private String hospitalId;

    /** 临床科室分类 1-内科 2-外科 3-妇产 4-儿科 5-中医科 6-ICU  9-其他 */
    private String deptType2;

    /** 科室代码 */
    private String deptId;

    /** 是否删除 */
    private String isDelete;

    /** 科室类型 1-门诊科室 2-住院科室 3-护理病区 9-其他 */
    private String deptType1;
}
