<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href ="../project/index.jsp"><h3>Home</h3></a>
	
	<div align="center" class="div_center">
		
			
			<h3>로그인페이지</h3>
			<hr>
			<form action="loginForm.user" method="post">
				<input type="text" name="id" placeholder="아이디"><br><br>
				<input type="password" name="pw" placeholder="비밀번호"><br><br>
				<input type="submit" value="로그인" class="btn btn-default">&nbsp;&nbsp;
				<input type="button" value="회원가입" class="btn btn-default" onclick="location.href='customer_join.jsp'">    
			</form>										
		
			
			
		
	</div>
</body>
</html>