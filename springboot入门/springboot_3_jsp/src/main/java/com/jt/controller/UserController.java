package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.User;
import com.jt.service.UserService;

//@RestController  //返回的结果为JSON
@Controller		   //返回的是页面的逻辑名称
public class UserController {
	
	@Autowired
	private UserService userService;	//按照类型注入
	
	//要求跳转页面  localhost:8090/findAll
	//${userList} 动态取值
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		List<User> userList = userService.findAll();
		//同步页面数据展现 model操作的是request对象（request域）
		model.addAttribute("userList", userList);
		//页面逻辑名称将来交给视图解析器处理
		return "userList";
	}
	
	/**
	 * 实现ajax页面跳转
	 */
	@RequestMapping("/ajax")
	public String ajax() {
		
		return "ajax"; //跳转页面逻辑名称
	}
	
	/**
	 * 实现ajax数据访问  /findAjax   返回的是userList集合的JSON数据
	 */
	@RequestMapping("/findAjax")
	@ResponseBody	//将数据转化为JSON
	public List<User> findAjax(){
		
		return userService.findAll();
	}
}
