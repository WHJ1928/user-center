package com.whj.usercenter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * Created by 2017/8/8.
 * @author fuqiang
 */
final public class Md5Util {
    private static Logger logger = LoggerFactory.getLogger(Md5Util.class);
    /**
     * 通用MD5处理工具类
     * @param text
     * @return
     */
    public static String bit32(String text) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes("UTF-8"));
            byte[] b = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            logger.error("md5加密失败");
            return null;
        }
        return sb.toString().toUpperCase();
    }

    public static String bit16(String sourceString){
        return bit32(sourceString).substring(8, 24);
    }
}
