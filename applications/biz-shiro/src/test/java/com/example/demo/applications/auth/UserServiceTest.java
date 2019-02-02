package com.example.demo.applications.auth;

import com.example.demo.applications.auth.service.IUserService;
import com.example.demo.plugins.model.entity.UserEntity;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * @author: rock
 * @date: 2019/1/28
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BizShiroApplication.class)
public class UserServiceTest {
    @Autowired
    IUserService userService;


    public void addUser(){

        userService.add(TestDataUtils.createUser());
    }
    public void getUser(){
        UserEntity user = userService.getByUsername("test");
        Assert.assertNotNull(user);
        Assert.assertThat(user, is(TestDataUtils.createUser()));
    }

    public void getList(){
        List<UserEntity> list = userService.list();
        Assert.assertNotNull(list);
        UserEntity user = new UserEntity();
        user.setUsername("123456");
        user.setPassword("aaaaaa");
        Assert.assertThat(list, Matchers.hasItems(TestDataUtils.createUser(),user));
    }
    @Test
//    @Transactional  //事务注解会在执行完成后回滚数据
    public void trans(){
        addUser();
//        getUser();
        getList();
    }
}
