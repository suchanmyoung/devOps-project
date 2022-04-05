package com.x1.chan.mapper;

import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import com.x1.chan.domain.FileVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardMapper {

    List<Board> boardList(Criteria criteria);
    void write(@Param("loginId") String loginId, @Param("contents") String contents, @Param("title") String title);
    Board boardView(@Param("boardIdx") Long boardIdx);
    int getTotal();
    void updateBoard(Board board);
    void deleteBoard(@Param("boardIdx") Long boardIdx);
    void boardViewHit(@Param("boardIdx") Long boardIdx);
}
