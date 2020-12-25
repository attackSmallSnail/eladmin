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

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ylz.base.config.HistoryPK;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author lcw
* @date 2020-12-25
**/
@Entity
@Data
@IdClass(HistoryPK.class)
@Table(name="DEPT")
public class DeptSys implements Serializable {

    @Column(name = "DEPT_NAME")
    @ApiModelProperty(value = "科室名称")
    private String deptName;

    @Column(name = "ORG_NAME")
    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @Id
    @Column(name = "ORG_ID",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "机构id")
    private String orgId;

    @Column(name = "SORT_CODE")
    @ApiModelProperty(value = "排序码")
    private String sortCode;

    @Column(name = "HOSPITAL_ID")
    @ApiModelProperty(value = "分院")
    private String hospitalId;

    @Column(name = "DEPT_TYPE2")
    @ApiModelProperty(value = "临床科室分类 1-内科 2-外科 3-妇产 4-儿科 5-中医科 6-ICU  9-其他")
    private String deptType2;

    @Id
    @Column(name = "DEPT_ID",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "科室代码")
    private String deptId;

    @Column(name = "IS_DELETE")
    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    @Column(name = "DEPT_TYPE1")
    @ApiModelProperty(value = "科室类型 1-门诊科室 2-住院科室 3-护理病区 9-其他")
    private String deptType1;

    public void copy(DeptSys source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
