package com.x1.chan.dao;

import com.x1.chan.domain.Member;
import com.x1.chan.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;


@Repository
public class MemberDaoImpl implements MemberDao{

    private MemberMapper memberMapper;

    public MemberDaoImpl(SqlSession sqlSession) {
        this.memberMapper = sqlSession.getMapper(MemberMapper.class);
    }

    @Override
    public void save(Member member){
        try {
            memberMapper.joinMember(member);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
        }
    }

    @Override
    public Member findByLoginId(String loginId, String password) {
        return memberMapper.findByLoginId(loginId, password);
    }

    @Override
    public void logLogin(String loginId, String description) {
        memberMapper.logLogin(loginId, description);
    }
}
