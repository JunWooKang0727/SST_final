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
<link href="../../../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../../../resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="../../../resources/css/studynote.css" rel="stylesheet">
<link>
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
						<h1 class="h3 mb-0 text-gray-800"></h1>
					</div>

					<!-- Content Row -->


					<div class="row">


						<div class="noteView">
							<div class="noteHeader">
								<h2>${studynote.sn_title}</h2>
								<hr>
								작성일임 :${studynote.sn_date}
							</div>
							<%-- 글번호 :${studynote.sn_num }<br>  --%>
							
							<div class="noteContents">
							  ${studynote.sn_contents}
							</div>
							
							<div class="noteFooter">
								<a class="btn btn-light btn-icon-split noteFooterBtn" href="StudyNote_ListAction.do">
								<span class="icon text-gray-600"><i class="fas fa-arrow-right"></i></span><span class="text">글 목록</span>
								</a>
								
								<a class="btn btn-primary btn-icon-split noteFooterBtn" 
								href="StudyNote_UpdateFormAction.do?sn_num=${studynote.sn_num}">
									<span class="icon text-white-50"><i class="fas fa-check"></i></span><span class="text">글 수정하기</span>
								</a> 
								
								<a class="btn btn-danger btn-icon-split noteFooterBtn" 
								href="StudyNote_DeleteAction.do?sn_num=${studynote.sn_num }">
									<span class="icon text-white-50"><i class="fas fa-trash"></i></span><span class="text">글 삭제</span>
								</a>
							</div>
					
			
							
						</div>
					</div>
					<!-- end of row -->


					<div class="row">

						<form action="StudyNote_ListAction.do" method="post">
							<input type="checkbox" name="area" value="title">제목 <input
								type="checkbox" name="area" value="writer">ID <input
								type="text" name="searchKey" size="10"> <input
								type="submit" value="검색">
						</form>

					</div>
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
	<script src="../../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="../../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="../../../resources/js/sb-admin-2.min.js"></script>


</body>
</html>












