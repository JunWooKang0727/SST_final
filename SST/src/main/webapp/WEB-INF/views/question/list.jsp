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
<link href="../../resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../../resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="../../resources/css/studynote.css" rel="stylesheet">
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
						<h1 class="h3 mb-0 text-gray-800">노트 목록</h1>
					</div>

					<!-- Content Row -->
					<div class="row headerLine"></div>
					<!-- end of row -->

					<div class="row">

						<div class="noteListArea">


							<div class="noteTableArea">
								<table class="noteTable">
									<tr>
										<td class="sn_num thead">글번호</td>
										<td class="sn_title thead">글제목</td>
										<td class="sn_writer thead">작성자</td>
										<td class="sn_date thead">작성일자</td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<c:forEach var="question" items="${list}">
										<tr class="sn_row">
											<td class="sn_num">${question.q_num }</td>
											<td class="sn_title"><a class="noHyper move"
												href="${question.q_num }">${question.q_title }</a></td>
											<td class="sn_writer">${question.q_writer }</td>
											<td class="sn_date"><fmt:parseDate var="dt"
													value="${question.q_date}" pattern="yyyy-MM-dd HH:mm:ss" />
												<fmt:formatDate value="${dt}" pattern="yyyy/MM/dd" /></td>
										</tr>
									</c:forEach>
								</table>
							</div>

							<div class="notePagingArea">
								<!-- 페이징 영역 -->
								<!-- 이전 영역 -->
								<c:if test="${pageMaker.prev}">
									<a class="pageBeforeBtn btn btn-light btn-icon-split"
										href="${pageMaker.startPage -1}">이전</a>
								</c:if>

								<!-- 페이지목록 -->
								<c:forEach var="pageNo" begin="${pageMaker.startPage }"
									end="${pageMaker.endPage }">
									<%-- <c:if test="${pageMaker.requestPage == pageNo }">
									<div class="curBtn btn btn-icon-split">
									</c:if> --%>
									<a class="pageBtn btn btn-light btn-icon-split"
										href="${pageNo }"> <span
										class="text">${pageNo }</span>


									</a>
									<%-- 	<c:if test="${pageMaker.requestPage == pageNo }"></div></c:if> --%>
								</c:forEach>

								<!-- 이후 영역 -->
								<c:if
									test="${pageMaker.next}">
									<a class="pageAfterBtn btn btn-light btn-icon-split"
										href="${pageMaker.endPage +1 }">이후</a>
								</c:if>

							</div>
						</div>
					</div>
					<!-- end of row -->


					<div class="row">
						<div class="noteListFooterArea">

												
							<form id='searchForm' action="/question/list" method='get'>
							<select name='type'>
								<option value=""
									<c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
								<option value="T"
									<c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
								<option value="C"
									<c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
								<option value="W"
									<c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
								<option value="TC"
									<c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목
									or 내용</option>
								<option value="TW"
									<c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목
									or 작성자</option>
								<option value="TWC"
									<c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>제목
									or 내용 or 작성자</option>
							</select> <input type='text' name='keyword'
								value='<c:out value="${pageMaker.cri.keyword}"/>' /> <input
								type='hidden' name='pageNum'
								value='<c:out value="${pageMaker.cri.pageNum}"/>' /> <input
								type='hidden' name='amount'
								value='<c:out value="${pageMaker.cri.amount}"/>' />
							<button class='btn btn-default'>Search</button>
							
							<a id='regBtn'
									class="btn btn-secondary btn-icon-split rightBtn"
									href=""> <span
									class="icon text-white-50"> <i class="fas fa-pen"></i>
								</span> <span class="text">글쓰기</span>
								</a>
						</form>

						</div>

						<form id='actionForm' action="/question/list" method='get'>
							<input type='hidden' name='pageNum'
								value='${pageMaker.cri.pageNum}'> 
							<input type='hidden'
								name='amount' value='${pageMaker.cri.amount}'> 
							<input
								type='hidden' name='type'
								value='<c:out value="${ pageMaker.cri.type }"/>'> 
							<input
								type='hidden' name='keyword'
								value='<c:out value="${ pageMaker.cri.keyword }"/>'>
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
	<script src="../../resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script
		src="../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="../../resources/js/sb-admin-2.min.js"></script>
	
	<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var result = '<c:out value="${result}"/>';

						checkModal(result);

						history.replaceState({}, null, null);

						function checkModal(result) {

							if (result === '' || history.state) {
								return;
							}

							if (parseInt(result) > 0) {
								$(".modal-body").html(
										"게시글 " + parseInt(result)
												+ " 번이 등록되었습니다.");
							}

							$("#myModal").modal("show");
						}

						$("#regBtn").on("click", function(e) {
							
							e.preventDefault();
							self.location = "/question/create";

						});

						var actionForm = $("#actionForm");

						$(".notePagingArea a").on(
								"click",
								function(e) {

									e.preventDefault();

									console.log('click');

									actionForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									actionForm.submit();
								});

						$(".move")
								.on(
										"click",
										function(e) {

											e.preventDefault();
											actionForm
													.append("<input type='hidden' name='q_num' value='"
															+ $(this).attr(
																	"href")
															+ "'>");
											actionForm.attr("action",
													"/question/read");
											actionForm.submit();

										});

						var searchForm = $("#searchForm");

						$("#searchForm button").on(
								"click",
								function(e) {
				
									if (!searchForm.find("option:selected")
											.val()) {
										alert("검색종류를 선택하세요");
										return false;
									}

									if (!searchForm.find(
											"input[name='keyword']").val()) {
										alert("키워드를 입력하세요");
										return false;
									}

									searchForm.find("input[name='pageNum']")
											.val("1");
									e.preventDefault();

									searchForm.submit();

								});

					});
</script>

</body>
</html>












