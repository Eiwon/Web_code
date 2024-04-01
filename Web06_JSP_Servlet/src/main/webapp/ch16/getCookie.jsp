<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get Cookie</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
	
		if(cookies != null){ // 쿠키가 존재하는 경우
			out.println("<h2>모든 쿠키의 이름과 값 찾기</h2>");
			for(Cookie cookie : cookies){
				out.print("name : " + cookie.getName() + "<br>");
				out.print("value : " + cookie.getValue() + "<br>");
				out.print(cookie.getComment() + "<br>");
			}
		}else{
			out.println("<h2>쿠키를 찾지 못했습니다.</h2>");
		}
	
	%>

</body>
</html>