$(document).ready(function(){
	
	$('.groupsecrets').on("click", function(){
		var value = $('.groupsecrets:checked').val();
		if(value === 'open'){
			$('#inputgrouppw').val('');
			$('#inputgrouppw').attr('readonly', true);
		} else if(value === 'secret'){
			$('#inputgrouppw').attr('readonly', false);
		}
	}); // groupsecrets 끝
})