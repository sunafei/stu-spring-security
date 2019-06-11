package com.sun.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUserDetails implements UserDetails {
    // 用户ID
    private String userId;
    // 姓名
    private String name;
    // 账号
    private String username;
    // 密码
    private String password;
    // 是否可用
    private boolean enabled;
    // 用户权限集合(多个角色)
    private Collection<GrantedAuthority> authorities;
    // 账号是否为管理员
    private boolean accountIsAdmin;
    // 账号是否过期
    private boolean accountNonExpired;
    // 账号是否被锁
    private boolean accountNonLocked;
    // 密码是否过期
    private boolean credentialsNonExpired;


}
