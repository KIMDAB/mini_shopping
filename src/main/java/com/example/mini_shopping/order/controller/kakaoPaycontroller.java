package com.example.mini_shopping.order.controller;

import com.example.mini_shopping.order.model.Amount;
import com.example.mini_shopping.order.model.KakaoApproveResponse;
import com.example.mini_shopping.order.model.KakaoReadyResponse;
import com.example.mini_shopping.order.model.OrderRequest;
import com.example.mini_shopping.order.service.AmountService;
import com.example.mini_shopping.order.service.kakaopayService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class kakaoPaycontroller {

    @Autowired
    kakaopayService kakaopayService;

    @Autowired
    AmountService amountService;


    @Autowired
    HttpSession session;



//    결제 요청
    @PostMapping("/order/ready")
    public KakaoReadyResponse readyKakao(@RequestBody OrderRequest orderRequest){
        log.info("readyKakao");

        log.info("상품 결제 요청 정보:{}", orderRequest);


        KakaoReadyResponse vo = kakaopayService.kakaoPayReady(orderRequest);

        session.setAttribute("userId", orderRequest.getUserId());
        session.setAttribute("orderId", orderRequest.getOrderId());

        return vo;
    }

//  결제 성공
    @GetMapping("/order/success")
    public String afterPay(
            @RequestParam("pg_token") String pgToken, Model model){


        log.info("order/success");
        log.info("결제 승인 토큰:{}", pgToken);

        KakaoApproveResponse approve = kakaopayService.approveResponse(pgToken);
        log.info("결제 승인:{}", approve);

        model.addAttribute("approve", approve);


        return "order/complated";
    }



}
