package com.whj.usercenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wanghaijun
 * @date 2018/8/1
 * @desc
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
