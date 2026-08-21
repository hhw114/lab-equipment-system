package com.hhw.config;

import com.hhw.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")                    // 拦截所有请求
                .excludePathPatterns("/user/login")        // 排除登录接口
                .excludePathPatterns("/user/register")     // 排除注册接口
                .excludePathPatterns("/swagger-ui/**")     // 排除swagger
                .excludePathPatterns("/v3/api-docs/**");   // 排除api文档
    }
}