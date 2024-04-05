<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글 수정</h1>
	<form action="list" method="POST">
		<div>
			<input type="text" name="postTitle" required="required">
		</div>
		<div>
			<textarea rows="30" cols="60" name="postContent" required="required"></textarea>
		</div>
		<div>
			<input type="submit" name="type" value="수정">
		</div>
	</form>
</body>
</html>