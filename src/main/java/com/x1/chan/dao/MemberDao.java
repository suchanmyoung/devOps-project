package com.x1.chan.dao;

import com.x1.chan.domain.Member;
import com.x1.chan.domain.NaverLoginDTO;
import com.x1.chan.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
public class MemberDao{

    private MemberMapper memberMapper;

    public MemberDao(SqlSession sqlSession) {
        this.memberMapper = sqlSession.getMapper(MemberMapper.class);
    }

    public void join(Member member){
        try {
            memberMapper.joinMember(member);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
        }
    }

    public NaverLoginDTO findByNaverId(NaverLoginDTO naverMember) {
        try {
            return memberMapper.findByNaverId(naverMember);
        }catch (NullPointerException e){
            log.error("MemberDao/findByNaverId 에러");
            log.error(String.valueOf(e));
            return null;
        }
    }

    public String findNaverId(String naverId){
        return memberMapper.findNaverId(naverId);
    }

    public void saveNaverMember(NaverLoginDTO naverMember) {
        memberMapper.saveNaverId(naverMember);
    }

    public Member findByLoginId(String loginId, String password) {
        try{
            return memberMapper.findByLoginId(loginId, password);
        } catch (NullPointerException e){
            log.error("로그인 아이디 없음");
            log.error(String.valueOf(e));
            return null;
        }
    }

    public void logLogin(String loginId, String description) {
        memberMapper.logLogin(loginId, description);
    }

    public String idCheck(String userId){
        return memberMapper.idCheck(userId);
    }
}
