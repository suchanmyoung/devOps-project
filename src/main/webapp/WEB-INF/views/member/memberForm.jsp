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
					<input type="text" name="name" placeholder="이름을 입력하세요." autofocus required>
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					아이디
				</li>
				<li class="item">
					<input type="text" name="loginId" placeholder="아이디를 입력하세요." required>
				</li>
				<li class="item">
					<button id="idCheck">중복확인</button>
				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					비밀번호
				</li>
				<li class="item">
					<input type="password" name="password" placeholder="비밀번호를 입력하세요." required>
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					비밀번호 확인
				</li>
				<li class="item">
					<input type="password" placeholder="비밀번호를 입력하세요." required>
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					이메일
				</li>
				<li class="item">
					<input type="email" name="userEmail" placeholder="이메일을 입력하세요." required>
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					휴대폰 번호
				</li>
				<li class="item">
					<input type="tel" name="phoneNumber" placeholder="휴대폰 번호를 입력하세요.">
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
	<button class="btn btn-success" onclick="location.href='/'">홈페이지</button>
</div>
</body>
</html>