package com.x1.chan.service;

import com.x1.chan.dao.MemberDao;
import com.x1.chan.domain.Member;
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
        memberDao.save(member);
    }

    @Override
    public Member findById(String loginId, String password){
        return memberDao.findById(loginId, password);
    }
}
