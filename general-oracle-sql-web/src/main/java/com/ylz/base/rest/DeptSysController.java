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
package com.ylz.base.rest;

import com.ylz.annotation.Log;
import com.ylz.base.domain.DeptSys;
import com.ylz.base.service.DeptSysService;
import com.ylz.base.service.dto.DeptSysQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @website https://el-admin.vip
* @author lcw
* @date 2020-12-25
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "分级查询部门表管理")
@RequestMapping("/api/deptSys")
public class DeptSysController {

    private final DeptSysService deptSysService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('dept:list')")
    public void download(HttpServletResponse response, DeptSysQueryCriteria criteria) throws IOException {
        deptSysService.download(deptSysService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询分级查询部门表")
    @ApiOperation("查询分级查询部门表")
    @PreAuthorize("@el.check('dept:list')")
    public ResponseEntity<Object> query(DeptSysQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(deptSysService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增分级查询部门表")
    @ApiOperation("新增分级查询部门表")
    @PreAuthorize("@el.check('dept:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody DeptSys resources){
        return new ResponseEntity<>(deptSysService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改分级查询部门表")
    @ApiOperation("修改分级查询部门表")
    @PreAuthorize("@el.check('dept:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody DeptSys resources){
        deptSysService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除分级查询部门表")
    @ApiOperation("删除分级查询部门表")
    @PreAuthorize("@el.check('dept:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        deptSysService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
