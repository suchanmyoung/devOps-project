package com.x1.chan.service;

import com.x1.chan.dao.MemberDao;
import com.x1.chan.domain.Member;
import com.x1.chan.security.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

    private MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public void join(Member member) {
        Member secureMember = Encrypt.encryptPassword(member);
        memberDao.save(secureMember);
    }

    @Override
    public Member login(String loginId, String password){
        Member loginMember = memberDao.findByLoginId(loginId, password);
        return loginMember;
    }
}