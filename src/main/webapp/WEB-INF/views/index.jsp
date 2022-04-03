<%@ page import="com.x1.chan.common.session.SessionConst" %>
<%@ page import="com.x1.chan.domain.Member" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="/resources/js/index.js"></script>
	<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css">
</head>
<script>
	<c:if test="${not empty successMsg}">
	let signupSuccessMsg="${successMsg}";
	const redirectUrl="${redirectUrl}";
	signupSuccess(signupSuccessMsg, redirectUrl);
	</c:if>
	<c:if test="${not empty justLoginMember}">
	let loginMemberName = "${justLoginMember.name}";
	loginSuccess(loginMemberName);
	</c:if>
	<c:if test="${not empty accessDenied}">
	let accessDeniedMsg = "${accessDdenied}";
	let redirectUrl = "${redirectUrl}";
	accessDeniedAlert(accessDeniedMsg, redirectUrl);
	</c:if>
</script>

<body>
<div class="container">
	<div class="text-center text-primary">
		<c:choose>
		<c:when test="${not empty naverLoginSession}">
				<h2>네이버 로그인 성공</h2>
				<h3>${naverLoginSession.name} 님 환영합니다.</h3>
				<img width="150px" height="150px" src="${naverLoginSession.profileImage}">
			</c:when>
			<c:otherwise>
				<h2>안녕하세요. 이머니입니다. 반갑습니다.${loginMember.name}</h2>
			</c:otherwise>
		</c:choose>
	</div>
	<div id="button" class="text-center">
		<button class="btn btn-success" onclick="location.href='/members'">회원가입</button>
		<c:if test="${not empty loginMember || not empty naverLoginSession}">
			<button class="btn btn-success" onclick="location.href='/logout'">로그아웃</button>
		</c:if>
		<c:if test="${empty loginMember && empty naverLoginSession}">
			<button class="btn btn-success" onclick="location.href='/login'">로그인</button>
		</c:if>
		<button class="btn btn-success" onclick="location.href='/board'">게시판</button>
		<button class="btn btn-success" onclick="location.href='/'">홈페이지</button>
	</div>
</div>
<div class="text-center text-primary">
</div>
</body>
</html>