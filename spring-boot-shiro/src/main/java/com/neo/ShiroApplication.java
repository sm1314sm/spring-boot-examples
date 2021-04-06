package com.neo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.neo.dao")
public class ShiroApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShiroApplication.class, args);
	}
}
