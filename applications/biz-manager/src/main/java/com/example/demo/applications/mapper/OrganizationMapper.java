package com.example.demo.applications.mapper;

import com.example.demo.plugins.model.entity.OrganizationEntity;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@Mapper
public interface OrganizationMapper extends CrudMapper<OrganizationEntity>{

    void changeStatus(@Param("id") BigDecimal id, @Param("status") CommonStatusEnum status);
}
