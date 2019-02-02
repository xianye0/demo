package com.example.demo.applications.auth.mapper;

import com.example.demo.applications.auth.entity.Operator;
import com.example.demo.applications.auth.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description:
 */
@Mapper
public interface SecurityMapper {
    Operator getByUsername(String username);

    List<Permission> getPermissions(BigDecimal operId);
}
