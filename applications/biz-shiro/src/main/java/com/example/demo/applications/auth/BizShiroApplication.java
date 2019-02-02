package com.example.demo.applications.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableEurekaClient
@ComponentScan("com.example.demo")
public class BizShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizShiroApplication.class, args);
	}


}

