package com.example.mini_shopping.naver;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class kakaopatService {

    private final KakaoPayProperties payProperties;
    private RestTemplate restTemplate = new RestTemplate();


    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        String auth = "SECRET_KEY " + payProperties.getSecretKey();
        headers.set("Authorization", auth);
        headers.set("Content-Type", "application/json");
        log.info("headers:{}", headers);

        return headers;
    }

}
