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
<!-- Custom styles for this template-->
<link href="/sst/resources/css/personalstudy.css" rel="stylesheet">
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
						<h1 class="h3 mb-0 text-gray-800">시험 정보 입력</h1>
					</div>
					<!-- Content Row -->
					<div class="row">
						<div class="col-lg-12">
							<div class="card shadow mb-4 col-lg-12">
								<div class="card-header py-3">
									<h5 class="m-0 font-weight-bold text-color-sst">시험 정보 입력하기</h5>
								</div>
								<div class="card-body">
									<form action="/sst/reportcard/schooltest/update" method="post"
										class="centerform">
										<input type="hidden" name="rc_num" value="${st.rc_num}">
										<input type="hidden" name="st_num" value="${st.st_num}">
										학년: <input type="number" name="st_year" value="${st.st_year}" class="form-control"  min="1" max="10" required><br> 
										학기: <input type="number" name="st_semester" value="${st.st_semester}" class="form-control"   min="1" max="10" required><br> 
										시험명: <input type="text" name="st_test" value="${st.st_test}" class="form-control" required><br> 
										시험 날짜:<input type="date" name="st_date" class="form-control" value="${st.st_date}" required> <br>
										<c:forEach items="${st.scorelist}" var="score" varStatus="status">
										<hr>
										<input type="hidden" name="scorelist[${status.count-1 }].ss_num" value="${score.ss_num}">
										과목${status.count } : <input type="text" name="scorelist[${status.count-1 }].ss_name" value="${score.ss_name}"  class="form-control" required><br>
										점수 : <input type="number" name="scorelist[${status.count-1 }].ss_score" value="${score.ss_score}" class="form-control "  min="1" max="100" required>
										<br>
										<a class="float-right text-danger" href="/sst/reportcard/schoolscore/delete?st_num=${st.st_num}&ss_num=${score.ss_num}">삭제</a>
										<br>
										</c:forEach>
										<a class="btn btn-success btn-circle" id="plus_score" data-toggle="modal" data-target="#exampleModal"> <i
											class="fas fa-plus"></i>
										</a> 과목 점수 추가하기 <br><hr>
										<input class="btn btn-info" type="submit" value="수정">
										<button type="button" class="btn btn-danger"
													onclick="location.href='/sst/reportcard/schooltest/delete?rc_num=${st.rc_num}&st_num=${st.st_num}'">삭제</button>
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

	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">성적 추가</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
					</button>
				</div>
				<form action="/sst/reportcard/schoolscore/create" method="post">
				<div class="modal-body">
						<input type="hidden" name="st_num" value="${st.st_num}">
						과목 : <input type="text" name="ss_name" placeholder="과목명 입력해주세요." class="form-control" required><br> 
						점수 : <input type="number" name="ss_score" placeholder="점수를 입력해주세요." 
							class="form-control " min="1" max="100" required>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
					<input type="submit" class="btn btn-primary" value="추가"/>
				</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>