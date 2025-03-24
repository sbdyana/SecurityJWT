package com.cos.securityjwt.controller;

import com.cos.securityjwt.dto.JoinDTO;
import com.cos.securityjwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody // api 서버를 만들거기 때문에 응답을 responsbody로 해야함.
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);
        return "ok";
    }
}
