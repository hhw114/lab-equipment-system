package com.hhw.service.impl;

import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.hhw.domain.dto.LoginFormDTO;
import com.hhw.domain.po.User;
import com.hhw.domain.result.Result;
import com.hhw.exception.BizException;
import com.hhw.mapper.UserMapper;
import com.hhw.service.IUserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Value("${jwt.secret}")
    private String secret; // JWT密钥，从配置文件读取

    @Value("${jwt.expire}")
    private Long expire; // 过期时间（毫秒），从配置文件读取

    /*
    * 登录接口
    *
    * */
    @Override
    public Result login(LoginFormDTO loginFormDTO) {
        //1.获取基本信息
        String username = loginFormDTO.getUsername();
        String password = loginFormDTO.getPassword();
        //2.判断数据是否为空
        if(username==null || password==null){
            throw new BizException("用户名或密码为空!");
        }
        //3.根据用户名查询对应的密码
        User user = lambdaQuery().eq(User::getUsername, username)
                .one();
        if (user == null) {
            //用户不存在
            throw new BizException("登录失败，用户不存在");
        }
        //4.获取数据库中用户密码
        String userPassword = user.getPassword();
        if (!userPassword.equals(password)) {
            throw new BizException("密码错误，请重试");
        }
        //5.登录成功,生成jwt
        String token = generateToken(user);

        // 6.返回token给前端
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user);

        return Result.ok(data);
    }


    /**
     * 生成JWT令牌
     * @param user 用户信息
     * @return JWT字符串
     */
    private String generateToken(User user) {
        // 设置过期时间
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire);

        // 生成JWT
        return JWT.create()
                .setPayload("userId", user.getId())      // 设置载荷 - key-value形式
                .setPayload("username", user.getUsername())
                .setIssuedAt(now)                        // 签发时间
                .setExpiresAt(expireDate)               // 过期时间
                .setKey(secret.getBytes())              // 设置签名密钥
                .sign();               // 签名并返回字符串
    }
}
