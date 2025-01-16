package com.example.mini_shopping.login.service;

import com.example.mini_shopping.login.mapper.KakaoMapper;
import com.example.mini_shopping.login.model.KakaoAccessTokenResponseVO;
import com.example.mini_shopping.login.model.KakaoUserInfoResponseVO;
import com.example.mini_shopping.member.model.MemberVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KakaoLoginService {

    @Autowired
    KakaoMapper kakaoMapper;

    private KakaoAccessTokenResponseVO responseVO;


    private String clientId = "1b0e39f190fea25e739ad63ab77687ce";
    private String redirectUrl = "http://localhost:8080/kakao/callback";



// token 발급
    public String getAccessToken(String code) throws JsonProcessingException {


        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        log.info("getAccessToken");

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("client_id", clientId );
        parameters.add("redirect_url", redirectUrl);
        parameters.add("code", code);


        log.info("parameters:{}", parameters);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parameters ,headers);


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


    //kakao 사용자 정보 가져오기
    public long getUserInfo(String accessToken) throws JsonProcessingException {

        log.info("getUserInfo");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        log.info("user headers:{}", headers);

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("target_id_type", "user_id");
        parameters.add("target_id", userInfo.getId() );

        log.info("");
        log.info("user parameters:{}", parameters);
        log.info("");

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<KakaoUserInfoResponseVO> response = restTemplate.postForEntity(
                "https://kapi.kakao.com/v2/user/me",
                httpEntity,
                KakaoUserInfoResponseVO.class
        );

        log.info("");
        log.info("");
        log.info(" [사용자 정보 응답] ---> {}", response);
        log.info("");
        log.info("");

        long userId = response.getBody().getId();
        String userName = response.getBody().getKakao_account().getProfile().getNickname();
        String userEmail = response.getBody().getKakao_account().getEmail();
        String state = "kakao";

        List<Object> list = new ArrayList<>();
        list.add(userId);
        list.add(userName);
        list.add(userEmail);
        list.add(state);


        log.info(" [ 사용자 정보 확인 ] ---> :{}", list);


        log.info("[ Kakao Service ] Auth ID ---> {} ", userId);
        log.info("[ Kakao Service ] NickName ---> {} ", userName);
        log.info("[ Kakao Service ] Email ---> {} ", userEmail);

        //쇼핑몰 회원등록 확인 --> 갯수가 0이면 회원등록됨
        int count = kakaoMapper.findByid(userId);

        if (count == 0){
            kakaoMapper.save(userId, userEmail, userName, state);
        }



        return response.getBody().getId();
    }

    //kakao 로그아웃 Service
    public void kakaoLogout(String accessToken) {
        log.info("kakao logout");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+ accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

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

    public String loginOK(long userInfo) {
        log.info(" 등록된 kakao ID 확인 ");
        MemberVO vo = kakaoMapper.KaKaoLoginOK(userInfo);

        log.info("[ kakao service id check ]:{}", vo.getId());

        return vo.getId();
    }
}//end
