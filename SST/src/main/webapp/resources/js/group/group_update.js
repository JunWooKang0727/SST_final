$(document).ready(function(){

	var formObj = $('form');
	var g_num = $('#g_num').val();
	$('.groupbtn').on("click", function(e){
		e.preventDefault();
		var oper = $(this).data("oper");
		console.log(oper);
		console.log(g_num);
		if(oper === 'grouplist'){
			// formObj.attr("action", "/group/read?g_num=" + g_num);
			var url = "/group/read?g_num=" + g_num;
			self.location = "/group/read?g_num=" + g_num;
			return;
		} else if(oper === 'groupdelete') {
			formObj.attr("action", "/group/remove");
		}
		
		formObj.submit();
	}); // groupbtn 끝 
});
