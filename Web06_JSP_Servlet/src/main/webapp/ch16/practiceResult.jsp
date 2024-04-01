<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result</title>
</head>
<body>
	<%
		// practiceLogin.jsp에서 saveAgreed가 체크가 되어 있으면,
		// id, pw에 대한 쿠키를 생성한다.
		// 쿠키 만료 시간은 10분으로 설정.
		String id = "", pw = "";
		if(request.getParameter("saveAgreed") != null){
			id = request.getParameter("id");
			pw = request.getParameter("pw");
		}
		Cookie idCookie = new Cookie("id", id);
		Cookie pwCookie = new Cookie("pw", pw);
		idCookie.setMaxAge(60*10);
		pwCookie.setMaxAge(60*10);
			
		response.addCookie(idCookie);
		response.addCookie(pwCookie);
		Date date = new Date();
		int remainHour = 23 - date.getHours();
		int remainMinute = 59 - date.getMinutes();
		int remainSecond = 59 - date.getSeconds();
		
		int remainTotalSec = remainSecond + remainMinute*60 + remainHour*3600;
	%>

	<h2>로그인 결과 화면</h2>
	<p><%= request.getParameter("id") %>님, 환영합니다.</p>
	<p>자정까지 남은 시간 : <%= remainHour%>시 <%= remainMinute %>분 <%= remainSecond %>초</p>
	
</body>
</html>