package com.sun.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @GetMapping("me")
    public String info(@AuthenticationPrincipal UserDetails userDetails) {
    return JSON.toJSONString(userDetails);
}
}