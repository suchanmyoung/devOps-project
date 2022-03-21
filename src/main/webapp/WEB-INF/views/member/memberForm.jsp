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
	<h3>회원가입</h3>
	<form action="/members" method="post">
		<div class="flex">
			<ul class="container">
				<li class="item center">
					이름
				</li>
				<li class="item">
					<input type="text" name="name" autofocus required>
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					아이디
				</li>
				<li class="item">
					<input type="text" name="loginId" placeholder="아이디를 입력하세요." required="required">
				</li>
				<li class="item">
					<button class="idcheck">중복확인</button>
				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					비밀번호
				</li>
				<li class="item">
					<input type="password" placeholder="비밀번호를 입력하세요." name="password" required="required">
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					비밀번호
				</li>
				<li class="item">
					<input type="passwordCorr" placeholder="비밀번호를 입력하세요." name="passwordCorr" required="required">
				</li>
				<li class="item">

				</li>
			</ul>
				<ul class="container">
				<li class="item center">
					전화번호
				</li>
				<li class="item">
					<input type="tel" name="phoneNumber" placeholder="휴대전화번호">
				</li>
				<li class="item">
				</li>
			</ul>
			</ul>
			</ul>
			<ul class="container">
				<li class="item center">
					닉네임
				</li>
				<li class="item">
					<input type="String" name="nickName" placeholder="닉네임">
				</li>
				<li class="item">
				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					이메일
				</li>
				<li class="item">
					<input type="email" name="userEmail" placeholder="이메일">
				</li>
				<li class="item">
				</li>
			</ul>
			<ul class="container">
				<li class="item center">

				</li>
				<li class="item">
					<button class="submit">가입하기</button>
				</li>
				<li class="item">

				</li>
			</ul>
		</div>
	</form>
</div>
</body>
</html>
