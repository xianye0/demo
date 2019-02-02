package com.example.demo.applications.auth.service.impl;

import com.example.demo.applications.auth.entity.Authority;
import com.example.demo.applications.auth.entity.Operator;
import com.example.demo.applications.auth.entity.Permission;
import com.example.demo.applications.auth.mapper.SecurityMapper;
import com.example.demo.applications.auth.service.ISecurityService;
import com.example.demo.applications.auth.service.IUserService;
import com.example.demo.plugins.message.response.ResponsesBuilder;
import com.example.demo.plugins.model.response.Responses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description:
 */
@Slf4j
@Service
public class SecurityServiceImpl implements ISecurityService,UserDetailsService {
    @Autowired
    ResponsesBuilder responsesBuilder;
    @Autowired
    IUserService userService;
    @Autowired
    SecurityMapper securityMapper;

    @Override
    public Operator loadUserByUsername(String username) throws UsernameNotFoundException {
        Operator operator = securityMapper.getByUsername(username);
        if(operator == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        getAuthority(operator);
        return operator;
    }

    @Override
    public Responses login(String username, String password) {
        Operator operator = loadUserByUsername(username);
        return null;
    }

    public void getAuthority(Operator operator){
        List<Permission> permissions = securityMapper.getPermissions(operator.getId());
        List<Authority> authorities = new ArrayList<>();
        for(Permission p:permissions){
            authorities.addAll(p.getAutorityList());
        }
        operator.setAuthorities(authorities);
    }
}
