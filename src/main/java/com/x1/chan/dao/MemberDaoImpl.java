package com.x1.chan.dao;

import com.x1.chan.domain.Member;
import com.x1.chan.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

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
    public Member findById(Long memberId) {
        return null;
    }
}
