package com.ylz.common.zhutil;


import com.ylz.common.enums.ColumnEnum;
import com.ylz.common.enums.LastParamEnum;
import com.ylz.common.pojo.Column;
import com.ylz.common.pojo.TableObjectReq;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TablesUtil {
    /*
     * @Description: 表和字段，别名对应表
     * @Author: Nomark
     * @Date: 2019/9/7 18:15
     * @Param: []
     * @Return: java.util.Map<java.lang.String,com.ylz.sxpt.req.TableObjectReq>
     */
    public static Map<String, TableObjectReq> tablesMap() {
        Map<String, TableObjectReq> tables = new HashMap<>();
        //--------------------------------------------------公共卫生---------------------------------------------------------------------------------
        // 居民健康档案信息变化表
        TableObjectReq hiTable = new TableObjectReq("HEALTH_INFORMATION_VIEW");
        hiTable.setNotToDept(true);
        hiTable.setNotPointOfTime(false);
        hiTable.setOrgIdColumn("org_id_gw");
        hiTable.getColumns().put("docArchives", new Column("doc_num", "docArchives", "健康档案数"));
        hiTable.getColumns().put("docChangeArchives", new Column("doc_change_num", "docChangeArchives", "健康档案动态使用数"));
        tables.put("hiTable", hiTable);

        // 健康教育开展情况表
        TableObjectReq heTable = new TableObjectReq("GW_HEALTHY_EDU_VIEW");
        heTable.setNotToDept(true);
        heTable.setNotPointOfTime(false);
        heTable.setCustomPointOfTimeFlag(true);
        heTable.setOrgIdColumn("org_id_gw");
        heTable.getColumns().put("letterpressMaterial", new Column("tgyszlzs", "letterpressMaterial", "提供印刷资料的种数"));
        heTable.getColumns().put("acousticShadow", new Column("tgyxzlzs", "acousticShadow", "播放音像资料的种数"));
        heTable.getColumns().put("publicityBoard", new Column("jkjyxcls", "publicityBoard", "宣传栏数(填报)"));
        heTable.getColumns().put("healthConsultation", new Column("kzjkzxhds", "healthConsultation", "开展健康咨询活动数"));
        heTable.getColumns().put("healthKnowledge", new Column("jkzsjz", "healthKnowledge", "健康知识讲座次数"));
        heTable.getColumns().put("effectAssessment", new Column("wcsjbzgzs", "effectAssessment", "按要求完成上级布置的效果评估调查工作数"));
        tables.put("heTable", heTable);

        // 公卫人员健康信息表 (舍弃表)
        TableObjectReq chiTable = new TableObjectReq("CITIZEN_HEALTHY_INFO_VIEW");
        chiTable.setNotToDept(true);
        chiTable.setNotPointOfTime(false);
        chiTable.setOrgIdColumn("org_id_gw");
        chiTable.getColumns().put("underSixChildrenManagers", new Column("six_children_man_num", "underSixChildrenManagers", "0-6岁儿童健康管理数"));
        chiTable.getColumns().put("underSixChildrens", new Column("six_children_num", "underSixChildrens", "0-6岁儿童数"));
        chiTable.getColumns().put("underThreeChildrenManagers", new Column("three_children_man_num", "underThreeChildrenManagers", "3岁以下儿童系统管理数"));
        chiTable.getColumns().put("newChildrenSees", new Column("new_children_see_num", "newChildrenSees", "新生儿访视数"));
        chiTable.getColumns().put("newChildrens", new Column("new_children_num", "newChildrens", "新生儿总数"));
        chiTable.getColumns().put("earlyPregnancyDocs", new Column("early_pregnancy_doc_num", "earlyPregnancyDocs", "早孕建册数"));
        chiTable.getColumns().put("maternals", new Column("maternal_num", "maternals", "孕产妇总数"));
        chiTable.getColumns().put("maternalHealthManagers", new Column("maternal_healthey_num", "maternalHealthManagers", "孕产妇健康管理数"));
        chiTable.getColumns().put("afterChildBirthSees", new Column("birth_see_num", "afterChildBirthSees", "产后访视数"));
        chiTable.getColumns().put("maternalDocs", new Column("maternal_doc_num", "maternalDocs", "孕产妇健康档案建档数"));
        chiTable.getColumns().put("oldHealthyMans", new Column("old_healthy_man_num", "oldHealthyMans", "65岁以上老年人健康管理数"));
        chiTable.getColumns().put("olds", new Column("old_num", "olds", "65岁以上老年人数"));
        tables.put("chiTable", chiTable);

        // 妇幼统计报表
        TableObjectReq  maternalChild = new TableObjectReq("PH_MATERNAL_CHILD");
        maternalChild.setNotToDept(true);
        maternalChild.setNotPointOfTime(false);
        maternalChild.setCustomPointOfTimeFlag(true);
        maternalChild.setDateColumn("statistics_date");
        maternalChild.setOrgIdColumn("org_id_fy");
        maternalChild.getColumns().put("managedMaternals", new Column("managed_maternals", "managedMaternals", "孕产妇人数"));
        maternalChild.getColumns().put("postpartumVisitMaternals", new Column("postpartum_visit_maternals", "postpartumVisitMaternals", "产后28天内的接受过产后访视的产妇人数"));
        maternalChild.getColumns().put("earlyPregnancies", new Column("early_pregnancies", "earlyPregnancies", "辖区内孕12周之前建册的人数"));
        maternalChild.getColumns().put("hmMaternals", new Column("hm_maternals", "hmMaternals", "符合系统管理的孕产妇数"));
        maternalChild.getColumns().put("maternalArchives", new Column("maternal_archives", "maternalArchives", "孕产妇已建立健康档案数量"));
        maternalChild.getColumns().put("newbornsVisits", new Column("newborns_visits", "newbornsVisits", "产后访视新生儿数"));
        maternalChild.getColumns().put("localLyinginMaternals", new Column("local_lyingin_maternals", "localLyinginMaternals", "本地产休产妇数"));
        maternalChild.getColumns().put("liveBirthsNewborns", new Column("live_births_newborns", "liveBirthsNewborns", "辖区内新生儿活产数"));
        maternalChild.getColumns().put("childrens06", new Column("childrens_0_6", "childrens06", "0-6岁儿童人数"));
        maternalChild.getColumns().put("hmChildrens06", new Column("hm_childrens_0_6", "hmChildrens06", "0-6岁保健管理儿童数（健康管理人数）"));
        maternalChild.getColumns().put("hmChildrens03", new Column("hm_childrens_0_3", "hmChildrens03", "0-3岁保健管理儿童数（健康管理人数）"));
        maternalChild.getColumns().put("childrens03", new Column("childrens_0_3", "childrens03", "3岁以下儿童人数"));
        maternalChild.getColumns().put("parturitionMaternals", new Column("parturition_maternals", "parturitionMaternals", "分娩产妇人数"));
        maternalChild.getColumns().put("hmParturitionMaternals", new Column("hm_parturition_maternals", "hmParturitionMaternals", "符合系统管理的孕产妇数(已分娩)"));
        tables.put("maternalChild", maternalChild);
        // 健康档案情况表
        TableObjectReq  healthArchive = new TableObjectReq("PH_HEALTH_ARCHIVE");
        healthArchive.setNotToDept(true);
        healthArchive.setDateColumn("STATISTICS_DATE");
        healthArchive.setNotPointOfTime(false);
        healthArchive.setCustomPointOfTimeFlag(true);
        healthArchive.setOrgIdColumn("org_id_gw");
        healthArchive.getColumns().put("archives", new Column("archives", "archives", "居民健康档案建档人数"));
        healthArchive.getColumns().put("vaildArchives", new Column("vaild_archives", "vaildArchives", "居民健康档案有效建档人数"));
        healthArchive.getColumns().put("novaildArchives", new Column("novaild_archives", "novaildArchives", "居民健康档案无效建档人数(时间点)"));
        healthArchive.getColumns().put("dynamicArchives", new Column("dynamic_archives", "dynamicArchives", "居民健康档案动态档案人数(时间点)"));
        healthArchive.getColumns().put("archives06", new Column("archives_0_6", "archives06", "0-6岁儿童健康档案建档人数"));
        healthArchive.getColumns().put("archives03", new Column("archives_0_3", "archives03", "0-3岁儿童健康档案建档人数"));
        healthArchive.getColumns().put("archives65", new Column("archives_65", "archives65", "65岁以上老年人健康档案建档人数"));
        tables.put("healthArchive", healthArchive);

        //
        TableObjectReq  phCdcDiseaseView = new TableObjectReq("PH_CDC_DISEASE_VIEW");
        phCdcDiseaseView.setNotToDept(true);
        phCdcDiseaseView.setDateColumn("STATISTICS_DATE");
        phCdcDiseaseView.setNotPointOfTime(false);
        phCdcDiseaseView.setCustomPointOfTimeFlag(true);
        phCdcDiseaseView.setOrgIdColumn("ORG_ID_JKZX");
        phCdcDiseaseView.getColumns().put("diseaseNum", new Column("DISEASE_NUM", "diseaseNum", "传染病报告例数"));
        phCdcDiseaseView.getColumns().put("diseaseDeadNum", new Column("DISEASE_DEAD_NUM", "diseaseDeadNum", "传染病死亡例数"));
        phCdcDiseaseView.getColumns().put("diseaseDeadNum2", new Column("DISEASE_DEAD_NUM2", "diseaseDeadNum2", "终审传染病死亡例数2"));
        tables.put("phCdcDiseaseView", phCdcDiseaseView);


        // 慢病人群统计报表
        TableObjectReq  phChronicDisease = new TableObjectReq("PH_CHRONIC_DISEASE");
        phChronicDisease.setNotToDept(true);
        phChronicDisease.setCustomPointOfTimeFlag(true);
        phChronicDisease.setNotPointOfTime(false);
        phChronicDisease.setDateColumn("STATISTICS_DATE");
        phChronicDisease.setOrgIdColumn("org_id_gw");
        phChronicDisease.getColumns().put("healthManagementElderly", new Column("health_management_elderly", "healthManagementElderly", "接受健康管理的老年人人数（时间点）"));
        phChronicDisease.getColumns().put("managementedHypertension", new Column("managemented_hypertension", "managementedHypertension", "在管高血压患者数（高血压患者数至少1年1次随访人数）（结束时间年区间）"));
        phChronicDisease.getColumns().put("hypertensionsPh", new Column("hypertensions", "hypertensionsPh", "高血压患者任务数（高血压患者人数）（时间点）"));
        phChronicDisease.getColumns().put("standardManagementHypertensi", new Column("standard_management_hypertensi", "standardManagementHypertensi", "高血压患者规范管理人数（结束时间年区间）"));
        phChronicDisease.getColumns().put("standardBloodPressure", new Column("standard_blood_pressure", "standardBloodPressure", "最近一次随访血压达标人数（结束时间年区间）"));
        phChronicDisease.getColumns().put("managementedDiabetic", new Column("managemented_diabetic", "managementedDiabetic", "在管糖尿病患者数（时间点）"));
        phChronicDisease.getColumns().put("diabetics", new Column("diabetics", "diabetics", "糖尿病患者任务数（糖尿病患者人数）（时间点）"));
        phChronicDisease.getColumns().put("standardManagementDiabetic", new Column("standard_management_diabetic", "standardManagementDiabetic", "糖尿病患者规范管理人数在管糖尿病患者数（糖尿病患者数随访人数高血压患者数至少1年1次随访人数）（结束时间年区间）"));
        phChronicDisease.getColumns().put("standardBloodSugar", new Column("standard_blood_sugar", "standardBloodSugar", "最近一次随访血糖达标人数在管糖尿病患者数（结束时间年区间）"));
        phChronicDisease.getColumns().put("registerMentalPatients", new Column("register_mental_patients", "registerMentalPatients", "所有登记在册的确诊重性精神病患者数（重性精神病患者数）（时间点）"));
        phChronicDisease.getColumns().put("managerMentalPatien", new Column("MANAGER_MENTAL_PATIEN", "managerMentalPatien", "管理的重性精神病人数"));
        phChronicDisease.getColumns().put("standardManagerMentalPatien", new Column("standard_manager_mental_patien", "standardManagerMentalPatien", "规范管理的重性精神病人数在管糖尿病患者数（结束时间年区间）"));
        phChronicDisease.getColumns().put("regularMedicationMentalPati", new Column("regular_medication_mental_pati", "regularMedicationMentalPati", "规律服药的重性精神病人数（结束时间年区间）"));
        phChronicDisease.getColumns().put("visitedTuberculosis", new Column("visited_tuberculosis", "visitedTuberculosis", "肺结核可疑者推介转诊人数（结束时间年区间）"));
        phChronicDisease.getColumns().put("suspiciousTuberculosis", new Column("SUSPICIOUS_TUBERCULOSIS", "suspiciousTuberculosis", "肺结核可疑症状者数"));
        phChronicDisease.getColumns().put("managedTuberculosis", new Column("MANAGED_TUBERCULOSIS", "managedTuberculosis", "肺结核患者管理人数"));
        //肺结核患者人数没有 ,需要填报
        phChronicDisease.getColumns().put("noticeBaseTuberculosis", new Column("NOTICE_BASE_TUBERCULOSIS", "noticeBaseTuberculosis", "肺结核辖区内同期经上级定点医疗机构确诊并通知基层管理人数"));
        phChronicDisease.getColumns().put("standardTuberculosis", new Column("STANDARD_TUBERCULOSIS", "standardTuberculosis", "真实且规范管理的结核病患者档案"));
        phChronicDisease.getColumns().put("regularMedicationTuberculosi", new Column("REGULAR_MEDICATION_TUBERCULOSI", "regularMedicationTuberculosi", "按要求规则服药的患者人数"));
        phChronicDisease.getColumns().put("finishTreatmentTuberculosis", new Column("FINISH_TREATMENT_TUBERCULOSIS", "finishTreatmentTuberculosis", "同期辖区内已完成治疗的肺结核患者人数"));
        tables.put("phChronicDisease", phChronicDisease);

        // 公卫病种变化表 (舍弃)
        TableObjectReq gdcTable = new TableObjectReq("GW_DISEASE_CHANGE_VIEW");
        gdcTable.setNotToDept(true);
        gdcTable.setNotPointOfTime(false);
        gdcTable.setOrgIdColumn("org_id_gw");
        gdcTable.getColumns().put("hypertensions", new Column("hypertension_num", "hypertensions", "高血压人数"));
        gdcTable.getColumns().put("hypertensionManagers", new Column("hypertension_man_num", "hypertensionManagers", "高血压管理人数"));
        gdcTable.getColumns().put("hypertensionSpecificManagers", new Column("hypertension_specific_man_num", "hypertensionSpecificManagers", "高血压规范管理人数"));
        gdcTable.getColumns().put("hypertensionBloodControls", new Column("hypertension_blood_cont_num", "hypertensionBloodControls", "高血压血压控制人数"));
        gdcTable.getColumns().put("diabetes", new Column("diabetes_num", "diabetes", "糖尿病人数"));
        gdcTable.getColumns().put("diabetesManagers", new Column("diabetes_man_num", "diabetesManagers", "糖尿病管理人数"));
        gdcTable.getColumns().put("diabetesSpecificManagers", new Column("diabetes_specific_man_num", "diabetesSpecificManagers", "糖尿病规范管理人数"));
        gdcTable.getColumns().put("diabetesControls", new Column("diabetes_cont_num", "diabetesControls", "糖尿病血糖控制人数"));
        gdcTable.getColumns().put("mentalIllnesss", new Column("mental_illness_num", "mentalIllnesss", "精神病人数"));
        gdcTable.getColumns().put("mentalIllnessOut", new Column("mental_illness_out_num", "mentalIllnessOut", "严重精神障碍患者检出人数"));
        gdcTable.getColumns().put("mentalIllnessManagers", new Column("mental_illness_man_num", "mentalIllnessManagers", "严重精神障碍患者管理人数"));
        // gdcTable.getColumns().put("mentalIllnessManagers", new Column("mental_illness_man_num", "mentalIllnessManagers", "严重精神障碍患者规范管理人数"));
        gdcTable.getColumns().put("tuberculosis", new Column("tuberculosis_num", "tuberculosis", "肺结核人数"));
        gdcTable.getColumns().put("tuberculosisManagers", new Column("tuberculosis_man_num", "tuberculosisManagers", "肺结核规范管理人数"));  //表中是规范管理也是管理
        gdcTable.getColumns().put("tuberculosisRegDrugs", new Column("tuberculosis_reg_drug_num", "tuberculosisRegDrugs", "糖尿病规则服药人数"));  //表中是规范管理也是管理
        gdcTable.getColumns().put("oldPeoples", new Column("old_people_num", "oldPeoples", "老年人人数"));
        gdcTable.getColumns().put("oldPeopleHealthServices", new Column("old_people_man_num", "oldPeopleHealthServices", "老年人健康管理服务人数"));
        gdcTable.getColumns().put("underThreeAgePeoples", new Column("therr_people_num", "underThreeAgePeoples", "0-3岁儿童人数"));
        gdcTable.getColumns().put("underThreeAgeHealthManagers", new Column("therr_people_man_num", "underThreeAgeHealthManagers", "0-3岁儿童健康管理服务人数"));
        tables.put("gdcTable", gdcTable);

        // 常量临时表
        TableObjectReq constTable = new TableObjectReq("dual");
        constTable.getColumns().put("SpecificationMentalDisease", new Column("0", "SpecificationMentalDisease", "严重精神障碍患者规范管理率", true));
        constTable.getColumns().put("inoculationRoom", new Column("0", "inoculationRoom", "接种证查验间数", true));
        constTable.getColumns().put("remoteAreaNum", new Column("0", "remoteAreaNum", "远程医疗服务地区数", true));
        constTable.getColumns().put("areaNum", new Column("7", "areaNum", "珠海地区数", true));
        constTable.getColumns().put("remoteHospitalNum", new Column("0", "remoteHospitalNum", "开展远程医疗服务公立医院数", true));
        constTable.getColumns().put("hospitalNum", new Column("305", "hospitalNum", "医疗服务公立医院数", true));
        constTable.getColumns().put("haveGeneralDocOrgNum", new Column("200", "haveGeneralDocOrgNum", "有全科医生的卫生院数量", true));
        constTable.getColumns().put("baseOrgNum", new Column("288", "baseOrgNum", "基层机构数量", true));
        constTable.setNotConstTable(false);
        constTable.setNotToDept(true);
        tables.put("constTable", constTable);

        // 民营医疗机构表
        TableObjectReq privateOrgTable =  new TableObjectReq("(select * from sys_org s where s.IFGLJG='0')");
        privateOrgTable.setNotToDept(true);
        privateOrgTable.setOrgIdColumn("org_id");
        privateOrgTable.setNotConstTable(false);
        privateOrgTable.setAreaTable(false);
        privateOrgTable.setNotPointOfTime(false);
        privateOrgTable.getColumns().put("orgId", new Column("a.org_id", "orgId", "机构id", true));
        privateOrgTable.getColumns().put("orgName", new Column("a.org_name", "orgName", "机构名称", true));
        privateOrgTable.getColumns().put("addressId", new Column("a.AREA_ID", "addressId", "区域id", true));
        privateOrgTable.getColumns().put("addressName", new Column("a.AREA_NAME", "addressName", "区域名称", true));
        privateOrgTable.getColumns().put("orgGrade", new Column("decode(a.org_grade, '1', '一级', '2', '二级', '3', '三级' , '其他')", "orgGrade", "机构等级", true));
        privateOrgTable.getColumns().put("address", new Column("a.address", "address", "地址", true));
        privateOrgTable.getColumns().put("linkman", new Column("a.linkman", "linkman", "联系人", true));
        privateOrgTable.getColumns().put("president", new Column("a.principal", "president", "院长", true));
        privateOrgTable.getColumns().put("foundingTime", new Column("to_char(a.founding_time,'yyyy-MM-dd')", "foundingTime", "创建时间", true));
        tables.put("privateOrgTable", privateOrgTable);

        //所有机构表
        TableObjectReq orgTable =  new TableObjectReq("sys_org");
        orgTable.setNotToDept(true);
        orgTable.setOrgIdColumn("org_id");
        orgTable.setNotConstTable(false);
        orgTable.setAreaTable(false);
        orgTable.setNotPointOfTime(false);
        orgTable.getColumns().put("privateOrgNum", new Column("decode(og.IFGLJG,'0',1,0)", "privateOrgNum", "私立机构数量", true));
        orgTable.getColumns().put("publicOrgNum", new Column("decode(og.IFGLJG,'1',1,0)", "publicOrgNum", "公立机构数量", true));
        orgTable.getColumns().put("oneOrgNum", new Column("(case when og.ORG_GRADE='1'  and og.IFGLJG = '1' then 1 else 0 end)", "oneOrgNum", "一级机构数量", true));
        orgTable.getColumns().put("allOrgNum", new Column("1", "allOrgNum", "所有机构数量", true));
        tables.put("orgTable", orgTable);

        //私营机构填报表
        TableObjectReq privateOrgInstitutionView =  new TableObjectReq("PRIVATE_ORG_INSTITUTION_VIEW");
        privateOrgInstitutionView.setNotToDept(true);
        privateOrgInstitutionView.setOrgIdColumn("org_id");
        privateOrgInstitutionView.getColumns().put("privateOrgTreatNum", new Column("PRIVATE_ORG_TREAT_NUM", "privateOrgTreatNum", "民营医疗机构诊疗人次数"));
        privateOrgInstitutionView.getColumns().put("privateOrgOutHisNum", new Column("PRIVATE_ORG_OUT_HIS_NUM", "privateOrgOutHisNum", "民营医疗机构出院人次数"));
        privateOrgInstitutionView.getColumns().put("privateOrgBedNum", new Column("PRIVATE_ORG_BED_NUM", "privateOrgBedNum", "民营医院床位数"));
        privateOrgInstitutionView.getColumns().put("addressName", new Column("og.area_name", "addressName", "区域名称",true));
        privateOrgInstitutionView.getColumns().put("addressId", new Column("og.area_id", "addressId", "区域id",true));
        privateOrgInstitutionView.getColumns().put("foundingTime", new Column("og.founding_time", "foundingTime", "创建时间",true));
        privateOrgInstitutionView.getColumns().put("orgName", new Column("og.org_name", "orgName", "机构名称",true));
        privateOrgInstitutionView.getColumns().put("orgId", new Column("og.org_id", "orgId", "机构名称",true));
        tables.put("privateOrgInstitutionView", privateOrgInstitutionView);

        //--------------------------------------------------分级诊疗---------------------------------------------------------------------------------
        // 住院门诊 分级诊疗   不能使用
        TableObjectReq gradedDisTable = new TableObjectReq("GRADED_DIAGNOSIS_VIEW");
        gradedDisTable.setNotToDept(true);
        gradedDisTable.setOrgIdColumn("org_id");
        gradedDisTable.getColumns().put("totalReferralNumber", new Column("TOTAL_REFERRAL_NUMBER", "totalReferralNumber", "总转诊人次"));
        gradedDisTable.getColumns().put("outLowerNumber", new Column("LOWER_NUMBER", "outLowerNumber", "门诊下转人数"));
        gradedDisTable.getColumns().put("inpLowerNumber", new Column("HOSPITALIZATION_LOWER_NUMBER", "inpLowerNumber", "住院下转人次"));
        gradedDisTable.getColumns().put("tThreeOutLowerNumber", new Column("(case when og.ORG_GRADE in ('2','3') then nvl(a.LOWER_NUMBER,0) else 0 end)", "tThreeOutLowerNumber", "二三级机构门诊下转人数",true));
        gradedDisTable.getColumns().put("tTInpLowerNumber", new Column("(case when og.ORG_GRADE in ('2','3') then nvl(a.HOSPITALIZATION_LOWER_NUMBER,0) else 0 end)", "tTInpLowerNumber", "二三级机构住院下转人次",true));
        gradedDisTable.getColumns().put("tTLowerNumber", new Column("(case when og.ORG_GRADE in ('2','3') then nvl(a.HOSPITALIZATION_LOWER_NUMBER,0) + nvl(a.LOWER_NUMBER,0) else 0 end)", "tTLowerNumber", "二三级机构转诊人次",true));
        gradedDisTable.getColumns().put("baseUpperNumber", new Column("(case when og.ORG_GRADE = '1' then nvl(a.DEPARTMENT_UPPER_NUMBER,0) + nvl(a.INPT_UPPER_NUMBER,0) else 0 end)", "baseUpperNumber", "基层机构转诊人次",true));

        gradedDisTable.getColumns().put("outUpperNumber", new Column("DEPARTMENT_UPPER_NUMBER", "outUpperNumber", "门诊上转人次"));
        gradedDisTable.getColumns().put("inpUpperNumber", new Column("INPT_UPPER_NUMBER", "inpUpperNumber", "住院上转人次"));
        gradedDisTable.getColumns().put("baseOutUpperNumber", new Column("decode(og.ORG_GRADE,'1',a.DEPARTMENT_UPPER_NUMBER,0)", "baseOutUpperNumber", "基层门诊上转人次",true));
        gradedDisTable.getColumns().put("baseInpUpperNumber", new Column("decode(og.ORG_GRADE,'1',a.INPT_UPPER_NUMBER,0)", "baseInpUpperNumber", "基层住院上转人次",true));
//        tables.put("gradedDisTable", gradedDisTable);

        //分级诊疗抽取表1.0
        TableObjectReq referralManView = new TableObjectReq("REFERRAL_MAN_VIEW");
        referralManView.setNotToDept(true);
        referralManView.setOrgIdColumn("org_id");
        referralManView.getColumns().put("MZSZRC", new Column("MZSZRC", "MZSZRC", "门诊上转人次"));
        referralManView.getColumns().put("MZXZRC", new Column("MZXZRC", "MZXZRC", "门诊下转人次"));
        referralManView.getColumns().put("ZYSZRC", new Column("ZYSZRC", "ZYSZRC", "住院上转人次"));
        referralManView.getColumns().put("ZYXZRC", new Column("ZYXZRC", "ZYXZRC", "住院下转人次"));
        referralManView.getColumns().put("OTHERSZRS", new Column("OTHERSZRS", "OTHERSZRS", "其它上转人次"));
        referralManView.getColumns().put("OTHERXZRS", new Column("OTHERXZRS", "OTHERXZRS", "其它下转人次"));
        referralManView.getColumns().put("MZFROMJCSZRS", new Column("MZFROMJCSZRS", "MZFROMJCSZRS", "从基层门诊上转人次"));
        referralManView.getColumns().put("ZYFROMJCSZRS", new Column("ZYFROMJCSZRS", "ZYFROMJCSZRS", "从基层住院上转人次"));
        referralManView.getColumns().put("OTHERFROMJCSZRS", new Column("OTHERFROMJCSZRS", "OTHERFROMJCSZRS", "从基层其它上转人次"));
        referralManView.getColumns().put("MZTOJCXZRS", new Column("MZTOJCXZRS", "MZTOJCXZRS", "二三级医疗机构门诊往基层下转人次"));
        referralManView.getColumns().put("ZYTOJCXZRS", new Column("ZYTOJCXZRS", "ZYTOJCXZRS", "二三级医疗机构住院往基层下转人次"));
        referralManView.getColumns().put("allBaseDown", new Column("(a.ZYTOJCXZRS+a.MZTOJCXZRS)", "allBaseDown", "二三级医疗机构总住院和门诊往基层下转人次",true));
        referralManView.getColumns().put("OTHERTOJCXZRS", new Column("OTHERTOJCXZRS", "OTHERTOJCXZRS", "二三级医疗机构其他住院往基层下转人次"));
        referralManView.getColumns().put("tTNumber", new Column("(case when og.ORG_GRADE in ('2','3') then (a.MZXZRC+a.ZYXZRC+a.MZSZRC+a.ZYSZRC) else 0 end )", "tTNumber", "二三级医疗机构总住院和门诊转诊人次",true));

        tables.put("referralManView", referralManView);

        //公卫健康教育表1.0  要使用  org_gw_id 关联
        TableObjectReq gwHealthyEduView = new TableObjectReq("GW_HEALTHY_EDU_VIEW");
        gwHealthyEduView.setNotToDept(true);
        gwHealthyEduView.setOrgIdColumn("org_id_gw");
        gwHealthyEduView.getColumns().put("JKJYXCLS", new Column("JKJYXCLS", "JKJYXCLS", "教育宣传栏信息"));
        gwHealthyEduView.getColumns().put("KZJKZXHDS", new Column("KZJKZXHDS", "KZJKZXHDS", "开展健康咨询活动数"));
        gwHealthyEduView.getColumns().put("TGYSZLZS", new Column("TGYSZLZS", "TGYSZLZS", "提供印刷资料的种数"));
        gwHealthyEduView.getColumns().put("TGYXZLZS", new Column("TGYXZLZS", "TGYXZLZS", "提供音像资料的种数"));
        gwHealthyEduView.getColumns().put("JKZSJZ", new Column("JKZSJZ", "JKZSJZ", "办健康知识讲座次数"));
        gwHealthyEduView.getColumns().put("WCSJBZGZS", new Column("WCSJBZGZS", "WCSJBZGZS", "按要求完成上级布置的效果评估调查"));
        tables.put("gwHealthyEduView", gwHealthyEduView);

        //  分级诊疗 填报指标表
        TableObjectReq gradedDisReportTable = new TableObjectReq("GRADE_DIS_REPORT_VIEW");
        gradedDisReportTable.setNotToDept(true);
        gradedDisReportTable.setOrgIdColumn("org_id");
        gradedDisReportTable.getColumns().put("expertDownBaseNum", new Column("EXPERT_DOWN_BASE_NUM", "expertDownBaseNum", "专家挂号源下放基层数量"));
        gradedDisReportTable.getColumns().put("expertNum", new Column("EXPERT_NUM", "expertNum", "专家挂号源总数量"));
        gradedDisReportTable.getColumns().put("daySurgeryNum", new Column("DAY_SURGERY_NUM", "daySurgeryNum", "公立医院开展日间手术次数"));
        gradedDisReportTable.getColumns().put("dayPersonSurgeryNum", new Column("DAY_PERSON_SURGERY_NUM", "dayPersonSurgeryNum", "私立医院开展日间手术次数"));
        gradedDisReportTable.getColumns().put("diseasesPayTypeNum", new Column("DISEASES_PAY_TYPE_NUM", "diseasesPayTypeNum", "实行按病种付费的病种数"));
        gradedDisReportTable.getColumns().put("diseasesTakeTypeNum", new Column("DISEASES_TAKE_TYPE_NUM", "diseasesTakeTypeNum", "实行按病种收费的病种数"));
        gradedDisReportTable.getColumns().put("downTechnologyNum", new Column("DOWN_TECHNOLOGY_NUM", "downTechnologyNum", "医院下派基层专业技术人员数"));
        gradedDisReportTable.getColumns().put("baseHighPostNum", new Column("BASE_HIGH_POST_NUM", "baseHighPostNum", "基层的高级职称（副高及以上）医务人员数量"));
        gradedDisReportTable.getColumns().put("downBaseDoctorNum", new Column("DOWN_BASE_DOCTOR_NUM", "downBaseDoctorNum", "派驻各基层医疗卫生机构医师数"));
        gradedDisReportTable.getColumns().put("docRegisteredNum", new Column("DOC_REGISTERED_NUM", "docRegisteredNum", "二级及以上医院派驻各基层医疗卫生机构医师挂号量"));
        gradedDisReportTable.getColumns().put("generalDocRecruitNum", new Column("GENERAL_DOC_RECRUIT_NUM", "generalDocRecruitNum", "全科医生规范化培养招收人数"));
        gradedDisReportTable.getColumns().put("generalDocQualNum", new Column("GENERAL_DOC_QUAL_NUM", "generalDocQualNum", "全科医生规范化培养取得合格证人数"));
        gradedDisReportTable.getColumns().put("assGeneralDocRecruitNum", new Column("ASS_GENERAL_DOC_RECRUIT_NUM", "assGeneralDocRecruitNum", "助理全科医生规范化培养招收人数"));
        gradedDisReportTable.getColumns().put("assGeneralDocQualNum", new Column("ASS_GENERAL_DOC_QUAL_NUM", "assGeneralDocQualNum", "助理全科医生规范化培养取得合格证人数"));
        gradedDisReportTable.getColumns().put("tranGeneralDocRecruitNum", new Column("TRAN_GENERAL_DOC_RECRUIT_NUM", "tranGeneralDocRecruitNum", "全科医生转岗培训招收人数"));
        gradedDisReportTable.getColumns().put("tranGeneralDocQualNum", new Column("TRAN_GENERAL_DOC_QUAL_NUM", "tranGeneralDocQualNum", "全科医生转岗培训取得合格证人数"));
        gradedDisReportTable.getColumns().put("speGeneralDocNum", new Column("SPE_GENERAL_DOC_NUM", "speGeneralDocNum", "设立全科医生特设岗位数"));
//        gradedDisReportTable.getColumns().put("haveGeneralDocNum", new Column("HAVE_GENERAL_DOC_NUM", "haveGeneralDocNum", "拥有全科医生数量"));
        gradedDisReportTable.getColumns().put("keyPopulationSignNum", new Column("KEY_POPULATION_SIGN_NUM", "keyPopulationSignNum", "重点人群签约人口数"));
        gradedDisReportTable.getColumns().put("keyPopulationNum", new Column("KEY_POPULATION_NUM", "keyPopulationNum", "重点人群数"));
        gradedDisReportTable.getColumns().put("remotePeoNum", new Column("REMOTE_PEO_NUM", "remotePeoNum", "远程医疗服务人次"));

        gradedDisReportTable.getColumns().put("haveOneDocNum", new Column("HAVE_ONE_DOC_NUM", "haveOneDocNum", "是否拥有一名以上全科医生的卫生院"));
        gradedDisReportTable.getColumns().put("isRemoteHis", new Column("IS_REMOTE_HIS", "isRemoteHis", "是否开展远程医疗服务的公立医院"));
        gradedDisReportTable.getColumns().put("zzOutAreaNum", new Column("ZZ_OUT_AREA_NUM", "zzOutAreaNum", "转诊或者转院到统筹区域外人数"));
        gradedDisReportTable.getColumns().put("ydjzrs", new Column("YDJZRS", "ydjzrs", "异地就诊人数"));
        gradedDisReportTable.getColumns().put("isRemoteAreaNum", new Column("IS_REMOTE_AREA_NUM", "isRemoteAreaNum", "是否是远程医疗服覆盖区域"));
        gradedDisReportTable.getColumns().put("isRjssHisNum", new Column("IS_RJSS_HIS_NUM", "isRjssHisNum", "是否开展日间手术公立医院手术医院"));

        tables.put("gradedDisReportTable", gradedDisReportTable);

        // 门诊住院诊疗人次变化记录表 分级诊疗
        TableObjectReq consulationRecodeTable = new TableObjectReq("CONSULATION_RECODE_VIEW");
        consulationRecodeTable.setNotToDept(true);
        consulationRecodeTable.setOrgIdColumn("org_id");
        consulationRecodeTable.getColumns().put("outsideMedicalTreatNum", new Column("OUTSIDE_MEDICAL_TREATMENT_NUM", "outsideMedicalTreatNum", "异地就诊人次"));
        tables.put("consulationRecodeTable", consulationRecodeTable);

        // 门诊账单医院统计日报表 分级诊疗
        TableObjectReq outStatisticsTable = new TableObjectReq("OUT_STATISTICS_VIEW");
        outStatisticsTable.setNotToDept(true);
        outStatisticsTable.setOrgIdColumn("org_id");
        outStatisticsTable.getColumns().put("outpatientNum", new Column("OUTPATIENT_NUM", "outpatientNum", "门诊人次"));
        outStatisticsTable.getColumns().put("baseOutpatientNum", new Column("decode(og.ORG_GRADE,'1',(nvl(a.OUTPATIENT_NUM,0)+nvl(a.EMERGENCY_NUM,0)),0)", "baseOutpatientNum", "基层门急诊疗人次",true));
        outStatisticsTable.getColumns().put("twoOutpatientNum", new Column("decode(og.ORG_GRADE,'2',(nvl(a.OUTPATIENT_NUM,0)+nvl(a.EMERGENCY_NUM,0)),0)", "twoOutpatientNum", "二级机构诊疗人次",true));
        outStatisticsTable.getColumns().put("threeOutpatientNum", new Column("decode(og.ORG_GRADE,'3',(nvl(a.OUTPATIENT_NUM,0)+nvl(a.EMERGENCY_NUM,0)),0)", "threeOutpatientNum", "三级机构诊疗人次",true));
        outStatisticsTable.getColumns().put("tTOutpatientNum", new Column("(case when og.ORG_GRADE in ('2','3') then (nvl(a.OUTPATIENT_NUM,0)+nvl(a.EMERGENCY_NUM,0)) else 0 end)", "tTOutpatientNum", "二三级机构诊疗人次",true));
        outStatisticsTable.getColumns().put("emergencyNum", new Column("EMERGENCY_NUM", "emergencyNum", "急诊人次"));
        outStatisticsTable.getColumns().put("outAllPatientNum", new Column("a.EMERGENCY_NUM+a.OUTPATIENT_NUM", "outAllPatientNum", "门(急)诊人次",true));
        tables.put("outStatisticsTable", outStatisticsTable);

        // 住院账单医院统计日报表 分级诊疗
        TableObjectReq inpStatisticsTable = new TableObjectReq("INP_STATISTICS_VIEW");
        inpStatisticsTable.setNotToDept(true);
        inpStatisticsTable.setOrgIdColumn("org_id");
        inpStatisticsTable.getColumns().put("hospitalizationNum", new Column("ADMISSION_NUM", "hospitalizationNum", "住院人次"));
        inpStatisticsTable.getColumns().put("dischargedNum", new Column("DISCHARGED_NUM", "dischargedNum", "出院人次"));
        inpStatisticsTable.getColumns().put("baseHospitalizationNum", new Column("decode(og.ORG_GRADE,'1',a.ADMISSION_NUM,0)", "baseHospitalizationNum", "基层住院人次",true));
        inpStatisticsTable.getColumns().put("twoHospitalizationNum", new Column("decode(og.ORG_GRADE,'2',a.ADMISSION_NUM,0)", "twoHospitalizationNum", "二级机构住院人次",true));
        inpStatisticsTable.getColumns().put("threeHospitalizationNum", new Column("decode(og.ORG_GRADE,'3',a.ADMISSION_NUM,0)", "threeHospitalizationNum", "三级机构住院人次",true));
        tables.put("inpStatisticsTable", inpStatisticsTable);

        // 家庭医生信息变化表 分级诊疗
        TableObjectReq familyDoctorChangeTable = new TableObjectReq("FAMILY_DOCTOR_CHANGE_VIEW");
        familyDoctorChangeTable.setNotToDept(true);
        familyDoctorChangeTable.setNotPointOfTime(false);
        familyDoctorChangeTable.setCustomPointOfTimeFlag(true);
        familyDoctorChangeTable.setOrgIdColumn("org_id");
        familyDoctorChangeTable.getColumns().put("familyDoctorSingNum", new Column("FAMILY_DOCTOR_SIGN_NUM", "familyDoctorSingNum", "家庭签约医生人数"));
        familyDoctorChangeTable.getColumns().put("peopleSignNum", new Column("PEOPLE_SIGN_NUM", "peopleSignNum", "签约人口数"));
        tables.put("familyDoctorChangeTable", familyDoctorChangeTable);

        // 机构信息变化表 分级诊疗  不能使用
        TableObjectReq orgChangeInfoTable = new TableObjectReq("ORG_CHANGE_INFO_VIEW");
        orgChangeInfoTable.setNotToDept(true);
        orgChangeInfoTable.setOrgIdColumn("org_id");
        orgChangeInfoTable.getColumns().put("clinicalPathwayManNum", new Column("CLINICAL_PATHWAY_MAN_NUM", "clinicalPathwayManNum", "临床路径管理人次"));
//        tables.put("orgChangeInfoTable", orgChangeInfoTable);

        //临床路径管理表
        TableObjectReq pathwayManView = new TableObjectReq("PATHWAY_MAN_VIEW");
        pathwayManView.setDeptIdColumn("dept_id");
        pathwayManView.setOrgIdColumn("org_id");
        pathwayManView.getColumns().put("managerCpath", new Column("MANAGER_CPATH", "managerCpath", "实施临床路径管理的病例数"));
        pathwayManView.getColumns().put("managerCpathInpatient", new Column("MANAGER_CPATH_INPATIENT", "managerCpathInpatient", "临床路径管理的住院病人数量"));
        tables.put("pathwayManView", pathwayManView);

        // 机构信息变化表 分级诊疗
        TableObjectReq sinkingStaffTable = new TableObjectReq("SINKING_STAFF");
        sinkingStaffTable.setNotToDept(true);
        sinkingStaffTable.setOrgIdColumn("org_id");
        sinkingStaffTable.getColumns().put("sinkNum", new Column("(case when og.ORG_GRADE in ('2','3') then nvl(a.SINK_NUM,0) else 0 end)", "sinkNum", "二级以上医院下沉就诊医生人数",true));
        sinkingStaffTable.getColumns().put("technologyNum", new Column("(case when og.ORG_GRADE in ('2','3') then nvl(a.TECHNOLOGY_NUM,0) else 0 end)", "technologyNum", "二级以上医院向基层机构派出专业技术人数",true));
        sinkingStaffTable.getColumns().put("dispatchAdminNum", new Column("(case when og.ORG_GRADE in ('2','3') then nvl(a.DISPATCH_ADMIN_NUM,0) else 0 end)", "dispatchAdminNum", "二级以上医院向基层派出管理人才人数",true));
        sinkingStaffTable.getColumns().put("studyNum", new Column("decode(og.ORG_GRADE,'1',nvl(a.STUDY_NUM,0),0)", "studyNum", "去上级医院进修学习人数",true));
        sinkingStaffTable.getColumns().put("downRegisteredNum", new Column("(case when og.ORG_GRADE in ('2','3') then nvl(a.DOWN_REGISTERED_NUM,0) else 0 end)", "downRegisteredNum", "医院派驻各基层医疗卫生机构医师挂号量(下沉医师挂号量)",true));

        tables.put("sinkingStaffTable", sinkingStaffTable);

        //----------------人力资源表 分级诊疗--------------------------------------------------------------
        TableObjectReq hrBaseInformationTable = new TableObjectReq("HR_BASE_INFORMATION");
        hrBaseInformationTable.setNotToDept(true);
        hrBaseInformationTable.setOrgIdColumn("ORG_ID_WJW");
        hrBaseInformationTable.setDateColumn("STATISTICS_DATE");//入职时间
        hrBaseInformationTable.setNotPointOfTime(false);
        hrBaseInformationTable.setCustomPointOfTimeFlag(true);
        hrBaseInformationTable.getColumns().put("allTwoTechnologyNum", new Column("(case when og.ORG_GRADE in ('2','3') and a.ARTISAN in ('1','2') then 1 else 0 end)", "allTwoTechnologyNum", "二级及以上医院下派基层专业技术人员",true));
        hrBaseInformationTable.getColumns().put("baseHighLevelNum", new Column("(case when og.ORG_GRADE = '1' and a.POST_LEVEL_NUM in ('4','5') and a.POST_TYPE in ('2','3') then 1 else 0 end)", "baseHighLevelNum", "基层的高级职称(副高及以上)医务人员数量",true));
        hrBaseInformationTable.getColumns().put("baseMedicalPeoNum", new Column("(case when og.ORG_GRADE  = '1' and a.POST_TYPE in ('1','2','3','4')  then 1 else 0 end)", "baseMedicalPeoNum", "基层医务人员数量",true));
        hrBaseInformationTable.getColumns().put("generalDocNum", new Column("(case when a.IS_GENERAL = '1' then 1 else 0 end)", "generalDocNum", "全科医生数量",true));
        hrBaseInformationTable.getColumns().put("baseGeneralDocNum", new Column("(case when og.ORG_GRADE = '1' and  a.IS_GENERAL = '1' then 1 else 0 end)", "baseGeneralDocNum", "基层全科医生数量",true));
        //TODO : flage allDoctorNum
        hrBaseInformationTable.getColumns().put("allDoctorNum", new Column("(case when a.POST_TYPE in ('2','3') then 1 else 0 END)", "allDoctorNum", "医生数",true));
        hrBaseInformationTable.getColumns().put("allNurseNum", new Column("(case when a.POST_TYPE = '1' or a.IS_REGISTERED_NURSE = '1' then 1 else 0 END)", "allNurseNum", "护士数",true));
//        hrBaseInformationTable.getColumns().put("miDoctorNum", new Column("decode(a.IS_MIDOCTOR,'1',1,0)", "miDoctorNum", "医保医生数",true));
        hrBaseInformationTable.getColumns().put("highLevelNum", new Column("(case when a.POST_LEVEL_NUM in ('4','5') and a.POST_TYPE in ('2','3') then 1 else 0 end)", "highLevelNum", "高级职称(副高及以上)医务人员数量",true));
        hrBaseInformationTable.getColumns().put("medicalPeoNum", new Column("(case when a.POST_TYPE in ('1','2','3','4')  then 1 else 0 end)", "medicalPeoNum", "医务人员数量",true));
        hrBaseInformationTable.getColumns().put("mzDocNum", new Column("decode(a.IS_ANESTHESIA,'1',1,0)", "mzDocNum", "麻醉医生数量",true));
        hrBaseInformationTable.getColumns().put("ekDocNum", new Column("decode(a.IS_PEDIATRICIAN,'1',1,0)", "ekDocNum", "儿科医生数量",true));
        hrBaseInformationTable.getColumns().put("zzDocNum", new Column("decode(a.IS_SEVERE,'1',1,0)", "zzDocNum", "重症医生数量",true));
        hrBaseInformationTable.getColumns().put("blDocNum", new Column("decode(a.IS_PATHOLOGY,'1',1,0)", "blDocNum", "病理医生数量",true));
        hrBaseInformationTable.getColumns().put("zyDocNum", new Column("decode(a.IS_CHINESE_MEDICINE,'1',1,0)", "zyDocNum", "中医医生数量",true));

        hrBaseInformationTable.getColumns().put("ltxrys", new Column("(case when (a.IS_RETIRE = '1') then 1 else 0 end)", "ltxrys", "离退休人数",true));
        tables.put("hrBaseInformationTable", hrBaseInformationTable);

        TableObjectReq hrBaseInformationTwo = new TableObjectReq("HR_BASE_INFORMATION");
        hrBaseInformationTwo.setNotToDept(true);
        hrBaseInformationTwo.setOrgIdColumn("ORG_ID_WJW");
        hrBaseInformationTwo.setDateColumn("STATISTICS_DATE");//入职时间
        hrBaseInformationTwo.setNotPointOfTime(false);
        hrBaseInformationTwo.setCustomPointOfTimeFlag(true);
        hrBaseInformationTwo.getColumns().put("allDoctorNum", new Column("(case when a.POST_TYPE in ('2','3') then 1 else 0 END)", "allDoctorNum", "医生数",true));
//        hrBaseInformationTwo.getColumns().put("allDoctorNumNx", new Column("(case when a.POST_TYPE in ('2','3') then a.ANNUAL_SALARY else 0 end)", "allDoctorNumNx", "医生年薪",true));

        hrBaseInformationTwo.getColumns().put("allNurseNum", new Column("(case when a.POST_TYPE = '1' or a.IS_REGISTERED_NURSE = '1' then 1 else 0 END)", "allNurseNum", "护士数",true));
//        hrBaseInformationTwo.getColumns().put("miDoctorNum", new Column("decode(a.IS_MIDOCTOR,'1',1,0)", "miDoctorNum", "医保医生数",true));
        hrBaseInformationTwo.getColumns().put("total", new Column("1", "total", "总人数",true));
        hrBaseInformationTwo.getColumns().put("bzrs", new Column("(case when a.AUTHORIZED_PERSON = '1' then 1 else 0 end)", "bzrs", "编制在册人数",true));
        hrBaseInformationTwo.getColumns().put("zgrs", new Column("(case when a.IS_ON_DUTY = '1' then 1 else 0 end)", "zgrs", "在岗在册人数",true));
        hrBaseInformationTwo.getColumns().put("zgzcrs", new Column("(case when a.IS_ON_DUTY = '1' or a.AUTHORIZED_PERSON = '1' then 1 else 0 end)", "zgzcrs", "在岗在册人数",true));
        hrBaseInformationTwo.getColumns().put("htrs", new Column("(case when a.CONTRACT_EMPLOYMENT = '1' then 1 else 0 end)", "htrs", "合同人数",true));
        hrBaseInformationTwo.getColumns().put("lprs", new Column("(case when a.AUTHORIZED_PERSON = '3' then 1 else 0 end)", "lprs", "临聘人数",true));
        hrBaseInformationTwo.getColumns().put("lphzhtrs", new Column("(case when a.AUTHORIZED_PERSON = '3' or a.CONTRACT_EMPLOYMENT = '1' then 1 else 0 end)", "lphzhtrs", "临聘/合同人数",true));
        hrBaseInformationTwo.getColumns().put("bznry", new Column("(case when a.AUTHORIZED_PERSON = '1' or a.AUTHORIZED_PERSON = '2' then 1 else 0 end)", "bznry", "编制内人员",true));
        hrBaseInformationTwo.getColumns().put("bzwry", new Column("(case when a.CONTRACT_EMPLOYMENT = '1' or a.AUTHORIZED_PERSON = '3' then 1 else 0 end)", "bzwry", "编制外人员",true));
        hrBaseInformationTwo.getColumns().put("nanxingrs", new Column("(case when a.SEX = '1' then 1 else 0 end)", "nanxingrs", "男性人数",true));
        hrBaseInformationTwo.getColumns().put("nvxingrs", new Column("(case when a.SEX = '2' then 1 else 0 end)", "nvxingrs", "女性人数",true));
        hrBaseInformationTwo.getColumns().put("yhrys", new Column("(case when a.POST_TYPE in ('1','2','3','4') then 1 else 0 end)", "yhrys", "医护人员数",true));
        hrBaseInformationTwo.getColumns().put("qtgwrys", new Column("(case when a.POST_TYPE in ('6') then 1 else 0 end)", "qtgwrys", "其他岗位人员数",true));
        hrBaseInformationTwo.getColumns().put("qtzcrs", new Column("(case when a.POST_LEVEL_NUM = '6' then 1 else 0 end)", "qtzcrs", "其他职称人数",true));
        hrBaseInformationTwo.getColumns().put("qtzcrsyt", new Column("(case when a.POST_LEVEL_NUM in ('1','6') then 1 else 0 end)", "qtzcrsyt", "其他人数(员级别、其他)",true));
        hrBaseInformationTwo.getColumns().put("gjzcrs", new Column("(case when a.POST_LEVEL_NUM = '5' then 1 else 0 end)", "gjzcrs", "高级职称人数",true));
        hrBaseInformationTwo.getColumns().put("fgjzzrs", new Column("(case when a.POST_LEVEL_NUM ='4' then 1 else 0 end)", "fgjzzrs", "副高级职称人数",true));
        hrBaseInformationTwo.getColumns().put("zjzcrs", new Column("(case when a.POST_LEVEL_NUM ='3' then 1 else 0 end)", "zjzcrs", "中级职称人数",true));

        hrBaseInformationTwo.getColumns().put("cjzcrs", new Column("(case when a.POST_LEVEL_NUM ='2' then 1 else 0 end)", "cjzcrs", "初级职称人数",true));
        hrBaseInformationTwo.getColumns().put("yjzcrs", new Column("(case when a.POST_LEVEL_NUM ='1' then 1 else 0 end)", "yjzcrs", "员级职称人数",true));
        //各职称总人数
        hrBaseInformationTwo.getColumns().put("gzcrs", new Column("(case when a.POST_LEVEL_NUM in ('1','2','3','4','5','6') then 1 else 0 end)", "gzcrs", "各职称人数",true));

        //--编制内
        hrBaseInformationTwo.getColumns().put("nqtzcrs", new Column("(case when a.POST_LEVEL_NUM = '6' and (a.AUTHORIZED_PERSON = '1' or a.AUTHORIZED_PERSON = '2') then 1 else 0 end)", "nqtzcrs", "其他职称人数",true));
        hrBaseInformationTwo.getColumns().put("ngjzcrs", new Column("(case when a.POST_LEVEL_NUM = '5' and (a.AUTHORIZED_PERSON = '1' or a.AUTHORIZED_PERSON = '2') then 1 else 0 end)", "ngjzcrs", "高级职称人数",true));
        hrBaseInformationTwo.getColumns().put("nfgjzzrs", new Column("(case when a.POST_LEVEL_NUM ='4' and (a.AUTHORIZED_PERSON = '1' or a.AUTHORIZED_PERSON = '2') then 1 else 0 end)", "nfgjzzrs", "副高级职称人数",true));
        hrBaseInformationTwo.getColumns().put("nzjzcrs", new Column("(case when a.POST_LEVEL_NUM ='3' and (a.AUTHORIZED_PERSON = '1' or a.AUTHORIZED_PERSON = '2') then 1 else 0 end)", "nzjzcrs", "中级职称人数",true));
        hrBaseInformationTwo.getColumns().put("ncjzcrs", new Column("(case when a.POST_LEVEL_NUM ='2' and (a.AUTHORIZED_PERSON = '1' or a.AUTHORIZED_PERSON = '2') then 1 else 0 end)", "ncjzcrs", "初级职称人数",true));
        hrBaseInformationTwo.getColumns().put("nyjzcrs", new Column("(case when a.POST_LEVEL_NUM ='1' and (a.AUTHORIZED_PERSON = '1' or a.AUTHORIZED_PERSON = '2') then 1 else 0 end)", "nyjzcrs", "员级职称人数",true));
        hrBaseInformationTwo.getColumns().put("nbznzrs", new Column("(case when a.POST_LEVEL_NUM in ('6','5','4','3','2','1') and (a.AUTHORIZED_PERSON = '1' or a.AUTHORIZED_PERSON = '2') then 1 else 0 end)", "nbznzrs", "编制内总人数",true));

        //--编制外
        hrBaseInformationTwo.getColumns().put("wqtzcrs", new Column("(case when a.POST_LEVEL_NUM = '6' and (a.CONTRACT_EMPLOYMENT = '1' or a.AUTHORIZED_PERSON = '3') then 1 else 0 end)", "wqtzcrs", "其他职称人数",true));
        hrBaseInformationTwo.getColumns().put("wgjzcrs", new Column("(case when a.POST_LEVEL_NUM = '5' and (a.CONTRACT_EMPLOYMENT = '1' or a.AUTHORIZED_PERSON = '3') then 1 else 0 end)", "wgjzcrs", "高级职称人数",true));
        hrBaseInformationTwo.getColumns().put("wfgjzzrs", new Column("(case when a.POST_LEVEL_NUM ='4' and (a.CONTRACT_EMPLOYMENT = '1' or a.AUTHORIZED_PERSON = '3') then 1 else 0 end)", "wfgjzzrs", "副高级职称人数",true));
        hrBaseInformationTwo.getColumns().put("wzjzcrs", new Column("(case when a.POST_LEVEL_NUM ='3' and (a.CONTRACT_EMPLOYMENT = '1' or a.AUTHORIZED_PERSON = '3') then 1 else 0 end)", "wzjzcrs", "中级职称人数",true));
        hrBaseInformationTwo.getColumns().put("wcjzcrs", new Column("(case when a.POST_LEVEL_NUM ='2' and (a.CONTRACT_EMPLOYMENT = '1' or a.AUTHORIZED_PERSON = '3') then 1 else 0 end)", "wcjzcrs", "初级职称人数",true));
        hrBaseInformationTwo.getColumns().put("wyjzcrs", new Column("(case when a.POST_LEVEL_NUM ='1' and (a.CONTRACT_EMPLOYMENT = '1' or a.AUTHORIZED_PERSON = '3') then 1 else 0 end)", "wyjzcrs", "员级职称人数",true));
        hrBaseInformationTwo.getColumns().put("wbzwzrs", new Column("(case when a.POST_LEVEL_NUM in ('6','5','4','3','2','1') and (a.AUTHORIZED_PERSON = '1' or a.AUTHORIZED_PERSON = '3') then 1 else 0 end)", "wbzwzrs", "编制外总人数",true));
        hrBaseInformationTwo.getColumns().put("zyyss", new Column("decode(a.IS_REGISTERED_DOC,'1',1,0)", "zyyss", "执业医师数",true));
        hrBaseInformationTwo.getColumns().put("zyysnx", new Column("(case when a.IS_REGISTERED_DOC = '1' then a.ANNUAL_SALARY else 0 end)", "zyysnx", "执业医师年薪",true));
        hrBaseInformationTwo.getColumns().put("hsrys", new Column("(case when a.POST_TYPE ='1' then 1 else 0 end)", "hsrys", "护师人员数",true));

        hrBaseInformationTwo.getColumns().put("zchss", new Column("decode(a.IS_REGISTERED_NURSE,'1',1,0)", "zchss", "注册护士数",true));
//        hrBaseInformationTwo.getColumns().put("hsrynx", new Column("(case when a.POST_TYPE ='1' then a.ANNUAL_SALARY else 0 end)", "hsrynx", "护师人员年薪",true));
        hrBaseInformationTwo.getColumns().put("yjrys", new Column("(case when a.POST_TYPE ='3' then 1 else 0 end)", "yjrys", "医技人员数(填报)",true));
//        hrBaseInformationTwo.getColumns().put("yjrynx", new Column("(case when a.POST_TYPE ='3' then a.ANNUAL_SALARY else 0 end)", "yjrynx", "医技人员年薪",true));
        hrBaseInformationTwo.getColumns().put("ysrys", new Column("(case when a.POST_TYPE ='4' then 1 else 0 end)", "ysrys", "药师人员数",true));
//        hrBaseInformationTwo.getColumns().put("ysrynx", new Column("(case when a.POST_TYPE ='4' then a.ANNUAL_SALARY else 0 end)", "ysrynx", "药师人员年薪",true));
        hrBaseInformationTwo.getColumns().put("gqrys", new Column("(case when a.POST_TYPE ='5' or a.POST_TYPE ='6' then 1 else 0 end)", "gqrys", "工勤人员数",true));
//        hrBaseInformationTwo.getColumns().put("gqrynx", new Column("(case when a.POST_TYPE ='5' or a.POST_TYPE ='6' then a.ANNUAL_SALARY else 0 end)", "gqrynx", "工勤人员年薪",true));

        hrBaseInformationTwo.getColumns().put("xxhrys", new Column("(case when a.IS_INFORMATION = '1' then 1 else 0 end)", "xxhrys", "信息化人员数",true));
        hrBaseInformationTwo.getColumns().put("wsjdrys", new Column("(case when a.IS_SUPERVISE = '1' then 1 else 0 end)", "wsjdrys", "卫生监督人员数",true));
//        hrBaseInformationTwo.getColumns().put("dysl", new Column("(case when a.IS_PARTYMEMBER = '1'  then 1 else 0 end)", "dysl", "党员数量",true));
        hrBaseInformationTwo.getColumns().put("qkyss", new Column("(case when a.IS_GENERAL = '1' then 1 else 0 end)", "qkyss", "全科医生数",true));
//        hrBaseInformationTwo.getColumns().put("jskyss", new Column("(case when a.IS_PSYCHOLOGIST = '1' then 1 else 0 end)", "jskyss", "精神科医生数",true));
        hrBaseInformationTwo.getColumns().put("glrys", new Column("(case when a.IS_MANAGEMENT = '1' then 1 else 0 end)", "glrys", "管理人员数",true));
//        hrBaseInformationTwo.getColumns().put("glrynx", new Column("(case when a.IS_MANAGEMENT = '1' then a.ANNUAL_SALARY else 0 end)", "glrynx", "管理人员年薪",true));
        hrBaseInformationTwo.getColumns().put("wsjsrys", new Column("(case when a.ARTISAN = '1' then 1 else 0 end)", "wsjsrys", "卫生技术人员数",true));
//        hrBaseInformationTwo.getColumns().put("wsjsrynx", new Column("(case when a.ARTISAN = '1' then a.ANNUAL_SALARY else 0 end)", "wsjsrynx", "卫生技术人员年薪",true));
        hrBaseInformationTwo.getColumns().put("yjkxdtrhzgzrs", new Column("(case when a.POST_LEVEL_NUM = '5' or a.IS_LEADERFORSTUDY = '1' then 1 else 0 end)", "yjkxdtrhzgzrs", "引进学科带头人或高职人数",true));

        hrBaseInformationTwo.getColumns().put("bshrs", new Column("(case when a.EDUCATION = '6'  then 1 else 0 end)", "bshrs", "博士后",true));
        hrBaseInformationTwo.getColumns().put("bsrs", new Column("(case when a.EDUCATION = '3' then 1 else 0 end)", "bsrs", "博士",true));
        hrBaseInformationTwo.getColumns().put("ssrs", new Column("(case when a.EDUCATION = '2' then 1 else 0 end)", "ssrs", "硕士",true));
        hrBaseInformationTwo.getColumns().put("bkrs", new Column("(case when a.EDUCATION = '1'  then 1 else 0 end)", "bkrs", "本科",true));
        hrBaseInformationTwo.getColumns().put("dzrs", new Column("(case when a.EDUCATION = '4'  then 1 else 0 end)", "dzrs", "大专",true));
        hrBaseInformationTwo.getColumns().put("dzyxrs", new Column("(case when a.EDUCATION in ('4','5','99')   then 1 else 0 end)", "dzyxrs", "大专及以下",true));
        hrBaseInformationTwo.getColumns().put("zzjjxrs", new Column("(case when a.EDUCATION in ('5','99')   then 1 else 0 end)", "zzjjxrs", "中专及中技",true));
        hrBaseInformationTwo.getColumns().put("yjsysrs", new Column("(case when a.EDUCATION in ('2','3','6')   then 1 else 0 end)", "yjsysrs", "研究生及以上",true));
        hrBaseInformationTwo.getColumns().put("qtxlrs", new Column("(case when a.EDUCATION in ('99')   then 1 else 0 end)", "qtxlrs", "其他学历",true));
        hrBaseInformationTwo.getColumns().put("syxlrs", new Column("(case when a.EDUCATION in ('1','2','3','4','5','6','99')   then 1 else 0 end)", "syxlrs", "所有学历人数",true));
        hrBaseInformationTwo.getColumns().put("txrs", new Column("(case when a.IS_RETIRE = '1' then 1 else 0 end)", "txrs", "退休人员",true));
        hrBaseInformationTwo.getColumns().put("lzrs", new Column("(case when a.LEAVE_FLAG = '1' then 1 else 0 end)", "lzrs", "离职人员",true));
        hrBaseInformationTwo.getColumns().put("ltsj", new Column("SUBSTR(a.CALL_IN_TIME,1,4)", "ltsj", "调入/调出时间",true, ColumnEnum.STRING_TYPE));
        hrBaseInformationTwo.getColumns().put("lrlcsj", new Column("SUBSTR(a.IN_OUT_FLOOW_TIME,1,4)", "lrlcsj", "流入/流出时间",true, ColumnEnum.STRING_TYPE));
        tables.put("hrBaseInformationTwo", hrBaseInformationTwo);
        //离退休人员特殊计算
        TableObjectReq hrBaseInformationThree = new TableObjectReq("HR_BASE_INFORMATION");
        hrBaseInformationThree.setNotToDept(true);
        hrBaseInformationThree.setOrgIdColumn("ORG_ID_WJW");
        hrBaseInformationThree.setDateColumn("STATISTICS_DATE");//入职时间
        hrBaseInformationThree.setNotPointOfTime(false);
        hrBaseInformationThree.getColumns().put("txrs", new Column("(case when a.IS_RETIRE = '1' then 1 else 0 end)", "txrs", "退休人员",true));
        hrBaseInformationThree.getColumns().put("leaveDate", new Column("a.LEAVE_DATE", "leaveDate", "离职时间",true));
        tables.put("hrBaseInformationThree", hrBaseInformationThree);

        TableObjectReq hrLz = new TableObjectReq("select * from HR_BASE_INFORMATION");
        hrLz.setNotToDept(true);
        hrLz.setOrgIdColumn("org_id");
        //TODO : 时间判断不正确，原sql:（a.EMPLOYMENT_DATE = trunc(to_date('2019-12-31', 'yyyy-MM-dd') + 1, 'mm')），应该修改为 <=
        hrLz.setDateColumn("STATISTICS_DATE");//入职时间
        hrLz.setNotPointOfTime(false);


        // 挂号表 分级诊疗
        TableObjectReq outRegisteredTable = new TableObjectReq("OUT_REGISTERED_VIEW");
        outRegisteredTable.setNotToDept(true);
        outRegisteredTable.setOrgIdColumn("org_id");
        outRegisteredTable.getColumns().put("baseRegisteredNum", new Column("(case when og.ORG_GRADE = '1' then nvl(a.TOTAL_REGISTERED_NUM,0) else 0 end)", "baseRegisteredNum", "基层机构医师挂号量",true));
        tables.put("outRegisteredTable", outRegisteredTable);
        //上转病人病种表
        TableObjectReq upturnDiseasesView = new TableObjectReq("UPTURN_DISEASES_VIEW");
        upturnDiseasesView.setOrgIdColumn("org_id");
        upturnDiseasesView.setDeptIdColumn("dept_id");
        upturnDiseasesView.getColumns().put("diseasesName", new Column("a.DISEASES_NAME", "diseasesName", "病种名称",true));
        upturnDiseasesView.getColumns().put("patientNum", new Column("PATIENT_NUM", "patientNum", "上转病人数量"));
        upturnDiseasesView.getColumns().put("patientType", new Column("a.PATIENT_TYPE", "patientType", "门诊住院类型(1门诊/2住院)",true));
        tables.put("upturnDiseasesView", upturnDiseasesView);
        //下转病人病种表
        TableObjectReq downDiseasesView = new TableObjectReq("DOWN_DISEASES_VIEW");
        downDiseasesView.setOrgIdColumn("org_id");
        downDiseasesView.setDeptIdColumn("dept_id");
        downDiseasesView.getColumns().put("diseasesName", new Column("DISEASES_NAME", "diseasesName", "病种名称",true));
        downDiseasesView.getColumns().put("patientNum", new Column("PATIENT_NUM", "patientNum", "上转病人数量"));
        downDiseasesView.getColumns().put("patientType", new Column("PATIENT_TYPE", "patientType", "门诊住院类型(1门诊/2住院)",true));
        tables.put("downDiseasesView", downDiseasesView);


        TableObjectReq diseasesCatchPayView = new TableObjectReq("DISEASES_CATCH_PAY_VIEW");
        diseasesCatchPayView.setOrgIdColumn("org_id");
        diseasesCatchPayView.setNotToDept(false);
        diseasesCatchPayView.setNotConstTable(false);
        diseasesCatchPayView.getColumns().put("diseasesName", new Column("DISEASES_NAME", "diseasesName", "病种名称",true));
        diseasesCatchPayView.getColumns().put("payPrice", new Column("(case when a.PATIENT_TYPE = '1' then DISEASES_PRICE else 0 end)", "payPrice", "付费价格",true));
        diseasesCatchPayView.getColumns().put("catchPrice", new Column("(case when a.PATIENT_TYPE = '2' then DISEASES_PRICE else 0 end)", "catchPrice", "收费价格",true));
        diseasesCatchPayView.getColumns().put("catchPayType", new Column("PATIENT_TYPE", "catchPayType", "费用类型(1付费/2收费)",true));
        tables.put("diseasesCatchPayView", diseasesCatchPayView);

        //--------------------------------------------------人力资源---------------------------------------------------------------------------------

        // 床位表
        TableObjectReq deptWardBedTable = new TableObjectReq("DEPT_WARD_BED");
        deptWardBedTable.setOrgIdColumn("org_id");
        deptWardBedTable.setNotToDept(false);
//        deptWardBedTable.setNotConstTable(false);
        deptWardBedTable.setDateColumn("STATISTICS_DATE");//入职时间
        deptWardBedTable.setNotPointOfTime(false);
        deptWardBedTable.setCustomPointOfTimeFlag(true);
        deptWardBedTable.getColumns().put("bedCountOpen", new Column("BED_COUNT_OPEN", "bedCountOpen", "开放床位数"));
        deptWardBedTable.getColumns().put("bedCountChecks", new Column("BED_COUNT_CHECK", "bedCountChecks", "编制床位数"));
        deptWardBedTable.getColumns().put("privateBedCount", new Column("decode(og.IFGLJG,'0',a.BED_COUNT_OPEN,0)", "privateBedCount", "私人开放床位数",true));

        tables.put("deptWardBedTable", deptWardBedTable);
        // 床位表
        TableObjectReq deptWard = new TableObjectReq("DEPT_WARD_BED");
        deptWard.setOrgIdColumn("org_id");
        deptWard.setNotToDept(false);
//        deptWard.setNotConstTable(false);
        deptWardBedTable.setDateColumn("STATISTICS_DATE");//入职时间
        deptWardBedTable.setNotPointOfTime(false);
        deptWardBedTable.setCustomPointOfTimeFlag(true);
        deptWard.getColumns().put("cws", new Column("BED_COUNT_OPEN", "cws", "开放床位数"));
        tables.put("deptBedTable", deptWard);

        //人员薪酬填报表（新）
        TableObjectReq staffSalaryRep = new TableObjectReq("HR_STAFF_SALARY_REP");
        staffSalaryRep.setOrgIdColumn("org_id");
        staffSalaryRep.setNotToDept(true);
        staffSalaryRep.setDateColumn("STATISTICS_DATE");//年份
        staffSalaryRep.getColumns().put("managerCpath", new Column("manager_cpath","managerCpath","薪酬"));
        staffSalaryRep.getColumns().put("peopleNumber", new Column("people_number","peopleNumber","人数"));
        staffSalaryRep.getColumns().put("meritPay", new Column("merit_pay","meritPay","绩效工资"));
        staffSalaryRep.getColumns().put("regPersonsOnDuty", new Column("reg_persons_on_duty","regPersonsOnDuty","在岗在册人员数"));
        staffSalaryRep.getColumns().put("regPersonsOnSalary", new Column("reg_persons_on_salary","regPersonsOnSalary","在岗在册人员薪酬"));
        staffSalaryRep.getColumns().put("regPersonsOnPersonnel", new Column("reg_persons_on_personnel","regPersonsOnPersonnel","在岗在册人员绩效工资"));
        staffSalaryRep.getColumns().put("contractNumber", new Column("contract_number","contractNumber","临聘/合同人员数"));
        staffSalaryRep.getColumns().put("contractRemuneration", new Column("contract_remuneration","contractRemuneration","临聘/合同人员薪酬"));
        staffSalaryRep.getColumns().put("contractPerformancePay", new Column("contract_performance_pay","contractPerformancePay","临聘/合同人员绩效工资"));
        staffSalaryRep.getColumns().put("deanOfSalary", new Column("dean_of_salary","deanOfSalary","院长年薪"));
        staffSalaryRep.getColumns().put("leadersSalary", new Column("leaders_salary","leadersSalary","院级领导(不包括院长)年薪"));
        tables.put("hrStaffSalaryRep", staffSalaryRep);
        //--------------------------------------------------医疗监测---------------------------------------------------------------------------------
        //医院收支情况表（财政补助收入，科教项目收入）  医疗监测
        TableObjectReq hrpBudgetSituationTable = new TableObjectReq("HRP_BUDGET_SITUATION");
        hrpBudgetSituationTable.setNotToDept(true);
        hrpBudgetSituationTable.setOrgIdColumn("org_id");
        hrpBudgetSituationTable.setDateColumn("STATISTICS_DATE");
        hrpBudgetSituationTable.getColumns().put("finSubIncome", new Column("FIN_SUB_INCOME", "finSubIncome", "财政补助收入"));
        hrpBudgetSituationTable.getColumns().put("scienceIncome", new Column("SCIENCE_INCOME", "scienceIncome", "科教项目收入"));
        tables.put("hrpBudgetSituationTable", hrpBudgetSituationTable);
        // 门诊账单医院统计日报表（门诊表） 医疗监测
        TableObjectReq outStatisticsView = new TableObjectReq("OUT_STATISTICS_VIEW");
        outStatisticsView.setOrgIdColumn("org_id");
        outStatisticsView.setDeptIdColumn("dept_id");
        outStatisticsView.getColumns().put("outIncome", new Column("(nvl(REGISTRATION_INCOME,0)+nvl(EXAMINATION_INCOME,0)+nvl(CHECKED_INCOME,0)+nvl(LAB_INCOME,0)" +
                "+nvl(TREATMENT_INCOME,0)+nvl(SURGERY_INCOME,0)+nvl(MATERIAL_INCOME,0)+nvl(DRUG_INCOME,0)+nvl(PHARMACY_SERVICE_INCOME,0)+nvl(OTHER_INCOME,0))", "outIncome", "门诊收入",true));
//        outStatisticsView.getColumns().put("outMedicalBusinessIncome", new Column("MEDICAL_BUSINESS_INCOME", "outMedicalBusinessIncome", "医疗业务收入"));
        outStatisticsView.getColumns().put("outMedicalBusinessIncome", new Column("(case when og.dept_name <> '体检科' then a.MEDICAL_BUSINESS_INCOME else 0 end)", "outMedicalBusinessIncome", "医疗业务收入",true));
        outStatisticsView.getColumns().put("outTotalIncome", new Column("TOTAL_INCOME", "outTotalIncome", "总收入(暂时可以使用,后期抽离到财务指标中)"));
        outStatisticsView.getColumns().put("outRegistrationIncome", new Column("REGISTRATION_INCOME", "outRegistrationIncome", "挂号费"));
        outStatisticsView.getColumns().put("outTreatmentIncome", new Column("TREATMENT_INCOME", "outTreatmentIncome", "治疗费"));
        outStatisticsView.getColumns().put("outBedIncome", new Column("BED_INCOME", "outBedIncome", "床位费"));
        outStatisticsView.getColumns().put("outNurseIncome", new Column("NURSE_INCOME", "outNurseIncome", "护理费"));
        outStatisticsView.getColumns().put("outExaminationIncome", new Column("EXAMINATION_INCOME", "outExaminationIncome", "诊察费"));
        outStatisticsView.getColumns().put("outLabIncome", new Column("LAB_INCOME", "outLabIncome", "化验费"));
        outStatisticsView.getColumns().put("outSurgeryIncome", new Column("SURGERY_INCOME", "outSurgeryIncome", "手术费"));
        outStatisticsView.getColumns().put("outPharmacyServiceIncome", new Column("PHARMACY_SERVICE_INCOME", "outPharmacyServiceIncome", "药事服务费"));
//        outStatisticsView.getColumns().put("outHealthIncome", new Column("HEALTH_INCOME", "outHealthIncome", "健康检查收入"));
        outStatisticsView.getColumns().put("outHealthIncome", new Column("decode(og.dept_name,'体检科',a.MEDICAL_BUSINESS_INCOME,0)", "outHealthIncome", "健康检查收入",true));
        outStatisticsView.getColumns().put("outOutpatientNum", new Column("OUTPATIENT_NUM", "outOutpatientNum", "门诊人次"));
        outStatisticsView.getColumns().put("outCheckedIncome", new Column("CHECKED_INCOME", "outCheckedIncome", "检查费"));
        outStatisticsView.getColumns().put("outMaterialIncome", new Column("MATERIAL_INCOME", "outMaterialIncome", "材料费"));
        outStatisticsView.getColumns().put("outWsMaterialIncome", new Column("MATERIAL_INCOME", "outWsMaterialIncome", "卫生材料费"));
        outStatisticsView.getColumns().put("outOtherIncome", new Column("OTHER_INCOME", "outOtherIncome", "其他收入"));
        outStatisticsView.getColumns().put("outDrugIncome", new Column("DRUG_INCOME", "outDrugIncome", "药品收入"));
        outStatisticsView.getColumns().put("outBaseDrugIncome", new Column("BASE_DRUG_INCOME", "outBaseDrugIncome", "基本药物收入"));
        // todo 药品收入(不含中药饮片) 中“中药饮片”费用类别无法区分，先使用药品收入替换 DRUG_NOT_CHIN_INCOME 先使用DRUG_INCOME
        outStatisticsView.getColumns().put("outDrugNotChinIncome", new Column("DRUG_INCOME", "outDrugNotChinIncome", "药品收入(不含中药饮片)"));
        outStatisticsView.getColumns().put("outEmergencyNum", new Column("EMERGENCY_NUM", "outEmergencyNum", "急诊人次"));
        outStatisticsView.getColumns().put("outServerIncome", new Column("SERVER_INCOME", "outServerIncome", "医疗服务收入"));
        outStatisticsView.getColumns().put("outPatientNum", new Column("PATIENT_NUM", "outPatientNum", "所有病人数"));
        outStatisticsView.getColumns().put("outMedicalNatureIncome", new Column("MEDICAL_NATURE_INCOME", "outMedicalNatureIncome", "医务性收入"));
        outStatisticsView.getColumns().put("outYbtcFee", new Column("YBTC_FEE", "outYbtcFee", "医保基金收入"));
        outStatisticsView.getColumns().put("outSpecialServiceIncome", new Column("SPECIAL_SERVICE_INCOME", "outSpecialServiceIncome", "特需服务收入"));
        outStatisticsView.getColumns().put("outSpecialServiceNum", new Column("SPECIAL_SERVICE_NUM", "outSpecialServiceNum", "特需服务量"));
        outStatisticsView.getColumns().put("outWestDrugIncome", new Column("WEST_DRUG_INCOME", "outWestDrugIncome", "西药费"));
        outStatisticsView.getColumns().put("outAllPatientNum", new Column("(nvl(a.EMERGENCY_NUM,0)+nvl(a.OUTPATIENT_NUM,0))", "outAllPatientNum", "门(急)诊人次",true));
        outStatisticsView.getColumns().put("hygienicMaterialIncome", new Column("HYGIENIC_MATERIAL_INCOME", "hygienicMaterialIncome", "卫生材料费"));
        outStatisticsView.getColumns().put("inHighCostMaterialIncome", new Column("HIGH_COST_MATERIAL_INCOME", "inHighCostMaterialIncome", "高值耗材费"));
        outStatisticsView.getColumns().put("outpatientIncome", new Column("OUTPATIENT_INCOME", "outpatientIncome", "门诊费用"));
//        outStatisticsView.getColumns().put("outChiPatentDrugIncome", new Column("CHI_PATENT_DRUG_INCOME", "outChiPatentDrugIncome", "中成药费"));
//        outStatisticsView.getColumns().put("outChiHerbalDrugIncome", new Column("CHI_HERBAL_DRUG_INCOME", "outChiHerbalDrugIncome", "中草药费"));

        outStatisticsView.getColumns().put("twoOutIncome", new Column("decode(og.ORG_GRADE,'2',nvl(a.MEDICAL_BUSINESS_INCOME,0),0)", "twoOutIncome", "二级机构门诊收入",true));
        outStatisticsView.getColumns().put("threeOutIncome", new Column("decode(og.ORG_GRADE,'3',nvl(a.MEDICAL_BUSINESS_INCOME,0),0)", "threeOutIncome", "三级机构门诊收入",true));

        outStatisticsView.getColumns().put("oneOutPatient", new Column("decode(og.ORG_GRADE,'1',nvl(a.OUTPATIENT_NUM+a.EMERGENCY_NUM,0),0)", "oneOutPatient", "二级机构门诊人次",true));
        outStatisticsView.getColumns().put("twoOutPatient", new Column("decode(og.ORG_GRADE,'2',nvl(a.OUTPATIENT_NUM+a.EMERGENCY_NUM,0),0)", "twoOutPatient", "二级机构门诊人次",true));
        outStatisticsView.getColumns().put("threeOutPatient", new Column("decode(og.ORG_GRADE,'3',nvl(a.OUTPATIENT_NUM+a.EMERGENCY_NUM,0),0)", "threeOutPatient", "三级机构门诊人次",true));


        tables.put("outStatisticsView", outStatisticsView);

        //查询门诊的其他收入
        TableObjectReq outStatisticsView2 = new TableObjectReq("OUT_STATISTICS_VIEW");
        outStatisticsView2.setOrgIdColumn("org_id");
        outStatisticsView2.setDeptIdColumn("dept_id");
        outStatisticsView2.setConditionCustom(" og.dept_name <> '体检科' ");
        outStatisticsView2.getColumns().put("outMedicalBusinessIncome1", new Column("MEDICAL_BUSINESS_INCOME", "outMedicalBusinessIncome1", "医疗业务收入"));
        outStatisticsView2.getColumns().put("outTotalIncome1", new Column("TOTAL_INCOME", "outTotalIncome", "总收入(暂时可以使用,后期抽离到财务指标中)"));
        outStatisticsView2.getColumns().put("outRegistrationIncome1", new Column("REGISTRATION_INCOME", "outRegistrationIncome1", "挂号费"));
        outStatisticsView2.getColumns().put("outExaminationIncome1", new Column("EXAMINATION_INCOME", "outExaminationIncome1", "诊察费"));
        outStatisticsView2.getColumns().put("outLabIncome1", new Column("LAB_INCOME", "outLabIncome1", "化验费"));
        outStatisticsView2.getColumns().put("outSurgeryIncome1", new Column("SURGERY_INCOME", "outSurgeryIncome1", "手术费"));
        outStatisticsView2.getColumns().put("outCheckedIncome1", new Column("CHECKED_INCOME", "outCheckedIncome1", "检查费"));
        outStatisticsView2.getColumns().put("outMaterialIncome1", new Column("MATERIAL_INCOME", "outMaterialIncome1", "材料费"));
        outStatisticsView2.getColumns().put("outDrugIncome1", new Column("DRUG_INCOME", "outDrugIncome1", "药品收入"));
        outStatisticsView.getColumns().put("outTreatmentIncome1", new Column("TREATMENT_INCOME", "outTreatmentIncome1", "治疗费"));
        // todo 药品收入(不含中药饮片) 中“中药饮片”费用类别无法区分，先使用药品收入替换 DRUG_NOT_CHIN_INCOME 先使用DRUG_INCOME
        outStatisticsView2.getColumns().put("outMedicalNatureIncome1", new Column("MEDICAL_NATURE_INCOME", "outMedicalNatureIncome1", "医务性收入"));
        tables.put("outStatisticsView2", outStatisticsView2);

        //PS 中药饮片收入= 药品收入- 药品收入(不含中药饮片收入)
        // 住院账单医院统计日报表（住院表） 医疗监测
        TableObjectReq inpStatisticsView = new TableObjectReq("INP_STATISTICS_VIEW");
        inpStatisticsView.setOrgIdColumn("org_id");
        inpStatisticsView.setDeptIdColumn("dept_id");
        inpStatisticsView.getColumns().put("inpIncome", new Column("(nvl(BED_INCOME,0)+nvl(EXAMINATION_INCOME,0)+nvl(CHECKED_INCOME,0)+nvl(TREATMENT_INCOME,0)+nvl(SURGERY_INCOME,0)" +
                "+nvl(NURSE_INCOME,0)+nvl(MATERIAL_INCOME,0)+nvl(DRUG_INCOME,0)+nvl(PHARMACY_SERVICE_INCOME,0)+nvl(OTHER_INCOME,0))", "inpIncome", "住院收入",true));
        inpStatisticsView.getColumns().put("inpMedicalBusinessIncome", new Column("MEDICAL_BUSINESS_INCOME", "inpMedicalBusinessIncome", "医疗业务收入"));
        inpStatisticsView.getColumns().put("inpTotalIncome", new Column("TOTAL_INCOME", "inpTotalIncome", "总收入(暂时可以使用,后期抽离到财务指标中)"));
        inpStatisticsView.getColumns().put("inpWestDrugIncome", new Column("WEST_DRUG_INCOME", "inpWestDrugIncome", "西药费"));
        inpStatisticsView.getColumns().put("inpChiPatentDrugIncome", new Column("CHI_PATENT_DRUG_INCOME", "inpChiPatentDrugIncome", "中成药费"));
        inpStatisticsView.getColumns().put("inpChiHerbalDrugIncome", new Column("CHI_HERBAL_DRUG_INCOME", "inpChiHerbalDrugIncome", "中草药费"));
        inpStatisticsView.getColumns().put("inpRegistrationIncome", new Column("REGISTRATION_INCOME", "inpRegistrationIncome", "挂号费"));
        inpStatisticsView.getColumns().put("inpTreatmentIncome", new Column("TREATMENT_INCOME", "inpTreatmentIncome", "治疗费"));
        inpStatisticsView.getColumns().put("inpBedIncome", new Column("BED_INCOME", "inpBedIncome", "床位费"));
        inpStatisticsView.getColumns().put("inpNurseIncome", new Column("NURSE_INCOME", "inpNurseIncome", "护理费"));
        inpStatisticsView.getColumns().put("inpExaminationIncome", new Column("EXAMINATION_INCOME", "inpExaminationIncome", "诊察费"));
        inpStatisticsView.getColumns().put("inpRadiationIncome", new Column("RADIATION_INCOME", "inpRadiationIncome", "放射费"));
        inpStatisticsView.getColumns().put("inpLabIncome", new Column("LAB_INCOME", "inpLabIncome", "化验费"));
        inpStatisticsView.getColumns().put("inpBloodTransfusionIncome", new Column("BLOOD_TRANSFUSION_INCOME", "inpBloodTransfusionIncome", "输血费"));
        inpStatisticsView.getColumns().put("inpOxygenDeliveryIncome", new Column("OXYGEN_DELIVERY_INCOME", "inpOxygenDeliveryIncome", "输氧费"));
        inpStatisticsView.getColumns().put("inpAnesthesiaIncome", new Column("ANESTHESIA_INCOME", "inpAnesthesiaIncome", "麻醉费"));
        inpStatisticsView.getColumns().put("inpSurgeryIncome", new Column("SURGERY_INCOME", "inpSurgeryIncome", "手术费"));
        inpStatisticsView.getColumns().put("inpHospitalizationNum", new Column("ADMISSION_NUM", "inpHospitalizationNum", "住院人次"));
        //实际开放总床日数 暂时使用这个表
        inpStatisticsView.getColumns().put("actualOpenBedNum", new Column("INP_HOSPITAL_DAYS", "actualOpenBedNum", "实际开放总床日数"));
        inpStatisticsView.getColumns().put("inpDischargedNum", new Column("DISCHARGED_NUM", "inpDischargedNum", "出院人次"));
        // todo 药品收入(不含中药饮片) 中“中药饮片”费用类别无法区分，先使用药品收入替换 DRUG_NOT_CHIN_INCOME 先使用DRUG_INCOME
        inpStatisticsView.getColumns().put("inpDrugNotChinIncome", new Column("DRUG_INCOME", "inpDrugNotChinIncome", "药品收入（不含中药饮片）"));
        inpStatisticsView.getColumns().put("inpDrugIncome", new Column("DRUG_INCOME", "inpDrugIncome", "药品收入"));
        inpStatisticsView.getColumns().put("inpPharmacyServiceIncome", new Column("PHARMACY_SERVICE_INCOME", "inpPharmacyServiceIncome", "药事服务费"));
        inpStatisticsView.getColumns().put("inpCheckedIncome", new Column("CHECKED_INCOME", "inpCheckedIncome", "检查费"));
        inpStatisticsView.getColumns().put("inpMaterialIncome", new Column("MATERIAL_INCOME", "inpMaterialIncome", "材料费"));
        inpStatisticsView.getColumns().put("inpWsMaterialIncome", new Column("MATERIAL_INCOME", "inpWsMaterialIncome", "卫生材料费"));
        inpStatisticsView.getColumns().put("inpBaseDrugIncome", new Column("BASE_DRUG_INCOME", "inpBaseDrugIncome", "基本药物收入"));
        inpStatisticsView.getColumns().put("inpInpHospitalDays", new Column("INP_HOSPITAL_DAYS", "inpInpHospitalDays", "出院人数总住院天数"));
        inpStatisticsView.getColumns().put("inpInpOutSurgeryNum", new Column("INP_OUT_SURGERY_NUM", "inpInpOutSurgeryNum", "出院患者手术人次"));
        inpStatisticsView.getColumns().put("inpOtherIncome", new Column("OTHER_INCOME", "inpOtherIncome", "其他收入"));
        inpStatisticsView.getColumns().put("inpServerIncome", new Column("SERVER_INCOME", "inpServerIncome", "医疗服务收入"));
        inpStatisticsView.getColumns().put("inpAdmissionNum", new Column("ADMISSION_NUM", "inpAdmissionNum", "入院人数"));
        inpStatisticsView.getColumns().put("inpMedicalNatureIncome", new Column("MEDICAL_NATURE_INCOME", "inpMedicalNatureIncome", "医务性收入"));
        inpStatisticsView.getColumns().put("inpSpecialServiceIncome", new Column("SPECIAL_SERVICE_INCOME", "inpSpecialServiceIncome", "特需服务收入"));
        inpStatisticsView.getColumns().put("inpSpecialServiceNum", new Column("SPECIAL_SERVICE_NUM", "inpSpecialServiceNum", "特需服务量"));
        inpStatisticsView.getColumns().put("inpYbtcFee", new Column("YBTC_FEE", "inpYbtcFee", "医保基金收入"));
        inpStatisticsView.getColumns().put("hygienicMaterialIncome", new Column("HYGIENIC_MATERIAL_INCOME", "hygienicMaterialIncome", "卫生材料费"));
        inpStatisticsView.getColumns().put("ouHighCostMaterialIncome", new Column("HIGH_COST_MATERIAL_INCOME", "ouHighCostMaterialIncome", "高值耗材费"));

        inpStatisticsView.getColumns().put("twoInpIncome", new Column("decode(og.ORG_GRADE,'2',nvl(a.MEDICAL_BUSINESS_INCOME,0),0)", "twoInpIncome", "二级机构住院收入",true));
        inpStatisticsView.getColumns().put("threeInpIncome", new Column("decode(og.ORG_GRADE,'3',nvl(a.MEDICAL_BUSINESS_INCOME,0),0)", "threeInpIncome", "三级机构住院收入",true));

        inpStatisticsView.getColumns().put("oneInpPatient", new Column("decode(og.ORG_GRADE,'1',nvl(a.DISCHARGED_NUM,0),0)", "oneInpPatient", "二级机构出院人次",true));
        inpStatisticsView.getColumns().put("twoInpPatient", new Column("decode(og.ORG_GRADE,'2',nvl(a.DISCHARGED_NUM,0),0)", "twoInpPatient", "二级机构出院人次",true));
        inpStatisticsView.getColumns().put("threeInpPatient", new Column("decode(og.ORG_GRADE,'3',nvl(a.DISCHARGED_NUM,0),0)", "threeInpPatient", "三级机构出院人次",true));

        tables.put("inpStatisticsView", inpStatisticsView);

        //住院机构级别统计表
        TableObjectReq inpStatisticsOrgView = new TableObjectReq("INP_STATISTICS_ORG_VIEW");
        inpStatisticsOrgView.setOrgIdColumn("org_id");
        inpStatisticsOrgView.setDeptIdColumn("dept_id");
        inpStatisticsOrgView.getColumns().put("thFoSurgeryNum", new Column("TH_FO_SURGERY_NUM", "thFoSurgeryNum", "Ⅲ、Ⅳ类手术例数"));
        inpStatisticsOrgView.getColumns().put("baseDrugUsePeoNum", new Column("BASE_DRUG_USE_PEO_NUM", "baseDrugUsePeoNum", "出院患者使用基本药物总人次数"));
        inpStatisticsOrgView.getColumns().put("surPatCompNum", new Column("SUR_PAT_COMP_NUM", "surPatCompNum", "手术患者并发症发生例数"));
        tables.put("inpStatisticsOrgView", inpStatisticsOrgView);

        // 门诊挂号统计日报表（挂号表） 医疗监测
        //该表不能使用，必须使用OUT_RESERVATION_REGISTERED（预约挂号表）
        TableObjectReq outRegisteredView = new TableObjectReq("OUT_REGISTERED_VIEW");
        outRegisteredView.setOrgIdColumn("org_id");
        outRegisteredView.setDeptIdColumn("dept_id");
        outRegisteredView.setDateColumn("STATISTICS_DATE");
//        outRegisteredView.getColumns().put("registrationOtherPeoNum", new Column("REGISTRATION_OTHER_PEO_NUM", "registrationOtherPeoNum", "其他人次"));
//        tables.put("outRegisteredView", outRegisteredView);
        //手术表
        TableObjectReq inpOperationSummaryView = new TableObjectReq("INP_OPERATION_SUMMARY_VIEW");
        inpOperationSummaryView.setOrgIdColumn("org_id");
        inpOperationSummaryView.setNotToDept(true);
        inpOperationSummaryView.getColumns().put("operationNums", new Column("SS_NUM", "operationNums", "手术例数"));
        inpOperationSummaryView.getColumns().put("zqOperationNums", new Column("ZQSS_NUM", "zqOperationNums", "择期手术数"));
        inpOperationSummaryView.getColumns().put("rjOperationNums", new Column("RJSS_NUM", "rjOperationNums", "日间手术数量"));
        inpOperationSummaryView.getColumns().put("shqkOtherNums", new Column("SS_QK_LEVEL_OTHER_NUM", "shqkOtherNums", "切口等级其它手术数"));
        inpOperationSummaryView.getColumns().put("ssqkL1OperationNums", new Column("SS_QK_LEVEL1_NUM", "ssqkL1OperationNums", "切口等级1手术数"));
        inpOperationSummaryView.getColumns().put("ssqkL2OperationNums", new Column("SS_QK_LEVEL2_NUM", "ssqkL2OperationNums", "切口等级2手术数"));
        inpOperationSummaryView.getColumns().put("ssqkL3OperationNums", new Column("SS_QK_LEVEL3_NUM", "ssqkL3OperationNums", "切口等级3手术数"));
        inpOperationSummaryView.getColumns().put("ssqkL4OperationNums", new Column("SS_QK_LEVEL4_NUM", "ssqkL4OperationNums", "切口等级4手术数"));
        inpOperationSummaryView.getColumns().put("ssqkL1AntOperationNums", new Column("SS_QK_LEVEL1_ANT_NUM", "ssqkL1AntOperationNums", "I类手术切口手术患者使用抗菌药数"));
        inpOperationSummaryView.getColumns().put("ssL1Nums", new Column("SS_LEVEL1_NUM", "ssL1Nums", "手术等级1其它手术数"));
        inpOperationSummaryView.getColumns().put("ssL2Nums", new Column("SS_LEVEL2_NUM", "ssL2Nums", "手术等级2其它手术数"));
        inpOperationSummaryView.getColumns().put("ssL3Nums", new Column("SS_LEVEL3_NUM", "ssL3Nums", "手术等级3其它手术数"));
        inpOperationSummaryView.getColumns().put("ssL4Nums", new Column("SS_LEVEL4_NUM", "ssL4Nums", "手术等级4其它手术数"));
        inpOperationSummaryView.getColumns().put("ssqkL1GrNums", new Column("SS_QK_LEVEL1_GR_NUM", "ssqkL1GrNums", "一类手术感染量"));
        inpOperationSummaryView.getColumns().put("bfzNums", new Column("BFZ_NUM", "bfzNums", "手术病人并发症数量"));
        inpOperationSummaryView.getColumns().put("sszlCost", new Column("SSZL_FEE", "sszlCost", "手术治疗费"));
        inpOperationSummaryView.getColumns().put("operationPatientCost", new Column("TOTAL_FEE", "operationPatientCost", "有手术病人住院总费用"));
        tables.put("inpOperationSummaryView", inpOperationSummaryView);

        //住院手术汇总表(来源手术表)
        TableObjectReq inpOperationView = new TableObjectReq("INP_OPERATION_VIEW");
        inpOperationView.setOrgIdColumn("org_id");
        inpOperationView.setNotToDept(true);
        inpOperationView.getColumns().put("operationNums", new Column("SS_NUM", "operationNums", "手术例数"));
        inpOperationView.getColumns().put("zqOperationNums", new Column("ZQSS_NUM", "zqOperationNums", "择期手术数"));
        inpOperationView.getColumns().put("rjOperationNums", new Column("RJSS_NUM", "rjOperationNums", "日间手术数量"));
        inpOperationView.getColumns().put("shqkOtherNums", new Column("SS_QK_LEVEL_OTHER_NUM", "shqkOtherNums", "切口等级其它手术数"));
        inpOperationView.getColumns().put("ssqkL1OperationNums", new Column("SS_QK_LEVEL1_NUM", "ssqkL1OperationNums", "切口等级1手术数"));
        inpOperationView.getColumns().put("ssqkL2OperationNums", new Column("SS_QK_LEVEL2_NUM", "ssqkL2OperationNums", "切口等级2手术数"));
        inpOperationView.getColumns().put("ssqkL3OperationNums", new Column("SS_QK_LEVEL3_NUM", "ssqkL3OperationNums", "切口等级3手术数"));
        inpOperationView.getColumns().put("ssqkL4OperationNums", new Column("SS_QK_LEVEL4_NUM", "ssqkL4OperationNums", "切口等级4手术数"));
        inpOperationView.getColumns().put("ssqkL1AntOperationNums", new Column("SS_QK_LEVEL1_ANT_NUM", "ssqkL1AntOperationNums", "I类手术切口手术患者使用抗菌药数"));
        inpOperationView.getColumns().put("ssL1Nums", new Column("SS_LEVEL1_NUM", "ssL1Nums", "手术等级1其它手术数"));
        inpOperationView.getColumns().put("ssL2Nums", new Column("SS_LEVEL2_NUM", "ssL2Nums", "手术等级2其它手术数"));
        inpOperationView.getColumns().put("ssL3Nums", new Column("SS_LEVEL3_NUM", "ssL3Nums", "手术等级3其它手术数"));
        inpOperationView.getColumns().put("ssL4Nums", new Column("SS_LEVEL4_NUM", "ssL4Nums", "手术等级4其它手术数"));
        inpOperationView.getColumns().put("ssqkL1GrNums", new Column("SS_QK_LEVEL1_GR_NUM", "ssqkL1GrNums", "一类手术感染量"));
        inpOperationView.getColumns().put("bfzNums", new Column("BFZ_NUM", "bfzNums", "手术病人并发症数量"));
        inpOperationView.getColumns().put("sszlCost", new Column("SSZL_FEE", "sszlCost", "手术治疗费"));
        inpOperationView.getColumns().put("operationPatientCost", new Column("TOTAL_FEE", "operationPatientCost", "有手术病人住院总费用"));
        tables.put("inpOperationView", inpOperationView);

        //门诊医保表
        TableObjectReq outMedicalView = new TableObjectReq("OUT_MEDICAL_VIEW");
        outMedicalView.setOrgIdColumn("org_id");
        outMedicalView.getColumns().put("outMedicalFee", new Column("MZBRZJE", "outMedicalFee", "门诊医保病人总费用"));
        outMedicalView.getColumns().put("outSelfPay", new Column("MZBRZFJE", "outSelfPay", "医保病人门诊自付费用（费用）"));
        outMedicalView.getColumns().put("outMedicalSelfPay", new Column("MZBRYBWZFJE", "outMedicalSelfPay", "医保病人门诊医保范围外自付费用(目录外医疗费用)"));
        outMedicalView.getColumns().put("outMedicalPay", new Column("MZYBWBXJE", "outMedicalPay", "医保病人门诊医保报销费用"));
        tables.put("outMedicalView", outMedicalView);

        //住院医保表
        TableObjectReq inpMedicalView = new TableObjectReq("INP_MEDICAL_VIEW");
        inpMedicalView.setOrgIdColumn("org_id");
        inpMedicalView.getColumns().put("inpMedicalFee", new Column("ZYYBBRZJE", "inpMedicalFee", "住院医保病人总费用"));
        inpMedicalView.getColumns().put("inpSelfPay", new Column("ZYYBBRZFJE", "inpSelfPay", "医保病人住院自付费用（费用））"));
        inpMedicalView.getColumns().put("inpMedicalSelfPay", new Column("ZYYBBRWZFJE", "inpMedicalSelfPay", "医保病人住院医保范围外自付费用(目录外医疗费用)"));
        inpMedicalView.getColumns().put("inpMedicalPay", new Column("ZYYBWBXJE", "inpMedicalPay", "医保病人住院医保报销费用"));
        inpMedicalView.getColumns().put("inpPatientPay", new Column("ZYZFY", "inpPatientPay", "医保病人所有病人住院总费用"));
        inpMedicalView.getColumns().put("inpPatientSelfPay", new Column("ZYZFFY", "inpPatientSelfPay", "医保病人所有病人自付费用"));
        tables.put("inpMedicalView", inpMedicalView);

        //低风险组病种
        TableObjectReq lowDiagnosis = new TableObjectReq("MED_DIAGNOSIS_DISEASE");
        lowDiagnosis.setOrgIdColumn("org_id");
        lowDiagnosis.setNotToDept(true);
        lowDiagnosis.setConditionCustom(" regexp_like(a.disease_icd,(select LISTAGG(b.icd_class,'|') within group(order by b.like_flag) as icd_classs from ICD_LOW_RISK_CLASS b)) ");
        lowDiagnosis.getColumns().put("lowDischargedPatients", new Column("DISCHARGED_PATIENTS", "lowDischargedPatients", "低风险病种出院病人数"));
        lowDiagnosis.getColumns().put("lowDeathPatients", new Column("DEATH_PATIENTS", "lowDeathPatients", "低风险病种死亡人数"));
        tables.put("lowDiagnosis", lowDiagnosis);

        //典型单病种统计表
        TableObjectReq medDiagnosisDisease = new TableObjectReq("MED_DIAGNOSIS_DISEASE");
        medDiagnosisDisease.setOrgIdColumn("org_id");
        medDiagnosisDisease.setNotToDept(true);
        medDiagnosisDisease.setConditionCustom("REGEXP_LIKE(a.DISEASE_ICD,'I21|I11.0|I13.0|I13.2|I50|J13|J14|J15|J18|I63|J44.0|J44.1|J44.9') and instr(a.DISEASE_ICD,'I63.301')=0 and instr(a.DISEASE_ICD,'I63.302')=0 and instr(a.DISEASE_ICD,'I63.401')=0 and instr(a.DISEASE_ICD,'I63.801')=0 and instr(a.DISEASE_ICD,'I63.802')=0");
        medDiagnosisDisease.getColumns().put("diseaseCases", new Column("DISEASE_CASES", "diseaseCases", "病种例数"));
        medDiagnosisDisease.getColumns().put("totalBedDays", new Column("TOTAL_BED_DAYS", "totalBedDays", "病种出院患者占用总床日数"));
        medDiagnosisDisease.getColumns().put("dischargedPatients", new Column("DISCHARGED_PATIENTS", "dischargedPatients", "病种出院病人数"));
        medDiagnosisDisease.getColumns().put("dischargedTotalMoney", new Column("DISCHARGED_TOTAL_MONEY", "dischargedTotalMoney", "病种出院费用"));
        medDiagnosisDisease.getColumns().put("dischargedSelfMoney", new Column("DISCHARGED_SELF_MONEY", "dischargedSelfMoney", "病种出院自费费用"));
        medDiagnosisDisease.getColumns().put("deathPatients", new Column("DEATH_PATIENTS", "deathPatients", "病种死亡人数"));
        medDiagnosisDisease.getColumns().put("diseaseName", new Column("DISEASE_NAME", "diseaseName", "病种名称"));
        medDiagnosisDisease.getColumns().put("diseaseIcd", new Column("DISEASE_ICD", "diseaseIcd", "病种ICD"));
        tables.put("medDiagnosisDisease", medDiagnosisDisease);

        //典型单病种汇总统计表
        TableObjectReq medSummaryDisease = new TableObjectReq("MED_SUMMARY_DISEASE");
        medSummaryDisease.setOrgIdColumn("org_id");
        medSummaryDisease.setNotToDept(true);
        medSummaryDisease.getColumns().put("diseaseCases", new Column("DISEASE_CASES", "diseaseCases", "病种例数"));
        medSummaryDisease.getColumns().put("totalBedDays", new Column("TOTAL_BED_DAYS", "totalBedDays", "病种出院患者占用总床日数"));
        medSummaryDisease.getColumns().put("dischargedPatients", new Column("DISCHARGED_PATIENTS", "dischargedPatients", "病种出院病人数"));
        medSummaryDisease.getColumns().put("dischargedTotalMoney", new Column("DISCHARGED_TOTAL_MONEY", "dischargedTotalMoney", "病种出院费用"));
        medSummaryDisease.getColumns().put("dischargedSelfMoney", new Column("DISCHARGED_SELF_MONEY", "dischargedSelfMoney", "病种出院自费费用"));
        medSummaryDisease.getColumns().put("deathPatients", new Column("DEATH_PATIENTS", "deathPatients", "病种死亡人数"));
        medSummaryDisease.getColumns().put("diseaseName", new Column("DISEASE_NAME", "diseaseName", "病种名称"));
        medSummaryDisease.getColumns().put("diseaseIcd", new Column("DISEASE_ICD", "diseaseIcd", "病种ICD"));
        tables.put("medSummaryDisease", medSummaryDisease);

        //绩效考核典型单病种统计表
        TableObjectReq hospitalDisease = new TableObjectReq("MED_DIAGNOSIS_DISEASE");
        hospitalDisease.setOrgIdColumn("org_id");
        hospitalDisease.setNotToDept(true);
        hospitalDisease.setConditionCustom("REGEXP_LIKE(a.DISEASE_ICD,'I21|I11.0|I13.0|I13.2|I50|J13|J14|J15|J18|I63|J44.0|J44.1|J44.9') and instr(a.DISEASE_ICD,'I63.301')=0 and instr(a.DISEASE_ICD,'I63.302')=0 and instr(a.DISEASE_ICD,'I63.401')=0 and instr(a.DISEASE_ICD,'I63.801')=0 and instr(a.DISEASE_ICD,'I63.802')=0");
        //急性心肌梗死
        hospitalDisease.getColumns().put("dischargedPatients1", new Column("(case when instr(a.DISEASE_ICD,'I21') > 0 then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients1", "急性心肌梗死病种例数",true));
        hospitalDisease.getColumns().put("totalBedDays1", new Column("(case when instr(a.DISEASE_ICD,'I21') > 0 then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays1", "急性心肌梗死病种出院患者占用总床日数",true));
        hospitalDisease.getColumns().put("dischargedTotalMoney1", new Column("(case when instr(a.DISEASE_ICD,'I21') > 0 then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney1", "急性心肌梗死病种出院费用",true));
        hospitalDisease.getColumns().put("deathPatients1", new Column("(case when instr(a.DISEASE_ICD,'I21') > 0 then a.DEATH_PATIENTS else 0 end)", "deathPatients1", "急性心肌梗死病种死亡人数",true));
        //心力衰竭
        hospitalDisease.getColumns().put("dischargedPatients2", new Column("(case when REGEXP_LIKE(a.DISEASE_ICD,'I11.0|I13.0|I13.2|I50') then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients2", "心力衰竭病种例数",true));
        hospitalDisease.getColumns().put("totalBedDays2", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'I11.0|I13.0|I13.2|I50') then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays2", "心力衰竭出院患者占用总床日数",true));
        hospitalDisease.getColumns().put("dischargedTotalMoney2", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'I11.0|I13.0|I13.2|I50') then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney2", "心力衰竭病种出院费用",true));
        hospitalDisease.getColumns().put("deathPatients2", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'I11.0|I13.0|I13.2|I50') then a.DEATH_PATIENTS else 0 end)", "deathPatients2", "心力衰竭病种死亡人数",true));
        //肺炎（住院、成人）病种例数
        hospitalDisease.getColumns().put("dischargedPatients3", new Column("(case when REGEXP_LIKE(a.DISEASE_ICD,'J13|J14|J15|J18') and a.CROWD_TYPE = '2' then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients3", "肺炎病种例数",true));
        hospitalDisease.getColumns().put("totalBedDays3", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J13|J14|J15|J18') and a.CROWD_TYPE = '2' then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays3", "肺炎出院患者占用总床日数",true));
        hospitalDisease.getColumns().put("dischargedTotalMoney3", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J13|J14|J15|J18') and a.CROWD_TYPE = '2' then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney3", "肺炎病种出院费用",true));
        hospitalDisease.getColumns().put("deathPatients3", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J13|J14|J15|J18') and a.CROWD_TYPE = '2' then a.DEATH_PATIENTS else 0 end)", "deathPatients3", "肺炎病种死亡人数",true));
        //肺炎（住院、儿童）病种例数
        hospitalDisease.getColumns().put("dischargedPatients10", new Column("(case when REGEXP_LIKE(a.DISEASE_ICD,'J13|J14|J15|J18') and a.CROWD_TYPE = '1' then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients10", "肺炎病种例数",true));
        hospitalDisease.getColumns().put("totalBedDays10", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J13|J14|J15|J18') and a.CROWD_TYPE = '1'  then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays10", "肺炎出院患者占用总床日数",true));
        hospitalDisease.getColumns().put("dischargedTotalMoney10", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J13|J14|J15|J18') and a.CROWD_TYPE = '1'  then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney10", "肺炎病种出院费用",true));
        hospitalDisease.getColumns().put("deathPatients10", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J13|J14|J15|J18') and a.CROWD_TYPE = '1'  then a.DEATH_PATIENTS else 0 end)", "deathPatients10", "肺炎病种死亡人数",true));
        //脑梗死
        hospitalDisease.getColumns().put("dischargedPatients4", new Column("(case when  instr(a.DISEASE_ICD,'I63') > 0 then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients4", "脑梗死病种例数",true));
        hospitalDisease.getColumns().put("totalBedDays4", new Column("(case when  instr(a.DISEASE_ICD,'I63') > 0 then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays4", "脑梗死出院患者占用总床日数",true));
        hospitalDisease.getColumns().put("dischargedTotalMoney4", new Column("(case when  instr(a.DISEASE_ICD,'I63') > 0 then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney4", "脑梗死病种出院费用",true));
        hospitalDisease.getColumns().put("deathPatients4", new Column("(case when   instr(a.DISEASE_ICD,'I63') > 0 then a.DEATH_PATIENTS else 0 end)", "deathPatients4", "脑梗死病种死亡人数",true));
        //慢性阻塞性肺疾病
        hospitalDisease.getColumns().put("dischargedPatients5", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J44.0|J44.1|J44.9') then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients5", "慢性阻塞性肺疾病病种例数",true));
        hospitalDisease.getColumns().put("totalBedDays5", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J44.0|J44.1|J44.9') then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays5", "慢性阻塞性肺疾病出院患者占用总床日数",true));
        hospitalDisease.getColumns().put("dischargedTotalMoney5", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J44.0|J44.1|J44.9') then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney5", "慢性阻塞性肺疾病病种出院费用",true));
        hospitalDisease.getColumns().put("deathPatients5", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'J44.0|J44.1|J44.9') then a.DEATH_PATIENTS else 0 end)", "deathPatients5", "慢性阻塞性肺疾病病种死亡人数",true));
        tables.put("hospitalDisease", hospitalDisease);

        //手术详情统计表
        TableObjectReq medOperationDisease = new TableObjectReq("MED_OPERATION_DISEASE");
        medOperationDisease.setOrgIdColumn("org_id");
        medOperationDisease.setNotToDept(true);
        medOperationDisease.setConditionCustom("REGEXP_LIKE(a.DISEASE_ICD,'81.5100|81.5200|81.54|36.1|74.0|74.1|74.2')");
        medOperationDisease.getColumns().put("diseaseCases", new Column("DISEASE_CASES", "diseaseCases", "病种例数"));
        medOperationDisease.getColumns().put("totalBedDays", new Column("TOTAL_BED_DAYS", "totalBedDays", "病种出院患者占用总床日数"));
        medOperationDisease.getColumns().put("dischargedPatients", new Column("DISCHARGED_PATIENTS", "dischargedPatients", "病种出院病人数"));
        medOperationDisease.getColumns().put("dischargedTotalMoney", new Column("DISCHARGED_TOTAL_MONEY", "dischargedTotalMoney", "病种出院费用"));
        medOperationDisease.getColumns().put("dischargedSelfMoney", new Column("DISCHARGED_SELF_MONEY", "dischargedSelfMoney", "病种出院自费费用"));
        medOperationDisease.getColumns().put("deathPatients", new Column("DEATH_PATIENTS", "deathPatients", "病种死亡人数"));
        medOperationDisease.getColumns().put("diseaseName", new Column("DISEASE_NAME", "diseaseName", "手术名称"));
        medOperationDisease.getColumns().put("diseaseIcd", new Column("DISEASE_ICD", "diseaseIcd", "手术ICD"));
        tables.put("medOperationDisease", medOperationDisease);

        //绩效考核手术详情统计表
        TableObjectReq hospitalOperationDis = new TableObjectReq("MED_OPERATION_DISEASE");
        hospitalOperationDis.setOrgIdColumn("org_id");
        hospitalOperationDis.setNotToDept(true);
        hospitalOperationDis.setConditionCustom(" REGEXP_LIKE(a.DISEASE_ICD,'81.5100|81.5200|81.54|36.1|74.0|74.1|74.2')");
        //髋关节置换术
        hospitalOperationDis.getColumns().put("dischargedPatients6", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'81.5100|81.5200') then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients6", "髋关节置换术病种例数",true));
        hospitalOperationDis.getColumns().put("totalBedDays6", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'81.5100|81.5200') then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays6", "髋关节置换术出院患者占用总床日数",true));
        hospitalOperationDis.getColumns().put("dischargedTotalMoney6", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'81.5100|81.5200') then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney6", "髋关节置换术病种出院费用",true));
        hospitalOperationDis.getColumns().put("deathPatients6", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'81.5100|81.5200') then a.DEATH_PATIENTS else 0 end)", "deathPatients6", "髋关节置换术病种死亡人数",true));
        //膝关节置换术
        hospitalOperationDis.getColumns().put("dischargedPatients7", new Column("(case when instr(a.DISEASE_ICD,'81.54')>0 then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients7", "膝关节置换术病种例数",true));
        hospitalOperationDis.getColumns().put("totalBedDays7", new Column("(case when instr(a.DISEASE_ICD,'81.54')>0 then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays7", "膝关节置换术出院患者占用总床日数",true));
        hospitalOperationDis.getColumns().put("dischargedTotalMoney7", new Column("(case when instr(a.DISEASE_ICD,'81.54')>0 then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney7", "膝关节置换术病种出院费用",true));
        hospitalOperationDis.getColumns().put("deathPatients7", new Column("(case when instr(a.DISEASE_ICD,'81.54')>0 then a.DEATH_PATIENTS else 0 end)", "deathPatients7", "膝关节置换术病种死亡人数",true));
        //冠状动脉旁路移植术
        hospitalOperationDis.getColumns().put("dischargedPatients8", new Column("(case when instr(a.DISEASE_ICD,'36.1')>0 then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients8", "冠状动脉旁路移植术病种例数",true));
        hospitalOperationDis.getColumns().put("totalBedDays8", new Column("(case when instr(a.DISEASE_ICD,'36.1')>0 then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays8", "冠状动脉旁路移植术出院患者占用总床日数",true));
        hospitalOperationDis.getColumns().put("dischargedTotalMoney8", new Column("(case when instr(a.DISEASE_ICD,'36.1')>0 then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney8", "冠状动脉旁路移植术病种出院费用",true));
        hospitalOperationDis.getColumns().put("deathPatients8", new Column("(case when instr(a.DISEASE_ICD,'36.1')>0 then a.DEATH_PATIENTS else 0 end)", "deathPatients8", "冠状动脉旁路移植术病种死亡人数",true));
        //剖宫产
        hospitalOperationDis.getColumns().put("dischargedPatients9", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'74.0|74.1|74.2') then a.DISCHARGED_PATIENTS else 0 end)", "dischargedPatients9", "剖宫产病种例数",true));
        hospitalOperationDis.getColumns().put("totalBedDays9", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'74.0|74.1|74.2') then a.TOTAL_BED_DAYS else 0 end)", "totalBedDays9", "剖宫产出院患者占用总床日数",true));
        hospitalOperationDis.getColumns().put("dischargedTotalMoney9", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'74.0|74.1|74.2') then a.DISCHARGED_TOTAL_MONEY else 0 end)", "dischargedTotalMoney9", "剖宫产病种出院费用",true));
        hospitalOperationDis.getColumns().put("deathPatients9", new Column("(case when  REGEXP_LIKE(a.DISEASE_ICD,'74.0|74.1|74.2') then a.DEATH_PATIENTS else 0 end)", "deathPatients9", "剖宫产病种死亡人数",true));
        tables.put("hospitalOperationDis", hospitalOperationDis);

        //绩效考核相关指标表  绩效考核才用到的表  其他模块不能用到的表
        TableObjectReq kpiTertiaryPublicHospital = new TableObjectReq("KPI_TERTIARY_PUBLIC_HOSPITAL");
        kpiTertiaryPublicHospital.setOrgIdColumn("ORG_ID");
        kpiTertiaryPublicHospital.setNotToDept(true);
        kpiTertiaryPublicHospital.getColumns().put("visitCount", new Column("VISIT_COUNT", "visitCount", "门诊患者人次（不包括一挂号后根据医嘱进行的各项检查、治疗处置工作量以及免疫接种、健康管理服务人次数）"));
        kpiTertiaryPublicHospital.getColumns().put("dischargedCount", new Column("DISCHARGED_COUNT", "dischargedCount", "出院患者人次数"));
        kpiTertiaryPublicHospital.getColumns().put("daytimeOperations", new Column("DAYTIME_OPERATIONS", "daytimeOperations", "日间手术台次数"));
        kpiTertiaryPublicHospital.getColumns().put("electiveOperations", new Column("ELECTIVE_OPERATIONS", "electiveOperations", "出院患者择期手术总台次数(人数)"));
        kpiTertiaryPublicHospital.getColumns().put("kpiOperations", new Column("OPERATIONS", "kpiOperations", "出院患者手术总台次数"));
        kpiTertiaryPublicHospital.getColumns().put("gradeFourOperations", new Column("GRADE_FOUR_OPERATIONS", "gradeFourOperations", "出院患者四级手术台次数"));
        kpiTertiaryPublicHospital.getColumns().put("operationsExclude1", new Column("OPERATIONS_EXCLUDE1", "operationsExclude1", "出院的手术患者人数（不含妊娠、分娩、围产期、新生儿患者）"));
        kpiTertiaryPublicHospital.getColumns().put("iIncisionOperationInfected", new Column("I_INCISION_OPERATION_INFECTED", "iIncisionOperationInfected", "I类切口手术部位感染人次数"));
        kpiTertiaryPublicHospital.getColumns().put("iIncisionOperations", new Column("I_INCISION_OPERATIONS", "iIncisionOperations", "I类切口手术台次数"));
        kpiTertiaryPublicHospital.getColumns().put("inAntibioticsConsumption", new Column("IN_ANTIBIOTICS_CONSUMPTION", "inAntibioticsConsumption", "本年度住院患者抗菌药物消耗量（累计DDD值）"));
        kpiTertiaryPublicHospital.getColumns().put("averageHospitalStay", new Column("AVERAGE_HOSPITAL_STAY", "averageHospitalStay", "出院患者住院总天数"));
        kpiTertiaryPublicHospital.getColumns().put("outEssentialDrugs", new Column("OUT_ESSENTIAL_DRUGS", "outEssentialDrugs", "门诊使用基本药物人次数"));
        kpiTertiaryPublicHospital.getColumns().put("visitCountExclude1", new Column("VISIT_COUNT_EXCLUDE1", "visitCountExclude1", "门诊诊疗总人次数（不包括健康体检者及未开具药物处方患者）"));
        kpiTertiaryPublicHospital.getColumns().put("inEssentialDrugs", new Column("IN_ESSENTIAL_DRUGS", "inEssentialDrugs", "出院患者使用基本药物总人次数"));
        kpiTertiaryPublicHospital.getColumns().put("dischargedCountExclude1", new Column("DISCHARGED_COUNT_EXCLUDE1", "dischargedCountExclude1", "出院总人次数（不包括未使用药物的出院患者）"));
        kpiTertiaryPublicHospital.getColumns().put("appointmentAvgWaitingTime", new Column("APPOINTMENT_AVG_WAITING_TIME", "appointmentAvgWaitingTime", "门诊患者预约后平均等待时间（分钟）"));
        kpiTertiaryPublicHospital.getColumns().put("medIncome", new Column("MED_INCOME", "medIncome", "医疗收入"));
        kpiTertiaryPublicHospital.getColumns().put("kpiOutIncome", new Column("OUT_INCOME", "kpiOutIncome", "门诊收入"));
        kpiTertiaryPublicHospital.getColumns().put("outFundIncome", new Column("OUT_FUND_INCOME", "outFundIncome", "门诊收入中来自医保基金收入"));
        kpiTertiaryPublicHospital.getColumns().put("kpiInIncome", new Column("IN_INCOME", "kpiInIncome", "住院收入"));
        kpiTertiaryPublicHospital.getColumns().put("inFundIncome", new Column("IN_FUND_INCOME", "inFundIncome", "住院收入中来自医保基金收入"));
        kpiTertiaryPublicHospital.getColumns().put("medSerivceIncome", new Column("MED_SERIVCE_INCOME", "medSerivceIncome", "医疗服务收入"));
        kpiTertiaryPublicHospital.getColumns().put("kpiDrugIncome", new Column("DRUG_INCOME", "kpiDrugIncome", "药品总收入"));
        kpiTertiaryPublicHospital.getColumns().put("kpiOutDrugIncome", new Column("OUT_DRUG_INCOME", "kpiOutDrugIncome", "门诊药品收入"));
        kpiTertiaryPublicHospital.getColumns().put("inDrugIncome", new Column("IN_DRUG_INCOME", "inDrugIncome", "住院药品收入"));
        kpiTertiaryPublicHospital.getColumns().put("emergencyIncome", new Column("EMERGENCY_INCOME", "emergencyIncome", "急诊患者人次"));
        kpiTertiaryPublicHospital.getColumns().put("testIncome", new Column("TEST_INCOME", "testIncome", "体检患者人次"));
        kpiTertiaryPublicHospital.getColumns().put("kpiOutVisitCount",new Column("(a.TEST_INCOME+a.EMERGENCY_INCOME+a.VISIT_COUNT)", "kpiOutVisitCount", "门诊总人次(包含门诊+急诊+体检)",true));
        tables.put("kpiTertiaryPublicHospital", kpiTertiaryPublicHospital);

        TableObjectReq kpiPublicHospital = new TableObjectReq("KPI_TERTIARY_PUBLIC_HOSPITAL");
        kpiPublicHospital.setOrgIdColumn("ORG_ID");
        kpiPublicHospital.setNotToDept(true);
        kpiPublicHospital.getColumns().put("outOutpatientNum", new Column("VISIT_COUNT", "outOutpatientNum", "门诊患者人次（不包括一挂号后根据医嘱进行的各项检查、治疗处置工作量以及免疫接种、健康管理服务人次数）"));
        kpiPublicHospital.getColumns().put("visitCount", new Column("VISIT_COUNT", "visitCount", "门诊患者人次（不包括一挂号后根据医嘱进行的各项检查、治疗处置工作量以及免疫接种、健康管理服务人次数）"));
        kpiPublicHospital.getColumns().put("inpDischargedNum", new Column("DISCHARGED_COUNT", "inpDischargedNum", "出院患者人次数"));
        kpiPublicHospital.getColumns().put("dischargedCount", new Column("DISCHARGED_COUNT", "dischargedCount", "出院患者人次数"));
        kpiPublicHospital.getColumns().put("daytimeOperations", new Column("DAYTIME_OPERATIONS", "daytimeOperations", "日间手术台次数"));
        kpiPublicHospital.getColumns().put("electiveOperations", new Column("ELECTIVE_OPERATIONS", "electiveOperations", "出院患者择期手术总台次数(人数)"));
        kpiPublicHospital.getColumns().put("kpiOperations", new Column("OPERATIONS", "kpiOperations", "出院患者手术总台次数"));
        kpiPublicHospital.getColumns().put("gradeFourOperations", new Column("GRADE_FOUR_OPERATIONS", "gradeFourOperations", "出院患者四级手术台次数"));
        kpiPublicHospital.getColumns().put("operationsExclude1", new Column("OPERATIONS_EXCLUDE1", "operationsExclude1", "出院的手术患者人数（不含妊娠、分娩、围产期、新生儿患者）"));
        kpiPublicHospital.getColumns().put("iIncisionOperationInfected", new Column("I_INCISION_OPERATION_INFECTED", "iIncisionOperationInfected", "I类切口手术部位感染人次数"));
        kpiPublicHospital.getColumns().put("iIncisionOperations", new Column("I_INCISION_OPERATIONS", "iIncisionOperations", "I类切口手术台次数"));
        kpiPublicHospital.getColumns().put("inAntibioticsConsumption", new Column("IN_ANTIBIOTICS_CONSUMPTION", "inAntibioticsConsumption", "本年度住院患者抗菌药物消耗量（累计DDD值）"));
        kpiPublicHospital.getColumns().put("inpInpHospitalDays", new Column("AVERAGE_HOSPITAL_STAY", "inpInpHospitalDays", "出院患者住院总天数"));
        kpiPublicHospital.getColumns().put("averageHospitalStay", new Column("AVERAGE_HOSPITAL_STAY", "averageHospitalStay", "出院患者住院总天数"));
        kpiPublicHospital.getColumns().put("outEssentialDrugs", new Column("OUT_ESSENTIAL_DRUGS", "outEssentialDrugs", "门诊使用基本药物人次数"));
        kpiPublicHospital.getColumns().put("outAllPatientNum", new Column("VISIT_COUNT_EXCLUDE1", "outAllPatientNum", "门诊诊疗总人次数（不包括健康体检者及未开具药物处方患者）"));
        kpiPublicHospital.getColumns().put("visitCountExclude1", new Column("VISIT_COUNT_EXCLUDE1", "visitCountExclude1", "门诊诊疗总人次数（不包括健康体检者及未开具药物处方患者）"));
        kpiPublicHospital.getColumns().put("inEssentialDrugs", new Column("IN_ESSENTIAL_DRUGS", "inEssentialDrugs", "出院患者使用基本药物总人次数"));
        kpiPublicHospital.getColumns().put("dischargedCountExclude1", new Column("DISCHARGED_COUNT_EXCLUDE1", "dischargedCountExclude1", "出院总人次数（不包括未使用药物的出院患者）"));
        kpiPublicHospital.getColumns().put("appointmentAvgWaitingTime", new Column("APPOINTMENT_AVG_WAITING_TIME", "appointmentAvgWaitingTime", "门诊患者预约后平均等待时间（分钟）"));
        kpiPublicHospital.getColumns().put("medIncome", new Column("MED_INCOME", "medIncome", "医疗收入"));
        kpiPublicHospital.getColumns().put("outMedicalBusinessIncome", new Column("OUT_INCOME", "outMedicalBusinessIncome", "门诊收入"));
        kpiPublicHospital.getColumns().put("kpiOutIncome", new Column("OUT_INCOME", "kpiOutIncome", "门诊收入"));
        kpiPublicHospital.getColumns().put("outFundIncome", new Column("OUT_FUND_INCOME", "outFundIncome", "门诊收入中来自医保基金收入"));
        kpiPublicHospital.getColumns().put("inpMedicalBusinessIncome", new Column("IN_INCOME", "inpMedicalBusinessIncome", "住院收入"));
        kpiPublicHospital.getColumns().put("kpiInIncome", new Column("IN_INCOME", "kpiInIncome", "住院收入"));
        kpiPublicHospital.getColumns().put("inFundIncome", new Column("IN_FUND_INCOME", "inFundIncome", "住院收入中来自医保基金收入"));
        kpiPublicHospital.getColumns().put("medSerivceIncome", new Column("MED_SERIVCE_INCOME", "medSerivceIncome", "医疗服务收入"));
        kpiPublicHospital.getColumns().put("kpiDrugIncome", new Column("DRUG_INCOME", "kpiDrugIncome", "药品总收入"));
        kpiPublicHospital.getColumns().put("kpiOutDrugIncome", new Column("OUT_DRUG_INCOME", "kpiOutDrugIncome", "门诊药品收入"));
        kpiPublicHospital.getColumns().put("inDrugIncome", new Column("IN_DRUG_INCOME", "inDrugIncome", "住院药品收入"));
        kpiPublicHospital.getColumns().put("emergencyIncome", new Column("EMERGENCY_INCOME", "emergencyIncome", "急诊患者人次"));
        kpiPublicHospital.getColumns().put("testIncome", new Column("TEST_INCOME", "testIncome", "体检患者人次"));
        kpiPublicHospital.getColumns().put("kpiOutVisitCount",new Column("(a.TEST_INCOME+a.EMERGENCY_INCOME+a.VISIT_COUNT)", "kpiOutVisitCount", "门诊总人次(包含门诊+急诊+体检)",true));
        tables.put("kpiPublicHospital", kpiPublicHospital);

        TableObjectReq kpiSummaryView = new TableObjectReq("KPI_SUMMARY_VIEW");
        kpiSummaryView.setOrgIdColumn("ORG_ID");
        kpiSummaryView.setNotToDept(true);
        kpiSummaryView.getColumns().put("drugNotChinIncome", new Column("DRUG_NOT_CHIN_INCOME", "drugNotChinIncome", "药品收入（不含中药饮片）"));
        kpiSummaryView.getColumns().put("medicalNatureIncome", new Column("MEDICAL_NATURE_INCOME", "medicalNatureIncome", "医务性收入"));
        kpiSummaryView.getColumns().put("serverIncome", new Column("SERVER_INCOME", "serverIncome", "医疗服收入"));
        kpiSummaryView.getColumns().put("materialIncome", new Column("MATERIAL_INCOME", "materialIncome", "卫生材料收入"));
        kpiSummaryView.getColumns().put("baseDrugIncome", new Column("BASE_DRUG_INCOME", "baseDrugIncome", "基本药物收入"));
        kpiSummaryView.getColumns().put("inpDrugIncome", new Column("INP_DRUG_INCOME", "inpDrugIncome", "住院药品费用"));
        kpiSummaryView.getColumns().put("hospitalizationNum", new Column("HOSPITALIZATION_NUM", "hospitalizationNum", "住院天数(舍弃)"));
        kpiSummaryView.getColumns().put("inpAdmissionNum", new Column("ADMISSION_NUM", "inpAdmissionNum", "入院人数"));
        kpiSummaryView.getColumns().put("inpDrugUseNum", new Column("INP_DRUG_USE_NUM", "inpDrugUseNum", "住院药品使用数量"));
        kpiSummaryView.getColumns().put("inpKjyDrugUseNum", new Column("INP_KJY_DRUG_USER_NUM", "inpKjyDrugUseNum", "住院抗菌药品使用人次"));
        kpiSummaryView.getColumns().put("inpAntibiotic", new Column("INP_ANTIBIOTIC", "inpAntibiotic", "住院抗生素使用数量"));
        kpiSummaryView.getColumns().put("treOutpatientNum", new Column("TRE_OUTPATIENT_NUM", "treOutpatientNum", "门诊预约挂号数"));
        kpiSummaryView.getColumns().put("totalRegisteredNum", new Column("TOTAL_REGISTERED_NUM", "totalRegisteredNum", "门诊挂号总数"));
        kpiSummaryView.getColumns().put("reservationOutpatientNum", new Column("TOTAL_REGISTERED_NUM", "reservationOutpatientNum", "门诊挂号总数"));
        kpiSummaryView.getColumns().put("outKjyDrugUseNum", new Column("OUT_KJY_CFS", "outKjyDrugUseNum", "门诊患者抗菌药物处方数"));
        kpiSummaryView.getColumns().put("outJyFs", new Column("OUT_JY_FS", "outJyFs", "门诊患者基本药物处方数"));
        kpiSummaryView.getColumns().put("outDrugUseNum", new Column("OUT_CFS", "outDrugUseNum", "门诊患者处方数"));
        kpiSummaryView.getColumns().put("zyypDrugCfNum", new Column("ZYYP_CFS", "zyypDrugCfNum", "中药饮片处方数"));
        kpiSummaryView.getColumns().put("drugCfNum", new Column("OUT_DRUG_NUM", "drugCfNum", "门诊处方总数"));
        kpiSummaryView.getColumns().put("outDrugUseNum", new Column("OUT_DRUG_NUM", "outDrugUseNum", "门诊处方总数"));
        kpiSummaryView.getColumns().put("ssqkL1OperationNums", new Column("SS_QK_LEVEL1_NUM", "ssqkL1OperationNums", "Ⅰ类手术台数"));
        kpiSummaryView.getColumns().put("ssQkLevel2_num", new Column("SS_QK_LEVEL2_NUM", "ssQkLevel2_num", "Ⅱ类手术台数"));
        kpiSummaryView.getColumns().put("ssL3Nums", new Column("SS_QK_LEVEL3_NUM", "ssL3Nums", "Ⅲ类手术台数"));
        kpiSummaryView.getColumns().put("ssL4Nums", new Column("SS_QK_LEVEL4_NUM", "ssL4Nums", "Ⅳ类手术台数"));
        kpiSummaryView.getColumns().put("ssQkLevel1_grNum", new Column("SS_QK_LEVEL1_GR_NUM", "ssQkLevel1_grNum", "I类切口手术部位感染手术台数"));
        kpiSummaryView.getColumns().put("bfzNums", new Column("BFZ_NUM", "bfzNums", "手术患者并发症台次数"));
        kpiSummaryView.getColumns().put("ssLevel4_num", new Column("SS_LEVEL4_NUM", "ssLevel4_num", "出院患者四级手术台次数"));
        kpiSummaryView.getColumns().put("operationNums", new Column("SS_NUM", "operationNums", "出院患者手术总台次数"));
        kpiSummaryView.getColumns().put("ssqkL1AntOperationNums", new Column("SS_QK_LEVEL1_ANT_NUM", "ssqkL1AntOperationNums", "I类切口手术患者预防使用抗菌药物人次"));
        kpiSummaryView.getColumns().put("dischargedPatients1", new Column("DISCHARGED_PATIENTS1", "dischargedPatients1", "急性心肌梗死病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays1", new Column("TOTAL_BED_DAYS1", "totalBedDays1", "急性心肌梗死住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney1", new Column("DISCHARGED_TOTAL_MONEY1", "dischargedTotalMoney1", "急性心肌梗死费用"));
        kpiSummaryView.getColumns().put("deathPatients1", new Column("DEATH_PATIENTS1", "deathPatients1", "急性心肌梗死病病死例数"));
        kpiSummaryView.getColumns().put("dischargedPatients2", new Column("DISCHARGED_PATIENTS2", "dischargedPatients2", "心力衰竭病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays2", new Column("TOTAL_BED_DAYS2", "totalBedDays2", "心力衰竭病种住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney2", new Column("DISCHARGED_TOTAL_MONEY2", "dischargedTotalMoney2", "心力衰竭病种费用"));
        kpiSummaryView.getColumns().put("deathPatients2", new Column("DEATH_PATIENTS2", "deathPatients2", "心力衰竭病种病死例数"));
        kpiSummaryView.getColumns().put("dischargedPatients3", new Column("DISCHARGED_PATIENTS3", "dischargedPatients3", "肺炎（住院、成人）病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays3", new Column("TOTAL_BED_DAYS3", "totalBedDays3", "肺炎（住院、成人）病种住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney3", new Column("DISCHARGED_TOTAL_MONEY3", "dischargedTotalMoney3", "肺炎（住院、成人）病种费用"));
        kpiSummaryView.getColumns().put("deathPatients3", new Column("DEATH_PATIENTS3", "deathPatients3", "肺炎（住院、成人）病种病死例数"));
        kpiSummaryView.getColumns().put("dischargedPatients4", new Column("DISCHARGED_PATIENTS4", "dischargedPatients4", "肺炎（住院、儿童）病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays4", new Column("TOTAL_BED_DAYS4", "totalBedDays4", "肺炎（住院、儿童）病种住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney4", new Column("DISCHARGED_TOTAL_MONEY4", "dischargedTotalMoney4", "肺炎（住院、儿童）病种费用"));
        kpiSummaryView.getColumns().put("deathPatients4", new Column("DEATH_PATIENTS4", "deathPatients4", "肺炎（住院、儿童）病种病死例数"));
        kpiSummaryView.getColumns().put("dischargedPatients5", new Column("DISCHARGED_PATIENTS5", "dischargedPatients5", "脑梗死病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays5", new Column("TOTAL_BED_DAYS5", "totalBedDays5", "脑梗死病种住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney5", new Column("DISCHARGED_TOTAL_MONEY5", "dischargedTotalMoney5", "脑梗死病种费用"));
        kpiSummaryView.getColumns().put("deathPatients5", new Column("DEATH_PATIENTS5", "deathPatients5", "脑梗死病种病死例数"));
        kpiSummaryView.getColumns().put("dischargedPatients6", new Column("DISCHARGED_PATIENTS6", "dischargedPatients6", "髋关节置换术病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays6", new Column("TOTAL_BED_DAYS6", "totalBedDays6", "髋关节置换术病种住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney6", new Column("DISCHARGED_TOTAL_MONEY6", "dischargedTotalMoney6", "髋关节置换术病种费用"));
        kpiSummaryView.getColumns().put("deathPatients6", new Column("DEATH_PATIENTS6", "deathPatients6", "髋关节置换术病种病死例数"));
        kpiSummaryView.getColumns().put("dischargedPatients7", new Column("DISCHARGED_PATIENTS7", "dischargedPatients7", "膝关节置换术病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays7", new Column("TOTAL_BED_DAYS7", "totalBedDays7", "膝关节置换术病种住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney7", new Column("DISCHARGED_TOTAL_MONEY7", "dischargedTotalMoney7", "膝关节置换术病种费用"));
        kpiSummaryView.getColumns().put("deathPatients7", new Column("DEATH_PATIENTS7", "deathPatients7", "膝关节置换术病种病死例数"));
        kpiSummaryView.getColumns().put("dischargedPatients8", new Column("DISCHARGED_PATIENTS8", "dischargedPatients8", "冠状动脉旁路移植术病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays8", new Column("TOTAL_BED_DAYS8", "totalBedDays8", "冠状动脉旁路移植术病种住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney8", new Column("DISCHARGED_TOTAL_MONEY8", "dischargedTotalMoney8", "冠状动脉旁路移植术病种费用"));
        kpiSummaryView.getColumns().put("deathPatients8", new Column("DEATH_PATIENTS8", "deathPatients8", "冠状动脉旁路移植术病种病死例数"));
        kpiSummaryView.getColumns().put("dischargedPatients9", new Column("DISCHARGED_PATIENTS9", "dischargedPatients9", "剖宫产病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays9", new Column("TOTAL_BED_DAYS9", "totalBedDays9", "剖宫产病种住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney9", new Column("DISCHARGED_TOTAL_MONEY9", "dischargedTotalMoney9", "剖宫产病种费用"));
        kpiSummaryView.getColumns().put("deathPatients9", new Column("DEATH_PATIENTS9", "deathPatients9", "剖宫产病种病死例数"));
        kpiSummaryView.getColumns().put("dischargedPatients10", new Column("DISCHARGED_PATIENTS10", "dischargedPatients10", "慢性阻塞性肺疾病病种例数"));
        kpiSummaryView.getColumns().put("totalBedDays10", new Column("TOTAL_BED_DAYS10", "totalBedDays10", "慢性阻塞性肺疾病病种住院日数"));
        kpiSummaryView.getColumns().put("dischargedTotalMoney10", new Column("DISCHARGED_TOTAL_MONEY10", "dischargedTotalMoney10", "慢性阻塞性肺疾病病种费用"));
        kpiSummaryView.getColumns().put("deathPatients10", new Column("DEATH_PATIENTS10", "deathPatients10", "慢性阻塞性肺疾病病种病死例数"));
        kpiSummaryView.getColumns().put("lowDeathPatients", new Column("LOW_DEATH_PATIENTS", "lowDeathPatients", "低风险组病例死亡人数"));
        kpiSummaryView.getColumns().put("lowDischargedPatients", new Column("LOW_DISCHARGED_PATIENTS", "lowDischargedPatients", "低风险组病例人数"));
        kpiSummaryView.getColumns().put("ssBrNum", new Column("SS_BR_NUM", "ssBrNum", "做过手术病人数"));
        kpiSummaryView.getColumns().put("totalReferralNumber", new Column("DOWN_PATIENT_NUM", "totalReferralNumber", "下转病人数量"));
        kpiSummaryView.getColumns().put("txNum", new Column("TX_NUM", "txNum", "特需医疗服人次"));
        kpiSummaryView.getColumns().put("txSr", new Column("TX_SR", "txSr", "特需医疗服收入"));
        tables.put("kpiSummaryView", kpiSummaryView);

        TableObjectReq kpiHrSummaryView = new TableObjectReq("KPI_HR_SUMMARY_VIEW");
        kpiHrSummaryView.setOrgIdColumn("ORG_ID_WJW");
        kpiHrSummaryView.setNotToDept(true);
        kpiHrSummaryView.getColumns().put("highLevelNum", new Column("HIGH_LEVEL_NUM", "highLevelNum", "高级职称(副高及以上)医务人员数量"));
        kpiHrSummaryView.getColumns().put("medicalPeoNum", new Column("MEDICAL_PEO_NUM", "medicalPeoNum", "医务人员数量"));
        kpiHrSummaryView.getColumns().put("ekDocNum", new Column("EK_DOC_NUM", "ekDocNum", "儿科医师人数"));
        kpiHrSummaryView.getColumns().put("allDocNum", new Column("ALL_DOC_NUM", "allDocNum", "总医师人数"));
        kpiHrSummaryView.getColumns().put("allDoctorNum", new Column("ALL_DOC_NUM", "allDoctorNum", "总医师人数"));
        kpiHrSummaryView.getColumns().put("zyDocNum", new Column("ZY_DOC_NUM", "zyDocNum", "中医医师人数"));
        kpiHrSummaryView.getColumns().put("allNurseNum", new Column("ALL_NURSE_NUM", "allNurseNum", "护士人数"));
        tables.put("kpiHrSummaryView", kpiHrSummaryView);

        //人口常量表
        TableObjectReq peopleConstantView = new TableObjectReq("PEOPLE_CONSTANT_VIEW");
        peopleConstantView.setOrgIdColumn("ORG_ID_GW");
        peopleConstantView.setNotToDept(true);
        peopleConstantView.setNotPointOfTime(false);
        peopleConstantView.setCustomPointOfTimeFlag(true);//设置取时间最大的一条记录
        peopleConstantView.getColumns().put("CZRKS", new Column("CZRKS", "CZRKS", "常驻人口数量"));
        peopleConstantView.getColumns().put("MXBRKS", new Column("MXBRKS", "MXBRKS", "慢性病人口数量"));
        peopleConstantView.getColumns().put("PKRKSL", new Column("PKRKSL", "PKRKSL", "贫困人口数量"));
        peopleConstantView.getColumns().put("YFZS", new Column("YFZS", "YFZS", "孕妇总数"));
        peopleConstantView.getColumns().put("ETZS", new Column("ETZS", "ETZS", "儿童总数"));
        peopleConstantView.getColumns().put("CSRKZS", new Column("CSRKZS", "CSRKZS", "出生人口总数"));
        peopleConstantView.getColumns().put("threeAgeNum", new Column("THREE_AGE_NUM", "threeAgeNum", "0-3岁人口数量"));
        peopleConstantView.getColumns().put("sixAgeNum", new Column("SIX_AGE_NUM", "sixAgeNum", "0-6岁人口数量"));
        peopleConstantView.getColumns().put("sixtyFiveAgeNum", new Column("SIXTY_FIVE_AGE_NUM", "sixtyFiveAgeNum", "65岁人口数量"));
        peopleConstantView.getColumns().put("GXYRKSL", new Column("GXYRKSL", "GXYRKSL", "高血压人口数量"));
        peopleConstantView.getColumns().put("JSBRKSL", new Column("JSBRKSL", "JSBRKSL", "精神病人口数量"));
        peopleConstantView.getColumns().put("TNBRKSL", new Column("TNBRKSL", "TNBRKSL", "糖尿病人口数量"));
        peopleConstantView.getColumns().put("FJHRKSL", new Column("FJHRKSL", "FJHRKSL", "肺结核人口数量"));
        tables.put("peopleConstantView", peopleConstantView);
        //门诊药品使用记录表
        TableObjectReq outpDrugUsedRecordView = new TableObjectReq("OUTP_DRUG_USED_RECORD_VIEW");
        outpDrugUsedRecordView.setOrgIdColumn("org_id");
        outpDrugUsedRecordView.setDeptIdColumn("dept_id");
        outpDrugUsedRecordView.getColumns().put("outpatientDrugMoney", new Column("OUTPATIENT_MONEY", "outpatientDrugMoney", "门诊药品总费用"));
        outpDrugUsedRecordView.getColumns().put("outDrugUseNum", new Column("OUTP_DRUG_USE_NUM", "outDrugUseNum", "门诊药品使用数量"));
        outpDrugUsedRecordView.getColumns().put("outUserTimes", new Column("USER_TIMES", "outUserTimes", "药品使用人次"));
        outpDrugUsedRecordView.getColumns().put("outKjyDrugUseNum", new Column("decode(a.DRUG_TYPE3,'1',a.OUTP_DRUG_USE_NUM,0)", "outKjyDrugUseNum", "门诊抗菌药品使用数量",true));
        outpDrugUsedRecordView.getColumns().put("outAntibiotic", new Column("decode(a.DRUG_TYPE2,'1',a.OUTP_DRUG_USE_NUM,0)", "outAntibiotic", "门诊抗生素使用数量",true));
        outpDrugUsedRecordView.getColumns().put("outVaccine", new Column("decode(a.DRUG_TYPE2,'2',a.OUTP_DRUG_USE_NUM,0)", "outVaccine", "门诊疫苗使用数量",true));

        outpDrugUsedRecordView.getColumns().put("westDrugTimes", new Column("decode(a.DRUG_TYPE,'2',a.USER_TIMES,0)", "westDrugTimes", "西药人次",true));
        outpDrugUsedRecordView.getColumns().put("grassDrugTimes", new Column("decode(a.DRUG_TYPE,'3',a.USER_TIMES,0)", "grassDrugTimes", "中草药人次",true));
        outpDrugUsedRecordView.getColumns().put("middleDrugTimes", new Column("decode(a.DRUG_TYPE,'4',a.USER_TIMES,0)", "middleDrugTimes", "中成药人次",true));
        outpDrugUsedRecordView.getColumns().put("otherDrugTimes", new Column("decode(a.DRUG_TYPE,'10',a.USER_TIMES,0)", "otherDrugTimes", "其他中药人次",true));

        outpDrugUsedRecordView.getColumns().put("westDrugMoney", new Column("decode(a.DRUG_TYPE,'2',a.OUTPATIENT_MONEY,0)", "westDrugMoney", "西药费用",true));
        outpDrugUsedRecordView.getColumns().put("grassDrugMoney", new Column("decode(a.DRUG_TYPE,'3',a.OUTPATIENT_MONEY,0)", "grassDrugMoney", "中草药费用",true));
        outpDrugUsedRecordView.getColumns().put("middleDrugMoney", new Column("decode(a.DRUG_TYPE,'4',a.OUTPATIENT_MONEY,0)", "middleDrugMoney", "中成药费用",true));
        outpDrugUsedRecordView.getColumns().put("otherDrugMoney", new Column("decode(a.DRUG_TYPE,'10',a.OUTPATIENT_MONEY,0)", "otherDrugMoney", "其他中药费用",true));

        tables.put("outpDrugUsedRecordView", outpDrugUsedRecordView);


        //门诊药品使用记录表
        TableObjectReq outDrugUserView = new TableObjectReq("OUT_DRUG_USER_VIEW");
        outDrugUserView.setOrgIdColumn("org_id");
        outDrugUserView.setDeptIdColumn("dept_id");

        outDrugUserView.getColumns().put("westDrugTimes", new Column("XY_USE_TIMES", "westDrugTimes", "西药人次"));
        outDrugUserView.getColumns().put("grassDrugTimes", new Column("ZCY_USE_TIMES", "grassDrugTimes", "中草药人次"));
        outDrugUserView.getColumns().put("middleDrugTimes", new Column("ZCYP_USE_TIMES", "middleDrugTimes", "中成药人次"));
        outDrugUserView.getColumns().put("otherDrugTimes", new Column("QTYP_USE_TIMES", "otherDrugTimes", "其他中药人次"));

        outDrugUserView.getColumns().put("westDrugMoney", new Column("XY_FY", "westDrugMoney", "西药费用"));
        outDrugUserView.getColumns().put("grassDrugMoney", new Column("ZCY_FY", "grassDrugMoney", "中草药费用"));
        outDrugUserView.getColumns().put("middleDrugMoney", new Column("ZCYP_FY", "middleDrugMoney", "中成药费用"));
        outDrugUserView.getColumns().put("otherDrugMoney", new Column("QTYP_FY", "otherDrugMoney", "其他中药费用"));

        outDrugUserView.getColumns().put("outAntibiotic", new Column("KSS_USE_TIMES", "outAntibiotic", "门诊抗生素使用数量"));
        outDrugUserView.getColumns().put("outVaccine", new Column("YM_USE_TIMES", "outVaccine", "门诊疫苗使用数量"));

        tables.put("outDrugUserView", outDrugUserView);


        //门诊药品使用记录表2
        TableObjectReq outpDrugUsedRecordView2 = new TableObjectReq("OUTP_DRUG_USED_RECORD_VIEW");
        outpDrugUsedRecordView2.setOrgIdColumn("org_id");
        outpDrugUsedRecordView2.setDeptIdColumn("dept_id");
        outpDrugUsedRecordView2.getColumns().put("orgName", new Column("a.org_name", "orgName", "机构名称",true));
        outpDrugUsedRecordView2.getColumns().put("deptName", new Column("og.dept_name", "deptName", "科室名称",true));
        outpDrugUsedRecordView2.getColumns().put("doctorId", new Column("a.DOCTOR_ID", "doctorId", "医生id",true));
        outpDrugUsedRecordView2.getColumns().put("doctorName", new Column("a.DOCTOR_NAME", "doctorName", "医生名称",true));
        outpDrugUsedRecordView2.getColumns().put("drugCode", new Column("a.DRUG_CODE", "drugCode", "药品编码",true));
        outpDrugUsedRecordView2.getColumns().put("drugName", new Column("a.DRUG_NAME", "drugName", "药品名称",true));
        outpDrugUsedRecordView2.getColumns().put("outpatientDrugMoney", new Column("OUTPATIENT_MONEY", "outpatientDrugMoney", "门诊药品总费用"));
        outpDrugUsedRecordView2.getColumns().put("outDrugUseNum", new Column("OUTP_DRUG_USE_NUM", "outDrugUseNum", "门诊药品使用数量"));
        outpDrugUsedRecordView2.getColumns().put("outUserTimes", new Column("USER_TIMES", "outUserTimes", "药品使用人次"));
        outpDrugUsedRecordView2.getColumns().put("outKjyDrugUseNum", new Column("decode(a.DRUG_TYPE3,'1',a.OUTP_DRUG_USE_NUM,0)", "outKjyDrugUseNum", "门诊抗菌药品使用数量",true));
        outpDrugUsedRecordView2.getColumns().put("outAntibiotic", new Column("decode(a.DRUG_TYPE2,'1',a.OUTP_DRUG_USE_NUM,0)", "outAntibiotic", "门诊抗生素使用数量",true));
        outpDrugUsedRecordView2.getColumns().put("outVaccine", new Column("decode(a.DRUG_TYPE2,'2',a.OUTP_DRUG_USE_NUM,0)", "outVaccine", "门诊抗生素使用数量",true));
        tables.put("outpDrugUsedRecordView2", outpDrugUsedRecordView2);

        //门诊药品使用记录表3
        TableObjectReq outpDrugUsedRecordView3 = new TableObjectReq("OUTP_DRUG_USED_RECORD_VIEW");
        outpDrugUsedRecordView3.setOrgIdColumn("org_id");
        outpDrugUsedRecordView3.setDeptIdColumn("dept_id");
        outpDrugUsedRecordView3.getColumns().put("outUserTimes", new Column("USER_TIMES", "outUserTimes", "药品使用人次"));
        outpDrugUsedRecordView3.getColumns().put("outDrugUseNum", new Column("OUTP_DRUG_USE_NUM", "outDrugUseNum", "门诊药品使用数量"));
        outpDrugUsedRecordView3.getColumns().put("outKjyDrugUseNum", new Column("decode(a.DRUG_TYPE3,'1',a.OUTP_DRUG_USE_NUM,0)", "outKjyDrugUseNum", "门诊抗菌药品使用数量",true));
        tables.put("outpDrugUsedRecordView3", outpDrugUsedRecordView3);

        //
        TableObjectReq outpDrugUsedRecordView4 = new TableObjectReq("OUTP_DRUG_USED_RECORD_VIEW");
        outpDrugUsedRecordView4.setOrgIdColumn("org_id");
        outpDrugUsedRecordView4.setDeptIdColumn("dept_id");
        outpDrugUsedRecordView4.setConditionCustom(" a.DRUG_TYPE3 = '1'");
        outpDrugUsedRecordView4.getColumns().put("outpatientDrugMoney", new Column("OUTPATIENT_MONEY", "outpatientDrugMoney", "门诊药品总费用"));
        outpDrugUsedRecordView4.getColumns().put("outUserTimes", new Column("USER_TIMES", "outUserTimes", "药品使用人次"));
        outpDrugUsedRecordView4.getColumns().put("outDrugUseNum", new Column("OUTP_DRUG_USE_NUM", "outDrugUseNum", "门诊药品使用数量"));
        tables.put("outpDrugUsedRecordView4", outpDrugUsedRecordView4);


        //门诊药品使用记录表5
        TableObjectReq outpDrugUsedRecordView5 = new TableObjectReq("OUTP_DRUG_USED_ORG_VIEW");
        outpDrugUsedRecordView5.setOrgIdColumn("org_id");
        outpDrugUsedRecordView5.setNotToDept(true);
        outpDrugUsedRecordView5.getColumns().put("orgName", new Column("a.org_name", "orgName", "机构名称",true));
        outpDrugUsedRecordView5.getColumns().put("drugCode", new Column("a.DRUG_CODE", "drugCode", "药品编码",true));
        outpDrugUsedRecordView5.getColumns().put("drugName", new Column("a.DRUG_NAME", "drugName", "药品名称",true));
        outpDrugUsedRecordView5.getColumns().put("outpatientDrugMoney", new Column("OUTPATIENT_MONEY", "outpatientDrugMoney", "门诊药品总费用"));
        outpDrugUsedRecordView5.getColumns().put("outDrugUseNum", new Column("OUTP_DRUG_USE_NUM", "outDrugUseNum", "门诊药品使用数量"));
        outpDrugUsedRecordView5.getColumns().put("outUserTimes", new Column("USER_TIMES", "outUserTimes", "药品使用人次"));
        tables.put("outpDrugUsedRecordView5", outpDrugUsedRecordView5);

        //门诊药品使用记录表6
        TableObjectReq outpDrugUsedRecordView6 = new TableObjectReq("OUTP_DRUG_USED_ORG_VIEW");
        outpDrugUsedRecordView6.setOrgIdColumn("org_id");
        outpDrugUsedRecordView6.setNotToDept(true);
        outpDrugUsedRecordView6.getColumns().put("outpatientDrugMoney", new Column("OUTPATIENT_MONEY", "outpatientDrugMoney", "门诊药品总费用"));
        outpDrugUsedRecordView6.getColumns().put("outDrugUseNum", new Column("OUTP_DRUG_USE_NUM", "outDrugUseNum", "门诊药品使用数量"));
        outpDrugUsedRecordView6.getColumns().put("outUserTimes", new Column("USER_TIMES", "outUserTimes", "药品使用人次"));
        tables.put("outpDrugUsedRecordView6", outpDrugUsedRecordView6);

        //住院药品使用记录表
        TableObjectReq inptDrugUsedRecordView = new TableObjectReq("INPT_DRUG_USED_RECORD_VIEW");
        inptDrugUsedRecordView.setOrgIdColumn("org_id");
        inptDrugUsedRecordView.setDeptIdColumn("dept_id");
        inptDrugUsedRecordView.getColumns().put("inpatientDrugMoney", new Column("INPATENT_MONEY", "inpatientDrugMoney", "住院药品总费用"));
        inptDrugUsedRecordView.getColumns().put("inpDrugUseNum", new Column("INP_DRUG_USE_NUM", "inpDrugUseNum", "住院药品使用数量"));
        inptDrugUsedRecordView.getColumns().put("inpUserTimes", new Column("USER_TIMES", "inpUserTimes", "药品使用人次"));
        inptDrugUsedRecordView.getColumns().put("inpKjyDrugUseNum", new Column("decode(a.DRUG_TYPE3,'1',a.INP_DRUG_USE_NUM,0)", "inpKjyDrugUseNum", "住院抗菌药品使用数量",true));

        inptDrugUsedRecordView.getColumns().put("westDrugTimes", new Column("decode(a.DRUG_TYPE,'2',a.USER_TIMES,0)", "westDrugTimes", "西药人次",true));
        inptDrugUsedRecordView.getColumns().put("grassDrugTimes", new Column("decode(a.DRUG_TYPE,'3',a.USER_TIMES,0)", "grassDrugTimes", "中草药人次",true));
        inptDrugUsedRecordView.getColumns().put("middleDrugTimes", new Column("decode(a.DRUG_TYPE,'4',a.USER_TIMES,0)", "middleDrugTimes", "中成药人次",true));
        inptDrugUsedRecordView.getColumns().put("otherDrugTimes", new Column("decode(a.DRUG_TYPE,'10',a.USER_TIMES,0)", "otherDrugTimes", "其他中药人次",true));

        inptDrugUsedRecordView.getColumns().put("westDrugMoney", new Column("decode(a.DRUG_TYPE,'2',a.INPATENT_MONEY,0)", "westDrugMoney", "西药费用",true));
        inptDrugUsedRecordView.getColumns().put("grassDrugMoney", new Column("decode(a.DRUG_TYPE,'3',a.INPATENT_MONEY,0)", "grassDrugMoney", "中草药费用",true));
        inptDrugUsedRecordView.getColumns().put("middleDrugMoney", new Column("decode(a.DRUG_TYPE,'4',a.INPATENT_MONEY,0)", "middleDrugMoney", "中成药费用",true));
        inptDrugUsedRecordView.getColumns().put("otherDrugMoney", new Column("decode(a.DRUG_TYPE,'10',a.INPATENT_MONEY,0)", "otherDrugMoney", "其他中药费用",true));


        inptDrugUsedRecordView.getColumns().put("inpAntibiotic", new Column("decode(a.DRUG_TYPE2,'1',a.INP_DRUG_USE_NUM,0)", "inpAntibiotic", "住院抗生素使用数量",true));
        inptDrugUsedRecordView.getColumns().put("inpVaccine", new Column("decode(a.DRUG_TYPE2,'2',a.INP_DRUG_USE_NUM,0)", "inpVaccine", "住院疫苗使用数量",true));
        tables.put("inptDrugUsedRecordView", inptDrugUsedRecordView);

        //住院药品使用记录表
        TableObjectReq inpDrugUserView = new TableObjectReq("INP_DRUG_USER_VIEW");
        inpDrugUserView.setOrgIdColumn("org_id");
        inpDrugUserView.setDeptIdColumn("dept_id");
        inpDrugUserView.getColumns().put("westDrugTimes", new Column("XY_USE_TIMES", "westDrugTimes", "西药人次"));
        inpDrugUserView.getColumns().put("grassDrugTimes", new Column("ZCY_USE_TIMES", "grassDrugTimes", "中草药人次"));
        inpDrugUserView.getColumns().put("middleDrugTimes", new Column("ZCYP_USE_TIMES", "middleDrugTimes", "中成药人次"));
        inpDrugUserView.getColumns().put("otherDrugTimes", new Column("QTYP_USE_TIMES", "otherDrugTimes", "其他中药人次"));

        inpDrugUserView.getColumns().put("westDrugMoney", new Column("XY_FY", "westDrugMoney", "西药费用"));
        inpDrugUserView.getColumns().put("grassDrugMoney", new Column("ZCY_FY", "grassDrugMoney", "中草药费用"));
        inpDrugUserView.getColumns().put("middleDrugMoney", new Column("ZCYP_FY", "middleDrugMoney", "中成药费用"));
        inpDrugUserView.getColumns().put("otherDrugMoney", new Column("QTYP_FY", "otherDrugMoney", "其他中药费用"));

        inpDrugUserView.getColumns().put("inpAntibiotic", new Column("KSS_USE_TIMES", "inpAntibiotic", "住院抗生素使用数量"));
        inpDrugUserView.getColumns().put("inpVaccine", new Column("YM_USE_TIMES", "inpVaccine", "住院疫苗使用数量"));

        tables.put("inpDrugUserView", inpDrugUserView);

        TableObjectReq inptDrugUsedRecordView2 = new TableObjectReq("INPT_DRUG_USED_RECORD_VIEW");
        inptDrugUsedRecordView2.setOrgIdColumn("org_id");
        inptDrugUsedRecordView2.setDeptIdColumn("dept_id");
        inptDrugUsedRecordView2.getColumns().put("orgNameInp", new Column("a.ORG_NAME", "orgNameInp", "机构名称",true));
        inptDrugUsedRecordView2.getColumns().put("deptNameInp", new Column("a.DEPT_NAME", "deptNameInp", "科室名称",true));
        inptDrugUsedRecordView2.getColumns().put("doctorIdInp", new Column("a.DOCTOR_ID", "doctorIdInp", "医生id",true));
        inptDrugUsedRecordView2.getColumns().put("doctorNameInp", new Column("a.DOCTOR_NAME", "doctorNameInp", "医生名称",true));
        inptDrugUsedRecordView2.getColumns().put("drugCodeInp", new Column("a.DRUG_CODE", "drugCodeInp", "药品编码",true));
        inptDrugUsedRecordView2.getColumns().put("drugNameInp", new Column("a.DRUG_NAME", "drugNameInp", "药品名称",true));
        inptDrugUsedRecordView2.getColumns().put("inpatientDrugMoney", new Column("INPATENT_MONEY", "inpatientDrugMoney", "住院药品总费用"));
        inptDrugUsedRecordView2.getColumns().put("inpDrugUseNum", new Column("INP_DRUG_USE_NUM", "inpDrugUseNum", "住院药品使用数量"));
        inptDrugUsedRecordView2.getColumns().put("inpUserTimes", new Column("USER_TIMES", "inpUserTimes", "药品使用人次"));
        inptDrugUsedRecordView2.getColumns().put("inpKjyDrugUseNum", new Column("decode(a.DRUG_TYPE3,'1',a.INP_DRUG_USE_NUM,0)", "inpKjyDrugUseNum", "住院抗菌药品使用数量",true));
        tables.put("inptDrugUsedRecordView2", inptDrugUsedRecordView2);

        //住院药品使用记录表
        TableObjectReq inptDrugUsedRecordView3 = new TableObjectReq("INPT_DRUG_USED_RECORD_VIEW");
        inptDrugUsedRecordView3.setOrgIdColumn("org_id");
        inptDrugUsedRecordView3.setDeptIdColumn("dept_id");
        inptDrugUsedRecordView3.getColumns().put("inpUserTimes", new Column("USER_TIMES", "inpUserTimes", "药品使用人次"));
        inptDrugUsedRecordView3.getColumns().put("inpDrugUseNum", new Column("INP_DRUG_USE_NUM", "inpDrugUseNum", "住院药品使用数量"));
        inptDrugUsedRecordView3.getColumns().put("inpKjyDrugUseNum", new Column("decode(a.DRUG_TYPE3,'1',a.INP_DRUG_USE_NUM,0)", "inpKjyDrugUseNum", "住院抗菌药品使用数量",true));
        tables.put("inptDrugUsedRecordView3", inptDrugUsedRecordView3);

        TableObjectReq inptDrugUsedRecordView4 = new TableObjectReq("INPT_DRUG_USED_RECORD_VIEW");
        inptDrugUsedRecordView4.setOrgIdColumn("org_id");
        inptDrugUsedRecordView4.setDeptIdColumn("dept_id");
        inptDrugUsedRecordView4.setConditionCustom(" a.DRUG_TYPE3 = '1'");
        inptDrugUsedRecordView4.getColumns().put("inpatientDrugMoney", new Column("INPATENT_MONEY", "inpatientDrugMoney", "住院药品总费用"));
        inptDrugUsedRecordView4.getColumns().put("inpUserTimes", new Column("USER_TIMES", "inpUserTimes", "药品使用人次"));
        inptDrugUsedRecordView4.getColumns().put("inpDrugUseNum", new Column("INP_DRUG_USE_NUM", "inpDrugUseNum", "住院药品使用数量"));
        tables.put("inptDrugUsedRecordView4", inptDrugUsedRecordView4);

        TableObjectReq inptDrugUsedRecordView5 = new TableObjectReq("INPT_DRUG_USED_ORG_VIEW");
        inptDrugUsedRecordView5.setOrgIdColumn("org_id");
        inptDrugUsedRecordView5.setNotToDept(true);
        inptDrugUsedRecordView5.getColumns().put("orgNameInp", new Column("a.ORG_NAME", "orgNameInp", "机构名称",true));
        inptDrugUsedRecordView5.getColumns().put("drugCodeInp", new Column("a.DRUG_CODE", "drugCodeInp", "药品编码",true));
        inptDrugUsedRecordView5.getColumns().put("drugNameInp", new Column("a.DRUG_NAME", "drugNameInp", "药品名称",true));
        inptDrugUsedRecordView5.getColumns().put("inpatientDrugMoney", new Column("INPATENT_MONEY", "inpatientDrugMoney", "住院药品总费用"));
        inptDrugUsedRecordView5.getColumns().put("inpDrugUseNum", new Column("INP_DRUG_USE_NUM", "inpDrugUseNum", "住院药品使用数量"));
        inptDrugUsedRecordView5.getColumns().put("inpUserTimes", new Column("USER_TIMES", "inpUserTimes", "药品使用人次"));
        tables.put("inptDrugUsedRecordView5", inptDrugUsedRecordView5);


        TableObjectReq inptDrugUsedRecordView6 = new TableObjectReq("INPT_DRUG_USED_ORG_VIEW");
        inptDrugUsedRecordView6.setOrgIdColumn("org_id");
        inptDrugUsedRecordView6.setNotToDept(true);
        inptDrugUsedRecordView6.getColumns().put("inpatientDrugMoney", new Column("INPATENT_MONEY", "inpatientDrugMoney", "住院药品总费用"));
        inptDrugUsedRecordView6.getColumns().put("inpDrugUseNum", new Column("INP_DRUG_USE_NUM", "inpDrugUseNum", "住院药品使用数量"));
        inptDrugUsedRecordView6.getColumns().put("inpUserTimes", new Column("USER_TIMES", "inpUserTimes", "药品使用人次"));
        tables.put("inptDrugUsedRecordView6", inptDrugUsedRecordView6);

        //年度区间指标集合
        TableObjectReq yearIntervalIndexView = new TableObjectReq("YEAR_INTERVAL_INDEX_VIEW");
        yearIntervalIndexView.setOrgIdColumn("org_id");
        yearIntervalIndexView.setNotToDept(true);
        yearIntervalIndexView.getColumns().put("outHospitalNum", new Column("OUT_HOSPITAL_NUM", "outHospitalNum", "出院人头数(只能用于年度指标)"));
        tables.put("yearIndexView", yearIntervalIndexView);

        //季度区间指标集合
        TableObjectReq quarterIntervalIndexView = new TableObjectReq("QUARTER_INTERVAL_INDEX_VIEW");
        quarterIntervalIndexView.setOrgIdColumn("org_id");
        quarterIntervalIndexView.setNotToDept(true);
        quarterIntervalIndexView.getColumns().put("outHospitalNum", new Column("OUT_HOSPITAL_NUM", "outHospitalNum", "出院人头数(只能用于季度指标)"));
        tables.put("quarterIndexView", quarterIntervalIndexView);

        //月度区间指标集合
        TableObjectReq monthIntervalIndexView = new TableObjectReq("MONTH_INTERVAL_INDEX_VIEW");
        monthIntervalIndexView.setOrgIdColumn("org_id");
        monthIntervalIndexView.setNotToDept(true);
        monthIntervalIndexView.getColumns().put("outHospitalNum", new Column("OUT_HOSPITAL_NUM", "outHospitalNum", "出院人头数(只能用于月度指标)"));
        tables.put("monthIndexView", monthIntervalIndexView);

        // 门诊预约挂号统计日报表（预约挂号表） 医疗监测
        TableObjectReq outReservationRegistered = new TableObjectReq("OUT_RESERVATION_REGISTERED");
        outReservationRegistered.setOrgIdColumn("org_id");
        outReservationRegistered.setDeptIdColumn("dept_id");
        outReservationRegistered.getColumns().put("totalRegisteredNum", new Column("TOTAL_REGISTERED_NUM", "totalRegisteredNum", "总挂号数"));
        outReservationRegistered.getColumns().put("registrationOrdinaryNum", new Column("REGISTRATION_ORDINARY_NUM", "registrationOrdinaryNum", "普通号"));
        outReservationRegistered.getColumns().put("registrationDeputyNum", new Column("REGISTRATION_DEPUTY_NUM", "registrationDeputyNum", "副主任号"));
        outReservationRegistered.getColumns().put("registrationIrectorNum", new Column("REGISTRATION_IRECTOR_NUM", "registrationIrectorNum", "主任号"));
        outReservationRegistered.getColumns().put("registrationExpertNum", new Column("REGISTRATION_EXPERT_NUM", "registrationExpertNum", "专家号"));
        outReservationRegistered.getColumns().put("registrationEmergencyNum", new Column("REGISTRATION_EMERGENCY_NUM", "registrationEmergencyNum", "急诊号"));
        outReservationRegistered.getColumns().put("registrationOtherNum", new Column("REGISTRATION_OTHER_NUM", "registrationOtherNum", "其他号"));
        outReservationRegistered.getColumns().put("reservationTreatmentNum", new Column("RESERVATION_TREATMENT_NUM", "reservationTreatmentNum", "预约诊疗人次"));
        outReservationRegistered.getColumns().put("checkNum", new Column("CHECK_NUM", "checkNum", "体检检查人次(诊疗其它人次)"));

        outReservationRegistered.getColumns().put("reservationOutpatientNum", new Column("RESERVATION_OUTPATIENT_NUM", "reservationOutpatientNum", "门诊挂号总数"));
        outReservationRegistered.getColumns().put("treOutpatientNum", new Column("TRE_OUTPATIENT_NUM", "treOutpatientNum", "门诊预约挂号数"));


        outReservationRegistered.getColumns().put("baseRegisteredNum", new Column("(case when og.ORG_GRADE = '1' then nvl(a.TOTAL_REGISTERED_NUM,0) else 0 end)", "baseRegisteredNum", "基层机构医师挂号量",true));


        outReservationRegistered.getColumns().put("oneCheckNum", new Column("decode(og.ORG_GRADE,'1',nvl(a.CHECK_NUM,0),0)", "oneCheckNum", "一级机构体检检查人次(诊疗其它人次)",true));
        outReservationRegistered.getColumns().put("twoCheckNum", new Column("decode(og.ORG_GRADE,'2',nvl(a.CHECK_NUM,0),0)", "twoCheckNum", "二级机构体检检查人次(诊疗其它人次)",true));
        outReservationRegistered.getColumns().put("threeCheckNum", new Column("decode(og.ORG_GRADE,'3',nvl(a.CHECK_NUM,0),0)", "threeCheckNum", "三级机构体检检查人次(诊疗其它人次)",true));

        tables.put("outReservationRegistered", outReservationRegistered);
        // 医疗监测填报报表统计（填报表） 医疗监测
        //先不添加，还不确定是否使用
        TableObjectReq healthExaminationView = new TableObjectReq("HEALTH_EXAMINATION_VIEW");
        healthExaminationView.setOrgIdColumn("org_id");
        healthExaminationView.setDeptIdColumn("dept_id");
        // 医疗监测填报表（填报表） 医疗监测
        TableObjectReq medicalFilling = new TableObjectReq("MEDICAL_FILLING");
        medicalFilling.setOrgIdColumn("org_id");
        medicalFilling.setDeptIdColumn("dept_id");
        medicalFilling.getColumns().put("expertOutpatientNum", new Column("EXPERT_OUTPATIENT_NUM", "expertOutpatientNum", "专家门急诊人次"));
        medicalFilling.getColumns().put("bedCountCheck", new Column("BED_COUNT_CHECK", "bedCountCheck", "编制床位数(填报)"));
//        medicalFilling.getColumns().put("actualOpenBedNum", new Column("ACTUAL_OPEN_BED_NUM", "actualOpenBedNum", "实际开放总床日数(填报)"));
        medicalFilling.getColumns().put("bedTurnoverNum", new Column("BED_TURNOVER_NUM", "bedTurnoverNum", "床位周转次数(填报)"));
        medicalFilling.getColumns().put("healthyCheckNum", new Column("HEALTHY_CHECK_NUM", "healthyCheckNum", "健康检查人次"));
        medicalFilling.getColumns().put("plannedIncome", new Column("PLANNED_INCOME", "plannedIncome", "计免收入"));
        medicalFilling.getColumns().put("vaccinationNum", new Column("VACCINATION_NUM", "vaccinationNum", "计免接种人次"));
        medicalFilling.getColumns().put("surgeryNum", new Column("SURGERY_NUM", "surgeryNum", "手术次数"));
        medicalFilling.getColumns().put("hospitalizationRs", new Column("HOSPITALIZATION_RS", "hospitalizationRs", "住院人数(不是人次)"));
        medicalFilling.getColumns().put("oneSurgeryNum", new Column("ONE_SURGERY_NUM", "oneSurgeryNum", "I级手术"));
        medicalFilling.getColumns().put("twoSurgeryNum", new Column("TWO_SURGERY_NUM", "twoSurgeryNum", "II级手术"));
        medicalFilling.getColumns().put("threeSurgeryNum", new Column("THREE_SURGERY_NUM", "threeSurgeryNum", "III级手术"));
        medicalFilling.getColumns().put("fourSurgeryNum", new Column("FOUR_SURGERY_NUM", "fourSurgeryNum", "IV级手术次数"));
        medicalFilling.getColumns().put("registrationCarNum", new Column("REGISTRATION_CAR_NUM", "registrationCarNum", "出车人次"));
        tables.put("medicalFilling", medicalFilling);

        //--------------------门诊处方详细表------------------------
        TableObjectReq prescriptionTable = new TableObjectReq("PRESCRIPTION_DETAILED_VIEW");
        prescriptionTable.setOrgIdColumn("org_id");
        prescriptionTable.setDeptIdColumn("dept_id");
        //prescriptionTable.getColumns().put("rxCode", new Column("RX_CODE", "rxCode", "处方流水号"));
        prescriptionTable.getColumns().put("rxTotalMoney", new Column("RX_TOTAL_MONEY", "rxTotalMoney", "处方费用"));
        prescriptionTable.getColumns().put("bigPrescription", new Column("(case when a.RX_TOTAL_MONEY > 400 then 1 else 0 end)", "bigPrescription", "大处方数量",true));
        prescriptionTable.getColumns().put("baseDrugCfNum", new Column("(case when a.JY_DRUG_TYPE_NUM > 0 then 1 else 0 end)", "baseDrugCfNum", "基本药物处方数量",true));
        prescriptionTable.getColumns().put("zyypDrugCfNum", new Column("(case when a.ZYYP_CF_NUM > 0 then 1 else 0 end)", "zyypDrugCfNum", "中药饮片药物处方数量",true));
        prescriptionTable.getColumns().put("drugCfNum", new Column("1", "drugCfNum", "药物处方总数量",true));
        tables.put("prescriptionTable", prescriptionTable);

        TableObjectReq prescriptionTable2 = new TableObjectReq("PRESCRIPTION_DETAILED_VIEW");
        prescriptionTable2.setOrgIdColumn("org_id");
        prescriptionTable2.setDeptIdColumn("dept_id");
        prescriptionTable2.setConditionCustom(" a.RX_TOTAL_MONEY > 400 ");
        //prescriptionTable.getColumns().put("rxCode", new Column("RX_CODE", "rxCode", "处方流水号"));
        prescriptionTable2.getColumns().put("bigPrescription", new Column("1", "bigPrescription", "大处方数量",true));
        tables.put("prescriptionTable2", prescriptionTable2);


        TableObjectReq outDrugSummaryView = new TableObjectReq("OUT_DRUG_SUMMARY_VIEW");
        outDrugSummaryView.setOrgIdColumn("org_id");
        outDrugSummaryView.setDeptIdColumn("dept_id");
        outDrugSummaryView.getColumns().put("userPeopleNum", new Column("USER_PEOPLE_NUM", "userPeopleNum", "处方费用"));
        outDrugSummaryView.getColumns().put("rxTotalMoney", new Column("TOTAL_CF_FEE", "rxTotalMoney", "处方费用"));
        outDrugSummaryView.getColumns().put("bigPrescription", new Column("BIG_CF_NUM", "bigPrescription", "大处方数量"));
        outDrugSummaryView.getColumns().put("baseDrugCfNum", new Column("BASE_CF_NUM", "baseDrugCfNum", "基本药物处方数量"));
        outDrugSummaryView.getColumns().put("zyypDrugCfNum", new Column("CHINA_CF_NUM", "zyypDrugCfNum", "中药饮片药物处方数量"));
        outDrugSummaryView.getColumns().put("drugCfNum", new Column("TOTAL_CF_NUM", "drugCfNum", "药物处方总数量"));
        outDrugSummaryView.getColumns().put("kssDrug1",new Column("KSS_1_UP_NUM","kssDrug1","抗生素1联以上处方数"));
        outDrugSummaryView.getColumns().put("kssDrug1cf",new Column("KSS_1_NUM","kssDrug1cf","抗生素1联处方数"));
        outDrugSummaryView.getColumns().put("kssDrug2",new Column("KSS_2_NUM","kssDrug2","抗生素2联"));
        outDrugSummaryView.getColumns().put("kssDrug3",new Column("KSS_3_NUM","kssDrug3","抗生素3联"));
        outDrugSummaryView.getColumns().put("kssDrug4",new Column("KSS_4_UP_NUM","kssDrug4","抗生素4联"));
        tables.put("outDrugSummaryView", outDrugSummaryView);


        //-------------------财务收支表---------------
        TableObjectReq hrpBudgetSituation = new TableObjectReq("HRP_BUDGET_SITUATION");
        hrpBudgetSituation.setOrgIdColumn("org_id");
        hrpBudgetSituation.setNotToDept(true);
        hrpBudgetSituation.getColumns().put("totalBudgetMoney",new Column("TOTAL_BUDGET_MONEY","totalBudgetMoney","本期预算收入总额"));
        hrpBudgetSituation.getColumns().put("totalActualMoney",new Column("TOTAL_ACTUAL_MONEY","totalActualMoney","本期实际收入总额"));
        hrpBudgetSituation.getColumns().put("totalBudgetMedical",new Column("TOTAL_BUDGET_MEDICAL","totalBudgetMedical","本期预算医疗收入总额"));
        hrpBudgetSituation.getColumns().put("totalActualMedical",new Column("TOTAL_ACTUAL_MEDICAL","totalActualMedical","本期实际医疗收入总额"));
        hrpBudgetSituation.getColumns().put("totalBudgetExpenditure",new Column("TOTAL_BUDGET_EXPENDITURE","totalBudgetExpenditure","本期预算支出总额"));
        hrpBudgetSituation.getColumns().put("totalActualExpenditure",new Column("TOTAL_ACTUAL_EXPENDITURE","totalActualExpenditure","本期实际支出总额"));
        hrpBudgetSituation.getColumns().put("budgetFinanceIncome",new Column("BUDGET_FINANCE_INCOME","budgetFinanceIncome","本期预算财政资金收入总额"));
        hrpBudgetSituation.getColumns().put("actualFinanceIncome",new Column("ACTUAL_FINANCE_INCOME","actualFinanceIncome","本期实际财政资金收入总额"));
        hrpBudgetSituation.getColumns().put("budgetFinanceExpenditure",new Column("BUDGET_FINANCE_EXPENDITURE","budgetFinanceExpenditure","本期预算财政资金支出总额"));
        hrpBudgetSituation.getColumns().put("actualFinanceExpenditure",new Column("ACTUAL_FINANCE_EXPENDITURE","actualFinanceExpenditure","本期实际财政资金支出总额"));
        // todo 这些字段都没有用到  ,只有上面的预算的才是使用到的
        hrpBudgetSituation.getColumns().put("financialBasicExpenditure",new Column("FINANCIAL_BASIC_EXPENDITURE","financialBasicExpenditure","财政基本支出补助收入"));
        hrpBudgetSituation.getColumns().put("businessBudgetBalance",new Column("BUSINESS_BUDGET_BALANCE","businessBudgetBalance","业务收支结余"));
        hrpBudgetSituation.getColumns().put("drugExpenditure",new Column("DRUG_EXPENDITURE","drugExpenditure","药品支出"));
        hrpBudgetSituation.getColumns().put("healthMaterialsExpenditure",new Column("HEALTH_MATERIALS_EXPENDITURE","healthMaterialsExpenditure","卫生材料支出"));
        hrpBudgetSituation.getColumns().put("otherMaterialsExpenditure",new Column("OTHER_MATERIALS_EXPENDITURE","otherMaterialsExpenditure","其他材料支出"));
        hrpBudgetSituation.getColumns().put("averageDeposit",new Column("AVERAGE_DEPOSIT","averageDeposit","平均存货"));
        hrpBudgetSituation.getColumns().put("businessExpenditure",new Column("BUSINESS_EXPENDITURE","businessExpenditure","业务支出"));
        hrpBudgetSituation.getColumns().put("medicalCost",new Column("MEDICAL_COST","medicalCost","医疗总成本"));
        hrpBudgetSituation.getColumns().put("personnelExpenditure",new Column("PERSONNEL_EXPENDITURE","personnelExpenditure","人员支出"));
        hrpBudgetSituation.getColumns().put("totalIncomeMoney",new Column("TOTAL_INCOME_MONEY","totalIncomeMoney","本期收入总额"));
        hrpBudgetSituation.getColumns().put("totalExpenditureMoney",new Column("TOTAL_EXPENDITURE_MONEY","totalExpenditureMoney","本期支出总额"));
        hrpBudgetSituation.getColumns().put("inspectIncome",new Column("INSPECT_INCOME","inspectIncome","检验检查收入"));
        hrpBudgetSituation.getColumns().put("consumablesIncome",new Column("CONSUMABLES_INCOME","consumablesIncome","耗材收入"));
        hrpBudgetSituation.getColumns().put("accountBalance",new Column("ACCOUNT_BALANCE","accountBalance","平均应收账款余额"));
        hrpBudgetSituation.getColumns().put("finSubIncome",new Column("FIN_SUB_INCOME","finSubIncome","财政补助收入"));
        hrpBudgetSituation.getColumns().put("scienceIncome",new Column("SCIENCE_INCOME","scienceIncome","科教项目收入"));
        hrpBudgetSituation.getColumns().put("totalLiabilities",new Column("TOTAL_LIABILITIES","totalLiabilities","负债总额"));
        hrpBudgetSituation.getColumns().put("totalAssets",new Column("TOTAL_ASSETS","totalAssets","资产总额"));
        hrpBudgetSituation.getColumns().put("totalAverageAssets",new Column("TOTAL_AVERAGE_ASSETS","totalAverageAssets","平均总资产"));
        hrpBudgetSituation.getColumns().put("startingFixedAssetTv",new Column("STARTING_FIXED_ASSET_TV","startingFixedAssetTv","固定资产年初数"));
        hrpBudgetSituation.getColumns().put("endingFixedAssetTv",new Column("ENDING_FIXED_ASSET_TV","endingFixedAssetTv","固定资产年末数"));
        hrpBudgetSituation.getColumns().put("earlyNetAssets",new Column("EARLY_NET_ASSETS","earlyNetAssets","年初净资产"));
        hrpBudgetSituation.getColumns().put("endNetAssets",new Column("END_NET_ASSETS","endNetAssets","年末净资产"));
        hrpBudgetSituation.getColumns().put("avgFixedNum",new Column("AVG_FIXED_NUM","avgFixedNum","年平均固定资产总额"));
        hrpBudgetSituation.getColumns().put("medicalExpenditure",new Column("MEDICAL_EXPENDITURE","medicalExpenditure","医疗支出"));
        hrpBudgetSituation.getColumns().put("otherExpenditure",new Column("OTHER_EXPENDITURE","otherExpenditure","其他支出"));
        hrpBudgetSituation.getColumns().put("managementCosts",new Column("MANAGEMENT_COSTS","managementCosts","管理费用"));
        tables.put("hrpBudgetSituation", hrpBudgetSituation);

        //----------------------财务管理抽取数据表-------------
        TableObjectReq hrpBudgetExtract = new TableObjectReq("HRP_BUDGET_EXTRACT");
        hrpBudgetExtract.setOrgIdColumn("org_id");
        hrpBudgetExtract.setNotToDept(true);
        hrpBudgetExtract.getColumns().put("otherIncome",new Column("OTHER_INCOME","otherIncome","其他收入"));
        hrpBudgetExtract.getColumns().put("drugIncome",new Column("DRUG_INCOME","drugIncome","药品收入"));
        hrpBudgetExtract.getColumns().put("healthMaterials",new Column("HEALTH_MATERIALS_INCOME","healthMaterials","卫生材料收入"));
        hrpBudgetExtract.getColumns().put("businessIncome",new Column("BUSINESS_INCOME","businessIncome","业务收入"));
        hrpBudgetExtract.getColumns().put("medicalIncome",new Column("MEDICAL_INCOME","medicalIncome","医疗收入"));
        hrpBudgetExtract.getColumns().put("outDepNum",new Column("OUT_DEP_NUM","outDepNum","门急诊人次"));
        hrpBudgetExtract.getColumns().put("inpHospNum",new Column("INP_HOSP_NUM","inpHospNum","出院人次"));
        hrpBudgetExtract.getColumns().put("avgHospDay",new Column("AVG_HOSP_DAY","avgHospDay","住院总天数"));
        hrpBudgetExtract.getColumns().put("inspectionIncome",new Column("INSPECTION_INCOME","inspectionIncome","检验检查收入"));
        hrpBudgetExtract.getColumns().put("medicalxIncome",new Column("MEDICALX_INCOME","medicalxIncome","医务性收入"));
        tables.put("hrpBudgetExtract", hrpBudgetExtract);

        //----------------------财务管理资产负债表-------------
        TableObjectReq hrpProduction = new TableObjectReq("HRP_PRODUCTION");
        hrpProduction.setOrgIdColumn("org_id");
        hrpProduction.setNotToDept(true);
        hrpProduction.setNotPointOfTime(false);
        hrpProduction.setCustomPointOfTimeFlag(true);//设置取时间最大的一条记录
        hrpProduction.getColumns().put("chQmye",new Column("CH_QMYE","chQmye","存货(期末余额)"));
        hrpProduction.getColumns().put("ldzcQmye",new Column("LDZC_QMYE","ldzcQmye","流动资产合计(期末余额)"));
        hrpProduction.getColumns().put("gdzcQmye",new Column("GDZC_QMYE","gdzcQmye","固定资产原值(期末余额)"));
        hrpProduction.getColumns().put("gdzcjzQmye",new Column("GDZCJZ_QMYE","gdzcjzQmye","固定资产净值(期末余额)"));
        hrpProduction.getColumns().put("fldzchjQmye",new Column("FLDZCHJ_QMYE","fldzchjQmye","非流动资产合计(期末余额)"));
        hrpProduction.getColumns().put("zczjQmye",new Column("ZCZJ_QMYE","zczjQmye","资产总计(期末余额)"));
        hrpProduction.getColumns().put("ldfzhjQmye",new Column("LDFZHJ_QMYE","ldfzhjQmye","流动负债合计(期末余额)"));
        hrpProduction.getColumns().put("fldfzhjQmye",new Column("FLDFZHJ_QMYE","fldfzhjQmye","非流动负债合计(期末余额)"));
        hrpProduction.getColumns().put("jzchjQmye",new Column("JZCHJ_QMYE","jzchjQmye","净资产合计(期末余额)"));
        hrpProduction.getColumns().put("fzhjQmye",new Column("FZHJ_QMYE","fzhjQmye","负债合计(期末余额)"));
        hrpProduction.getColumns().put("chNcye",new Column("CH_NCYE","chNcye","存货(年初余额)"));
        hrpProduction.getColumns().put("ldzcNcye",new Column("LDZC_NCYE","ldzcNcye","流动资产合计(年初余额)"));
        hrpProduction.getColumns().put("gdzcNcye",new Column("GDZC_NCYE","gdzcNcye","固定资产原值(年初余额)"));
        hrpProduction.getColumns().put("gdzcjzNcye",new Column("GDZCJZ_QNCYE","gdzcjzNcye","固定资产净值(年初余额)"));
        hrpProduction.getColumns().put("fldzchjNcye",new Column("FLDZCHJ_NCYE","fldzchjNcye","非流动资产合计(年初余额)"));
        hrpProduction.getColumns().put("zczjNcye",new Column("ZCZJ_NCYE","zczjNcye","资产总计(年初余额)"));
        hrpProduction.getColumns().put("ldfzhjNcye",new Column("LDFZHJ_QNCYE","ldfzhjNcye","流动负债合计(年初余额)"));
        hrpProduction.getColumns().put("fldfzhjNcye",new Column("FLDFZHJ_NCYE","fldfzhjNcye","非流动负债合计(年初余额)"));
        hrpProduction.getColumns().put("fzhjNcye",new Column("FZHJ_NCYE","fzhjNcye","负债合计(年初余额)"));
        hrpProduction.getColumns().put("jzchjNcye",new Column("JZCHJ_NCYE","jzchjNcye","净资产合计(年初余额)"));
        hrpProduction.getColumns().put("pjchye",new Column("round((nvl(a.CH_NCYE,0)+nvl(a.CH_QMYE,0))/2,2)","pjchye","平均存货",true));
        hrpProduction.getColumns().put("pjzzc",new Column("round((nvl(a.ZCZJ_QMYE,0)+nvl(a.ZCZJ_NCYE,0))/2,2)","pjzzc","平均总资产",true));
        hrpProduction.getColumns().put("pjgdzc",new Column("round((nvl(a.GDZC_QMYE,0)+nvl(a.GDZC_NCYE,0))/2,2)","pjgdzc","平均固定资产",true));
        hrpProduction.getColumns().put("pjjzzc",new Column("round((nvl(a.GDZCJZ_QMYE,0)+nvl(a.GDZCJZ_QNCYE,0))/2,2)","pjjzzc","平均固定资产",true));
        tables.put("hrpProduction", hrpProduction);

        //----------------------财务管理收支结余表-------------
        TableObjectReq hrpIncomeExpenditure = new TableObjectReq("HRP_INCOME_EXPENDITURE");
        hrpIncomeExpenditure.setOrgIdColumn("org_id");
        hrpIncomeExpenditure.setNotToDept(true);
        hrpIncomeExpenditure.getColumns().put("czbksr",new Column("CZBKSR","czbksr","财政拨款收入"));
        hrpIncomeExpenditure.getColumns().put("ylsr",new Column("YLSR","ylsr","医疗收入"));
        hrpIncomeExpenditure.getColumns().put("qtsr",new Column("QTSR","qtsr","其他收入"));
        hrpIncomeExpenditure.getColumns().put("bqyy",new Column("BQYY","bqyy","本期盈余"));
        hrpIncomeExpenditure.getColumns().put("czbksrxm",new Column("CZBKSRXM","czbksrxm","财政拨款收入（项目）"));
        hrpIncomeExpenditure.getColumns().put("czxmzc",new Column("CZXMZC","czxmzc","财政项目支出"));
        hrpIncomeExpenditure.getColumns().put("mzsrBbhtj",new Column("MZSR_BBHTJ","mzsrBbhtj","门诊收入(不包含体检)"));
        hrpIncomeExpenditure.getColumns().put("mzsrZcsr",new Column("MZSR_ZCSR","mzsrZcsr","门诊收入-诊察收入"));
        hrpIncomeExpenditure.getColumns().put("mzsrJcsr",new Column("MZSR_JCSR","mzsrJcsr","门诊收入-检查收入"));
        hrpIncomeExpenditure.getColumns().put("mzsrHysr",new Column("MZSR_HYSR","mzsrHysr","门诊收入-化验收入"));
        hrpIncomeExpenditure.getColumns().put("mzsrZlsr",new Column("MZSR_ZLSR","mzsrZlsr","门诊收入-治疗收入"));
        hrpIncomeExpenditure.getColumns().put("mzsrSssr",new Column("MZSR_SSSR","mzsrSssr","门诊收入-手术收入"));
        hrpIncomeExpenditure.getColumns().put("mzsrWsclsr",new Column("MZSR_WSCLSR","mzsrWsclsr","门诊收入-卫生材料收入"));
        hrpIncomeExpenditure.getColumns().put("mzsrYpsr",new Column("MZSR_YPSR","mzsrYpsr","门诊收入-药品收入"));
        hrpIncomeExpenditure.getColumns().put("mzsrXysr",new Column("MZSR_XYSR","mzsrXysr","门诊收入-西药收入"));
        hrpIncomeExpenditure.getColumns().put("mzsrZcysr",new Column("MZSR_ZCYSR","mzsrZcysr","门诊收入-中成药收入"));
        hrpIncomeExpenditure.getColumns().put("mzsrZyypsr",new Column("MZSR_ZYYPSR","mzsrZyypsr","门诊收入-中药饮片收入"));
        hrpIncomeExpenditure.getColumns().put("qtmzsr",new Column("QTMZSR","qtmzsr","其他门诊收入"));
        hrpIncomeExpenditure.getColumns().put("zysr",new Column("ZYSR","zysr","住院收入"));
        hrpIncomeExpenditure.getColumns().put("zysrCwsr",new Column("ZYSR_CWSR","zysrCwsr","住院收入-床位收入"));
        hrpIncomeExpenditure.getColumns().put("zysrZcsr",new Column("ZYSR_ZCSR","zysrZcsr","住院收入-诊察收入"));
        hrpIncomeExpenditure.getColumns().put("zysrJcsr",new Column("ZYSR_JCSR","zysrJcsr","住院收入-检查收入"));
        hrpIncomeExpenditure.getColumns().put("zysrHysr",new Column("ZYSR_HYSR","zysrHysr","住院收入-化验收入"));
        hrpIncomeExpenditure.getColumns().put("zysrZlsr",new Column("ZYSR_ZLSR","zysrZlsr","住院收入-治疗收入"));
        hrpIncomeExpenditure.getColumns().put("zysrSssr",new Column("ZYSR_SSSR","zysrSssr","住院收入-手术收入"));
        hrpIncomeExpenditure.getColumns().put("zysrHlsr",new Column("ZYSR_HLSR","zysrHlsr","住院收入-护理收入"));
        hrpIncomeExpenditure.getColumns().put("zysrWsclsr",new Column("ZYSR_WSCLSR","zysrWsclsr","住院收入-卫生材料收入"));
        hrpIncomeExpenditure.getColumns().put("zysrYpsr",new Column("ZYSR_YPSR","zysrYpsr","住院收入-药品收入"));
        hrpIncomeExpenditure.getColumns().put("zysrXysr",new Column("ZYSR_XYSR","zysrXysr","住院收入-西药收入"));
        hrpIncomeExpenditure.getColumns().put("zysrZcysr",new Column("ZYSR_ZCYSR","zysrZcysr","住院收入-中成药收入"));
        hrpIncomeExpenditure.getColumns().put("zysrZyypsr",new Column("ZYSR_ZYYPSR","zysrZyypsr","住院收入-中药饮片收入"));
        hrpIncomeExpenditure.getColumns().put("qtzysr",new Column("QTZYSR","qtzysr","其他住院收入"));
        hrpIncomeExpenditure.getColumns().put("ywhdYlhdfy",new Column("YWHD_YLHDFY","ywhdYlhdfy","业务活动-医疗活动费用"));
        hrpIncomeExpenditure.getColumns().put("ywhdRyjf",new Column("YWHD_RYJF","ywhdRyjf","业务活动-人员经费"));
        hrpIncomeExpenditure.getColumns().put("ywhdWsclf",new Column("YWHD_WSCLF","ywhdWsclf","业务活动-卫生材料费"));
        hrpIncomeExpenditure.getColumns().put("ywhdYpf",new Column("YWHD_YPF","ywhdYpf","业务活动-药品费"));
        hrpIncomeExpenditure.getColumns().put("ywhdXyf",new Column("YWHD_XYF","ywhdXyf","业务活动-西药费"));
        hrpIncomeExpenditure.getColumns().put("ywhdZcyf",new Column("YWHD_ZCYF","ywhdZcyf","业务活动-中成药费"));
        hrpIncomeExpenditure.getColumns().put("ywhdZyypf",new Column("YWHD_ZYYPF","ywhdZyypf","业务活动-中药饮片费"));
        hrpIncomeExpenditure.getColumns().put("ywhdGdzczjf",new Column("YWHD_GDZCZJF","ywhdGdzczjf","业务活动-固定资产折旧费"));
        hrpIncomeExpenditure.getColumns().put("ywhdWxzctxf",new Column("YWHD_WXZCTXF","ywhdWxzctxf","业务活动-无形资产摊销费"));
        hrpIncomeExpenditure.getColumns().put("ywhdJtzctxf",new Column("YWHD_JTZCTXF","ywhdJtzctxf","业务活动-计提医疗风险基金"));
        hrpIncomeExpenditure.getColumns().put("ywhdQtfy",new Column("YWHD_QTFY","ywhdQtfy","业务活动-其他费用"));
        hrpIncomeExpenditure.getColumns().put("dwglYlhdfy",new Column("DWGL_YLHDFY","dwglYlhdfy","单位管理-医疗活动费用"));
        hrpIncomeExpenditure.getColumns().put("dwglRyjf",new Column("DWGL_RYJF","dwglRyjf","单位管理-人员经费"));
        hrpIncomeExpenditure.getColumns().put("dwglGdzczjf",new Column("DWGL_GDZCZJF","dwglGdzczjf","单位管理-固定资产折旧费"));
        hrpIncomeExpenditure.getColumns().put("dwglWxzctxf",new Column("DWGL_WXZCTXF","dwglWxzctxf","单位管理-无形资产摊销费"));
        hrpIncomeExpenditure.getColumns().put("dwglQtfy",new Column("DWGL_QTFY","dwglQtfy","单位管理-其他费用"));
        hrpIncomeExpenditure.getColumns().put("jbgz",new Column("JBGZ","jbgz","基本工资"));
        hrpIncomeExpenditure.getColumns().put("jxgz",new Column("JXGZ","jxgz","绩效工资"));
        hrpIncomeExpenditure.getColumns().put("sf",new Column("SF","sf","水费"));
        hrpIncomeExpenditure.getColumns().put("df",new Column("DF","df","电费"));
        hrpIncomeExpenditure.getColumns().put("mzypsr",new Column("MZYPSR","mzypsr","门诊药品收入"));
        hrpIncomeExpenditure.getColumns().put("zyypsr",new Column("ZYYPSR","zyypsr","住院药品收入"));
        hrpIncomeExpenditure.getColumns().put("nzsr",new Column("NZSR","nzsr","年总收入(包括财政补助收入、科教项目收入、医疗收入、其他收入等全部收入)"));
        hrpIncomeExpenditure.getColumns().put("kzylfwhdsddxj",new Column("KZYLFWHDSDDXJ","kzylfwhdsddxj","开展医疗服务活动受到的现金"));
        hrpIncomeExpenditure.getColumns().put("ywsrhj",new Column("YWSRHJ","ywsrhj","业务收入合计"));
        hrpIncomeExpenditure.getColumns().put("zyrlf",new Column("ZYRLF","zyrlf","专用燃料费"));
        hrpIncomeExpenditure.getColumns().put("zysrlzybjjsr",new Column("ZYSRLZYBJJSR","zysrlzybjjsr","住院收入来自医保基金收入"));
        hrpIncomeExpenditure.getColumns().put("cybjjsddkx",new Column("CYBJJSDDKX","cybjjsddkx","从医保基金收到的款项"));
        hrpIncomeExpenditure.getColumns().put("nznhzc",new Column("NZNHZC","nznhzc","年总能耗支出(水、电、气、热等能耗)"));
        hrpIncomeExpenditure.getColumns().put("czjbzcbzsddxj",new Column("CZJBZCBZSDDXJ","czjbzcbzsddxj","财政基本支出补助收到的现金"));
        hrpIncomeExpenditure.getColumns().put("cskjxmhdsddczbzywdxj",new Column("CSKJXMHDSDDCZBZYWDXJ","cskjxmhdsddczbzywdxj","从事科教项目活动收到的财政补助以外的现金"));
        hrpIncomeExpenditure.getColumns().put("bqczxmyy",new Column("BQCZXMYY","bqczxmyy","本期财政项目盈余"));
        hrpIncomeExpenditure.getColumns().put("totalPeriodIncome",new Column("(nvl(a.CZBKSR,0) + nvl(a.YLSR,0) + nvl(a.QTSR,0))","totalPeriodIncome","本期收入(总收入)",true));
        hrpIncomeExpenditure.getColumns().put("totalPeriodExpenditure",new Column("(nvl(a.YWHD_YLHDFY,0) + nvl(a.DWGL_YLHDFY,0) + nvl(a.DWGL_QTFY,0))","totalPeriodExpenditure","本期支出(总支出)",true));
        hrpIncomeExpenditure.getColumns().put("totalYlfyZc",new Column("(nvl(a.YWHD_YLHDFY,0) + nvl(a.DWGL_YLHDFY,0))","totalYlfyZc","医疗活动支出",true));
        hrpIncomeExpenditure.getColumns().put("ypzcWsclzcQtclzc",new Column("(nvl(a.YWHD_YPF,0) + nvl(a.YWHD_WSCLF,0))","ypzcWsclzcQtclzc","医疗支出中的药品+卫生材料+其他材料",true));
        hrpIncomeExpenditure.getColumns().put("ylsrQtsr",new Column("(nvl(a.YLSR,0) + nvl(a.QTSR,0))","ylsrQtsr","医疗收入+其他收入",true));
        hrpIncomeExpenditure.getColumns().put("ypfhhcf",new Column("(nvl(a.MZSR_WSCLSR,0) + nvl(a.MZSR_YPSR,0)+nvl(a.ZYSR_WSCLSR,0) + nvl(a.ZYSR_YPSR,0))","ypfhhcf","药品+耗材收入",true));
        tables.put("hrpIncomeExpenditure", hrpIncomeExpenditure);

        //---------------------医保管理信息变化表----------------
        TableObjectReq medInsChangeInfoView = new TableObjectReq("MED_INS_CHANGE_INFO_VIEW");
        medInsChangeInfoView.setOrgIdColumn("org_id");
        medInsChangeInfoView.setNotToDept(true);
        medInsChangeInfoView.getColumns().put("outsideAreaMedicarePay",new Column("OUTSIDE_AREA_MEDICARE_PAY","outsideAreaMedicarePay","统筹区外医保支付金额(万元)"));
        medInsChangeInfoView.getColumns().put("medicarePay",new Column("MEDICARE_PAY","medicarePay","医保支付费用(万元)"));
        medInsChangeInfoView.getColumns().put("medicarePayByDisease",new Column("MEDICARE_PAY_BY_DISEASE","medicarePayByDisease","按病种报销费用(万元)"));
        medInsChangeInfoView.getColumns().put("medicarePayByProject",new Column("MEDICARE_PAY_BY_PROJECT","medicarePayByProject","按项目报销费用(万元)"));
        medInsChangeInfoView.getColumns().put("inpCoordinatedFundCost",new Column("INP_COORDINATED_FUND_COST","inpCoordinatedFundCost","住院统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("offSiteCoordinatedFund",new Column("OFF_SITE_COORDINATED_FUND","offSiteCoordinatedFund","住院异地安置统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("transferCoordinatedFund",new Column("TRANSFER_COORDINATED_FUND","transferCoordinatedFund","住院转外就医统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("staffCoordinatedFund",new Column("STAFF_COORDINATED_FUND","staffCoordinatedFund","职工医保统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("outpOrdCoordinatedFund",new Column("OUTP_ORD_COORDINATED_FUND","outpOrdCoordinatedFund","普通门诊统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("empMedCoordinatedFund",new Column("EMP_MED_COORDINATED_FUND","empMedCoordinatedFund","职工医保统筹基金支出总额(万元)"));
        medInsChangeInfoView.getColumns().put("outpSpecCoordinatedFund",new Column("OUTP_SPEC_COORDINATED_FUND","outpSpecCoordinatedFund","特殊门诊统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("outpSpecDisCoordinatedFund",new Column("OUTP_SPEC_DIS_COORDINATED_FUND","outpSpecDisCoordinatedFund","门诊特殊病种统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("inpLocalCoordinatedFund",new Column("INP_LOCAL_COORDINATED_FUND","inpLocalCoordinatedFund","本地住院统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("bigDisMuCoordinatedFund",new Column("BIG_DIS_MU_COORDINATED_FUND","bigDisMuCoordinatedFund","大病补偿统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("bigDisBusCoordinatedFund",new Column("BIG_DIS_BUS_COORDINATED_FUND","bigDisBusCoordinatedFund","大病商保统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("coordinatedFundCost",new Column("COORDINATED_FUND_COST","coordinatedFundCost","统筹基金支出(万元)"));
        medInsChangeInfoView.getColumns().put("inpTotalCompensationFee",new Column("INP_TOTAL_COMPENSATION_FEE","inpTotalCompensationFee","住院实际补偿总费用(万元)"));
        medInsChangeInfoView.getColumns().put("outpTotalCompensationFee",new Column("OUTP_TOTAL_COMPENSATION_FEE","outpTotalCompensationFee","门诊实际补偿总费用(万元)"));

        tables.put("medInsChangeInfoView", medInsChangeInfoView);


        //----------------------医保信息变化表-----------------------
        TableObjectReq medChangeInfoView = new TableObjectReq("MED_CHANGE_INFO_VIEW");
        medChangeInfoView.setOrgIdColumn("org_id");
        medChangeInfoView.setNotToDept(true);
        medChangeInfoView.getColumns().put("townPersonalFundNum",new Column("TOWN_PERSONAL_FUND_NUM","townPersonalFundNum","城镇职工个人账户中基金的收入金额(万元)"));
        medChangeInfoView.getColumns().put("townPlanningFundNum",new Column("TOWN_PLANNING_FUND_NUM","townPlanningFundNum","城镇职工统筹账户中基金的收入金额(万元)"));
        medChangeInfoView.getColumns().put("townPersonalPayNum",new Column("TOWN_PERSONAL_PAY_NUM","townPersonalPayNum","城镇职工个人账户中基金的支出金额(万元)"));
        medChangeInfoView.getColumns().put("townPlanningPayNum",new Column("TOWN_PLANNING_PAY_NUM","townPlanningPayNum","城镇职工统筹账户中基金的支出金额(万元)"));
        medChangeInfoView.getColumns().put("townPersonalBalanceNum",new Column("TOWN_PERSONAL_BALANCE_NUM","townPersonalBalanceNum","城镇职工个人账户的结余金额(万元)"));
        medChangeInfoView.getColumns().put("townPlanningBalanceNum",new Column("TOWN_PLANNING_BALANCE_NUM","townPlanningBalanceNum","城镇职工统筹账户的结余金额(万元)"));
        medChangeInfoView.getColumns().put("townInsuredNum",new Column("TOWN_INSURED_NUM","townInsuredNum","城镇职工的参保人数"));
        medChangeInfoView.getColumns().put("townRetireNum",new Column("TOWN_RETIRE_NUM","townRetireNum","城镇退休职工参保人数"));
        medChangeInfoView.getColumns().put("townHospNum",new Column("TOWN_HOSP_NUM","townHospNum","城镇职工住院人数"));
        medChangeInfoView.getColumns().put("townHospPayNum",new Column("TOWN_HOSP_PAY_NUM","townHospPayNum","城镇职工住院支出金额(万元)"));
        medChangeInfoView.getColumns().put("townHospPersonalNum",new Column("TOWN_HOSP_PERSONAL_NUM","townHospPersonalNum","城镇职工住院个人支出金额(万元)"));
        medChangeInfoView.getColumns().put("townHospMedicallNum",new Column("TOWN_HOSP_MEDICALL_NUM","townHospMedicallNum","城镇职工住院医保支出金额(万元)"));
        medChangeInfoView.getColumns().put("townTransferNum",new Column("TOWN_TRANSFER_NUM","townTransferNum","城镇职工医保住院转外就医人次数"));
        medChangeInfoView.getColumns().put("townDifferentNum",new Column("TOWN_DIFFERENT_NUM","townDifferentNum","城镇职工医保住院异地住院人次数"));
        medChangeInfoView.getColumns().put("townOneHospNum",new Column("TOWN_ONE_HOSP_NUM","townOneHospNum","城镇职工一级住院人次"));
        medChangeInfoView.getColumns().put("townTwoHospNum",new Column("TOWN_TWO_HOSP_NUM","townTwoHospNum","城镇职工二级住院人次"));
        medChangeInfoView.getColumns().put("townThreeHospNum",new Column("TOWN_THREE_HOSP_NUM","townThreeHospNum","城镇职工三级住院人次"));
        medChangeInfoView.getColumns().put("townTransferPayNum",new Column("TOWN_TRANSFER_PAY_NUM","townTransferPayNum","城镇职工医保住院转外就医基金支出金额(万元)"));
        medChangeInfoView.getColumns().put("townDifferentPayNum",new Column("TOWN_DIFFERENT_PAY_NUM","townDifferentPayNum","城镇职工医保住院异地就医基金支出金额(万元)"));
        medChangeInfoView.getColumns().put("townFundPayNum",new Column("TOWN_FUND_PAY_NUM","townFundPayNum","城镇职工医保住院基金支出金额(万元)"));
        medChangeInfoView.getColumns().put("townOneFundNum",new Column("TOWN_ONE_FUND_NUM","townOneFundNum","城镇职工一级住院基金支出(万元)"));
        medChangeInfoView.getColumns().put("townTwoFundNum",new Column("TOWN_TWO_FUND_NUM","townTwoFundNum","城镇职工二级住院基金支出(万元)"));
        medChangeInfoView.getColumns().put("townThreeFundNum",new Column("TOWN_THREE_FUND_NUM","townThreeFundNum","城镇职工三级住院基金支出(万元)"));
        medChangeInfoView.getColumns().put("townOneBxNum",new Column("TOWN_ONE_BX_NUM","townOneBxNum","城镇职工一级住院医保报销费用(万元)"));
        medChangeInfoView.getColumns().put("townTwoBxNum",new Column("TOWN_TWO_BX_NUM","townTwoBxNum","城镇职工二级住院医保报销费用(万元)"));
        medChangeInfoView.getColumns().put("townThreeBxNum",new Column("TOWN_THREE_BX_NUM","townThreeBxNum","城镇职工三级住院基金支出(万元)"));
        medChangeInfoView.getColumns().put("townZwjyBxNum",new Column("TOWN_ZWJY_BX_NUM","townZwjyBxNum","城镇职工转外就医医保报销费用(万元)"));
        medChangeInfoView.getColumns().put("townYdzyBxNum",new Column("TOWN_YDZY_BX_NUM","townYdzyBxNum","城镇职工异地住院医保报销费用(万元)"));
        medChangeInfoView.getColumns().put("townOneHospCost",new Column("TOWN_ONE_HOSP_COST","townOneHospCost","城镇职工一级住院费用(万元)"));
        medChangeInfoView.getColumns().put("townTwoHospCost",new Column("TOWN_TWO_HOSP_COST","townTwoHospCost","城镇职工二级住院费用(万元)"));
        medChangeInfoView.getColumns().put("townThreeHospCost",new Column("TOWN_THREE_HOSP_COST","townThreeHospCost","城镇职工三级住院费用(万元)"));
        medChangeInfoView.getColumns().put("townZwjyHospCost",new Column("TOWN_ZWJY_HOSP_COST","townZwjyHospCost","城镇职工转外就医住院费用(万元)"));
        medChangeInfoView.getColumns().put("townYdzyHospCost",new Column("TOWN_YDZY_HOSP_COST","townYdzyHospCost","城镇职工异地住院费用(万元)"));
        medChangeInfoView.getColumns().put("ruralPayFundNum",new Column("RURAL_PAY_FUND_NUM","ruralPayFundNum","城乡居民个人缴纳基金金额(万元)"));
        medChangeInfoView.getColumns().put("ruralSubsidyFundNum",new Column("RURAL_SUBSIDY_FUND_NUM","ruralSubsidyFundNum","城乡居民政府补助基金金额(万元)"));
        medChangeInfoView.getColumns().put("ruralHospFundNum",new Column("RURAL_HOSP_FUND_NUM","ruralHospFundNum","城乡居民住院基金支出金额(万元)"));
        medChangeInfoView.getColumns().put("ruralOutpFundNum",new Column("RURAL_OUTP_FUND_NUM","ruralOutpFundNum","城乡居民门诊基金支出金额(万元)"));
        medChangeInfoView.getColumns().put("ruralOutpSpecialNum",new Column("RURAL_OUTP_SPECIAL_NUM","ruralOutpSpecialNum","城乡居民门诊特殊病种基金支出金额(万元)"));
        medChangeInfoView.getColumns().put("ruralSeriousFundNum",new Column("RURAL_SERIOUS_FUND_NUM","ruralSeriousFundNum","城乡居民大病补充基金支出金额(万元)"));
        medChangeInfoView.getColumns().put("ruralInsuredNum",new Column("RURAL_INSURED_NUM","ruralInsuredNum","城乡居民参保人数"));
        medChangeInfoView.getColumns().put("ruralHospNum",new Column("RURAL_HOSP_NUM","ruralHospNum","城乡居民住院人次"));
        medChangeInfoView.getColumns().put("ruralDifferenthospNum",new Column("RURAL_DIFFERENT_HOSP_NUM","ruralDifferenthospNum","城乡居民统筹区外住院人次"));
        medChangeInfoView.getColumns().put("ruralDifferentFundNum",new Column("RURAL_DIFFERENT_FUND_NUM","ruralDifferentFundNum","城乡居民统筹区外住院基金支出(万元)"));
        medChangeInfoView.getColumns().put("ruralSubsidyFinancing",new Column("RURAL_SUBSIDY_FINANCING","ruralSubsidyFinancing","城乡居民政府补助总筹资(万元)"));
        medChangeInfoView.getColumns().put("ruralPersonalFinancing",new Column("RURAL_PERSONAL_FINANCING","ruralPersonalFinancing","城乡居民个人缴费总筹资(万元)"));
        medChangeInfoView.getColumns().put("ruralOneHospNum",new Column("RURAL_ONE_HOSP_NUM","ruralOneHospNum","城乡居民一级住院人次"));
        medChangeInfoView.getColumns().put("ruralTwoHospNum",new Column("RURAL_TWO_HOSP_NUM","ruralTwoHospNum","城乡居民二级住院人次"));
        medChangeInfoView.getColumns().put("ruralThreeHospNum",new Column("RURAL_THREE_HOSP_NUM","ruralThreeHospNum","城乡居民三级住院人次"));
        medChangeInfoView.getColumns().put("ruralOneFundNum",new Column("RURAL_ONE_FUND_NUM","ruralOneFundNum","城乡居民一级住院基金支出"));
        medChangeInfoView.getColumns().put("ruralTwoFundNum",new Column("RURAL_TWO_FUND_NUM","ruralTwoFundNum","城乡居民二级住院基金支出"));
        medChangeInfoView.getColumns().put("ruralThreeFundNum",new Column("RURAL_THREE_Fund_NUM","ruralThreeFundNum","城乡居民三级住院基金支出"));
        tables.put("medChangeInfoView", medChangeInfoView);

        TableObjectReq highValueUsedView = new TableObjectReq("HIGH_VALUE_USED_VIEW");
        highValueUsedView.setOrgIdColumn("org_id");
        highValueUsedView.setNotToDept(true);
        highValueUsedView.getColumns().put("itemPrice",new Column("ITEM_PRICE","itemPrice","耗材价格"));
        highValueUsedView.getColumns().put("inpatentMoney",new Column("INPATENT_MONEY","inpatentMoney","耗材总费用"));
        highValueUsedView.getColumns().put("itemUseNum",new Column("ITEM_USE_NUM","itemUseNum","耗材使用数量"));
        tables.put("highValueUsedView", highValueUsedView);
        //
        TableObjectReq prescriptionDetailedView = new TableObjectReq("PRESCRIPTION_DETAILED_VIEW");
        prescriptionDetailedView.setOrgIdColumn("org_id");
        prescriptionDetailedView.setNotToDept(true);
        prescriptionDetailedView.setConditionCustom(" a.KSS_DRUG_TYPE_NUM >= 1 ");
        prescriptionDetailedView.getColumns().put("kssDrug1",new Column("1","kssDrug1","抗生素1联以上处方数",true));
        prescriptionDetailedView.getColumns().put("kssDrug1cf",new Column("(case when a.KSS_DRUG_TYPE_NUM = 1 then 1 else 0 end)","kssDrug1cf","抗生素1联处方数",true));
        prescriptionDetailedView.getColumns().put("kssDrug2",new Column("(case when a.KSS_DRUG_TYPE_NUM = 2 then 1 else 0 end)","kssDrug2","抗生素2联",true));
        prescriptionDetailedView.getColumns().put("kssDrug3",new Column("(case when a.KSS_DRUG_TYPE_NUM = 3 then 1 else 0 end)","kssDrug3","抗生素3联",true));
        prescriptionDetailedView.getColumns().put("kssDrug4",new Column("(case when a.KSS_DRUG_TYPE_NUM >= 4 then 1 else 0 end)","kssDrug4","抗生素4联",true));
        tables.put("prescriptionDetailedView", prescriptionDetailedView);

        //填报财务管理表
        TableObjectReq repFinance = new TableObjectReq("REP_FINANCE");
        repFinance.setOrgIdColumn("org_id");
        repFinance.setNotToDept(true);
//        repFinance.getColumns().put("zsrys",new Column("ZSRYS","zsrys","总收入预算"));
//        repFinance.getColumns().put("ylsrys",new Column("YLSRYS","ylsrys","医疗收入预算"));
//        repFinance.getColumns().put("zzcys",new Column("ZZCYS","zzcys","支出预算"));
//        repFinance.getColumns().put("czzjsr",new Column("CZZJSR","czzjsr","财政资金收入"));
//        repFinance.getColumns().put("czzjzc",new Column("CZZJZC","czzjzc","财政资金支出"));
//        repFinance.getColumns().put("yphcsr",new Column("YPHCSR","yphcsr","药品、耗材收入"));

        repFinance.getColumns().put("totalBudgetMoney",new Column("ZSRYS","totalBudgetMoney","本期预算收入总额"));
        repFinance.getColumns().put("totalBudgetMedical",new Column("YLSRYS","totalBudgetMedical","本期预算医疗收入总额"));
        repFinance.getColumns().put("totalBudgetExpenditure",new Column("ZZCYS","totalBudgetExpenditure","本期预算支出总额"));
        repFinance.getColumns().put("budgetFinanceIncome",new Column("CZZJSR","budgetFinanceIncome","本期预算财政资金收入总额"));
        repFinance.getColumns().put("budgetFinanceExpenditure",new Column("CZZJZC","budgetFinanceExpenditure","本期预算财政资金支出总额"));

        tables.put("repFinance", repFinance);

        //填报人力资源
        TableObjectReq repHr = new TableObjectReq("REP_HR");
        repHr.setOrgIdColumn("org_id");
        repHr.setNotToDept(true);
        repHr.setNotPointOfTime(false);
        repHr.setCustomPointOfTimeFlag(true);
        repHr.getColumns().put("managerCpath",new Column("SALARY","managerCpath","总薪酬"));
        repHr.getColumns().put("allDoctorNumNx",new Column("YSZXC","allDoctorNumNx","医生总薪酬"));
        repHr.getColumns().put("yjrynx",new Column("YJRYZXC","yjrynx","医技总薪酬"));
        repHr.getColumns().put("ysrynx",new Column("YJSRYZXC","ysrynx","药剂总薪酬"));
        repHr.getColumns().put("hsrynx",new Column("HSZXC","hsrynx","护士总薪酬"));
        repHr.getColumns().put("gqrynx",new Column("GQRYZXC","gqrynx","工勤人员总薪酬"));
        repHr.getColumns().put("glrynx",new Column("GLRYZXC","glrynx","管理人员总薪酬"));
        repHr.getColumns().put("wsjsrynx",new Column("QTJSRYZXC","wsjsrynx","其他技术人员总薪酬"));
        repHr.getColumns().put("meritPay",new Column("PERFORMANCE_SALARY","meritPay","总绩效工资"));
        repHr.getColumns().put("regPersonsOnSalary",new Column("ON_JOB_SALARY","regPersonsOnSalary","在岗在册人员总薪酬"));
        repHr.getColumns().put("regPersonsOnPersonnel",new Column("ON_JOB_PERFORMANCE","regPersonsOnPersonnel","在岗在册人员总绩效工资"));
        repHr.getColumns().put("contractRemuneration",new Column("TEMPORARY_SALARY","contractRemuneration","临聘/合同人员总薪酬"));
        repHr.getColumns().put("contractPerformancePay",new Column("TEMPORARY_PERFORMANCE","contractPerformancePay","临聘/合同人员总绩效工资"));
        repHr.getColumns().put("deanOfSalary",new Column("DEAN_YEAR_SALARY","deanOfSalary","院长年薪"));
        repHr.getColumns().put("leadersSalary",new Column("LEADER_YEAR_SALARY","leadersSalary","院级领导(不包括院长)总年薪"));

        repHr.getColumns().put("jskyss",new Column("JSKYSSL","jskyss","精神科医生数量"));
        repHr.getColumns().put("dysl",new Column("DYSL","dysl","党员数量"));
        repHr.getColumns().put("miDoctorNum",new Column("YBYSRS","miDoctorNum","医保医师人数"));
        repHr.getColumns().put("studyNum",new Column("JCYWRYJXRS","studyNum","基层医务人员去上级医院学习进修的人次数"));
        repHr.getColumns().put("technologyNum",new Column("XCZYJSRS","technologyNum","二级以上医疗机构向基层医疗机构下沉专业技术人数"));
        repHr.getColumns().put("dispatchAdminNum",new Column("XCGLRCRS","dispatchAdminNum","二级以上医疗机构向基层医疗机构下沉管理人才人数"));
        repHr.getColumns().put("sinkNum",new Column("XCJZYSRS","sinkNum","二级及以上公立医院下沉就诊医师数"));
        tables.put("repHr", repHr);

        //填报医疗监测表
        TableObjectReq repMedical = new TableObjectReq("REP_MEDICAL");
        repMedical.setOrgIdColumn("org_id");
        repMedical.setNotToDept(true);
        repMedical.getColumns().put("plannedIncome",new Column("JHMYSR","plannedIncome","计划免疫收入"));
        repMedical.getColumns().put("zZyypsr",new Column("ZYYPSR","zZyypsr","中药饮片收入"));
        repMedical.getColumns().put("vaccinationNum",new Column("JHMYRC","vaccinationNum","计划免疫人次"));
        repMedical.getColumns().put("finSubIncome",new Column("CZBZSR","finSubIncome","财政补助收入"));
        repMedical.getColumns().put("scienceIncome",new Column("KJXMSR","scienceIncome","科教项目收入"));
        repMedical.getColumns().put("zQtsr",new Column("QTSR","zQtsr","其他收入"));
        repMedical.getColumns().put("registrationCarNum", new Column("JHCCCRC", "registrationCarNum", "出车人次"));
        tables.put("repMedical", repMedical);

        //填报公共卫生表
        TableObjectReq repPh = new TableObjectReq("REP_PH");
        repPh.setOrgIdColumn("org_id");
        repPh.setNotToDept(true);
        repPh.setNotPointOfTime(false);
        repPh.setCustomPointOfTimeFlag(true);
        repPh.getColumns().put("jzzcyjs",new Column("JZZCYJS","jzzcyjs","接种证查验间数"));
        repPh.getColumns().put("xyjzymrs",new Column("XYJZYMRS","xyjzymrs","需要接种疫苗人数"));
        repPh.getColumns().put("myghymjzs",new Column("MYGHYMJZS","myghymjzs","免疫规划疫苗接种数"));
        repPh.getColumns().put("cyhbzs",new Column("CYHBZS","cyhbzs","查验后补种数"));
        repPh.getColumns().put("xybzrs",new Column("XYBZRS","xybzrs","需要补种人数"));
        repPh.getColumns().put("wsjdbgs",new Column("WSJDBGS","wsjdbgs","卫生监督报告数卫生事件及时报告次数"));
        repPh.getColumns().put("zwssjcs",new Column("ZWSSJCS","zwssjcs","总卫生事件次数"));
        tables.put("repPh", repPh);


        return tables;
    }
    public static Map<String, TableObjectReq> tablesFactor() {
        //人力填报因子
        Map<String, TableObjectReq> tables = new HashMap<>();
        TableObjectReq hrBaseInformation = new TableObjectReq("HR_INFO_TABLE");
        hrBaseInformation.setTableChineseName("人力卫统表");
        hrBaseInformation.getColumns().put("wjwid",new Column("wjwid","wjwid","卫健委机构"));
        hrBaseInformation.getColumns().put("yljgid",new Column("yljgid","yljgid","医疗卫生机构"));
        hrBaseInformation.getColumns().put("sfzjzl",new Column("sfzjzl","sfzjzl","身份证件种类"));
        hrBaseInformation.getColumns().put("xbdm",new Column("xbdm","xbdm","性别代码"));
        hrBaseInformation.getColumns().put("mzdm",new Column("mzdm","mzdm","民族代码"));
        hrBaseInformation.getColumns().put("szksdm",new Column("szksdm","szksdm","所在科室代码"));
        hrBaseInformation.getColumns().put("kssjmc",new Column("kssjmc","kssjmc","科室实际名称本期"));
        hrBaseInformation.getColumns().put("cszylbdm",new Column("cszylbdm","cszylbdm","从事专业类别代码"));
        hrBaseInformation.getColumns().put("yszylbdm",new Column("yszylbdm","yszylbdm","医师执业类别代码"));
        hrBaseInformation.getColumns().put("yszyfwdm",new Column("yszyfwdm","yszyfwdm","医师执业范围代码可选三个"));
        hrBaseInformation.getColumns().put("sfdddzyys",new Column("sfdddzyys","sfdddzyys","是否多地点执业医师"));
        hrBaseInformation.getColumns().put("dezydwjglb",new Column("dezydwjglb","dezydwjglb","第2执业单位的机构类别"));
        hrBaseInformation.getColumns().put("dszydwjglb",new Column("dszydwjglb","dszydwjglb","第3执业单位的机构类别"));
        hrBaseInformation.getColumns().put("sfhdgjzs",new Column("sfhdgjzs","sfhdgjzs","是否获得国家住院医师规范化培训合格证书"));
        hrBaseInformation.getColumns().put("zyyszsbm",new Column("zyyszsbm","zyyszsbm","住院医师规范化培训合格证书编码"));
        hrBaseInformation.getColumns().put("xzywglzwdm",new Column("xzywglzwdm","xzywglzwdm","行政/业务管理职务代码"));
        hrBaseInformation.getColumns().put("zyjszgdm",new Column("zyjszgdm","zyjszgdm","专业技术资格评代码"));
        hrBaseInformation.getColumns().put("zyjszwdm",new Column("zyjszwdm","zyjszwdm","专业技术职务聘代码"));
        hrBaseInformation.getColumns().put("xldm",new Column("xldm","xldm","学历代码"));
        hrBaseInformation.getColumns().put("zgxldm",new Column("zgxldm","zgxldm","最高学历代码"));
        hrBaseInformation.getColumns().put("xwdm",new Column("xwdm","xwdm","学位代码"));
        hrBaseInformation.getColumns().put("yjxkdm",new Column("yjxkdm","yjxkdm","一级学科代码"));
        hrBaseInformation.getColumns().put("szydm",new Column("szydm","szydm","所学专业代码"));
        hrBaseInformation.getColumns().put("zktc1",new Column("zktc1","zktc1","专科特长仅要求医院主任副主任医师填写"));
        hrBaseInformation.getColumns().put("zktc2",new Column("zktc2","zktc2","专科特长仅要求医院主任副主任医师填写"));
        hrBaseInformation.getColumns().put("zktc3",new Column("zktc3","zktc3","专科特长仅要求医院主任副主任医师填写"));
        hrBaseInformation.getColumns().put("nnryldqk",new Column("nnryldqk","nnryldqk","本年人员流动情况"));
        hrBaseInformation.getColumns().put("drdcsj",new Column("drdcsj","drdcsj","流入/流出时间"));
        hrBaseInformation.getColumns().put("bzqk",new Column("bzqk","bzqk","编制情况本期"));
        hrBaseInformation.getColumns().put("sfzcqkyx",new Column("sfzcqkyx","sfzcqkyx","是否注册为全科医学专业"));
        hrBaseInformation.getColumns().put("qdhgzs",new Column("qdhgzs","qdhgzs","全科医生取得培训合格证书情况（限参加培训人员填写）本期"));
        hrBaseInformation.getColumns().put("sfqdxcqkzyzlys",new Column("sfqdxcqkzyzlys","sfqdxcqkzyzlys","是否取得乡村全科执业助理医师证书"));
        hrBaseInformation.getColumns().put("c33",new Column("c33","c33","是否由乡镇卫生院或社区卫生服务机构派驻村卫生室工作"));
        hrBaseInformation.getColumns().put("sfcstjgz",new Column("sfcstjgz","sfcstjgz","是否从事统计信息化业务工作  "));
        hrBaseInformation.getColumns().put("tjxxhgz",new Column("tjxxhgz","tjxxhgz","统计信息化业务工作"));
        hrBaseInformation.getColumns().put("tbjgdm",new Column("tbjgdm","tbjgdm","填报机构代码"));
        hrBaseInformation.getColumns().put("sfzg",new Column("sfzg","sfzg","是否在岗"));
        hrBaseInformation.getColumns().put("userid",new Column("userid","userid","用户唯一id"));
        hrBaseInformation.getColumns().put("reportTime",new Column("report_time","reportTime","上报时间"));
        tables.put("hrBaseInformation", hrBaseInformation);
        return tables;
    }

    /*
     * @Description: 请求表List, 参数为需要查询的表
     * @Author: Nomark
     * @Date: 2019/9/8 22:15
     * @Param: [strings]
     * @Return: java.util.List<com.ylz.sxpt.req.TableObjectReq>
     */
    public static List<TableObjectReq> queryTables(String... strings) {
        List tablesList = new ArrayList();
        for(String str:strings) {
            String tableName = str;
            TableObjectReq table = new TableObjectReq();

            if(str.contains(LastParamEnum.LAST_YEAR.getCode())) {
                //查询上一年
                tableName = str.replace(LastParamEnum.LAST_YEAR.getCode(), "");
                table = tablesMap().get(tableName);
                table.setQueryLastYear(true);
                Map<String, Column> columnsMap = new HashMap<>();

                for(Column column : table.getColumns().values()) {
                    Column newColumn = new Column();
                    BeanUtils.copyProperties(column, newColumn);
                    newColumn.setAlias(column.getAlias() + LastParamEnum.LAST_YEAR.getColumnType());
                    columnsMap.put(column.getAlias() + LastParamEnum.LAST_YEAR.getColumnType(), newColumn);
                }
                table.setColumns(columnsMap);
            }else if (str.contains(LastParamEnum.LAST_DAY.getCode())){
                //查询上一季度
                tableName = str.replace(LastParamEnum.LAST_DAY.getCode(), "");
                table = tablesMap().get(tableName);
                table.setQueryLast(true);
                Map<String, Column> columnsMap = new HashMap<>();

                for(Column column : table.getColumns().values()) {
                    Column newColumn = new Column();
                    BeanUtils.copyProperties(column, newColumn);
                    newColumn.setAlias(column.getAlias() + LastParamEnum.LAST_DAY.getColumnType());
                    columnsMap.put(column.getAlias() +  LastParamEnum.LAST_DAY.getColumnType(), newColumn);
                }
                table.setColumns(columnsMap);
            } else if (str.contains(LastParamEnum.LAST_CUSTOM.getCode())){
                //查询自定义时间表
                tableName = str.replace(LastParamEnum.LAST_CUSTOM.getCode(), "");
                table = tablesMap().get(tableName);
                table.setQueryCustom(true);
                Map<String, Column> columnsMap = new HashMap<>();

                for(Column column : table.getColumns().values()) {
                    Column newColumn = new Column();
                    BeanUtils.copyProperties(column, newColumn);
                    newColumn.setAlias(column.getAlias() + LastParamEnum.LAST_CUSTOM.getColumnType());
                    columnsMap.put(column.getAlias() +  LastParamEnum.LAST_CUSTOM.getColumnType(), newColumn);
                }
                table.setColumns(columnsMap);
            } else {
                table = tablesMap().get(tableName);
            }
            tablesList.add(table);

        }
        return tablesList;
    }


    /*
     * @Description: 通过表名别名生成字段列表
     * @Author: Nomark
     * @Date: 2019/9/7 18:15
     * @Param: []
     * @Return: java.util.List<com.ylz.common.pojo.Column>
     */
    public static List<Column> fieldsList(Map<String, TableObjectReq> tables) {
        List<Column> list = new ArrayList();
        for(TableObjectReq table : tables.values()) {
            for(Column column : table.getColumns().values()) {
                list.add(column);
            }
        }
        return list;
    }

    /*
     * @Description: 根据String数据生成fields
     * @Author: Nomark
     * @Date: 2019/9/9 17:30
     * @Param: [tables]
     * @Return: java.util.List<com.ylz.common.pojo.Column>
     */
    public static List<Column> fieldsList(String... tablesName) {
        List<Column> list = new ArrayList();
        Map<String, TableObjectReq> tablesMap = tablesMap();
        for(String tableName : tablesName) {
            Map<String, Column> columnsMap = new HashMap<>();
            if(tableName.contains(LastParamEnum.LAST_YEAR.getCode())) {
                columnsMap = tablesMap.get(tableName.replace(LastParamEnum.LAST_YEAR.getCode(), "")).getColumns();
                //防止指针修改原来的静态值,保证原静态值不变
                Map<String, Column> newColumnsMap = new HashMap<>();
                for(Column column : columnsMap.values()) {
                    Column newColumn = new Column();
                    BeanUtils.copyProperties(column, newColumn);
                    newColumn.setAlias(column.getAlias() + LastParamEnum.LAST_YEAR.getColumnType());
                    newColumnsMap.put(column.getAlias() + LastParamEnum.LAST_YEAR.getColumnType(), newColumn);
                }
                columnsMap = newColumnsMap;
            }else if (tableName.contains(LastParamEnum.LAST_DAY.getCode())){
                columnsMap = tablesMap.get(tableName.replace(LastParamEnum.LAST_DAY.getCode(), "")).getColumns();
                //防止指针修改原来的静态值,保证原静态值不变
                Map<String, Column> newColumnsMap = new HashMap<>();
                for(Column column : columnsMap.values()) {
                    Column newColumn = new Column();
                    BeanUtils.copyProperties(column, newColumn);
                    newColumn.setAlias(column.getAlias() + LastParamEnum.LAST_DAY.getColumnType());
                    newColumnsMap.put(column.getAlias() + LastParamEnum.LAST_DAY.getColumnType(), newColumn);
                }
                columnsMap = newColumnsMap;
            } else if (tableName.contains(LastParamEnum.LAST_CUSTOM.getCode())){
                columnsMap = tablesMap.get(tableName.replace(LastParamEnum.LAST_CUSTOM.getCode(), "")).getColumns();
                //防止指针修改原来的静态值,保证原静态值不变
                Map<String, Column> newColumnsMap = new HashMap<>();
                for(Column column : columnsMap.values()) {
                    Column newColumn = new Column();
                    BeanUtils.copyProperties(column, newColumn);
                    newColumn.setAlias(column.getAlias() + LastParamEnum.LAST_CUSTOM.getColumnType());
                    newColumnsMap.put(column.getAlias() + LastParamEnum.LAST_CUSTOM.getColumnType(), newColumn);
                }
                columnsMap = newColumnsMap;
            } else {
                columnsMap = tablesMap.get(tableName).getColumns();
            }

            for(Column column : columnsMap.values()) {
                Column addColumn = new Column();
                BeanUtils.copyProperties(column, addColumn);
                list.add(addColumn);
            }
        }
        return list;
    }
    public static Set getFieldsBySql(String text){
        String regexContent = "\\(.*?\\)";
        Pattern pattern = Pattern.compile(regexContent);
        Matcher matcher = pattern.matcher(text);
        Set<String> list = new HashSet<String>();
        while (matcher.find()){
            MatchResult result = matcher.toMatchResult();
            String string = result.group();
            list.add(string);
        }
        String regex2 = ".*?\\(";
        String regex3 = "\\).*?";
        Set<String> fields = new HashSet<String>();
        for (String string: list){
            String[] strings = string.replaceAll(regex2,"").replaceAll(regex3,"").split("\\+|\\-|\\*|\\/");
            fields.addAll(Arrays.asList(strings));
        }
        return fields;
    }

}
