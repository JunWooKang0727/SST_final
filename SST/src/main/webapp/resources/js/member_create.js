$(document).ready(function() {
	
		var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}";
			
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
					type:"get",
					url:"/member/checkId",
					dataType: "text",
					data: {"id":id},
					success: function(result) {
						if(result === "fail") {
							alert("사용할 수 없는 아이디입니다");
						} else if(result === "success"){
							alert("사용할 수 있는 아이디입니다");
						}
					}
				});
			} // [ idCheck 함수 ] 끝
}); 