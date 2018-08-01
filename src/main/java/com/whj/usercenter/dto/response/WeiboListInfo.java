package com.whj.usercenter.dto.response;

import java.util.List;

/**
 * @author wanghaijun
 * @date 2018/8/1
 * @desc
 */
public class WeiboListInfo {

    private List<WeiboInfo>  data;

    public List<WeiboInfo> getData() {
        return data;
    }

    public void setData(List<WeiboInfo> data) {
        this.data = data;
    }
}
