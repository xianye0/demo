package com.example.demo.plugin.test;

import com.example.demo.plugins.utils.lock.CacheLock;
import com.example.demo.plugins.utils.lock.HashLock;
import com.example.demo.plugins.utils.memory.CacheMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: rock
 * @date: 2019/2/19
 * @Description:
 */
@RunWith(SpringRunner.class)
public class LockTest {
    @Test
    public void test(){
        CacheMap map = new CacheMap();
        CacheLock lock = new HashLock(map);
        for(int i = 0;i<100;i++){
            String key = i+"_";
            new Thread(()->{
                if(lock.tryLock("_")){
                    map.put(key,"1");
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        Assert.assertEquals(1,map.size());
    }
}
