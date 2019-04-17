package com.example.demo.applications.mapper;

import com.example.demo.applications.entity.Authority;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: wulei
 * @date: 2019/4/16
 * @Description:
 */
@Mapper
public interface AuthorityMapper {

    List<String> getAuthorityCodeList(BigDecimal roleId);
    List<Authority> getAllAuthorityList();
}
