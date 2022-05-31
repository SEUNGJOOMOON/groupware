<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.1.1.min.js"></script>
<!-- JQuery Load -->
<script src="js/typeit-master/dev/typeit.js"></script>
<!-- Typeit Plugin Load -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<link href="js/alertifyjs/css/alertify.css" rel="stylesheet">
<script src="js/alertifyjs/alertify.js"></script>
<title>Group Ware</title>
<script>
	$(function() {
		$('.typeIt').typeIt({
			strings : [ '웹 어플리케이션 전문 SI 기업','LASTLINE SOFT.Inc' ]
		});
		$("#btn_login").click(
				function() {
					if (!$("#txt_empnum").val()) {
						alertify.alert("사원번호를 입력해주세요.");
					} else if (!$("#txt_pass").val()) {
						alertify.alert("비밀번호를 입력해주세요.!");
					} else {
						$.ajax({
							type : "post",
							url : "/Groupware/HRmag/login.hm",
							data : "empno=" + $("#txt_empnum").val()
									+ "&password=" + $("#txt_pass").val(),
							dataType : "xml",
							success : function(xml) {
								var result = $(xml).find("result").text();
								var message = $(xml).find("message").text();

								if (result == "successed") {
									alertify.alert(message, function() {
										alertify.message('잠시 후 메인화면으로 이동합니다.');
										setTimeout(function() {
											location.href = "/Groupware/Approval/main.jsp";
										}, 3000);
									});
								} else {
									alertify.alert(message);
								}

							},
							error : function(xhr, status, error) {
								alert("예상치 못한 에러가 발생하였습니다. 관리자에게 문의하세요. 에러코드 : " + xhr);
							}

						});
					}
				})
	});
</script>
<style>
body {
	background-color: #EAEAEA;
}

.inner_div {
	margin: auto;
	width: 300px;
	height: 300px;
	margin-top: 20%;
	background-color: white;
}

.outer_div {
	background-color: white;
	width: 100%;
	max-width:400px;
	height: 400px;
	margin: auto;
	border-radius: 25px;
}
</style>
</head>
<body>
	<div class="outer_div">
		<div class="inner_div">
			<form id="login_infor">
				<div style="text-align: center">
					<img src="img/logo.png"
						style="width: 150px; height: 70px; margin-top: 50px">
						<br/>
						<br/>
					<p class="typeIt"></p>
				</div>
				<input type="text" id="txt_empnum" name="empno" style="width: 99%;"
					class="form-control" placeholder="사원번호를 입력해주세요."> <input
					type="password" id="txt_pass" name="password" style="width: 99%;"
					class="form-control" placeholder="비밀번호를 입력해주세요."> <input
					type="button" id="btn_login" value="입장하기"
					style="float: right; margin-top: 15px"
					class="btn btn-lg btn-primary btn-block">
			</form>
		</div>
	</div>
</body>
</html>