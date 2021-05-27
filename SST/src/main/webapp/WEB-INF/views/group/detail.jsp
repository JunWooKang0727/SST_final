<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                        <h1 class="h3 mb-0 text-gray-800">${group.g_name}</h1>
                    </div>
                    <!-- 페이지 본문 -->
                    <!-- 생성한 그룹 -->
                    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-2 justify-content-center">
                    	<ul class="nav">
						  <li class="nav-item">
						    <a class="nav-link active" href="/studynote/list?g_num=${group.g_num}">스터디 노트</a>
						  </li>
						  <li class="nav-item">
						    <a class="nav-link" href="#">출석체크</a>
						  </li>
						  <li class="nav-item">
						    <a class="nav-link" href="/studydata/list2?g_num=${group.g_num}">자료실</a>
						  </li>
						  <li class="nav-item">
						    <a class="nav-link" href="#">menu2</a>
						  </li>
						  <li class="nav-item">
						    <a class="nav-link" href="#">menu4</a>
						  </li>
						  <li class="nav-item">
						    <a class="nav-link" href="#">그룹 설정</a>
						  </li>
						</ul>
                    </nav>
                    <div class="row">
                    	<div class="col-xl-8">
                    		<div class="d-sm-flex align-items-center justify-content-between mb-4">
                        		<h1 class="h3 mb-0 text-gray-800">그룹일정(캘린더)</h1>
                        		<a class="float-right"> 스케줄 설정 </a>
                    		</div>
                    		<h5 class="ml-2">캘린더 위치</h5>
                    		<br><br><br><br><br><br>
                    	</div>
	                	<div class="col-xl-4">
                    		<div class="d-sm-flex align-items-center justify-content-between mb-4">
                        		<h1 class="h3 mb-0 text-gray-800">현재 접속 인원</h1>
                    		</div>
                    		<h5 class="ml-2">1. 가</h5>
                    		<h5 class="ml-2">2. 나</h5>
                    		<h5 class="ml-2">3. 다</h5>
                    		<br><br><br><br><br><br>
                    	</div>
                    </div>
                                        <div class="row">
                    	<div class="col-xl-8">
                    	</div>
	                	<div class="col-xl-4">
                    		<button type="button" class="btn btn-primary btn-block">화상채팅방</button>
                    		<button type="button" class="btn btn-primary btn-block">일반채팅방</button>
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