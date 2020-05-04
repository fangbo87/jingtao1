package com.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;
//@Mapper	//将接口交给Spring容器管理
public interface UserMapper extends BaseMapper<User>{
	
	//查询全部数据
	List<User> findAll();
	
}
