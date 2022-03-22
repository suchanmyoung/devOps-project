package com.x1.chan.service;

import com.x1.chan.dao.MemberDao;
import com.x1.chan.domain.Member;
import com.x1.chan.security.Encrypt;
import com.x1.chan.security.Salt;
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
        Member secureMember = encryptPassword(member);
        memberDao.save(secureMember);
    }

    @Override
    public Member findById(String loginId, String password){
        return memberDao.findById(loginId, password);
    }

    private Member encryptPassword(Member member) {
        String encryptPassword = null;
        try {
            encryptPassword = Encrypt.encryptSha256(member.getPassword(), Salt.createSalt());
        } catch (Exception e) {
            e.printStackTrace();
        }
        member.setPassword(encryptPassword);
        return member;
    }
}
