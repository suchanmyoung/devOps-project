package com.x1.chan.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    private Long accountId; // 계정 아이디(Increment, PK)
    private String loginId; // 로그인 아이디
    private String password; // 로그인 패스워드
    private String userType; // 유저 타입(일반 유저/어드민)
    private String userEmail; // 이메일
    private String name; // 이름
    private String nickName; // 닉네임
    private Date regDate; // 가입일
    private String phoneNumber; // 휴대폰 번호
    private Character useYN; // 삭제여부

    public Member() {
    }
}
