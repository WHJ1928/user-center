package com.whj.usercenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dao.UserMessage;
import com.whj.usercenter.dao.UserMessageExample;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.dto.request.UserRegisterReqDto;
import com.whj.usercenter.mapper.UserMessageMapper;
import com.whj.usercenter.service.UserService;
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
    @Override
    public BaseResDto<JSONObject> userRegister(UserRegisterReqDto registerReqDto) {
        String userName = registerReqDto.getUserName();
        UserMessageExample userMessageExample = new UserMessageExample();
        userMessageExample.createCriteria().andUsernameEqualTo(userName);
        List<UserMessage> userMessageList = userMessageMapper.selectByExample(userMessageExample);
        if (CollectionUtils.isEmpty(userMessageList)){

        }

        return null;
    }
}
