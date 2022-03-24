package com.x1.chan.dao;

import com.x1.chan.domain.Board;
import com.x1.chan.mapper.BoardMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    SqlSession sqlSession;

    @Override
    public void write(String loginId, String contents, String title) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        boardMapper.write(loginId, contents, title);
    }

    @Override
    public List<Board> boardList() {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        return boardMapper.boardList();
    }
}
