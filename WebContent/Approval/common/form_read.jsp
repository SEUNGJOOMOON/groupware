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
<link rel="stylesheet" href="common/form_style.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="../js/alertifyjs/css/alertify.css" rel="stylesheet">
<script src="../js/alertifyjs/alertify.js"></script>
<decorator:getProperty property="page.local_script"></decorator:getProperty>
</head>
<script>
	$(function() {
		bindContent("${documentInformation.docContent}");//내용입력
		setApprovalLineTbl("${documentInformation.docApprovalline}");//결재라인 테이블
		$("#viewApprovalTitle").html("${documentInformation.docTitle}");

	});
	/* 양식 상단 결재라인 테이블 그리기 */
	function setApprovalLineTbl(approvalLineXml) {
		$(approvalLineXml)
				.find("approvalinfor")
				.each(
						function() {

							var approvalType;
							if ($(this).find("lineapprovaltype").text() == 'approval') {
								approvalType = "결제";
							} else if ($(this).find("lineapprovaltype").text() == 'reference') {
								approvalType = "참조";
							} else if ($(this).find("lineapprovaltype").text() == 'Draft') {
								approvalType = "기안";
							}

							if (approvalType == '결제' || approvalType == '기안') {
								var tblTags = "<tr><th>";
								tblTags += approvalType + "</th>";
								tblTags += "<td>"
										+ $(this).find("lineempname").text()
										+ "</td></tr>";
								$('#top_approvalline > tbody:last').append(
										tblTags);
							} else if (approvalType == '참조') {
								$("#viewApprovalReferences").append(
										$(this).find("lineempname").text()
												+ " ");
							}

						});
		$("#btn_approval")
				.click(
						function() {
							var docNum = ${documentInformation.docNum};
							$.ajax({
								type : "post",
								url : "/Groupware/Approval/Approval.ap",
								data : "docNum=" + docNum,
								dataType : "xml",
								success : function(xml) {
									alert($(xml).find("message").text());
									opener.location.reload(true);
									self.close();
								},
								error : function(xhr, status, error) {
									alert("예상치 못한 에러가 발생하였습니다. 에러코드 :  " + xhr.status);
								}

							});
						});

		$("#btn_reject")
				.click(
						function() {
							var docNum = ${documentInformation.docNum};
							$.ajax({
								type : "post",
								url : "/Groupware/Approval/Reject.ap",
								data : "docNum=" + docNum,
								dataType : "xml",
								success : function(xml) {
									alert($(xml).find("message").text());
									opener.location.reload(true);
									self.close();
								},
								error : function(xhr, status, error) {
									alert("예상치 못한 에러가 발생하였습니다. 에러코드 :  " + xhr.status);
								}

							});
						});
/* 		$("#btn_redraft")
				.click(
						function() {
							location.href = "/Groupware/Approval/ReDraft.ap?docNum=${documentInformation.docNum}";
						}); */

	}
	function bindContent(xml) {
		$(xml).filter("approvalcontent").children().each(function() {
			$("#" + this.localName).html($(this).html());
		});
	}
</script>
<body>
	<div class="outside">
		<div class="form_title">
			<decorator:title />
		</div>
		<!-- 후에 db연동 -->
		<!-- 양식명 -->
		<div class="form_btn">
			<c:if
				test="${param.listType!='mylist'&&param.listType!='reference'&&documentInformation.docNextApprovalEmpno==user_empno&&(documentInformation.docState=='Draft'||documentInformation.docState=='Approval')}">
				<input type="button" id="btn_approval" class="btn btn-primary"
					value="승인" style="margin-left: 5px">
				<input type="button" id="btn_reject" class="btn btn-primary"
					value="반려">
			</c:if>
			<%-- <c:if test="${documentInformation.docState=='Rejected'&&param.listType!='reference'}">
				<input type="button" id="btn_redraft" class="btn btn-primary"
					value="재기안">
			</c:if> --%>
		</div>
		<!-- 버튼영역 -->
		<div>
			<div class="form_line">
				<table class="table table-bordered" id="top_approvalline">
					<tr></tr>
				</table>
			</div>
			<!-- 결제라인 -->
			<div class="form_sign">
				<table class="table table-bordered">
					<tr>
					</tr>
				</table>
			</div>
		</div>
		<!-- 결제서명 -->
		<div class="form_body">
			<table class="table table-bordered">
				<colgroup>
					<col style="width: 20%">
					<col style="width: 80%">
				</colgroup>
				<tr>
					<th>제목</th>
					<td><span id="viewApprovalTitle"></span></td>
				</tr>
			</table>
			<decorator:body />
		</div>
		<!-- 실제 양식 내용 -->
		<div class="form_attach">
			<table class="table table-bordered">
				<colgroup>
					<col style="width: 20%">
					<col style="width: 80%">
				</colgroup>
				<tr>
					<th>참조</th>
					<td><span id="viewApprovalReferences"></span></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><span><a href="${documentInformation.docFileurl}" download>${documentInformation.docFileName}</a></span></td>
				</tr>
			</table>
		</div>
		<!-- 첨부파일 -->
	</div>
</body>
</html>
