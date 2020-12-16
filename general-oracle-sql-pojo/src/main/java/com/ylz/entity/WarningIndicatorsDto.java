package com.ylz.entity;

import com.ylz.common.objutils.ListUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * 指标预警
 */
@Data
public class WarningIndicatorsDto {
    //预警id,后台生成UUID
    private String warningId;
    //预警名称
    private String warningName;
    //需要预警的指标的key
    private String indicatorsKey;
    //需要预警的指标的key集合
    private List<String> indicatorsKeyList;
    //指标所在模块
    private String indicatorsModule;
    //指标名称
    private String indicatorsName;
    //预警周期，month：月度quarter：季度year：年度
    private String dateType;
    //需要预警的区域id
    private String areaId;
    //需要预警的区域名称
    private String areaName;
    //需要预警的机构等级
    private List<String> orgGradeList;
    //需要预警的机构id
    private List<String> orgIdList;
    //需要预警的机构
    private List<SysOrg> sysOrgList;
    //创建者id
    private String createUserId;
    //创建者名称
    private String createUserName;
    //开始状态 1开启，0关闭
    private String usedStatus;
    //预警区间最小值
    private Double intervalMin;
    //预警区间最大值
    private Double intervalMax;
    //预警限制
    private List<WarningIntervalDto> warningIntervalList;
    //1删除，0正常
    private String isDel;
    //
    private String orgGradeStr;
    //
    private String orgIdStr;
    //
    private String isSingleOrg;
    //
    private String[] dataRangeList;

    private int countIntervalNum;


    public void setLonMonth(int lon, String dateType) {
        if ("month".equals(dateType) && lon <= 1) {
            this.dateType = "month";
        } else if ("quarter".equals(dateType) && lon <= 3) {
            this.dateType = "quarter";
        } else if ("year".equals(dateType) && lon <= 12) {
            this.dateType = "year";
        }
    }

    public void setOrgIdList(List<String> orgIdList) {
        this.orgIdList = orgIdList;
        if (ListUtils.isNotEmpty(orgIdList)) {
            Collections.sort(orgIdList);
            this.orgIdStr = StringUtils.join(orgIdList,",");
        }
    }

    public void  setOrgGradeList(List<String> orgGradeList) {
        this.orgGradeList = orgGradeList;
        if (ListUtils.isNotEmpty(orgGradeList)) {
            Collections.sort(orgGradeList);
            this.orgGradeStr = StringUtils.join(orgGradeList,",");
        }
    }
}
