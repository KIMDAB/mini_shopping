package com.example.mini_shopping.cart.model;

import lombok.Data;

@Data
public class CartVO {

    private int num;
    private int pnum;
    private String pname;
    private int price;
    private int quantity;
    private String user_id;
}
