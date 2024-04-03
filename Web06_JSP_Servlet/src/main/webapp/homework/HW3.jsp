<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<%--
	1. 로그인 정보 전송 페이지(HW3.jsp)
	 -id, pw input 태그 생성
	 
	2. 로그인 인증 페이지 (HW3_login_auth.jsp)
	- 전송받은 id, pw를 확인하여 id는 "test", pw는 "1234"일 경우 id 세션 생성(만료시간은 자유)
	- 세션을 생성한 후에 HW3_login_result.jsp 페이지로 이동
	- 페이지 이동 코드
	ㄴ out.print("<script>location.href='HW3_login_result.jsp'</script>");
	- 전송받은 id,pw가 "test", "1234"가 아닌 경우, HW3.jsp 페이지로 이동
	 
	3. 로그인 결과 페이지 (HW3_login_result.jsp)
	- 세션에서 id 값을 꺼내서 HTML 태그에 출력
	- 만약, 로그인을 하지 않고 url로 접속할 경우 '로그인 해주세요!!' alert 띄운 후 HW3.jsp 페이지로 이동
	- alert 띄우는 코드
	ㄴ out.print("<script>alert('로그인 해주세요!!');</script>");
	 --%>
	 <%
	 	if(request.getParameter("logout") != null){ // 로그 아웃 버튼으로 접근시
			session.invalidate();
			out.print("<script>alert('로그 아웃!!');</script>");
		}else if(session.getAttribute("id") != null){ // 로그인 상태에서 URL로 직접 접근시
			out.print("<script>alert('로그인 상태로는 접근할 수 없는 페이지입니다!!');</script>");
			out.print("<script>location.href='HW3_login_result.jsp'</script>");
		}
	 %>
	 <form action="HW3_login_auth" method="POST" autocomplete="off" style="align-content: center;">
	 	<div style="margin-top: 200px; margin-left: 800px">
	 		<p> ID </p>
	 		<input type="text" name="id" required="required">
	 		<p> Password </p>
	 		<input type="password" name="pw" required="required">
			<input type="submit" name="login" value="로그인">
			<input type="button" value="회원가입" onclick="toRegister()"> 
		</div>
	 </form>
	 <script type="text/javascript">
	 	function toRegister(){
	 		location.href= '../ch15/member/register.jsp';
	 	}
	 
	 </script>


</body>
</html>