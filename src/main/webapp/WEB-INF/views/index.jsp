<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="/resources/js/index.js"></script>
</head>
<script>
	<c:if test="${not empty successMsg}">
		let signupSuccessMsg="${successMsg}"
		const redirectUrl="${redirectUrl}"
		signupSuccess(signupSuccessMsg, redirectUrl);
	</c:if>

	<c:if test="${not empty loginMember}">
		let loginMemberName="${loginMember.name}";
		loginSuccess(loginMemberName);
	</c:if>

	<c:if test="${not empty accessDenied}">
		let accessDeniedMsg = "${accessDenied}";
		let redirectUrl = "${redirectUrl}";
		accessDeniedAlert(accessDeniedMsg, redirectUrl);
	</c:if>
</script>

<body>
<h1>안녕하세요. 이머니입니다. 반갑습니다 ${loginMember.name}.</h1>
<h2>${accessDenied}</h2>
<a href="/members">회원가입</a>
<a href="/login">로그인</a>
<a href="/board">게시판</a>
<form action="/logout" method="post">
	<button type="submit">로그아웃</button>
</form>
</body>
</html>
