package com.example.mini_shopping.login.service;

import com.example.mini_shopping.login.model.KakaoAccessTokenResponseVO;
import com.example.mini_shopping.login.model.KakaoUserInfoResponseVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
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

        log.info("jsonNode:{}", jsonNode);

        log.info("accessToken:{}", jsonNode.get("access_token").asText());


        return jsonNode.get("access_token").asText();


    }

    KakaoUserInfoResponseVO userInfo = new KakaoUserInfoResponseVO();


    public KakaoUserInfoResponseVO getUserInfo(String accessToken) throws JsonProcessingException {

        log.info("getUserInfo");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        log.info("user headers:{}", headers);

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("target_id_type", "user_id");
        parameters.add("target_id", userInfo.getId() );

        log.info("user parameters:{}", parameters);


        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<KakaoUserInfoResponseVO> response = restTemplate.postForEntity(
                "https://kapi.kakao.com/v2/user/me",
                httpEntity,
                KakaoUserInfoResponseVO.class
        );

        log.info("");
        log.info("");
        log.info("user response ---> {}", response);
        log.info("");
        log.info("");

        log.info("[ Kakao Service ] Auth ID ---> {} ", response.getBody().getId());
        log.info("[ Kakao Service ] NickName ---> {} ", response.getBody().getKakao_account().getProfile().getNickname());
        log.info("[ Kakao Service ] ProfileImageUrl ---> {} ", response.getBody().getKakao_account().getProfile().getProfile_image_url());

        return response.getBody();
    }

    public void kakaoLogout(String accessToken) {
        log.info("kakao logout");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+ accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeader());

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kapi.kakao.com/v1/user/logout",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        log.info("");
        log.info("responseEntity:{}", responseEntity);
        log.info("");



    }
}//end
