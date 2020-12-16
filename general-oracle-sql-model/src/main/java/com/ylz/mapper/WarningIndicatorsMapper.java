package com.ylz.mapper;

import com.ylz.entity.*;
import com.ylz.req.WarningIndicatorsReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WarningIndicatorsMapper {
    //通过指标key来获取预警id
    List<WarningIndicatorsDto> getWarningByIndicatorsName(WarningIndicatorsDto warningIndicatorsDto);

    List<WarningIndicatorsDto> getWarningByIndicatorsName2(WarningIndicatorsDto warningIndicatorsDto);

    //获取指标的判定范围
    List<WarningIntervalDto> getWarningIntervalByWarningId(@Param("warningId") String warningId);
    //批量获取指标判定范围
    List<WarningIntervalDto> getWarningIntervalByWarningIds(List<String> list);

    //以指标key聚合判断限定值
    List<WarningIntervalDto> getWarningIntervalByWarnings(List<WarningIndicatorsDto> list);

    int saveWarningIndicators(WarningIndicatorsReq param);

    int saveWarningInterval(WarningIntervalDto warningIntervalDto);

    int saveWarningSysOrg(@Param("warningId") String warningId, @Param("orgId") String orgId);

    int saveWarningSysOrgList(List<WarningSysOrgDto> list);

    int saveWarningSysGradeList(List<WarningSysGradeDto> list);

    int saveWarningIntervalList(List<WarningIntervalDto> list);

    /**
     * 更新预警指标
     * @param param
     * @return
     */
    int updateWarningIndicators(WarningIndicatorsReq param);

    int updateUsedStatusById(WarningIndicatorsReq param);

    /**
     * 删除预警指标
     * @param warningId
     * @return
     */
    int removeWarningIndicators(@Param("warningId") String warningId);
    int removeWarningSysOrg(@Param("warningId") String warningId);
    int removeWarningSysGrade(@Param("warningId") String warningId);
    int removeWarningInterval(@Param("warningId") String warningId);


    WarningIndicatorsDto getWarningIndicatorsById(@Param("warningId") String warningId);

    List<SysOrg> listWarningSysOrgById(@Param("warningId") String warningId);

    List<String> listWarningSysGradeById(@Param("warningId") String warningId);
    /**
     * 列出全部预警指标
     * @return 全部预警指标
     */
    List<WarningIndicatorsDto> listAllWarningIndicators(WarningIndicatorsReq param);

    List<WarningIntervalDto> listWarningIntervalById(@Param("warningId") String warningId);

    List<String> getWarningNameByName(@Param("warningName") String warningName);

    //查询指标表，return 指标的key,指标名称
    List<IndicatorDto> listAllIndicators();

    int saveIndicators(IndicatorDto param);
}
