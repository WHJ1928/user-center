package com.whj.usercenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.dto.request.UserRegisterReqDto;
import com.whj.usercenter.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wanghaijun
 * @date 2018/6/30
 * @desc
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册
     * @param registerReqDto
     * @return
     */
    @RequestMapping(value = "/user/register",method = RequestMethod.POST )
    public @ResponseBody BaseResDto<JSONObject> userRegister(@RequestBody UserRegisterReqDto registerReqDto){
        try{
            return userService.userRegister(registerReqDto);
        }catch (Exception e){
            return BaseResDto.createResult(BaseResDto.FAIL_REGISTER,BaseResDto.FAIL_REGISTER_MSG);
        }
    }
}
