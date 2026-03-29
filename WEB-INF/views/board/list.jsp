<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
body {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", Arial, sans-serif;
  background:
    linear-gradient(rgba(255,255,255,0.85), rgba(255,255,255,0.85)),
    url("${pageContext.request.contextPath}/resources/imgs/bg_spring.jpg") no-repeat center center fixed;
  background-size: cover;
}

.board-wrap {
  width: 900px;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 12px;
  box-shadow: 0 8px 25px rgba(0,0,0,0.15);
  padding: 30px;
}

.board-header {
  position: relative;
  min-height: 120px;
  margin-bottom: 12px;
}

.board-title {
  width: 100%;
  text-align: center;
  margin-bottom: 15px;
}

.board-title img {
  display: inline-block;
}

.auth-links {
  position: absolute;
  right: 0;
  top: 8px;
  font-size: 14px;
  color: #444;
}

.auth-links a {
  margin-left: 10px;
  color: #2f6b2f;
}

.search-box {
  text-align: center;
  margin: 18px 0 10px;
}

.search-box select,
.search-box input {
  height: 32px;
  border: 1px solid #cfcfcf;
  border-radius: 6px;
  padding: 0 8px;
  font-family: inherit;
}

.search-box input {
  width: 260px;
}

.search-box button {
  height: 32px;
  border: 0;
  border-radius: 6px;
  background: #2f6b2f;
  color: #fff;
  padding: 0 14px;
  cursor: pointer;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 15px;
}

th {
  background: #f7f7f7;
  height: 35px;
  border-bottom: 2px solid #ddd;
}

td {
  height: 32px;
  border-bottom: 1px solid #eee;
  padding: 5px;
}

a {
  text-decoration: none;
  color: #333;
}

a:hover {
  color: #4CAF50;
  font-weight: bold;
}
</style>
</head>
<body>
	<center style="margin-top: 120px;">
    <div class="board-wrap">
		<div class="board-header">
			<div class="board-title">
				<img src="${pageContext.request.contextPath}/resources/imgs/Board_Subject.png" width="400" height="120" alt="게시판">
			</div>
			<div class="auth-links">
				<c:if test="${empty sessionScope.loginMemberId}">
					<a href="loginForm?returnUrl=list">로그인</a>
					<a href="registerForm">회원가입</a>
				</c:if>
				<c:if test="${not empty sessionScope.loginMemberId}">
					<span><b>${sessionScope.loginMemberName}</b>님</span>
					<a href="logout">로그아웃</a>
				</c:if>
			</div>
		</div>
		<div style="color: black; text-align: left;">글목록 (글개수:<b>${cnt}</b>)</div>
		<div class="search-box">
			<form action="list" method="get">
				<select name="searchType">
					<option value="subject" ${searchType == 'subject' ? 'selected' : ''}>제목</option>
					<option value="writer" ${searchType == 'writer' ? 'selected' : ''}>글쓴이</option>
					<option value="content" ${searchType == 'content' ? 'selected' : ''}>본문</option>
				</select>
				<input type="text" name="keyword" value="${keyword}" placeholder="검색어를 입력하세요">
				<button type="submit">검색</button>
				<c:if test="${searching}">
					<button type="button" onclick="window.location='list'">초기화</button>
				</c:if>
			</form>
		</div>
		<table>
			<tr>
				<th colspan="6" align="right" style="height: 25px;">
					<c:if test="${empty sessionScope.loginMemberId}">
						<a href="loginForm?returnUrl=writeForm">글쓰기</a>
					</c:if>
					<c:if test="${not empty sessionScope.loginMemberId}">
						<a href="writeForm">글쓰기</a>
					</c:if>
				</th>
			</tr>
			<tr>
				<th style="width: 40px;">번호</th>
				<th style="width: 460px;">제목</th>
				<th style="width: 50px;">작성자</th>
				<th style="width: 60px;">작성일</th>
				<th style="width: 50px;">조회수</th>
				<th style="width: 80px;">IP</th>
			</tr>

			<c:if test="${cnt > 0}">
				<c:forEach var="article" items="${dtos}">
					<tr>
						<td style="text-align: center;">
							${number}
							<c:set var="number" value="${number - 1}" />
						</td>
						<td>
							<a href="contentForm?num=${article.num}&pageNum=${pageNum}&number=${number + 1}">
								<c:if test="${article.ref_level > 1}">
									<c:set var="wid" value="${(article.ref_level - 1) * 10}" />
									<img src="${project}imgs/level.gif" border="0" width="${wid}" height="15" />
								</c:if>
								<c:if test="${article.ref_level > 0}">
									<img src="${project}imgs/re.gif" border="0" width="20" height="15" />
								</c:if>
								${article.subject}
								<c:if test="${article.readCnt > 10}">
									<img src="${project}imgs/hot.gif" border="0" width="20" height="15" />
								</c:if>
							</a>
						</td>
						<td>${article.writer}</td>
						<td><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${article.reg_date}" /></td>
						<td>${article.readCnt}</td>
						<td>${article.ip}</td>
					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${cnt == 0}">
				<tr>
					<td colspan="6" align="center" style="height: 40px;">
						검색 결과가 없습니다.
					</td>
				</tr>
			</c:if>
		</table>
		
		<div style="color:black; margin-top: 10px;">
		<c:if test="${cnt > 0}">
			<c:if test="${startPage > pageBlock}">
				<a href="list?pageNum=1${searchQuery}">[맨앞]</a>
				<a href="list?pageNum=${startPage - pageBlock}${searchQuery}">[이전]</a>
			</c:if>
		
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<c:if test="${i == currentPage}">
					<b>[${i}]</b>
				</c:if>
				<c:if test="${i != currentPage}">
					<a href="list?pageNum=${i}${searchQuery}">[${i}]</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${pageCount > endPage}">
				<a href="list?pageNum=${startPage + pageBlock}${searchQuery}">[다음]</a>
				<a href="list?pageNum=${pageCount}${searchQuery}">[맨뒤]</a>
			</c:if>
		</c:if>
		</div>
	</div>
	</center>
</body>
</html>