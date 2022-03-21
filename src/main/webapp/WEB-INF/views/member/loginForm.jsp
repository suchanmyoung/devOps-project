<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Member</title>
	<link rel="stylesheet" href="/resources/static/css/member/memberForm.css">
</head>
<body>
<div class="register">
	<h3>로그인</h3>
	<form action="/login" method="post">
		<div class="flex">
			<ul class="container">
				<li class="item center">
					아이디
				</li>
				<li class="item">
					<input type="text" placeholder="아이디를 입력하세요." name="loginId" autofocus required="required">
				</li>
				<li class="item">
				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					비밀번호
				</li>
				<li class="item">
					<input type="password" placeholder="비밀번호를 입력하세요." name="password" required="required">
				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					비밀번호 확인
				</li>
				<li class="item">
					<input type="passwordCorr" placeholder="비밀번호 확인" name="password" required="required">
				</li>
				<li class="item">
				</li>
			</ul>
			</li>
			<li class="item">
				<button class="submit">로그인</button>
			</li>
			<li class="item">
			</li>
		</div>
	</form>
</div>
</body>
</html>
