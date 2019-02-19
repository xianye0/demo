package com.example.demo.plugins.cache.lock;

import com.example.demo.plugins.utils.lock.CacheLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: wulei
 * @date: 2018/11/15
 * @Description: 利用redis实现分布式同步锁
 */
@Slf4j
public class DistributedLock implements CacheLock {
    //默认有效时间
    private static final int WAIT_TIME = 15;
    private static final String PRE = "lock:";

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 获取同步锁，同一个key会等待前一个锁释放完成后再获取
     * @param key
     * @return
     */
    @Override
    public boolean lock(Object key) {
        return lock(key, WAIT_TIME);
    }




    /**
     * 解锁
     * @param key
     */
    @Override
    public void unLock(Object key) {
        redisTemplate.delete(PRE + key);
    }


    /**
     * 获取锁,如果未获取到则返回false
     * @param key
     * @return
     */
    @Override
    public boolean tryLock(Object key) {
        return tryLock(key, WAIT_TIME);
    }

    /**
     * 获取锁,如果未获取到则返回false
     * @param key
     * @param waitTime 锁的有效时间，单位(秒)
     * @return
     */
    @Override
    public boolean tryLock(Object key, int waitTime) {
        boolean a = redisTemplate.opsForValue().setIfAbsent(PRE + key, 1);
        if (a) {
            redisTemplate.expire(PRE + key, waitTime, TimeUnit.SECONDS);
        }
        return a;
    }

}
