package com.example.demo.applications.auth.service.impl;

import com.example.demo.applications.auth.mapper.DepartmentMapper;
import com.example.demo.applications.auth.service.IDepartmentService;
import com.example.demo.plugins.model.entity.OrganizationEntity;
import org.springframework.stereotype.Service;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
@Service
public class DepartmentServiceImpl extends CrudService<DepartmentMapper,OrganizationEntity> implements IDepartmentService {


}
