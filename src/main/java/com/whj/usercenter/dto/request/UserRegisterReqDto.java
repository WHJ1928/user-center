package com.whj.usercenter.dto.request;

import java.io.Serializable;

/**
 * @author wanghaijun
 * @date 2018/6/28
 * @desc 用户注册dto
 */
public class UserRegisterReqDto implements Serializable{

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
