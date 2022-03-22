package com.x1.chan.controller;

import com.x1.chan.domain.Member;
import com.x1.chan.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class BoardController {

    @GetMapping("/board")
    public String board(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){

        if (loginMember == null) {
            model.addAttribute("accessDenied", "로그인 후 이용해주세요.");
            model.addAttribute("redirectUrl", "/");
            return "index";
        }

        model.addAttribute("loginMember", loginMember);
        return "board/boardList";
    }
}
