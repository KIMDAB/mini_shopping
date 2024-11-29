package com.example.mini_shopping.cart.controller;


import com.example.mini_shopping.cart.model.CartVO;
import com.example.mini_shopping.cart.service.CartService;
import com.example.mini_shopping.product.model.ProductVO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/cartView")
    public String cartView(Model model, HttpSession session, ProductVO vo){
        log.info("my cart view");

        String id = (String) session.getAttribute("id");
        log.info("id:{}", id);

        List<CartVO> list = cartService.cartFindById(id);


        return "cart/cartView";
    }

    @PostMapping("/cart/push")
    public String cartPust(HttpSession session, CartVO vo){

        cartService.insert(vo);

        return "cart/cartView";
    }

    @GetMapping("/cart/update")
    public String update(Model model, CartVO vo){
        log.info("cart update");

        cartService.update(vo);

        return "rediect:/cart/cartView";
    }
    @GetMapping("/cart/delete")
    public String delete(CartVO vo){

        cartService.delete(vo);

        return "rediect:/cart/cartView";
    }
}
