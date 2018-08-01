package com.whj.usercenter.mapper;

import com.whj.usercenter.dao.Weibo;
import com.whj.usercenter.dao.WeiboExample;
import com.whj.usercenter.dto.response.WeiboInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeiboMapper {
    int countByExample(WeiboExample example);

    int deleteByExample(WeiboExample example);

    int deleteByPrimaryKey(String userid);

    int insert(Weibo record);

    int insertSelective(Weibo record);

    List<Weibo> selectByExample(WeiboExample example);

    Weibo selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") Weibo record, @Param("example") WeiboExample example);

    int updateByExample(@Param("record") Weibo record, @Param("example") WeiboExample example);

    int updateByPrimaryKeySelective(Weibo record);

    int updateByPrimaryKey(Weibo record);

    @Select("SELECT userId FROM weibo WHERE blogAuthenticate IS NULL")
    List<String> selectUserId();
    @Select("SELECT userId,username,fansNum,blogNum,originalBlogNum,followNum,homeLink,registrationTime,blogAuthenticate FROM weibo")
    List<WeiboInfo> selectAll();
}