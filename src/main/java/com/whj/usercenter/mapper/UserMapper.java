package com.whj.usercenter.mapper;

import com.whj.usercenter.dao.UserDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wanghaijun
 * @date 2018/7/20
 * @desc
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有信息
     * @return
     */
    @Select("SELECT * FROM message")
    List<UserDao> selectAll();
}
