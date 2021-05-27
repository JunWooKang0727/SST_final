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
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">생성한 그룹</h1>
                    </div>
                    <!-- 페이지 본문 -->
                    <!-- 생성한 그룹 -->  
                    <div class="row">
                       <c:forEach items="${mygrouplist }" var="group">
		                	<div class="col-xl-3 col-md-6 mb-4">
	                            <div class="card border-left-success shadow h-100 py-2">
	                                <div class="card-body">
	                                    <div class="row no-gutters align-items-center">
	                                        <div class="col mr-2">
	                                        	<div class="text-xs font-weight-bold text-gray text-uppercase mb-1">
													카테고리 :<%--  <c:out value="${group.g_category }"/> --%>
													<c:choose>
	                                            		<c:when test="${group.g_category eq '초등학교' }">
	                                            			초등학교
	                                            		</c:when>
	                                            		<c:when test="${group.g_category eq '중학교' }">
	                                            			중학교
	                                            		</c:when>
	                                            		<c:when test="${group.g_category eq '고등학교' }">
	                                            			고등학교
	                                            		</c:when>
	                                            		<c:when test="${group.g_category eq 'License' }">
	                                            			자격증
	                                            		</c:when>
	                                            		<c:when test="${group.g_category eq 'Language' }">
	                                            			어학시험
	                                            		</c:when>
	                                            		<c:when test="${group.g_category eq 'etc' }">
	                                            			기타
	                                            		</c:when>
	                                            	</c:choose>
	                                            </div>
	                                            <div class="mb-2">
	                                            	<a href="/group/read?g_num=<c:out value="${group.g_num }"/>" class="h5 mb-1 font-weight-bold text-gray-800">
	                                            	<c:out value="${group.g_name }"/></a>
	                                            	<a href="/group/detail?g_num=<c:out value="${group.g_num }"/>" class="h5 mb-1 font-weight-bold text-gray-800">
	                                            	입장</a>
	                                            </div>
	                                            <div class="text-xs font-weight-bold text-gray text-uppercase mb-1">
													스터디 설명 : <c:out value="${group.g_content }"/>
	                                            </div>
	                                            <div class="text-xs font-weight-bold text-gray text-uppercase mb-1">
	                                            	그룹 정원 : <c:out value="${group.g_memnum }"/>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                   		</div>
                       </c:forEach>
                	</div>
                       
                	
                	<!-- 참여중인 그룹 -->
                	<div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">참여중인 그룹</h1>
                    </div>
					<div class="row">
                       <c:forEach items="${attendgroup }" var="one">
		                	<div class="col-xl-3 col-md-6 mb-4">
	                            <div class="card border-left-success shadow h-100 py-2">
	                                <div class="card-body">
	                                    <div class="row no-gutters align-items-center">
	                                        <div class="col mr-2">
	                                            <div class="mb-2">
	                                            	<!-- href를 그룹 내부 페이지로 수정해야함 -->
	                                            	<a href="/group/read?g_num=<c:out value="${one.g_num }"/>" class="h5 mb-1 font-weight-bold text-gray-800">
	                                            	<c:out value="${one.g_name }"/></a>
	                                            	<a href="/group/detail?g_num=<c:out value="${one.g_num }"/>" class="h5 mb-1 font-weight-bold text-gray-800">
	                                            	입장</a>
	                                            </div>
	                                            <div class="text-xs font-weight-bold text-gray text-uppercase mb-1">
													스터디 설명 : <c:out value="${one.g_content }"/>
	                                            </div>
	                                            <div class="text-xs font-weight-bold text-gray text-uppercase mb-1">
	                                            	그룹 정원 : <c:out value="${one.g_memnum }"/>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                   		</div>
                       </c:forEach>
                	</div>
                	
                	<!-- 승인 대기중인 그룹 -->
                	<div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">승인 대기중인 그룹</h1>
                    </div>
					<div class="row">
                       <c:forEach items="${waitlist }" var="one">
		                	<div class="col-xl-3 col-md-6 mb-4">
	                            <div class="card border-left-success shadow h-100 py-2">
	                                <div class="card-body">
	                                    <div class="row no-gutters align-items-center">
	                                        <div class="col mr-2">
	                                            <div class="mb-2">
	                                            	<!-- href를 그룹 신청 페이지로 이동해야함 -->
	                                            	<a href="/group/read?g_num=<c:out value="${one.g_num }"/>" class="h5 mb-1 font-weight-bold text-gray-800">
	                                            	<c:out value="${one.g_name }"/></a>
	                                            </div>
	                                            <div class="text-xs font-weight-bold text-gray text-uppercase mb-1">
													스터디 설명 : <c:out value="${one.g_content }"/>
	                                            </div>
	                                            <div class="text-xs font-weight-bold text-gray text-uppercase mb-1">
	                                            	그룹 정원 : <c:out value="${one.g_memnum }"/>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                   		</div>
                       </c:forEach>
                	</div>
                </div>
            </div>
            <!-- End of Main Content -->

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
</body>
</html>