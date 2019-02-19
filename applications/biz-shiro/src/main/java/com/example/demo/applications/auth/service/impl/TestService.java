package com.example.demo.applications.auth.service.impl;

import com.example.demo.plugins.model.entity.UserEntity;
import com.example.demo.plugins.utils.lock.CacheLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: rock
 * @date: 2019/2/18
 * @Description:
 */
@Service
public class TestService {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CacheLock distributedLock;

//    @Transactional
    public void updUser(UserEntity user){

        if(distributedLock.lock(user.getUsername())){
            UserEntity u = userService.getByUsername(user.getUsername());
            try{
                TimeUnit.SECONDS.sleep(5);
                userService.mod(user);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            } finally {
                distributedLock.unLock(user.getUsername());
            }
        }
    }
}
