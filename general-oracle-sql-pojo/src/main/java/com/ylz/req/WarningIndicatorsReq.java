package com.ylz.req;

import com.ylz.common.objutils.ListUtils;
import com.ylz.entity.WarningIntervalDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@ApiModel(value = "指标预警",description = "指标预警")
public class WarningIndicatorsReq {


    @ApiModelProperty(required = false,hidden = true,notes = "预警id")
    private String warningId;

    @NotNull(message = "预警名称不能为空")
    @ApiModelProperty(value = "预警名称",example = "测试",required = true)
    private String warningName;

    @NotNull(message = "指标关键字不能为空")
    @ApiModelProperty(value = "指标的key",example = "medicalTotalIncome",required = true)
    private String indicatorsKey;

    @ApiModelProperty(required = false,hidden = true,notes = "预警的指标的key集合")
    private List<String> indicatorsKeyList;

    @NotNull(message = "时间类型不能为空")
    @ApiModelProperty(value = "month：月度quarter：季度year：年度",example = "month",required = true)
    private String dateType;

    @ApiModelProperty(required = false,hidden = true,notes = "指标所在模块")
    private String indicatorsModule;

    @ApiModelProperty(required = false,hidden = true,notes = "指标名称")
    private String indicatorsName;

    @Length(max = 12,min = 0,message = "区域长度为12位")
    @ApiModelProperty(value = "需要预警的区域id",example = "440400000000",required = true)
    private String areaId;

    @ApiModelProperty(required = false,hidden = true,notes = "org_id list")
    private List<String> orgIdList;

    @ApiModelProperty(required = false,hidden = true,notes = "org_id str，自动计算")
    private String orgIdStr;

    @ApiModelProperty(value = "限制医院的范围，机构等级（orgGrade）和机构id（orgId）组成",example = "[0]",required = true)
    private String[] dataRangeList;

    @ApiModelProperty(required = false,hidden = true,notes = "需要预警的机构等级")
    private List<String> orgGradeList;

    @ApiModelProperty(required = false,hidden = true,notes = "机构等级Str,自动计算")
    private String orgGradeStr;

    @ApiModelProperty(required = false,hidden = true,notes = "创建者id")
    private String createUserId;

    @ApiModelProperty(required = false,hidden = true,notes = "创建者名称")
    private String createUserName;

    @ApiModelProperty(required = false,hidden = true,notes = "开始状态 1开启，0关闭")
    private String usedStatus;

    @ApiModelProperty(required = true,hidden = true,notes = "预警区间最小值",example = "0")
    private Double intervalMin;

    @ApiModelProperty(required = true,hidden = true,notes = "预警区间最大值",example = "1000")
    private Double intervalMax;

    @ApiModelProperty(required = false,hidden = true,notes = "预警限制")
    private List<WarningIntervalDto> warningIntervalList;

    @ApiModelProperty(required = false,hidden = true,notes = "1删除，0正常")
    private String isDel;

    @ApiModelProperty(required = false,hidden = true,notes = "单机构匹配")
    private String isSingleOrg = "0";

    @ApiModelProperty(required = false,hidden = true,notes = "创建时间")
    private String createTime;

    public void setDataRangeList(String[] dataRangeList) {
        List<String> orgIdList = new ArrayList<>();
        List<String> orgGradeList = new ArrayList<>();
        boolean all = false;
        for (String dataRange: dataRangeList) {
            if ("0".equals(dataRange)) {
                all = true;
                break;
            }
            if ("1".equals(dataRange) || "2".equals(dataRange) || "3".equals(dataRange)) {
                orgGradeList.add(dataRange);
            } else {
                orgIdList.add(dataRange);
            }
        }
        if (!all) {
            setOrgIdList(orgIdList);
            if (orgGradeList.indexOf("0") == -1) {
                setOrgGradeList(orgGradeList);
            }
            if (ListUtils.isNotEmpty(this.orgGradeList) || ListUtils.isNotEmpty(this.orgIdList)) {
                this.dataRangeList = dataRangeList;
            }
        }
    }

    public String[] getDataRangeList() {
        return dataRangeList;
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
