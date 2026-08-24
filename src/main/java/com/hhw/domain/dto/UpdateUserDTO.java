package com.hhw.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateUserDTO {

    @NotNull(message = "用户id不能为空")
    private Long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 角色：ADMIN管理员，USER普通用户
     */
    private String role;

    /**
     * 联系电话
     */
    private String phone;

}
