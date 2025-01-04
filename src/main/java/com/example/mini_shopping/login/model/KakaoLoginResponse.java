package com.example.mini_shopping.login.model;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoLoginResponse {

    String token_type;
    String access_token;
    String expires_in;
    String refresh_token;


}
