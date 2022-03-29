package com.x1.chan.dao;

import com.x1.chan.domain.Member;
import com.x1.chan.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDao{

    private MemberMapper memberMapper;

    public MemberDao(SqlSession sqlSession) {
        this.memberMapper = sqlSession.getMapper(MemberMapper.class);
    }

    public void join(Member member){
        try {
            memberMapper.joinMember(member);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
        }
    }

    public Member findByLoginId(String loginId, String password) {
        return memberMapper.findByLoginId(loginId, password);
    }

    public void logLogin(String loginId, String description) {
        memberMapper.logLogin(loginId, description);
    }
}
