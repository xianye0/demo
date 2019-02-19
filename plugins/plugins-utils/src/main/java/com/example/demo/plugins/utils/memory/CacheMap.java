package com.example.demo.plugins.utils.memory;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wulei
 * @date: 2018/8/15
 * @Description: 缓存map，封装了ConcurrentHashMap，增加超时时间，超时后获取数据为null
 */
public class CacheMap<K, V> {
    private Map<K, CacheValue<V>> map = new ConcurrentHashMap<>();
    //单位秒
    private int timeOut = 30;

    //监控
    {
        CacheMapMonitor.getInstance().addMap(map);
    }

    public CacheMap() {

    }

    public CacheMap(int timeOut) {
        this.timeOut = timeOut;
    }

    public V put(K k, V v) {
        return put(k, v, timeOut);
    }


    public V put(K k, V v, int timeOut) {
        CacheValue value = new CacheValue(v, timeOut);
        CacheValue<V> oldValue = map.put(k, value);
        if (oldValue == null) {
            return null;
        } else {
            return oldValue.getValue();
        }
    }

    /**
     * put值，如果已存在则返回原始值，如果不存在则返回null
     * @param k
     * @param v
     * @param timeOut
     * @return
     */
    public V putIfAbsent(K k, V v, int timeOut) {
        CacheValue value = new CacheValue(v, timeOut);
        CacheValue<V> oldValue = map.putIfAbsent(k, value);
        if (oldValue == null) {
            return null;
        } else {
            return oldValue.getValue();
        }
    }


    public V get(Object k) {
        CacheValue<V> value = map.get(k);
        if (value == null) {
            return null;
        } else if (value.isExpired()) {
            map.remove(value);
            return null;
        }
        return value.getValue();
    }

    public void remove(Object k) {
        map.remove(k);
    }

    public int size() {
        return map.size();
    }

    public void destroy(){
        map = null;
    }


    class CacheValue<V> {
        //存放时间
        private long time;
        //保存值
        private V value;

        public CacheValue(V value, int timeOut) {
            this.value = value;
            this.time = System.currentTimeMillis() + timeOut * 1000;
        }

        public V getValue() {
            return value;
        }

        /**
         * 是否过期
         * @return
         */
        public boolean isExpired() {
            return System.currentTimeMillis() > time;
        }
    }
}
