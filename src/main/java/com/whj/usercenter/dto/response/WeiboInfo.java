package com.whj.usercenter.dto.response;


/**
 * @author wanghaijun
 * @date 2018/8/1
 * @desc
 */
public class WeiboInfo {

    private String userid;

    private String username;

    private Long fansnum;

    private Long blognum;

    private Long originalblognum;

    private Long follownum;

    private String homelink;

    private String registrationtime;

    private String blogauthenticate;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFansnum() {
        return fansnum;
    }

    public void setFansnum(Long fansnum) {
        this.fansnum = fansnum;
    }

    public Long getBlognum() {
        return blognum;
    }

    public void setBlognum(Long blognum) {
        this.blognum = blognum;
    }

    public Long getOriginalblognum() {
        return originalblognum;
    }

    public void setOriginalblognum(Long originalblognum) {
        this.originalblognum = originalblognum;
    }

    public Long getFollownum() {
        return follownum;
    }

    public void setFollownum(Long follownum) {
        this.follownum = follownum;
    }

    public String getHomelink() {
        return homelink;
    }

    public void setHomelink(String homelink) {
        this.homelink = homelink;
    }

    public String getRegistrationtime() {
        return registrationtime;
    }

    public void setRegistrationtime(String registrationtime) {
        this.registrationtime = registrationtime;
    }

    public String getBlogauthenticate() {
        return blogauthenticate;
    }

    public void setBlogauthenticate(String blogauthenticate) {
        this.blogauthenticate = blogauthenticate;
    }
}
