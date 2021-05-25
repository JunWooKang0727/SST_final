<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>SST</title>
<link href="/sst/resources/css/wanote.css" rel="stylesheet">
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- sidemenu -->

		<%@include file="../template/sidemenu.jsp"%>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- topbar -->
				<%@include file="../template/topbar.jsp"%>
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">오답노트 입력</h1>
					</div>
					<!-- Content Row -->
					<div class="row">
						<div class="col-lg-12">

							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h5 class="m-0 font-weight-bold text-color-sst">오답노트 입력하기</h5>
								</div>
								<div class="card-body">
									<form action="/sst/wanote/update" method="post"
										class="centerform">
										<input type="hidden" name="m_id" value="ggy">
										<input type="hidden" name="w_num" value="${wanote.w_num}">
										제목: <input type="text" name="w_title" value="${wanote.w_title}" class="form-control" required><br>
										문제:
										<textarea class="form-control" rows="3" name="w_question" required>${wanote.w_question}</textarea>  
										정답과 풀이:
										<textarea class="form-control" rows="3" name="w_answer" required>${wanote.w_answer}</textarea>  
										
										<hr>
										틀린 이유: 
											<select class="form-control " name="w_reason" id="w_reason" required>
											<option value="문제 파악 미흡" <c:out value="${wanote.w_reason eq '문제 파악 미흡'?'selected':''}"/> >문제 파악 미흡</option>
											<option value="해당내용에 대한 이해 부족" <c:out value="${wanote.w_reason eq '해당내용에 대한 이해 부족'?'selected':''}"/>  >해당내용에 대한 이해 부족</option>
											<option value="예상치 못한 문제" <c:out value="${wanote.w_reason eq '예상치 못한 문제'?'selected':''}"/> >예상치 못한 문제</option>
											<option value="계산 실수" <c:out value="${wanote.w_reason eq '계산 실수'?'selected':''}"/> >계산 실수</option>
											<option value="단순 실수" <c:out value="${wanote.w_reason eq '단순 실수'?'selected':''}"/> >단순 실수</option>
										</select><br> 
										과목: <input type="text" name="w_subject" value="${wanote.w_subject}" class="form-control" required><br>
										해시태그:<br> <input type="text" name="tag" placeholder="EX) 도형넓이" class="form-control d-inline" id="tag-name" style="width:auto">
										<button type="button" class="btn btn-info d-inline" id="add-tag">추가</button><br>
										<div class="tag-list">
										<c:forEach items="${wanote.taglist}" var="tag">
								<a><c:out value="${tag.tg_name}" /></a>
								</c:forEach>
										</div>
										
										<hr>
										<div class="float-right">

										 <button type="button" class="btn btn-info" id="submit-btn">수정</button>
										  <button type="button" class="btn btn-danger" id="submit-btn">삭제</button>
										
										</div>
                                   
										
									</form>
									<br><br>
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-default">

												<div class="panel-heading">File Attach</div>
												<!-- /.panel-heading -->
												<div class="panel-body">
													<div class="form-group uploadDiv">
														<input type="file" name='uploadFile' multiple>
													</div>

													<div class='uploadResult'>
														<ul>

														</ul>
													</div>


												</div>
												<!--  end panel-body -->

											</div>
											<!--  end panel-body -->
										</div>
										<!-- end panel -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->
			<!-- Footer -->
			<%@include file="../template/footer.jsp"%>

			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>
	


<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- Custom scripts for all pages-->
	<script type="text/javascript">
			var tagList = {};

			$('#add-tag').click(function() {
				if($('#tag-name').val()==''){
					alert('값을 입력해주세요');
				}else{
					
					var html="<a class='del-tag'>"+$('#tag-name').val()+"</a>";
					$('.tag-list').append(html);
					$('#tag-name').val('');
				}
			})
			

		
		$(document).on("click", ".del-tag", function(e) {
			$(this).remove();
		});

		$('#submit-btn').click(
				function() {
					var html = '';
					$('.tag-list').children().each(
							function(tag) {
								html += '<input type="hidden" name="taglist['
										+ $(this).index()
										+ '].tg_name" value="' + $(this).text()
										+ '">';
							})
					var str = "";

					$(".uploadResult ul li").each(
							function(i, obj) {

								var jobj = $(obj);

								console.dir(jobj);
								console.log("-------------------------");
								console.log(jobj.data("filename"));

								str += "<input type='hidden' name='attachList["
										+ i + "].fileName' value='"
										+ jobj.data("filename") + "'>";
								str += "<input type='hidden' name='attachList["
										+ i + "].uuid' value='"
										+ jobj.data("uuid") + "'>";
								str += "<input type='hidden' name='attachList["
										+ i + "].uploadPath' value='"
										+ jobj.data("path") + "'>";
								str += "<input type='hidden' name='attachList["
										+ i + "].fileType' value='"
										+ jobj.data("type") + "'>";

							});

					console.log(str);

					$('form').append(html).append(str).submit();
				})

		$('#tag-name').autocomplete({
			source : function(request, response) {
				$.ajax({
					url : '/sst/watag/listAllTag/' + request.term,
					dataType : "json",
					type : 'GET',
					success : function(data) {
						response($.map(data, function(item) {
							return {
								label : item.tg_name,
								value : item.tg_name
							}
						})) //end response 

					}
				}); //$.ajax
			}, //end source
			minLength : 1
		})

