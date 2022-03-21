package com.x1.chan.dao;

import com.x1.chan.dto.MemberDto;
import com.x1.chan.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

    SqlSession sqlSession;

    public MemberDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public MemberDto selectMember(String id){
        MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
        MemberDto memberDto = mapper.selectMember(id);
        return memberDto;
    }

}
