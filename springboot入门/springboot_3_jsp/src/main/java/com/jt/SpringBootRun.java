package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication	//springBoot主启动类
@MapperScan("com.jt.mapper") //mapper包扫描所有的接口
//spring容器创建的是Mapper对象的代理
public class SpringBootRun {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootRun.class, args);
	}
}
