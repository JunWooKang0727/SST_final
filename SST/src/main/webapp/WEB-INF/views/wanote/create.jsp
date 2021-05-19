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
									<form action="/wanote/create" method="post"
										class="centerform">
										<input type="hidden" name="m_id" value="ggy">
										제목: <input type="text" name="w_title" placeholder="시험명을 입력해주세요." class="form-control" required><br>
										문제: <input type="number" name="w_question" placeholder="시험 회차를 입력해주세요." class="form-control"  min="1" max="1000" required><br>  
										정답과 풀이: <input type="text" name="w_answer" placeholder="정답과 문제 풀이를 입력해 주세요." class="form-control" required><br>
										<hr>
										틀린 이유: 
											<select class="form-control " name="w_reason" id="w_reason" required>
											<option value="초등학교성적">문제 파악 미흡</option>
											<option value="중학교성적">해당내용에 대한 이해 부족</option>
											<option value="고등학교성적">예상치 못한 문제</option>
											<option value="License">계산 실수</option>
											<option value="Language">단순 실수</option>
										</select><br> 
										과목: <input type="text" name="w_subject" placeholder="시험명을 입력해주세요." class="form-control" required><br>
										해시태그: <input type="text" name="tag" placeholder="시험명을 입력해주세요." class="form-control" required><br>
										<hr>
										
										<div id="plus_score_input">
										</div>
										 <a class="btn btn-success btn-circle" id="plus_licensescore">
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
	<!-- Custom scripts for all pages-->

</body>
</html>