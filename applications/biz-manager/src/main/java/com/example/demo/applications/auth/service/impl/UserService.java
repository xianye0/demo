package com.example.demo.applications.auth.service.impl;

import com.example.demo.applications.auth.mapper.UserMapper;
import com.example.demo.plugins.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description: 用户
 */
@Service
public class UserService extends CrudService<UserMapper,UserEntity> {

    public UserEntity getByUsername(String username) {
        return dao.getByUsername(username);
    }

    public void changeStatus(BigDecimal id,String status){
        dao.changeStatus(id,status);
    }
}
