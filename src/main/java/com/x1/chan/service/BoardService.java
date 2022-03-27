package com.x1.chan.service;

import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;

import java.util.List;

public interface BoardService {
    void write(String loginId, String contents, String title);
    List<Board> boardList(Criteria criteria);
    Board boardView(Long boardIdx);
    int getTotal();
    void updateBoard(Long boardIdx);
    void deleteBoard(Long boardIdx);
}
