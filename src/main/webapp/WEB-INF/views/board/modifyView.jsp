<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<center style="margin-top: 350px; margin-bottom:50%;">
<h2>글수정</h2>
<c:if test="${cnt == 0}">
	<script type="text/javascript">
		errorAlert(passwdError);
	</script>
</c:if>

<c:if test="${cnt != 0}">
	<body onload="modifyFocus()">
		<form method="post" action="modifyPro" name="modifyform" onsubmit="return modifyCheck();">
		
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="pageNum" value="${pageNum}">
		
		<table>
			<tr>
				<th colspan="2">수정할 정보를 입력해 주세요</th>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${bdto.writer}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input class="input" type="text" name="subject" maxlength="50" value="${bdto.subject}" style="float:left; width: 260px;"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea class="input" rows="10" cols="38" name="content" style="width:260px; height:150px;">${bdto.content}</textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input class="input" type="password" name="passwd" maxlength="10" value="${bdto.passwd}" style="float:left; width:110px;"></td>
			</tr>
		
		<tr>
			<th colspan="2">
				<input class="inputButton" type="submit" value="글수정">
				<input class="inputButton" type="reset" value="수정취소" onclick="window.location='list?pageNum=${pageNum}'">
			</th>
		</tr>
		</table>
		
		</form>
	</body>
</c:if>
</center>
</html>
