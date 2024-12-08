package com.example.mini_shopping.cart.controller;


import com.example.mini_shopping.cart.model.CartVO;
import com.example.mini_shopping.cart.service.CartService;
import com.example.mini_shopping.product.model.ProductVO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class CartController {

    @Autowired
    CartService cartService;

    //장바구니 조회
    @GetMapping("/getCart")
    public String cartView(Model model, ProductVO vo, @RequestParam String user_id){
        log.info("my cart view");


        List<CartVO> list = cartService.cartFindById(user_id);

        int getTotal = cartService.getTotalPrice();
        log.info("getTotal:{}", getTotal);

        model.addAttribute("list", list);
        model.addAttribute("getTotal", getTotal);


        return "cart/cartView";
    }

    //장바구니 담기
    @PostMapping("/addCart")
    public String cartPust(@RequestBody CartVO vo){

        cartService.addCart(vo);

        return "상품이 장바구니에 추가되었습니다";
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

        return "상품을 장바구에서 삭제하였습니다";
    }
}
