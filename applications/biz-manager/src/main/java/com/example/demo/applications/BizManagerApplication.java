package com.example.demo.applications;


import com.example.demo.plugins.utils.lock.CacheLock;
import com.example.demo.plugins.utils.lock.HashLock;
import com.example.demo.plugins.utils.memory.CacheMap;
import com.example.demo.plugins.utils.memory.ICacheMap;
import com.example.demo.plugins.utils.security.DefaultKeyPairManager;
import com.example.demo.plugins.utils.security.KeyPairManager;
import com.example.demo.plugins.utils.security.PasswordUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.example.demo")
public class BizManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizManagerApplication.class, args);
    }



    @Bean
    public ICacheMap getCacheMap() {
        return new CacheMap();
    }

    /**
     * 事务锁
     * @return
     */
    @Bean
    public CacheLock getLock() {
        return new HashLock(getCacheMap());
    }
    /**
     * 密钥管理
     * @return
     */
    @Bean
    public KeyPairManager getKeyPairManager() {
        return new DefaultKeyPairManager(getCacheMap());
    }

    /**
     * 密钥管理
     * @return
     */
    @Bean
    public PasswordUtils getPasswordUtils() {
        return new PasswordUtils(getKeyPairManager());
    }

}

