package com.ylz.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ylz.common.enums.ShowProgressEnum;
import com.ylz.common.objutils.DateUtil;
import com.ylz.common.objutils.ListUtils;
import com.ylz.dto.TabColumnDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @className: HealthPovertyReq
 * @author: lcw
 * @date: 2019/05/22 14:46
 * @Desc: :家庭医生团队构成
 * @Version: 1.0
 */
@Data
@ApiModel(value = "家庭医生团队构成",description = "家庭医生团队构成")
public class FamilyDoctorInfoReq {

    @JSONField(ordinal = 1)
    @NotNull(message = "时间类型不能为空")
    @ApiModelProperty(value = "month：月度quarter：季度year：年度",example = "month",required = true)
    private String dateType;

    @JSONField(ordinal = 3)
    @NotNull(message = "开始时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间",example = "2019-01-01",required = true)
    private String dateStart;

    @JSONField(ordinal = 4)
    @NotNull(message = "结束时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间",example = "2019-12-31",required = true)
    private String dateEnd;

    @ApiModelProperty(required = false,hidden = true,notes = "去年时间")
    private String lastDateStart;

    @ApiModelProperty(required = false,hidden = true,notes = "去年时间")
    private String lastDateEnd;

    @ApiModelProperty(required = false,hidden = true,notes = "上个月/上个季(使用于环比)")
    private String lastMonthDateStart;

    @ApiModelProperty(required = false,hidden = true,notes = "上个月/上个季(使用于环比)")
    private String lastMonthDateEnd;

    @ApiModelProperty(required = false,hidden = true,notes = "上个月/上个季(使用于环比)")
    private String lastCustomDateStart;

    @ApiModelProperty(required = false,hidden = true,notes = "上个月/上个季(使用于环比)")
    private String lastCustomDateEnd;

    @Length(max = 12,min = 0,message = "区域长度为12位")
    @ApiModelProperty(value = "区县或乡镇区域id",example = "440400000000",required = true)
    private String county;

    @Length(max = 24,min = 0,message = "orgId")
    @ApiModelProperty(value = "orgId",example = "440400000000",required = false,hidden = true)
    private String orgId;

    @ApiModelProperty(required = false,hidden = true,notes = "org_id list")
    private List<String> orgIdList;

    @ApiModelProperty(value = "5：所有医疗机构4：基层医疗机构3：县级及县级以上医院",example = "5",required = true)
    private String dataRange;

    @JSONField(ordinal = 2)
    @ApiModelProperty(value = "限制医院的范围，机构等级（orgGrade）和机构id（orgId）组成",example = "[0]",required = true)
    private String[] dataRangeList;

    @ApiModelProperty(required = false,hidden = true,notes = "截取地区字符串填补")
    private String subString;

    @ApiModelProperty(required = false,hidden = true,notes = "截取地区位数")
    private Integer subNum;

    @ApiModelProperty(required = false,hidden = true,notes = "区域层级")
    private String areaType;

    @ApiModelProperty(required = false,hidden = true,notes = "父级区域名称")
    private String parentName;

    @ApiModelProperty(required = false,hidden = true,notes = "是否查询到机构1是/0不是")
    private String orgFlag;

    @ApiModelProperty(required = false,hidden = true,notes = "是否查询到科室1是/0不是")
    private String deptFlag;

    @ApiModelProperty(required = false,hidden = true,notes = "机构等级1/等级一2/等级二/3等级三4/统筹区外5/统筹区内")
    private String orgGrade;

    @ApiModelProperty(required = false,hidden = true,example = "", notes = "药品编码")
    private String drugCode;

    @ApiModelProperty(required = false,hidden = true,example = "", notes = "药品名称")
    private String drugName;

    @ApiModelProperty(required = false,hidden = true,example = "", notes = "药品名称")
    private String drugMoney;

    @ApiModelProperty(required = false,hidden = true,notes = "机构等级列表")
    private List<String> orgGradeList;

    @ApiModelProperty(required = false,hidden = true,notes = "是否是民营机构1是/0不是")
    private String privateOrgFlag = "0";

    @ApiModelProperty(required = false,hidden = true,notes = "是否是民营机构和公立机构1是/0不是")
    private String privateAndPublicOrgFlag = "0";

    @ApiModelProperty(required = false, notes = "接收层级钻取参数2－地区 3、机构级 4、部门级")
    private String rank;

    @ApiModelProperty(required = false, notes = "时间是区间也是单月查询－current单月、单季、单年，range区间")
    private String searchRange;

    //通用公式的参数
    // tabColunmn:[
    // {fieldName:"数据表的字段名",alias:"字段名对应的别名"}
    // ]
    @ApiModelProperty(required = false,hidden = true,notes = "")
    private List<TabColumnDto> tabColumn;
    // formula:[
    // {fieldName:"可以对前面的字段别名写公式",alias:"公式对应的别名，主要为了输出***Vo中的字段"}
    // ]
    @ApiModelProperty(required = false,hidden = true,notes = "")
    private List<TabColumnDto> formula;
    //
    @ApiModelProperty(required = false,hidden = true,notes = "")
    private String tabName;

    @ApiModelProperty(required = false,hidden = true,notes = "")
    private String controllerName;
    //列名是否是用进度条
    @ApiModelProperty(required = false,hidden = true,notes = "")
    private String showProgress = ShowProgressEnum.P.getValue();

    @ApiModelProperty(required = false,hidden = true,notes = "临时存放数据")
    private String tempDateStart = "";

