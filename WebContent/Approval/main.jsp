<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전자결제 홈</title>
</head>
<content tag="local_script">
<link rel="stylesheet" href="../js/jquery.treeview.css" />
<link rel="stylesheet" href="../js/screen.css" />
<link rel="stylesheet" href="../js/flexslider.css" />
<script src="../js/jquery.flexslider-min.js" type="text/javascript"></script>
<script src="../js/jquery.cookie.js" type="text/javascript"></script> <script
	src="../js/jquery.treeview.js" type="text/javascript"></script> <script
	type="text/javascript">
		$(function() {
			$("#tree").treeview({
				collapsed : true,
				animated : "medium",
				control : "#sidetreecontrol",
				persist : "location"
			});/* 전자결제 양식리스트 로드 */

			

		});

		function opendocument(document_type) {
			open("/Groupware/Approval/forms/" + document_type, "",
					"width=900, height=700, status=1");
		}
		function opendocument_read(document_type) {
			open("/Groupware/Approval/forms/read/" + document_type, "",
					"width=900, height=700, status=1");
		}
	</script>
<style>
.table th, td {
	text-align: center;
}

.table {
	width: 89%;
	margin: 0 auto;
}

.write_document {
	width: 48%;
	font-size: 15px;
	margin-top: 10px;
	float: left;
}

.temp {
	width: 48%;
	font-size: 15px;
	margin-top: 10px;
	float: right;
}

ul {
	list-style: none;
}

.main_top {
	width: 100%;
	height: 350px;
}

.progress_tbl {
	height: 350px;
}

@media screen and (max-width: 480px) {
	.write_document {
		width: 100%;
		clear: both;
	}
	.prograss_tbl {
		display: none;
	}
	.main_top {
		display: none;
	}
	.temp {
		width: 100%;
	}
}
}
</style>
</content>
<body>
	<div class="main_top">
		<div class="progress_tbl document panel panel-default">
			<div class="panel-heading" style="font-size: 15px;">
				<b>지난 행사</b>
			</div>
			<div class="panel-body" style="height:300px">
				<img src="../img/mainbanner1.jpg" style="width:100%;height:100%"/>
			</div>
		</div>
	</div>
	<div id="sidetree" style="width:100%;margin-top:20px" class="panel panel-default">
		<div class="treeheader panel-heading">
			<b>결재문서작성</b>
		</div>
		<div class="panel-body">
			<ul id="tree">
				<li><strong>기안서</strong>
					<ul>
						<li><a href="javascript:opendocument('draft.jsp');">기안서</a></li>
					</ul></li>
				<li><strong>휴가계</strong>
					<ul>
						<li><a href="javascript:opendocument('vacation.jsp');">정기휴가</a></li>
					</ul>
					<ul>
						<li><a href="javascript:opendocument('sickleave.jsp');">병가</a></li>
					</ul>
					<ul>
						<li><a href="javascript:opendocument('halfleave.jsp');">반차계</a></li>
					</ul></li>
				<li><strong>출장계</strong>
					<ul>
						<li><a href="javascript:opendocument('workout.jsp');">출장신청서</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
<!-- 	<div id="sidetree" class="temp panel panel-default">
		<div class="treeheader panel-heading">
			<b>다른거 넣어야함</b>
		</div>
		<div class="panel-body"></div>
	</div> -->
</body>
</html>