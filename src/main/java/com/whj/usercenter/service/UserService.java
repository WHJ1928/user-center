package com.whj.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.dto.request.UserLoginReqDto;
import com.whj.usercenter.dto.request.UserRegisterReqDto;

/**
 * @author wanghaijun
 * @date 2018/6/28
 * @desc
 */
public interface UserService {

    /**
     * 用户注册
     * @param registerReqDto
     * @return
     */
    BaseResDto<JSONObject> userRegister(UserRegisterReqDto registerReqDto);

    /**
     * 用户登录
     * @param loginReqDto
     * @return
     */
    BaseResDto<JSONObject> userLogin(UserLoginReqDto loginReqDto);
}
