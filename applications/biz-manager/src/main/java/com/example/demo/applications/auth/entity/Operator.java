package com.example.demo.applications.auth.entity;

import com.example.demo.plugins.model.entity.UserEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description:
 */
@Data
public class Operator extends UserEntity  {

    private Date modifyPassTime;
    private List<String> authorities;
    private List<Authority> menu;


}
