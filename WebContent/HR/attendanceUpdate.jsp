<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<content tag="local_script"> <script type="text/javascript">

$(function(){
	$("#att").bind("click",function(){
		$("#submit_check").val("att");
	});
	
	$("#goff").bind("click",function(){
		$("#submit_check").val("goff");
	});
	
});

	function fsubmit() {
		var submit_check = $("#submit_check").val();

		var today = new Date();
	    var h = today.getHours();
	    var m = today.getMinutes();
	 	var time = h+":"+m;
	 	var temp = time.split(":");
	 	var insertTime = Number(temp[0]+temp[1]);

	 	var subTime = insertTime - 900;

		if(subTime > 0 && submit_check =="att"){
			 var retVal = prompt("지각사유를 입력하시오.", "");
			 $('[name="late_reason"]').val(retVal);
			 var retVal2 = $('[name="late_reason"]').val();
			 
			 if($('[name="late_reason"]').val()==null){
				 alert("지각사유를 입력하시지 않았습니다. 다시 출근버튼을 눌러주세요");
				 return false;
			 }else{
				 return true;
			 }
		}else{
			return true;
		}
	
	}
</script> </content>
</head>
<body>
<form action="/Groupware/HR/update.at" method="post" id="attForm" name="attForm" onsubmit="return fsubmit()">
	<table class="table table-striped">
	
		<tr>
			<th colspan="2" style="text-align:center">출퇴근 관리</th>
		</tr>
		<tr>
			<th>사원명:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="emp_name" value="${a.emp_name}" readonly></td>
		</tr>
		<tr>
			<th>사원번호:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="emp_no" value="${a.emp_no}" readonly></td>
		</tr>
		<tr>
			<th>부서명:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="dept_name" value="${a.dept_name}" readonly></td>
		</tr>
		<tr hidden>
			<th>출근시간:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="attend_time" value="${a.attend_time}" readonly></td>
		</tr>
		<tr hidden>
			<th>지각사유:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="late_reason" value="${a.late_reason}" readonly></td>
		</tr>
		<tr hidden>
			<th>출/퇴근</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" id="submit_check"></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
			<input type="submit" value="출근" class="btn btn-primary" id="att" name="att">
			<input type="submit" value="퇴근" class="btn btn-primary" id="goff" name="goff">

			</td>
		</tr>
	</table>
</form>
	<table class="table table-striped">
		<tr style="text-aling:center">
			<th>사원이름</th>
			<th>사원번호</th>
			<th>부서</th>
			<th>날짜</th>
			<th>출근시간</th>
			<th>퇴근시간</th>
			<th>지각사유</th>
		</tr>
	 <tr>
			<th><input type="text" style="background-color:transparent;border:0 solid black;" name="emp_name" value="${a.emp_name}" readonly></th>
			<th><input type="text" style="background-color:transparent;border:0 solid black;" name="emp_no" value="${a.emp_no}" readonly></th>
			<th><input type="text" style="background-color:transparent;border:0 solid black;" name="dept_name" value="${a.dept_name}" readonly></th>
			<th><input type="text" style="background-color:transparent;border:0 solid black;" name="emp_date" value="${a.emp_date}" readonly></th>
			<th><input type="text" style="background-color:transparent;border:0 solid black;" name="attend_time" value="${a.attend_time}" readonly></th>
			<th><input type="text" style="background-color:transparent;border:0 solid black;" name="getoff_time" value="${a.getoff_time}" readonly></th>
			<th><input type="text" style="background-color:transparent;border:0 solid black;" name="late_reason" value="${a.late_reason}" readonly></th>
			
		</tr> 
	</table>

</body>
</html>