package com.whj.usercenter.util;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wanghaijun
 * @date 2018/6/29
 * @desc
 */
@Component
public class StringUtil {

    public String getCurrentTimeStr(String pattern) {
        DateTime dateTime = new DateTime();
        return dateTime.toString(pattern);
    }

    /**
     * 生成随机数
     * @return
     */
    public String getDefaultId() {
        return getCurrentTimeStr("yyyyMMdd")
                + (ThreadLocalRandom.current().nextInt(900) + 100);
    }
}
