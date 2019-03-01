package com.example.demo.applications.auth.mapper;

import com.example.demo.plugins.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@Mapper
public interface AuthMapper {

    UserEntity getUser(String username);

    void addUser(UserEntity user);

    List<UserEntity> getList();
}
