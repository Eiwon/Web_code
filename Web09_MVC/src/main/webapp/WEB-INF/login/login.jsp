<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h2>로그인</h2>
	<form action="member/login" method="POST">
		<div>
			<p>ID</p>
			<input type="text" name="memberId" required>
		</div>
		<div>
			<p>비밀번호</p>
			<input type="password" name="pw" required>
		</div>
		<div>
			
		</div>
		<input type="submit" value="로그인">
	</form>
	<a href="member/register">회원가입</a>
	
</body>
</html>