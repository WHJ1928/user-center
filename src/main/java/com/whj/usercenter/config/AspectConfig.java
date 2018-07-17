package com.whj.usercenter.config;

import com.alibaba.fastjson.JSON;
import com.whj.usercenter.dto.BaseResDto;
import com.whj.usercenter.util.LogOut;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanghaijun
 * @date 2018/7/6
 * @desc
 */
@Aspect
@Order(-1)
@Component
public class AspectConfig {
    private static final Logger logger = LoggerFactory.getLogger(AspectConfig.class);

    @Autowired
    private Registration registration;

    /**
     * 通用controller切点
     */
    @Pointcut("execution(* com.whj.usercenter.controller..*.*(..))&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controller(){}

    /**
     * 记录输出日志
     * @param joinPoint
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Before(value = "controller()")
    public void inputLog(JoinPoint joinPoint) throws IllegalArgumentException,IllegalAccessException {
        String classname = joinPoint.getTarget().getClass().getName();
        String methodname = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isNotEmpty(args)){
            LogOut.info(logger, "调用", "用户中心", "请求参数", "", JSON.toJSONString(args));
            LogOut.info(logger, "", "", "接口地址", "", getServiceKey());
        }
    }

    /**
     * 记录输出日志
     * @param joinPoint
     * @param retVal
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @AfterReturning(value = "controller()",returning = "retVal")
    public void outputLog(JoinPoint joinPoint,Object retVal) throws IllegalArgumentException,IllegalAccessException {
        String classname = joinPoint.getTarget().getClass().getName();
        String methodname = joinPoint.getSignature().getName();
        LogOut.info(logger, "调用", "用户中心", "响应参数", "", JSON.toJSONString(retVal));
    }

    /**
     * 异常通知 用于拦截异常,记录日志
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "controller()", throwing = "ex")
    public BaseResDto afterThrowing(JoinPoint joinPoint, Throwable ex) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        LogOut.error(logger, "调用", "用户中心", "", "", "失败", ex);
        return BaseResDto.createResult(BaseResDto.FAIL, BaseResDto.FAIL_MSG);
    }

    /**
     * 获得ServiceKey
     */
    public String getServiceKey(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return registration.getServiceId()+request.getServletPath();
    }
}
