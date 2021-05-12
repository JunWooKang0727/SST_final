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
<link href="../css/personalstudy.css" rel="stylesheet">
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
						<h1 class="h3 mb-0 text-gray-800">시험 정보 입력</h1>
					</div>
					<!-- Content Row -->
					<div class="row">
						<div class="col-lg-12">

							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h5 class="m-0 font-weight-bold text-color-sst">시험 정보 입력하기</h5>
								</div>
								<div class="card-body">
									<form action="/SST/ReportCard/insertSchoolTestAction.do" method="post"
										class="centerform">
										<input type="hidden" name="rc_num" value="${rc_num}">
										학년: <input type="number" name="st_year" placeholder="학년을 입력해주세요." class="form-control"  min="1" max="10" required><br> 
										학기: <input type="number" name="st_semester" placeholder="학기를 입력해주세요." class="form-control"   min="1" max="10" required><br> 
										시험명: <input type="text" name="st_test" placeholder="시험명을 입력해주세요." class="form-control" required><br> 
										시험 날짜:<input type="date" name="st_date" class="form-control" required> <br>
										<hr>
										과목1 : <input type="text" name="ss_name1" placeholder="과목명 입력해주세요." class="form-control" required><br>
										점수1 : <input type="number" name="ss_score1" placeholder="점수를 입력해주세요." class="form-control "  min="1" max="100" required>
										<br>
										<div id="plus_score_input">
										
										
										</div>
										 <a class="btn btn-success btn-circle" id="plus_score">
                                        <i class="fas fa-plus"></i>
                                    </a> 과목 점수 추가하기 
                                    <br><hr>
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