$(document).ready(function() {
  (function(){
    
    var w_num = '<c:out value="${wanote.w_num}"/>';
    
    $.getJSON("/sst/wanote/getAttachList", {w_num: w_num}, function(arr){
    
      console.log(arr);
      
      var str = "";


      $(arr).each(function(i, attach){
          
          //image type
          if(attach.fileType){
            var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/"+attach.uuid +"_"+attach.fileName);
            console.log("dkdkdkdkdkd"+fileCallPath);
            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
            str +=" data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
            str += "<span> "+ attach.fileName+"</span>";
            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' "
            str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
            str += "<img height='250' src='/sst/wanoteAttach/display?fileName="+fileCallPath+"'>";
            str += "</div>";
            str +"</li>";
          }else{
              
            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
            str += "data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
            str += "<span> "+ attach.fileName+"</span><br/>";
            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' "
            str += " class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
            str += "<img src='/sst/resources/img/attach.png'></a>";
            str += "</div>";
            str +"</li>";
          }
       });

      
      $(".uploadResult ul").html(str);
      
    });//end getjson
  })();//end function
  
  
  
  var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
  var maxSize = 5242880; //5MB
  
  function checkExtension(fileName, fileSize){
    
    if(fileSize >= maxSize){
      alert("파일 사이즈 초과");
      return false;
    }
    
    if(regex.test(fileName)){
      alert("해당 종류의 파일은 업로드할 수 없습니다.");
      return false;
    }
    return true;
  }
  
  $("input[type='file']").change(function(e){

    var formData = new FormData();
    
    var inputFile = $("input[name='uploadFile']");
    
    var files = inputFile[0].files;
    
    for(var i = 0; i < files.length; i++){

      if(!checkExtension(files[i].name, files[i].size) ){
        return false;
      }
      formData.append("uploadFile", files[i]);
      
    }
    
    $.ajax({
      url: '/sst/wanoteAttach/uploadAjaxAction',
      processData: false, 
      contentType: false,data: 
      formData,type: 'POST',
      dataType:'json',
        success: function(result){
          console.log(result); 
		  showUploadResult(result); //업로드 결과 처리 함수 

      }
    }); //$.ajax
    
  });    

  function showUploadResult(uploadResultArr){
	    
    if(!uploadResultArr || uploadResultArr.length == 0){ return; }
    
    var uploadUL = $(".uploadResult ul");
    
    var str ="";
    
    $(uploadResultArr).each(function(i, obj){
		
		if(obj.image){
			var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
			str += "<li data-path='"+obj.uploadPath+"'";
			str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'"
			str +" ><div>";
			str += "<span> "+ obj.fileName+"</span>";
			str += "<button type='button' data-file=\'"+fileCallPath+"\' "
			str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
			str += "<img src='/sst/wanoteAttach/display?fileName="+fileCallPath+"'>";
			str += "</div>";
			str +"</li>";
		}else{
			var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);			      
		    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
		      
			str += "<li "
			str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
			str += "<span> "+ obj.fileName+"</span>";
			str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' " 
			str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
			str += "<img src='/sst/resources/img/attach.png'></a>";
			str += "</div>";
			str +"</li>";
		}

    });
    
    uploadUL.append(str);
  }
  
  $(".uploadResult").on("click", "button", function(e) {

		console.log("delete file");

		var targetFile = $(this).data("file");

		var targetLi = $(this).closest("li");

		$.ajax({
			url : '/sst/wanoteAttach/deleteFile',
			data : {
				fileName : targetFile,
			},
			dataType : 'text',
			type : 'POST',
			success : function(result) {
				alert(result);

				targetLi.remove();
			}
		}); //$.ajax
	});
  
});

	</script>

</body>
</html>