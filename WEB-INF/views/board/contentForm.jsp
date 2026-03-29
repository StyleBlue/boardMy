<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<center style="margin-top: 350px; margin-bottom: 50%;">
	<h2>글 상세 보기</h2>
	<table>
		<tr>
			<th style="width: 150px">글번호</th>
			<td style="width: 150px">${number}</td>
			<th style="width: 150px">조회수</th>
			<td style="width: 150px" align="center">${bdto.readCnt}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td align="center">${bdto.writer}</td>
			<th>작성일</th>
			<td><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${bdto.reg_date}" /></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${bdto.subject}</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3">${bdto.content}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="3">
				<c:if test="${not empty bdto.org_file_name}">
					<a href="download?num=${bdto.num}">${bdto.org_file_name}</a>
					(<fmt:formatNumber value="${bdto.file_size}" pattern="#,##0" /> bytes)
				</c:if>
				<c:if test="${empty bdto.org_file_name}">
					없음
				</c:if>
			</td>
		</tr>
		<th colspan="4">
			<input type="button" value="글수정" onclick="window.location='modifyForm?num=${bdto.num}&pageNum=${pageNum}'"> 
			<input type="button" value="글삭제" onclick="window.location='deleteForm?num=${bdto.num}&pageNum=${pageNum}'"> 
			<input type="button" value="답글쓰기" onclick="window.location='writeForm?num=${bdto.num}&ref=${bdto.ref}&ref_step=${bdto.ref_step}&ref_level=${bdto.ref_level}'">
			<input type="button" value="목록" onclick="window.location='list?pageNum=${pageNum}'">
		</th>
	</table>
	</center>
</body>
</html>
