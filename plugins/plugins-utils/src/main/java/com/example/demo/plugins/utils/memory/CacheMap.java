package com.example.demo.plugins.utils.memory;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wulei
 * @date: 2018/8/15
 * @Description: 缓存map，封装了ConcurrentHashMap，增加超时时间，超时后获取数据为null
 */
public class CacheMap<K, V> implements ICacheMap<K, V> {
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

    @Override
    public V putHash(K k, String k1, V v) {
        CacheValue v1 = map.get(k);
        if (v1 == null) {
            Map<String, V> m = new HashMap();
            CacheValue value = new CacheValue(m, timeOut);
            map.put(k, value);
        } else {
            Map<String, V> m = (Map) v1.getValue();
            m.put(k1, v);
            return m.get(k1);
        }
        return null;
    }

    @Override
    public V getHash(K k, String k1) {
        CacheValue v1 = map.get(k);
        if (v1 == null) {
            return null;
        } else {
            Map<String, V> m = (Map) v1.getValue();
            return m.get(k1);
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

    /**
     * 是否包含某个key值
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    /**
     * 是否包含某个值，不存在或过期都返回false
     * @param key
     * @return
     */
    public boolean contains(K key) {
        if (!map.containsKey(key)) {
            return false;
        }
        CacheValue value = map.get(key);
        if (value.isExpired()) {
            map.remove(key);
            return false;
        }
        return true;
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

    public void remove(K k) {
        map.remove(k);
    }

    public int size() {
        return map.size();
    }

    public void destroy() {
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
