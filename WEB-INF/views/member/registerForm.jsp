<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../board/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
body {
  margin: 0;
  font-family: "Malgun Gothic", Arial, sans-serif;
  background: linear-gradient(135deg, #fff7e8, #eef7ff);
}
.auth-card {
  width: 460px;
  margin: 90px auto;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 18px 40px rgba(35, 62, 98, 0.16);
  padding: 34px;
}
.auth-card h2 {
  margin: 0 0 18px;
}
.auth-card input {
  width: 100%;
  box-sizing: border-box;
  padding: 12px 14px;
  margin: 8px 0;
  border: 1px solid #d7dde6;
  border-radius: 10px;
}
.auth-card button,
.auth-card a.button-link {
  display: inline-block;
  padding: 11px 16px;
  border-radius: 10px;
  border: 0;
  background: #1f5faa;
  color: #fff;
  text-decoration: none;
  cursor: pointer;
}
.auth-actions {
  display: flex;
  gap: 8px;
  margin-top: 8px;
}
.message {
  margin: 12px 0;
  color: #cc2a2a;
}
</style>
</head>
<body>
<div class="auth-card">
  <h2>회원가입</h2>
  <c:if test="${not empty error}">
    <div class="message">${error}</div>
  </c:if>
  <form name="registerform" action="registerPro" method="post" onsubmit="return registerCheck()">
    <input type="text" name="member_id" placeholder="아이디" value="${member_id}">
    <input type="text" name="member_name" placeholder="이름" value="${member_name}">
    <input type="password" name="passwd" placeholder="비밀번호">
    <input type="password" name="passwd_confirm" placeholder="비밀번호 확인">
    <div class="auth-actions">
      <button type="submit">가입하기</button>
      <a class="button-link" href="loginForm" style="background:#7d8794;">로그인</a>
      <button type="button" onclick="window.location='${pageContext.request.contextPath}/list'" style="background:#4CAF50;">목록으로 가기</button>
    </div>
  </form>
</div>
</body>
</html>
