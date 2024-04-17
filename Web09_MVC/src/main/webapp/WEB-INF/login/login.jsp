<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h2>로그인</h2>
	<form action="<%= request.getContextPath() %>/login/login.do" method="POST">
		<div>
			<p>ID</p>
			<input type="text" name="memberId" required>
		</div>
		<div>
			<p>비밀번호</p>
			<input type="password" name="pw" required>
		</div>
		<div>
			<input type="hidden" name="returnPath" value="<%=request.getAttribute("returnPath") %>">
		</div>
		<input type="submit" value="로그인">
	</form>
	<a href="register.do">회원가입</a>
	
</body>
</html>