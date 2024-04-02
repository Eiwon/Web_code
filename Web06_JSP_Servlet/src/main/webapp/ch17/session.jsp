<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
</head>
<body>
	<%
		// session 객체 : session 범위에 존재하는 객체
		// 브라우저가 하나라도 켜져있는 동안 유지
		// 일반적으로 서버에서 생성(클라이언트에선 값 꺼내기만)
		
		// 세션 생성 시간
		Date createTime = new Date(session.getCreationTime());
	
		// 이 웹페이지의 마지막 접속 시간
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		// 세션 유지시간 설정 방법 1
		session.setMaxInactiveInterval(100); // 10초(페이지를 이동하지 않고)
		
		// 세션 유지시간 설정 방법 2
		// - web.xml 설정
		
		/* 
			<session-config>
			<session-timeout>10</session-timeout> // 분 단위
			</session-config>
		*/
		
		// 세션에 키 값 저장하기
		session.setAttribute("userId", "운영자");
		
		
	%>
	
	<%-- 
		HTTP 특성
		- stateless protocol : 통신이 끝나면 상태를 유지 X
		- 쿠키와 세션이 이를 보완.
		
		Session
		- 쿠키 기반, 서버측에서 데이터 관리
		- 세션 ID를 부여하여 브라우저가 종료될 때까지 데이터 유지
		- 세션 객체 : 사용자를 식별할 수 있는 방법을 제공
			ㄴ 사용자에 대한 정보 저장
		- 주의사항 : 세션은 현재 프로젝트에 실행되는 모든 웹 페이지에 적용됨.
			ㄴ 하나의 클라이언트에 다수의 세션 사용시 충돌 발생 주의
		- Session 객체는 Session의 메소드를 사용해서 생성
			ex) session.setAttribute();
	 --%>
	
	<h2>세션 아이디 : <%=session.getId() %></h2>
	<h2>세션 생성시간 : <%=createTime %></h2>
	<h2>마지막 접속 시간 : <%=lastAccessTime %></h2>	
	<h2><%=session.getAttribute("userId") %></h2>
	<a href="sessionTest.jsp">sessionTest.jsp 페이지로 이동</a>

</body>
</html>




