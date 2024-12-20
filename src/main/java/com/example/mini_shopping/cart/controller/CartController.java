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

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class CartController {

    @Autowired
    CartService cartService;

    //장바구니 조회
    @GetMapping("/cartView")
    public String cartView(Model model, ProductVO vo, @RequestParam String user_id){
        log.info("my cart view");


        List<CartVO> list = cartService.cartFindById(user_id);


        model.addAttribute("list", list);


        return "cart/cartView";
    }

    //장바구니 담기
    @PostMapping("/addCart")
    public String cartPust(CartVO vo)throws IllegalStateException, IOException {
        log.info("add cart");


        cartService.addCart(vo);

        return "redirect:/cartView?user_id=" +vo.getUser_id();
        
    }

    @GetMapping("/cart/update")
    public String update(Model model, CartVO vo){
        log.info("cart update");

        cartService.update(vo);

        return "rediect:/cart/cartView";
    }
    @GetMapping("/cart/delete")
    public String delete(@RequestParam int num,
                         @RequestParam String user_id){

        cartService.delete(num, user_id);

        return "상품을 장바구에서 삭제하였습니다";
    }
}
