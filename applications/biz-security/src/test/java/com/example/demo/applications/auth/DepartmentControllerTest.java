package com.example.demo.applications.auth;

import com.example.demo.plugins.model.entity.OrganizationEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mvc;

    public void list() throws Exception{
        ResultActions a = mvc.perform(MockMvcRequestBuilders.get("/department/list").sessionAttr("user", TestDataUtils.createUser()));
                /**获取 URL. */
                a.andExpect(MockMvcResultMatchers.status().isOk())
                /** 返回 数据.*/
                .andExpect(MockMvcResultMatchers.content().json("[{\"name\":\"111\"}]"));
    }

    public void add() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/department/add").contentType(MediaType.APPLICATION_JSON).
                content("{\"name\":\"111\"}").sessionAttr("user", TestDataUtils.createUser())).
                andExpect(MockMvcResultMatchers.status().isOk())
                /** 返回 数据.*/
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Transactional
    @Test
    public void test() throws Exception{
        add();
        list();
    }

    public OrganizationEntity createDepartment(){
        OrganizationEntity department = new OrganizationEntity();
        department.setName("部门1");
        return department;
    }

}
