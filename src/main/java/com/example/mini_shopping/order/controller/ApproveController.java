package com.example.mini_shopping.order.controller;


import com.example.mini_shopping.order.model.KakaoApproveResponse;
import com.example.mini_shopping.order.service.AmountService;
import com.example.mini_shopping.order.service.kakaopayService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ApproveController {

    @Autowired
    kakaopayService kakaopayService;

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


    @GetMapping("/order/fail")
    public String PayFail(){

        log.info("kakao Pay fail");

        return "order/fail";
    }

    @GetMapping("/order/cancel")
    public String PayCancel(){

        log.info("kakao Pay cancel");

        return "order/cancel";
    }

}
