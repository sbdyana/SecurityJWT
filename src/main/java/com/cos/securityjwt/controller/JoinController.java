package com.cos.securityjwt.controller;

import com.cos.securityjwt.dto.JoinDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody // api 서버를 만들거기 때문에 응답을 responsbody로 해야함.
public class JoinController {

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {
        return "ok";
    }
}
