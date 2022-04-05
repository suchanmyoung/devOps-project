package com.x1.chan.controller;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.x1.chan.common.naver.NaverLogin;
import com.x1.chan.common.security.Encrypt;
import com.x1.chan.common.security.Rsa;
import com.x1.chan.common.session.SessionConst;
import com.x1.chan.domain.Member;
import com.x1.chan.domain.NaverLoginDTO;
import com.x1.chan.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Objects;

import static com.x1.chan.domain.LoginDescription.LOGOUT;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final NaverLogin naverLogin;

    @GetMapping(value = "/members")
    public String memberForm() {
        return "member/memberForm";
    }

    @PostMapping(value = "/members")
    public String join(Member member, Model model, HttpServletRequest request) {
        if (StringUtils.isEmpty(member.getLoginId()) || StringUtils.isEmpty(member.getPassword())) {
            log.error("아이디, 비밀번호 Null");
            return "member/memberForm";
        }

        Member loginMember = memberService.join(member);

        model.addAttribute("redirectUrl", "/");
        model.addAttribute("successMsg", "회원가입 되었습니다.");

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        model.addAttribute("loginMember", loginMember);
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginForm(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String naverAuthUrl = naverLogin.getAuthorizationUrl(session);
        model.addAttribute("url", naverAuthUrl);

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);
        KeyPair keyPair = generator.genKeyPair();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 세션에 공개키의 문자열을 키로하여 개인키를 저장한다.
        session.setAttribute("_RSA_WEB_Key_", privateKey);

        // 공개키를 문자열로 변환하여 JavaScript RSA 라이브러리 넘겨준다.
        RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

        String publicKeyModulus = publicSpec.getModulus().toString(16);
        String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

        request.setAttribute("publicKeyModulus", publicKeyModulus);
        request.setAttribute("publicKeyExponent", publicKeyExponent);
        return "member/loginForm";
    }

    @GetMapping(value = "/login/oauth2/naver")
    public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request) throws IOException, ParseException {
        log.info("네이버 로그인 시작");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLogin.getAccessToken(session, code, state);
        String apiResult = naverLogin.getUserProfile(oauthToken);

        JSONParser parser = new JSONParser();
        Object parsingApiResult = parser.parse(apiResult);
        JSONObject jsonObj = (JSONObject) parsingApiResult;

        JSONObject response_obj = (JSONObject) jsonObj.get("response");
        NaverLoginDTO naverMember = new NaverLoginDTO();
        naverMember.setName((String) response_obj.get("name"));
        naverMember.setEmail((String) response_obj.get("email"));
        naverMember.setProfileImage((String) response_obj.get("profile_image"));

        NaverLoginDTO naverLoginMember = memberService.loginByNaver(naverMember);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(SessionConst.NAVER_LOGIN_MEMBER, naverLoginMember);
        model.addAttribute("naverLoginMember", naverLoginMember);

        return "index";
    }

    @PostMapping(value = "/login")
    public String login(@Param("securedUsername") String securedUsername, @RequestParam("securedPassword") String securedPassword, Model model,
                        HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("클라이언트에서 암호화된 ID가 전달됩니다." + securedUsername);
        log.info("클라이언트에서 암호화된 PW가 전달됩니다." + securedPassword);

        HttpSession session = request.getSession(false);
        PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");
        session.removeAttribute("_RSA_WEB_Key_");

        try {
            String username = Rsa.decryptRsa(privateKey, securedUsername);
            String password = Rsa.decryptRsa(privateKey, securedPassword);
            Member loginMember = memberService.login(username, password);

            if (ObjectUtils.isEmpty(loginMember)) {
                model.addAttribute("loginFailMsg", "아이디가 맞지 않습니다.");
                return "member/loginForm";
            }

            String encryptPassword = Encrypt.setEncryptPassword(password, loginMember.getSalt());
            if (!encryptPassword.equals(loginMember.getPassword())) {
                model.addAttribute("passwordFailMsg", "비밀번호가 맞지 않습니다.");
                return "member/loginForm";
            }

            model.addAttribute("justLoginMember", loginMember);
            session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        } catch (Exception e) {
            log.error("RSA 복호화 에러");
            e.printStackTrace();
            response.sendRedirect("/login");
        }
        return "index";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (ObjectUtils.isEmpty(loginMember)) {
            NaverLoginDTO namverSessionId = (NaverLoginDTO) session.getAttribute(SessionConst.NAVER_LOGIN_MEMBER);
            memberService.logLogin(namverSessionId.getName(), LOGOUT.getValue());
        } else {
            memberService.logLogin(loginMember.getLoginId(), LOGOUT.getValue());
        }
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping(value = "idCheck")
    @ResponseBody
    public String idCheck(@RequestBody String userId){
        boolean isIdNull = ObjectUtils.isEmpty(memberService.idCheck(userId));
        if(isIdNull){
            return "null";
        }

        return "alreadyId";
    }
}
