<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result</title>
</head>
<body>
<%--3. 로그인 결과 페이지 (HW3_login_result.jsp)
	- 세션에서 id 값을 꺼내서 HTML 태그에 출력
	- 만약, 로그인을 하지 않고 url로 접속할 경우 '로그인 해주세요!!' alert 띄운 후 HW3.jsp 페이지로 이동
	- alert 띄우는 코드
	ㄴ out.print("<script>alert('로그인 해주세요!!');</script>"); --%>
	
	<%
		String id = (String) session.getAttribute("id");
		String sessionId = (String) application.getAttribute("sessionId");
		
		if(sessionId != null && !sessionId.equals(session.getId())){
			application.removeAttribute("sessionId");
			out.print("<script>alert('세션이 만료되었습니다!!');</script>");
			out.print("<script>location.href='HW3.jsp'</script>");
		}else if(id == null){
			out.print("<script>alert('로그인 해주세요!!');</script>");
			out.print("<script>location.href='HW3.jsp'</script>");
		}
	%>
	
	<h1 style="font-size: 1800%" align="center"><%=id %></h1>
	<form action="HW3.jsp" method="POST">
		<input type="submit" value="로그아웃" name="logout">
	</form>
	
</body>
</html>



