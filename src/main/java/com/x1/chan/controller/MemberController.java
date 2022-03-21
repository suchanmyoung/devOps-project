package com.x1.chan.controller;

import com.x1.chan.domain.Member;
import com.x1.chan.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members")
    public String memberForm(){
        return "member/memberForm";
    }

    @PostMapping(value = "/members")
    public String members(Member member, Model model){
        memberService.join(member);
        model.addAttribute("redirectUrl", "/");
        model.addAttribute("successMsg", "회원가입 되었습니다.");
        return "index";
    }
}
