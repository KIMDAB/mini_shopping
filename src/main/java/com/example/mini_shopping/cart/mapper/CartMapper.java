package com.example.mini_shopping.cart.mapper;


import com.example.mini_shopping.cart.model.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    
    List<CartVO> getCart(String user_id);

    void addCart(CartVO vo);

    void update(CartVO vo);

    void delete(int num, String user_id);

}
