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
<link href="../../../resources/css/studynote.css" rel="stylesheet">
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
									<c:forEach var="studynote" items="${list}">
										<tr class="sn_row">
											<td class="sn_num">${studynote.sn_num }</td>
											<td class="sn_title"><a class="noHyper"
												href="StudyNote_DetailAction.do?sn_num=${studynote.sn_num }">${studynote.sn_title }</a></td>
											<td class="sn_writer">${studynote.gm_num }</td>
											<td class="sn_date"><fmt:parseDate var="dt"
													value="${studynote.sn_date}" pattern="yyyy-MM-dd HH:mm:ss" />
												<fmt:formatDate value="${dt}" pattern="yyyy/MM/dd" /></td>
										</tr>
									</c:forEach>
								</table>
							</div>

							<div class="notePagingArea">
								<!-- 페이징 영역 -->
								<!-- 이전 영역 -->
								<c:if test="${pageMaker.startPage > 5 }">
									<a class="pageBeforeBtn btn btn-light btn-icon-split"
										href="StudyNote_ListAction.do?pageNum=${pageMaker.startPage -1 }">이전</a>
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
									test="${StudyNoteListVO.endPage < StudyNoteListVO.totalPageCount}">
									<a class="pageAfterBtn btn btn-light btn-icon-split"
										href="StudyNote_ListAction.do?pageNum=${StudyNoteListVO.endPage +1 }">이후</a>
								</c:if>

							</div>
						</div>







					</div>
					<!-- end of row -->


					<div class="row">
						<div class="noteListFooterArea">

							<form action="StudyNote_ListAction.do" method="post">
								<input type="checkbox" name="area" value="sn_title">제목 <input
									type="checkbox" name="area" value="sn_contents">내용 <input
									type="text" class="" name="searchKey" placeholder="검색어를 입력해주세요"
									size="20"> <input type="submit" class="btn-primary"
									value="검색"> <a
									class="btn btn-secondary btn-icon-split rightBtn"
									href="StudyNote_InsertFormAction.do"> <span
									class="icon text-white-50"> <i class="fas fa-pen"></i>
								</span> <span class="text">글쓰기</span>
								</a>
							</form>


						</div>

						<form id='actionForm' action="/studynote/list" method='get'>
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
	<script src="../../../resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="../../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script
		src="../../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="../../../resources/js/sb-admin-2.min.js"></script>
	
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

						$("#regBtn").on("click", function() {

							self.location = "/studynote/create";

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
													.append("<input type='hidden' name='bno' value='"
															+ $(this).attr(
																	"href")
															+ "'>");
											actionForm.attr("action",
													"/studynote/read");
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












