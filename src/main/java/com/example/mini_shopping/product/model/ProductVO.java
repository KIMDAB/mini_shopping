package com.example.mini_shopping.product.model;

import lombok.Data;

@Data
public class ProductVO {
    private int num;
    private String brandName;
    private String name;
    private String content;
    private int price;
    private String user_id;
}
