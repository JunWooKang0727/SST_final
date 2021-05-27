<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<meta name="_csrf" content="${_csrf.token}">
	<meta name="_csrf_header" content="${_csrf.headerName}">
    <title>SST</title>
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    
    <!-- Custom fonts for this template-->
    <link href="../../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="../../resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- sidemenu -->
        <%@include file="../template/sidemenu.jsp" %>
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
                <!-- topbar -->
				<%@include file="../template/topbar.jsp" %>
                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- 페이지 헤더 -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-2">
                        <h1 class="h3 mb-0 text-gray-800">그룹 관리</h1>
                    </div>
                  
                    
                    <div class="row">
                    	<div class="col-lg-1"></div>
                    	<div class="col-lg-5">
                    		<h1 class="h5 ml-4 mt-1 mb-0 text-gray-800">스터디 그룹원 관리</h1>
                    	</div>
                    	<div class="col-lg-5">
                    		<h1 class="h5 ml-4 mt-1 mb-0 text-gray-800">가입신청 대기자</h1>
                    	</div>
                    	<div class="col-lg-1"></div>

                    </div>
                    
                    <div class="row">
                    	<div class="col-lg-1"></div>
                    	<div class="col-lg-5 ml-5">
                   			<span class="ml-5 mr-5">아이디</span>
                    		<span class="ml-5 mr-5">권한</span>
                    		<span class="ml-5 mr-5">회원관리</span>
                    	</div>
                    	<div class="row col-lg-5">
                    		<span class="ml-5 mr-5">아이디</span>
                    		<span class="ml-5 mr-5"></span>
                    		<span class="ml-5 mr-5">수락/거절</span>
                    	</div>
                    	<div class="col-lg-1"></div>
                    </div>
                    
                    <div class="row">
                    	<div class="col-lg-6 totalmem">
                    		<c:forEach items="${memberlist }" var="member" varStatus="status">
                    			<div class="mt-3 mb-3">
                    				<div class="row">
                    					<div class="col-lg-2"></div>
                    					<div class="col-lg-3">
                    						<span class="ml-3 mr-5">${status.count }</span>
		                    				<span class="ml-4 text-center">${member.m_id }</span>
                    					</div>
                    					<div class="col-lg-2 ml-4 mr-3">
                    						<select class="form-control form-control-sm" id="auth">
											  <option 
											  	<c:if test="${member.p_grant eq 1}">selected</c:if>
											  >그룹장</option>
											  <option
											  	<c:if test="${member.p_grant eq 2}">selected</c:if>
											  >그룹매니저</option>
											  <option
											  	<c:if test="${member.p_grant eq 3}">selected</c:if>
											  >일반</option>
											</select>
                    					</div>
                    					<div class="col-lg-2 ml-4">
                    						<button type="button" class="btn btn-primary btn-sm" id="mem_modify"
                    						data-gnum="${group.g_num }" data-mid="${member.m_id }">수정</button>
		                    				<button type="button" class="btn btn-danger btn-sm" id="mem_delete"
		                    				data-gnum="${group.g_num }" data-mid="${member.m_id }">삭제</button>
                    					</div>
                    				</div>
	                    		</div>
                    		</c:forEach>
                    	</div>
                    	<div class="col-lg-6">
                    		<c:forEach items="${waitmember }" var="member" varStatus="status">
                    			<div class="mt-3 mb-3">
						          <div class="row">
						               <div class="col-lg-5">
						                  <span class="ml-3 mr-5">${status.count }</span>
								          <span class="text-center">${member.m_id }</span>
						               </div>
						               <div class="col-lg-2">
						                  <button data-toggle="modal" data-target="#exampleModal2" type="button" 
						                  class="btn btn-primary btn-sm" 
						                  data-mid="${member.m_id }" data-gnum="${group.g_num }">수락</button>
								          
								          <button data-toggle="modal" data-target="#exampleModal" type="button" 
								          data-mid="${member.m_id }" data-gnum="${group.g_num }" 
								          class="btn btn-danger btn-sm" >거절</button>
								          
						               </div>
						          </div>
								</div>
                    		</c:forEach>
                    	</div>
                    	
                    	<!-- 승인 Modal -->
                    	<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">스터디 가입</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						        스터디 가입을 승인하시겠습니까?
						      </div>
						      <div class="modal-footer">
						      	<button type="button" class="btn btn-primary" id="mem_accepts">승인</button>
						       	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						      </div>
						    </div>
						  </div>
						</div>
                    	<!-- 거절 Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">스터디 가입</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						        스터디 가입을 거절겠습니까?
						      </div>
						      <div class="modal-footer">
						      	<button type="button" class="btn btn-primary" id="mem_deny">거절</button>
						       	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						      </div>
						    </div>
						  </div>
						</div>

                    </div>
                </div>

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <%@include file="../template/footer.jsp" %>
            <!-- End of Footer -->
        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
    <script src="../../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="../../resources/js/sb-admin-2.min.js"></script>
    <script src="../../resources/js/group/group_read.js"></script>
    <script src="../../resources/js/group/group_create.js"></script>

</body>
</html>