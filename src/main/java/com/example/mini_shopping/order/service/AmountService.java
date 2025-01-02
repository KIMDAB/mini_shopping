package com.example.mini_shopping.order.service;


import com.example.mini_shopping.order.mapper.AmountMapper;
import com.example.mini_shopping.order.model.Amount;
import com.example.mini_shopping.order.model.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AmountService {

    @Autowired
    AmountMapper amountMapper;


    public Amount insertAmount(Amount amount) {

        log.info("insertAmount");

        return amountMapper.insertAmount(amount);

    }

    public OrderRequest getAmountByuserId(String userId) {

        log.info("getAmountByuserId");

        return amountMapper.getAmountByuserId(userId);

    }
}
