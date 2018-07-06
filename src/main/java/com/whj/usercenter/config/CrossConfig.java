package com.whj.usercenter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wanghaijun
 * @date 2018/7/6
 * @desc
 */
@Configuration
public class CrossConfig extends WebMvcConfigurerAdapter {

    //跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE","OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }

}
