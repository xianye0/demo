package com.example.demo.applications.auth.mapper;

import com.example.demo.applications.auth.entity.Authority;
import com.example.demo.applications.auth.entity.AuthorityUrl;
import com.example.demo.applications.auth.entity.Operator;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description:
 */
@Mapper
public interface SecurityMapper {
    Operator getByUsername(String username);

    List<Authority> queryAuthorityList(BigDecimal operId);

    void updateLoginDate(String username);
    @MapKey("path")
    Map<String,AuthorityUrl> queryUrlMap();
}
