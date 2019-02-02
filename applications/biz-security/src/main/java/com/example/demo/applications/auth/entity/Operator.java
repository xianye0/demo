package com.example.demo.applications.auth.entity;

import com.example.demo.plugins.model.entity.UserEntity;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description:
 */
@Data
public class Operator extends UserEntity implements UserDetails {

    private List<Authority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus() == CommonStatusEnum.ENABLE;
    }
}
