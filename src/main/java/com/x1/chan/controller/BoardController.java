package com.x1.chan.controller;

import com.x1.chan.common.session.SessionConst;
import com.x1.chan.domain.*;
import com.x1.chan.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * BindingResult가 있으면 -> 오류정보(FieldError)를 BindingResult에 담아서 컨트롤러를 정상 호출
 * 없으면 -> 400 에러 발생
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String board(Model model, Criteria criteria, HttpServletRequest request) throws NullPointerException {
        int total = boardService.getTotal();
        List<Board> boardList = boardService.boardList(criteria);
        PageMakerDTO pageMaker = new PageMakerDTO(criteria, total);

        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    @GetMapping("/boardForm")
    public String boardForm() {
        return "board/boardForm";
    }

    @PostMapping("/boardForm")
    public String boardWrite(HttpServletRequest request, @RequestParam("contents") String contents, @RequestParam("title") String title) {
        Member loginMember = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        String naverLoginMember = (String) request.getSession().getAttribute(SessionConst.NAVER_LOGIN_MEMBER);
        if (ObjectUtils.isEmpty(loginMember)) {
            boardService.write(naverLoginMember, contents, title);
            return "redirect:/board";
        }
        boardService.write(loginMember.getLoginId(), contents, title);
        return "redirect:/board";
    }

    @GetMapping("/board/{boardIdx}")
    public String boardView(@PathVariable("boardIdx") Long boardIdx, Model model, HttpServletRequest request) {
        Board boardView = boardService.boardView(boardIdx);
        String filePath = request.getSession().getServletContext().getRealPath("/") + "resources" + File.separator + "upload" + File.separator;

        HttpSession session = request.getSession(false);

        Member loginSession = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (ObjectUtils.isEmpty(loginSession)) {
            NaverLoginDTO naverSessionId = (NaverLoginDTO) session.getAttribute(SessionConst.NAVER_LOGIN_MEMBER);
            if (naverSessionId.getName().equals(boardView.getLoginId()))
                model.addAttribute("naverMember", naverSessionId);
        } else {
            if (loginSession.getLoginId().equals(boardView.getLoginId()) || loginSession.getUserType().equals("ADMIN"))
                model.addAttribute("loginMember", loginSession);
        }

        model.addAttribute("filePath", filePath);
        model.addAttribute("boardView", boardView);
        return "board/boardView";
    }

    @GetMapping("/board/update/{boardIdx}")
    public String boardUpdateForm(@PathVariable("boardIdx") Long boardIdx, Model model) {
        model.addAttribute("boardView", boardService.boardView(boardIdx));
        return "board/boardUpdateForm";
    }

    @PostMapping("/board/update")
    public String boardUpdate(Board board) {
        boardService.updateBoard(board);
        return "redirect:/board";
    }

    @PostMapping("/board/delete")
    public String boardDelete(@RequestParam("boardIdx") Long boardIdx) {
        boardService.deleteBoard(boardIdx);
        return "redirect:/board";
    }
}

