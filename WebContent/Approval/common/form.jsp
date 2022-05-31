<%@ page contentType="text/html; charset=utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="ko">
<head>
<script src="../../js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="../common/form_style.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="../../js/alertifyjs/css/alertify.css" rel="stylesheet">
<script src="../../js/alertifyjs/alertify.js"></script>
<script src="../../js/ckeditor/ckeditor.js"></script>
<script src="../../js/util.js"></script>
<script src="/Groupware/Approval/common/common.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="../../js/jquery.ajaxfileupload.js"></script>
<script>
	$(function() {
		/* 조직도를 위한 유저정보 */
		$.ajax({
			type : "post",
			url : "/Groupware/Approval/common/Organization.jsp",
			dataType : "xml",
			success : function(xml) {
				var organizationHtml = "<ul>";
				$(xml).find("Organization").find("Dept").each(
						function() {
							organizationHtml += "<li>"
									+ $(this).find("DeptName").text() + "<ul>";
							$(this).find("Emp").each(
									function() {
										organizationHtml += "<li>"
												+ "<span onclick=setUserbox("
												+ $(this).find("EmpNo").text()
												+ ")>"
												+ $(this).find("EmpName")
														.text()

												+ "</span></li>";
									});
							organizationHtml += "</ul></li>"
						});
				organizationHtml += "</ul>"
				$("#organization").html(organizationHtml);
				$("#organization").jstree({
					"types" : {
						"default" : {
							"icon" : "glyphicon glyphicon-user"
						},
						"input" : {
							"icon" : "glyphicon glyphicon-ok"
						},
					},
					"plugins" : [ "types" ]
				});
				$("#userbox").draggable();
			},
			error : function(xhr, status, error) {
				alert("실패!!!!!!! 사유 : " + xhr.status);
			}
		});
		$("#btn_showogranizaion").click(function() {
			$("#userbox").show();
		});
		/* 결재라인 Xml 생성 */
		$("#btn_approval_regi")
				.click(
						function() {
							var isSelectedApproval = false;//결재가 1명이상 선택되었는지.
							var approvalLineXml = "<approvalline>";
							approvalLineXml += "<approvalinfor>";
							approvalLineXml += "<lineempno>"
									+ "${sessionScope.user_empno}"
									+ "</lineempno>";
							approvalLineXml += "<lineempname>"
									+ "${sessionScope.user_name}"
									+ "</lineempname>";
							approvalLineXml += "<lineapprovaltype>" + "Draft"
									+ "</lineapprovaltype>";
							approvalLineXml += "</approvalinfor>";//기안자 정보
							$("span[name='approvalLineInfor']")
									.each(
											function() {
												approvalLineXml += "<approvalinfor>"
												approvalLineXml += "<lineempno>"
														+ $(this)
																.children(
																		"input[name='lineEmpNo']")
																.eq(0).val()
														+ "</lineempno>";
												approvalLineXml += "<lineempname>"
														+ $(this)
																.children(
																		"input[name='lineEmpName']")
																.eq(0).val()
														+ "</lineempname>";
												approvalLineXml += "<lineapprovaltype>"
														+ $(this)
																.children(
																		"input[name='lineApprovalType']")
																.eq(0).val()
														+ "</lineapprovaltype>";
												approvalLineXml += "</approvalinfor>";
												if ($(this)
														.children(
																"input[name='lineApprovalType']")
														.eq(0).val() == 'approval') {
													isSelectedApproval = true;
												}
											});
							approvalLineXml += "</approvalline>";
							if (isSelectedApproval) {
								$("#form_approvalLineXml").val(approvalLineXml);
								$("#userbox").hide();
								alertify.alert("결재정보가 등록되었습니다.");
								setApprovalLineTbl(approvalLineXml);
							} else {
								approvalLineXml = "";
								alert("결재권자가 선택되지 않았습니다.");
							}
						});

		/* 조직도 내 결재라인 테이블 그리기 */
		$("input[name='register_approval']")
				.click(
						function() {
							$("#tbl_approvalline").show();
							$("#btn_approval_regi").show();
							var view_approvalType;//보여지는 결제타입
							var approvalType;//hidden의 결제타입
							
							if ($(this).val() == '결재지정') {
								view_approvalType = '결재';
								approvalType = 'approval';
								if(!$("#previous_approval").val()){//이전 결재권자
									
								}
							} else {
								view_approvalType = '참조';
								approvalType = 'reference';
							}
							$('#tbl_approvalline > tbody:last')
									.append(
											'<tr><td style="text-align:center">'
													+ $("#hidden_empName")
															.val()
													+ '</td><td style="text-align:center">'
													+ $("#hidden_empNo").val()
													+ '</td><td style="text-align:center">'
													+ view_approvalType
													+ '</td><td style="text-align:center">'
													+ "<input type='button' id='delete_approval' onclick='deleteApproval(this);' class='btn btn-primary' value='삭제'>"
													+ "<span name='approvalLineInfor'>"
													+ "<input type='hidden' value='"
													+ $("#hidden_empNo").val()
													+ "' name='lineEmpNo'>"
													+ "<input type='hidden' value='" + approvalType + "' name='lineApprovalType'>"
													+ "<input type='hidden' value='"
													+ $("#hidden_empName")
															.val()
													+ "' name='lineEmpName'></span>"
													+ '</td></tr>');
						});
		/* 파일업로드 */
		$("#btn_fileupload").click(
				function() {
					var file = $("#attach_file");
					if (!file.val()) {
						alert("선택된 파일이 없습니다.");
						return false;
					}
					var form = $('[name=frm_fileupload]')[0];
					var formData = new FormData(form);
					$.ajax({
						type : "post",
						url : "/Groupware/Approval/FileUpload.ap",
						async : false,
						cache : false,
						contentType : false,
						processData : false,
						dataType : "xml",
						data : formData,
						success : function(xml) {
							$("#form_filename").val(
									$(xml).find("filename").text());
							$("#form_fileurl").val(
									$(xml).find("fileurl").text());
							alert("파일이 정상적으로 업로드되었습니다.");
							$("#div_file").hide();
							$("#div_fileshow").show();
							$("#display_file").text($(xml).find("filename").text());
							
						},
						error : function(xhr, status, error) {
							alert("파일이 업로드에 실패했습니다. 관리자에게 문의하세요. 에러코드 : "
									+ xhr.status);
						}
					});

				});

		/* 기안하기 버튼클릭 */
		$("#btn_draft")
				.click(
						function() {
							if (validationCheck()) {
								var contentXml;//양식 내용 xml
								if ($("input[name='documentType']").val() == "editor") {//양식이 editor 양식일 때
									contentXml = "<approvalcontent>";
									/* 			contentXml += "<title>" + $("input[name='form_title']").val()
														+ "</title>"; */
									contentXml += "<bodycontent>"
											+ CKEDITOR.instances.content
													.getData()
											+ "</bodycontent>";//ckeditor내용
									contentXml += "</approvalcontent>";
									$("#form_approvalContentXml").val(
											contentXml);
								} else if ($("input[name='documentType']")
										.val() == "html") {//양식이 html 양식일 때
									contentXml = "<approvalcontent>";
									$("input[data-type='approval']").each(
											function() {
												contentXml += "<"
														+ $(this).attr("id")
														+ ">" + $(this).val()
														+ "</"
														+ $(this).attr("id")
														+ ">"
											});
									contentXml += "</approvalcontent>";
									$("#form_approvalContentXml").val(
											contentXml);
								}

								var $form = $('<form></form>');//양식정보 동적Form 생성
								$form.attr('method', 'post');
								$form.appendTo('body');

								var approvalDocumentType = $('<input type="hidden" value="'
										+ $("input[name='documentType']").val()
										+ '" name="approvaldocumentType">');
								var approvalContentXml = $('<input type="hidden" value="'
										+ $("#form_approvalContentXml").val()
										+ '" name="approvalContentXml">');
								var approvalLineXml = $('<input type="hidden" value="'
										+ $("#form_approvalLineXml").val()
										+ '" name="approvalLineXml">');
								var approvalTitle = $('<input type="hidden" value="'
										+ $("#form_title").val()
										+ '" name="approvalTitle">');
								var approvalDocumentNumber = $('<input type="hidden" value="'
										+ $("input[name='documentNumber']")
												.val()
										+ '" name="approvaldocumentNumber">');
								var filename = $('<input type="hidden" value="'
										+ $("#form_filename").val()
										+ '" name="approvalfilename">');
								var fileurl = $('<input type="hidden" value="'
										+ $("#form_fileurl").val()
										+ '" name="approvalfileurl">');
								$form.append(approvalDocumentType).append(
										approvalContentXml).append(
										approvalLineXml).append(approvalTitle)
										.append(approvalDocumentNumber).append(
												filename).append(fileurl);

								$.ajax({
									type : "post",
									url : "/Groupware/Approval/Draft.ap",
									dataType : "xml",
									data : $form.serialize(),
									success : function(xml) {
										alert($(xml).find("message").text());
										self.close();

									},
									error : function(xhr, status, error) {
										alert("예상치 못한 에러가 발생하였습니다. 에러코드 :  "
												+ xhr.status);
									}
								});
							}
						});
	});
	function deleteApproval(obj) {
		var tr = $(obj).parent().parent();
		tr.remove();
	}
	/* 조직도 내 유저정보 상세테이블 */
	function setUserbox(empNo) {
		$
				.ajax({
					type : "post",
					url : "/Groupware/Approval/common/getUserDetail.jsp",
					data : "empno=" + empNo,
					dataType : "xml",
					success : function(xml) {
						$("#display_empName").html(
								$(xml).find("User").find("EmpName").text());
						$("#hidden_empName").val(
								$(xml).find("User").find("EmpName").text());
						$("#display_empNo").html(
								$(xml).find("User").find("EmpNo").text());
						$("#hidden_empNo").val(
								$(xml).find("User").find("EmpNo").text());
						$("#display_empContract").html(
								$(xml).find("User").find("EmpContract").text());
						$("#hidden_empContract").val(
								$(xml).find("User").find("EmpContract").text());
						$("#display_empPosition").html(
								$(xml).find("User").find("EmpPosition").text());
						$("#hidden_empPosition").val(
								$(xml).find("User").find("EmpPosition").text());
						if ($(xml).find("User").find("EmpProfileimg").text() == 'noimg') {
							$("#user_profile").attr("src",
									"/Groupware/img/noimg.gif");//이미지가 없다면
						} else {
							$("#user_profile").attr(
									"src",
									$(xml).find("User").find("EmpProfileimg")
											.text());
						}
						$("#div_userDetail").show();
					},
					error : function(xhr, status, error) {
						alert("실패!!!!!!! 사유 : " + error);
					}
				});
	}

	/* 양식 상단 결재라인 테이블 그리기 */
	function setApprovalLineTbl(approvalLineXml) {
		$('#top_approvalline > tbody > tr').remove();
		$("#display_reference").html("");
		$(approvalLineXml)
				.find("approvalinfor")
				.each(
						function() {

							var approvalType;
							if ($(this).find("lineapprovaltype").text() == 'approval') {
								approvalType = "결재";
							} else if ($(this).find("lineapprovaltype").text() == 'reference') {
								approvalType = "참조";
							} else if ($(this).find("lineapprovaltype").text() == 'Draft') {
								approvalType = "기안";
							}

							if (approvalType == '결재' || approvalType == '기안') {
								var tblTags = "<tr><th>";
								tblTags += approvalType + "</th>";
								tblTags += "<td>"
										+ $(this).find("lineempname").text()
										+ "</td></tr>";
								$('#top_approvalline > tbody:last').append(
										tblTags);
							} else if (approvalType == '참조') {
								$("#display_reference").append(
										$(this).find("lineempname").text()
												+ " ");
							}

						});

	}
