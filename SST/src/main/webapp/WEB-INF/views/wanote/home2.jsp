<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Coming Soon Bootstrap Template - Coming Soon</title>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="crossorigin"/>
    <link rel="preload" as="style" href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;700&amp;display=swap"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;700&amp;display=swap" media="print" onload="this.media='all'"/>
    <noscript>
      <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;700&amp;display=swap"/>
    </noscript>
    <link href="../../resources/css/bootstrap.min.css?ver=1.2.0" rel="stylesheet">
  <link href="../../resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css"> 
    <link href="../../resources/css/main.css?ver=1.2.0" rel="stylesheet">
  </head>
  <body id="top"><div class="site-wrapper">
  <div class="site-wrapper-inner">
    <div class="cover-container">
      <div class="masthead clearfix">
        <div class="inner">
          <h3 class="masthead-brand">S S T</h3>
          <nav class="nav nav-masthead">
            <a class="nav-link nav-social" href="#" tit,le="Facebook"><i class="fab fa-facebook-f" aria-hidden="true"></i></a>
            <a class="nav-link nav-social" href="#" title="Twitter"><i class="fab fa-twitter" aria-hidden="true"></i></a>
            <a class="nav-link nav-social" href="#" title="Youtube"><i class="fab fa-youtube" aria-hidden="true"></i></a>
            <a class="nav-link nav-social" href="#" title="Instagram"><i class="fab fa-instagram" aria-hidden="true"></i></a>
          </nav>
        </div>
      </div>      
      <div class="inner cover">
        <h1 class="cover-heading">Study Steady Together</h1>
        <p class="lead cover-copy">오질나게 공부해보자아~.</p>
        <security:authorize access="isAnonymous()">
        <p class="lead"><button type="button" class="btn btn-lg btn-default btn-notify"><a href="/member/login">로그인</a></button></p>
        </security:authorize>
      </div>
      <div class="mastfoot">
        <div class="inner">
          <p>&copy; SST. Design: <a href="https://templateflip.com/" target="_blank">TemplateFlip</a>.</p>
        </div>
      </div>

  </div>
</div>
<script src="../../resources/vendor/jquery/jquery.min.js"></script>
		<script src="../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script src="../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	
  </body>
</html>