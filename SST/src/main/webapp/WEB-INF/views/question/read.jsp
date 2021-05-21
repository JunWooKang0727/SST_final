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
								<h2>${question.q_title}</h2>
								<hr>
								작성일 :${question.q_date}
							</div>
							<%-- 글번호 :${studynote.sn_num }<br>  --%>

							<div class="noteContents">${question.q_contents}</div>

							<div class="noteFooter">
								<a class="btn btn-light btn-icon-split noteFooterBtn"
									href="list"> <span class="icon text-gray-600"><i
										class="fas fa-arrow-right"></i></span><span class="text">글 목록</span>
								</a> <a class="btn btn-primary btn-icon-split noteFooterBtn"
									href="/question/update?q_num=${question.q_num}"> <span
									class="icon text-white-50"><i class="fas fa-check"></i></span><span
									class="text">글 수정하기</span>
								</a> <a id="delete"
									class="btn btn-danger btn-icon-split noteFooterBtn"
									href="/question/list"> <span class="icon text-white-50"><i
										class="fas fa-trash"></i></span><span class="text">글 삭제</span>
								</a>
							</div>
						</div>
					</div>
					<!-- end of row -->

					<form id='operForm' action="/question/modify" method="get">
						<input type='hidden' id='q_num' name='q_num'
							value='<c:out value="${question.q_num}"/>'> <input
							type='hidden' name='pageNum'
							value='<c:out value="${cri.pageNum}"/>'> <input
							type='hidden' name='amount'
							value='<c:out value="${cri.amount}"/>'> <input
							type='hidden' name='keyword'
							value='<c:out value="${cri.keyword}"/>'> <input
							type='hidden' name='type' value='<c:out value="${cri.type}"/>'>

					</form>

					<div class="row"></div>
					<!-- end of row -->

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->
			<!-- 여기부터 댓글 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 댓글 -->
			<div class='row'>

				<div class="col-lg-12">

					<!-- /.panel -->
					<div class="panel panel-default">
						<!--       <div class="panel-heading">
        <i class="fa fa-comments fa-fw"></i> Reply
      </div> -->

						<div class="panel-heading">
							<i class="fa fa-comments fa-fw"></i> Reply
							<button id='addReplyBtn'
								class='btn btn-primary btn-xs pull-right'>New Reply</button>

						</div>


						<!-- /.panel-heading -->
						<div class="panel-body">

							<%--       <td>
    <button type="button" id="likeBtn" class="btn btn-info likeBtn" onclick="updateLike(this);">
        <c:out value="${result.faqrp_likeCnt}"/>                                                
    </button>
</td>
<td>
    <button type="button" id="hateBtn" class="btn btn-secondary hateBtn" onclick="updateHate(this);">
        <c:out value="${result.faqrp_hateCnt}"/>                                                
    </button>
