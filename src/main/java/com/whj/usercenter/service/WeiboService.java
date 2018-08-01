package com.whj.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.dto.request.QueryAllReqDto;

/**
 * @author wanghaijun
 * @date 2018/8/1
 * @desc
 */
public interface WeiboService {

    /**
     * 查询全部信息
     * @return
     */
    BaseResDto<JSONObject> queryAllInfo(QueryAllReqDto reqDto);
}
