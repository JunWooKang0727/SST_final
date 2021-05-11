<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="/member/create" method="post">
		아이디 : <input id="userid" name="m_id" type="text" placeholder="아이디를 입력하세요">
		<button id="duplicate_id" type="button">중복체크</button><br>
		비밀번호 : <input name="m_pw" type="password" placeholder="비밀번호를 입력하세요"><br>
		이름 : <input name="m_name" type="text" placeholder="이름을 입력하세요"><br>
		이메일 : <input name="m_email" type="text" placeholder="이메일을 입력하세요"><br>
		핸드폰 번호 : <input name="m_phone" type="text" placeholder="핸드폰 번호를 입력하세요"><br>
		생년월일 : <input name="m_birth" type="text" placeholder="생년월일을 입력하세요"><br>
		<input type="submit" value="회원가입">
	</form>
	
	<script>
		$(document).ready(function() {
			
			$("#duplicate_id").on("click", function() {
				var id = $("#userid").val();
				alert(id);
				if(id == "") {
					alert("아이디를 입력해 주세요");
				} else {
					idCheck(id);
				}
			}); // #duplicate_id 클릭 이벤트 끝
			
			function idCheck(id){
				$.ajax({
					type:"post",
					url:"/member/checkId",
					dataType: "text",
					data: id,
					success: function(result) {
						if(result == "cantuse") {
							alert("사용할 수 없는 아이디입니다");
						} else if(result == "canuse"){
							alert("사용할 수 있는 아이디입니다");
						}
					}
				});
			} // [ idCheck 함수 ] 끝
		}); 
	</script>
</body>
</html>