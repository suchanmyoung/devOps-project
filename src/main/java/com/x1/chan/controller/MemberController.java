package com.x1.chan.controller;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.x1.chan.common.naver.NaverLogin;
import com.x1.chan.common.security.Encrypt;
import com.x1.chan.common.session.SessionConst;
import com.x1.chan.domain.Member;
import com.x1.chan.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.x1.chan.domain.LoginDescription.LOGOUT;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private NaverLogin naverLogin;
    private String apiResult=null;

    @Autowired
    private void setNaverLogin(NaverLogin naverLogin) {
        this.naverLogin = naverLogin;
    }

    @GetMapping(value = "/members")
    public String memberForm() {
        return "member/memberForm";
    }

    @PostMapping(value = "/members")
    public String join(Member member, Model model, HttpServletRequest request) {
        Member loginMember = memberService.join(member);

        model.addAttribute("redirectUrl", "/");
        model.addAttribute("successMsg", "회원가입 되었습니다.");

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        model.addAttribute("loginMember", loginMember);
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginForm(Model model, HttpSession session) {
        String naverAuthUrl = naverLogin.getAuthorizationUrl(session);
        log.info("네이버 = {}", naverAuthUrl);
        model.addAttribute("url", naverAuthUrl);
        return "member/loginForm";
    }

    @GetMapping(value = "/login/oauth2/naver")
    public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request) throws IOException, ParseException {
        log.info("callback 시작");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLogin.getAccessToken(session, code, state);
        apiResult = naverLogin.getUserProfile(oauthToken);

        JSONParser parser = new JSONParser();
        Object parsingApiResult = parser.parse(apiResult);
        JSONObject jsonObj = (JSONObject) parsingApiResult;

        JSONObject response_obj = (JSONObject) jsonObj.get("response");
        String nickname = (String) response_obj.get("nickname");

        log.info("세션에 저장할 닉네임 = {}", nickname);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(SessionConst.LOGIN_MEMBER, nickname);
        model.addAttribute("result", apiResult);
        model.addAttribute("sessionId", nickname);

        return "redirect:/";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("loginId") String loginId, @RequestParam("password") String password, Model model, HttpServletRequest request) {
        Member loginMember = memberService.login(loginId, password);
        log.info(loginMember.toString());

        if (ObjectUtils.isEmpty(loginMember)) {
            model.addAttribute("loginFailMsg", "아이디가 맞지 않습니다.");
            return "member/loginForm";
        }

        String encryptPassword = Encrypt.setEncryptPassword(password, loginMember.getSalt());
        if (!encryptPassword.equals(loginMember.getPassword())) {
            model.addAttribute("passwordFailMsg", "비밀번호가 맞지 않습니다.");
            return "member/loginForm";
        }

        model.addAttribute("loginMember", loginMember);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        memberService.logLogin(loginMember.getLoginId(), LOGOUT.getValue());
        if (!ObjectUtils.isEmpty(session)) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
