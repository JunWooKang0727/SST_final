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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>SST</title>
<link href="/resources/css/wanote.css" rel="stylesheet">
<style type="text/css">
.chat>ul{
	list-style:none;
}

</style>
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
									<h5 class="m-0 font-weight-bold">오답노트</h5>
								</div>
								<div class="card-body">
								<h5 class="m-0 font-weight-bold text-gray-800">${wanote.w_title}</h5>
								  <p class="float-right">${wanote.m_id}   |   ${wanote.w_date}</p> <br>
								<hr>Q. ${wanote.w_question}
								<br>
								<hr>
									<div class='uploadResult'>
										<ul>
										</ul>
									</div>

									<div class="tag-list">
								<c:forEach items="${wanote.taglist}" var="tag">
								<a href="/wanote/list?type=Tag&keyword=${tag.tg_name}"><c:out value="${tag.tg_name}" /></a>
								</c:forEach>
								</div>
								<c:if test="${wanote.m_id eq 'ggy'}">
									 <button type="button" class="btn btn-info float-right" data-oper="update">수정</button>
								</c:if>
								
								</div>
							</div>
						</div>

					</div>
					
					<div class="row">
						<div class="col-lg-12">
							<!-- Collapsable Card-->
							<div class="card shadow mb-4">
								<!-- Card Header - Accordion -->
								<a href="#collapseCardExample" class="d-block card-header py-3"
									data-toggle="collapse" role="button" aria-expanded="false"
									aria-controls="collapseCardExample">
									<h5 class="m-0 font-weight-bold text-info">정답과 풀이</h5>
								</a>
								<!-- Card Content - Collapse -->
								<div class="collapse" id="collapseCardExample">
									<div class="card-body">
										${wanote.w_answer}
										
										
										<hr>
										<strong class="text-warning">틀린 이유:</strong> ${wanote.w_reason}
									</div>
								</div>
							</div>
						</div>
					</div>

					
					<div class="row">
						<div class="col-lg-12">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h5 class="m-0 font-weight-bold text-color-sst"><i class="fa fa-comments fa-fw"></i>댓글</h5>
								</div>
								<div class="card-body">
								      <!-- /.panel-heading -->

									<ul class="chat">

									</ul>

									<form action="/wanotereply/create" method="post"
										class="centerform">
										<input type="hidden" name="m_id" value="ggy" id="m_idValue">
										<textarea class="form-control" rows="3" name="wr_contents" placeholder="댓글을 입력해주세요."
										 id="wr_contentsValue"
										 required></textarea>  
                                    <button type="button" class="btn btn-info float-right" id="submit-btn">댓글 달기</button><br>
									</form>

									<div class="panel-footer">
									
										<div class='bigPictureWrapper'>
											<div class='bigPicture'></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<form id='operForm' action="/wanote/update" method="get">
  <input type='hidden' id='w_num' name='w_num' value='<c:out value="${wanote.w_num}"/>'>
  <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
  <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
  <input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
  <input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>  
 
</form>
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
	



