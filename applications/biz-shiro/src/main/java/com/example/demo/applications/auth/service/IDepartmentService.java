package com.example.demo.applications.auth.service;

import com.example.demo.plugins.model.entity.DepartmentEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */

public interface IDepartmentService {

    void add(DepartmentEntity department);

    List<DepartmentEntity> list();

    DepartmentEntity get(DepartmentEntity department);
}
