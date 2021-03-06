<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
	<script src="../../../resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="../../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script
		src="../../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="../../../resources/js/sb-admin-2.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<title>SST</title>
<!-- Custom fonts for this template-->
<link href="../../../resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<!-- <link href="../../../resources/css/sb-admin-2.min.css" rel="stylesheet"> -->

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
				<link href="../../../resources/css/studydata.css" rel="stylesheet">
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div class="row goMain">
						<a href="/group/detail?g_num=${studyDataList.g_num}">Main??????</a>
					</div>
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">?????????</h1>
					</div>

					<!-- Content Row -->
					<div class="row headerLine"></div>
					<!-- end of row -->
					<div class="row">
						<div class="showCurPath">
							<span>?????? ??????</span> <span class="curP"></span>
						</div>
					</div>
					<div class="row">
						<div class="goParent btn-secondary"><i class="fas fa-arrow-up"></i></div>
					</div>
					<div class="row">
						
						<div class="createDirBtn btn btn-light"><span><img class='addicon' src='../../../resources/img/plus.png'></span>??? ??????</div>


						<div class="modal">
							<div class="modal_content" title="???????????? ?????? ????????????.">??? ?????? ?????????</div>
							<!-- <form class="dirNameForm" action="/studydata/create"
								method="POST"> -->
								<input type="text" name="dirName">

							<!-- </form> -->
							<div class="btnArea">
								<a href="" class="create">?????????</a><a href="" class="cancel">??????</a>
							</div>
						</div>

					</div>

					<div class="row">
						<div class="folderbox"></div>
					</div> 
					<div class="row">
						<div class="boxheader">
							<span class="header header_fname">?????????</span>
							<span class="header header_fuploader">????????? ID</span>
							<span class="header header_fregdate">???????????????</span>
							
						</div>
					</div>
					<div class="row">
						<div class="filebox"></div>
					</div>

					<!-- end of row -->

					<input type="hidden"
						value="<c:out value='${studyDataList.curPath}'/>" name="curPath">
					<input type="hidden"
						value="<c:out value='${studyDataList.g_num}'/>" name="g_num">
				
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


	<script type="text/javascript">
	
		
		$(function() {

			showUploadedFile();

			$('.filebox').on("dragover", dragOver).on("dragleave", dragOver)
					.on("drop", uploadFiles);
			
			//?????? ?????? (span) ?????? ?????????
			$('.filebox, .folderbox').on("click", ".deleteBtn", function(e) {
				e.stopPropagation();
				if($(this).data("ftype")==0){
					if(!confirm("?????? ?????? ?????? ?????? ????????? ???????????????\n?????????????????????????")){
						return;
					}
				}else{
					if(!confirm("?????? ?????????????????????????")){
						return;
					}
				}
				var csrfHeaderName = "${_csrf.headerName}";
				var csrfTokenValue = "${_csrf.token}";
				
				console.log("???????????? : "+csrfHeaderName);
				console.log("?????? : "+csrfTokenValue);
				
				var targetFile = $(this).data("file");
				//var type = $(this).data("type");
				var uuid = $(this).data("uuid");
				//console.log(targetFile);
				var ftype = $(this).data("ftype");
				var fname = $(this).data("fname");
				var fpath = $(this).data("path");
				var g_num = $(this).data("gnum");
				$.ajax({

					url : '/studydata/deleteFile',
					beforeSend:function(xhr){
						xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
					},
					data : {
						fileCallPath : targetFile,
						uuid : uuid,
						fileType : ftype,
						fileName : fname,
						uploadPath : fpath,
						g_num : g_num
					},
					dataType : 'text',
					type : 'POST',
					success : function(result) {
						console.log("????????????");
						alert(result);
						showUploadedFile();
					},
					error:function(xhr,status,error){
						console.log('error:'+error);
					}

				});//end of ajax

			});//end of span on click event

		});

		//????????? ??? ?????? (??????)
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
			formData.append("g_num", $("input[name=g_num]").val());
			formData.append("curPath", $("input[name=curPath]").val());
		//	formData.append("${_csrf.parameterName}","${_csrf.token}");
			var csrfHeaderName = "${_csrf.headerName}";
			var csrfTokenValue = "${_csrf.token}";
			console.log("${_csrf.parameterName}");
			console.log("${_csrf.token}");
			
			if (confirm("????????? ???????????????")) {
				$.ajax({
					url : '/studydata/upload',
					
					processData : false,
					contentType : false,
					
					data : formData,
					type : 'POST',
 					beforeSend:function(xhr){
						
						xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
					}, 
					//dataType:'json',
					success : function() {
						alert("Uploaded");
						console.log("????????? ??????");
						showUploadedFile();
					},
					error:function(xhr,status,error){
						console.log("1"+csrfHeaderName);
						console.log("2??????"+csrfTokenValue);
						console.log('error:'+error);
						console.log('status:'+status);
					}

				});// end of ajax
			} else {
				return;
			}

		}//end of uploadFiles

		function showUploadedFile(cp) {
			
			var str = "";
			$('.filebox').empty();
			var curP = "";
			
			if(cp == null){
				curP =	$("input[name=curPath]").val();
			}else{
				curP = cp;
			}
			
			$('.curP').html(curP);
			
			$.ajax({
				url : '/studydata/list',
				dataType : 'json',
				type : 'GET',
				data : {
						"curPath" : curP,
						"g_num" : $("input[name=g_num]").val()
						},
				success : function(data) {
							$('.filebox').empty();
							$('.folderbox').empty();
							$(data).each(function(i, obj) {

								var fileCallPath = encodeURIComponent(obj.uploadPath
									+ "/"+ obj.uuid+ "_"+ obj.fileName);
								if (obj.fileType == true) {
									
									str += "<div class='fileObj'><a class='file btn btn-light' href='/studydata/download?fileName="
										+ fileCallPath+ "'><span><img class='fileicon' src='../../../resources/img/file.png'></span><span>"+ obj.fileName+ "</span></a>";
										
									str +="<span class='uploader'>"+obj.uploader+"</span>";
									str +="<span class='regdate'>"+moment(obj.regdate).format('YYYY-MM-DD')+"</span>";
										
									str += "<span class='btn deleteBtn' data-file=\'"+fileCallPath
									+"\' data-uuid=\'"+obj.uuid+"\' data-path=\'"+obj.uploadPath+"\'"
									+"data-ftype=\'"+obj.fileType+"\'> ?????? </span></div>";

									$('.filebox').append(str);
									str = "";

								} else if (obj.fileType == 0) {
									str += "<div class='dirObj card border-left-primary shadow'><a class='dir btn btn-light' href='#'><span><img class='fileicon' src='../../../resources/img/folder.png'></span><span>"+ obj.fileName+ "</span></a>";
									str += "<span class='btn deleteBtn' data-file=\'"+fileCallPath
									+"\' data-uuid=\'"+obj.uuid+"\' data-path='"+obj.uploadPath
									+"' data-fname=\'"+obj.fileName+"\'"
									+"data-ftype=\'"+obj.fileType+"\'"
									+"data-gnum=\'"+obj.g_num+"\'> ?????? </span></div>";
									$('.folderbox').append(str)
									str = "";
								}//end of if

								//console.log(fileCallPath);
							});//end of each (data)

						
						}//end of success
					}); //end of ajax

		}// end of showUploadedFile

		//????????? ????????? ??????
		$(function() {
			
			
			$(".createDirBtn").click(
					function() {
						var div = $(".modal")

						div.css("position", "absolute");
						div.css("top", Math.max(0, (($(window).height() - div
								.outerHeight()) / 2)
								+ $(window).scrollTop())
								+ "px");
						div.css("left", Math.max(0, (($(window).width() - div
								.outerWidth()) / 2)
								+ $(window).scrollLeft())
								+ "px");
						div.fadeIn();
					});
			
			$(".modal_content").click(
				function(){
					$('.modal').fadeOut();
				}		
			)
			$(".create").click(function(e) {
				e.preventDefault();
				e.stopPropagation();

				$(".modal").fadeOut();
				/* var csrfHeaderName = "${_csrf.headerName} ";
				var csrfTokenValue = "${_csrf.token}"; */
				var csrfHeaderName = "${_csrf.headerName}";
				var csrfTokenValue = "${_csrf.token}";
				if ($("input[name=dirName]").val().trim() == "") {
					alert("???????????? ???????????????");
					$("input[name=dirName]").val('');
					return;
				}
				
				
				$.ajax({
					url : "/studydata/create",
					beforeSend:function(xhr){
						console.log('dfafsf');
						xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
					},
					data : {
						"dirName" : $("input[name=dirName]").val(),
						"curPath" : $("input[name=curPath]").val(),
						"g_num" : $("input[name=g_num]").val(),
						
					},
					type : 'POST',
					success : function() {
						$("input[name=dirName]").val('');
						console.log('??????');
						showUploadedFile();
					},
					error:function(xhr,status,error){
						console.log('error:'+error);
						console.log('status:'+status);
						console.log("???????????? : "+csrfHeaderName);
						console.log("?????? : "+csrfTokenValue);
						
					}

				});//end of ajax

			});//end of create click event

			$(".cancel").click(function(e) {
				e.preventDefault();
				$(".modal").fadeOut();
			});
			
		}); // end of $(function())

		//???????????? ?????? ??????
		$(function() {

			$('.folderbox').on("click","a",function(e) {
							e.preventDefault();

							var cp = $(this).next().data('fname');
							var uuid = $(this).next().data('uuid');
							cp = $('input[name=curPath]').val() + "\\" + cp;
							console.log(cp);
							
							showUploadedFile(cp);
							$('input[name=curPath]').val(cp);
							
							
										
			});//?????? ?????? (a ?????? ?????????) ???

			//???????????? ?????????
			$('.goParent').on("click",function(){
				
				var cur = $('input[name=curPath]').val();
				
				
				if(cur.lastIndexOf("\\") > 0){
					var prev = cur.substr(0,cur.lastIndexOf("\\"));
					console.log(prev);
					showUploadedFile(prev);
					$('input[name=curPath]').val(prev);
				}else{
					alert("?????? ????????? ???????????????");
					return;
				}
				
				
			});//goParent ?????? ????????? ???		
							
							
							
		}); //end of $(function())
		
		$(function(){
			
			
			
		});
		
	</script>

</body>
</html>



