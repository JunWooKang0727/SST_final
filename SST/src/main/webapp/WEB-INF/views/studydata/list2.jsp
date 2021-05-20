<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>SST</title>
<!-- Custom fonts for this template-->
<link href="../../../resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../../../resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="../../../resources/css/studydata.css" rel="stylesheet">
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
						<h1 class="h3 mb-0 text-gray-800">자료실</h1>
					</div>

					<!-- Content Row -->
					<div class="row headerLine"></div>
					<!-- end of row -->
					<div class="row">
						<div class="showCurPath"></div>
					</div>

					<div class="row">

						<div class="createDirBtn">새 폴더</div>


						<div class="modal">
							<div class="modal_content" title="클릭하면 창이 닫힙니다.">
								새 폴더 만들기
							</div>
							<form class="dirNameForm" action="/studydata/create" method="POST">
								<input type="text" name="dirName">
							</form>
							<div class="btnArea">
								<a href="" class="create">만들기</a><a href="" class="cancel">취소</a>
							</div>
						</div>

					</div>


					<div class="row filebox"></div>
					<!-- end of row -->



					<!-- end of row -->

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

	<!-- Bootstrap core JavaScript-->
	<script src="../../../resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="../../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script
		src="../../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="../../../resources/js/sb-admin-2.min.js"></script>

	<script type="text/javascript">
		$(function() {

			showUploadedFile();

			$('.filebox').on("dragover", dragOver).on("dragleave", dragOver)
					.on("drop", uploadFiles);

			$('.filebox').on("click", "span", function(e) {

				var targetFile = $(this).data("file");
				//var type = $(this).data("type");
				var uuid = $(this).data("uuid");
				console.log(targetFile);

				$.ajax({

					url : '/studydata/deleteFile',
					data : {
						fileName : targetFile,
						uuid : uuid
					},
					dataType : 'text',
					type : 'POST',
					success : function(result) {
						alert(result);
						showUploadedFile();
					}

				});//end of ajax

			});//end of span on click event

		});

		function dragOver(e) {
			e.stopPropagation();
			e.preventDefault();

			if (e.type == "dragover") {
				$(e.target).css({
					"background-color" : "black",
					"outline-offset" : "-20px"
				});
			} else {
				$(e.target).css({
					"background-color" : "gray",
					"outline-offset" : "-10px"
				});
			}
		}

		function uploadFiles(e) {
			e.stopPropagation();
			e.preventDefault();

			dragOver(e); //1

			e.dataTransfer = e.originalEvent.dataTransfer; //2
			var files = e.target.files || e.dataTransfer.files;

			console.log(files);

			var formData = new FormData();

			for (var i = 0; i < files.length; i++) {
				formData.append("uploadFile", files[i]);
			}

			if (confirm("파일을 등록할까요")) {
				$.ajax({
					url : '/studydata/uploadAjax',
					processData : false,
					contentType : false,
					data : formData,
					type : 'POST',

					success : function(result) {
						alert("Uploaded");

						showUploadedFile();
					}

				});// end of ajax
			} else {
				return;
			}

		}//end of uploadFiles

		function showUploadedFile() {

			var str = "";
			$('.filebox').empty();

			$
					.ajax({

						url : '/studydata/list',
						dataType : 'json',
						type : 'GET',
						data : {
							"g_num" : "1"
						},
						success : function(data) {

							$(data)
									.each(
											function(i, obj) {

												var fileCallPath = encodeURIComponent(obj.uploadPath
														+ "/"
														+ obj.uuid
														+ "_"
														+ obj.fileName);

												str += "<div class='fileObj'><a href='/studydata/download?fileName="
														+ fileCallPath
														+ "'>"
														+ obj.fileName
														+ "</a><br>";
												str += "<span class='deleteBtn' data-file=\'"+fileCallPath+"\' data-uuid=\'"+obj.uuid+"\'> 삭제 </span></div>";

												console.log("실행됨");
											});

							$('.filebox').append(str);
						}
					}); //end of ajax

		}// end of showUploadedFile

		
		//새폴더 모달창 관련
		$(function() {
			$(".createDirBtn").click(function() {
				var div = $(".modal")
				
				
				div.css("position", "absolute");
				div.css("top", Math.max(0, (($(window).height() - div.outerHeight()) / 2) + $(window).scrollTop()) + "px");
				div.css("left", Math.max(0, (($(window).width() - div.outerWidth()) / 2) + $(window).scrollLeft()) + "px");
				div.fadeIn();
			});
			$(".modal_content").click(function() {
				$(".modal").fadeOut();
			});
			
			$(".create").click(function(e){
				e.preventDefault();
				
				$(".modal").fadeOut();
				
				if($("input[name=dirName]").val().trim()==""){
					alert("폴더명을 입력하세요");
					$("input[name=dirName]").val('');
					return;
				}
				
				$(".dirNameForm").submit();
				$("input[name=dirName]").val('');
			});
			
			
			$(".cancel").click(function(e){
				e.preventDefault();
				$(".modal").fadeOut();
			});
		});

		
	</script>

</body>
</html>

