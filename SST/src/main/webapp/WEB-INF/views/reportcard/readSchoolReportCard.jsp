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
<script src="https://www.gstatic.com/charts/loader.js"></script>


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
						<h1 class="h3 mb-0 text-gray-800">성적 관리</h1>
						<input type="hidden" id="rc-num" value="${reportcard.rc_num}">
					</div>
					<!-- Content Row -->
					<div class="row">
						<div class="col-lg-12">
							<div class="card shadow col-xl-12 mb-4">
								<div class="card-header py-3">
									<h5 class="m-0 font-weight-bold">성적 조회</h5>
								</div>
								<div class="card-body">
									<div class="col-lg-2">
										<select class="form-control " id="chart-change">
											<option value="평균">평균</option>
											<option value="전체과목">전체과목</option>
										</select>
									</div>
									<div id="curve_chart" style="width: 100%; height: 500px;"></div>
									
								</div>
							</div>
						</div>

						<div class="col-lg-6">
							<div class="card shadow  mb-4">
								<div class="card-header py-3">
									<h5 class="m-0 font-weight-bold">최근 시험그룹 추천</h5>
								</div>
								<div class="card-body">
									
									추천1
									
								</div>
							</div>
						</div>

						<div class="col-lg-6">
							<div class="card shadow  mb-4">
								<div class="card-header py-3">
									<h5 class="m-0 font-weight-bold">성적 낮은 과목 추천</h5>
								</div>
								<div class="card-body">
									  
									  추천2
									  
									  
								</div>
							</div>
						</div>
						<div class="col-lg-12">
							<div class="card shadow mb-4">
								<!-- Card Header - Accordion -->
								<a href="#collapseCardExample" class="d-block card-header py-3 collapsed"
									data-toggle="collapse" role="button" aria-expanded="false"
									aria-controls="collapseCardExample">
									<h5 class="m-0 font-weight-bold text-gray-700">성적 자세히 보기</h5>
								</a>
								<!-- Card Content - Collapse -->
								<div class="collapse show" id="collapseCardExample">
									<div class="card-body">
									<c:forEach items="${schoolTestList}" var="st">
									<strong>${st.st_year }학년  ${st.st_semester }학기  ${st.st_test } (${st.st_date })</strong>
									<a href="/reportcard/schooltest/update?st_num=${st.st_num}" class="float-right">수정</a> 
									<table class="table table-bordered">
									<tr>
										<c:forEach items="${st.scorelist }" var="score">
											<th>${score.ss_name }</th>
										</c:forEach>
									</tr>
									<tr>
									<c:forEach items="${st.scorelist }" var="score">
											<td>${score.ss_score }</td>
										</c:forEach>
									</tr>
									</table>
									<hr/>
									</c:forEach>
									<a class="btn btn-success btn-circle" href="/reportcard/schooltest/create?rc_num=${rc_num}">
                                        <i class="fas fa-plus"></i>
                                    </a> 성적 추가하기 
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
	<script src="/resources/js/school_chart.js"></script>
	<script type="text/javascript">
  	$(function(){
 		chart.drawAverage(); 
		$("#chart-change").change(function() {
			if ($("#chart-change").val() == "전체과목") {
				chart.drawAllScore();
			}else{
				chart.drawAverage();
			}
		})
	})  
	
	</script>

</body>