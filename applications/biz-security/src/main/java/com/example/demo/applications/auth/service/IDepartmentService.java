package com.example.demo.applications.auth.service;

import com.example.demo.plugins.model.entity.OrganizationEntity;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */

public interface IDepartmentService {

    void add(OrganizationEntity department);

    List<OrganizationEntity> list();

    OrganizationEntity get(OrganizationEntity department);
}
