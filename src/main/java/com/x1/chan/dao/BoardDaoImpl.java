package com.x1.chan.dao;

import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import com.x1.chan.mapper.BoardMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BoardDaoImpl implements BoardDao {
    private BoardMapper boardMapper;

    BoardDaoImpl(SqlSession sqlSession){
        this.boardMapper = sqlSession.getMapper(BoardMapper.class);
    }

    @Override
    public void write(String loginId, String contents, String title) {
               boardMapper.write(loginId, contents, title);
    }

    @Override
    public List<Board> boardList(Criteria criteria) {
        return boardMapper.boardList(criteria);
    }

    @Override
    public Board boardView(Long boardIdx) {
        return boardMapper.boardView(boardIdx);
    }

    @Override
    public int getTotal() {
        return boardMapper.getTotal();
    }

    @Override
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(Long boardIdx) {
        boardMapper.deleteBoard(boardIdx);
    }

}
