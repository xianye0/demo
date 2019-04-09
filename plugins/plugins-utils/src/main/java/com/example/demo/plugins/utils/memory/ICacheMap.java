package com.example.demo.plugins.utils.memory;

/**
 * @author: rock
 * @date: 2019/4/8
 * @Description: 缓存接口
 */
public interface ICacheMap<K,V> {

    V get(K k);

    V put(K k, V v, int timeOut);

    V putHash(K k,String k1,V v);

    V getHash(K k,String k1);

    V putIfAbsent(K k, V v, int timeOut);

    boolean containsKey(K key);

    boolean contains(K key);

    void remove(K k);


}
