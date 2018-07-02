package com.whj.usercenter.dto.request;

import java.io.Serializable;

/**
 * @author wanghaijun
 * @date 2018/7/2
 * @desc 用户登陆请求
 */
public class UserLoginReqDto implements Serializable{

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
