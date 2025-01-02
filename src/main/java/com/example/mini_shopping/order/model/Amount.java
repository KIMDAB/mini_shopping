package com.example.mini_shopping.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
public class Amount {

    private int num;
    private int totalPrice;
    private int point;
    private int discount;
    private int itemName;
    private String userId;
    private String orderId;
    private int quantity;





}