    @ApiModelProperty(required = false,hidden = false,notes = "人员类型:0=总人数，1=编制/在岗在册人员,2=合同/临聘人员,3=医护人员,4=高级职称人员,5=副高级职称人员,6=中级职称人员,7=执业医师数,8=注册护士数,9=医技人员,10=药师人员,11=离退休人数,12=信息化人员,13=卫生监督人员,14=党员数量,15=全科医生,16=精神科医生")
    private String peopleType;

    @ApiModelProperty(required = false,hidden = true,notes = "是否需要预警(1需要/0不需要)")
    private String needWaringFlag = "1";

    @ApiModelProperty(required = false,hidden = true,notes = "动态表后面增加的条件<表名,表条件>")
    private Map<String,String> filterSql;

    @ApiModelProperty(required = false,hidden = true,notes = "时间装换类型(yyyy年/mm月/q季度)")
    private String dateFormatType;

    @ApiModelProperty(required = false,hidden = true,notes = "时间范围内天数(365天年/30月/91季度)")
    private String dateNum = "365";

    @ApiModelProperty(required = false,hidden = true,notes = "所有的表格都有药品名称(1是/0否)")
    private String allTableHaveDrugNameFlag = "0";

    @ApiModelProperty(required = false,hidden = true,notes = "药品名单id")
    private String listManId;

    @ApiModelProperty(required = false,hidden = true,notes = "去年同比环比类型")
    private String lastType;


    public String getDateStart() {
        return dateStart;
    }

    public String getLastCustomDateStart() {
        return lastCustomDateStart;
    }

    public void setLastCustomDateStart(String lastCustomDateStart) {
        this.lastCustomDateStart = lastCustomDateStart;
    }

    public void setLastCustomDateStart(Integer monthNum) {
        this.lastCustomDateStart = DateUtil.getLastCustomMonth(this.dateStart,monthNum);
    }

    public String getLastCustomDateEnd() {
        return lastCustomDateEnd;
    }

    public void setLastCustomDateEnd(String lastCustomDateEnd) {
        this.lastCustomDateEnd = lastCustomDateEnd;
    }

    public void setLastCustomDateEnd(Integer monthNum) {
        this.lastCustomDateEnd =  DateUtil.getLastCustomMonth(this.dateEnd,monthNum);
    }

    public void setDateStart(String dateStart) {
//        this.lastDateStart = DateUtil.getLastYearDate(dateStart);
        this.dateStart = dateStart;
        if ("month".equals(this.dateType)){
            this.lastDateStart = DateUtil.getLastMonth(dateStart);
            this.lastMonthDateStart =  DateUtil.getLastMonth(dateStart);
            this.dateNum = "30";
            this.lastType =  "环比";
        }else if ("quarter".equals(this.dateType)){
            this.lastDateStart = DateUtil.getLastQuarter(dateStart);
            this.lastMonthDateStart =  DateUtil.getLastQuarter(dateStart);
            this.dateNum = "91";
            this.lastType =  "环比";
        }else{
            this.lastDateStart = DateUtil.getLastYearDate(dateStart);
            this.lastMonthDateStart =  DateUtil.getLastYearDate(dateStart);
            this.dateNum = "365";
            this.lastType =  "同比";
        }
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
//        this.lastDateEnd = DateUtil.getLastYearDate(dateEnd);
        this.dateEnd = dateEnd;
        this.dateFormatType = "yyyy";
        if ("month".equals(this.dateType)){
            this.lastDateEnd = DateUtil.getLastMonth(dateEnd);
            this.lastMonthDateEnd =  DateUtil.getLastMonth(dateEnd);
            this.dateFormatType =  "mm";
        }else if ("quarter".equals(this.dateType)){
            this.lastDateEnd = DateUtil.getLastQuarter(dateEnd);
            this.lastMonthDateEnd =  DateUtil.getLastQuarter(dateEnd);
            this.dateFormatType = "q";
        }else {
            this.lastDateEnd = DateUtil.getLastYearDate(dateEnd);
            this.lastMonthDateEnd =  DateUtil.getLastYearDate(dateEnd);
            this.dateFormatType = "yyyy";
        }
    }

    public void setGraphDateStart() {
        DateTime curentDateEnd = new DateTime(this.dateEnd);
        StringBuffer dateStr = new StringBuffer();
        if ("year".equals(this.dateType)) {
            dateStr.append(curentDateEnd.minusYears(4).getYear()).append("-01-01");
        }else{
            curentDateEnd = curentDateEnd.minusMonths(11);
            dateStr.append(curentDateEnd.toString("yyyy-MM")).append("-01");
        }
        this.tempDateStart = this.dateStart;
        this.dateStart = dateStr.toString();
    }

    public void resetGraphDateStart() {
        this.dateStart = this.tempDateStart;
    }

    public void setDataRangeList(String[] dataRangeList) {
        List<String> orgIdList = new ArrayList<>();
        List<String> orgGradeList = new ArrayList<>();
        boolean all = false;
        for (String dataRange: dataRangeList) {
            if ("0".equals(dataRange)) {
                all = true;
                break;
            }
            //1/2/3级医疗机构  9专科机构
            if ("1".equals(dataRange) || "2".equals(dataRange) || "3".equals(dataRange) || "9".equals(dataRange) ) {
                orgGradeList.add(dataRange);
            } else {
                orgIdList.add(dataRange);
            }
        }
        if (!all) {
            this.orgIdList = orgIdList;
            if (orgGradeList.indexOf("0") == -1) {
                this.orgGradeList = orgGradeList;
            }
            if (ListUtils.isNotEmpty(this.orgGradeList) || ListUtils.isNotEmpty(this.orgIdList)) {
                this.dataRangeList = dataRangeList;
            }
        }
    }

    public String[] getDataRangeList() {
        return dataRangeList;
    }
}
