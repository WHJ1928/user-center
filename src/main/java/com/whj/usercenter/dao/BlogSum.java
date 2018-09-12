package com.whj.usercenter.dao;

/**
 * @author wanghaijun
 * @date 2018/9/12
 * @desc
 */
public class BlogSum {

    private String likesSum;

    private String commentSum;

    private String forwardSum;

    public String getLikesSum() {
        return likesSum;
    }

    public void setLikesSum(String likesSum) {
        this.likesSum = likesSum;
    }

    public String getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(String commentSum) {
        this.commentSum = commentSum;
    }

    public String getForwardSum() {
        return forwardSum;
    }

    public void setForwardSum(String forwardSum) {
        this.forwardSum = forwardSum;
    }
}
