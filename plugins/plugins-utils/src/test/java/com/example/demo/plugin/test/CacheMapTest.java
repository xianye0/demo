package com.example.demo.plugin.test;

import com.example.demo.plugins.utils.memory.CacheMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: rock
 * @date: 2019/2/19
 * @Description: map测试类
 */
@RunWith(SpringRunner.class)
public class CacheMapTest {

    @Test
    public void test(){
        CacheMap map = new CacheMap();
        map.put("1","2",1);
        map.put("2","2",10);
        System.out.println(map.size());
        Assert.assertEquals(2,map.size());
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        Assert.assertEquals(1,map.size());

    }
}
