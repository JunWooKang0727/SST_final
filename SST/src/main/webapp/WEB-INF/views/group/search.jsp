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
                        <h1 class="h3 mb-0 text-gray-800">그룹 검색</h1>
                    </div>
                    <!-- 상세검색 지정 & 검색-->
                    
                    <!-- 검색창 -->
                    <form id="searchForm" action="/group/search" method="get">
	                    <div class="row mt-2"></div>
	                    <div class="row mb-3">
	                    	<div class="col-lg-2"></div>
	                    	<div class="col-lg-2">
	                    		<select name="type" class="detailsearch form-control form-control-lg rounded-pill" id="exampleFormControlSelect1">
							      <option value=""
							      	<c:out value="${pageMaker.cri.type == null ? 'selected' : '' }" />
							      >선택</option>
							      <option value="T"
							      	<c:out value="${pageMaker.cri.type eq 'T' ? 'selected' : '' }"/>
							      >제목</option>
							      <option value="C"
							      	<c:out value="${pageMaker.cri.type eq 'C' ? 'selected' : '' }" />
							      >내용</option>
							      <option value="TC"
							      	<c:out value="${pageMaker.cri.type eq 'TC' ? 'selected' : '' }"/>
							      >제목or내용</option>
							    </select>
	                    	</div>
	                    	<div class="col-lg-5">
	                    		<div class="input-group-lg">
	                    			<input type="text" name="keyword" class="form-control rounded-pill" 
	                    			value = '<c:out value="${pageMaker.cri.keyword }" />' aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" />
	                    			<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }" />' />
	                    			<input type="hidden" name="amount" value='<c:out value="${pageMaker.cri.amount }" />' />
	                    		</div>
		                    </div>
	                    	<div class="col-lg-1">
	                    		<button id="search_group" type="button" class="btn btn-success btn-user btn-block rounded-pill btn-lg">
	                                    	검색
	                            </button>
	                    	</div> 
	                    </div>
                    </form>
                    
                    <!-- 카테고리 -->
                    <!-- <div class="row col-lg-12 mb-3">
                   				<div class="col-lg-3"></div> 
                    			<div class="col-lg-1 form-check form-check-inline ml-5">
							  		<input class="form-check-input" type="checkbox" id="search" value="highschool">
							  		<label class="form-check-label" for="inlineCheckbox1">고등학생</label>
								</div>
								<div class="col-lg-1 form-check form-check-inline">
								  <input class="form-check-input" type="checkbox" id="search" value="college">
								  <label class="form-check-label" for="inlineCheckbox2">대학생</label>
								</div>
								<div class="col-lg-1 form-check form-check-inline">
								  <input class="form-check-input" type="checkbox" id="search" value="employment">
								  <label class="form-check-label" for="inlineCheckbox3">취업</label>
								</div>
								<div class="col-lg-1 form-check form-check-inline">
								  <input class="form-check-input" type="checkbox" id="search" value="language">
								  <label class="form-check-label" for="inlineCheckbox3">어학</label>
								</div>
								<div class="col-lg-1 form-check form-check-inline">
								  <input class="form-check-input" type="checkbox" id="search" value="etc">
								  <label class="form-check-label" for="inlineCheckbox3">기타</label>
								</div>
								<div class="col-lg-3"></div> 
                    </div> -->
                    
                    
                    <!-- 생성한 그룹 -->  
                    <div class="row">
                       <c:forEach items="${totalgroup }" var="group">
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
	                                            	<!-- <a href="/group/read?g_num=<c:out value="${group.g_num }"/>" class="h5 mb-1 font-weight-bold text-gray-800"></a> -->
	                                            	<a class="moveDetail h5 mb-1 font-weight-bold text-gray-800" href="<c:out value="${group.g_num}" />">
	                                            	<c:out value="${group.g_name }"/>
	                                            	</a>
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
                	
                	<!-- 페이징 -->
                	<div class="row">
                		<div class="col-lg-2"></div>
                		<div class="col-lg-8">
                			<nav aria-label="...">
  								<ul class="pagination justify-content-center">
  								
  									<c:if test="${pageMaker.prev }">
	  									<li class="pagi-btn page-item previous">
									      <a class="page-link" href="${pageMaker.startPage -1 }">Prev</a>
									    </li>
  									</c:if> 
  									 
  									
								    <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
								    	<li class="pagi-btn page-item ${pageMaker.cri.pageNum == num ? 'active' :'' } ">
								    		<a class="page-link" href="${num }">${num }</a>
								    	</li>
								    </c:forEach>
								    
								    <c:if test="${pageMaker.next }">
								    	<li class="pagi-btn page-item next">
								      		<a class="page-link" href="${pageMaker.endPage +1 }">Next</a>
								    	</li>
								    </c:if>
							  </ul>
							</nav>
                		</div>  
                		<div class="col-lg-2"></div> 
                		
                	</div>
                	
                	<!-- 페이지 클릭시 동작하는 부분 -->
                	<form id="actionForm" action="/group/search" method="get">
                		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }" />
                		<input type="hidden" name="amount" value="${pageMaker.cri.amount }"/>
                		<input type="hidden" name="type" value="${pageMaker.cri.type }" />
                		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }" />
                	</form>

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
    <script src="../../resources/js/group/group_search.js"></script>
</body>
</html>