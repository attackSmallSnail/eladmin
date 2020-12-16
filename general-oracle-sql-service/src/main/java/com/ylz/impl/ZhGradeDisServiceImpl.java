package com.ylz.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ylz.DynamicStatementService;
import com.ylz.ZhGradeDisService;
import com.ylz.common.enums.CoordinateEnum;
import com.ylz.common.enums.LastParamEnum;
import com.ylz.common.enums.ShowProgressEnum;
import com.ylz.common.pojo.Column;
import com.ylz.common.pojo.HeadColumnVo;
import com.ylz.common.pojo.OverviewObjectVo;
import com.ylz.common.zhutil.TablesUtil;
import com.ylz.req.FamilyDoctorInfoReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZhGradeDisServiceImpl implements ZhGradeDisService {

    @Resource
    private DynamicStatementService dynamicStatementService;
    /**
     * @Description 分级诊疗概览
     * @Author lcw
     * @Date 2019/9/10
     * @Param
     * @Return
     */
    @Override
    public Map<String, Object> gradeCockpit(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        Map<String,String> caliberMap = Maps.newHashMap();
        //概览
        caliberMap.put("caliber","基层医疗卫生机构诊疗人次数");
        resultMap.put("baseTreatment", new OverviewObjectVo<Integer>("基层医疗卫生机构诊疗人次数", "次","sum(baseOutpatientNum)",caliberMap));
        caliberMap.put("caliber","区域总诊疗人次数=门诊人次+急诊人次+出车人次+其他人次");
        resultMap.put("regionTreatment", new OverviewObjectVo<Integer>("区域总诊疗人次数", "次", "sum(outAllPatientNum)",caliberMap));

        caliberMap.put("caliber","基层医疗卫生机构诊疗量占总诊疗量比例=基层医疗卫生机构诊疗人次数/总诊疗人次数×100%");
        caliberMap.put("molecularStr","baseTreatment");
        caliberMap.put("denominatorStr","regionTreatment");
        resultMap.put("baseTreatmentProportion", new OverviewObjectVo<Double>("基层医疗卫生机构诊疗量占总诊疗量比例", "%", "round(sum(baseOutpatientNum)/sum(outpatientNum+emergencyNum)*100,2)","sum(outpatientNum+emergencyNum)",caliberMap));

        caliberMap.put("caliber","基层机构诊疗量同期增长率=（本期基层医疗卫生机构诊疗人次数-上期基层医疗卫生机构诊疗人次数）/上期基层医疗卫生机构诊疗人次数×100%");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("baseTreatmentGrowthRate", new OverviewObjectVo<Double>("基层机构诊疗量同期增长率", "%", "round((sum(baseOutpatientNum)-sum(baseOutpatientNumLy))/sum(baseOutpatientNumLy)*100,2)","sum(baseOutpatientNumLy)",caliberMap));

        caliberMap.put("caliber","医疗机构门(急)诊量同比=(本期医疗机构门(急)诊量-上期医疗机构门(急)诊量)/上期医疗机构门(急)诊量×100%");
        resultMap.put("outpatientYearOnYear", new OverviewObjectVo<Double>("医疗机构门(急)诊量同比", "%", "round((sum(outpatientNum+emergencyNum)-sum(outpatientNumLy+emergencyNumLy))/sum(outpatientNumLy+emergencyNumLy)*100,2)","sum(outpatientNumLy+emergencyNumLy)",caliberMap));

        caliberMap.put("caliber","医疗机构出院人数同比=(本期医疗机构出院人数-上期医疗机构出院人数)/上期医疗机构出院人数×100%");
        resultMap.put("leaveHospitalYearOnYear", new OverviewObjectVo<Double>("医疗机构出院人数同比", "%", "round((sum(dischargedNum)-sum(dischargedNumLy))/sum(dischargedNumLy)*100,2)","sum(dischargedNumLy)",caliberMap));

        caliberMap.put("caliber","异地就诊率=异地就诊人次/区域总诊疗人次数×100%");
        caliberMap.put("molecularStr","ydjzrs");
        caliberMap.put("denominatorStr","regionTreatment");
        resultMap.put("anotherPlaceTreatment", new OverviewObjectVo<Double>("异地就诊率", "%", "round(sum(ydjzrs)/sum(outpatientNum+emergencyNum)*100,2)","sum(outpatientNum+emergencyNum)",caliberMap));
        resultMap.put("ydjzrs", new OverviewObjectVo<Integer>("异地就诊人次", "人", "sum(ydjzrs)"));
        //        resultMap.put("regionTreatmentRate", new OverviewObjectVo<Double>("区域内就诊率(%)", "%", "round(sum(docChangeArchives+emergencyNum)/sum(20000)*100,2)","sum(20000)"));

        caliberMap.put("caliber","区域内就诊率=区域内总诊疗人次/区域内总人口数×100%");
        caliberMap.put("molecularStr","regionTreatment");
        caliberMap.put("denominatorStr","CZRKS");
        resultMap.put("regionTreatmentRate", new OverviewObjectVo<Double>("区域内就诊率", "%", "round(sum(outpatientNum+emergencyNum)/sum(CZRKS)*100,2)","sum(CZRKS)",caliberMap));
        resultMap.put("CZRKS", new OverviewObjectVo<Integer>("常驻人口数", "人", "sum(CZRKS)"));

        //上转下转
        caliberMap.put("caliber","上级医疗机构下转患者总例次数（门诊）");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("downward", new OverviewObjectVo<Integer>("上级医疗机构下转患者总例次数(门诊)", "次", "sum(MZXZRC)",caliberMap));

        caliberMap.put("caliber","上级医疗机构下转患者总例次数（住院）");
        resultMap.put("upward", new OverviewObjectVo<Integer>("上级医疗机构下转患者总例次数(住院)", "次", "sum(ZYXZRC)",caliberMap));

        caliberMap.put("caliber","二、三级医院向基层医疗卫生机构转诊人数");
        resultMap.put("downwardToBase", new OverviewObjectVo<Integer>("二、三级医院向基层医疗卫生机构转诊人数", "人", "sum(allBaseDown)",caliberMap));

        caliberMap.put("caliber","二、三级医院向基层医疗卫生机构转诊人数同期增长率=(报告期内二、三级医院向基层医疗卫生机构转诊人数-上期二、三级医院向基层医疗卫生机构转诊人数)/上期二、三级医院向基层医疗卫生机构转诊人数×100%");
        resultMap.put("downwardToBaseRate", new OverviewObjectVo<Double>("二、三级医院向基层医疗卫生机构转诊人数同期增长率", "%", "round((sum(allBaseDown)-sum(allBaseDownLy))/sum(allBaseDownLy)*100,2)","sum(allBaseDownLy)",caliberMap));

        caliberMap.put("caliber","基层医疗卫生机构上转患者总例次数(门诊)");
        resultMap.put("upwardToHospitalsOutpatient", new OverviewObjectVo<Integer>("基层医疗卫生机构上转患者总例次数(门诊)", "次", "sum(MZFROMJCSZRS)",caliberMap));

        caliberMap.put("caliber","基层医疗卫生机构上转患者总例次数(住院)");
        resultMap.put("upwardToHospitals", new OverviewObjectVo<Integer>("基层医疗卫生机构上转患者总例次数(住院)", "次", "sum(ZYFROMJCSZRS)",caliberMap));

        caliberMap.put("caliber","基层医疗卫生机构向上级医院转诊病人占比=报告期内基层医疗卫生机构向上级医院转诊病人数/基层医疗卫生机构就诊病人数×100%");
        caliberMap.put("molecularStr","baseUpNum");
        caliberMap.put("denominatorStr","baseTreatment");
        resultMap.put("upwardToHospitalsNumberRate", new OverviewObjectVo<Double>("基层医疗卫生机构向上级医院转诊病人占比", "%", "round(sum(MZFROMJCSZRS+ZYFROMJCSZRS)/sum(baseOutpatientNum)*100,2)","sum(baseOutpatientNum)",caliberMap));
        resultMap.put("baseUpNum", new OverviewObjectVo<Integer>("基层医疗机构上转人数", "人次", "sum(MZFROMJCSZRS+ZYFROMJCSZRS)"));

        caliberMap.put("caliber","专家号源下放基层比例=专家号挂号数下放基层数/总专家号挂号数×100%");
        caliberMap.put("molecularStr","expertDownBaseNum");
        caliberMap.put("denominatorStr","zjhNum");
        resultMap.put("expertDown", new OverviewObjectVo<Double>("专家号源下放基层比例", "%", "round(sum(expertDownBaseNum)/sum(expertNum)*100,2)","sum(expertNum)",caliberMap));
        resultMap.put("expertDownBaseNum", new OverviewObjectVo<Integer>("专家号挂号数下放基层数", "次", "sum(expertDownBaseNum)"));
        resultMap.put("zjhNum", new OverviewObjectVo<Integer>("总专家号挂号数", "次", "sum(expertNum)"));

        //慢病管理
        caliberMap.put("caliber","年末某区域建立高血压患者管理档案并进行规范管理的人数");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("hypertensionManagement", new OverviewObjectVo<Integer>("城市高血压患者规范化诊疗和管理人数", "人", "sum(standardManagementHypertensi)",caliberMap));

        caliberMap.put("caliber","区域高血压患者规范化诊疗和管理率=区域高血压患者规范化诊疗和管理人数/区域高血压患者人数×100%");
        resultMap.put("specificationHypertension", new OverviewObjectVo<Double>("城市高血压患者规范化诊疗和管理率", "%", "round(sum(standardManagementHypertensi)/sum(hypertensionsPh)*100,2)","sum(hypertensionsPh)",caliberMap));

        caliberMap.put("caliber","年末某区域建立糖尿病患者管理档案并进行规范管理的人数");
        resultMap.put("diabetesManagement", new OverviewObjectVo<Integer>("城市糖尿病患者规范化诊疗和管理人数", "人", "sum(standardManagementDiabetic)",caliberMap));

        caliberMap.put("caliber","区域糖尿病患者规范化诊疗和管理率=区域糖尿病患者规范化诊疗和管理人数/区域糖尿病患者人数×100%");
        caliberMap.put("molecularStr","diabetesManagement");
        caliberMap.put("denominatorStr","diabetics");
        resultMap.put("specificationDiabetesRate", new OverviewObjectVo<Double>("城市糖尿病患者规范化诊疗和管理率", "%", "round(sum(standardManagementDiabetic)/sum(diabetics)*100,2)","sum(diabetics)",caliberMap));
        resultMap.put("diabetics", new OverviewObjectVo<Integer>("区域糖尿病患者人数", "人", "sum(diabetics)"));

        //远程医疗
        caliberMap.put("caliber","远程医疗服务覆盖地区范围");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("telemedicineRegion", new OverviewObjectVo<String>("远程医疗服务覆盖地区范围", "", "to_char(sum(isRemoteAreaNum),'fm90') || '/' || to_char(7,'fm90')","","已覆盖的区/全部区",caliberMap));

        caliberMap.put("caliber","开展远程医疗服务公立医院数");
        resultMap.put("telemedicineNumber", new OverviewObjectVo<Integer>("开展远程医疗服务公立医院数", "家", "sum(isRemoteHis)",caliberMap));

        caliberMap.put("caliber","开展远程医疗服务公立医院占比=区域内开展远程医疗服务的公立医院数量/区域内公立医院总数×100%");
        caliberMap.put("molecularStr","telemedicineNumber");
        caliberMap.put("denominatorStr","publicOrgNum");
        resultMap.put("telemedicinePublicHospitals", new OverviewObjectVo<Double>("开展远程医疗服务公立医院占比", "%", "round(sum(isRemoteHis)/sum(publicOrgNum)*100,2)","sum(publicOrgNum)",caliberMap));
        resultMap.put("publicOrgNum", new OverviewObjectVo<Integer>("公立机构数量", "家", "sum(publicOrgNum)"));

        caliberMap.put("caliber","开展远程医疗服务人次数");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("telemedicineNumberOfServices", new OverviewObjectVo<Integer>("开展远程医疗服务人次数", "人", "sum(remotePeoNum)",caliberMap));

        //家庭医生服务
        caliberMap.put("caliber","开展签约服务的家庭医生数");
        resultMap.put("familyDoctorNumber", new OverviewObjectVo<Integer>("开展签约服务的家庭医生数", "人", "sum(familyDoctorSingNum)",caliberMap));

        caliberMap.put("caliber","家庭医生签约服务覆盖率=家庭医生签约服务人数/辖区内人口数×100%。家庭医生签约服务人数指家庭医生与居民签订服务协议的人数，含建立孕产妇保健手册、儿童保健手册的人数。");
        caliberMap.put("molecularStr","peopleSignNum");
        caliberMap.put("denominatorStr","CZRKS");
        resultMap.put("familyDoctorCoverage", new OverviewObjectVo<Double>("家庭医生签约服务覆盖率", "%", "round(sum(peopleSignNum)/sum(CZRKS)*100,2)","sum(CZRKS)",caliberMap));
        resultMap.put("peopleSignNum", new OverviewObjectVo<Integer>("家庭医生签约服务人数", "人次", "sum(peopleSignNum)"));

        caliberMap.put("caliber","重点人群签约服务覆盖率=重点人群签约人数/辖区重点人群数×100%。重点人群签约人数指65岁及以上老年人、孕产妇、儿童、残疾人以及严重精神障碍、高血压、糖尿病、结核病患者签约的人数（去除重复数）。辖区重点人群数：指辖区65岁及以上老年人、孕产妇、儿童、残疾人以及严重精神障碍、高血压、糖尿病、结核病患者签约的人数（去除重复数）。");
        resultMap.put("keyPopulationCoverage", new OverviewObjectVo<Double>("重点人群签约服务覆盖率", "%", "round(sum(keyPopulationSignNum)/sum(keyPopulationNum),2)","sum(keyPopulationNum)",caliberMap));

        // 医疗服务监管
        caliberMap.put("caliber","临床路径是指针对一个病种，制定出医院内医务人员必须遵循的诊疗模式，使病人从入院到出院依照该模式接受检查、手术、治疗、护理等医疗服务");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("clinicalPathway", new OverviewObjectVo<Integer>("实施临床路径管理的病例数", "例", "sum(managerCpath)",caliberMap));

        caliberMap.put("caliber","临床路径管理的住院病人占比=本期医院实行临床路径管理的出院病人例数／同期医院出院病人总例数×100%");
        caliberMap.put("molecularStr","clinicalPathway");
        caliberMap.put("denominatorStr","hospitalizationNum");
        resultMap.put("clinicalPathwayRate", new OverviewObjectVo<Double>("临床路径管理的住院病人占比", "%", "round(sum(managerCpath)/sum(hospitalizationNum)*100,2)","sum(hospitalizationNum)",caliberMap));
        resultMap.put("hospitalizationNum", new OverviewObjectVo<Integer>("同期医院出院病人总例数", "例", "sum(hospitalizationNum)"));


        caliberMap.put("caliber","按病种付费是指根据疾病分类法，将住院病人疾病按诊断分为若干组，每组又根据疾病的轻重程度及有无合并病、并发症分为几级，对每一组不同级别都分别制定费用标准，按此标准对某组某级疾病诊疗全过程一次性向医院支付费用");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("drgs", new OverviewObjectVo<Integer>("实行按病种付费的病种数", "个", "sum(diseasesPayTypeNum)",caliberMap));

        caliberMap.put("caliber","实行按病种收费的病种数");
        resultMap.put("chargedByDisease", new OverviewObjectVo<Integer>("实行按病种收费的病种数", "个", "sum(diseasesTakeTypeNum)",caliberMap));

        //todo
        caliberMap.put("caliber","开展日间手术的公立医院占比=开展日间手术的公立医院数/公立医院数×100%。日间手术是指患者按照诊疗计划在1日（24小时）内入、出院完成的手术或操作（不包括门诊手术），因病情需要延期住院的特殊病例，住院时间不超过48小时");
        caliberMap.put("molecularStr","isRjssHisNum");
        caliberMap.put("denominatorStr","publicOrgNum");
        resultMap.put("daytimeOperationRate", new OverviewObjectVo<Double>("开展日间手术的公立医院占比", "%", "round(sum(isRjssHisNum)/sum(publicOrgNum)*100,2)","sum(publicOrgNum)",caliberMap));
        resultMap.put("isRjssHisNum", new OverviewObjectVo<Integer>("开展日间手术例数", "医院数", "sum(isRjssHisNum)",caliberMap));

        caliberMap.put("caliber","开展日间手术例数");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("daytimeOperation", new OverviewObjectVo<Integer>("开展日间手术例数", "例", "sum(rjOperationNums)",caliberMap));

        // 优势资源下沉
        caliberMap.put("caliber","二级及以上医院下派基层专业技术人员数");
        resultMap.put("downturnTechnologyNumbers", new OverviewObjectVo<Integer>("二级及以上医院下派基层专业技术人员数", "人次", "sum(technologyNum)",caliberMap));

        caliberMap.put("caliber","区域内二级及以上医院下派基层专业技术人员比例=区域内二级及以上医院下派基层专业技术人员数/区域内二级及以上医院专业技术人员数×100%");
        caliberMap.put("molecularStr","downturnTechnologyNumbers");
        caliberMap.put("denominatorStr","allTwoTechnologyNum");
        resultMap.put("downturnTechnologyRate", new OverviewObjectVo<Double>("区域内二级及以上医院下派基层专业技术人员比例", "%", "round(sum(technologyNum)/sum(allTwoTechnologyNum)*100,2)","sum(allTwoTechnologyNum)",caliberMap));
        resultMap.put("allTwoTechnologyNum", new OverviewObjectVo<Integer>("区域内二级及以上医院专业技术人员数", "例", "sum(allTwoTechnologyNum)"));

        caliberMap.put("caliber","区域内基层的高级职称(副高及以上)医务人员比例=区域内基层的高级职称(副高及以上)医务人员数/区域内基层的医务人员数×100%");
        caliberMap.put("molecularStr","baseHighLevelNum");
        caliberMap.put("denominatorStr","baseMedicalPeoNum");
        resultMap.put("baseSeniorRate", new OverviewObjectVo<Double>("基层的高级职称(副高及以上)医务人员比例", "%", "round(sum(baseHighLevelNum)/sum(baseMedicalPeoNum)*100,2)","sum(baseMedicalPeoNum)",caliberMap));
        resultMap.put("baseHighLevelNum", new OverviewObjectVo<Integer>("区域内基层的高级职称(副高及以上)医务人员数", "人", "sum(baseHighLevelNum)"));
        resultMap.put("baseMedicalPeoNum", new OverviewObjectVo<Integer>("区域内基层的医务人员数", "人", "sum(baseMedicalPeoNum)"));

        caliberMap.put("caliber","二级及以上医院派驻各基层医疗卫生机构医师数");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("downturnDoctor", new OverviewObjectVo<Integer>("二级及以上医院派驻各基层医疗卫生机构医师数", "人次", "sum(sinkNum)",caliberMap));

        caliberMap.put("caliber","二级及以上医院派驻各基层医疗卫生机构医师挂号量");
        resultMap.put("downturnRegister", new OverviewObjectVo<Integer>("二级及以上医院派驻各基层医疗卫生机构医师挂号量", "人次", "sum(downRegisteredNum)",caliberMap));

        caliberMap.put("caliber","二级及以上医院派驻各基层医疗卫生机构医师挂号量占比=二级及以上医院派驻各基层医疗卫生机构医师挂号量/基层医疗卫生机构总挂号量×100%");
        caliberMap.put("molecularStr","downturnRegister");
        caliberMap.put("denominatorStr","baseRegisteredNum");
        resultMap.put("downturnRegisterRate", new OverviewObjectVo<Integer>("二级及以上医院派驻各基层医疗卫生机构医师挂号量占比", "%", "round(sum(downRegisteredNum)/sum(baseRegisteredNum)*100,2)","sum(baseRegisteredNum)",caliberMap));
        resultMap.put("baseRegisteredNum", new OverviewObjectVo<Integer>("基层医疗卫生机构总挂号量", "次", "sum(baseRegisteredNum)"));

        // 双向转诊
        caliberMap.put("caliber","统筹区域外转院率=转诊或转院到统筹区外的人数/(门诊人数+住院人数)×100%");
        caliberMap.put("molecularStr","zzOutAreaNum");
        caliberMap.put("denominatorStr","regionTreatment");
        resultMap.put("suburbRate", new OverviewObjectVo<Double>("统筹区域外转院率", "%", "round(sum(zzOutAreaNum)/sum(outpatientNum+emergencyNum)*100,2)","sum(outpatientNum+emergencyNum)",caliberMap));
        resultMap.put("zzOutAreaNum", new OverviewObjectVo<Integer>("转诊或者转院到统筹区域外人数", "人", "sum(zzOutAreaNum)"));


        caliberMap.put("caliber","基层医疗机构转诊率=基层医疗机构转诊人次数/基层医疗机构总诊疗人次数×100%");
        caliberMap.put("molecularStr","baseUpNum");
        caliberMap.put("denominatorStr","regionTreatment");
        resultMap.put("dualReferralBase", new OverviewObjectVo<Double>("基层医疗机构转诊率", "%", "round(sum(MZFROMJCSZRS+ZYFROMJCSZRS)/sum(baseOutpatientNum)*100,2)","sum(baseOutpatientNum)",caliberMap));

        caliberMap.put("caliber","二级及以上医疗机构转诊率=二级及以上医疗机构转诊人次数/二级及以上医疗机构总诊疗人次数×100%");
        caliberMap.put("molecularStr","tTNumber");
        caliberMap.put("denominatorStr","tTOutpatientNum");
        resultMap.put("dualReferralHospital", new OverviewObjectVo<Double>("二级及以上医疗机构转诊率", "%", "round(sum(tTNumber)/sum(tTOutpatientNum)*100,2)","sum(tTOutpatientNum)",caliberMap));
        resultMap.put("tTNumber", new OverviewObjectVo<Integer>("二级及以上医疗机构转诊人次数", "人次", "sum(tTNumber)"));
        resultMap.put("tTOutpatientNum", new OverviewObjectVo<Integer>("二级及以上医疗机构总诊疗人次数", "人次", "sum(tTOutpatientNum)"));

        // 全科医生
        caliberMap.put("caliber","全科医生规范化培养人数");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("generalPracticeNumber", new OverviewObjectVo<Integer>("全科医生规范化培养人数", "人", "sum(generalDocRecruitNum)",caliberMap));

        caliberMap.put("caliber","助理全科医生规范化培养人数");
        resultMap.put("generalPracticeAssistant", new OverviewObjectVo<Double>("助理全科医生规范化培养人数", "人", "sum(assGeneralDocRecruitNum)",caliberMap));

        caliberMap.put("caliber","全科医生转岗培训人数");
        resultMap.put("generalPracticeTransfer", new OverviewObjectVo<Double>("全科医生转岗培训人数", "人", "sum(tranGeneralDocRecruitNum)",caliberMap));

        caliberMap.put("caliber","设立全科医生特设岗位数");
        resultMap.put("characteristicPost", new OverviewObjectVo<Integer>("设立全科医生特设岗位数", "人", "sum(speGeneralDocNum)",caliberMap));
//        resultMap.put("generalPracticeOverOne", new OverviewObjectVo<Integer>("拥有1名以上全科医生的乡镇卫生院占比(%)", "%", "round(200/182*100,2)","182"));

        caliberMap.put("caliber","拥有1名以上全科医生的乡镇卫生院占比=区域内拥有1名以上全科医生的乡镇卫生院数量/区域内乡镇卫生院数量×100%");
        caliberMap.put("molecularStr","haveOneDocNum");
        caliberMap.put("denominatorStr","oneOrgNum");
        resultMap.put("generalPracticeOverOne", new OverviewObjectVo<Integer>("拥有1名以上全科医生的乡镇卫生院占比", "%", "round(sum(haveOneDocNum)/sum(oneOrgNum)*100,2)","sum(oneOrgNum)",caliberMap));
        resultMap.put("haveOneDocNum", new OverviewObjectVo<Integer>("拥有一名以上全科医生的卫生院数量", "机构数", "sum(haveOneDocNum)"));
        resultMap.put("oneOrgNum", new OverviewObjectVo<Integer>("区域内乡镇卫生院数量", "机构数", "sum(oneOrgNum)"));

        caliberMap.put("caliber","每万名城市居民拥有的全科医生数=区域全科医生数/区域常住人口数*10000");
        caliberMap.put("molecularStr","generalDocNum");
        caliberMap.put("denominatorStr","CZRKS");
        resultMap.put("cityGeneralPractice", new OverviewObjectVo<Integer>("每万名城市居民拥有全科医生数", "人", "decode(sum(CZRKS),0,0,round(sum(generalDocNum)/sum(CZRKS)*10000,2))",true,caliberMap));
        resultMap.put("generalDocNum", new OverviewObjectVo<Integer>("全科医生数量", "人", "sum(generalDocNum)"));

        caliberMap.put("caliber","每个乡镇卫生院拥有的全科医生数");
        resultMap.put("villageGeneralPractice", new OverviewObjectVo<Double>("每个乡镇卫生院拥有的全科医生数", "人", "decode(sum(oneOrgNum), 0, 0, sum(baseGeneralDocNum)/sum(oneOrgNum),2)",true,caliberMap));

        //--------------------------统筹区域外转院率要重新取------dual常量取数据的指标后期取----

        //去年  lastYear  Ly
        //上个季 /上个月 LastDay  Ld
        //自定义时间设置为两年以前 LaseCustom
        param.setLastCustomDateStart(-24);
        param.setLastCustomDateEnd(-24);
        String[] queryTables = new String[]{ "inpOperationView","referralManView",
                "gradedDisReportTable","familyDoctorChangeTable",
                "pathwayManView","sinkingStaffTable","hrBaseInformationTable","outReservationRegistered",
                "gdcTable", "outStatisticsTable", "inpStatisticsTable",
                "peopleConstantView","healthArchive", "phChronicDisease","orgTable"
        };
        queryTables = LastParamEnum.addLastEnum(queryTables, LastParamEnum.LAST_YEAR);
        Map map = dynamicStatementService.commonOverview(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables));
        //原名称太长,缩短名称
        map.put("upwardToHospitalsHospitalization",map.get("upwardToHospitals"));
        return map;
    }

    //总诊疗人次 = 门诊人次+急诊人次+出车人次+其他人次+计免人次
