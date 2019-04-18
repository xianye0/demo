package com.example.demo.applications.entity;

import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import com.example.demo.plugins.model.enumtype.UserTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@Data
public class UserEntity extends OperateBase {
    private String username;
    private String password;
    private OrganizationEntity organization;
    private String name;
    private String phone;
    private String email;
    private UserTypeEnum type;
    private CommonStatusEnum status;
    private UserEntity creator;
    private UserEntity modifier;
    private List<String> roles;
}
