package com.example.demo.applications.auth.service.impl;

import com.example.demo.applications.auth.mapper.DepartmentMapper;
import com.example.demo.applications.auth.service.IDepartmentService;
import com.example.demo.plugins.model.entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
@Service
public class DepartmentServiceImpl extends CrudService<DepartmentMapper,DepartmentEntity> implements IDepartmentService {


}
