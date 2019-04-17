package com.example.demo.plugins.model.entity;

import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import lombok.Data;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
@Data
public class OrganizationEntity extends OperateBase {
    private String name;
    private CommonStatusEnum status;
    private OrganizationEntity parent;
    private UserEntity creator;
    private UserEntity modifier;
}
