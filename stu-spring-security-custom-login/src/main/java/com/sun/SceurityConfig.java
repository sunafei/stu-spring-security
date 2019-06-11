package com.sun;

import com.sun.auth.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
public class SceurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailsService userDetailService;//用户获取器
    @Autowired
    protected AuthenticationSuccessHandler loginSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler loginFailHandler;
    @Autowired
    protected LogoutHandler logoutHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                 .loginPage("/login.html").permitAll()
                 .successHandler(loginSuccessHandler)
                 .failureHandler(loginFailHandler)//登录界面和登录出错界面可以直接访问
                 .loginProcessingUrl("/auth/login").permitAll()
            .and()
            .authorizeRequests()
            .anyRequest()
                .authenticated()
            .and().csrf().disable();
        ;
    }
}
