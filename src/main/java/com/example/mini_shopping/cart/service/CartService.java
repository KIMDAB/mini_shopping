package com.example.mini_shopping.cart.service;


import com.example.mini_shopping.cart.mapper.CartMapper;
import com.example.mini_shopping.cart.model.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    public List<CartVO> cartFindById(String id) {
        log.info("cart view");

        return cartMapper.cartFindById(id);
    }

    public void insert(CartVO vo) {
        log.info("cart insert");

        cartMapper.insert(vo);
    }

    public void update(CartVO vo) {
        log.info("cart update");

        cartMapper.update(vo);
    }

    public void delete(CartVO vo) {
        log.info("cart delete");

        cartMapper.delete(vo);
    }
}
