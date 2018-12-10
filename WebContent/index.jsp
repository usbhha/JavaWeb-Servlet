<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <!-- 第一种设置全局路径：
    		使用方式如： <form
    		action=" ${APP_PATH}/LoginServlet"  
    	等同于: ${pageContext.request.contextPath}
    	
    -->
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Servlet 登录注册测试</title>

<script src="${APP_PATH }/js/jquery-1.12.4.min.js"></script>

</head>
<body>
	<!-- 第二种设置全局路径：
		<\%=request.getContextPath() %>
	 -->
	<form id="form" action="<%=request.getContextPath() %>/login/loginServlet" method="POST">
		username:<input type="text" name="username"/><br>
		password:<input type="password" name="password" /><br>
		<input type="submit" value="登录"/><button type="button" id="ajaxBtn">后台登录</button><br>
		<button type="button" id="btnRegister">注册</button>
	</form>
	
	<!-- ajax 后台登录 -->
	<script type="text/javascript">
		$("#ajaxBtn").on('click',function(){
			//后台打印
			console.log("ajax 后台登录验证");
			
			//ajax
			//data：传入表单form 序列化
			$.ajax({
				url:"${APP_PATH}/login/ajaxServlet" ,
				type:"POST" ,
				data:$("#form").serialize() ,
				success:function(res){
					console.log("ajax 校验返回结果:"+res);
					/* json 解析 */
					var jsons = eval("("+res+")");
					console.log("是否登录成功："+jsons.isLogin);
					console.log("用户名："+jsons.user[0].username);
					console.log("密码："+jsons.user[0].password);
					
					//根据json信息 判断
					if("success" == jsons.isLogin){
						alert("登录成功：用户名密码正确");
						window.location.href= "success.jsp" ;
					}else{
						alert("用户登录失败："+jsons.isLogin)
					}
				}
			});
			
		});
		
		$("#btnRegister").on('click',function(){
			window.location.href= "register.jsp";
		});
		
	</script>
</body>
</html>