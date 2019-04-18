package com.example.demo.applications.mapper;

import com.example.demo.applications.entity.UserEntity;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@Mapper
public interface UserMapper extends CrudMapper<UserEntity> {

    UserEntity getByUsername(String username);

    void changeStatus(@Param("id") BigDecimal id, @Param("status") CommonStatusEnum status);

    void addRoles(UserEntity user);
    void delRoles(UserEntity user);

    List<BigDecimal> getRoles(UserEntity user);
}
