package com.example.mini_shopping.cart.mapper;


import com.example.mini_shopping.cart.model.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    
    List<CartVO> getCart(String user_id);

    void addCart(String pname,
                 int quantity,
                 String user_id,
                 int price, int pnum);

    void update(CartVO vo);

    void delete(CartVO vo);

    int getTotalPrice();
}
