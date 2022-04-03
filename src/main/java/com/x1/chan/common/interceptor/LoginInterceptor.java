package com.x1.chan.common.interceptor;

import com.x1.chan.common.session.SessionConst;
import com.x1.chan.domain.Member;
import com.x1.chan.domain.NaverLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Member loginSession = (Member)request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        NaverLoginDTO naverSession = (NaverLoginDTO) request.getSession().getAttribute(SessionConst.NAVER_LOGIN_MEMBER);

        if(ObjectUtils.isEmpty(loginSession) && ObjectUtils.isEmpty(naverSession)){
            log.error("로그인 하지 않은 사용자가 접근하였습니다.");
            request.setAttribute("accessDenied", "로그인 후 이용해주세요.");
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }


}