<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- Custom scripts for all pages-->
<script type="text/javascript" src="/resources/js/wanote_reply.js"></script>
	<script type="text/javascript">
	
	//태그검색
		$(document).on("click", ".del-tag", function (e) {
            $(this).remove();
        });
	

		//업데이트
		$(document).ready(function() {

			var operForm = $("#operForm");

			$("button[data-oper='update']").on("click", function(e) {

				operForm.attr("action", "/wanote/update").submit();

			});

			$("button[data-oper='list']").on("click", function(e) {

				operForm.find("#bno").remove();
				operForm.attr("action", "/05/board/list")
				operForm.submit();

			});
		});

		//댓글처리
		$(function() {
			var w_numValue = '<c:out value="${wanote.w_num}"/>';
			var replyUL = $(".chat");

			showList(1);

			function showList(page) {

				console.log("show list " + page);

				replyService
						.getList(
								{
									w_num : w_numValue,
									page : page || 1
								},
								function(replyCnt, list) {

									console.log("replyCnt: " + replyCnt);
									console.log("list: " + list);
									console.log(list);

									if (page == -1) {
										pageNum = Math.ceil(replyCnt / 10.0);
										showList(pageNum);
										return;
									}

									var str = "";

									if (list == null || list.length == 0) {
										return;
									}

									for (var i = 0, len = list.length || 0; i < len; i++) {
										str += "<li class='left clearfix border-bottom' data-wrnum='"+list[i].wr_num+"'>";
										str += "  <div><div class='header'><strong class='primary-font'>["
												+ list[i].wr_num
												+ "] "
												+ list[i].m_id + "</strong>";
										str += "    <small class='float-right text-muted'>"
												+ replyService
														.displayTime(list[i].wr_date)
												+ "</small></div>";
										str += "<div class='contents'><p>"
												+ list[i].wr_contents + "</p>";
										if (list[i].m_id == 'ggy') {
											str += "<div class='float-right updateForm'><a class='text-info' data-oper='updateReply'>수정</a> | <a class='text-danger' data-oper='deleteReply'>삭제</a></div></div>";
										}

										str += "</div></li>";
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
							+ (startNum - 1) + "'>Previous</a></li>";
				}

				for (var i = startNum; i <= endNum; i++) {

					var active = pageNum == i ? "active" : "";

					str += "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"
							+ i + "</a></li>";
				}

				if (next) {
					str += "<li class='page-item'><a class='page-link' href='"
							+ (endNum + 1) + "'>Next</a></li>";
				}

				str += "</ul></div>";

				console.log(str);

				replyPageFooter.html(str);
			}

			replyPageFooter.on("click", "li a", function(e) {
				e.preventDefault();
				console.log("page click");

				var targetPageNum = $(this).attr("href");

				console.log("targetPageNum: " + targetPageNum);

				pageNum = targetPageNum;

				showList(pageNum);
			});

			$('#submit-btn').on("click", function(e) {

				var reply = {
					wr_contents : $('#wr_contentsValue').val(),
					m_id : $('#m_idValue').val(),
					w_num : w_numValue
				};
				replyService.add(reply, function(result) {
					alert(result);
					$("#wr_contentsValue").val("");

					//showList(1);
					showList(1);

				});

			});

			$(".chat")
					.on(
							"click",
							".updateForm>a",
							function(e) {

								var wr_num = $(this).parents('li')
										.data("wrnum");
								if ($(this).data('oper') == 'updateReply') {
									$(this).parents('li')
											.find('div.contents>p').attr(
													'hidden', 'true');
									$(this).parent().attr('hidden', 'true');
									$(this)
											.parents('li')
											.find('div.contents')
											.append(
													"<div class='modifyReply'><textarea class='form-control' rows='3' name='wr_contents'" 
			        			+" id='wr_contentsUpdate' required>"
															+ $(this)
																	.parents(
																			'li')
																	.find(
																			'div.contents>p')
																	.text()
															+ "</textarea> "
															+ "<div class='float-right updateReply'><a data-oper='cancel'>취소</a> | <a class='text-info' data-oper='update'>수정</a><br></div></div></div>");
								} else {
									replyService.remove(wr_num,
											function(result) {
												alert(result);
												showList(pageNum);

											});
								}
							});

			$(".chat").on(
					"click",
					"div.updateReply>a",
					function(e) {
						var wr_num = $(this).parents('li').data("wrnum");
						if ($(this).data('oper') == 'update') {
							var wr_contents = $(this).parents('li').find(
									'#wr_contentsUpdate').val();
							var reply = {
								wr_num : wr_num,
								wr_contents : wr_contents
							};
							replyService.update(reply, function(result) {
								alert(result);
								$(this).parents('li').find('div.contents>p')
										.removeAttr('hidden');
								$(this).parents('li').find('div.updateForm')
										.removeAttr('hidden');
								$(this).parents('li').find(
										'div.contents>.modifyReply').remove();
								showList(pageNum);
							});

						} else {
							$(this).parents('li').find('div.contents>p')
									.removeAttr('hidden');
							$(this).parents('li').find('div.updateForm')
									.removeAttr('hidden');
							$(this).parents('li').find(
									'div.contents>.modifyReply').remove();
						}

					});

			//첨부파일
			$.getJSON("/wanote/getAttachList",{w_num : w_numValue},
							function(arr) {
								console.log(arr);

								var str = "";

								$(arr)
										.each(
												function(i, attach) {
													console
															.log(attach.fileType);
													//image type
													if (attach.fileType) {
														var fileCallPath = encodeURIComponent(attach.uploadPath
																+ "/"
																+ attach.uuid
																+ "_"
																+ attach.fileName);

														str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
														str += "<img height='250' src='/wanoteAttach/display?fileName="
																+ fileCallPath
																+ "'>";
														str += "</div>";
														str + "</li>";
													} else {

														str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
														str += "<span> "
																+ attach.fileName
																+ "</span><br/>";
														str += "<img src='/resources/img/attach.png'></a>";
														str += "</div>";
														str + "</li><hr>";
													}
												});

								$(".uploadResult ul").html(str);

							});//end getjson

			function showImage(fileCallPath) {

				alert(fileCallPath);

				$(".bigPictureWrapper").css("display", "flex").show();

				$(".bigPicture").html(
						"<img src='sst/wanoteAttach/display?fileName="
								+ fileCallPath + "' >").animate({
					width : '100%',
					height : '100%'
				}, 1000);

			}
			$(".bigPictureWrapper").on("click", function(e) {
				$(".bigPicture").animate({
					width : '0%',
					height : '0%'
				}, 1000);
				setTimeout(function() {
					$('.bigPictureWrapper').hide();
				}, 1000);
			});

			$(".uploadResult")
					.on(
							"click",
							"li",
							function(e) {

								console.log("view image");

								var liObj = $(this);

								var path = encodeURIComponent(liObj
										.data("path")
										+ "/"
										+ liObj.data("uuid")
										+ "_"
										+ liObj.data("filename"));

								if (liObj.data("type")) {
									showImage(path.replace(new RegExp(/\\/g),
											"/"));
								} else {
									//download 
									self.location = "/wanoteAttach/download?fileName="
											+ path
								}

							});
		})
	</script>
</body>
</html>