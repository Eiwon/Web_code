<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<%
		// 저장된 id, pw 쿠키 꺼내서 input 태그(id, pw)에 값 보여주기
		Cookie[] cookies = request.getCookies();
		String id = "";
		String pw = "";
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("id")){
					id = cookie.getValue();
				}
				if(cookie.getName().equals("pw")){
					pw = cookie.getValue();
				}
			}
		}
	%>
	<form action="practiceResult.jsp" method="POST">
		아이디 <br>
		<input type="text" name="id" required="required" value="<%=id %>" ><br>
		비밀번호 <br>
		<input type="password" name="pw" required="required" value="<%=pw %>" ><br>
		<input type="checkbox" name="saveAgreed" value="agreed" checked="checked">
		아이디 저장 <br><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>