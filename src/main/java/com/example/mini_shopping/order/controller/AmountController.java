package com.example.mini_shopping.order.controller;


import com.example.mini_shopping.order.model.OrderRequest;
import com.example.mini_shopping.order.service.AmountService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class AmountController {

    @Autowired
    AmountService amountService;


    @Autowired
    HttpSession session;


//    @PostMapping("/amount/insert")
//    public String amountInsert(@RequestBody OrderRequest orderRequest){
//
//        log.info("amount insert");
//
//        OrderRequest order = amountService.insertAmount(orderRequest);
//
//        log.info("order amount:{}", order);
//
//        return "";
//    }


    @GetMapping("/order/amount")
    public String amount(){

        String userId = (String) session.getAttribute("id");

        OrderRequest vo2 = amountService.getAmountByuserId(userId);

        log.info("해당 유저 결제 내역:{}", vo2);

        return "order/amount";
    }
}
