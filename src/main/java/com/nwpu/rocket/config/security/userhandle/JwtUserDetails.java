package com.nwpu.rocket.config.security.userhandle;


import com.nwpu.rocket.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 *
 * @author zy
 */
@Getter
public class JwtUserDetails implements UserDetails {
    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(User user) {
        this.user = user;
        this.authorities = mapToGrantedAuthorities(Collections.singletonList(user.getRoles()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
    }

    /**
     * 账号未过期？
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号未锁定？
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.user.getStatus() == 1;
    }

    /**
     * 密码未过期？
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号已启用？
     */
    @Override
    public boolean isEnabled() {
        return this.user.getEnabled() == 1;
    }
}

