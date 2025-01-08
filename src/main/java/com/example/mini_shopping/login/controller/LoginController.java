package com.example.mini_shopping.login.controller;


import com.example.mini_shopping.login.model.KakaoLoginResponse;
import com.example.mini_shopping.login.service.KakaoLoginService;
import com.example.mini_shopping.member.model.MemberVO;
import com.example.mini_shopping.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

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
    public void callback(@RequestParam("code") String code){
        log.info("kakao callback");

        loginService.getAccessToken(code);
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

}
