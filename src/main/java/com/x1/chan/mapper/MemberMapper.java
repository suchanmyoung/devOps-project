package com.x1.chan.mapper;

import com.x1.chan.domain.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
      void joinMember(Member member);
      Member findByLoginId(@Param("loginId") String id, @Param("password") String password);
}
