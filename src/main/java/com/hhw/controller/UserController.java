package com.hhw.controller;


import com.hhw.domain.dto.LoginFormDTO;
import com.hhw.domain.dto.RegisterFormDTO;
import com.hhw.domain.dto.UpdateUserDTO;
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


    /*
    * 用户注册接口
    *
    * */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterFormDTO registerFormDTO){
        return userService.register(registerFormDTO);
    }

    /*
    * 获取单个用户信息
    *
    * */
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    /*
    * 查询全部用户信息
    *
    * */
    @GetMapping("/list")
    public Result getUserList(){
        return userService.getUserList();
    }

    /*
    * 修改用户
    *
    * */
    @PutMapping("/{id}")
    public Result updateUser(@RequestBody UpdateUserDTO updateUserDTO){
        return userService.updateUser(updateUserDTO);
    }

    /*
    * 删除用户
    *
    * */
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
