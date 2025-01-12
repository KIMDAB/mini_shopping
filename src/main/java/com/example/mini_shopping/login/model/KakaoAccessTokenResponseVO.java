package com.example.mini_shopping.login.model;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoAccessTokenResponseVO {

    private String token_type;
    private String access_token;
    private String expires_in;
    private String refresh_token;

}
