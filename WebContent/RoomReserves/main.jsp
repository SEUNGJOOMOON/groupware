<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel='stylesheet' type='text/css'
	href='http://arshaw.com/css/fullcalendar.css?3' />

<link rel="stylesheet" type="text/css" href="/jquery.datetimepicker.css"/ >
<script src="/jquery.js"></script>
<script src="/build/jquery.datetimepicker.full.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type='text/javascript'></script>
<content tag="local_script"> <script>
	$(function() {
		$("#datepicker1").datepicker(
				{
					dateFormatS : 'yy-mm-dd',
					prevText : '이전 달',
					nextText : '다음 달',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					showMonthAfterYear : true,
					changeMonth : true,
					changeYear : true,
					yearSuffix : '년'
				});

		$("#find").click(
				function() {

					var cal = $('#datepicker1').val();
					var inf = $('input:radio[name="reserve_room"]:checked')
							.val();
					var tim = $('input:radio[name="reserve_time"]:checked')
							.val();
					var nam = $('#reserve_name').val();
					var num = $('#reserve_num').val();
					$.ajax({
						url : "/Groupware/RoomReserves/check.jsp",
						type : "post",
						dataType : "xml",
						data : "#datepicker1=" + cal + "&reserve_room=" + inf
								+ "&reserve_time=" + tim + "&#reserve_name="
								+ nam + "&#reserve_num=" + num,
						success : function(xml) {
							var str = $(xml).find("reserveInfor").find("date")
									.text();
							var strtime = $(xml).find("reserveInfor").find(
									"time").text();
							var strinfo = $(xml).find("reserveInfor").find(
									"info").text();
							var strname = $(xml).find("reserveInfor").find(
									"name").text();
							var strnum = $(xml).find("reserveInfor")
									.find("num").text();
							var result = str + "<br>" + strinfo + "<br>"
									+ strtime + "<br>" + strname + "<br>"
									+ strnum;
							$("#text").html(result);
						},
						error : function(request, status, error) {
							alert("에러:" + error);
						}
					});
				});
	});
	function checkdate() {
		var calen = $('#datepicker1').val();
		var tim = $('input:radio[name="reserve_time"]:checked').val();

		$
				.ajax({
					url : "checkdate.jsp",
					type : "post",
					dataType : "xml",
					data : "calendar=" + calen + "&time=" + tim,
					success : function(xml) {
						var str1 = $(xml).find("reserve").find("info").text();
						var result = "";
						var reservedRoomArr = new Array();
						reservedRoomArr = str1.split(";");
						for (var i = 0; i < reservedRoomArr.length; i++) {
							for (var c = 0; c < $("[name='reserve_room']").length; c++) {
								if ($("[name='reserve_room']").eq(c).val() == reservedRoomArr[i]) {
									$("[name='reserve_room']").eq(c).attr(
											'disabled', '');
									result += reservedRoomArr[i] + " ";
								}
							}
						}
						if (result == "") {
							alert("회의실을 선택하세요.");
						} else {
							alert(result + "은 예약되어 있습니다.");
						}

					},
					error : function(xhr, status, error) {
						alert(error);
					}
				});
	}
	function checkRSR() {
		if ($("#datepicker1").val() == "") {
			alert("예약날짜를 선택하세요");
			return false;
		}
		if ($('input:radio[name="reserve_time"]:checked').val() == null) {
			alert("회의시간을 선택하세요");
			return false;
		}
		if ($('input:radio[name="reserve_room"]:checked').val() == null) {
			alert("회의실을 선택하세요");
			return false;
		}
		if ($("#reserve_name").val() == "") {
			alert("이름을 입력하세요.");
			return false;
		}
		if ($("#reserve_num").val() == "") {
			alert("사원번호를 입력하세요.");
			return false;
		}
		alert("예약완료");
		return true;
	}
</script> </content>
</head>
<body>
	<form method="post" name="RSRForm"
		action="/Groupware/RoomReserves/RSRInsertAction.rs" onsubmit="return checkRSR();">
		<div style="width: 700px; margin: 0 auto">
			<table style="width: 100%" class="table">
				<colgroup>
					<col style="width: 40%">
					<col style="width: 20%">
					<col style="width: 20%">
					<col style="width: 20%">
				</colgroup>
				<tr height="10%">
					<th colspan="4" style="text-align: center">회의실 예약</th>
				</tr>
				<tr height="5%">
					<td style="text-align: center">예약 날짜</td>
					<td style="text-align: center">회의시간</td>
					<td style="text-align: center">회의실 예약</td>
					<td style="text-align: cneter">예약자정보</td>
				</tr>
				<tr height="65%">
					<td>날짜선택: <input type="text" id="datepicker1"
						name="reserve_date">
					</td>
					<td><input type="radio" name="reserve_time"
						value="08:00-10:00" onClick="checkdate();">08:00-10:00<br>
						<input type="radio" name="reserve_time" value="10:00-12:00"
						onClick="checkdate();">10:00-12:00<br> <input
						type="radio" name="reserve_time" value="13:00-15:00"
						onClick="checkdate();">13:00-15:00<br> <input
						type="radio" name="reserve_time" value="15:00-17:00"
						onClick="checkdate();">15:00-17:00<br> <input
						type="radio" name="reserve_time" value="17:00-19:00"
						onClick="checkdate();">17:00-19:00<br></td>
					<td><input type="radio" name="reserve_room" value="9층회의실">9층회의실<br>
						<input type="radio" name="reserve_room" value="8층회의실">8층회의실<br>
						<input type="radio" name="reserve_room" value="7층회의실">7층회의실<br>
						<input type="radio" name="reserve_room" value="6층회의실">6층회의실<br>
						<input type="radio" name="reserve_room" value="5층회의실">5층회의실<br>
					</td>
					<td>예약자:<span>${user_name}</span><input type="hidden" name="reserve_name"
						id="reserve_name" value="${user_name}"><br>사원번호:<span>${user_empno}</span><input type="hidden"
						name="reserve_num" id="reserve_num" value="${user_empno}">
					</td>
				</tr>
				<tr height="20%">
					<td colspan="4" valign=top>예약 내역
						<p id="text"></p>
					</td>
				</tr>
				<tr>
					<td colspan="4"><input type="button" class="btn btn-primary"
						id="find" value="예약내역 확인" style="float: right"> <input
						type="submit" id="OK" class="btn btn-primary" name="OK"
						value="예약하기" style="float: right"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>