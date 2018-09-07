package com.whj.usercenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whj.usercenter.dao.Weibo;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.dto.request.QueryAllReqDto;
import com.whj.usercenter.dto.response.WeiboInfo;
import com.whj.usercenter.dto.response.WeiboListInfo;
import com.whj.usercenter.mapper.WeiboMapper;
import com.whj.usercenter.service.WeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghaijun
 * @date 2018/8/1
 * @desc
 */
@Service
public class WeiboServiceImpl implements WeiboService{

    @Autowired
    private WeiboMapper weiboMapper;

    @Value("${pageSize}")
    private int pageSize;
    @Override
    public BaseResDto<JSONObject> queryAllInfo(QueryAllReqDto reqDto) {
        int pageNum = 20;
        PageHelper.startPage(pageNum,pageSize);
        List<WeiboInfo> weiboList = weiboMapper.selectAll();
        PageInfo<WeiboInfo> pageInfo = new PageInfo<>(weiboList);
        BaseResDto baseResDto = BaseResDto.createResult(BaseResDto.SUCCESS,BaseResDto.SUC_MSG);
        baseResDto.setT(pageInfo);
        return baseResDto;
    }
}
