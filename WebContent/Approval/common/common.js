function validationCheck() {
	if ($("input[name=documentType]").val() == 'html') {
		$("[data-type=approval]").each(function() {
			if (!$(this).val()) {
				alert("모든 내용을 작성해주세요.");
				return false;
			}
		});

	}else if ($("input[name=documentType]").val() == 'editor') {
		if(CKEDITOR.instances.content.getData() == ""){
			alert("내용을 입력해주세요.");
			return false;
		}
	}
	if (!$("#form_title").val()) {
		alert("제목을 입력해주세요.");
		return false;
	}
	if (!$("#form_approvalLineXml").val()) {
		alert("결재자 및 참조자를 지정해주세요.");
		return false;
	}
	return true;
}