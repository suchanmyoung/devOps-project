package com.x1.chan.controller;

import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import com.x1.chan.domain.Member;
import com.x1.chan.domain.PageMakerDTO;
import com.x1.chan.service.BoardService;
import com.x1.chan.common.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String board(Member loginMember, Model model, Criteria criteria) throws NullPointerException {
        int total = boardService.getTotal();
        List<Board> boardList = boardService.boardList(criteria);
        PageMakerDTO pageMaker = new PageMakerDTO(criteria, total);

        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("boardList", boardList);
        model.addAttribute("loginMember", loginMember);
        return "board/boardList";
    }

    @GetMapping("/boardForm")
    public String boardForm() {
        return "board/boardForm";
    }

    @PostMapping("/boardForm")
    public String boardWrite(HttpServletRequest request, @RequestParam("contents") String contents, @RequestParam("title") String title) {
        Member loginMember = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        boardService.write(loginMember.getLoginId(), contents, title);
        return "redirect:/board";
    }

    @GetMapping("/board/{boardIdx}")
    public String boardView(@PathVariable("boardIdx") Long boardIdx, Model model) {
        Board boardView = boardService.boardView(boardIdx);
        model.addAttribute("boardView", boardView);
        return "board/boardView";
    }

    @GetMapping("/board/update/{boardIdx}")
    public String boardUpdateForm(@PathVariable("boardIdx") Long boardIdx, Model model){
        model.addAttribute("boardView", boardService.boardView(boardIdx));
        return "board/boardUpdateForm";
    }

    @PostMapping("/board/update")
    public String boardUpdate(Board board) {
        boardService.updateBoard(board);
        return "redirect:/board";
    }

    @PostMapping("/board/delete")
    public String boardDelete(@RequestParam("boardIdx") Long boardIdx){
        boardService.deleteBoard(boardIdx);
        return "redirect:/board";
    }

}
