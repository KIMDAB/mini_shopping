package com.example.mini_shopping.kakao;

import jakarta.servlet.http.HttpSession;
import jdk.jfr.Registered;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class kakaoPaycontroller {

    @Autowired
    kakaopayService service;

    @PostMapping("/order/ready")
    public kakaoReadyResponse readyKakao(OrderRequest order,
                                         HttpSession session){
        log.info("readyKakao");

        String userId = (String) session.getAttribute("id");


        return service.kakaoPayReady(order, userId);
    }

//    @GetMapping("/order/cancel")
//    public

}
