package com.x1.chan.service;

import com.x1.chan.domain.Member;

public interface MemberService {

    void join(Member member);
    Member login(String loginId, String password);
    void logLogin(String loginId, String description);
}
