package com.example.mini_shopping.login.service;

import com.example.mini_shopping.login.model.KakaoLoginApprove;
import com.example.mini_shopping.login.model.KakaoLoginResponse;
import com.example.mini_shopping.order.model.KakaoReadyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KakaoLoginService {

    private KakaoLoginResponse kakaoLoginRespons;

    private String clientId = "3359D49015F0F3551A1E";
    private String secretKey = "DEV75BA44AC77E1006B8C8655558C873257FBBED";
    private String clientSecretKey = "7728CE72ED7F1FEF8A49";

    private HttpHeaders getHeader(){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Host", "open-api.kakaopay.com");
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        log.info("headers:{}", headers);

        return headers;

    }


    public KakaoLoginResponse KakaoLoginReadyResponse() {

        log.info("KakaoLogin ReadyResponse");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("grant_type",  );
        parameters.put("client_id", clientId );
        parameters.put("gclient_secretrant_type", clientSecretKey );
        parameters.put("code",  );
        parameters.put("redirect_uri", "http://localhost:8080/kakao/login/approve" );

        HttpEntity<Map<String, Object>> responseEntity = new HttpEntity<>(parameters, this.getHeader());

        RestTemplate restTemplate = new RestTemplate();

        kakaoLoginRespons = restTemplate.postForObject(
                "https://open-api.kakaopay.com/oauth/token",
                responseEntity,
                KakaoLoginResponse.class
        );

        return ;
    }


}
