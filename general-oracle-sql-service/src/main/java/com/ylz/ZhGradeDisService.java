package com.ylz;


import com.ylz.common.pojo.OverviewObjectVo;
import com.ylz.req.FamilyDoctorInfoReq;

import java.util.Map;

public interface ZhGradeDisService {

    Map<String,Object> gradeCockpit(FamilyDoctorInfoReq param);//概览
    Map<String, OverviewObjectVo> getHomeGradeMedical(FamilyDoctorInfoReq param);//驾驶舱,分级诊疗监管
    Map<String,Object> baseTreatment(FamilyDoctorInfoReq param);//基层医疗卫生机构诊疗人次数
    Map<String,Object> regionTreatment(FamilyDoctorInfoReq param);//区域总诊疗人次数
    Map<String,Object> baseTreatmentProportion(FamilyDoctorInfoReq param);//基层医疗卫生机构诊疗量占总诊疗量比例
    Map<String,Object> baseTreatmentGrowthRate(FamilyDoctorInfoReq param);//基层机构诊疗量同期增长率
    Map<String,Object> outpatientYearOnYear(FamilyDoctorInfoReq param);//各级医疗机构门(急)诊量同比
    Map<String,Object> leaveHospitalYearOnYear(FamilyDoctorInfoReq param);//各级医疗机构出院人数同比
    Map<String,Object> anotherPlaceTreatment(FamilyDoctorInfoReq param);//异地就诊率(%)
    Map<String,Object> regionTreatmentRate(FamilyDoctorInfoReq param);//区域内就诊率(%)
    Map<String,Object> downward(FamilyDoctorInfoReq param);//上级医疗机构下转患者总例次数(门诊)
    Map<String,Object> upward(FamilyDoctorInfoReq param);//上级医疗机构下转患者总例次数(住院)
    Map<String,Object> downwardToBase(FamilyDoctorInfoReq param);//二、三级医院向基层医疗卫生机构转诊人数
    Map<String,Object> downwardToBaseRate(FamilyDoctorInfoReq param);//二、三级医院向基层医疗卫生机构转诊人数同期增长率
    Map<String,Object> upwardToHospitalsOutpatient(FamilyDoctorInfoReq param);//基层医疗卫生机构上转患者总例次数(门诊)
    Map<String,Object> upwardToHospitalsHospitalization(FamilyDoctorInfoReq param);//基层医疗卫生机构上转患者总例次数(住院)
    Map<String,Object> upwardToHospitalsNumberRate(FamilyDoctorInfoReq param);//基层医疗卫生机构向上级医院转诊病人占比(%)
    Map<String,Object> expertDown(FamilyDoctorInfoReq param);//专家号源下放基层比例
    Map<String,Object> hypertensionManagement(FamilyDoctorInfoReq param);//城市高血压患者规范化诊疗和管理人数
    Map<String,Object> specificationHypertension(FamilyDoctorInfoReq param);//城市高血压患者规范化诊疗和管理率(%)
    Map<String,Object> diabetesManagement(FamilyDoctorInfoReq param);//城市糖尿病患者规范化诊疗和管理人数
    Map<String,Object> specificationDiabetesRate(FamilyDoctorInfoReq param);//城市糖尿病患者规范化诊疗和管理率(%)
    Map<String,Object> telemedicineRegion(FamilyDoctorInfoReq param);//远程医疗服务覆盖地区范围
    Map<String,Object> telemedicineNumber(FamilyDoctorInfoReq param);//开展远程医疗服务公立医院数
    Map<String,Object> telemedicinePublicHospitals(FamilyDoctorInfoReq param);//开展远程医疗服务公立医院占比
    Map<String,Object> telemedicineNumberOfServices(FamilyDoctorInfoReq param);//开展远程医疗服务人次数
    Map<String,Object> familyDoctorNumber(FamilyDoctorInfoReq param);//开展签约服务的家庭医生数(人)
    Map<String,Object> familyDoctorCoverage(FamilyDoctorInfoReq param);//家庭医生签约服务覆盖率(%)
    Map<String,Object> keyPopulationCoverage(FamilyDoctorInfoReq param);//重点人群签约服务覆盖率(%)
    Map<String,Object> clinicalPathway(FamilyDoctorInfoReq param);//实施临床路径管理的病例数
    Map<String,Object> clinicalPathwayRate(FamilyDoctorInfoReq param);//临床路径管理的住院病人占比(%)
    Map<String,Object> drgs(FamilyDoctorInfoReq param);//实行按病种付费的病种数
    Map<String,Object> chargedByDisease(FamilyDoctorInfoReq param);//实行按病种收费的病种数
    Map<String,Object> daytimeOperationRate(FamilyDoctorInfoReq param);//开展日间手术的公立医院占比
    Map<String,Object> daytimeOperation(FamilyDoctorInfoReq param);//开展日间手术例数(例次)
    Map<String,Object> downturnTechnologyNumbers(FamilyDoctorInfoReq param);//二级及以上医院下派基层专业技术人员数
    Map<String,Object> downturnTechnologyRate(FamilyDoctorInfoReq param);//二级及以上医院下派基层专业技术人员比例
    Map<String,Object> baseSeniorRate(FamilyDoctorInfoReq param);//基层的高级职称(副高及以上)医务人员比例
    Map<String,Object> downturnDoctor(FamilyDoctorInfoReq param);//二级及以上医院派驻各基层医疗卫生机构医师数
    Map<String,Object> downturnRegister(FamilyDoctorInfoReq param);//二级及以上医院派驻各基层医疗卫生机构医师挂号量
    Map<String,Object> downturnRegisterRate(FamilyDoctorInfoReq param);//二级及以上医院派驻各基层医疗卫生机构医师挂号量占比
    Map<String,Object> suburbRate(FamilyDoctorInfoReq param);//统筹区城外转院率
    Map<String,Object> dualReferralBase(FamilyDoctorInfoReq param);//基层医疗机构转诊率
    Map<String,Object> dualReferralHospital(FamilyDoctorInfoReq param);//二级及以上医疗机构转诊率
    Map<String,Object> generalPracticeNumber(FamilyDoctorInfoReq param);//全科医生规范化培养人数(人)
    Map<String,Object> generalPracticeAssistant(FamilyDoctorInfoReq param);//助理全科医生规范化培养人数(人)
    Map<String,Object> generalPracticeTransfer(FamilyDoctorInfoReq param);//全科医生转岗培训人数(人)
    Map<String,Object> characteristicPost(FamilyDoctorInfoReq param);//设立全科医生特设岗位数(人)
    Map<String,Object> generalPracticeOverOne(FamilyDoctorInfoReq param);//拥有1名以上全科医生的乡镇卫生院占比(%)
    Map<String,Object> cityGeneralPractice(FamilyDoctorInfoReq param);//每万名城市居民拥有全科医生数(人)
    Map<String,Object> villageGeneralPractice(FamilyDoctorInfoReq param);//每个乡镇卫生院拥有的全科医生数(人)

    Map<String,Object> upturnDiseases(FamilyDoctorInfoReq param);//上转病人转诊病种
    Map<String,Object> downDiseases(FamilyDoctorInfoReq param);//下转病人转诊病种

    Map capitaPrescription(FamilyDoctorInfoReq param);
}
