package com.example.mini_shopping.kakao;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderRequest {

    int orderId;
    String itemName;
    String quantity;
    String totalPrice;

}
