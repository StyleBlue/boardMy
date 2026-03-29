<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../board/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
body {
  margin: 0;
  font-family: "Malgun Gothic", Arial, sans-serif;
  background: linear-gradient(135deg, #f7fbe8, #eef7ff);
}
.auth-card {
  width: 420px;
  margin: 120px auto;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 18px 40px rgba(35, 62, 98, 0.16);
  padding: 34px;
}
.auth-card h2 { margin: 0 0 18px; }
.auth-card input {
  width: 100%;
  box-sizing: border-box;
  padding: 12px 14px;
  margin: 8px 0;
  border: 1px solid #d7dde6;
  border-radius: 10px;
}
.auth-card button, .auth-card a.button-link {
  display: inline-block;
  padding: 11px 16px;
  border-radius: 10px;
  border: 0;
  background: #2f6b2f;
  color: #fff;
  text-decoration: none;
  cursor: pointer;
}
.auth-card .sub-link { margin-left: 10px; color: #2f6b2f; }
.message { margin: 12px 0; color: #cc2a2a; }
.success { color: #2f6b2f; }
</style>
</head>
<body>
<div class="auth-card">
  <h2>로그인</h2>
  <c:if test="${registered == '1'}">
    <div class="message success">회원가입이 완료되었습니다. 로그인해 주세요.</div>
  </c:if>
  <c:if test="${not empty error}">
    <div class="message">${error}</div>
  </c:if>
  <form name="loginform" action="loginPro" method="post" onsubmit="return loginCheck()">
    <input type="hidden" name="returnUrl" value="${returnUrl}">
    <input type="text" name="member_id" placeholder="아이디" value="${member_id}">
    <input type="password" name="passwd" placeholder="비밀번호">
    <button type="submit">로그인</button>
    <a class="button-link" href="list" style="background:#7d8794;">목록</a>
  </form>
  <div style="margin-top:14px;">
    회원이 아니면 <a class="sub-link" href="registerForm">회원가입</a>
  </div>
</div>
</body>
</html>