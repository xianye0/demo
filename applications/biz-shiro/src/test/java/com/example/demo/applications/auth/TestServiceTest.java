package com.example.demo.applications.auth;

import com.example.demo.applications.auth.service.impl.TestService;
import com.example.demo.plugins.model.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author: rock
 * @date: 2019/2/18
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BizShiroApplication.class)
public class TestServiceTest {
    @Autowired
    TestService testService;

    @Test
    public void test(){
        UserEntity user = new UserEntity();
        user.setUsername("test");
        user.setPassword("1231");
        for(int i=0;i<2;i++){
            new Thread(()->{testService.updUser(user);}).start();
        }
        try{
            TimeUnit.SECONDS.sleep(30);
        }catch (Exception e){

        }

    }
}
