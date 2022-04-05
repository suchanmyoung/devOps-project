package com.x1.chan.service;

import com.x1.chan.dao.MemberDao;
import com.x1.chan.domain.Member;
import com.x1.chan.common.security.Encrypt;
import com.x1.chan.domain.NaverLoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static com.x1.chan.domain.LoginDescription.LOGIN;
import static com.x1.chan.domain.LoginDescription.NAVER_LOGIN;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService{

    private final MemberDao memberDao;

    public Member join(Member member) {
        Member secureMember = Encrypt.setEncryptPassword(member);
        memberDao.join(secureMember);
        Member login = login(member.getLoginId(), secureMember.getPassword());
        return login;
    }

    @Transactional
    public Member login(String loginId, String password){
        Member loginMember = memberDao.findByLoginId(loginId, password);

        logLogin(loginId, LOGIN.getValue());
        return loginMember;
    }

    @Transactional
    public NaverLoginDTO loginByNaver(NaverLoginDTO naverMember){
        NaverLoginDTO loginMember = memberDao.findByNaverId(naverMember);
        log.error(String.valueOf(ObjectUtils.isEmpty(loginMember)));

        if(ObjectUtils.isEmpty(loginMember)){
            memberDao.saveNaverMember(naverMember);
            logLogin(naverMember.getEmail(), NAVER_LOGIN.getValue());
            return naverMember;
        }else{
            logLogin(loginMember.getEmail(), NAVER_LOGIN.getValue());
        }

        return loginMember;
    }

    public String findNaverId(String naverId){
        return memberDao.findNaverId(naverId);
    }

    public void logLogin(String loginId, String description) {
        memberDao.logLogin(loginId, description);
    }


}
