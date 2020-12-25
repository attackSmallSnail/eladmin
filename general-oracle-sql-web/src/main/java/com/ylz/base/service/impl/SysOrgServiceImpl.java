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
import com.ylz.base.domain.SysOrg;
import com.ylz.base.repository.SysOrgRepository;
import com.ylz.base.service.SysOrgService;
import com.ylz.base.service.dto.SysOrgDto;
import com.ylz.base.service.dto.SysOrgQueryCriteria;
import com.ylz.base.service.mapstruct.SysOrgMapper;
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
public class SysOrgServiceImpl implements SysOrgService {

    private final SysOrgRepository sysOrgRepository;
    private final SysOrgMapper sysOrgMapper;

    @Override
    public Map<String,Object> queryAll(SysOrgQueryCriteria criteria, Pageable pageable){
        Page<SysOrg> page = sysOrgRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(sysOrgMapper::toDto));
    }

    @Override
    public List<SysOrgDto> queryAll(SysOrgQueryCriteria criteria){
        return sysOrgMapper.toDto(sysOrgRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public SysOrgDto findById(String orgId) {
        SysOrg sysOrg = sysOrgRepository.findById(orgId).orElseGet(SysOrg::new);
        ValidationUtil.isNull(sysOrg.getOrgId(),"SysOrg","orgId",orgId);
        return sysOrgMapper.toDto(sysOrg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysOrgDto create(SysOrg resources) {
        resources.setOrgId(IdUtil.simpleUUID());
        return sysOrgMapper.toDto(sysOrgRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysOrg resources) {
        SysOrg sysOrg = sysOrgRepository.findById(resources.getOrgId()).orElseGet(SysOrg::new);
        ValidationUtil.isNull( sysOrg.getOrgId(),"SysOrg","id",resources.getOrgId());
        sysOrg.copy(resources);
        sysOrgRepository.save(sysOrg);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String orgId : ids) {
            sysOrgRepository.deleteById(orgId);
        }
    }

    @Override
    public void download(List<SysOrgDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysOrgDto sysOrg : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("是否删除", sysOrg.getIsDelete());
            map.put("社保机构代码", sysOrg.getSocialOrgCode());
            map.put("机构代码", sysOrg.getOrgCode());
            map.put("机构级别（市 1 区县 2 医院/卫生院 3 卫生所4 卫生室5）", sysOrg.getOrgPostlevel());
            map.put("区域id", sysOrg.getAreaId());
            map.put("单位地址 地市", sysOrg.getAddressCity());
            map.put("是否全市机构（1是，0否）对应aa10（T_YesNo）", sysOrg.getIfqsjg());
            map.put("机构性质:1 中医院 2妇幼保健院 3综合医院 4 专科医院 99其他(对应sys_code 的org_property)", sysOrg.getOrgProperty());
            map.put("数据接入状态", sysOrg.getConnectStatus());
            map.put("新农合机构代码", sysOrg.getRuralOrgCode());
            map.put("创建时间", sysOrg.getCreateDate());
            map.put("是否公立机构（1是，0否）对应aa10（T_YesNo）", sysOrg.getIfgljg());
            map.put("联系人", sysOrg.getLinkman());
            map.put("是否改革（1是，0否）对应aa10（T_YesNo）", sysOrg.getIfgg00());
            map.put("父机构id", sysOrg.getParentId());
            map.put("机构地址", sysOrg.getAddress());
            map.put("是否全市所有基层医疗机构(1是，0否)对应aa10（T_YesNo）", sysOrg.getIfqsjc());
            map.put("卫健委机构id", sysOrg.getOrgIdWjw());
            map.put("单位地址 省", sysOrg.getAddressProvince());
            map.put("机构等级(对应sys_code的org_level)", sysOrg.getOrgLevel());
            map.put("机构简称", sysOrg.getShortName());
            map.put("电话", sysOrg.getTel());
            map.put("1已经接入 0未接入", sysOrg.getInFlag());
            map.put("职工医保机构代码", sysOrg.getStaffOrgCode());
            map.put("机构描述", sysOrg.getOrgDesc());
            map.put("机构编号", sysOrg.getSid());
            map.put("妇幼机构ID", sysOrg.getOrgIdFy());
            map.put("医院类型（对应基卫机构表的JGLB00）01:卫生管理部门； 02:医疗机构; 03:妇幼保健院; 04综合医院; 05:村卫生所 06:卫生服务站 07:民营医院", sysOrg.getOrgCategory());
            map.put("在同一级机构中的序号", sysOrg.getOrderNo());
            map.put("是否专科机构（1是，0否）", sysOrg.getIfzkjg());
            map.put("机构类型:1 市属 2 县级 3 基层 0 其他 5 村医", sysOrg.getOrgType());
            map.put("机构等级(1=一级/2=二级/3=三级)", sysOrg.getOrgGrade());
            map.put("医院等次，1、甲等，2、乙等，3、丙等，9、未评等", sysOrg.getHosOrder());
            map.put("机构名称", sysOrg.getOrgName());
            map.put("属于区县的id", sysOrg.getBelongCountyId());
            map.put("机构对应数据库用户", sysOrg.getOrgDbUser());
            map.put("机构类别,1机构,2部门", sysOrg.getOrgkind());
            map.put("居民健康档案机构id", sysOrg.getJmjkdaOrgid());
            map.put("区域名称", sysOrg.getAreaName());
            map.put("公卫机构id", sysOrg.getOrgIdGw());
            map.put("疾控中心机构id", sysOrg.getOrgIdJkzx());
            map.put("机构负责人", sysOrg.getPrincipal());
            map.put("单位地址 区县", sysOrg.getAddressCounty());
            map.put("开业时间", sysOrg.getFoundingTime());
            map.put("分管负责人", sysOrg.getChargeOfficer());
            map.put("机构对应数据库表空间名称", sysOrg.getOrgDbTablespace());
            map.put("邮编", sysOrg.getZipCode());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
