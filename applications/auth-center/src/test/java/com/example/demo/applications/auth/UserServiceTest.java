package com.example.demo.applications.auth;

import com.example.demo.applications.auth.service.AuthService;
import com.example.demo.plugins.model.entity.UserEntity;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * @author: rock
 * @date: 2019/1/28
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthCenterApplication.class)
public class UserServiceTest {
    @Autowired
    AuthService authService;

    private UserEntity createUser(){
        UserEntity user = new UserEntity();
        user.setUsername("test");
        user.setPassword("1234");
        return user;
    }
    public void addUser(){

        authService.addUser(createUser());
    }
    public void getUser(){
        UserEntity user = authService.getUser("test");
        Assert.assertNotNull(user);
        Assert.assertThat(user, is(createUser()));
    }

    public void getList(){
        List<UserEntity> list = authService.getList();
        Assert.assertNotNull(list);
        UserEntity user = new UserEntity();
        user.setUsername("123456");
        user.setPassword("aaaaaa");
        Assert.assertThat(list, Matchers.hasItems(createUser(),user));
    }
    @Test
    @Transactional  //事务注解会在执行完成后回滚数据
    public void trans(){
        addUser();
        getUser();
        getList();
    }
}
