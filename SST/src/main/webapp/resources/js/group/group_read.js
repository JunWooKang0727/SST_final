$(document).ready(function(){
	
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
	var m_id = "";
	var g_num = "";
	
	$('#updatebutton').on("click", function(e){
		self.location="/group/update";
	}); // #updateBtn end

	$('#exampleModal').on('show.bs.modal', function(e){
		m_id = $(e.relatedTarget).data(mid);
		g_num = $(e.relatedTarget).data(gnum);
		alert(m_id);
		alert(g_num);
	}); // 거절 모달창 로드시 변수 저장

	$('#exampleModal2').on('show.bs.modal', function(e){
		m_id = $(e.relatedTarget).data(mid);
		g_num = $(e.relatedTarget).data(gnum);
	}); // 승인 모달창 로드시 변수 저장

	$('#mem_accepts').on("click", function(e){
		memaccept(m_id, g_num);
		alert(m_id + "그룹번호" + g_num)
	}); // 모달창 수락 버튼 클릭 이벤트

	$('#mem_deny').on("click", function(e){
		memdeny(m_id, g_num);
	}); // 모달창 거절 버튼 클릭 이벤트

	function memdeny(m_id, g_id){
		$.ajax({
			url : '/group/memdeny',
			dataType : 'text',
			type : 'post',
			data : {
				"m_id" : m_id,
				"g_id" : g_id
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			success: function() {
				alert("삭제완료");
			}
		})
	}

	function memaccept(m_id, g_id){
		$.ajax({
			url : '/group/memaccept',
			dataType : 'text',
			type : 'post',
			data : {
				"m_id" : m_id,
				"g_id" : g_id
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			success: function() {
				alert("수락완료");
			}
		})
	}
});