package com.ylz.controller;

import com.alibaba.fastjson.JSONObject;
import com.ylz.ZhGradeDisService;
import com.ylz.req.FamilyDoctorInfoReq;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Api(tags = "测试接口")
public class TestController {

    @Autowired
    private ZhGradeDisService zhGradeDisService;

    // http://127.0.0.1:8080/user/findAll
    /*
     * @Author LCW
     * @Description //
     * @Date  2020/12/15
     * @Param
    county: "440400000000"
    dataRangeList: ["0"]
    dateEnd: "2019-12-31"
    dateStart: "2019-01-01"
    dateType: "year"
    endStr: "2019"
    label: "珠海市"
    rank: 2
    searchRange: "current"
    showCounty: true
    showDataRange: true
    startStr: "2019"
    value: "440400000000"
     * @return
     **/
    @GetMapping("/findAll")
    @PreAuthorize("permitAll()")
    public Map<String, Object> findAll(){
        String jsonStr = "{\"dateType\":\"year\",\"dateStart\":\"2019-01-01\",\"dateEnd\":\"2019-12-31\",\"startStr\":\"2019\",\"endStr\":\"2019\",\"searchRange\":\"current\",\"showDataRange\":true,\"showCounty\":true,\"dataRangeList\":[\"0\"],\"rank\":2,\"label\":\"珠海市\",\"value\":\"440400000000\",\"county\":\"440400000000\",\"pageNo\":1}";
        FamilyDoctorInfoReq req = JSONObject.parseObject(jsonStr,FamilyDoctorInfoReq.class);
        return zhGradeDisService.gradeCockpit(req);
    }


}
