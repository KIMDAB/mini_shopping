package com.example.mini_shopping.order.mapper;


import com.example.mini_shopping.order.model.Amount;
import com.example.mini_shopping.order.model.OrderRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AmountMapper {


    Amount insertAmount(Amount amount);

    OrderRequest getAmountByuserId(String userId);
}
