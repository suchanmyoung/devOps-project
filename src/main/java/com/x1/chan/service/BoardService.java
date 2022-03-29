package com.x1.chan.service;

import com.x1.chan.dao.BoardDao;
import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Transactional
 * 격리 수준을 REPEATABLE 처리함으로써 dirty read, Non-Repeatable read 방지
 * readOnly 속성으로 dirty checking 생략 > 성능 향상 및 조회 메소드 명시
 */

@AllArgsConstructor
@Service
public class BoardService{

    private final BoardDao boardDao;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void write(String loginId, String contents, String title
    ) {
        boardDao.write(loginId, contents, title);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateBoard(Board board) {
        boardDao.updateBoard(board);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteBoard(Long boardIdx) {
        boardDao.deleteBoard(boardIdx);
    }

    @Transactional(readOnly = true)
    public List<Board> boardList(Criteria criteria) {
        return boardDao.boardList(criteria);
    }

    @Transactional(readOnly = true)
    public Board boardView(Long boardIdx) {
        return boardDao.boardView(boardIdx);
    }

    public int getTotal() {
        return boardDao.getTotal();
    }
}
