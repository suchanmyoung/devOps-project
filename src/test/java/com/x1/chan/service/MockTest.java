package com.x1.chan.service;

import com.x1.chan.dao.BoardDao;
import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "/applicationContext.xml",
        "/dispatcher-servlet.xml"})
public class MockTest {

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    private BoardService boardService;

    Criteria criteria = new Criteria();

    @Mock
    private BoardService boardServiceMock;

    @Spy @InjectMocks
    private BoardService boardServiceSpy;

    @Mock
    private BoardDao boardDao;

    @Test
    public void 모의_객체가_아닐_경우(){
        boardService.write("hi", "hello", "not mock test");

        List<Board> boards = boardService.boardList(criteria);

        for (Board board : boards) {
            System.out.println(board.toString());
        }
    }

    @Test
    public void 모의_객체일_경우(){
//        BDDMockito.given(boardServiceMock.getTotal()).willReturn(0);
        assertEquals(0, boardServiceMock.getTotal());
    }

    @Test
    public void 스파이일_경우(){
        given(boardDao.getTotal()).willReturn(328);
        assertEquals(boardService.getTotal(), boardServiceSpy.getTotal());
    }
}
