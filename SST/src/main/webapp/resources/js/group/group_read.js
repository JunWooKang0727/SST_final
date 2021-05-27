$(document).ready(function(){
	
	var csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
	var csrfTokenValue =  $("meta[name='_csrf']").attr("content");
	var modalAccept = $('#mem_accepts');
	var modalDeny = $('#mem_deny');
	
	var m_id = "";
	var g_num = "";

	console.log(csrfHeaderName);
	console.log(csrfTokenValue);
	$('#updatebutton').on("click", function(e){
		self.location="/group/update";
	}); // #updateBtn end

	$('#exampleModal2').on('show.bs.modal', function(e){
		m_id = $(e.relatedTarget).data("mid");
		g_num = $(e.relatedTarget).data("gnum");
		console.log(m_id);
		console.log(g_num);
	});
	
	$('#exampleModal').on('show.bs.modal', function(e){
		m_id = $(e.relatedTarget).data("mid");
		g_num = $(e.relatedTarget).data("gnum");
		alert(m_id);
		alert(g_num);
	}); // 거절 모달창 로드시 변수 저장
	
	$('#mem_accepts').on("click", function(e){
		memaccept(m_id, g_num);
		alert(m_id);
		$(".modal").parent('#exampleModal2').modal("hide");
	});
	
	$('#mem_deny').on("click", function(e){
		memdeny(m_id, g_num);
		$(".modal").modal("hide");
	}); // 모달창 거절 버튼 클릭 이벤트

	$('#mem_modify').on("click", function(e){
		alert("ddd");
		var gnum = $('#mem_modify').data("gnum");
		var mid = $('#mem_modify').data("mid");
		console.log(gnum + " &&& " + mid);
		var pgrant = $('#auth option:selected').val();
		memmodify(gnum, mid, pgrant);
	})
	
	$('#mem_delete').on("click", function(e){
		var gnum = $('#mem_delete').data("gnum");
		var mid = $('#mem_delete').data("mid");
		console.log(gnum + " & " + mid);
		memdelete(mid, gnum);
	})
	
	function memdelete(m_id, g_num){
		$.ajax({
			url : '/group/memdelete',
			dataType : 'text',
			type : 'post',
			data : {
				"m_id" : m_id,
				"g_num" : g_num,
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			success: function() {
				alert.log("멤버삭제완료");
			},
			error : function(xhr, status, error) {
                alert("error. status : " + status + ", xhr : " + xhr + ", error : "+ error);
			}
		})
	}
	
	function memmodify(m_id, g_num, p_grant) {
		alert(m_id);
		$.ajax({
			url : '/group/authupdate',
			dataType : 'text',
			type : 'post',
			data : {
				"m_id" : m_id,
				"g_num" : g_num,
				"p_grant" : p_grant
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			success: function() {
				alert.log("권한수정완료");
			},
			error : function(xhr, status, error) {
                alert("error. status : " + status + ", xhr : " + xhr + ", error : "+ error);
			}
			
		})
	}
	
	function memdeny(m_id, g_num){
		alert(m_id);
		$.ajax({
			url : '/group/memdelete',
			dataType : 'text',
			type : 'post',
			data : {
				"m_id" : m_id,
				"g_num" : g_num
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			success: function() {
				console.log("삭제완료");
			},
			error : function(xhr, status, error) {
                alert("error. status : " + status + ", xhr : " + xhr + ", error : "+ error);
			}
			
		})
	}

	/*function memaccept(m_id, g_num){
		alert(m_id);
		$.ajax({
			url : '/group/accept',
			dataType : 'text',
			type : 'post',
			data : {
				"m_id" : m_id,
				"g_num" : g_num
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			success: function() {
				alert("수락완료");
				
			},
			error : function(xhr, status, error) {
                alert("error. status : " + status + ", xhr : " + xhr + ", error : "+ error);
			}
		})
	}*/
});