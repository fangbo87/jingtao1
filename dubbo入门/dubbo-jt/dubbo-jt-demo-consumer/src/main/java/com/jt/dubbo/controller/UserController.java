package com.jt.dubbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.service.UserService;

@RestController
public class UserController {
	
	/**
	 * timeout:定义连接超时时间
	 * check= 消费者启动时检查是否有服务的提供者
	 *   
	 *    调整负载均衡策略:
	 *    1.随机策略  random
	 *    2.轮询策略	roundrobin
	 *    3.IPHASH策略  consistenthash
	 *    4.最小访问策略 leastactive
	 * 	
	 */
	@Reference(timeout=3000,check=true)
	private UserService userService;
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		//感觉调用自己的服务 远程服务器中数据 rpc
		return userService.findAll();
	}
	
	///saveUser/花祭/18/女
	//测试新增操作 如果名称与对象中的属性一致,则可以使用对象直接接收
	@RequestMapping("/saveUser/{name}/{age}/{sex}")
	public String saveUser(User user) {
		
		userService.saveUser(user);
		return "用户入库成功!!!";
	}
}
