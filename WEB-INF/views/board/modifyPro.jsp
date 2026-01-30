<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<html>
<body>
	<h2>글수정</h2>
	
	<c:if test="${cnt == 0}">
		<script type="text/javascript">
			errorAlert();
		</script>
	</c:if>
	
	<c:if test="${cnt != 0}">
		<script type="text/javascript">
			alert("글이 수정되었습니다.!!");
		</script>
		<c:redirect url="list?pageNum=${pageNum}" />	
	</c:if>
</body>
</html>