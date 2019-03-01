package com.example.demo.applications.auth.service.impl;

import com.example.demo.applications.auth.entity.Menu;
import com.example.demo.applications.auth.entity.Operator;
import com.example.demo.applications.auth.mapper.SecurityMapper;
import com.example.demo.plugins.message.response.ResponsesBuilder;
import com.example.demo.plugins.utils.tree.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description: 登录相关
 */
@Slf4j
@Service
public class SecurityService {
    @Autowired
    ResponsesBuilder responsesBuilder;
    @Autowired
    UserService userService;
    @Autowired
    SecurityMapper securityMapper;

    public Operator getByUsername(String username) {
        Operator operator = securityMapper.getByUsername(username);
        if(operator == null){
            return null;
        }
        getAuthority(operator);
        return operator;
    }


    public void getAuthority(Operator operator){
        List<Menu> menus = securityMapper.getPermissions(operator.getId());
        List<String> authorities = new ArrayList<>();
        for(Menu p: menus){
            authorities.addAll(p.getAuthorityList());
        }
        operator.setAuthorities(authorities);
        operator.setMenu(TreeUtils.listToTree(menus));
    }
}
