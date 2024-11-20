package com.example.mini_shopping.member.mapper;

import com.example.mini_shopping.member.model.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMepper {


    public int insertOK(MemberVO vo);

    public List<MemberVO> selectAll(int cpage, int totalRow);
}
