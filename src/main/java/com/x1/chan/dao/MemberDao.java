package com.x1.chan.dao;

import com.x1.chan.domain.Member;

public interface MemberDao {

    void join(Member member);
    Member findByLoginId(String loginId, String password);
    void logLogin(String loginId, String description);
}
