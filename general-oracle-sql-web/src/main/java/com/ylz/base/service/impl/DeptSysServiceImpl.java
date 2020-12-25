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
package com.ylz.base.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ylz.base.domain.DeptSys;
import com.ylz.base.repository.DeptSysRepository;
import com.ylz.base.service.DeptSysService;
import com.ylz.base.service.dto.DeptSysDto;
import com.ylz.base.service.dto.DeptSysQueryCriteria;
import com.ylz.base.service.mapstruct.DeptSysMapper;
import com.ylz.utils.FileUtil;
import com.ylz.utils.PageUtil;
import com.ylz.utils.QueryHelp;
import com.ylz.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author lcw
* @date 2020-12-25
**/
@Service
@RequiredArgsConstructor
public class DeptSysServiceImpl implements DeptSysService {

    private final DeptSysRepository deptSysRepository;
    private final DeptSysMapper deptSysMapper;

    @Override
    public Map<String,Object> queryAll(DeptSysQueryCriteria criteria, Pageable pageable){
        Page<DeptSys> page = deptSysRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(deptSysMapper::toDto));
    }

    @Override
    public List<DeptSysDto> queryAll(DeptSysQueryCriteria criteria){
        return deptSysMapper.toDto(deptSysRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public DeptSysDto findById(String deptId) {
        DeptSys deptSys = deptSysRepository.findById(deptId).orElseGet(DeptSys::new);
        ValidationUtil.isNull(deptSys.getDeptId(),"Dept","deptId",deptId);
        return deptSysMapper.toDto(deptSys);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeptSysDto create(DeptSys resources) {
        resources.setDeptId(IdUtil.simpleUUID());
        return deptSysMapper.toDto(deptSysRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DeptSys resources) {
        DeptSys deptSys = deptSysRepository.findById(resources.getDeptId()).orElseGet(DeptSys::new);
        ValidationUtil.isNull( deptSys.getDeptId(),"Dept","id",resources.getDeptId());
        deptSys.copy(resources);
        deptSysRepository.save(deptSys);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String deptId : ids) {
            deptSysRepository.deleteById(deptId);
        }
    }

    @Override
    public void download(List<DeptSysDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DeptSysDto dept : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("科室名称", dept.getDeptName());
            map.put("机构名称", dept.getOrgName());
            map.put("排序码", dept.getSortCode());
            map.put("分院", dept.getHospitalId());
            map.put("临床科室分类 1-内科 2-外科 3-妇产 4-儿科 5-中医科 6-ICU  9-其他", dept.getDeptType2());
            map.put("是否删除", dept.getIsDelete());
            map.put("科室类型 1-门诊科室 2-住院科室 3-护理病区 9-其他", dept.getDeptType1());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
