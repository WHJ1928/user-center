package com.whj.usercenter.dto.response;

import java.io.Serializable;

/**
 * @author wanghaijun
 * @date 2018/6/28
 * @desc
 */
public class UserRegisterResDto implements Serializable{

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
