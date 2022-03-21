package com.x1.chan.service;

import com.x1.chan.domain.Member;

public interface MemberService {

    void join(Member member);

    Member findMemberById(Long memberId);
}
