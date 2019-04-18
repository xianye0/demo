package com.example.demo.applications.service.impl;

import com.example.demo.applications.entity.UserEntity;
import com.example.demo.applications.mapper.UserMapper;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description: 用户
 */
@Service
public class UserService extends CrudService<UserMapper,UserEntity> {
    @Autowired
    RoleService roleService;

    @Transactional
    @Override
    public void add(UserEntity user){
        super.add(user);
        if(!CollectionUtils.isEmpty(user.getRoles())){
            delRoles(user);
            addRoles(user);
        }
    }
    @Transactional
    @Override
    public void mod(UserEntity user) {
        super.mod(user);
        if(!CollectionUtils.isEmpty(user.getRoles())){
            delRoles(user);
            addRoles(user);
        }
    }

    @Override
    public UserEntity get(UserEntity userEntity) {
        UserEntity user = super.get(userEntity);
        List<BigDecimal> roles = dao.getRoles(userEntity);
        user.setRoles(roles);
        return user;
    }

    public UserEntity getByUsername(String username) {
        return dao.getByUsername(username);
    }

    public void changeStatus(BigDecimal id,CommonStatusEnum status){
        dao.changeStatus(id,status);
    }

    public void addRoles(UserEntity user){
        dao.addRoles(user);
    }

    public void delRoles(UserEntity user){
        dao.delRoles(user);
    }
}
