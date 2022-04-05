<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Member</title>
	<link rel="stylesheet" href="/resources/static/css/member/memberForm.css">
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="register">
	<h3>회원가입</h3>
	<form action="/members" method="post" id="memberForm">
		<div class="flex">
			<ul class="container">
				<li class="item center">
					이름
				</li>
				<li class="item">
					<input type="text" id="name" name="name" placeholder="이름을 입력하세요." autofocus required>
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					아이디
				</li>
				<li class="item">
					<input type="text" id="loginId" name="loginId" placeholder="아이디를 입력하세요." required>
				</li>
				<li class="item">
					<button type="button" id="idCheck" onclick="id_check();">중복확인</button>
				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					비밀번호
				</li>
				<li class="item">
					<input type="password" name="password" id="pwd" placeholder="비밀번호를 입력하세요." required>
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					비밀번호 확인
				</li>
				<li class="item">
					<input type="password" id="passwordRepeat" placeholder="비밀번호를 입력하세요." required>
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					이메일
				</li>
				<li class="item">
					<input type="email" id="email" name="userEmail" placeholder="이메일을 입력하세요.">
				</li>
				<li class="item">

				</li>
			</ul>
			<ul class="container">
				<li class="item center">
					휴대폰 번호
				</li>
				<li class="item">
					<input type="tel" id="phoneNum" name="phoneNumber" placeholder="휴대폰 번호를 입력하세요.">
				</li>
				<li class="item">

				</li>
			</ul>
				<ul class="container">
				<li class="item center">

				</li>
				<li class="item">
					<button type="button" class="submit" onclick="memberForm_check();">가입하기</button>
				</li>
				<li class="item">

				</li>
			</ul>
		</div>
	</form>
	<button class="btn btn-success" onclick="location.href='/'">홈페이지</button>
</div>

<script>

	let isIdCheck = false;

	function memberForm_check() {
		let uid = document.getElementById("loginId").value;
		let pwd = document.getElementById("pwd").value;
		let pwdRe = document.getElementById("passwordRepeat").value;
		let phoneNum = document.getElementById("phoneNum").value;


		if (pwd != pwdRe) {
			alert("비밀번호가 일치하지 않습니다.");
			pwdRe.focus();
			return false;
		}


		const phoneNumberCheck = /^01[0179][0-9]{7,8}$/;
		if (!phoneNumberCheck.test(phoneNum)) {
			alert("휴대폰 번호 형식을 지켜주세요.");
			phoneNum.focus();
			return false;
		}

		const idCheck = /^[A-Za-z0-9+]*$/;
		if (!idCheck.test(uid)){
			alert("id는 영문 또는 숫자로 입력해주세요");
			document.getElementById("id");
			return false;
		}

		const passwordCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
		if (!passwordCheck.test(pwd)){
			alert("비밀번호는 8자 이상, 영문과 숫자, 특수문자 조합으로 입력해주세요.");
			document.getElementById("password").focus();
			return false;
		}

		if(!isIdCheck){
			alert("아이디 중복확인을 해주세요.")
			return false;
		}

		document.getElementById("memberForm").submit();
	}

	function id_check(){
		let userId = document.getElementById("loginId").value;

		$.ajax({
			async : true,
			type : 'POST',
			data : userId,
			url : "idCheck",
			dataType : "text",
			contentType: "application/json; charset=UTF=8",
			success : function (data) {
				if (data == 'alreadyId') {
					alert("아이디가 존재합니다.");
					document.getElementById("loginId").focus();
					isIdCheck = false;
				} else {
					alert("사용가능한 아이디입니다.");
					isIdCheck = true;
				}
			}
		});
	}
</script>
</body>
</html>