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
                    <div class="d-sm-flex align-items-center justify-content-between mb-2">
                        <h1 class="h3 mb-0 text-gray-800">그룹 관리</h1>
                    </div>
                    <!-- 페이지 본문 -->
                    <div class="row">
                    	<div class="col-lg-1"></div>
	                    <div class="col-lg-10">
	                    <a href="/group/update?g_num=<c:out value="${group.g_num }"/>" class="float-right"> 그룹설정 변경 </a>
	                    <h1 class="h3 mb-2 text-gray-800"><c:out value="${group.g_name }"/> </h1>
	                    <h1 class="h5 ml-4 mt-1 mb-3 text-gray-800">그룹 설명 : <c:out value="${group.g_content }"/></h1>
	                    </div>
	                    <div class="col-lg-1"></div>
						<br> 
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
    <script src="../../resources/js/group/group_create.js"></script>
</body>
</html>