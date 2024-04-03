<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 가입하기</h2>
	<form action="../register.do" method="POST">
		<p>아이디</p>
		<input type="text" name="userId">
		<p>패스워드</p>
         <input type="password" name="password" required="required">    
      	<p>이메일</p>
        <input type="email" name="email" required="required">    
      	<p>이메일 수신여부</p>
        <input type="radio" name="emailAgree" value="yes">예     
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