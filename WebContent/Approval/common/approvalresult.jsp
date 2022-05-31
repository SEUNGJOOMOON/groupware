<%@ page language="java" contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${param.resultcnt != 0 }">
	<result>
		<message>정상적으로 처리되었습니다.</message>
	</result>
</c:if>
<c:if test="${param.resultcnt == 0 }">
	<result>
		<message>예상치 못한 오류가 발생하였습니다. 관리자에게 문의하세요.</message>
	</result>
</c:if>