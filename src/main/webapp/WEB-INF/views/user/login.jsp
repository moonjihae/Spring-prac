<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<div class="loginForm">
	<form name="loginForm" method="post" action="/login">
		<c:if test="${user==null}">
			<div>
				<label for="uid"></label>
				<input type="text" id="uid" name="uid">
			</div>
			<div>
				<label for="pwd"></label>
				<input type="password" id="pwd" name="pwd"> 
			</div>
			<div>
				<button type="submit">로그인</button>
				<button type="button">회원가입</button>
			</div>
		</c:if>
		<c:if test="${user!=null }">
			<div>
				<p>${user.uid}님 환영합니다.</p>
				<button id="logoutBtn" type="button">로그아웃</button>
			</div>
		</c:if>
		<c:if test="${msg==false }">
			<p style="color :red";>로그인 실패! 아이디 비밀번호를 확인해주세요</p>
		</c:if>
	</form>
</div>
</body>
</html>