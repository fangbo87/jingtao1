package com.jt.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

//springBoot的测试类
@SpringBootTest	//内部关联了主启动类
class Springboot1ApplicationTests {
	/**
	 * @Test启动程序
	 * 当程序启动时会加载该注解.该注解内部链接者主启动类. spring容器内部实例化对象
	 * 利用autowired注解动态从spring容器中获取对象进行注入.
	 */
	@Autowired	//动态注入
	private UserMapper userMapper;
	
	//测试包注解主持. 
	//测试方法语法规范    public void testXXXX()
	@Test
	public void testFind() {
		
		List<User> userList = userMapper.findAll();
		System.out.println(userList);
	}
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setId(1910)
			.setName("测试")
			.setAge(18)
			.setSex("女");
		userMapper.insert(user);
	}
	
	
	
	
	
}
