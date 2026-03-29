<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
<body onload="writeFocus()">
	<h2>글쓰기</h2>
	<form name="writeform" action="writePro" method="post" enctype="multipart/form-data" onsubmit="return writeCheck()">
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="ref_step" value="${ref_step}">
		<input type="hidden" name="ref_level" value="${ref_level}">
		
		<table>
			<tr>
				<th>작성자</th>
				<td>
					<input class="input" type="text" name="writer" maxlength="20" style="float:left; width:110px; background:#f4f4f4;" value="${sessionScope.loginMemberName}" readonly>
				</td>
			</tr>
			<tr>
				<th>게시글 비밀번호</th>
				<td>
					<input class="input" type="password" name="passwd" maxlength="10" style="float:left; width:110px;">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input class="input" type="text" name="subject" maxlength="50" style="float:left; width:260px;">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea class="input" rows="10" cols="38" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input class="input" type="file" name="uploadFile" style="float:left; width:260px;">
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="inputButton" type="submit" value="작성">
					<input class="inputButton" type="reset" value="취소">
					<input class="inputButton" type="button" value="목록" onclick="window.location='${pageContext.request.contextPath}/list';">
				</th>
			</tr>
		</table>
	</form>
</body>
</html>