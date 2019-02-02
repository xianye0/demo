package com.example.demo.applications.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author: rock
 * @date: 2019/2/1
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Authority implements GrantedAuthority {

    private String auth;
    @Override
    public String getAuthority() {
        return auth;
    }
}
