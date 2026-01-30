<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body onload="passwdFocus()">
	<center style="margin-top: 350px; margin-bottom:50%;"><h2>글 수정</h2>

	<form method="post" action="modifyView" name="passwordform" onsubmint="return passwdCheck()">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	
	<table>
		<tr>
			<th colspan="2">비밀번호를 다시 확인해 주세요!</th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input class="input" type="password" name="passwd" maxlength="10"></td>
		</tr>
		
		<tr>
			<th colspan="2">
				<input class="inputButton" type="submit" value="글수정">
				<input class="inputButton" type="button" value="수정취소" onclick="window.location='list?pageNum=${pageNum}'">
			</th>	
	</table>
	</form></center>
</body>
</html>