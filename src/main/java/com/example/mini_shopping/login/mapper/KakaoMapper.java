package com.example.mini_shopping.login.mapper;


import com.example.mini_shopping.login.model.KakaoUserInfoResponseVO;
import com.example.mini_shopping.member.model.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaoMapper {

    void save(long userId, String userEmail, String userName, String state);

    int findByid(long userId);

    MemberVO KaKaoLoginOK(long userInfo);
}
