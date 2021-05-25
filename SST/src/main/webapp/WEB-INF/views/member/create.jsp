<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SST</title>
    <link href="../../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <link href="../../resources/css/sb-admin-2.min.css" rel="stylesheet">
	<link href="/../../resources/css/member.css" rel="stylesheet">
</head>

<body class="main-bg memback">
    <div class="container">
        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">회원가입</h1>
                            </div>
                            <form class="user" action="/member/create" method="post"> 
                                <div class="form-group row">
                                    <div class="col-sm-9 mb-3 mb-sm-0">
                                        <input id="userid" name="m_id" type="text" class="form-control form-control-user"
                                            placeholder="아이디를 입력하세요">
                                    </div>
                                    <div class="col-sm-3">
                                    	<!-- submit 방지를 위해 type="button" 설정 -->
                                    	<button id="duplicate_id" type="button" class="btn btn-success btn-user btn-block">
                                    	중복체크
                                		</button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input name="m_pw" type="password" class="form-control form-control-user"
                                        placeholder="비밀번호를 입력하세요">
                                </div>
                                <div class="form-group">
                                    <input name="m_name" type="text" class="form-control form-control-user"
                                        placeholder="이름을 입력하세요 ex) name">
                                </div>
                                <div class="form-group">
                                    <input name="m_email" type="email" class="form-control form-control-user"
                                        placeholder="이메일을 입력하세요 ex) study@study.com">
                                </div>
                             	<div class="form-group">
                                    <input name="m_phone" type="text" class="form-control form-control-user"
                                        placeholder="핸드폰 번호를 입력하세요 ex) 010-1111-1111">
                                </div>
                                <div class="form-group">
                                    <input name="m_birth" type="text" class="form-control form-control-user"
                                        placeholder="생년월일을 입력하세요 ex) 991225">
                                </div>
                                <input type="submit" class="btn btn-primary btn-user btn-block" value="회원가입">
                            
                                <hr>
                               <!--  <a href="index.html" class="btn btn-google btn-user btn-block">
                                    <i class="fab fa-fw"></i> 구글로 회원가입
                                </a>
                                <a href="index.html" class="btn btn-warning btn-user btn-block">
                                    <i class="fab fa-fw"></i> 카카오톡으로 회원가입
                                </a> -->
                                <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
                            </form>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script src="../../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="../../resources/js/sb-admin-2.min.js"></script>
    <script src="../../resources/js/member/member_create.js"></script>
</body>

</html>