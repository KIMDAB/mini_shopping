package com.example.mini_shopping.login.controller;


import com.example.mini_shopping.login.model.KakaoLoginResponse;
import com.example.mini_shopping.login.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kakao")
public class KakaoLoginController {

    @Autowired
    KakaoLoginService service;


    @PostMapping("/login/ready")
    public KakaoLoginResponse loginReady(){
        log.info("kakao loginReady");

        KakaoLoginResponse response = service.KakaoLoginReadyResponse();

        return  response;

    }

}
