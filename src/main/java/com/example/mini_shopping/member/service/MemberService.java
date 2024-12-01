package com.example.mini_shopping.member.service;

import com.example.mini_shopping.member.mapper.MemberMepper;
import com.example.mini_shopping.member.model.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberService {

    @Autowired
    MemberMepper memberMepper;

    public int insertOK(MemberVO vo) {
        log.info("insertOK");

        return memberMepper.insertOK(vo);
    }

    public List<MemberVO> selectAll(int cpage, int pageBlcok) {
        log.info("member selectAll");

        int totalRow = (cpage -1)*pageBlcok;

        return memberMepper.selectAll(cpage,totalRow);
    }

    public int updateOK(MemberVO vo) {
        log.info("member updateOK");

        return memberMepper.updateOK(vo);
    }

    public int getPageCnt() {
        log.info("member list getPageCnt");

        return memberMepper.getPageCnt();
    }

    public MemberVO selectOne(MemberVO vo) {
        log.info("member selectOne");

        return memberMepper.selectOne(vo);
    }

    public boolean idCheck(String id) {
        log.info("idCheck");

        return memberMepper.idCheck(id);
    }

    public MemberVO loginOK(MemberVO vo) {
        log.info("loginOK");

        return memberMepper.loginOK(vo);
    }

    public int deleteOK(int num) {
        log.info("member delete");

        return memberMepper.deleteOK(num);
    }

    public void findbyPwOK(MemberVO vo) {
        log.info("findbyPwOK");

        memberMepper.findbyPwOK(vo);
    }

    public void findbyIdOK(MemberVO vo) {
        log.info("findbyIdOK");

        memberMepper.findbyIdOK(vo);
    }
}
