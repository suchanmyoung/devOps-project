package com.x1.chan.mapper;

import org.apache.ibatis.annotations.Param;

public interface BoardMapper {
    void write(@Param("loginId") String loginId, @Param("contents") String contents);
}
