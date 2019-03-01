package com.example.demo.applications.auth;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.demo.plugins.utils.lock.CacheLock;
import com.example.demo.plugins.utils.lock.HashLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableEurekaClient
@ComponentScan("com.example.demo")
public class BizShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizShiroApplication.class, args);
	}

	@Bean
	public CacheLock getLock(){
		return new HashLock();
	}

	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
}

