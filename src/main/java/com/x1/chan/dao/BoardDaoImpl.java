package com.x1.chan.dao;

import com.x1.chan.mapper.BoardMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    SqlSession sqlSession;

    @Override
    public void write(String loginId, String contents) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        boardMapper.write(loginId, contents);
    }
}
