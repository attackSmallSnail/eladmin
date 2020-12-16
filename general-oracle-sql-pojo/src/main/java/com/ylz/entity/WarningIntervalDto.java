package com.ylz.entity;

import lombok.Data;

/**
 * 指标预警规则
 */
@Data
public class WarningIntervalDto {
    //预警id
    private String warningId;
    //指标key
    private String indicatorsKey;
    //预警等级,现在只有正常和不正常
    private String warningGrade;
    //预警规则，等于: = ;不等于: != ;大于: > ;小于: < ;大于等于: >= ;小于等于: <= ;
    private String warningRule;
    //固定值
    private Double warningNumber;
    //
    private Integer countIntervalNum = 0;
}
