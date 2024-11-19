package com.example.mini_shopping.product.controller;

import com.example.mini_shopping.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ProdectContoller {

    @Autowired
    ProductService productService;
}
