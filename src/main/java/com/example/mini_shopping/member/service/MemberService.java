package com.example.mini_shopping.member.service;

import com.example.mini_shopping.member.mapper.MemberMapper;
import com.example.mini_shopping.member.model.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    public int insertOK(MemberVO vo) {
        log.info("insertOK");

        return memberMapper.insertOK(vo);
    }

    public List<MemberVO> selectAll(int cpage, int pageBlcok) {
        log.info("member selectAll");

        int totalRow = (cpage -1)*pageBlcok;

        return memberMapper.selectAll(cpage,totalRow);
    }

    public int updateOK(MemberVO vo) {
        log.info("member updateOK");

        return memberMapper.updateOK(vo);
    }

    public int getPageCnt() {
        log.info("member list getPageCnt");

        return memberMapper.getPageCnt();
    }

    public MemberVO selectOne(MemberVO vo) {
        log.info("member selectOne");

        return memberMapper.selectOne(vo);
    }

    public boolean idCheck(String id) {
        log.info("idCheck");

        int count = memberMapper.idCheck(id);

        return count > 0;
    }

    public String loginOK(String id, String pw) {
        log.info("loginOK");
        MemberVO vo = memberMapper.loginOK(id);
        if (vo.getPw().equals(pw)){
            return vo.getId();
        }

        return null;
    }

    public int deleteOK(int num) {
        log.info("member delete");

        return memberMapper.deleteOK(num);
    }

    public String findbyPwOK(String name, String id, String email) {
        log.info("findbyPwOK");

       return memberMapper.findbyPwOK(name, email, id);
    }

    public String findbyIdOK(String name, String email) {
        log.info("findbyIdOK");

        MemberVO vo = memberMapper.findbyIdOK(name, email);

       return vo.getId();
    }
}
