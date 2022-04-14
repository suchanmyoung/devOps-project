package com.x1.chan.service;

import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"/applicationContext.xml",
        "/dispatcher-servlet.xml"})
@WebAppConfiguration("file:src/main/webapp")
public class AnythingTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void anythingTest(){
        boardService.write("hello", "hi", "what");
    }

    @Test
    public void readTest(){
        Criteria criteria = new Criteria();
        List<Board> boards = boardService.boardList(criteria);

        for (Board board : boards) {
            System.out.println(board.getLoginId());
        }
    }
}
