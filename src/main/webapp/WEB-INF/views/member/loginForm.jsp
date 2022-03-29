<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Member</title>
	<link rel="stylesheet" href="/resources/static/css/member/memberForm.css">
	<script src="/resources/js/loginForm.js"></script>
</head>
<body>
<script>
	<c:if test="${not empty loginFailMsg}">
	let loginFailMsg = "${loginFailMsg}";
	loginFailById(loginFailMsg);
	</c:if>

	<c:if test="${not empty passwordFailMsg}">
	let passwordFailMsg = "${passwordFailMsg}";
	loginFailByPassword(passwordFailMsg);
	</c:if>
</script>

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
			</li>
			<li class="item">
				<button class="submit">로그인</button>
			</li>
			<li class="item">
			</li>
		</div>
	</form>
</div>
<!-- 네이버 로그인 창으로 이동 -->
	<div id="naver_id_login" style="text-align:center"><a href="${url}">
	<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a></div>

</body>
</html>
