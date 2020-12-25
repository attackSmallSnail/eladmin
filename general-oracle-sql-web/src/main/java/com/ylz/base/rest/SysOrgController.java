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
import com.ylz.base.domain.SysOrg;
import com.ylz.base.service.SysOrgService;
import com.ylz.base.service.dto.SysOrgQueryCriteria;
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
@Api(tags = "机构表管理")
@RequestMapping("/api/sysOrg")
public class SysOrgController {

    private final SysOrgService sysOrgService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('sysOrg:list')")
    public void download(HttpServletResponse response, SysOrgQueryCriteria criteria) throws IOException {
        sysOrgService.download(sysOrgService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询机构表")
    @ApiOperation("查询机构表")
    @PreAuthorize("@el.check('sysOrg:list')")
    public ResponseEntity<Object> query(SysOrgQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(sysOrgService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增机构表")
    @ApiOperation("新增机构表")
    @PreAuthorize("@el.check('sysOrg:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody SysOrg resources){
        return new ResponseEntity<>(sysOrgService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改机构表")
    @ApiOperation("修改机构表")
    @PreAuthorize("@el.check('sysOrg:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody SysOrg resources){
        sysOrgService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除机构表")
    @ApiOperation("删除机构表")
    @PreAuthorize("@el.check('sysOrg:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        sysOrgService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
