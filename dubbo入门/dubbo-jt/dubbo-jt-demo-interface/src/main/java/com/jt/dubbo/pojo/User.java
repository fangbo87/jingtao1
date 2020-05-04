package com.jt.dubbo.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName
//RPC调用可以直接传递java对象,序列化和反序列化的问题
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@TableId(type=IdType.AUTO)
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
	
	
}
