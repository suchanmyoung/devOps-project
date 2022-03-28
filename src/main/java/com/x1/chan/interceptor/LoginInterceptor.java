package com.x1.chan.interceptor;

import com.x1.chan.domain.Member;
import com.x1.chan.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Member loginMember = (Member)request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember == null){
            log.error("로그인 하지 않은 사용자가 접근하였습니다.");
            request.setAttribute("accessDenied", "로그인 후 이용해주세요.");
            ModelAndView mv = new ModelAndView();
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
