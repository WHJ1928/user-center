package com.whj.usercenter.dto.response;

import java.io.Serializable;

/**
 * @author wanghaijun
 * @date 2018/7/2
 * @desc 用户登陆响应
 */
public class UserLoginResDto implements Serializable{

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
