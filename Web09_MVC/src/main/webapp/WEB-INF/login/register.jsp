<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Register</title>
</head>
<body>
	<h2>회원 가입</h2>
	
	<form action="register.do" method="POST">
		<div>
			<p>ID</p>
			<input type="text" name="memberId" id="inputId" required>
			<input type="button" value="아이디 중복 체크" onclick="idDupChk()">
		</div>
		<div>
			<p>비밀번호</p>
			<input type="password" name="pw" required>
		</div>
		<div>
			<p>이름</p>
			<input type="text" name="name" required>
		</div>
		<div>
			<p>이메일</p>
			<input type="email" name="email" required>
		</div>
		<div>
			<p>전화번호</p>
			<input type="text" name="phone" required> 
		</div>
		<input type="submit" id="btnRegister" value="회원가입" disabled="disabled">
	</form>
	
	
	<script type="text/javascript">
		
		let inputId = $('#inputId');
		let btnRegister = $('#btnRegister');
		
		inputId.blur(function(){
			console.log("id changed");
			btnRegister.attr("disabled", "disabled");
		});
		
		function idDupChk(input){
			let memberId = inputId.val();
			console.log("idDupChk memberId : " + memberId);
			
			$.ajax({
				type : "POST",
				url : "idDupChk.do",
				data : {
					"memberId" : memberId	
				},
				success : function(result){
					console.log(result);
					if(result == '1'){
						alert("사용 가능한 ID 입니다.");
						btnRegister.attr("disabled", null);
					}else {
						alert("사용할 수 없는 ID 입니다.");
					}
				}
			}); // end ajax
			
		} // end idDupChk
	
	</script>
	
</body>
</html>