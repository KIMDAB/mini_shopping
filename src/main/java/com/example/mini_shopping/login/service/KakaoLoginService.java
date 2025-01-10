package com.example.mini_shopping.login.service;

import com.example.mini_shopping.login.model.KakaoAccessTokenResponseVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KakaoLoginService {

    private KakaoAccessTokenResponseVO responseVO;


    private String clientId = "1b0e39f190fea25e739ad63ab77687ce";
    private String redirectUrl = "http://localhost:8080/kakao/callback";



    private HttpHeaders getHeader(){

        HttpHeaders headers = new HttpHeaders();
//        headers.set("Host", "open-api.kakaopay.com");
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        log.info("headers:{}", headers);

        return headers;

    }



// token 발급
    public String getAccessToken(String code) throws JsonProcessingException {

        log.info("getAccessToken");

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("client_id", clientId );
        parameters.add("redirect_url", redirectUrl);
        parameters.add("code", code);


        log.info("parameters:{}", parameters);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parameters ,this.getHeader());


        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                httpEntity,
                String.class

        );

        log.info("");
        log.info("responseEntity:{}....", responseEntity);
        log.info("");

        String responseBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        log.info("accessToken:{}", jsonNode.get("access_token").asText());


        return jsonNode.get("access_token").asText();


    }




}//end
