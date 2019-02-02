package com.example.demo.plugins.model.entity;

import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import com.example.demo.plugins.model.enumtype.UserTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@Data
public class UserEntity extends OperateBase {
    private String username;
    private String password;
    private DepartmentEntity department;
    private String name;
    private String phone;
    private UserTypeEnum type;
    private CommonStatusEnum status;
    private UserEntity creator;
    private UserEntity modifier;



    public static UserEntity createUser(){
        UserEntity user = new UserEntity();
        user.setPassword("123456");
        user.setUsername("ceshi");
        //        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //        for(int i=0;i<1000000;i++){
//            Map map = new HashMap<>();
//            map.put(user,user);
//        }
        return user;
    }
}
