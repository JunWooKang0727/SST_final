$(function() {
	$("#rc_typeDirect").hide();
	$("#rc_type").change(function() {
		//직접입력을 누를 때 나타남
		if ($("#rc_type").val() == "etc" || $("#rc_type").val() == "License" || $("#rc_type").val() == "Language") {
			$("#rc_typeDirect").show();
			$("#rc_typeDirect").attr('required', true);

		} else {
			$("#rc_typeDirect").hide();
		}
	})
	
	var test=2;

	$('#plus_score').click(function() { //자바스크립트 이벤트 발생
		
		if($('#plus_score_input').children().length<9){
			var html='<div><hr>과목'+test+': <input type="text" name="ss_name'+test+'" placeholder="과목명을 입력해주세요." class="form-control" required><br>';
			html+='점수: <input type="number" name="ss_score'+test+'" placeholder="점수를 입력해주세요." class="form-control "  min="1" max="100" required><br></div>';
			$('#plus_score_input').append(html);
		}
		else{
			alert('성적은 최대 10개까지 등록 가능합니다.');
		}
		test++;
	})
	
	var test2=2;
	
	$('#plus_licensescore').click(function() { //자바스크립트 이벤트 발생
		
		if($('#plus_score_input').children().length<9){
			var html='<div><hr>과목'+test2+': <input type="text" name="ls_name'+test2+'" placeholder="과목명을 입력해주세요." class="form-control" required><br>';
			html+='획득 점수: <input type="number" name="ls_score'+test2+'" placeholder="점수를 입력해주세요." class="form-control "  min="1" max="100" required><br>';
			html+='목표 점수 : <input type="number" name="ls_goal'+test2+'" placeholder="목표점수를 입력해주세요." class="form-control "  min="1" max="500" required><br></div>';
			$('#plus_score_input').append(html);
		}
		
		else{
			alert('성적은 최대 10개까지 등록 가능합니다.');
		}
		test2++;
	})
});