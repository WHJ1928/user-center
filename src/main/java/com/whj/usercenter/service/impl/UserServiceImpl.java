package com.whj.usercenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dao.UserMessage;
import com.whj.usercenter.dao.UserMessageExample;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.dto.request.UserLoginReqDto;
import com.whj.usercenter.dto.request.UserRegisterReqDto;
import com.whj.usercenter.dto.response.UserLoginResDto;
import com.whj.usercenter.dto.response.UserRegisterResDto;
import com.whj.usercenter.mapper.UserMessageMapper;
import com.whj.usercenter.service.UserService;
import com.whj.usercenter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wanghaijun
 * @date 2018/6/28
 * @desc
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMessageMapper userMessageMapper;
    @Autowired
    private StringUtil util;
    @Override
    public BaseResDto<JSONObject> userRegister(UserRegisterReqDto registerReqDto) {
        String userName = registerReqDto.getUserName();
        UserMessageExample userMessageExample = new UserMessageExample();
        userMessageExample.createCriteria().andUsernameEqualTo(userName);
        List<UserMessage> userMessageList = userMessageMapper.selectByExample(userMessageExample);
        if (CollectionUtils.isEmpty(userMessageList)){
            UserMessage userMessage = regDto2Po(registerReqDto);
            userMessageMapper.insertSelective(userMessage);

            UserRegisterResDto registerResDto = new UserRegisterResDto();
            registerResDto.setUserId(userMessage.getUserid());
            BaseResDto baseResDto = BaseResDto.createResult(BaseResDto.SUC_REGISTER,BaseResDto.SUC_REGISTER_MSG);
            baseResDto.setT(JSONObject.toJSON(registerResDto));
            return baseResDto;
        }else {
            BaseResDto baseResDto = BaseResDto.createResult(BaseResDto.FAIL_REGISTER,BaseResDto.SUC_EXISTED_MSG);
            return baseResDto;
        }
    }

    /**
     * dto转po
     * @param registerReqDto
     * @return
     */
    private UserMessage regDto2Po(UserRegisterReqDto registerReqDto){
        UserMessage userMessage = new UserMessage();
        userMessage.setUserid("U"+util.getDefaultId());
        userMessage.setUsername(registerReqDto.getUserName());
        userMessage.setPassword(registerReqDto.getPassword());
        userMessage.setCreatetime(util.getCurrentTimeStr("yyyy/MM/dd HH:mm:ss"));
        return userMessage;
    }

    /**
     * 用户登录
     * @param loginReqDto
     * @return
     */
    @Override
    public BaseResDto<JSONObject> userLogin(UserLoginReqDto loginReqDto) {
        String username = loginReqDto.getUserName();
        UserMessageExample messageExample = new UserMessageExample();
        messageExample.createCriteria().andUsernameEqualTo(username);
        List<UserMessage> userMessageList = userMessageMapper.selectByExample(messageExample);
        if (CollectionUtils.isEmpty(userMessageList)){
            return BaseResDto.createResult(BaseResDto.FAIL_LOGIN_MSG,BaseResDto.SUC_NOT_USER_MSG);
        }else {
            UserMessage userMessage = userMessageList.get(0);
            if (loginReqDto.getUserName().equals(userMessage.getUsername())
                    &&loginReqDto.getPassword().equals(userMessage.getPassword())){
                UserLoginResDto loginResDto = new UserLoginResDto();
                loginResDto.setUserId(userMessage.getUserid());
                BaseResDto baseResDto = BaseResDto.createResult(BaseResDto.SUC_LOGIN,BaseResDto.SUC_LOGIN_MSG);
                baseResDto.setT(JSONObject.toJSON(loginResDto));
                return baseResDto;
            }else {
                return BaseResDto.createResult(BaseResDto.FAIL_LOGIN_MSG,BaseResDto.FAIL_ERROR_MSG);
            }
        }
    }
}
