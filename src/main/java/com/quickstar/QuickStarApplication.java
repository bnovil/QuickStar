package com.quickstar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@MapperScan("com.quickstar.mapper")
public class QuickStarApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickStarApplication.class, args);
	}
}
