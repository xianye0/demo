package com.example.demo.applications.auth.service.impl;

import com.example.demo.applications.auth.entity.Operator;
import com.example.demo.applications.auth.entity.Permission;
import com.example.demo.applications.auth.mapper.SecurityMapper;
import com.example.demo.plugins.message.response.ResponsesBuilder;
import com.example.demo.plugins.model.response.Responses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SecurityServiceImpl {
    @Autowired
    ResponsesBuilder responsesBuilder;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SecurityMapper securityMapper;

    public Operator getByUsername(String username) {
        Operator operator = securityMapper.getByUsername(username);
        getAuthority(operator);
        return operator;
    }

    public Responses login(String username, String password) {
        Operator operator = getByUsername(username);
        return null;
    }

    public void getAuthority(Operator operator){
        List<Permission> permissions = securityMapper.getPermissions(operator.getId());
        List<String> authorities = new ArrayList<>();
        for(Permission p:permissions){
            authorities.addAll(p.getAutorityList());
        }
        operator.setAuthorities(authorities);
    }
}
