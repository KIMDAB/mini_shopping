package com.example.mini_shopping.login.controller;


import com.example.mini_shopping.login.model.KakaoAccessTokenResponseVO;
import com.example.mini_shopping.login.model.KakaoUserInfoResponseVO;
import com.example.mini_shopping.login.service.KakaoLoginService;
import com.example.mini_shopping.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

//    https://soni-developer.tistory.com/275
//    https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api

    @Autowired
    MemberService memberService;

    @Autowired
    KakaoLoginService loginService;


    String clientId = "1b0e39f190fea25e739ad63ab77687ce";
    String redirectUrl = "http://localhost:8080/kakao/callback";


    @GetMapping("/login")
    public String login(HttpSession session, Model model){
        String id = (String) session.getAttribute("id");
        log.info("login");

        String kakaoRedirectUrl = "https://kauth.kakao.com/oauth/authorize?" +
                "response_type=code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUrl;

        model.addAttribute("kakaoRedirectUrl", kakaoRedirectUrl);
        log.info("kakaoRedirectUrl:{}", kakaoRedirectUrl);

        if(id !=null){
            return "redirect:/";
        }
        return "user/login";
    }

    @GetMapping("/kakao/callback")
    public String callback(@RequestParam("code") String code,
                                      HttpServletResponse response,
                           KakaoUserInfoResponseVO vo) throws JsonProcessingException {
        log.info("kakao callback");

//            1. 인가코드 받기
//            코드 발급 후 redirect URL로 이동
//            2. 인가코드로 토큰 발급
        String accessToken = loginService.getAccessToken(code);

//        3. 토큰을 이용해 사용자 정보 조회
        log.info("KakaoUserInfoResponseVO:{}", vo);
        KakaoUserInfoResponseVO userInfo = loginService.getUserInfo(accessToken);
        log.info("유저 정보:{}", userInfo);

        return "redirect:/";
    }


    @PostMapping("/loginOK")
    public String loginOK(String id, String pw, HttpSession session){
        log.info("loginOK");

        String userId = memberService.loginOK(id, pw);
        log.info("id:{}", id);

        if (userId == null){ //로그인 실패
            return "redirect:/login";
        }
        session.setAttribute("id", userId);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        log.info("logout");

        session.invalidate();




        return "redirect:/";
    }

    @GetMapping("/kakao/logout")
    public String kakaoLogout(@RequestParam("ACCESS_TOKEN") String accessToken){
        log.info("kakao logout");

        loginService.kakaoLogout(accessToken);

        return "redirect:/";
    }

}
