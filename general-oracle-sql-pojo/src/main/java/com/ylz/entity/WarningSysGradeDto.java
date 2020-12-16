package com.ylz.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WarningSysGradeDto {
    private String warningId;
    private String orgGrade;

    public WarningSysGradeDto(String warningId, String orgGrade) {
        this.warningId = warningId;
        this.orgGrade = orgGrade;
    }

    public static List<WarningSysGradeDto> creartWaringSysGradeList(String warningId, List<String> orgIdList) {
        List<WarningSysGradeDto> list = new ArrayList<>(orgIdList.size());
        for (String orgId: orgIdList) {
            list.add(new WarningSysGradeDto(warningId, orgId));
        }
        return list;
    }
}
