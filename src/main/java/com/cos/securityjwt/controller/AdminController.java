package com.cos.securityjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class AdminController {

    @GetMapping("/admin") // admin 경로로 들어오면
    public String admin() {
        return "Admin Controller";
    }
}
