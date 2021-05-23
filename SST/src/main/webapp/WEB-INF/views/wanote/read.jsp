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
<link href="/sst/resources/css/wanote.css" rel="stylesheet">
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
								<div class="tag-list">
								<c:forEach items="${wanote.taglist}" var="tag">
								<a><c:out value="${tag.tg_name}" /></a>
								</c:forEach>
								</div>
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

									<form action="/sst/wanotereply/create" method="post"
										class="centerform">
										<input type="hidden" name="m_id" value="ggy" id="m_idValue">
										<textarea class="form-control" rows="3" name="wr_contents" placeholder="댓글을 입력해주세요."
										 id="wr_contentsValue"
										 required></textarea>  
                                    <button type="button" class="btn btn-info float-right" id="submit-btn">댓글 달기</button><br>
									</form>
								
								<div class="panel-footer"></div>
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
	


<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- Custom scripts for all pages-->
<script type="text/javascript" src="/sst/resources/js/wanote_reply.js"></script>
	<script type="text/javascript">
		$(document).on("click", ".del-tag", function (e) {
            $(this).remove();
        });
		
		$(function(){
			var w_numValue = '<c:out value="${wanote.w_num}"/>';
			  var replyUL = $(".chat");
			  
			    showList(1);
			    

			    function showList(page){
			    	
			    	console.log("show list " + page);
			        
			        replyService.getList({w_num:w_numValue,page: page|| 1 }, function(replyCnt, list) {
			          
			        console.log("replyCnt: "+ replyCnt );
			        console.log("list: " + list);
			        console.log(list);
			        
			        if(page == -1){
			          pageNum = Math.ceil(replyCnt/10.0);
			          showList(pageNum);
			          return;
			        }
			          
			         var str="";
			         
			         if(list == null || list.length == 0){
			           return;
			         }
			         
			         for (var i = 0, len = list.length || 0; i < len; i++) {
			           str +="<li class='left clearfix border-bottom' data-wrnum='"+list[i].wr_num+"'>";
			           str +="  <div><div class='header'><strong class='primary-font'>["
			        	   +list[i].wr_num+"] "+list[i].m_id+"</strong>"; 
			           str +="    <small class='float-right text-muted'>"
			               +replyService.displayTime(list[i].wr_date)+"</small></div>";
			           str +="    <p>"+list[i].wr_contents+"</p></div></li>";
			         }
			         
			         replyUL.html(str);
			         
			         showReplyPage(replyCnt);

			     
			       });//end function
			         
			     }//end showList
			     
			     
			    
			    var pageNum = 1;
			    var replyPageFooter = $(".panel-footer");
			    
			    function showReplyPage(replyCnt){
			      
			      var endNum = Math.ceil(pageNum / 10.0) * 10;  
			      var startNum = endNum - 9; 
			      
			      var prev = startNum != 1;
			      var next = false;
			      
			      if(endNum * 10 >= replyCnt){
			        endNum = Math.ceil(replyCnt/10.0);
			      }
			      
			      if(endNum * 10 < replyCnt){
			        next = true;
			      }
			      
			      var str = "<ul class='pagination pull-right'>";
			      
			      if(prev){
			        str+= "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
			      }
			      
			       
			      
			      for(var i = startNum ; i <= endNum; i++){
			        
			        var active = pageNum == i? "active":"";
			        
			        str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
			      }
			      
			      if(next){
			        str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
			      }
			      
			      str += "</ul></div>";
			      
			      console.log(str);
			      
			      replyPageFooter.html(str);
			    }
			     
			    replyPageFooter.on("click","li a", function(e){
			        e.preventDefault();
			        console.log("page click");
			        
			        var targetPageNum = $(this).attr("href");
			        
			        console.log("targetPageNum: " + targetPageNum);
			        
			        pageNum = targetPageNum;
			        
			        showList(pageNum);
			      });     
			    
			    
			    
			    $('#submit-btn').on("click",function(e){
			        
			        var reply = {
			              wr_contents: $('#wr_contentsValue').val(),
			              m_id:$('#m_idValue').val(),
			              w_num:w_numValue
			            };
			        replyService.add(reply, function(result){
			          alert(result);
			         $("#wr_contentsValue").val("");

			          
			          //showList(1);
			          showList(1);
			          
			        });
			        
			      });

		})
		
	</script>

</body>
</html>