<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Member</title>
	<link rel="stylesheet" href="/resources/static/css/member/memberForm.css">
	<script src="/resources/js/loginForm.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jsbn.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/rsa.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/prng4.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/rng.js"/>"></script>
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

	function validateEncryptedForm()
	{
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;

		if(username == ""){
			alert("아이디를 입력하세요.");
			document.getElementById("username").focus();
			return false;
		}

		if(password == ""){
			alert("비밀번호를 입력하세요.");
			document.getElementById("password").focus();
			return false;
		}

		try {
			var rsaPublicKeyModulus = document.getElementById("rsaPublicKeyModulus").value;
			var rsaPublicKeyExponent = document.getElementById("rsaPublicKeyExponent").value;
			submitEncryptedForm(username,password, rsaPublicKeyModulus, rsaPublicKeyExponent);
		} catch(err) {
			alert(err);
		}

		return false;
	}

	function submitEncryptedForm(username, password, rsaPublicKeyModulus, rsaPpublicKeyExponent) {
		var rsa = new RSAKey();
		rsa.setPublic(rsaPublicKeyModulus, rsaPpublicKeyExponent);

		// 사용자ID와 비밀번호를 RSA로 암호화한다.
		var securedUsername = rsa.encrypt(username);
		var securedPassword = rsa.encrypt(password);

		var securedLoginForm = document.getElementById("securedLoginForm");
		securedLoginForm.securedUsername.value = securedUsername;
		securedLoginForm.securedPassword.value = securedPassword;
		securedLoginForm.submit();
	}

</script>

<div class="register">
	<h3>로그인</h3>
				<label for="username">사용자ID : <input type="text" id="username"  size="16"/></label>
				<label for="password">비밀번호 : <input type="password" id="password"  size="16" /></label>
				<input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus}"/>
				<input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent}" />
				<button class="submit" onclick="validateEncryptedForm();">로그인</button>
		</div>
	</form>

	<form id="securedLoginForm" name="securedLoginForm" action="/login" method="post" style="display: none;">
		<input type="hidden" name="securedUsername" id="securedUsername" value="" />
		<input type="hidden" name="securedPassword" id="securedPassword" value="" />
	</form>
</div>
<!-- 네이버 로그인 창으로 이동 -->
	<div id="naver_id_login" style="text-align:center"><a href="${url}">
	<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a></div>
<button class="btn btn-success" onclick="location.href='/'">홈페이지</button>


</body>
</html>
