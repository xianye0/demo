package com.example.demo.applications.auth.service;

import com.example.demo.plugins.model.auth.UserEntity;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
public interface AuthService {

    UserEntity getUser(String username);
}
