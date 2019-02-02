package com.example.demo.plugins.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
@Data
public abstract class OperateBase {
    private BigDecimal id;
    private UserEntity currentUser;
    private Date createTime;
    private Date modifyTime;
}
