<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SST</title>
<!-- Custom fonts for this template-->
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="../css/personalstudy.css" rel="stylesheet">
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- sidemenu -->
		<%@include file="../../template/sidemenu.jsp"%>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- topbar -->
				<%@include file="../../template/topbar.jsp"%>
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">성적 관리</h1>
					</div>
					<!-- Content Row -->
					<div class="row">
						<div class="col-lg-12">

							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h5 class="m-0 font-weight-bold text-color-sst">성적란 생성하기</h5>
								</div>
								<div class="card-body">
									<form action="/SST/ReportCard/insertReportCardAction.do" method="post"
										class="centerform">
										타이틀: <input type="text" name="rc_title" placeholder="입력해주세요." class="form-control" required><br> 
											성적 유형: 
											<select class="form-control " name="rc_type" id="rc_type" required>
											<option value="초등학교성적">초등학교 성적</option>
											<option value="중학교성적">중학교 성적</option>
											<option value="고등학교성적">고등학교 성적</option>
											<option value="License">자격증</option>
											<option value="Language">어학시험</option>
											<option value="etc">기타</option>
										</select><br> 
										
										<input type="text" id="rc_typeDirect" name="rc_subtype" class="form-control" placeholder="시험명을 입력해 주세요."/><br>
										<input class="btn btn-info" type="submit" value="등록하기">

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
			<%@include file="../../template/footer.jsp"%>
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
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="../js/personalstudy.js"></script>
	<script src="../js/sb-admin-2.min.js"></script>
</body>
</html>