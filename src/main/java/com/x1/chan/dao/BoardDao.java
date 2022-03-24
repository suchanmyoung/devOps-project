package com.x1.chan.dao;

import com.x1.chan.domain.Board;

import java.util.List;

public interface BoardDao {
    void write(String loginId, String contents, String title);
    List<Board> boardList();
}
