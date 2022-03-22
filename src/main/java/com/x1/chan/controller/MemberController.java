package com.x1.chan.controller;

import com.x1.chan.domain.Member;
import com.x1.chan.security.Encrypt;
import com.x1.chan.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
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
    public String join(Member member, Model model){
        log.warn("member = {}", member);
        memberService.join(member);
        model.addAttribute("redirectUrl", "/");
        model.addAttribute("successMsg", "회원가입 되었습니다.");
        return "index";
    }

    @GetMapping(value = "/login")
    public String loginForm(){
        return "member/loginForm";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("loginId") String loginId, @RequestParam("password") String password, Model model){
        Member loginMember = memberService.login(loginId, password);
        if(loginMember == null) {
            model.addAttribute("loginFailMsg", "아이디가 맞지 않습니다.");
            return "member/loginForm";
        }

        String encryptPassword = Encrypt.encryptPassword(password, loginMember.getSalt());
        if(encryptPassword != password){
            model.addAttribute("passwordFailMsg", "비밀번호가 맞지 않습니다.");
            return "member/loginForm";
        }

        model.addAttribute("loginMember", loginMember);
        log.info(loginMember.toString());
        return "index";
    }
}
