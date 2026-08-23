package com.hhw.domain.dto;

import lombok.Data;

@Data
public class RegisterFormDTO {
    private String username;
    private String password;
    private String realName;
    private String role;
    private String phone;

}
