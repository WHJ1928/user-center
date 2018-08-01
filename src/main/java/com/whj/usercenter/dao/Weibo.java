package com.whj.usercenter.dao;

public class Weibo {
    private String userid;

    private String username;

    private Long fansnum;

    private Long blognum;

    private Long originalblognum;

    private Long follownum;

    private String homelink;

    private String registrationtime;

    private String blogauthenticate;

    private String industry;

    private String bci;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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
        this.homelink = homelink == null ? null : homelink.trim();
    }

    public String getRegistrationtime() {
        return registrationtime;
    }

    public void setRegistrationtime(String registrationtime) {
        this.registrationtime = registrationtime == null ? null : registrationtime.trim();
    }

    public String getBlogauthenticate() {
        return blogauthenticate;
    }

    public void setBlogauthenticate(String blogauthenticate) {
        this.blogauthenticate = blogauthenticate == null ? null : blogauthenticate.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getBci() {
        return bci;
    }

    public void setBci(String bci) {
        this.bci = bci == null ? null : bci.trim();
    }
}