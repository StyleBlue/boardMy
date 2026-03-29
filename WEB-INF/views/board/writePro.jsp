<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<center style="margin-top: 350px; margin-bottom: 50%;">
	<h2>글쓰기 - 처리 페이지</h2>
	<c:if test="${cnt == 0}">
		<a>작성 실패</a>
	</c:if>
	<c:if test="${cnt != 0}">
		<c:redirect url="list"></c:redirect>
	</c:if>
	</center>
</body>
</html>
