package com.whj.usercenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.dto.request.QueryAllReqDto;
import com.whj.usercenter.dto.request.QueryBciRepDto;
import com.whj.usercenter.service.WeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wanghaijun
 * @date 2018/8/1
 * @desc
 */
@RestController
public class WeiboController {

    @Autowired
    private WeiboService weiboService;

    /**
     * 查询所有信息
     * @return
     */
    @RequestMapping(value = "/query/info",method = RequestMethod.POST)
    public @ResponseBody
    BaseResDto<JSONObject> queryAll(@RequestBody QueryAllReqDto reqDto){
        try{
            return weiboService.queryAllInfo(reqDto);
        }catch (Exception e){
            return BaseResDto.createResult(BaseResDto.FAIL,BaseResDto.FAIL_MSG);
        }
    }

    /**
     * 查询所有信息
     * @return
     */
    @RequestMapping(value = "/query/bci",method = RequestMethod.POST)
    public @ResponseBody
    BaseResDto<JSONObject> queryBci(@RequestBody QueryBciRepDto repDto){
//        try{
            return weiboService.queryBci(repDto);
//        }catch (Exception e){
//            return BaseResDto.createResult(BaseResDto.FAIL,BaseResDto.FAIL_MSG);
//        }
    }
}
