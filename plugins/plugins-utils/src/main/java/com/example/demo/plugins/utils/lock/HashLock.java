package com.example.demo.plugins.utils.lock;

import com.example.demo.plugins.utils.memory.CacheMap;

/**
 * @author: rock
 * @date: 2019/2/19
 * @Description:
 */
public class HashLock implements CacheLock {
    private static final int WAIT_TIME = 15;
    CacheMap map = new CacheMap();

    @Override
    public boolean tryLock(Object key, int waitTime) {
        return map.putIfAbsent(key,"",waitTime) == null;
    }

    @Override
    public boolean tryLock(Object key) {
        return tryLock(key,WAIT_TIME);
    }

    @Override
    public boolean lock(Object key) {
        return lock(key,WAIT_TIME);
    }

    @Override
    public void unLock(Object key) {
        map.remove(key);
    }
}
