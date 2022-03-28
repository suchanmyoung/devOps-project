package com.x1.chan.dao;

import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardDao {
    void write(String loginId, String contents, String title);

    void updateBoard(Board board);

    void deleteBoard(Long boardIdx);

    List<Board> boardList(Criteria criteria);

    Board boardView(Long boardIdx);

    int getTotal();


}
