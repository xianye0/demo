package com.example.demo.plugins.cache.cache;

import com.example.demo.plugins.utils.memory.ICacheMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: wulei
 * @date: 2019/4/8
 * @Description:
 */

public class RedisCacheMap<K,V> implements ICacheMap<K,V> {
    @Autowired
    RedisTemplate<K,V> redisTemplate;

    @Override
    public V get(K k) {
        return redisTemplate.opsForValue().get(k);
    }

    @Override
    public V put(K k, V v, int timeOut) {
        redisTemplate.opsForValue().set(k,v);
        redisTemplate.expire(k,timeOut, TimeUnit.SECONDS);
        return null;
    }

    @Override
    public V putHash(K k, String k1, V v) {
        redisTemplate.opsForHash().put(k,k1,v);
        return null;
    }

    @Override
    public V getHash(K k, String k1) {
        return (V)redisTemplate.opsForHash().get(k,k1);
    }

    @Override
    public V putIfAbsent(K k, V v, int timeOut) {
        redisTemplate.opsForValue().setIfAbsent(k,v);
        redisTemplate.expire(k,timeOut, TimeUnit.SECONDS);
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public void remove(K k) {
        redisTemplate.delete(k);
    }
}
