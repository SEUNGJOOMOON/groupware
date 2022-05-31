<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="message" value="처리전"/>
<c:if test="${result == 'successed' }">
	<c:set var="message" value="${sessionScope.user_name}님 안녕하세요."/>
</c:if>
<c:if test="${result == 'failed' }">
	<c:set var="message" value="로그인에 실패하였습니다."/>
</c:if>
<login> <result>${result}</result><message>${message}</message>
</login>