package com.x1.chan.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageMakerDTO {
    private int startPage;
    private int endPage;
    private boolean prev, next;
    private int total;
    private Criteria criteria;

    public PageMakerDTO(Criteria criteria, int total) {
        this.criteria = criteria;
        this.total = total;

        this.endPage = (int)(Math.ceil(criteria.getPageNum()/10.0))*10;
        this.startPage = this.endPage -9;

        int readEnd = (int)(Math.ceil(total*1.0/criteria.getAmount()));

        if (readEnd < this.endPage) {
            this.endPage = readEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < readEnd;
    }
}
