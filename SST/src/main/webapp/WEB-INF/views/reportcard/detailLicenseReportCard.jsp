<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
<script src="https://www.gstatic.com/charts/loader.js"></script>


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
						<h1 class="h3 mb-0 text-gray-800">성적 관리  </h1>
						
						<input type="hidden" id="rc-num" value="${rc_num}">
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
											<option value="최근시험">최근시험</option>
											<option value="전체시험">전체시험</option>
										</select>
									</div>
									
									<div id="curve_chart" style="width: 100%; height: 600px;">
									<canvas id="myChart"></canvas>
									</div>
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
									<c:forEach items="${ltlist}" var="test">
									<strong>${test.lt_test}  ${test.lt_round}회  (${test.lt_date })</strong>
									<a href="/SST/ReportCard/deleteLicenseTestAction.do?lt_num=${test.lt_num}&rc_num=${rc_num}" class="float-right text-danger">삭제</a>
									<a href="/SST/ReportCard/updateLicenseTestForm.do?lt_num=${test.lt_num}" class="float-right">수정</a>
											<c:if test="${test.scorelist.size() !=0 }">
											<table class="table table-bordered">
												<tr>
												<th>과목</th>
													<c:forEach items="${test.scorelist }" var="score">
														
														<th>${score.ls_name }</th>
													</c:forEach>
												</tr>
												<tr>
												<th>획득 점수</th>
													<c:forEach items="${test.scorelist }" var="score">
														
														<c:if test="${score.ls_score<score.ls_goal }">
															<td class="text-danger">${score.ls_score }</td>
														</c:if>
														<c:if test="${score.ls_score >= score.ls_goal }">
															<td class="text-success">${score.ls_score }</td>
														</c:if>

													</c:forEach>
												</tr>
												<tr>
												<th>목표 점수</th>
													<c:forEach items="${test.scorelist }" var="score">
														<td>${score.ls_goal }</td>
													</c:forEach>
												</tr>
											</table>
											</c:if>
											<hr/>
									</c:forEach>
									<a class="btn btn-success btn-circle" href="/SST/ReportCard/insertLicenseTestForm.do?rc_num=${rc_num}">
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
	<!-- Page level plugins -->
    <script src="../vendor/chart.js/Chart.min.js"></script>
    <script src="../js/license_chart.js?ver=4"></script>
	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../js/sb-admin-2.min.js"></script>
<c:set var="current" value="${ltlist[fn:length(ltlist)-1]}"></c:set>
	<script type="text/javascript">
		var data = {
			labels : [ 
				<c:forEach items="${current.scorelist}" var="score">
				'<c:out value="${score.ls_name}"/>',
			 	</c:forEach>
			],
			datasets : [ {
				label : '목표점수',
				data : [ 
					<c:forEach items="${current.scorelist}" var="score">
					'<c:out value="${score.ls_goal}"/>',
				 	</c:forEach>
				],
				fill : true,
				backgroundColor : 'rgba(255, 99, 132, 0.2)',
				borderColor : 'rgb(255, 99, 132)',
				pointBackgroundColor : 'rgb(255, 99, 132)',
				pointBorderColor : '#fff',
				pointHoverBackgroundColor : '#fff',
				pointHoverBorderColor : 'rgb(255, 99, 132)'
			}, {
				label : '${current.lt_test}',
				data : [ 
					<c:forEach items="${current.scorelist}" var="score">
				'<c:out value="${score.ls_score}"/>',
			 	</c:forEach> 
				],
				fill : true,
				backgroundColor : 'rgba(54, 162, 235, 0.2)',
				borderColor : 'rgb(54, 162, 235)',
				pointBackgroundColor : 'rgb(54, 162, 235)',
				pointBorderColor : '#fff',
				pointHoverBackgroundColor : '#fff',
				pointHoverBorderColor : 'rgb(54, 162, 235)'
			} ]
		};
		
		var ctx = document.getElementById('myChart').getContext('2d');
		var myChart = new Chart(ctx, {
			type : 'radar',
			data : data,
			options : {
				elements : {
					line : {
						borderWidth : 3
					},
					
				},scales: {
			        r: {
			            min: 0,
			            max: 100,
			            ticks: {
			                stepSize: 10
			            }
			        }
			    }
				
			},
		});
		
		$("#chart-change").change(
				function() {
					if ($("#chart-change").val() == "최근시험") {
						$('#curve_chart').empty();
						$('#curve_chart').append("<canvas id='myChart'></canvas>");
						var ctx = document.getElementById('myChart').getContext('2d');
						var myChart = new Chart(ctx, {
							type : 'radar',
							data : data,
							options : {
								scales: {
						        r: {
						            min: 0,
						            max: 100,
						            ticks: {
						                stepSize: 10
						            }
						        }
						    },
								elements : {
									line : {
										borderWidth : 3
									}
								}
							},
						});
					}
				}
				);
		
		
		
	</script>

</body>