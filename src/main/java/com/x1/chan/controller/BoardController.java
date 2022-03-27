package com.x1.chan.controller;

import com.x1.chan.domain.Board;
import com.x1.chan.domain.Criteria;
import com.x1.chan.domain.Member;
import com.x1.chan.domain.PageMakerDTO;
import com.x1.chan.service.BoardService;
import com.x1.chan.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String board(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                Member loginMember, Model model, Criteria criteria) throws NullPointerException {
        if (loginMember == null) {
            model.addAttribute("accessDenied", "로그인 후 이용해주세요.");
            model.addAttribute("redirectUrl", "/");
            return "index";
        }

        int total = boardService.getTotal();
        List<Board> boardList = boardService.boardList(criteria);
        PageMakerDTO pageMaker = new PageMakerDTO(criteria, total);
        model.addAttribute("boardList", boardList);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("pageMaker", pageMaker);
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

    @PostMapping("/board/{boderIdx}")
    public String boardUpdate(@PathVariable("boardIdx") Long boardIdx) {
        boardService.updateBoard(boardIdx);
        return "redirect:/board";
    }

    @PostMapping("/board/delete/{boardIdx}")
    public String boardDelete(@PathVariable("boardIdx") Long boardIdx){
//        boardService.deleteBoard(boardIdx);
        return "redirect:/board";
    }

}
