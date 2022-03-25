package com.x1.chan.service;

import com.x1.chan.dao.BoardDao;
import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public void write(String loginId, String contents, String title
    ) {
        boardDao.write(loginId, contents, title);
    }

    @Override
    public List<Board> boardList(Criteria criteria) {
        return boardDao.boardList(criteria);
    }

    @Override
    public Board boardView(Long boardIdx) {
        return boardDao.boardView(boardIdx);
    }

    @Override
    public int getTotal() {
        return boardDao.getTotal();
    }


}
