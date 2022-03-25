package com.x1.chan.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    /* 현재 페이지 */
    public int pageNum;

    /* 한 페이지 당 보여질 게시물 갯수 */
    private int amount;

    public Criteria() {
        this.pageNum = 1;
        this.amount = 10;
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

}
