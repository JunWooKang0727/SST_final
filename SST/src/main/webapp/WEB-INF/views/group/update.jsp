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
	                        <div class="p-5">
	                            <form action="/group/update" method="post" class="user">
	                                <div class="form-group row">	
	                            		<input type="hidden" id="g_num" name="g_num" value="${group.g_num}"/>
	                                    <div class="col-sm-3 pl-3 mb-3 mb-sm-0 text-center align-self-center">
	                                        	그룹명 
	                                    </div>
	                                    <div class="col-sm-9">
	                                        <input name="g_name" type="text" class="form-control rounded-pill" id="exampleLastName"
	                                            value="<c:out value="${group.g_name }"/>" />
	                                    </div>
	                                </div>
	                                <div class="form-group row">
	                                    <div class="col-sm-3 pl-3 mb-3 mb-sm-0 text-center align-self-center">
	                                        	카테고리
	                                    </div>
	                                    <div class="col-sm-9">
	                                    	<select name="g_category" class="form-control rounded-pill" >
	                                    		<option value="초등학교" <c:if test="${group.g_category eq '초등학교'}">selected</c:if>>초등학교</option>
	                                    		<option value="중학교" <c:if test="${group.g_category eq '중학교'}">selected</c:if>>중학교</option>
	                                    		<option value="고등학교" <c:if test="${group.g_category eq '고등학교'}">selected</c:if>>고등학교</option>
	                                    		<option value="License" <c:if test="${group.g_category eq 'License'}">selected</c:if>>자격증</option>
	                                    		<option value="Language" <c:if test="${group.g_category eq 'Language'}">selected</c:if>>어학시험</option>
	                                    		<option value="etc" <c:if test="${group.g_category eq 'etc'}">selected</c:if>>기타</option>
	                                    	</select>
	                                    </div>
	                                </div>
	                                <div class="form-group row">
	                                    <div class="col-sm-3 pl-3 mb-3 mb-sm-0 text-center align-self-center">
	                                        	스터디 설명
	                                    </div>
	                                    <div class="col-sm-9">
	                                        <textarea name="g_content" class="form-control rounded" id="exampleLastName"><c:out value="${group.g_content }"/></textarea>
	                                    </div>
	                                </div>
	                                <div class="form-group row">
	                                    <div class="col-sm-3 pl-3 mb-3 mb-sm-0 text-center align-self-center">
	                                        	모집인원
	                                    </div>
	                                    <div class="col-sm-9">
	                                        <input name="g_memnum" type="text" class="form-control rounded-pill" id="exampleLastName"
	                                            value="<c:out value="${group.g_memnum }"/>">
	                                    </div>
	                                </div>
	                                <div class="form-group row">
	                                    <div class="col-sm-3 pl-3 mb-3 mb-sm-0 text-center align-self-center">
	                                        	공개여부
	                                    </div>
	                                    <div class="col-sm-9 row">
		                                     	<div class="col-sm-6 form-check text-center">
													<input class="form-check-input groupsecrets" type="radio" name="g_secreat" 
													id="exampleRadios1" value="open" <c:if test="${group.g_secreat eq 'open'}">checked</c:if> />
													<label class="form-check-label" for="exampleRadios1">
												    	공개
													</label>
												</div>
												<div class="col-sm-6 form-check text-center">
													<input class="form-check-input groupsecrets" type="radio" name="g_secreat" 
													id="exampleRadios2" value="secret" <c:if test="${group.g_secreat eq 'secret'}">checked</c:if>/>
													<label class="form-check-label" for="exampleRadios2">비공개
													</label>
												</div>
	                                    </div>
	                                </div>
	                                <div class="form-group row">
	                                    <div class="col-sm-3 pl-3 mb-3 mb-sm-0 text-center align-self-center">
	                                        	비밀번호
	                                    </div>
	                                    <div class="col-sm-9">
	                                        <input name="g_passwd" type="password" class="form-control rounded-pill" id="inputgrouppw"
	                                            <c:if test="${group.g_secreat ne null }">value="${group.g_passwd }"</c:if>
	                                            <c:if test="${group.g_passwd eq null }">readonly</c:if>/>
	                                    </div>
	                                </div>
	                                <hr>
	                                <input id ="groupupdatebtn" type="submit" class="btn btn-primary btn-user btn-block" value="그룹정보수정" />
	                                <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" /><br>
	                                <input data-oper="grouplist" type="button" class="btn btn-primary btn-user btn-block groupbtn" value="이전으로" />
	                                <input data-oper="groupdelete" type="button" class="btn btn-google btn-user btn-block groupbtn" value="그룹삭제" />
	                            </form>
	                        </div>
	                    </div>
	                    <div class="col-lg-1"></div>
					<br> 
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
    <script src="../../resources/js/group/group_read.js"></script>
    <script src="../../resources/js/group/group_update.js"></script>
</body>
</html>