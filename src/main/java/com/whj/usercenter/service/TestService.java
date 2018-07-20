package com.whj.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dto.BaseResDto;

/**
 * @author wanghaijun
 * @date 2018/7/20
 * @desc
 */
public interface TestService {

    /**
     * 用户
     * @return
     */
    BaseResDto<JSONObject> userInfo( );
}
