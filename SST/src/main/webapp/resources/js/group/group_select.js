$(document).ready(function(){
	var operForm = $("#operForm");

	$("button[data-oper='list']").on("click", function(e){
    	operForm.submit();
  	});
});