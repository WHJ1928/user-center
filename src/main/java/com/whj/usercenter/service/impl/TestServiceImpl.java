package com.whj.usercenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dao.UserDao;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.dto.response.UserResDto;
import com.whj.usercenter.mapper.UserMapper;
import com.whj.usercenter.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghaijun
 * @date 2018/7/20
 * @desc
 */
@Service
public class TestServiceImpl implements TestService{
    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResDto<JSONObject> userInfo() {
        List<UserDao> userDaoList = userMapper.selectAll();
        ArrayList<UserResDto> userResList = new ArrayList<>();
        for (UserDao userDao:userDaoList){
            UserResDto userResDto = new UserResDto();
            userResDto.setName(userDao.getName());
            userResDto.setTel(userDao.getTel());
            userResList.add(userResDto);
        }
        BaseResDto baseResDto = BaseResDto.createResult(BaseResDto.SUCCESS,BaseResDto.SUC_MSG);
        baseResDto.setT(JSONObject.toJSON(userResList));
        return baseResDto;
    }
}
