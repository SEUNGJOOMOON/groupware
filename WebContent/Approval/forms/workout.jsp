<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>출장신청서</title>
<content tag="local_script"> <script>
	$(function() {
		$("#div_seekPartner").css("left", "244px");
		$("#div_seekPartner").css("top", "247px");
		$("#btn_seekPartner").click(function() {
			$('#div_seekPartner').show();
			alert("방문 고객사가 2곳 이상인 경우 해당 고객사만큼 신청서를 작성하세요.");
		});
		$("#btn_seekpartner").click(
				function() {
					$.ajax({
						type : "post",
						url : "/Groupware/Approval/common/seekPartner.jsp",
						data : "seekPartnerName="
								+ $("input[name='seekPartnerName']").val(),
						dataType : "xml",
						success : function(xml) {
							var listTr;
							$("#partner_list  > tbody > tr").not(":first").remove();
							if($(xml).find("partnerlist").find("partner").length > 0){
								$(xml).find("partnerlist").find("partner").each(function(i){
									listTr = "";
									listTr += "<tr>";
									listTr += "<td style='text-align:center' name='partnername'>" + $(this).find("partnercompanyname").text() + "</td>";
									listTr += "<td style='text-align:center'>" + $(this).find("partneraddress").text() + "</td>";
									listTr += "<td>" + '<input type="button" onclick="setDestination(' + i + ');" class="btn btn-primary" value="등록">' + "</td>";
									listTr += "</tr>";
									$("#partner_list  > tbody:last").append(listTr);
								});
							}else{
								$("#partner_list  > tbody:last").append("<tr><td colspan='2'>검색결과가 없습니다.</td></tr>");
							}
						},
						error : function(xhr, status, error) {
							alert("실패!!!!!!! 사유 : " + xhr.status);
						}
					});
				});
	});
	function setDestination(i){
		$("#workout_destination").val($("td[name='partnername']").eq(i).text());
		$('#div_seekPartner').hide();
	};
</script> </content>
</head>
<body>
	<input type="hidden" name="documentType" value="html">
	<input type="hidden" name="documentNumber" value="50">
	<table class="table table-bordered">
		<colgroup>
			<col style="width: 20%">
			<col style="width: 80%">
		</colgroup>

		<tr>
			<th>신청자</th>
			<td><input type="text" data-type="approval" style="width: 99%"
				id="workout_applier" class="form-control"></td>
		</tr>
		<tr>
			<th>출장시작일</th>
			<td><input type="date" data-type="approval" style="width: 50%"
				id="workout_sdate" class="form-control"></td>
		</tr>
		<tr>
			<th>출장종료일</th>
			<td><input type="date" data-type="approval" style="width: 50%"
				id="workout_edate" class="form-control"></td>
		</tr>
		<tr>
			<th>사유</th>
			<td><input type="text" data-type="approval" style="width: 99%"
				id="workout_reason" class="form-control"></td>
		</tr>
		<tr>
			<th>목적지</th>
			<td><input type="text" readonly="readonly" data-type="approval"
				style="width: 78%; float: left" class="form-control"
				id="workout_destination"> <input type="button" value="고객사검색"
				class="btn btn-primary" id="btn_seekPartner"
				style="margin-left: 18px;"><br></td>
		</tr>
	</table>
	<div class="panel panel-primary"
		style="width: 400px; position: absolute; display: none"
		id="div_seekPartner">
		<div class="panel-heading">
			고객사 검색<i class="glyphicon glyphicon-remove"
				style="float: right; cursor: pointer;"
				onclick="$('#div_seekPartner').hide();"></i>
		</div>
		<div class="panel-body">
			<table class="table" id="partner_list">
				<colgroup>
					<col style="width: 30%;">
					<col style="width: 51%">
					<col style="width: 19%">
				</colgroup>
				<tr>
					<td style="text-align: center">고객사</td>
					<td colspan="2"><input type="text" class="form-control"
						name="seekPartnerName" placeholder="전체검색은 미입력" style="width: 70%; float: left"> <input
						type="button" class="btn btn-primary" id="btn_seekpartner"
						value="검색" style="float: right"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>