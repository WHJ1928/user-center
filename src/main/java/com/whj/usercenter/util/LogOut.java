package com.whj.usercenter.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wanghaijun
 * @date 2018/7/6
 * @desc 日志描述
 */
public class LogOut {
    @Value("${spring.application.name}")
    private static String name;

    /**
     * 日志信息
     * <p>
     * 如该字段有多种输出请采用英文逗号隔开，
     * 日志信息中不要使用空格中划线空格（ - ）的组合，
     * 如确有必要可用中划线，以免与前面的配置信息混淆。
     * 如果某个字段为空值，采用null代替
     *
     * @param act     动作 增加/修改/删除/查询，如有其它动作自行增加即可
     * @param obj     本次日志数据的对象
     * @param num     日志输出的数量
     * @param result  本次日志输出，对应的结果
     * @param content 其他一些描述信息
     * @return 日志信息 如：
     */
    public static String getMsg(String act, String obj, String num, String result, String content) {
        StringBuilder sb = new StringBuilder();
        name = StringUtils.isEmpty(name) ? "user-center" : name;
        sb.append(name).append(" ")
                .append(getIPAdd()).append(" ")
                .append(act).append(" ")
                .append(obj).append(" ")
                .append(num).append(" ")
                .append(result).append(" ")
                .append(content);
        return sb.toString();
    }

    public static void error(Logger logger, String act, String obj, String num, String result, String desc, Throwable t) {
        logger.error(getMsg(act, obj, num, result, desc), t);
    }

    public static void info(Logger logger, String act, String obj, String num, String result, String desc) {
        logger.info(getMsg(act, obj, num, result, desc));
    }

    public static void debug(Logger logger, String act, String obj, String num, String result, String desc) {
        logger.debug(getMsg(act, obj, num, result, desc));
    }

    public static void warn(Logger logger, String act, String obj, String num, String result, String desc) {
        logger.warn(getMsg(act, obj, num, result, desc));
    }

    private static String getIPAdd() {
        try {
            RequestContextHolder.currentRequestAttributes();
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return getIP(request);
        } catch (Exception e) {
            return null;
        }
    }

    private static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        String unknown = "unknown";
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String localhost = "127.0.0.1";
        String remoteAddr = "0:0:0:0:0:0:0:1";
        if (localhost.equals(ip) || remoteAddr.equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ignored) {
            }
        }
        return ip;
    }
}
