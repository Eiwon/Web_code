<%@page import="edu.web.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Update</title>
</head>
<body>
	<%
		String userId = (String)request.getSession().getAttribute("userId");
		// TODO : 로그인된 사용자 아이디를 저장
		// TODO : form action="update.do" method="POST" 생성
		// TODO : userId를 제외한 모든 정보 수정 가능하게 input 태그 작성
		// 		  userID는 읽기만 가능하도록 input 태그 생성
	%>
	<form action="update.do" method="POST">
		<p>아이디</p>
		<input type="text" name="userId" value="<%=userId%>" disabled="disabled">
		<p>패스워드</p>
         <input type="password" name="password" required="required">    
      	<p>이메일</p>
        <input type="email" name="email" required="required">    
      	<p>이메일 수신여부</p>
        <input type="radio" name="emailAgree" value="yes" >예     
      	<input type="radio" name="emailAgree" value="no" checked="checked">아니오  
      	<p>취미</p>
		<input type="checkbox" name="interest" value="IT/인터넷">IT/인터넷
		<input type="checkbox" name="interest" value="영화">영화
		<input type="checkbox" name="interest" value="독서">독서
		<input type="checkbox" name="interest" value="운동">운동
		<input type="checkbox" name="interest" value="산책">산책
		<p>핸드폰</p>
		<input type="text" name="phone" required="required"> 
		<p>자기소개</p>
		<textarea rows="4" cols="25" name="introduce" required="required"></textarea>       
        <input type="submit" value="전송"> 
    </form>

</body>
</html>