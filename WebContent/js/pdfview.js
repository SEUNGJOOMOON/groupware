
$.fn.pdfview = function(url) {
	var error_message = "";
	if (typeof url == "string") {
		var pdfTag = "<embed src='" + url + "'type='application/pdf'";
		pdfTag += "style='overflow: auto; width: 100%; height: 100%;'>";
		$(this).html(pdfTag);
/*		$(this).attr("width",width);
		$(this).attr("height",height);*/


	} else {
		error_message = "[PDFError]Url is not vailed";
		console.log(error_message);
		return false;
	}
};