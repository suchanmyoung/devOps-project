
// 회원가입 성공
function signupSuccess(signUpSuccessMsg, redirectUrl){
    alert(signUpSuccessMsg);
    location.href = redirectUrl;
}

// 로그인 성공
function loginSuccess(loginMemberName){
    alert("반갑습니다. " + loginMemberName + " 님!");
}

// 액세스 거부
function accessDeniedAlert(accessDeniedMsg, redirectUrl){
    alert(accessDeniedMsg);
    location.href = redirectUrl;
}
