package com.example.demo.plugins.model.entity;

import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
@Data
public class DepartmentEntity extends OperateBase {
    private String name;
    private CommonStatusEnum status;
    private DepartmentEntity parent;
    private UserEntity creator;
    private UserEntity modifier;
}