//    public static final String sumZzlrc = "sum(outAllPatientNum+registrationCarNum+vaccinationNum)";
//    //去年总诊疗人次
//    public static final String sumZzlrcLy = "sum(outAllPatientNumLy+registrationCarNumLy+vaccinationNumLy)";

    @Override
    public Map<String, OverviewObjectVo> getHomeGradeMedical(FamilyDoctorInfoReq param){
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        final String sumZzlrc =  "sum(outAllPatientNum+registrationCarNum+vaccinationNum)";
        final String sumZzlrcLy = "sum(outAllPatientNumLy+registrationCarNumLy+vaccinationNumLy)";

        Map<String,String> caliberMap = Maps.newHashMap();

        caliberMap.put("caliber","基层医疗卫生机构诊疗量占总诊疗量比例=基层医疗卫生机构诊疗量/总诊疗量×100%");
        caliberMap.put("molecularStr","baseMedicalNum");
        caliberMap.put("denominatorStr","sumZzlrc");
        resultMap.put("baseTreatmentProportion", new OverviewObjectVo<Double>("基层医疗卫生机构诊疗量占总诊疗量比例", "%", "round((sum(baseOutpatientNum)+sum(baseHospitalizationNum))/"+sumZzlrc+"*100,2)",""+sumZzlrc+"",caliberMap));
        //baseTreatmentProportion同比
        resultMap.put("baseTreatmentProportionAn", new OverviewObjectVo<Double>("基层医疗卫生机构诊疗量占总诊疗量比例", "%", "round((sum(baseOutpatientNumLy)+sum(baseHospitalizationNumLy))/"+sumZzlrcLy+"*100,2)",""+sumZzlrcLy+"", true));
//        resultMap.put("regionTreatmentRate", new OverviewObjectVo<Double>("区域内就诊率(%)", "%", "round(sum(docChangeArchives+emergencyNum)/sum(20000)*100,2)","sum(20000)"));
        resultMap.put("baseMedicalNum", new OverviewObjectVo<Double>("基层医疗卫生机构诊疗量", "","sum(baseOutpatientNum)+sum(baseHospitalizationNum)"));
        resultMap.put("sumZzlrc", new OverviewObjectVo<Double>("住院人数", "",sumZzlrc));

        caliberMap.put("caliber","区域内就诊率=总诊疗人次 / 区域内总人口数×100%");
        caliberMap.put("molecularStr","sumZzlrc");
        caliberMap.put("denominatorStr","CZRKS");
        resultMap.put("regionTreatmentRate", new OverviewObjectVo<Double>("区域内就诊率(%)", "%", "round("+sumZzlrc+"/sum(CZRKS)*100,2)","sum(CZRKS)",caliberMap));
        //regionTreatmentRate同比
        resultMap.put("regionTreatmentRateAn", new OverviewObjectVo<Double>("区域内就诊率(%)", "%", "decode(sum(CZRKS),0,0,round("+sumZzlrc+"/sum(CZRKS)*100,2))-decode(sum(CZRKSLy),0,0,round("+sumZzlrcLy+"/sum(CZRKSLy)*100,2))","1", true));
        resultMap.put("CZRKS", new OverviewObjectVo<Double>("区域内总人口数", "","sum(CZRKS)"));

        caliberMap.put("caliber","二、三级医院向基层医疗卫生机构转诊人数同期增长率=(二、三级医院向基层医疗卫生机构转诊人数-上期二、三级医院向基层医疗卫生机构转诊人数)/上期二、三级医院向基层医疗卫生机构转诊人数×100%");
        caliberMap.remove("molecularStr");
        caliberMap.remove("denominatorStr");
        resultMap.put("downwardToBaseRate", new OverviewObjectVo<Double>("二、三级医院向基层医疗卫生机构转诊人数同期增长率", "%", "round((sum(allBaseDown)-sum(allBaseDownLy))/sum(allBaseDownLy)*100,2)","sum(allBaseDownLy)",caliberMap));
        //downwardToBaseRate同比
        resultMap.put("downwardToBaseRateAn", new OverviewObjectVo<Double>("二、三级医院向基层医疗卫生机构转诊人数同期增长率", "%", "decode(sum(allBaseDownLy),0,0,round((sum(allBaseDown)-sum(allBaseDownLy))/sum(allBaseDownLy)*100,2))-decode(sum(allBaseDownLc),0,0,round((sum(allBaseDownLy)-sum(allBaseDownLc))/sum(allBaseDownLc)*100,2))",true, true));

        caliberMap.put("caliber","高血压规范化诊疗和管理率=高血压患者规范化诊疗和管理人数/高血压患者人数×100%");
        caliberMap.put("molecularStr","standardManagementHypertensi");
        caliberMap.put("denominatorStr","hypertensionsPh");
        resultMap.put("specificationHypertension", new OverviewObjectVo<Double>("城市高血压患者规范化诊疗和管理率(%)", "%", "round(sum(standardManagementHypertensi)/sum(hypertensionsPh)*100,2)","sum(hypertensionsPh)",caliberMap));
        resultMap.put("specificationHypertensionAn", new OverviewObjectVo<Double>("城市高血压患者规范化诊疗和管理率(%)", "%", true,"decode(sum(hypertensionsPh),0,0,round(sum(standardManagementHypertensi)/sum(hypertensionsPh)*100,2))-decode(sum(hypertensionsPhLy),0,0,round(sum(standardManagementHypertensiLy)/sum(hypertensionsPhLy)*100,2))", true));
        resultMap.put("standardManagementHypertensi", new OverviewObjectVo<Double>("高血压患者规范化诊疗和管理人数", "","sum(standardManagementHypertensi)"));
        resultMap.put("hypertensionsPh", new OverviewObjectVo<Double>("高血压患者人数", "","sum(hypertensionsPh)"));


        caliberMap.put("caliber","糖尿病规范化诊疗和管理率=糖尿病患者累计规范管理人数/糖尿病患者人数×100%");
        caliberMap.put("molecularStr","standardManagementDiabetic");
        caliberMap.put("denominatorStr","diabetics");
        resultMap.put("specificationDiabetesRate", new OverviewObjectVo<Double>("城市糖尿病患者规范化诊疗和管理率(%)", "%", "decode(sum(diabetics),0,0,round(sum(standardManagementDiabetic)/sum(diabetics)*100,2))",true,caliberMap));
        resultMap.put("specificationDiabetesRateAn", new OverviewObjectVo<Double>("城市糖尿病患者规范化诊疗和管理率(%)", "%", true,"decode(sum(diabetics),0,0,round(sum(standardManagementDiabetic)/sum(diabetics)*100,2))-decode(sum(diabeticsLy),0,0,round(sum(standardManagementDiabeticLy)/sum(diabeticsLy)*100,2))",true));
        resultMap.put("standardManagementDiabetic", new OverviewObjectVo<Double>("糖尿病患者累计规范管理人数", "","sum(standardManagementDiabetic)"));
        resultMap.put("diabetics", new OverviewObjectVo<Double>("糖尿病患者人数", "","sum(diabetics)"));

        //去年  lastYear  Ly
        //上个季 /上个月 LastDay  Ld
        //自定义时间(LaseCustom)设置为两年以前
        param.setLastCustomDateStart(-24);
        param.setLastCustomDateEnd(-24);
        String[] queryTables = new String[]{ "referralManView", "phChronicDisease","peopleConstantView","healthArchive",
                  "gdcTable", "outStatisticsTable", "inpStatisticsTable","medicalFilling"};
        queryTables = LastParamEnum.addLastEnum(queryTables, LastParamEnum.LAST_YEAR,LastParamEnum.LAST_CUSTOM);
        param.setNeedWaringFlag("0");
        Map map = dynamicStatementService.commonOverview(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables));

        return map;
    }

    @Override
    public Map<String, Object> baseTreatment(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("baseTreatment", new OverviewObjectVo<Integer>("基层医疗卫生机构诊疗人次数", "人次", "sum(baseOutpatientNum)", true, true, true, false, true, CoordinateEnum.Y));
        resultMap.put("baseTreatmentTb", new OverviewObjectVo<Integer>(param.getLastType(), "%", "round((sum(baseOutpatientNum)-sum(baseOutpatientNumLy))/sum(baseOutpatientNumLy),2)", "sum(baseOutpatientNumLy)", true));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("baseTreatment", "基层医疗卫生机构诊疗人次数",  "人次", false, false, ShowProgressEnum.F, true));
        headColumns.add(new HeadColumnVo("baseTreatmentTb", param.getLastType(),  "%", false, true));
        String[] queryTables = new String[]{"outStatisticsTable", "outStatisticsTableLastYear", "inpStatisticsTable", "inpStatisticsTableLastYear"};

        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> regionTreatment(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("regionTreatment", new OverviewObjectVo<Integer>("区域总诊疗人次数", "人次", "sum(outpatientNum+emergencyNum)", true, true, true, false, true, CoordinateEnum.Y));
        resultMap.put("regionTreatmentTb", new OverviewObjectVo<Integer>(param.getLastType(), "%", "round((sum(outpatientNum+emergencyNum)-sum(outpatientNumLy+emergencyNumLy))/sum(outpatientNumLy+emergencyNumLy),2)", "sum(outpatientNumLy+emergencyNumLy)", true));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("regionTreatment", "区域总诊疗人次数",  "人次", false, false, ShowProgressEnum.F, true));
        headColumns.add(new HeadColumnVo("regionTreatmentTb", param.getLastType(),  "%", false, true));
        String[] queryTables = new String[]{"outStatisticsTable", "outStatisticsTableLastYear", "inpStatisticsTable", "inpStatisticsTableLastYear"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> baseTreatmentProportion(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("baseTreatmentProportion", new OverviewObjectVo<Double>("基层医疗卫生机构诊疗量占总诊疗量比例", "%", "round(sum(baseOutpatientNum)/(sum(outpatientNum)+sum(emergencyNum))*100,2)","sum(outpatientNum)+sum(emergencyNum)", true, true, false, true, CoordinateEnum.Y));
        resultMap.put("baseTreatmentProportionTb", new OverviewObjectVo<Integer>(param.getLastType(), "%", "decode(sum(outpatientNum+emergencyNum), 0, 0, round(sum(baseOutpatientNum)/(sum(outpatientNum+emergencyNum))*100,2))-decode(sum(outpatientNumLy+emergencyNumLy), 0, 0, round((sum(baseOutpatientNumLy))/(sum(outpatientNumLy+emergencyNumLy))*100,2))", true));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("baseTreatmentProportion", "基层医疗卫生机构诊疗量占总诊疗量比例",  "%", false, false, ShowProgressEnum.P, true));
        headColumns.add(new HeadColumnVo("baseTreatmentProportionTb", param.getLastType(),  "%", false, true));
        String[] queryTables = new String[]{"outStatisticsTable", "outStatisticsTableLastYear", "inpStatisticsTable", "inpStatisticsTableLastYear"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> baseTreatmentGrowthRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("baseTreatmentGrowthRate", new OverviewObjectVo<Double>("基层机构诊疗量同期增长率", "%", "round((sum(baseOutpatientNum)-sum(baseOutpatientNumLy))/sum(baseOutpatientNumLy)*100,2)","sum(baseOutpatientNumLy)", true, true, false, true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("baseTreatmentGrowthRate", "基层机构诊疗量同期增长率",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"outStatisticsTable", "outStatisticsTableLastYear"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }
    /*
    各级医疗机构门(急)诊量同比
     */
    @Override
    public Map<String, Object> outpatientYearOnYear(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();

        resultMap.put("threeOutpatient", new OverviewObjectVo<Double>("三级医疗机构门（急）诊量同比", "%", "round((sum(threeOutpatientNum)-sum(threeOutpatientNumLy))/sum(threeOutpatientNumLy)*100,2)","sum(threeOutpatientNumLy)", true, true, false, true, CoordinateEnum.Y));
        resultMap.put("twoOutpatient", new OverviewObjectVo<Double>("二级医疗机构门（急）诊量同比", "%", "round((sum(twoOutpatientNum)-sum(twoOutpatientNumLy))/sum(twoOutpatientNumLy)*100,2)","sum(twoOutpatientNumLy)", true, CoordinateEnum.Y));
        resultMap.put("baseOutpatient", new OverviewObjectVo<Double>("基层医疗机构门（急）诊量同比", "%", "round((sum(baseOutpatientNum)-sum(baseOutpatientNumLy))/sum(baseOutpatientNumLy)*100,2)","sum(baseOutpatientNumLy)", true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("threeOutpatient", "三级医疗机构门（急）诊量同比",  "%", false, true));
        headColumns.add(new HeadColumnVo("twoOutpatient", "二级医疗机构门（急）诊量同比",  "%", false, true));
        headColumns.add(new HeadColumnVo("baseOutpatient", "基层医疗机构门（急）诊量同比",  "%", false, true));

        String[] queryTables = new String[]{"outStatisticsTable", "outStatisticsTableLastYear"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }
    /*
    各级医疗机构出院人数同比
     */
    @Override
    public Map<String, Object> leaveHospitalYearOnYear(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("threeLeaveHospital", new OverviewObjectVo<Double>("三级医疗机构出院人数同比", "%", "round(sum(threeHospitalizationNum)/sum(threeHospitalizationNumLy)*100,2)","sum(threeHospitalizationNumLy)", true, true, false, true, CoordinateEnum.Y));
        resultMap.put("twoLeaveHospital", new OverviewObjectVo<Double>("二级医疗机构出院人数同比", "%", "round(sum(twoHospitalizationNum)/sum(twoHospitalizationNumLy)*100,2)","sum(twoHospitalizationNumLy)", true, CoordinateEnum.Y));
        resultMap.put("baseLeaveHospital", new OverviewObjectVo<Double>("基层医疗机构出院人数同比", "%", "round(sum(baseHospitalizationNum)/sum(baseHospitalizationNumLy)*100,2)","sum(baseHospitalizationNumLy)", true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("threeLeaveHospital", "三级医疗机构出院人数同比",  "%", false, true));
        headColumns.add(new HeadColumnVo("twoLeaveHospital", "二级医疗机构出院人数同比",  "%", false, true));
        headColumns.add(new HeadColumnVo("baseLeaveHospital", "基层医疗机构出院人数同比",  "%", false, true));

        String[] queryTables = new String[]{"inpStatisticsTable", LastParamEnum.resultLastYearTable("inpStatisticsTable")};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> anotherPlaceTreatment(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("anotherPlaceTreatment", new OverviewObjectVo<Double>("异地就诊率(%)", "%", "round(sum(ydjzrs)/sum(outpatientNum+emergencyNum)*100,2)","sum(outpatientNum+emergencyNum)", true, true, false, true,CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("anotherPlaceTreatment", "异地就诊率(%)",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{ "outStatisticsTable", "gradedDisReportTable"};
        queryTables = LastParamEnum.addLastEnum(queryTables, LastParamEnum.LAST_YEAR);
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> regionTreatmentRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("regionTreatmentRate", new OverviewObjectVo<Double>("区域内就诊率(%)", "%", "round(sum(outAllPatientNum)/sum(CZRKS)*100,2)","sum(CZRKS)", true, true, false, true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("regionTreatmentRate", "区域内就诊率(%)",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"outStatisticsTable", "peopleConstantView"};
        queryTables = LastParamEnum.addLastEnum(queryTables, LastParamEnum.LAST_YEAR);
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> downward(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("downward", new OverviewObjectVo<Double>("上级医疗机构下转患者总例次数(门诊)", "人次", "sum(MZXZRC)",true, true, true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("downward", "上级医疗机构下转患者总例次数(门诊)",  "人次", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"referralManView"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> upward(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("upward", new OverviewObjectVo<Double>("上级医疗机构下转患者总例次数(住院)", "人次", "sum(ZYXZRC)",true, true, true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("upward", "上级医疗机构下转患者总例次数(住院)",  "人次", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"referralManView"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> downwardToBase(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("downwardToBase", new OverviewObjectVo<Double>("二、三级医院向基层医疗卫生机构转诊人数", "人次", "sum(allBaseDown)",true, true, true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("downwardToBase", "二、三级医院向基层医疗卫生机构转诊人数",  "人次", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"referralManView"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> downwardToBaseRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("downwardToBaseRate", new OverviewObjectVo<Double>("二、三级医院向基层医疗卫生机构转诊人数同期增长率", "%", "round((sum(allBaseDown)-sum(allBaseDownLy))/sum(allBaseDownLy)*100,2)","sum(allBaseDownLy)", true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("downwardToBaseRate", "二、三级医院向基层医疗卫生机构转诊人数同期增长率",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"referralManView",LastParamEnum.resultLastYearTable("referralManView") };
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> upwardToHospitalsOutpatient(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("upwardToHospitalsOutpatient", new OverviewObjectVo<Double>("基层医疗卫生机构上转患者总例次数(门诊)", "人次", "sum(MZFROMJCSZRS)",true, true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("upwardToHospitalsOutpatient", "基层医疗卫生机构上转患者总例次数(门诊)",  "人次", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"referralManView"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> upwardToHospitalsHospitalization(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("upwardToHospitalsOutpatient", new OverviewObjectVo<Double>("基层医疗卫生机构上转患者总例次数(住院)", "人次", "sum(ZYFROMJCSZRS)",true, true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("upwardToHospitalsOutpatient", "基层医疗卫生机构上转患者总例次数(住院)",  "人次", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"referralManView"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> upwardToHospitalsNumberRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("upwardToHospitalsNumberRate", new OverviewObjectVo<Double>("基层医疗卫生机构向上级医院转诊病人占比(%)", "%", "round(sum(MZFROMJCSZRS+ZYFROMJCSZRS)/sum(baseOutpatientNum)*100,2)","sum(baseHospitalizationNum+baseOutpatientNum)", true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("upwardToHospitalsNumberRate", "基层医疗卫生机构向上级医院转诊病人占比(%)",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"referralManView","inpStatisticsTable","outStatisticsTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> expertDown(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("expertDown", new OverviewObjectVo<Double>("专家号源下放基层比例", "%", "round(sum(expertDownBaseNum)/sum(expertNum)*100,2)","sum(expertNum)", true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("expertDown", "专家号源下放基层比例",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> hypertensionManagement(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("hypertensionManagement", new OverviewObjectVo<Double>("城市高血压患者规范化诊疗和管理人数", "人数", "sum(standardManagementHypertensi)",true, true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("hypertensionManagement", "城市高血压患者规范化诊疗和管理人数",  "人数", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"phChronicDisease"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> specificationHypertension(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("specificationHypertension", new OverviewObjectVo<Double>("城市高血压患者规范化诊疗和管理率", "%", "round(sum(standardManagementHypertensi)/sum(hypertensionsPh)*100,2)","sum(hypertensionsPh)", true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("specificationHypertension", "城市高血压患者规范化诊疗和管理率",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"phChronicDisease"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> diabetesManagement(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("diabetesManagement", new OverviewObjectVo<Double>("城市糖尿病患者规范化诊疗和管理人数", "人次", "sum(standardManagementDiabetic)", true,true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("diabetesManagement", "城市糖尿病患者规范化诊疗和管理人数",  "人次", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"phChronicDisease"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> specificationDiabetesRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("specificationDiabetesRate", new OverviewObjectVo<Double>("城市糖尿病患者规范化诊疗和管理率", "%", "round(sum(standardManagementDiabetic)/sum(diabetics)*100,2)","sum(diabetics)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("specificationDiabetesRate", "城市糖尿病患者规范化诊疗和管理率",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"phChronicDisease"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }
    /*
    todo
    没办法列出 具体区域不好判断,没有区域明细
     */
    @Override
    public Map<String, Object> telemedicineRegion(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("specificationDiabetesRate", new OverviewObjectVo<Double>("远程医疗服务覆盖地区范围", "", "7",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("specificationDiabetesRate", "远程医疗服务覆盖地区范围",  "", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> telemedicineNumber(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("telemedicineNumber", new OverviewObjectVo<Double>("开展远程医疗服务公立医院数", "家", "sum(isRemoteHis)",true,true, CoordinateEnum.DL));
        resultMap.put("telemedicinePublicHospitals", new OverviewObjectVo<Double>("开展远程医疗服务公立医院占比", "%", "round(sum(isRemoteHis)/sum(publicOrgNum)*100,2)","sum(publicOrgNum)",true, CoordinateEnum.DR));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("telemedicineNumber", "开展远程医疗服务公立医院数",  "家", false, false, ShowProgressEnum.P, true));
        headColumns.add(new HeadColumnVo("telemedicinePublicHospitals", "开展远程医疗服务公立医院占比",  "%", false, false, ShowProgressEnum.P, true));

        String[] queryTables = new String[]{"gradedDisReportTable","orgTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> telemedicinePublicHospitals(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("telemedicinePublicHospitals", new OverviewObjectVo<Double>("开展远程医疗服务公立医院占比", "%", "round(sum(isRemoteHis)/sum(publicOrgNum)*100,2)","sum(publicOrgNum)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("telemedicinePublicHospitals", "开展远程医疗服务公立医院占比",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"gradedDisReportTable","orgTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> telemedicineNumberOfServices(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("telemedicineNumberOfServices", new OverviewObjectVo<Double>("开展远程医疗服务人次数", "人次", "sum(remotePeoNum)",true,true, CoordinateEnum.DL));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("telemedicineNumberOfServices", "开展远程医疗服务人次数",  "人次", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> familyDoctorNumber(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("telemedicineNumberOfServices", new OverviewObjectVo<Double>("开展签约服务的家庭医生数(人)", "人次", "sum(familyDoctorSingNum)",true, true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("telemedicineNumberOfServices", "开展签约服务的家庭医生数(人)",  "人次", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"familyDoctorChangeTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> familyDoctorCoverage(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("familyDoctorCoverage", new OverviewObjectVo<Double>("家庭医生签约服务覆盖率", "%", "round(sum(peopleSignNum)/sum(CZRKS)*100,2)","sum(CZRKS)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("familyDoctorCoverage", "家庭医生签约服务覆盖率",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"familyDoctorChangeTable","peopleConstantView"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> keyPopulationCoverage(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("keyPopulationCoverage", new OverviewObjectVo<Double>("重点人群签约服务覆盖率", "%", "round(sum(keyPopulationSignNum)/sum(keyPopulationNum),2)","sum(keyPopulationNum)",true,CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("keyPopulationCoverage", "重点人群签约服务覆盖率",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> clinicalPathway(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("clinicalPathway", new OverviewObjectVo<Double>("实施临床路径管理的病例数", "例", "sum(managerCpath)",true,true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("clinicalPathway", "实施临床路径管理的病例数",  "例", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"pathwayManView"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> clinicalPathwayRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("clinicalPathwayRate", new OverviewObjectVo<Double>("临床路径管理的住院病人占比", "%", "round(sum(managerCpath)/sum(hospitalizationNum)*100,2)","sum(hospitalizationNum)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("clinicalPathwayRate", "临床路径管理的住院病人占比",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"pathwayManView","inpStatisticsTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }
    /*
    todo
    没有病种明细  实行按病种付费的病种
     */
    @Override
    public Map<String, Object> drgs(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("payPrice", new OverviewObjectVo<Double>("价格", "人次", "payPrice",true,false,true,false,CoordinateEnum.Y));
        resultMap.put("diseasesName", new OverviewObjectVo<Double>("病种名称", "人次", "diseasesName",true,false,true,false,CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("diseasesName", "病种名称",  ""));
        headColumns.add(new HeadColumnVo("payPrice", "付费",  "元", false, false, ShowProgressEnum.F, true));

        List<Column> orderByColumn = Lists.newArrayList();
        //列名可以取 函数名加列名   也可以去别名 'patientNum'
        orderByColumn.add(new Column("payPrice","payPrice","价格"));
        List<Column> groupByColumn = Lists.newArrayList();
        groupByColumn.add(new Column("diseasesName","diseasesName","下转病种名称", true));
        groupByColumn.add(new Column("payPrice","payPrice","价格", true));

        String[] queryTables = new String[]{"diseasesCatchPayView"};
        return dynamicStatementService.commonCustomQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables),orderByColumn,groupByColumn, headColumns);
    }

    @Override
    public Map<String, Object> chargedByDisease(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("catchPrice", new OverviewObjectVo<Double>("价格", "人次", "catchPrice",true,false,true,false,CoordinateEnum.Y));
        resultMap.put("diseasesName", new OverviewObjectVo<Double>("病种名称", "人次", "diseasesName",true,false,true,false,CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("diseasesName", "病种名称",  ""));
        headColumns.add(new HeadColumnVo("catchPrice", "收费",  "元", false, false, ShowProgressEnum.F, true));

        List<Column> orderByColumn = Lists.newArrayList();
        //列名可以取 函数名加列名   也可以去别名 'patientNum'
        orderByColumn.add(new Column("catchPrice","catchPrice","价格"));
        List<Column> groupByColumn = Lists.newArrayList();
        groupByColumn.add(new Column("diseasesName","diseasesName","病种名称", true));
        groupByColumn.add(new Column("catchPrice","catchPrice","价格", true));

        String[] queryTables = new String[]{"diseasesCatchPayView"};
        return dynamicStatementService.commonCustomQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables),orderByColumn,groupByColumn, headColumns);
    }

    @Override
    public Map<String, Object> daytimeOperationRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("daytimeOperationRate", new OverviewObjectVo<Double>("开展日间手术的公立医院占比", "%", "decode(sum(publicOrgNum),0,0,round(sum(isRjssHisNum)/sum(publicOrgNum)*100,2))",true,true,true, CoordinateEnum.DR));
        resultMap.put("daytimeOperation", new OverviewObjectVo<Double>("开展日间手术例数", "例", "sum(rjOperationNums)",true,true, CoordinateEnum.DL));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("daytimeOperationRate", "开展日间手术的公立医院占比",  "%", false, false, ShowProgressEnum.P, true));
        headColumns.add(new HeadColumnVo("daytimeOperation", "开展日间手术例数",  "次", false, false, ShowProgressEnum.F, true));

        String[] queryTables = new String[]{"inpOperationSummaryView","orgTable","gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> daytimeOperation(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("daytimeOperationRate", new OverviewObjectVo<Double>("开展日间手术的公立医院占比", "%", "decode(sum(publicOrgNum),0,0,round(sum(isRjssHisNum)/sum(publicOrgNum)*100,2))",true,true,true, CoordinateEnum.DR));
        resultMap.put("daytimeOperation", new OverviewObjectVo<Double>("开展日间手术例数", "例", "sum(rjOperationNums)",true,true, CoordinateEnum.DL));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("daytimeOperationRate", "开展日间手术的公立医院占比",  "%", false, false, ShowProgressEnum.P, true));
        headColumns.add(new HeadColumnVo("daytimeOperation", "开展日间手术例数",  "次", false, false, ShowProgressEnum.F, true));

        String[] queryTables = new String[]{"inpOperationView","orgTable","gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> downturnTechnologyNumbers(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("daytimeOperation", new OverviewObjectVo<Double>("二级及以上医院下派基层专业技术人员数", "次", "sum(technologyNum)",true,true, CoordinateEnum.DL));
        resultMap.put("downturnTechnologyRate", new OverviewObjectVo<Double>("二级及以上医院下派基层专业技术人员比例", "%", "round(sum(technologyNum)/sum(allTwoTechnologyNum)*100,2)","sum(allTwoTechnologyNum)",true, CoordinateEnum.DR));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("daytimeOperation", "二级及以上医院下派基层专业技术人员数",  "次", false, false, ShowProgressEnum.F, true));
        headColumns.add(new HeadColumnVo("downturnTechnologyRate", "二级及以上医院下派基层专业技术人员比例",  "%", false, false, ShowProgressEnum.P, true));

        String[] queryTables = new String[]{"sinkingStaffTable","hrBaseInformationTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> downturnTechnologyRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("downturnTechnologyRate", new OverviewObjectVo<Double>("二级及以上医院下派基层专业技术人员比例", "%", "round(sum(technologyNum)/sum(allTwoTechnologyNum)*100,2)","sum(allTwoTechnologyNum)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("downturnTechnologyRate", "二级及以上医院下派基层专业技术人员比例",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"sinkingStaffTable","hrBaseInformationTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }
    /*
    todo
     */
    @Override
    public Map<String, Object> baseSeniorRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("downturnTechnologyRate", new OverviewObjectVo<Double>("基层的高级职称(副高及以上)医务人员比例", "%", "round(sum(baseHighLevelNum)/sum(baseMedicalPeoNum)*100,2)","sum(baseMedicalPeoNum)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("downturnTechnologyRate", "基层的高级职称(副高及以上)医务人员比例",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"hrBaseInformationTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> downturnDoctor(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("downturnTechnologyRate", new OverviewObjectVo<Double>("二级及以上医院派驻各基层医疗卫生机构医师数", "人", "sum(sinkNum)",true,true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("downturnTechnologyRate", "二级及以上医院派驻各基层医疗卫生机构医师数",  "人", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"sinkingStaffTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> downturnRegister(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("downturnTechnologyRate", new OverviewObjectVo<Double>("二级及以上医院派驻各基层医疗卫生机构医师挂号量", "人", "sum(downRegisteredNum)",true,true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("downturnTechnologyRate", "二级及以上医院派驻各基层医疗卫生机构医师挂号量",  "人", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"sinkingStaffTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> downturnRegisterRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("downturnRegisterRate", new OverviewObjectVo<Double>("二级及以上医院派驻各基层医疗卫生机构医师挂号量占比", "%", "round(sum(downRegisteredNum)/sum(baseRegisteredNum)*100,2)","sum(baseRegisteredNum)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("downturnRegisterRate", "二级及以上医院派驻各基层医疗卫生机构医师挂号量占比",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"sinkingStaffTable","outReservationRegistered"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> suburbRate(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("suburbRate", new OverviewObjectVo<Double>("统筹区城外转院率", "%", "round(sum(zzOutAreaNum)/sum(outpatientNum+emergencyNum)*100,2)","sum(outpatientNum+emergencyNum)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("suburbRate", "统筹区城外转院率",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"gradedDisReportTable", "outStatisticsTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> dualReferralBase(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("dualReferralBase", new OverviewObjectVo<Double>("基层医疗机构转诊率", "%", "round(sum(MZFROMJCSZRS+ZYFROMJCSZRS)/sum(baseOutpatientNum)*100,2)","sum(baseOutpatientNum)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("dualReferralBase", "基层医疗机构转诊率",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"referralManView","outStatisticsTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> dualReferralHospital(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("dualReferralBase", new OverviewObjectVo<Double>("二级及以上医疗机构转诊率", "%", "round(sum(tTNumber)/sum(tTOutpatientNum)*100,2)","sum(tTOutpatientNum)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("dualReferralBase", "二级及以上医疗机构转诊率",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"referralManView","outStatisticsTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }
    /*
    全科医生规范化培养人数
     */
    @Override
    public Map<String, Object> generalPracticeNumber(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("generalDocQualNum", new OverviewObjectVo<Double>("取得合格证人数", "人", "sum(generalDocQualNum)",true,true, CoordinateEnum.DL));
        resultMap.put("generalPracticeNumber", new OverviewObjectVo<Double>("招收人数", "人", "sum(generalDocRecruitNum)",true,true, CoordinateEnum.DR));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("generalDocQualNum", "取得合格证人数",  "人", false, false, ShowProgressEnum.F, true));
        headColumns.add(new HeadColumnVo("generalPracticeNumber", "招收人数",  "人", false, false, ShowProgressEnum.F, true));

        String[] queryTables = new String[]{"gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }
    /*
    助理全科医生规范化培养人数
     */
    @Override
    public Map<String, Object> generalPracticeAssistant(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("generalPracticeAssistant", new OverviewObjectVo<Double>("取得合格证人数", "人", "sum(assGeneralDocQualNum)",true,true, CoordinateEnum.Y));
        resultMap.put("assGeneralDocRecruitNum", new OverviewObjectVo<Double>("招收人数", "人", "sum(assGeneralDocRecruitNum)",true,true,CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("generalPracticeAssistant", "取得合格证人数",  "人", false, false, ShowProgressEnum.F, true));
        headColumns.add(new HeadColumnVo("assGeneralDocRecruitNum", "招收人数",  "人", false, false, ShowProgressEnum.F, true));

        String[] queryTables = new String[]{"gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }
    /*
    全科医生转岗培训人数
     */
    @Override
    public Map<String, Object> generalPracticeTransfer(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("generalPracticeTransfer", new OverviewObjectVo<Double>("取得合格证人数", "人", "sum(assGeneralDocQualNum)",true,true, CoordinateEnum.Y));
        resultMap.put("zsGeneralPracticeTransfer", new OverviewObjectVo<Double>("招收人数", "人", "sum(tranGeneralDocRecruitNum)",true,true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("generalPracticeTransfer", "取得合格证人数",  "人", false, false, ShowProgressEnum.F, true));
        headColumns.add(new HeadColumnVo("zsGeneralPracticeTransfer", "招收人数",  "人", false, false, ShowProgressEnum.F, true));

        String[] queryTables = new String[]{"gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> characteristicPost(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("characteristicPost", new OverviewObjectVo<Double>("设立全科医生特设岗位数", "人", "sum(speGeneralDocNum)",true,true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("characteristicPost", "设立全科医生特设岗位数",  "人", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> generalPracticeOverOne(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("characteristicPost", new OverviewObjectVo<Double>("拥有1名以上全科医生的乡镇卫生院占比", "%", "round(sum(haveOneDocNum)/sum(oneOrgNum)*100,2)","sum(oneOrgNum)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("characteristicPost", "拥有1名以上全科医生的乡镇卫生院占比",  "%", false, false, ShowProgressEnum.P, true));
        String[] queryTables = new String[]{"orgTable", "gradedDisReportTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> cityGeneralPractice(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("cityGeneralPractice", new OverviewObjectVo<Double>("每万名城市居民拥有全科医生数", "人", "decode(sum(CZRKS),0,0,round(sum(generalDocNum)/sum(CZRKS)*10000,2))","sum(CZRKS)",true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("cityGeneralPractice", "每万名城市居民拥有全科医生数",  "人", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"hrBaseInformationTable","peopleConstantView"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> villageGeneralPractice(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("cityGeneralPractice", new OverviewObjectVo<Double>("每个乡镇卫生院拥有的全科医生数", "人", "decode(sum(oneOrgNum), 0, 0, round(sum(baseGeneralDocNum)/sum(oneOrgNum),2))",true,true, CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("cityGeneralPractice", "每个乡镇卫生院拥有的全科医生数",  "人", false, false, ShowProgressEnum.F, true));
        String[] queryTables = new String[]{"hrBaseInformationTable","orgTable"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }

    @Override
    public Map<String, Object> upturnDiseases(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("patientNum", new OverviewObjectVo<Double>("下转转诊数", "人次", "sum(patientNum)",true,false,true,false,CoordinateEnum.Y));
        resultMap.put("diseasesName", new OverviewObjectVo<Double>("病种名称", "人次", "diseasesName",true,false,true,false,CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("diseasesName", "病种名称",  ""));
        headColumns.add(new HeadColumnVo("patientNum", "下转诊数",  "人", false, false, ShowProgressEnum.F, true));

        List<Column> orderByColumn = Lists.newArrayList();
        //列名可以取 函数名加列名   也可以去别名 'patientNum'
        orderByColumn.add(new Column("sum(patientNum)","sum(patientNum)","下转人数"));
        List<Column> groupByColumn = Lists.newArrayList();
        groupByColumn.add(new Column("diseasesName","diseasesName","下转病种名称", true));

        String[] queryTables = new String[]{"upturnDiseasesView"};
        return dynamicStatementService.commonCustomQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables),orderByColumn,groupByColumn, headColumns);
    }

    @Override
    public Map<String, Object> downDiseases(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("patientNum", new OverviewObjectVo<Double>("下转转诊数", "人次", "sum(patientNum)",true,false,true,false,CoordinateEnum.Y));
        resultMap.put("diseasesName", new OverviewObjectVo<Double>("病种名称", "人次", "diseasesName",true,false,true,false,CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("diseasesName", "病种名称",  ""));
        headColumns.add(new HeadColumnVo("patientNum", "下转诊数",  "人", false, false, ShowProgressEnum.F, true));

        List<Column> orderByColumn = Lists.newArrayList();
        //列名可以取 函数名加列名   也可以去别名 'patientNum'
        orderByColumn.add(new Column("sum(patientNum)","sum(patientNum)","下转人数"));
        List<Column> groupByColumn = Lists.newArrayList();
        groupByColumn.add(new Column("diseasesName","diseasesName","下转病种名称", true));

        String[] queryTables = new String[]{"downDiseasesView"};
        return dynamicStatementService.commonCustomQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables),orderByColumn,groupByColumn, headColumns);
    }

    @Override
    public Map capitaPrescription(FamilyDoctorInfoReq param) {
        Map<String, OverviewObjectVo> resultMap = new HashMap();
        resultMap.put("medicalIncome", new OverviewObjectVo<Double>("处方总费用", "元", "sum(rxTotalMoney)"));
        resultMap.put("personTotal", new OverviewObjectVo<Double>("人次", "", "sum(userPeopleNum)"));
        resultMap.put("perCapitaTotal", new OverviewObjectVo<Double>("人均处方费用", "元", "decode(sum(userPeopleNum),0,0,round(sum(rxTotalMoney)/sum(userPeopleNum),2))",true,true,true,CoordinateEnum.Y));

        List<HeadColumnVo> headColumns = new ArrayList<>();
        headColumns.add(new HeadColumnVo("sort", "序号",  ""));
        headColumns.add(new HeadColumnVo("areaName", "区域/机构名称",  ""));
        headColumns.add(new HeadColumnVo("perCapitaTotal", "人均处方费用(元)",  "元"));
        headColumns.add(new HeadColumnVo("personTotal", "人次",  ""));
        headColumns.add(new HeadColumnVo("medicalIncome", "处方总费用(元)",  "元"));

        String[] queryTables = new String[]{"outDrugSummaryView"};
        return dynamicStatementService.commonDataQuery(param, resultMap, TablesUtil.queryTables(queryTables), TablesUtil.fieldsList(queryTables), headColumns);
    }
}
