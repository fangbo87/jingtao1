package com.jt.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@SpringBootTest
public class TestMP {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 用户入库测试
	 */
	@Test
	public void insert() {
		User user = new User();
		user.setName("MP测试")
		.setAge(19)
		.setSex("女");
		userMapper.insert(user); 
		//单表操作几乎不写sql. 以对象的方式操作数据库!!!
	}

	//查询操作 查询单个用户 id=49
	@Test
	public void select01() {
		User user = userMapper.selectById(49);  //id代表主键
		System.out.println(user);
	}

	//查询操作 查询全部用户
	//sql: select * from user
	@Test
	public void select02() {
		//null相当于 不需要写where条件
		List<User> userList = userMapper.selectList(null);
		System.out.println(userList);
	}
	
	/**
	 *   查询sex="女"  and age>300 
	 *  规则: 如果连续操作,则连接符默认and
	 *  > gt   =eq   < lt
	 *  >= ge  <=le
	 */
	@Test
	public void select03() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();//条件构造器
		queryWrapper.eq("sex", "女")
					.gt("age", 300);
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	/**
	 *  查询sex="女"  or age > 300
	 */
	@Test
	public void select04() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();//条件构造器
		queryWrapper.eq("sex", "女")
					.or()
					.gt("age", 300);
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	/**
	 * 查询ID=1,3,5的数据
	 * sql: xxxxxxxx  where id in (1,3,5);
	 */
	@Test
	public void select05() {
		List<Integer> idList = new ArrayList<>();
		idList.add(1);
		idList.add(3);
		idList.add(5);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();//条件构造器
		//queryWrapper.in("id", idList);
		queryWrapper.in("id", 1,3,5);
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	/**
	 *  根据对象实现条件查询
	 *条件限制: 只能查询=业务逻辑
	 * 查询name="不知火舞" sex="女"
	 */
	@Test
	public void select06() {
		User user = new User();
		user.setName("不知火舞")
			.setSex("女");
		//利用对象中不为null的属性充当where条件  where name sex
		QueryWrapper<User> queryWrapper = 
				new QueryWrapper<User>(user);
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	/**
	 * 查询sex="女" and name="%精%"    
	 *以乔结尾的数据     %乔
	 */
	@Test
	public void select07() {
		QueryWrapper<User> queryWrapper = 
				new QueryWrapper<User>();
		queryWrapper.eq("sex","女")
					.likeLeft("name", "乔");
					//.like("name","精");
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	/**
	 * 查询age 18-35之间数据，按照性别排序
	 * asc:升序
	 * desc:降序
	 *    默认：按照主键升序
	 */
	@Test
	public void select08() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.between("age", 18, 35)
					.orderByDesc("sex");
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	/**
	 * 1.查询总记录数
	 * 2.查询单个记录
	 */
	@Test
	public void select09() {
		
		int count = userMapper.selectCount(null);
		User user = new User();
		user.setId(3);
		User user2 = userMapper.selectOne(new QueryWrapper<User>(user));
		System.out.println(count);
		System.out.println(user2);
	}
	
	/**
	 *  更新操作 update
	 * 将id=1911 数据 改为嫦娥 22岁
	 */
	@Test
	public void select10() {
		User user = new User();
		//除了主键之外的数据,其他不为null的数据充当set条件!!!
		user.setId(1911)
			.setName("嫦娥")
			.setAge(22);
		userMapper.updateById(user);//根据主键更新
	}
	
	/**
	 * 
	 *  要求:将1910,1911,1912的数据 改为林志玲 age=18
	 *  entity:修改数据的实体 
	 *  updateWrapper:修改的条件构造器
	 */
	@Test
	public void select11() {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.in("id", 1910,1911,1912);
		User user = new User();
		user.setName("林志玲")
			.setAge(18);
		userMapper.update(user, updateWrapper);
	}
	
	/**
	 *需求:将name="林志玲" sex="女" 用户删除
	 *说明: 删除的是 查询出来的数据
	 */
	@Test
	public void select12() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name","林志玲")
					.eq("sex","女");
		userMapper.delete(queryWrapper);
	}
	
	/**
	 *需求:删除name为null的数据
	 */
	@Test
	public void select13() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.isNull("name");
		userMapper.delete(queryWrapper);
	}
	
	
}
