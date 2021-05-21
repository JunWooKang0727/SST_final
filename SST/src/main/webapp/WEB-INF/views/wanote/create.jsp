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
									<form action="/sst/wanote/create" method="post"
										class="centerform">
										<input type="hidden" name="m_id" value="ggy">
										제목: <input type="text" name="w_title" placeholder="노트제목 입력해주세요." class="form-control" required><br>
										문제:
										<textarea class="form-control" rows="3" name="w_question" placeholder="문제를 입력해주세요." required></textarea>  
										정답과 풀이:
										<textarea class="form-control" rows="3" name="w_answer" placeholder="정답과 풀이를 입력해주세요." required></textarea>  
										
										<hr>
										틀린 이유: 
											<select class="form-control " name="w_reason" id="w_reason" required>
											<option value="문제 파악 미흡">문제 파악 미흡</option>
											<option value="해당내용에 대한 이해 부족">해당내용에 대한 이해 부족</option>
											<option value="예상치 못한 문제">예상치 못한 문제</option>
											<option value="계산 실수">계산 실수</option>
											<option value="단순 실수">단순 실수</option>
										</select><br> 
										과목: <input type="text" name="w_subject" placeholder="과목명을 입력해주세요." class="form-control" required><br>
										해시태그:<br> <input type="text" name="tag" placeholder="EX) 도형넓이" class="form-control d-inline" id="tag-name" style="width:auto">
										<button type="button" class="btn btn-info d-inline" id="add-tag">추가</button><br>
										<div class="tag-list"></div>
										<hr>
                                    <button type="button" class="btn btn-info float-right" id="submit-btn">등록</button><br>
										
									</form>
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
			
		$(document).on("click", ".del-tag", function (e) {
            $(this).remove();
        });
			
			$('#submit-btn').click(function() {
				var html='';
				$('.tag-list').children().each(function(tag){
					html+='<input type="hidden" name="taglist['+$(this).index()+'].tg_name" value="'+$(this).text()+'">';
					})
				$('form').append(html).submit();
				})


		$('#tag-name').autocomplete({
			source : function(request, response) {
				$.ajax({
					url : '/sst/watag/listAllTag/'+request.term,
					dataType: "json",
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
		
	
	</script>

</body>
</html>