package com.jt.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//最好与数据库中的字段一致
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@TableName	//实现了对象与表的映射关系 如果表名相同,则可以省略不写
public class User implements Serializable{
	@TableId(type=IdType.AUTO)	//定义主键,主键自增
	private Integer id;
	//@TableField("name") //关联表字段,规则如果属性名称与字段名称一致则
						  //可以省略不写(包含驼峰规则)
	private String name;
	private Integer age;
	private String sex;
}
