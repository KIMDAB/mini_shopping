package com.example.mini_shopping.cart.mapper;


import com.example.mini_shopping.cart.model.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    
    List<CartVO> cartFindById(String id);

    void insert(CartVO vo);
}
