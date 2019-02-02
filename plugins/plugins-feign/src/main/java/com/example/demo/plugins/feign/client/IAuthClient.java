package com.example.demo.plugins.feign.client;

import com.example.demo.plugins.model.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: rock
 * @date: 2019/1/26
 * @Description:
 */
@FeignClient(name = "auth-center")  //name与服务名对应
public interface IAuthClient {
    @RequestMapping(path = "/security/getUser", method = RequestMethod.GET)
    UserEntity getUser();
}
