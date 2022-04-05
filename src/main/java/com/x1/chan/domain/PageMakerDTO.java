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
        // 현재 페이지와 한 페이지에 보여줄 게시물 값
        this.criteria = criteria;
        // 전체 게시물 카운트
        this.total = total;

        // 현재 화면에 표시할 마지막 페이지 현재페이지에서 10을 나눈 뒤 값을 올림 하고, 다시 곱함으로써 1~10 페이지 유지
        this.endPage = (int)(Math.ceil(criteria.getPageNum()/10.0))*10;
        this.startPage = this.endPage -9;

        // 35페이지가 데이터의 끝일 때, endPage 만으로는 40페이지까지 노출되므로 실제 전체 데이터 갯수를 카운트하여 적용
        int readEnd = (int)(Math.ceil(total*1.0/criteria.getAmount()));

        if (readEnd < this.endPage) {
            this.endPage = readEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < readEnd;
    }
}
