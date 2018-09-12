package com.whj.usercenter.dto.response;

/**
 * @author wanghaijun
 * @date 2018/9/12
 * @desc
 */
public class QueryBciResDto {

    private String userid;

    private String username;

    private String blogNum;

    private String originalBlogNum;

    private int forward;

    private int comment;

    private int likes;

    private int orgForward;

    private int orgComment;

    private String bci;

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

    public String getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(String blogNum) {
        this.blogNum = blogNum;
    }

    public String getOriginalBlogNum() {
        return originalBlogNum;
    }

    public void setOriginalBlogNum(String originalBlogNum) {
        this.originalBlogNum = originalBlogNum;
    }

    public int getForward() {
        return forward;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getOrgForward() {
        return orgForward;
    }

    public void setOrgForward(int orgForward) {
        this.orgForward = orgForward;
    }

    public int getOrgComment() {
        return orgComment;
    }

    public void setOrgComment(int orgComment) {
        this.orgComment = orgComment;
    }

    public String getBci() {
        return bci;
    }

    public void setBci(String bci) {
        this.bci = bci;
    }
}
