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
						<h1 class="h3 mb-0 text-gray-800">오답노트</h1>
					</div>
					<!-- Content Row -->
					<div class="row">
						<div class="col-lg-12">

							<div class="card shadow mb-4">

								<ul class="nav nav-tabs">
									<li class="nav-item"><a class="nav-link active" href="/wanote/list" id='allWanote'>전체 오답노트</a>
									</li>
									<li class="nav-item"><a class="nav-link" href="/wanote/mylist?m_id=<security:authentication property="principal.username"/>">나의 오답노트</a>
									</li>
								</ul>

							<div class="card-body">

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<form id='searchForm' action="/wanote/list" method='get'
						class="float-right">
						 <div class="input-group">
							<select name='type' class="form-control">
								<option value=""
									<c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
								<option value="T"
									<c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
								<option value="C"
									<c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
								<option value="TC"
									<c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목
									or 내용</option>
								<option value="Tag"
									<c:out value="${pageMaker.cri.type eq 'Tag'?'selected':''}"/>>태그</option>
							</select> 
							<input type='text' name='keyword' class="form-control" placeholder="검색어 입력"
								value='<c:out value="${pageMaker.cri.keyword}"/>' /> 
								<input type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum}"/>' /> 
								<input	type='hidden' name='amount'	value='<c:out value="${pageMaker.cri.amount}"/>' />
								 <div class="input-group-append">
								 <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
                                <button class='btn btn-default'>Search</button>
                            </div>
								</div>
						</form>
			</div>
				
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>과목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>

          <c:forEach items="${list}" var="wanote">
            <tr>
              <td><c:out value="${wanote.w_num}" /></td>
               <td>
                  <a class='move' href='<c:out value="${wanote.w_num}"/>'>
                  <c:out value="${wanote.w_title}" />
                  </a>
                  <td><c:out value="${wanote.w_subject}" /></td>
              <td><c:out value="${wanote.m_id}" /></td>
              <td><c:out value="${wanote.w_date}" /></td>
            </tr>
          </c:forEach>

				</table>
				<div class='d-flex justify-content-center'>
					<ul class="pagination">

						<c:if test="${pageMaker.prev}">
							<li class="page-item previous"><a 
								href="${pageMaker.startPage -1}" class="page-link"> &lt; </a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="page-item  ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a href="${num}" class="page-link">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="page-item next"><a
								href="${pageMaker.endPage +1 }" class="page-link">&gt; </a></li>
						</c:if>


					</ul>
				</div>
				<!--  end Pagination -->
				<div class="raw">
				<a href="/wanote/create" class="btn btn-info float-right">작성하기</a>
				</div>
				
			</div>

			<form id='actionForm' action="/wanote/list" method='get'>
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>

				<input type='hidden' name='type'
					value='<c:out value="${ pageMaker.cri.type }"/>'> <input
					type='hidden' name='keyword'
					value='<c:out value="${ pageMaker.cri.keyword }"/>'>
 <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>

			</form>

			


			<!-- Modal  추가 -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">처리가 완료되었습니다.</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save
								changes</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->


		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
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
	<!-- Custom scripts for all pages-->
	<script src="/resources/js/personalstudy.js"></script>
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



						var actionForm = $("#actionForm");

						$(".page-item a").on(
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
													.append("<input type='hidden' name='w_num' value='"
															+ $(this).attr(
																	"href")
															+ "'>");
											actionForm.attr("action",
													"/wanote/read");
											actionForm.submit();

										});

						var searchForm = $("#searchForm");

						$("#searchForm button").on(
								"click",
								function(e) {

									/* if (!searchForm.find("option:selected")
											.val()) {
										alert("검색종류를 선택하세요");
										return false;
									}

									if (!searchForm.find(
											"input[name='keyword']").val()) {
										alert("키워드를 입력하세요");
										return false;
									} */

									searchForm.find("input[name='pageNum']")
											.val("1");
									e.preventDefault();

									searchForm.submit();

								});

					});
</script>
</body>
</html>

