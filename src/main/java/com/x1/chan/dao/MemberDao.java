package com.x1.chan.dao;

import com.x1.chan.domain.Member;

public interface MemberDao {

    void save(Member member);

    Member findById(Long memberId);
}
