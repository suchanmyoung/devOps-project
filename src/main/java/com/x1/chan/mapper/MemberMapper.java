package com.x1.chan.mapper;

import com.x1.chan.domain.Member;
import com.x1.chan.domain.NaverLoginDTO;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
      void joinMember(Member member);
      Member findByLoginId(@Param("loginId") String id, @Param("password") String password);
      void logLogin(@Param("loginId") String id, @Param("description") String description);
      NaverLoginDTO findByNaverId(NaverLoginDTO naverMember);
      void saveNaverId(NaverLoginDTO naverMember);
      String findNaverId(@Param("naverId") String naverId);
}
