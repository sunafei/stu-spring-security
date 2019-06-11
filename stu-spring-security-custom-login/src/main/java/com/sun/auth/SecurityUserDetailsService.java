package com.sun.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("001".equals(username)) {
            Collection<GrantedAuthority> authorties = new HashSet<>();
            authorties.add(new SimpleGrantedAuthority("1"));
            String password = new BCryptPasswordEncoder().encode("123456");
            return new SecurityUserDetails("123456", "张三", "001", password, true, authorties, true, true, true, true);
        }
        throw new BadCredentialsException("账户名、密码错误!");
    }
}
