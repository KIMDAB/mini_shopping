package com.example.mini_shopping.order.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class OrderRequest {

    int num;
    String userId;
    String orderId;
    String itemName;
    int quantity;
    int totalPrice;

    public OrderRequest(String userId, String itemName, int totalPrice, int quantity){
        this.userId = userId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderId = generateOrderId();
    }

    private String generateOrderId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }

}
