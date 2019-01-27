package com.example.demo.applications.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demo")
@EnableFeignClients(basePackages = "com.example.demo.plugins.feign.client")
public class BizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizServiceApplication.class, args);
	}

}

