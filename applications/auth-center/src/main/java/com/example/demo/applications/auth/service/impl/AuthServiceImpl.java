package com.example.demo.applications.auth.service.impl;

import com.example.demo.applications.auth.mapper.AuthMapper;
import com.example.demo.applications.auth.service.AuthService;
import com.example.demo.plugins.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    AuthMapper authMapper;

    @Override
    public UserEntity getUser(String username) {
        return authMapper.getUser(username);
    }

    @Override
    public void addUser(UserEntity user) {
        authMapper.addUser(user);
    }

    @Override
    public List<UserEntity> getList() {
        return authMapper.getList();
    }
}
