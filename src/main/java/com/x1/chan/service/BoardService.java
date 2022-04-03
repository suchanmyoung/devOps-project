package com.x1.chan.service;

import com.x1.chan.dao.BoardDao;
import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import com.x1.chan.domain.FileVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Transactional
 * Oracle의 Default 값은 Read Committed - Dirty Read 방지
 */

@RequiredArgsConstructor
@Service
public class BoardService{

    private final BoardDao boardDao;

    @Transactional()
    public void write(String loginId, String contents, String title
    ) {
        boardDao.write(loginId, contents, title);
    }

    @Transactional()
    public void updateBoard(Board board) {
        boardDao.updateBoard(board);
    }

    @Transactional()
    public void deleteBoard(Long boardIdx) {
        boardDao.deleteBoard(boardIdx);
    }

    @Transactional(readOnly = true)
    public List<Board> boardList(Criteria criteria) {
        return boardDao.boardList(criteria);
    }

    @Transactional(readOnly = true)
    public Board boardView(Long boardIdx) {
        boardDao.boardViewHit(boardIdx);
        Board boardView = boardDao.boardView(boardIdx);
        return boardView;
    }

    public void insertFile(FileVO fileVO){
        boardDao.insertFile(fileVO);
    }

    public int getTotal() {
        return boardDao.getTotal();
    }
}
