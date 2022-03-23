package com.x1.chan.dao;

import com.x1.chan.domain.Member;
import com.x1.chan.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class MemberDaoImpl implements MemberDao{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void save(Member member) {
        MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
        mapper.joinMember(member);
    }

    @Override
    public Member findByLoginId(String loginId, String password) {
        MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
        return mapper.findByLoginId(loginId, password);
    }

    @Override
    public void logLogin(String loginId, String description) {
        MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
        mapper.logLogin(loginId, description);
    }


}
