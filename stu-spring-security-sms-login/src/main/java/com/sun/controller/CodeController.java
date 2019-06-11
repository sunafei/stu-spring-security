package com.sun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
@RequestMapping(value = "/code")
public class CodeController {

    @GetMapping("/sms")
    public String getSmsCode(HttpServletRequest request, HttpServletResponse response) {
        // 创建短信验证码
        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        int random = new Random().ints(100000, 1000000).limit(1).findFirst().getAsInt();
        webRequest.setAttribute("smscode", random, RequestAttributes.SCOPE_SESSION);
        return "短信验证码为" + random;
    }
}
