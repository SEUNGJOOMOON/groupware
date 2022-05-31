<%@ page contentType="text/html; charset=utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="ko">
<head>
<script src="../js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="../style/common.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="../js/alertifyjs/css/alertify.css" rel="stylesheet">
<script src="../js/alertifyjs/alertify.js"></script>
<decorator:getProperty property="page.local_script"></decorator:getProperty>
</head>
<body>
	<c:if test="${user_name != null}">
		<div id="logo" style="text-align: center">
			<img src="/Groupware/img/logo.png"
				style="width: 150px; height: 70px; margin: 0 auto">
		</div>
		<nav class="navbar navbar-default navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/Groupware/Approval/main.jsp">LASTLINE
						SOFT.Inc</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">전자결제 
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="/Groupware/Approval/main.jsp">전자결제 홈</a></li>
								<li><a href="/Groupware/Approval/List.ap?listtype=mylist">내문서</a></li>
								<li><a
									href="/Groupware/Approval/List.ap?listtype=unfinished">미결문서</a></li>
								<li><a href="/Groupware/Approval/List.ap?listtype=finished">완료문서</a></li>
								<li><a
									href="/Groupware/Approval/List.ap?listtype=reference">참조문서</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">업무지원<span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="/Groupware/PTM/PTMList.ma?pageNum=1">고객사
										관리</a></li>
								<li><a href="/Groupware/RoomReserves/main.jsp">회의실 예약</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">사내게시판
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="/Groupware/board/boardList.bo?pageNum=1">공지사항</a></li>
								<li><a href="/Groupware/EDL/list.gw?pageNum=1">문서함</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">인사관리
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="/Groupware/HR/list.at">근태관리</a></li>
								<li><a href="/Groupware/HR/HRIndex.jsp">개인정보수정</a></li>
							</ul></li>
						<c:if test="${user_position == '관리자'}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false">관리자<!-- 관리자메뉴 -->
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="/Groupware/Attendance_mag/attendance_index.jsp">근태통합관리</a></li>
									<li><a href="/Groupware/HR_mag/HRmag_Index.jsp">사원통합관리</a></li>
								</ul></li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
		<!-- 상단메뉴 -->
		<div class="leftMenu">
			<div style="width: 180px; height: 200px; border: 5px solid #EAEAEA">
				<div style="margin-top: 10px; margin-left: 10px">
					<c:if test="${user_imgsrc!='noimg'}">
						<img src="${user_imgsrc }" style="width: 48px; height: 48px;">
						<b>${user_name }</b> [${user_position }]
					</c:if>
					<c:if test="${user_imgsrc=='noimg'}">
						<img src="/Groupware/img/noimg.gif"
							style="width: 48px; height: 48px;">
						<b>${user_name }</b> [${user_position }]
					</c:if>
				</div>
				<div style="margin-top: 10px; margin-left: 10px; text-align: center">
					<b>KH LASTLINE</b><br /> ${user_dept }
				</div>
				<div style="text-align: center">
					<a href="/Groupware/common/LogoutProcess.jsp">로그아웃</a>
				</div>
				<div style="margin-left: 30px; margin-top: 10px">
<!-- 					<button type="button" class="btn btn-primary">출근</button>
					<button type="button" class="btn btn-primary">퇴근</button> -->
				</div>
			</div>
			<br />
			<div style="width: 180px; height: 100px; border: 5px solid #EAEAEA">
				<img src="/Groupware/img/banner1.png"
					style="width: 100%; height: 100%">
			</div>
			<!-- 배너1 -->
			<br />
			<div style="width: 180px; height: 100px; border: 5px solid #EAEAEA">
				<img src="/Groupware/img/banner2.png"
					style="width: 100%; height: 100%">
			</div>
			<!-- 배너2 -->
			<br />
			<div style="width: 180px; height: 100px; border: 5px solid #EAEAEA">
				<img src="/Groupware/img/banner3.png"
					style="width: 100%; height: 100%">
			</div>
			<!-- 배너3 -->
		</div>
		<div class="main_div">
			<decorator:body />
		</div>
	<!-- 	<div class="panel-footer footer" style="bottom:0px">
			COPYRIGHT 2017 BY KHAcademy BUILDER.<br /> ALL RIGHTS RESERVED.
		</div> -->
		<!-- 하단 -->
	</c:if>
	<c:if test="${user_name == null}">
		<div style="width:600px;margin:0 auto">
			잘못된 접근입니다. 관리자에게 문의하세요.
			<input type="button" value="돌아가기" class="btn btn-primary" onclick="location.href='http://localhost:8080/Groupware/index.jsp'">
		</div>
	</c:if>
</body>
</html>
