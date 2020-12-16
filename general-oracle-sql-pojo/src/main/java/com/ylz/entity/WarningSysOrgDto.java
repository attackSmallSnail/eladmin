package com.ylz.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WarningSysOrgDto {
    //预警id
    private String warningId;
    //机构id
    private String orgId;
    //机构名
    private String orgNmae;

    public WarningSysOrgDto() {}

    public WarningSysOrgDto(String warningId, String orgId) {
        this.warningId = warningId;
        this.orgId = orgId;
    }

    public static List<WarningSysOrgDto> creartWaringSysOrgList(String warningId, List<String> orgIdList) {
        List<WarningSysOrgDto> list = new ArrayList<>(orgIdList.size());
        for (String orgId: orgIdList) {
            list.add(new WarningSysOrgDto(warningId, orgId));
        }
        return list;
    }
}
