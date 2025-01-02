package com.example.mini_shopping.order.model;


import lombok.Data;

@Data
public class KakaoReadyResponse {
    private String tid;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String iso_app_scheme;
    private String created_at;
}
