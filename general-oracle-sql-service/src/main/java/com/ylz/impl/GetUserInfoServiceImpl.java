package com.ylz.impl;


import com.ylz.GetUserInfoService;
import com.ylz.entity.MybatisDemoUser;
import com.ylz.mapper.MybatisDemoUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;



/*
 *
 * @author paida 派哒 zeyu.pzy@alibaba-inc.com
 * @date 2020/10/27
 */

@Service
public class GetUserInfoServiceImpl implements GetUserInfoService {

    @Autowired
    protected MybatisDemoUserMapper mybatisDemoUserMapper;

    @Override
    public void getUserInfoById(String id, Model model)
    {


        //search by id, get UserInfo
        MybatisDemoUser user = mybatisDemoUserMapper.queryUserInfo(id);
        model.addAttribute("name", user.getId())
                .addAttribute("age", user.getAge())
                .addAttribute("height", user.getHeight())
                .addAttribute("weight", user.getWeight());
    }
}
