package com.example.demo.plugins.utils.lock;

/**
 * @author: rock
 * @date: 2019/2/19
 * @Description: 分布式锁
 */
public interface CacheLock {

    boolean tryLock(Object key,int waitTime);

    boolean tryLock(Object key);

    /**
     * 获取同步锁，同一个key会等待前一个锁释放完成后再获取
     * @param key
     * @param waitTime 等待时间，单位（秒）
     * @return
     */
    default boolean lock(Object key,int waitTime){
        if (tryLock(key, waitTime)) {
            return true;
        } else {
            long t1 = System.currentTimeMillis();
            while (t1 > System.currentTimeMillis() - waitTime * 1000) {
                if (tryLock(key, waitTime)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean lock(Object key);

    void unLock(Object key);
}
