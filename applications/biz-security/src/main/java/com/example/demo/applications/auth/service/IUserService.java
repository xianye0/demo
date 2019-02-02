package com.example.demo.applications.auth.service;

import com.example.demo.plugins.model.entity.UserEntity;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
public interface IUserService {

    UserEntity getByUsername(String username);

    void add(UserEntity user);

    List<UserEntity> list();
}
