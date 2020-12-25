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
import com.ylz.base.domain.SysAreaFjzl;
import com.ylz.base.service.SysAreaFjzlService;
import com.ylz.base.service.dto.SysAreaFjzlQueryCriteria;
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
@Api(tags = "区域编码表,维护数据区域管理")
@RequestMapping("/api/sysAreaFjzl")
public class SysAreaFjzlController {

    private final SysAreaFjzlService sysAreaFjzlService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('sysAreaFjzl:list')")
    public void download(HttpServletResponse response, SysAreaFjzlQueryCriteria criteria) throws IOException {
        sysAreaFjzlService.download(sysAreaFjzlService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询区域编码表,维护数据区域")
    @ApiOperation("查询区域编码表,维护数据区域")
    @PreAuthorize("@el.check('sysAreaFjzl:list')")
    public ResponseEntity<Object> query(SysAreaFjzlQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(sysAreaFjzlService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增区域编码表,维护数据区域")
    @ApiOperation("新增区域编码表,维护数据区域")
    @PreAuthorize("@el.check('sysAreaFjzl:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody SysAreaFjzl resources){
        return new ResponseEntity<>(sysAreaFjzlService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改区域编码表,维护数据区域")
    @ApiOperation("修改区域编码表,维护数据区域")
    @PreAuthorize("@el.check('sysAreaFjzl:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody SysAreaFjzl resources){
        sysAreaFjzlService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除区域编码表,维护数据区域")
    @ApiOperation("删除区域编码表,维护数据区域")
    @PreAuthorize("@el.check('sysAreaFjzl:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        sysAreaFjzlService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
