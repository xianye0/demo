package com.example.demo.applications.auth.mapper;

import com.example.demo.plugins.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@Mapper
public interface UserMapper extends CrudMapper<UserEntity>{

    UserEntity getByUsername(String username);

}
