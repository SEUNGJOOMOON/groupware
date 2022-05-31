<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<script src="/build/jquery.datetimepicker.full.min.js"></script>
		<meta charset="EUC-KR">
		<content tag="local_script">
			<script>
			$(function() {
			      $("#date").datepicker(
			            {
			               dateFormat : 'yy-mm-dd',
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
			});
				function check1() {
					if ($("input[name=partner_contact]").val() == "") {
						alert("회사연락처를 입력하시오.");
						return false;
					}
					if ($("input[name=partner_address]").val() == "") {
						alert("회사주소입력하시오");
						return false;
						
					}
					if ($("input[name=partner_reprecent]").val() == "") {
						alert("담당자를입력하시오");
						return false;
					}
					if ($("input[name=partner_companyname]").val() == "") {
						alert("업체명을입력하시오");
						return false;
					}
			
					if ($("input[name=partner_contractdate]").val() == "") {
						alert("거래일시를입력하시오");
						return false;
					}
					if ($("input[name=partner_tradeitem]").val() == "") {
						alert("거래품목을입력하시오");
						return false;
					}
					if ($("input[name=partner_image1]").val() == "") {
						alert("회사로고를 넣으시오");
						return false;
					}
					alert("등록완료");
					return true;
				}
			</script>
		</content>
		<title>고객사 등록</title>
	</head>
	<body>
		<form name="Enrollment" method="post" action="/Groupware/PTM/PTMInsert.ma" enctype="multipart/form-data" onsubmit="return check1();">
			<div style="margin: 0 auto">
				<table class="table" style="text-align: center; height: 100%; text-align: right; margin-top: 10px; width: 100%;">
					<colgroup>
						<col style="width: 50%">
						<col style="width: 50%">
					</colgroup>
					<tr>
						<td style="text-align: center; vertical-align: middle" colspan="2">
							<h4>고객사 등록</h4>
						</td>
					<tr>
						<td ROWSPAN="8">
							<img src="../img/logo.png" style="width: 100%; height: 100%">
						</td>
					</tr>
					<tr>
						<td>업체명:<input type="text" name="partner_companyname" style="width: 75%" /></td>
					</tr>
					<tr>
						<td>회사연락처:<input type="text" name="partner_contact"  style="width: 75%" /></td>
					</tr>
					<tr>
						<td>주소:<input type="text" name="partner_address" style="width: 75%" /></td>
					</tr>
					<tr>
						<td>담당자:<input type="text" name="partner_reprecent" style="width: 75%" /></td>
					</tr>
					<tr>
						<td>거래일시:<input type="text" id="date" name="partner_contractdate" style="width: 75%" /></td>
					</tr>
					<tr>
						<td>거래품목:<input type="text" name="partner_tradeitem" style="width: 75%" /></td>
					</tr>
					<tr>
					    <td style="text-align:left">이미지:<input type="file" name="partner_image1" style="width: 100%"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="취소" class="btn btn-primary" style="float: right" onclick="javascript:history.go(-1);">
							<input type="submit" value="등록" class="btn btn-primary" style="float: right" >
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>