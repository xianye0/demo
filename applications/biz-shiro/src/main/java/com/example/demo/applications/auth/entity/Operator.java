package com.example.demo.applications.auth.entity;

import com.example.demo.plugins.model.entity.UserEntity;
import lombok.Data;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;

import java.util.Collection;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description:
 */
@Data
public class Operator extends UserEntity implements AuthorizationInfo {

    private List<String> authorities;

    @Override
    public Collection<String> getRoles() {
        return null;
    }

    @Override
    public Collection<String> getStringPermissions() {
        return authorities;
    }

    @Override
    public Collection<Permission> getObjectPermissions() {
        return null;
    }
}
