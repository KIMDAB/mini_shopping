package com.example.mini_shopping.order.service;



import com.example.mini_shopping.order.model.KakaoApproveResponse;
import com.example.mini_shopping.order.model.KakaoReadyResponse;
import com.example.mini_shopping.order.model.OrderRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class kakaopayService {

    @Autowired
    HttpSession session;




    private RestTemplate restTemplate = new RestTemplate();
    private KakaoReadyResponse kakaoReadyResponse;

    String cid ="TC0ONETIME";
    String secret_key = "DEVC53E4A9803C2F46065AC0B3A57757D2F7495A";


    private HttpHeaders getHeaders(){

        HttpHeaders headers = new HttpHeaders();
        String auth = "SECRET_KEY " + secret_key;
        headers.set("Authorization", auth);
        headers.set("Content-Type", "application/json");
        log.info("headers:{}", headers);

        return headers;
    }

    //결제 요청
    public KakaoReadyResponse kakaoPayReady(OrderRequest orderRequest) {
        log.info("kakao ready");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("cid", cid);
        parameters.put("partner_order_id", orderRequest.getOrderId());
        parameters.put("partner_user_id", orderRequest.getUserId());
        parameters.put("item_name",orderRequest.getItemName());
        parameters.put("quantity", orderRequest.getQuantity());
        parameters.put("total_amount", orderRequest.getTotalPrice() );
        parameters.put("vat_amount", "0");
        parameters.put("tax_free_amount", "0" );
        parameters.put("approval_url", "http://localhost:8080/order/success"); // 결제 성공 시 URL
        parameters.put("cancel_url", "http://localhost:8080/product/list");      // 결제 취소 시 URL
        parameters.put("fail_url", "http://localhost:8080/order/fail");          // 결제 실패 시 URL




        HttpEntity<Map<String, Object>> requestEntity =
                new HttpEntity<>(parameters, this.getHeaders());


        //외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        log.info("restTemplate:{}", restTemplate);

        kakaoReadyResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);

        log.info("kakaoReadyResponse:{}", kakaoReadyResponse);

        return kakaoReadyResponse;


    }

    public KakaoApproveResponse approveResponse(String pgToken) {
    log.info("approveResponse");

    String userId = (String) session.getAttribute("userId");
    String orderId = (String) session.getAttribute("orderId");

    log.info("userId:{}", userId);
    log.info("orderId:{}", orderId);


    Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", cid);
        parameters.put("tid", kakaoReadyResponse.getTid());
        parameters.put("partner_order_id", orderId);
        parameters.put("partner_user_id", userId);
        parameters.put("pg_token", pgToken);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        log.info("");
        log.info("");
        log.info("requestEntity", requestEntity);
        log.info("");
        log.info("");

        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse approve = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class
        );


        log.info("");
        log.info("");
        log.info("approve:{}", approve);
        log.info("");
        log.info("");

        return approve;
    }


}
