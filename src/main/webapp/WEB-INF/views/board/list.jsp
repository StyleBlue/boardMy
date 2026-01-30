<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
/* 전체 배경 */
body {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", Arial, sans-serif;
  background:
    linear-gradient(rgba(255,255,255,0.85), rgba(255,255,255,0.85)),
    url("${pageContext.request.contextPath}/resources/imgs/bg_spring.jpg")
    no-repeat center center fixed;
  background-size: cover;
}

/* 중앙 카드 영역 */
.board-wrap {
    width: 900px;
    background: rgba(255, 255, 255, 0.95);
    border-radius: 12px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.15);
    padding: 30px;
}

/* 제목 */
.board-title {
    font-size: 26px;
    font-weight: bold;
    margin-bottom: 15px;
}

/* 테이블 */
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

/* 링크 */
a {
    text-decoration: none;
    color: #333;
}

a:hover {
    color: #4CAF50;
    font-weight: bold;
}

/* 페이징 */
.page-area a {
    margin: 0 4px;
}


/* 게시판 카드 영역 */
.board-wrap {
    width: 900px;
    background: rgba(255,255,255,0.95);
    border-radius: 12px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.15);
    padding: 30px;
}")
        no-repeat center center fixed;

    background-size: cover;
	}

/* 게시판 카드 영역 */
.board-wrap {
    width: 900px;
    background: rgba(255,255,255,0.95);
    border-radius: 12px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.15);
    padding: 30px;
}

</style>
</head>
<body>
	<center style="margin-top: 120px;">
    <div class="board-wrap">
	<div class="board-title"><img src="\resources\imgs\Board_Subject.png" width="400" height="120"></div>
	<a style="color: black;">글목록 (글갯수:<b>${cnt}</b>)
	</a>
	<table>
		<tr>
			<th colspan="6" align="right" style="height: 25px;"><a
				href="writeForm">글쓰기</a></th>
		</tr>
		<tr>
			<th onmouseover="this.style.background='#ebebeb'"
				onmouseout="this.style.background='white'" style="width: 40px;">번호</th>
			<th onmouseover="this.style.background='#ebebeb'"
				onmouseout="this.style.background='white'" style="width: 460px;">제목</th>
			<th onmouseover="this.style.background='#ebebeb'"
				onmouseout="this.style.background='white'" style="width: 50px;">작성자</th>
			<th onmouseover="this.style.background='#ebebeb'"
				onmouseout="this.style.background='white'" style="width: 60px;">작성일</th>
			<th onmouseover="this.style.background='#ebebeb'"
				onmouseout="this.style.background='white'" style="width: 50px;">조회수</th>
			<th onmouseover="this.style.background='#ebebeb'"
				onmouseout="this.style.background='white'" style="width: 80px;">IP</th>
		</tr>

		<c:if test="${cnt > 0}">
			<c:forEach var="article" items="${dtos}">
				<tr>
					<td style="align:center;">${number}
					<c:set var="number" value="${number - 1}" />
					
					<td><a href="contentForm?num=${article.num}&pageNum=${pageNum}&number=${number + 1}">
						<!-- 들여쓰기 > 0 : 답변글 -->
						<c:if test="${article.ref_level > 1}"> <!-- 답변  -->
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
					</a></td>
					<td>
						${article.writer}
					</td>
					<td>
						<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${article.reg_date}" />
					</td>
					<td>
						${article.readCnt}
					</td>
					<td>
						${article.ip}
					</td>
				</tr>
			</c:forEach>
		</c:if>
		</table>
		
	<div style="color:black;">	
		<c:if test="${cnt == 0}">
			<tr>
				<td colspan="6" align="center" style="height: 40px;">
				게시판에 글이 없습니다. 글을 작성해 주세요.
				</td>
			</tr>
		</c:if>
	
	<c:if test="${cnt > 0}">
		<c:if test="${startPage > pageBlock}">
			<a href="list">◀◀</a>
			<a href="list?pageNum=${startPage - pageBlock}">◀</a>
	</c:if>	
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:if test="${i == currentPage}">
			<b>[${i}]</b>
		</c:if>
		<c:if test="${i != currentPage}">
			<a href="list?pageNum=${i}">[${i}]</a>
		</c:if>
	</c:forEach>
			
	<c:if test="${pageCount > endPage}">
		<a href="list?pageNum=${startPage + pageBlock}">▶</a>
		<a href="list?pageNum=${pageCount}">▶▶</a>
		</c:if>
	</c:if>
	</div>
	</div>
	</center>
</body>
</html>