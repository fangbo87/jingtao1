<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>您好Springboot</title>
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	//将页面加载完成之后调用
	$(function(){

		/*异步方式获取服务器数据.并且在页面中展现数据  
			jQuery中常见ajax方法
			1.$.get(url地址,"提交的参数",回调函数,"返回值数据类型可以省略不写");
			2.$.post(url地址,"提交的参数",回调函数,"返回值数据类型可以省略不写");
			3.$.getJSON(url地址,"提交参数",回调函数);
			4.$.ajax({.....}); 必会内容
		*/

		$.get("/findAjax",function(result){
			/**
				服务器数据正确返回   [user,user,user]
				思路:
					1.循环遍历list集合获取其中user对象信息
					2.动态获取id/name/age/sex的属性值
					3.拼接为一行tr标签数据.
			**/
			$(result).each(function(index,user){ //arg0 下标  arg1遍历对象
				//var user = result[index];	//获取每一个user对象
				//var name = user.name;		//获取名称
				var id = user.id;
				var name = user.name;
				var age = user.age;
				var sex = user.sex;
				var tr = "<tr align='center'><td>"+id+"</td><td>"+name+"</td><td>"+age+"</td><td>"+sex+"</td></tr>";
				//将tr标签追加到table中
				$("#tb1").append(tr);
			});
		});

		/**
			$.ajax{}  必须掌握的ajax请求调用方式 万能的用法
			data: 可以进行字符串拼接.

			说明:
				1.JS对象       
				2.JSON对象 没有JSON对象
				3.JSON字符串  特殊的字符格式   json可以被js解析 js对象可以获取key/value进行字符串拼接
				/findAjax?id=100&name=tomcat
		**/
		$.ajax({
			type: "GET",
			url:  "/findAjax",
			data: {"id":"100","name":"tomcat"},//"id=1&name=tomcat&age=18",
			async: true, //ajax请求默认是异步的 true/false 请求为同步
			//dataType: //数据返回的类型 xml/html/text/json/script
			success: function(result){ //json串 JS可以解析 JS对象
				var tr = null;
				$(result).each(function(index,user){ //arg0 下标  arg1遍历对象
					var id = user.id;
					var name = user.name;
					var age = user.age;
					var sex = user.sex;
					tr += "<tr align='center'><td>"+id+"</td><td>"+name+"</td><td>"+age+"</td><td>"+sex+"</td></tr>";
				});
				//将tr标签追加到table中
				$("#tb1").append(tr);						
			},
			error: function(){
				alert("请求失败了!!!!");
			}
		})
		
	})
</script>
</head>
<body>
	<table border="1px" width="65%" align="center" id="tb1">
		<tr>
			<td colspan="6" align="center"><h3>学生信息</h3></td>
		</tr>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
			<th></th>
		</tr>
	</table>
</body>
</html>