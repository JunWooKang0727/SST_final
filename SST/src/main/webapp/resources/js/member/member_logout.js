$(document).ready(function(){
	$('#logout').on("click", function(e){
		e.preventDefault();
		document.getElementById('logoutform').submit();
	}); // #logout end
});