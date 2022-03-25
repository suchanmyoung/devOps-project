package com.x1.chan.service;

import com.x1.chan.domain.Board;

import java.util.List;

public interface BoardService {
    void write(String loginId, String contents, String title);
    List<Board> boardList();
    Board boardView(Long boardIdx);
}
