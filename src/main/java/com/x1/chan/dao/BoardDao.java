package com.x1.chan.dao;

import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import com.x1.chan.mapper.BoardMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao{
    private BoardMapper boardMapper;

    BoardDao(SqlSession sqlSession){
        this.boardMapper = sqlSession.getMapper(BoardMapper.class);
    }

    public void write(String loginId, String contents, String title) {
               boardMapper.write(loginId, contents, title);
    }

    public List<Board> boardList(Criteria criteria) {
        return boardMapper.boardList(criteria);
    }

    public Board boardView(Long boardIdx) {
        return boardMapper.boardView(boardIdx);
    }

    public int getTotal() {
        return boardMapper.getTotal();
    }

    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    public void deleteBoard(Long boardIdx) {
        boardMapper.deleteBoard(boardIdx);
    }

}
