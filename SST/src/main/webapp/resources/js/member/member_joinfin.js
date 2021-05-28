$(document).ready(function(){
	$('#mainpage').on("click", function(){
		self.location="/member/main";
	})
	
	$('#loginpage').on("click", function(){
		self.location="/member/login";
	})
})