package com.x1.chan.service;

import com.x1.chan.dao.BoardDao;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

    private BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public void write(String loginId, String contents) {
        boardDao.write(loginId, contents);
    }
}
