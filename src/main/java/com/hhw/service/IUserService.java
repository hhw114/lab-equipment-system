package com.hhw.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.hhw.domain.dto.LoginFormDTO;
import com.hhw.domain.dto.RegisterFormDTO;
import com.hhw.domain.po.User;

import com.hhw.domain.result.Result;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
public interface IUserService extends IService<User> {

    Result login(LoginFormDTO loginFormDTO);

    Result register(RegisterFormDTO registerFormDTO);
}
