package com.example.demo.applications.auth;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: rock
 * @date: 2019/1/29
 * @Description: mock测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    public void list() throws Exception{
        ResultActions a = mvc.perform(MockMvcRequestBuilders.get("/user/list").sessionAttr("user",TestDataUtils.createUser()));
                /**获取 URL. */
                a.andExpect(MockMvcResultMatchers.status().isOk())
                /** 返回 数据.*/
                .andExpect(MockMvcResultMatchers.content().json("[{\"username\":\"111\",\"password\":\"123456\"}]"));
    }

    public void addUser() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/user/add").contentType(MediaType.APPLICATION_JSON).
                content(JSON.toJSONString(TestDataUtils.createUser())).sessionAttr("user",TestDataUtils.createUser())).
                andExpect(MockMvcResultMatchers.status().isOk())
                /** 返回 数据.*/
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

//    @Transactional
    @Test
    public void test() throws Exception{
        addUser();
        list();
    }

}
