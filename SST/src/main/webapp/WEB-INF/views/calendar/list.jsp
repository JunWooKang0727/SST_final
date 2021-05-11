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

<meta charset="utf-8">
<link href='../../../resources/fullcalendar-5.6.0/lib/main.css'
	rel='stylesheet' />
<script src='../../../resources/fullcalendar-5.6.0/lib/main.js'></script>
<script src="../../../resources/vendor/jquery/jquery.min.js"></script>
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
<script>

  function realDate(listStartDate){
  	
  	var date = "";
  	date+=listStartDate.substring(4,7);
  	date+='-';
  	date+=listStartDate.substring(8,10);
  	date+='-';
  	date+=listStartDate.substring(24);
  	return date;
  }
  
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    
    var calendar = new FullCalendar.Calendar(calendarEl, {
    	

      headerToolbar: {
    	height: '10%',
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      initialDate: '2020-09-12',
      navLinks: true, // can click day/week names to navigate views
      //이거 false로 처리하고 fc-daygrid-day-number 여기에 이벤트 처리 하고싶음.
      //overlap: true이면 스케쥴 이동이 가능하다, false면 스케쥴이동이불가하다.
      //시간 미 입력시 all-day이벤트로 된다.
      businessHours: true, // display business hours
      editable: true,
      selectable: true,
      locale : 'ko',
      //dayMaxEvents: true,
      events: [
    	  
	    	  <c:forEach var="todo" items="${list}">
	    	  
	    	  {
	    	    title: '${todo.t_title}',
	    	    <c:set var="start" value="${todo.t_startdate}" />
	    	    <c:set var='startmonth' value = "${fn:substring(start,4,7)}"/>
	    	    <c:if test="${startmonth eq 'Jan'}">
	    	    start : '${fn:substring(start,24,28)}-01-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Feb'}">
	    	    start : '${fn:substring(start,24,28)}-02-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Mar'}">
	    	    start : '${fn:substring(start,24,28)}-03-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Apr'}">
	    	    start : '${fn:substring(start,24,28)}-04-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'May'}">
	    	    start : '${fn:substring(start,24,28)}-05-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Jun'}">
	    	    start : '${fn:substring(start,24,28)}-06-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Jul'}">
	    	    start : '${fn:substring(start,24,28)}-07-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Aug'}">
	    	    start : '${fn:substring(start,24,28)}-08-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Sep'}">
	    	    start : '${fn:substring(start,24,28)}-09-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Oct'}">
	    	    start : '${fn:substring(start,24,28)}-10-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Nov'}">
	    	    start : '${fn:substring(start,24,28)}-11-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    <c:if test="${startmonth eq 'Dec'}">
	    	    start : '${fn:substring(start,24,28)}-12-${fn:substring(start,8,10)}',
	    	    </c:if>
	    	    
	    	    	<c:set var="end" value="${todo.t_enddate}" />
		    	    <c:set var='endmonth' value = "${fn:substring(end,4,7)}"/>
		    	    <c:if test="${endmonth eq 'Jan'}">
		    	    end : '${fn:substring(end,24,28)}-01-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Feb'}">
		    	    end : '${fn:substring(end,24,28)}-02-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Mar'}">
		    	    end : '${fn:substring(end,24,28)}-03-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Apr'}">
		    	    end : '${fn:substring(end,24,28)}-04-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'May'}">
		    	    end : '${fn:substring(end,24,28)}-05-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Jun'}">
		    	    end : '${fn:substring(end,24,28)}-06-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Jul'}">
		    	    end : '${fn:substring(end,24,28)}-07-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Aug'}">
		    	    end : '${fn:substring(end,24,28)}-08-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Sep'}">
		    	    end : '${fn:substring(end,24,28)}-09-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Oct'}">
		    	    end : '${fn:substring(end,24,28)}-10-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Nov'}">
		    	    end : '${fn:substring(end,24,28)}-11-${fn:substring(end,8,10)}',
		    	    </c:if>
		    	    <c:if test="${endmonth eq 'Dec'}">
		    	    end : '${fn:substring(end,24,28)}-12-${fn:substring(end,8,10)}',
		    	    </c:if>
	    	  },
			  </c:forEach>
         {
        	//매주 지정 요일 반복 1 => 월요일
          title: '자격증공부',
          start: '2020-09-03',
          end : '2020-09-20'
          
        },
        {
            title: '단어암기”',
            start: '2020-09-01',
            end: '2020-09-15',
            color: '#4776fd',
          },
        {
            title: '운동',
            start: '2020-09-01T13:00:00',
            end: '2020-09-25T14:00:00',
            daysOfWeek: [ 1, 2 ],
              
            url: 'http://www.naver.com',
            color: '#7dc5d7',
            catid: '99',
            cat: 'all-events',
          },  
           
      ]
    });

    calendar.render();
  });
</script>
<script type="text/javascript">
$(function(){
	$(".toDos").click(function(e){
	e.preventDefault();  
	$(this).parent().parent().prev().find("li").addClass('doneto');
	  alert($(this).parent().parent().prev().find("li").html())
		});
	$(".nontoDos").click(function(e){
		e.preventDefault();  
		$(this).parent().parent().prev().find("li").removeClass('doneto');
		  alert($(this).parent().parent().prev().find("li").html())
			});
	$(".toDoList .1").addClass('doneto');
	});


</script>
<style>
.doneto {
	text-decoration: line-through;
	color: #00af91;
}
/*   body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  } */
#calendar {
	max-width: 900px;
	margin: 0 auto;
}

.toDoListHeader {
	margin-top: 40px;
}

.toDoList {
	padding-inline-start: 0px;
	margin-left: 20px;
}

.buttonBasic {
	background-color: #2c3e50;
	border-radius: 3px;
}

.buttonBasic a {
	color: white;
}

.toDoListHeader {
	color: #1a252f;
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
						<h1 class="h3 mb-0 text-gray-800">캘린더</h1>
					</div>
					<!-- Content Row -->
					<div class="row">
						<div class="col-md-9">
							<div id='calendar'></div>
						</div>
						<div class="col-md-3">
							<div class="row">
								<button class="buttonBasic">
									<a href="/SST/fullcalendar-5.6.0/Calendar2/insertTodoForm.do">Todo작성</a>
								</button>
							</div>

							<div class="row">
								<h5 class="toDoListHeader">Todo List</h5>
							</div>
							<div class="row">
								<c:forEach var="CalendarTodoVO" items="${list}">
									<div class="col-md-4">
										<ul class="toDoList">
											<li class="${CalendarTodoVO.t_todocheck}">${CalendarTodoVO.t_title}</li>
										</ul>
									</div>
									<div class="col-md-8">
										<button class="buttonBasic">
											<a class="toDos"
												href="/SST/fullcalendar-5.6.0/Calendar2/CheckTodo.do?t_num=${CalendarTodoVO.t_num }">완료</a>
										</button>
										<button class="buttonBasic">
											<a class="nontoDos"
												href="/SST/fullcalendar-5.6.0/Calendar2/NonCheckTodo.do?t_num=${CalendarTodoVO.t_num }">미완료</a>
										</button>
										<button class="buttonBasic">
											<a
												href="/SST/fullcalendar-5.6.0/Calendar2/DeleteTodo.do?t_num=${CalendarTodoVO.t_num }">삭제</a>
										</button>
									</div>
								</c:forEach>


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

	<!-- Bootstrap core JavaScript-->

	<script
		src="../../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script
		src="../../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="../../../resources/js/sb-admin-2.min.js"></script>
</body>
</html>