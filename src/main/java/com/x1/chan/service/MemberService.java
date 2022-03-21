package com.x1.chan.service;

import com.x1.chan.dao.MemberDao;
import com.x1.chan.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private static MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public MemberDto selectMember(String id) {
        MemberDto memberDto = memberDao.selectMember(id);
        return memberDto;
    }

}
