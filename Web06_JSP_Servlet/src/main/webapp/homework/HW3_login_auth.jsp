<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Auth</title>
</head>
<body>
<%--2. 로그인 인증 페이지 (HW3_login_auth.jsp)
	- 전송받은 id, pw를 확인하여 id는 "test", pw는 "1234"일 경우 id 세션 생성(만료시간은 자유)
	- 세션을 생성한 후에 HW3_login_result.jsp 페이지로 이동
	- 페이지 이동 코드
	ㄴ out.print("<script>location.href='HW3_login_result.jsp'</script>");
	- 전송받은 id,pw가 "test", "1234"가 아닌 경우, HW3.jsp 페이지로 이동 --%>
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		int maxInterval = 30; // 세션 만료 시간 5초
		
		if(id == null){ // URL로 바로 접근
			out.print("<script>alert('정상적으로 로그인해주세요!!');</script>");
			out.print("<script>location.href='HW3.jsp'</script>");
		} else if(id.equals("test") && pw.equals("1234")){ // 로그인 성공
			session.setAttribute("id", id);
			session.setMaxInactiveInterval(maxInterval);
			application.setAttribute("sessionId", session.getId());
			out.print("<script>location.href='HW3_login_result.jsp'</script>");
		}else{ // 로그인 실패
			out.print("<script>alert('잘못된 아이디 또는 비밀번호 입니다!!');</script>");
			out.print("<script>location.href='HW3.jsp'</script>");
		}
	
	%>
</body>
</html>