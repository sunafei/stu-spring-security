package com.sun.validate;

import com.sun.auth.handler.LoginFailHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    @Autowired
    private LoginFailHandler loginFailHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 校验验证码
        if ("/auth/mobile".equals(httpServletRequest.getRequestURI())) {
            ServletWebRequest servletWebRequest = new ServletWebRequest(httpServletRequest, httpServletResponse);
            Object smscode = servletWebRequest.getAttribute("smscode", RequestAttributes.SCOPE_SESSION);
            String smsCodeParam = httpServletRequest.getParameter("smsCode");
            if (smscode == null || !StringUtils.equals(smsCodeParam, String.valueOf(smscode))) {
                loginFailHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, new BadCredentialsException("短信验证码错误"));
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}