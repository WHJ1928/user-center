package com.whj.usercenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.service.TestService;
import com.whj.usercenter.util.MakeTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;

/**
 * @author wanghaijun
 * @date 2018/7/20
 * @desc
 */
@RestController
public class TestController {
    private static final String MODEL_FILE_NAME ="名单导出.xlsx";

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/query/userinfo",method = RequestMethod.GET)
    public @ResponseBody
    BaseResDto<JSONObject> queryBankCard(){
        return testService.userInfo();
    }

    @RequestMapping(value = "/media", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadFile()
            throws Exception {
        ByteArrayInputStream inputStream = MakeTemp.getInputStream();
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        String fileNAME = URLEncoder.encode( MODEL_FILE_NAME, "UTF-8");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileNAME ));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(inputStreamResource);
    }
}
