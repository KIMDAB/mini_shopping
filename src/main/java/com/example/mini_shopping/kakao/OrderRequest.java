package com.example.mini_shopping.kakao;

import lombok.*;

@Getter
@Setter
@ToString
public class OrderRequest {

    int orderId;
    String itemName;
    int quantity;
    int totalPrice;

}
