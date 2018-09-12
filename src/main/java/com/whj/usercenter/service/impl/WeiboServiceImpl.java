package com.whj.usercenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whj.usercenter.dao.Blog;
import com.whj.usercenter.dao.BlogExample;
import com.whj.usercenter.dao.BlogSum;
import com.whj.usercenter.dao.Weibo;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.dto.request.QueryAllReqDto;
import com.whj.usercenter.dto.request.QueryBciRepDto;
import com.whj.usercenter.dto.response.QueryBciResDto;
import com.whj.usercenter.dto.response.QueryBciResDtoList;
import com.whj.usercenter.mapper.BlogMapper;
import com.whj.usercenter.mapper.WeiboMapper;
import com.whj.usercenter.service.WeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    @Autowired
    private BlogMapper blogMapper;

    @Value("${pageSize}")
    private int pageSize;

    @Override
    public BaseResDto<JSONObject> queryAllInfo(QueryAllReqDto reqDto) {
        int pageNum = 20;
        PageHelper.startPage(pageNum,pageSize);
        List<Weibo> weiboList = weiboMapper.selectAll();
        PageInfo<Weibo> pageInfo = new PageInfo<>(weiboList);
        BaseResDto baseResDto = BaseResDto.createResult(BaseResDto.SUCCESS,BaseResDto.SUC_MSG);
        baseResDto.setT(pageInfo);
        return baseResDto;
    }

    @Override
    public BaseResDto<JSONObject> queryBci(QueryBciRepDto repDto) {
        String time = repDto.getCreatedTime();
        QueryBciResDtoList bciResDtoList = new QueryBciResDtoList();
        List<QueryBciResDto> queryBciResDtos = new ArrayList<>();
        List<String> strings = weiboMapper.selectUserId();
        for (String userId:strings){
            Weibo weibo = weiboMapper.selectByPrimaryKey(userId);
            double x1 = 0.3*Math.log(weibo.getBlognum().doubleValue()+1);
            double x2 = 0.7*Math.log(weibo.getOriginalblognum().doubleValue()+1);
            double w1 = x1+x2;
            double w2;
            //计算微博数据
            BlogExample example = new BlogExample();
            example.createCriteria().andUserIdEqualTo(userId)
                    .andCreatedTimeEqualTo(time);
            List<Blog> blogs = blogMapper.selectByExample(example);
//            BlogSum blog = blogMapper.selectBlogSum(example);
//            //计算原创微博的数据
//            BlogExample blogExample = new BlogExample();
//            blogExample.createCriteria().andUserIdEqualTo(userId)
//                    .andCreatedTimeEqualTo(time).andOriginalFlagEqualTo("1");
//            BlogSum blogSum = blogMapper.selectBlogSum(blogExample);
            int forward =0;
            int comment =0;
            int likes =0;
            int orgForward =0;
            int orgComment =0;
            if (!ObjectUtils.isEmpty(blogs)){
                for (Blog blog:blogs){
                    forward += Integer.parseInt(blog.getForwardNum());
                    comment += Integer.parseInt(blog.getCommentNum());
                    likes += Integer.parseInt(blog.getLikesNum());
                    if ("1".equals(blog.getOriginalFlag())){
                        orgForward += Integer.parseInt(blog.getForwardNum());
                        orgComment += Integer.parseInt(blog.getCommentNum());
                    }
                }
//                double x3 = 0.2*Math.log(Double.parseDouble(blog.getForwardSum())+1);
//                double x4 = 0.2*Math.log(Double.parseDouble(blog.getCommentSum())+1);
//                //原创
//                double x5 = 0.25*Math.log(Double.parseDouble(blogSum.getForwardSum())+1);
//                double x6 = 0.25*Math.log(Double.parseDouble(blogSum.getCommentSum())+1);
//                double x7 = 0.1*Math.log(Double.parseDouble(blog.getLikesSum())+1);
                double x3 = 0.2*Math.log(forward+1);
                double x4 = 0.2*Math.log(comment+1);
                //原创
                double x5 = 0.25*Math.log(orgForward+1);
                double x6 = 0.25*Math.log(orgComment+1);
                double x7 = 0.1*Math.log(likes+1);
                w2 = x3+x4+x5+x6+x7;
            }else {
                w2 = 0;
            }
            double bci = 160*(0.2*w1 + 0.8*w2);
            QueryBciResDto queryBciResDto = new QueryBciResDto();
            queryBciResDto.setBlogNum(weibo.getBlognum().toString());
            queryBciResDto.setOriginalBlogNum(weibo.getOriginalblognum().toString());
            queryBciResDto.setComment(comment);
            queryBciResDto.setForward(forward);
            queryBciResDto.setLikes(likes);
            queryBciResDto.setOrgComment(orgComment);
            queryBciResDto.setOrgForward(orgForward);
            queryBciResDto.setBci(String.format("%.2f",bci));
            queryBciResDto.setUserid(userId);
            queryBciResDto.setUsername(weibo.getUsername());
            queryBciResDtos.add(queryBciResDto);
        }
        bciResDtoList.setList(queryBciResDtos);
        BaseResDto baseResDto = BaseResDto.createResult(BaseResDto.SUCCESS,BaseResDto.SUC_MSG);
        baseResDto.setT(bciResDtoList);
        return baseResDto;
    }
}
