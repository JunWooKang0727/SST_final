<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.sst.domain.CalendarTodoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="http://code.jquery.com/jquery-1.10.2.js">
	
</script>
<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>SST</title>
<!-- Custom fonts for this template-->
<link href="../../../resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../../../resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="../../../resources/css/personalstudy.css" rel="stylesheet">
<style>
#startbtn, #stopbtn, .studydone {
	margin-top: 10px;
	background-color: #2c3e50;
	border-radius: 3px;
	color: white;
}

#studyhour, #studymilisec, #studymin, #studysec {
	margin-top: 10px;
	color: #1a252f;
	font-size: 30px;
	font-weight: bold;
}

.inputTodoText_content {
	color: #1a252f;
	font-size: 15px;
	font-weight: bold;
}

.inputStudy {
	float: left;
	color: #1a252f;
	font-size: 15px;
	font-weight: bold;
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
						<h1 class="h3 mb-0 text-gray-800">개인공부</h1>
					</div>
					<!-- Content Row -->
					<div class="row">
						<div class="col1">
							<form action="/personalstudy/create" method="post">
								<div class="inputStudy">분류 :</div>
								<select name="ps_category" placeholder="카테고리를 선택하세요">
									<option selected>선택</option>
									<option value="certificate">자격증</option>
									<option value="language">어학</option>
									<option value="schoolexam">학교시험</option>
									<option value="etc">기타</option>
								</select> <br>
								<div class="inputStudy">장소 :</div>
								<select name="ps_place" placeholder="카테고리를 선택하세요">
									<option selected>선택</option>
									<option value="cafe">카페</option>
									<option value="house">집</option>
									<option value="reading room">독서실</option>
									<option value="library">도서관</option>
									<option value="etc">기타</option>
								</select> <br>
								<div>
									<div>
										<span id="studyhour">00</span>
										<!-- 시 -->
										<span>:</span><span id="studymin">00</span>
										<!-- 분 -->
										<span>:</span> <span id="studysec">00</span>
										<!--초-->
										<span>.</span> <span id="studymilisec">00</span>
										<!--밀리초-->

									</div>
									<input type="hidden" name="ps_time" id="inputTime">
									<div>
										<ul id="recordlist">

										</ul>
										<!--중간 기록할 리스트-->
									</div>
									<div>
										<button type="button" id="startbtn">START</button>
										<!--시작/재시작/기록 버튼-->
										<button type="button" id="stopbtn">STOP</button>
										<!--스톱 버튼-->
									</div>
								</div>
								<input type="submit" value="공부완료" class="studydone"> <br>
								<br>
						</div>
						<div class="col"/><div class="col"><div class="col"><div class="col"><div class="col">
						<div class="col">

							
							<form id="createForm" method="post" action="/personalcrawlmake/create">
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"
/>
							
							<div class = "inputPath">
							<label>경로 : <input type="text" name = "Path"></label>
							<div class = "inputPath">
							<label>경로 : <input type="text" name = "path"></label>
							</div>
							<div class = "storeFile">
							<label>저장될 파일명 : <input type="text" name = "exFileName"></label>
							</div>
							<div class = "searchT">
							<label>검색할 문제 : <input type="text" name = "searchT"></label>
							</div>
							</div>

							<div class="inputSubject">과목 :

							<form method="get" action="form-action.html">

								<label><input type="radio" name="subject" value="kor">
									국어</label> <label><input type="radio" name="subject"
									value="mat"> 수학</label> <label><input type="radio"
									name="subject" value="eng"> 영어</label> <label><input
									type="radio" name="subject" value="soc"> 사회탐구</label> <label><input
									type="radio" name="subject" value="sci"> 과학탐구</label>
								
							</form></div>
							<br>

							<div class="selectYear">년도 :
							<select name="ps_year" placeholder="카테고리를 선택하세요">
								<option selected>선택</option>
								<option value="2015">2015</option>
								<option value="2016">2016</option>
								<option value="2017">2017</option>
								<option value="2018">2018</option>
								<option value="2019">2019</option>
								<option value="2020">2020</option>
								<option value="2021">2021</option>

							</select> 
</div>
<br>
							<form>
								<fieldset>
									<legend>월선택</legend>
									3월 <input type="checkbox" name="month" value="mar" checked />
									4월 <input type="checkbox" name="month" value="apr" /> 5월 <input
										type="checkbox" name="month" value="may" /> 6월 <input
										type="checkbox" name="month" value="jun" /> 7월 <input
										type="checkbox" name="month" value="jul" /> 9월 <input
										type="checkbox" name="month" value="set" /> 10월 <input
										type="checkbox" name="month" value="oct" /> 11월 <input
										type="checkbox" name="month" value="nov" /> 12월 <input
										type="checkbox" name="month" value="dec" />

								</fieldset>

<<<<<<< HEAD
							</form><br>
							<p>
									<input type="submit" value="Submit"> <input
										type="reset" value="Reset">
								</p>
=======
							</div>
							<br>
								<div>
								<div class="createBtn btn btn-secondary">야얄야얆ㅁㅈㄷㄹ</div>
								<input type="submit" value="문제만들기"> 
								<input type="reset" value="설정초기화">
								</div>
							</form>
>>>>>>> refs/remotes/origin/main
						</div>
						</form>
					</div>
<<<<<<< HEAD


=======
					
					<div class="row">
						<div class="downArea">
							
						</div>
					
					</div>
>>>>>>> refs/remotes/origin/main
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
	</div>
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Bootstrap core JavaScript-->
	<script src="../../../resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="../../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script
		src="../../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="../../../resources/js/sb-admin-2.min.js"></script>
	<script type="text/javascript">
		var stTime = 0
		var endTime = 0
		var timerStart
		var hour
		var min
		var sec
		var milisec
		var startBtn = document.getElementById('startbtn')
		var stopBtn = document.getElementById('stopbtn')
		var recordList = document.getElementById('recordlist')
		startBtn.addEventListener('click', function() {

			// RECORD
			if (this.innerText == 'RECORD' && milisec) {
				//console.log(hour,min, sec, milisec)
				var li = document.createElement('li')
				li.style.color = "black"
				li.innerText = hour + ' : ' + min + ' : ' + sec + ' : '
						+ milisec
				if (!recordList.firstChild) {
					recordList.append(li)
				} else {
					recordList.insertBefore(li, recordList.firstChild)
				}
				return false
			}
			this.innerText = 'RECORD'
			if (!stTime) {
				stTime = Date.now() // 최초 START
			} else {
				stopBtn.innerText = 'STOP'
				stTime += (Date.now() - endTime) // RESTART
			}
			timerStart = setInterval(function() {
				var nowTime = new Date(Date.now() - stTime)

				hour = addZero(nowTime.getHours() - 9)//기본초기값이 대한민국 표준이 9시로 맞춰져 있음
				min = addZero(nowTime.getMinutes())
				sec = addZero(nowTime.getSeconds())
				milisec = addZero(Math.floor(nowTime.getMilliseconds() / 10))
				document.getElementById('studyhour').innerText = hour
				document.getElementById('studymin').innerText = min
				document.getElementById('studysec').innerText = sec
				document.getElementById('studymilisec').innerText = milisec
			}, 1)
		})
		stopBtn.addEventListener('click', function() {
			var hour = $('#studyhour').text();
			var min = $('#studymin').text();
			var sec = $('#studysec').text();
			var milisec = $('#studymilisec').text();
			alert(hour + ':' + min + ' : ' + sec + ' : ' + milisec
					+ $(".input_contents"));
			$('#inputTime').val(hour + ":" + min + ":" + sec + ":" + milisec);
			if (timerStart) {
				clearInterval(timerStart) // STOP
				if (this.innerText == 'STOP') {
					endTime = Date.now()
					this.innerText = 'RESET'
					startBtn.innerText = 'RESTART'
				} else { // RESET
					stTime = 0
					hour = 0
					min = 0
					sec = 0
					milisec = 0
					document.getElementById('studyhour').innerText = '00'
					document.getElementById('studymin').innerText = '00'
					document.getElementById('studysec').innerText = '00'
					document.getElementById('studymilisec').innerText = '00'
					startBtn.innerText = 'START'
					this.innerText = 'STOP'
					timerStart = null
					recordList.innerHTML = ''
				}
			}
		})
		function addZero(num) {
			return (num < 10 ? '0' + num : '' + num)
		}
		
		$(function(){
			
			$('.createBtn').click(function(){
				
				var fdata = $('#createForm').serialize();
				
				$.ajax({
					url:'/personalcrawlmake/create',
					type:'GET',
					data : fdata,
					dataType:'json',
					success : function(data){
						$('.downArea').empty();
						var str="";
						
						$(data).each(function(i, obj) {
							
							str += '<div class="fileItem">'
								
							+ '<img class="pdficon" src="../../../resources/img/pdf.png">'
							+ '<a class="downlink" href="/personalcrawlmake/download?fileName='+ obj +'">'
							+ obj + '</a>'
							+ '</div>';
							$('.downArea').append(str);
							str='';
						});
						
						$('#createForm')[0].reset();
					}
					
					
					
				});
				
				
			});
			
			
			
		});
	</script>
</body>
</html>