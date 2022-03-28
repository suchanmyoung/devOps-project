package com.x1.chan.service;

import com.x1.chan.dao.MemberDao;
import com.x1.chan.domain.Member;
import com.x1.chan.security.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static com.x1.chan.domain.LoginDescription.LOGIN;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

    private MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public Member join(Member member) {
        Member secureMember = Encrypt.setEncryptPassword(member);
        memberDao.join(secureMember);
        Member login = login(member.getLoginId(), secureMember.getPassword());
        return login;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Member login(String loginId, String password){
        Member loginMember = memberDao.findByLoginId(loginId, password);
        logLogin(loginId, LOGIN.getValue());
        return loginMember;
    }

    @Override
    public void logLogin(String loginId, String description) {
        memberDao.logLogin(loginId, description);
    }
}
