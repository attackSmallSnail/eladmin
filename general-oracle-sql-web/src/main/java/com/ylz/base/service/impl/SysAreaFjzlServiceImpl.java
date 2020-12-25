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
import com.ylz.base.domain.SysAreaFjzl;
import com.ylz.base.repository.SysAreaFjzlRepository;
import com.ylz.base.service.SysAreaFjzlService;
import com.ylz.base.service.dto.SysAreaFjzlDto;
import com.ylz.base.service.dto.SysAreaFjzlQueryCriteria;
import com.ylz.base.service.mapstruct.SysAreaFjzlMapper;
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
public class SysAreaFjzlServiceImpl implements SysAreaFjzlService {

    private final SysAreaFjzlRepository sysAreaFjzlRepository;
    private final SysAreaFjzlMapper sysAreaFjzlMapper;

    @Override
    public Map<String,Object> queryAll(SysAreaFjzlQueryCriteria criteria, Pageable pageable){
        Page<SysAreaFjzl> page = sysAreaFjzlRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(sysAreaFjzlMapper::toDto));
    }

    @Override
    public List<SysAreaFjzlDto> queryAll(SysAreaFjzlQueryCriteria criteria){
        return sysAreaFjzlMapper.toDto(sysAreaFjzlRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public SysAreaFjzlDto findById(String areaId) {
        SysAreaFjzl sysAreaFjzl = sysAreaFjzlRepository.findById(areaId).orElseGet(SysAreaFjzl::new);
        ValidationUtil.isNull(sysAreaFjzl.getAreaId(),"SysAreaFjzl","areaId",areaId);
        return sysAreaFjzlMapper.toDto(sysAreaFjzl);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysAreaFjzlDto create(SysAreaFjzl resources) {
        resources.setAreaId(IdUtil.simpleUUID());
        return sysAreaFjzlMapper.toDto(sysAreaFjzlRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysAreaFjzl resources) {
        SysAreaFjzl sysAreaFjzl = sysAreaFjzlRepository.findById(resources.getAreaId()).orElseGet(SysAreaFjzl::new);
        ValidationUtil.isNull( sysAreaFjzl.getAreaId(),"SysAreaFjzl","id",resources.getAreaId());
        sysAreaFjzl.copy(resources);
        sysAreaFjzlRepository.save(sysAreaFjzl);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String areaId : ids) {
            sysAreaFjzlRepository.deleteById(areaId);
        }
    }

    @Override
    public void download(List<SysAreaFjzlDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysAreaFjzlDto sysAreaFjzl : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("区域名称", sysAreaFjzl.getAreaName());
            map.put("父级id", sysAreaFjzl.getParentId());
            map.put("规则", sysAreaFjzl.getUrbanRural());
            map.put("区域负责人", sysAreaFjzl.getAreaPerson());
            map.put("是否使用", sysAreaFjzl.getInUse());
            map.put("区域简称", sysAreaFjzl.getAreaShort());
            map.put("区域类型", sysAreaFjzl.getAreaType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
