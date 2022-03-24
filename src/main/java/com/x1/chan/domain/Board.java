package com.x1.chan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Board {

    private Long boardIdx;
    private String loginId;
    private String contents;
    private Long hit;
    private Character useYn;
    private String title;
    private Date regDate;

    public Board(){
    }
}
