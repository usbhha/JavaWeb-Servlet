<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script src="${APP_PATH }/js/jquery-1.12.4.min.js"></script>
<title>用户注册</title>
</head>
<body>
	<div style="margin: 0 20%">
		username:<input id="idname" type="text" name="username"/><br>
		password:<input id="idpassword" type="password" name="password" /><br>
		<button type="button" id="ajaxBtn">确定</button>
	</div>
	
	
	<script type="text/javascript">
		/*'{"username":username,"password":password}' , */
		
		$("#ajaxBtn").on('click',function(){
			
			var username = $("#idname").val();
			var password = $("#idpassword").val();
			console.log(username+":"+password);
			var user = {"username":username,"password":password};
			
			$.ajax({
				url:"${APP_PATH}/register/registerServlet" ,
				type:"POST" ,
				data:user ,
				success:function(res){
					console.log(res);
					if(res == "注册成功"){
						alert("注册成功;跳转登录界面");
						window.location.href="index.jsp";
					}else{
						alert("注册失败,原因："+res);
					}
				}
			});
		});
	</script>
</body>
</html>