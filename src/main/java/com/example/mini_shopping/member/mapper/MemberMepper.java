package com.example.mini_shopping.member.mapper;

import com.example.mini_shopping.member.model.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMepper {


    public int insertOK(MemberVO vo);

    public List<MemberVO> selectAll(int cpage, int totalRow);

    int updateOK(MemberVO vo);

    int getPageCnt();

    MemberVO selectOne(MemberVO vo);

    boolean idCheck(String id);

    MemberVO loginOK(MemberVO vo);

    int deleteOK(int num);

    void findbyPwOK(MemberVO vo);

    void findbyIdOK(MemberVO vo);
}
