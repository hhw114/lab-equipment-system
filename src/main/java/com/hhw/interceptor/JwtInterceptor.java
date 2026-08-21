package com.hhw.interceptor;

import com.hhw.exception.BizException;
import com.hhw.utils.JwtUtils;
import com.hhw.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的token
        String token = request.getHeader("Authorization");

        // 验证token
        if (token == null || !jwtUtils.verify(token)) {
            throw new BizException("未登录或登录已过期，请重新登录");
        }

        // 检查是否过期
        if (jwtUtils.isExpired(token)) {
            throw new BizException("登录已过期，请重新登录");
        }

        // 将用户信息存入请求域中，方便后续使用
        Long userId = jwtUtils.getUserIdFromToken(token);
        String username = jwtUtils.getUsernameFromToken(token);
        // 存入ThreadLocal
        UserContext.setUserId(userId);
        UserContext.setUsername(username);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清除ThreadLocal
        UserContext.clear();
    }
}