package com.example.mini_shopping.kakao;



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

    private final KakaoPayProperties payProperties;
    private RestTemplate restTemplate = new RestTemplate();
    private  kakaoReadyResponse kakaoReady;

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
    public kakaoReadyResponse kakaoPayReady(
                                            String userId) {
        log.info("kakao ready");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("cid", cid);
        parameters.put("partner_order_id", 20241230);
        parameters.put("partner_user_id", userId);
        parameters.put("item_name", "니트" );
        parameters.put("quantity", 1 );
        parameters.put("total_amount", 100);
        parameters.put("vat_amount", "0");
        parameters.put("tax_free_amount", "0" );
        parameters.put("approval_url", "http://localhost:8080/order/pay/completed"); // 결제 성공 시 URL
        parameters.put("cancel_url", "http://localhost:8080/order/cancel");      // 결제 취소 시 URL
        parameters.put("fail_url", "http://localhost:8080/order/fail");          // 결제 실패 시 URL




        HttpEntity<Map<String, Object>> reauestEntity =
                new HttpEntity<>(parameters, this.getHeaders());


        //외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady= restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                reauestEntity,
                kakaoReadyResponse.class);

        return kakaoReady;


    }
}
