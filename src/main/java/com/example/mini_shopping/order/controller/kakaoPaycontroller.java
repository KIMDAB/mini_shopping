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
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    private OrderRequest orderRequest2;



//    결제 요청
    @PostMapping("/order/ready")
    public KakaoReadyResponse readyKakao(@RequestBody OrderRequest orderRequest){
        log.info("readyKakao");


        log.info("상품 결제 요청 정보:{}", orderRequest);



        KakaoReadyResponse vo = kakaopayService.kakaoPayReady(orderRequest);




        return vo;
    }

//  결제 성공
    @GetMapping("/order/success")
    public String afterPay(
            @RequestParam("pg_token") String pgToken
            ){


        log.info("order/success");

       log.info("상품 결제 승인 정보:{}", orderRequest2);

        KakaoApproveResponse approve = kakaopayService.approveResponse(pgToken, orderRequest2);
        log.info("결제 승인:{}", approve);

        return "order/complated";
    }



}
