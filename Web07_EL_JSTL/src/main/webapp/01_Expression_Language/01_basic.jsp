<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Basic</title>
</head>
<body>
	<%-- 
		EL (Expression Language)
		- page, request, session, application 관련 데이터 출력 코드를 간략하게
			사용할 수 있는 표기법
		- 자바빈 컴퍼넌트를 쉽게 접근
		- 집합 객체(list, String[])에 대한 접근 방법 제공
		- 수치 연산, 관계 연산, 논리 연산자 제공
		- 표현 언어만의 기본 객체 제공(범위 객체)
		
		EL 기본 객체
		- pageContext : JSP의 page 기본 객체와 동일
		
		- pageScope : page 기본 객체에 저장된 속성의 <속성, 값> 매핑을 저장한 Map 객체
		- requestScope : request 기본 객체에 저장된 속성의 <속성, 값> 매핑을 저장한 Map 객체
		- sessionScope : session 기본 객체에 저장된 속성의 <속성, 값> 매핑을 저장한 Map 객체
		- applicationScope : application 기본 객체에 저장된 속성의 <속성, 값> 매핑을 저장한 Map 객체
		
		- param : request 파라미터의 <파라미터 명, 값> 매핑을 저장한 Map 객체, 타입은 String
				= request.getParameter()
		- paramValues : param의 배열형
		- cookie : <쿠키명, 쿠키값> 매핑을 저장한 Map 객체
				= request.getCookies()
		- initParam : 초기화된 파라미터의 <파라미터 명, 값> 매핑을 저장한 Map 객체
				= application.getInitParameter
		
	 --%>
	 <%
		pageContext.setAttribute("num1", 1);
	 	request.setAttribute("num2", 2);
	 	session.setAttribute("num3", 3);
	 	application.setAttribute("num4", 4);
	 
	 	// 기본 방식으로 출력
	 	int number1 = (Integer) pageContext.getAttribute("num1");
	 	int number2 = (Integer) request.getAttribute("num2");
	 	
	 	pageContext.setAttribute("scopeNum", 1);
	 	request.setAttribute("scopeNum", 2);
	 	session.setAttribute("scopeNum", 3);
	 	application.setAttribute("scopeNum", 4);
	 	
	 %> 
	 <h1>변수 출력</h1>
	 <p>JSP 변수 합 : <%= number1 + number2 %></p>
	 
	 <p>page num1 : ${num1 }</p><!-- pageContext.getAttribute("num1"); -->
	 <p>request num2 : ${num2 }</p>
	 <p>session num3 : ${num3 }</p>
	 <p>application num4 : ${num4 }</p>
	 
	 <h1>EL 범위 테스트</h1>
	 <p>page ScopeNum : ${scopeNum }</p>
	 <p>request ScopeNum : ${scopeNum }</p>
	 <p>session ScopeNum : ${scopeNum }</p>
	 <p>application ScopeNum : ${scopeNum }</p>
	 
</body>
</html>