</td> --%>
							<ul class="chat">

							</ul>
							<!-- ./ end ul -->

						</div>
						<!-- /.panel .chat-panel -->

						<div class="panel-footer"></div>


					</div>
				</div>
				<!-- ./ end row -->
			</div>
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Reply</label> <input class="form-control" name='reply'
									value='New Reply!!!!'>
							</div>
							<div class="form-group">
								<label>Replyer</label> <input class="form-control"
									name='replyer' value='replyer'>
							</div>
							<div class="form-group">
								<label>Reply Date</label> <input class="form-control"
									name='replyDate' value='2018-01-01 13:13'>
							</div>

						</div>
						<div class="modal-footer">
							<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
							<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
							<button id='modalRegisterBtn' type="button"
								class="btn btn-primary">Register</button>
							<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->
			<!-- 여기부터 스크립트 -->

			<script type="text/javascript" src="../../../resources/js/reply.js"></script>
			<!-- Bootstrap core JavaScript-->
			<script src="../../../resources/vendor/jquery/jquery.min.js"></script>

			<script>
				$(document)
						.ready(
								function() {

									var bnoValue = '<c:out value="${question.q_num}"/>';
									var replyUL = $(".chat");

									showList(1);

									function showList(page) {

										console.log("show list " + page);
										
										replyService
												.getList(
														{
															bno : bnoValue,
															page : page || 1
														},
														function(replyCnt, list) {

															console
																	.log("replyCnt: "
																			+ replyCnt);
															console
																	.log("list: "
																			+ list);
															console.log(list);

															if (page == -1) {
																pageNum = Math
																		.ceil(replyCnt / 10.0);
																showList(pageNum);
																return;
															}

															var str = "";

															if (list == null
																	|| list.length == 0) {
																return;
															}

															for (var i = 0, len = list.length || 0; i < len; i++) {
																str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
																str += "  <div><div class='header'><strong class='primary-font'>["
																		+ list[i].rno
																		+ "] "
																		+ list[i].replyer
																		+ "</strong>";
																str += "    <small class='pull-right text-muted'>"
																		+ replyService
																				.displayTime(list[i].replyDate)
																		+ "</small></div>";
																str += "    <p>"
																		+ list[i].reply
																		+ "</p></div></li>";
															}

															replyUL.html(str);

															showReplyPage(replyCnt);

														});//end function

									}//end showList

									var pageNum = 1;
									var replyPageFooter = $(".panel-footer");

									function showReplyPage(replyCnt) {

										var endNum = Math.ceil(pageNum / 10.0) * 10;
										var startNum = endNum - 9;

										var prev = startNum != 1;
										var next = false;

										if (endNum * 10 >= replyCnt) {
											endNum = Math.ceil(replyCnt / 10.0);
										}

										if (endNum * 10 < replyCnt) {
											next = true;
										}

										var str = "<ul class='pagination pull-right'>";

										if (prev) {
											str += "<li class='page-item'><a class='page-link' href='"
													+ (startNum - 1)
													+ "'>Previous</a></li>";
										}

										for (var i = startNum; i <= endNum; i++) {

											var active = pageNum == i ? "active"
													: "";

											str += "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"
													+ i + "</a></li>";
										}

										if (next) {
											str += "<li class='page-item'><a class='page-link' href='"
													+ (endNum + 1)
													+ "'>Next</a></li>";
										}

										str += "</ul></div>";

										console.log(str);

										replyPageFooter.html(str);
									}

									replyPageFooter.on("click", "li a",
											function(e) {
												e.preventDefault();
												console.log("page click");

												var targetPageNum = $(this)
														.attr("href");

												console.log("targetPageNum: "
														+ targetPageNum);

												pageNum = targetPageNum;

												showList(pageNum);
											});

									var modal = $(".modal");
									var modalInputReply = modal
											.find("input[name='reply']");
									var modalInputReplyer = modal
											.find("input[name='replyer']");
									var modalInputReplyDate = modal
											.find("input[name='replyDate']");

									var modalModBtn = $("#modalModBtn");
									var modalRemoveBtn = $("#modalRemoveBtn");
									var modalRegisterBtn = $("#modalRegisterBtn");

									$("#modalCloseBtn").on("click",
											function(e) {

												modal.modal('hide');
											});

									$("#addReplyBtn")
											.on(
													"click",
													function(e) {

														modal.find("input")
																.val("");
														modalInputReplyDate
																.closest("div")
																.hide();
														modal
																.find(
																		"button[id !='modalCloseBtn']")
																.hide();

														modalRegisterBtn.show();

														$(".modal").modal(
																"show");

													});

									modalRegisterBtn
											.on(
													"click",
													function(e) {

														var reply = {
															reply : modalInputReply
																	.val(),
															replyer : modalInputReplyer
																	.val(),
															q_num : bnoValue
														};
														replyService
																.add(
																		reply,
																		function(
																				result) {

																			alert(result);

																			modal
																					.find(
																							"input")
																					.val(
																							"");
																			modal
																					.modal("hide");

																			//showList(1);
																			showList(-1);

																		});

													});

									//댓글 조회 클릭 이벤트 처리 
									$(".chat")
											.on(
													"click",
													"li",
													function(e) {

														var rno = $(this).data(
																"rno");

														replyService
																.get(
																		rno,
																		function(
																				reply) {

																			modalInputReply
																					.val(reply.reply);
																			modalInputReplyer
																					.val(reply.replyer);
																			modalInputReplyDate
																					.val(
																							replyService
																									.displayTime(reply.replyDate))
																					.attr(
																							"readonly",
																							"readonly");
																			modal
																					.data(
																							"rno",
																							reply.rno);

																			modal
																					.find(
																							"button[id !='modalCloseBtn']")
																					.hide();
																			modalModBtn
																					.show();
																			modalRemoveBtn
																					.show();

																			$(
																					".modal")
																					.modal(
																							"show");

																		});
													});

									modalModBtn.on("click", function(e) {

										var reply = {
											rno : modal.data("rno"),
											reply : modalInputReply.val()
										};

										replyService.update(reply, function(
												result) {

											alert(result);
											modal.modal("hide");
											showList(pageNum);

										});

									});

									modalRemoveBtn.on("click", function(e) {

										var rno = modal.data("rno");

										replyService.remove(rno, function(
												result) {

											alert(result);
											modal.modal("hide");
											showList(pageNum);

										});

									});
								});
			</script>



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
		$(function() {

			$('#delete').on('click', function(e) {
				var snnum = '<c:out value="${question.q_num}"/>'
				e.preventDefault();

				$.ajax({
					url : '/question/delete',
					data : {
						"q_num" : q_num
					},
					type : 'post',
					success : function() {
						console.log(success);
					}
				});//end of ajax

			});//end of delete onclick	
		});
	</script>
</body>
</html>












