package com.cos.securityjwt.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {
    // 회원가입할 때 필요
    private String username;
    private String password;
}
