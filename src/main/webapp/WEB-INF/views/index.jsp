<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Home</title>
	<script src="/resources/js/index.js"></script>
</head>
<script>
	<c:if test="${not empty successMsg}">
		signupSuccess(${successMsg}, ${redirectUrl});
	</c:if>

	<c:if test="${not empty loginMember}">
		loginSuccess(${loginMember.name});
	</c:if>
</script>
<body>
<h1>안녕하세요. 이머니입니다. 반갑습니다 ${loginMember.name} 님.</h1>
<a href="/members">회원가입</a>
<a href="/login">로그인</a>
<a href="/board">게시판</a>
</body>
</html>
