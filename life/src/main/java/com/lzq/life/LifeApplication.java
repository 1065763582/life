package com.lzq.life;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lzq.life.mapper")
public class LifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeApplication.class, args);
	}

}