</script>
<decorator:getProperty property="page.local_script"></decorator:getProperty>
</head>
<body>
	<div class="panel panel-primary"
		style="width: 600px; position: absolute; z-index: 10000; display: none;"
		id="userbox">
		<div class="panel-heading">
			조직도 <i class="glyphicon glyphicon-remove"
				style="float: right; cursor: pointer;"
				onclick="$('#userbox').hide();"></i>
		</div>
		<div class="panel-body">
			<div class="well well-lg"
				style="float: left; border: 1px solid #ddd; padding-top: 10px; padding-bottom: 10px; padding-left: 10px; padding-right: 10px;"
				id="organization"></div>
			<div style="float: left; display: none" id="div_userDetail">
			<input type="hidden" id="previous_approval">
				<table class="table" style="margin-left: 30px;">
					<tr>
						<td rowspan="2" style="text-align: center;"><img src=""
							id="user_profile" style="width: 70px; height: 70px;"></td>
						<td>이름 :</td>
						<td colspan="2"><span id="display_empName"></span><input
							type="hidden" id="hidden_empName"></td>
					</tr>
					<tr>
						<td>사번 :</td>
						<td colspan="3"><span id="display_empNo"></span><input
							type="hidden" id="hidden_empNo"></td>
					</tr>
					<tr>
						<td>내선 :</td>
						<td><span id="display_empContract"></span><input
							type="hidden" id="hidden_empDeptContract"></td>
						<td>직급 :</td>
						<td><span id="display_empPosition"></span><input
							type="hidden" id="hidden_empPosition"></td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: right"><input
							type="button" class="btn btn-primary" name="register_approval"
							value="결재지정"> <input type="button"
							class="btn btn-primary" value="참조지정" name="register_approval"></td>
					</tr>
				</table>
			</div>
			<div>
				<table class="table" id="tbl_approvalline" style="display: none">
					<colgroup>
						<col style="width: 25%">
						<col style="width: 25%">
						<col style="width: 25%">
						<col style="width: 25%">
					</colgroup>
					<tr>
						<td style="text-align: center;">이름</td>
						<td style="text-align: center;">사번</td>
						<td style="text-align: center;">결제종류</td>
						<td style="text-align: center;">비고</td>
					</tr>
				</table>
				<input type="button" value="결재정보등록"
					style="float: right; display: none;" id="btn_approval_regi"
					class="btn btn-primary">
			</div>
		</div>
	</div>
	<div class="outside">
		<div class="form_title">
			<decorator:title />
		</div>
		<!-- 후에 db연동 -->
		<!-- 양식명 -->
		<div class="form_btn">
			<input type="button" class="btn btn-primary" id="btn_showogranizaion"
				value="결재라인"><input type="button" class="btn btn-primary"
				style="margin-left: 5px" value="기안하기" id="btn_draft">
		</div>
		<!-- 버튼영역 -->
		<div>
			<div class="form_line">
				<table class="table table-bordered" id="top_approvalline">
					<tr>
						<th>기안자</th>
						<td>${sessionScope.user_name}</td>
					</tr>
				</table>
			</div>
			<!-- 결제라인 -->
			<div class="form_body">
				<table class="table table-bordered">
					<colgroup>
						<col style="width: 20%">
						<col style="width: 80%">
					</colgroup>
					<tr>
						<th>제목</th>
						<td><input type="text" name="form_title" id="form_title"
							style="width: 99%" class="form-control"> <input
							type="hidden" id="form_approvalLineXml"> <input
							type="hidden" id="form_approvalContentXml"> <input
							type="hidden" id="form_filename"><input type="hidden"
							id="form_fileurl"></td>
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
						<td><span id="display_reference"></span><input type="hidden"></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<form id="frm_fileupload" enctype="multipart/form-data"
								method="post" name="frm_fileupload">
								<div id="div_file">
									<input type="file" name="attach_file" id="attach_file"
										style="float: left; width: 250px"> <input
										type="button" value="Upload" id="btn_fileupload"
										style="float: left" class="btn btn-primary">
								</div>
								<div id="div_fileshow">
									<span id="display_file"></span>
								</div>
							</form>
						</td>
					</tr>
				</table>
			</div>
			<!-- 첨부파일 -->
		</div>
	</div>
</body>
</html>