package com.hhw.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    /**
     * 验证JWT是否有效
     */
    public boolean verify(String token) {
        try {
            // 去除可能存在的 "Bearer " 前缀
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            return JWTUtil.verify(token, secret.getBytes());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从JWT中获取用户ID
     */
    public Long getUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        JWT jwt = JWTUtil.parseToken(token);
        return Long.parseLong(jwt.getPayload("userId").toString());
    }

    /**
     * 从JWT中获取用户名
     */
    public String getUsernameFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        JWT jwt = JWTUtil.parseToken(token);
        return jwt.getPayload("username").toString();
    }

    /**
     * 检查token是否过期
     */
    public boolean isExpired(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        JWT jwt = JWTUtil.parseToken(token);
        // 都转成秒级比较
        Long expSeconds = Long.parseLong(jwt.getPayload("exp").toString());
        Long nowSeconds = System.currentTimeMillis() / 1000;
        return expSeconds < nowSeconds;
    }
}