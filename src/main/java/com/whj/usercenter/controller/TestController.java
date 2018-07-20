package com.whj.usercenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghaijun
 * @date 2018/7/20
 * @desc
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/query/userinfo",method = RequestMethod.GET)
    public @ResponseBody
    BaseResDto<JSONObject> queryBankCard(){
        return testService.userInfo();
    }
}
