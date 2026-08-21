package com.hhw.controller;


import com.hhw.domain.dto.LoginFormDTO;
import com.hhw.domain.result.Result;
import com.hhw.service.IUserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    /*
    * 用户登录接口
    *
    * */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginFormDTO) {
        return userService.login(loginFormDTO);
    }
}
