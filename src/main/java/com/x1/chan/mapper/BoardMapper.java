package com.x1.chan.mapper;

import com.x1.chan.domain.Board;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardMapper {
    void write(@Param("loginId") String loginId, @Param("contents") String contents, @Param("title") String title);
    List<Board> boardList();
    Board boardView(@Param("boardIdx") Long boardIdx);
}
