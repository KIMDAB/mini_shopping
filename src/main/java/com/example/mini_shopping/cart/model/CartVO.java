package com.example.mini_shopping.cart.model;

import lombok.Data;

@Data
public class CartVO {

    private String pName;
    private int price;
    private int count;
    private String user_id;
}
