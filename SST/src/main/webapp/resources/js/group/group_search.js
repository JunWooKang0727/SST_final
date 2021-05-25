$(document).ready(function(){
	var actionForm = $("#actionForm");
	var searchForm = $("#searchForm");
	
	$(".pagi-btn a").on("click", function(e){
		e.preventDefault();
		console.log("click");
		actionForm.find("input[name='pageNum']").val($(this)
			.attr("href"));
		actionForm.submit();
	});
	

	$(".moveDetail").on("click", function(e){
		e.preventDefault();
		actionForm.append("<input type='hidden' name='g_num' value='" + 
				$(this).attr("href")+"'>");
		actionForm.attr("action", "/group/selectdetail");
		actionForm.submit();
		
	}); // moveDetail end
	
	$("#searchForm button").on("click", function(e){
		if(!searchForm.find("option:selected").val()){
			alert("검색 종류를 선택하세요");
			return false;
		}

		if(!searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하세요");
			return false;
		}

		searchForm.find("input[name='pageNum']").val("1");
		e.preventDefault();

		searchForm.submit();
	}); // search button end
});