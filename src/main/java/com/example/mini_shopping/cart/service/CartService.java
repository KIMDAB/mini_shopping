package com.example.mini_shopping.cart.service;


import com.example.mini_shopping.cart.mapper.CartMapper;
import com.example.mini_shopping.cart.model.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    public List<CartVO> cartFindById(String user_id) {
        log.info("cart view");

        return cartMapper.getCart(user_id);
    }

    private static final String UPLOAD_DIR = "src/main/resources/static/uploadimgPath/"; // 이미지 업로드 경로

    public void addCart(CartVO vo)throws IOException {
        log.info("cart insert");

        MultipartFile file = vo.getFile();

        if (file != null && !file.isEmpty()) {
            // 파일 이름 생성
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);

            // 파일을 지정된 경로에 저장
            Files.copy(file.getInputStream(), path);

            // 파일 경로를 ProductVO에 설정
            vo.setImg_name("/uploadimgPath/" + fileName); // 웹에서 접근할 수 있는 경로로 설정
        }


        cartMapper.addCart(vo);
    }

    public void update(CartVO vo) {
        log.info("cart update");

        cartMapper.update(vo);
    }

    public void delete(CartVO vo) {
        log.info("cart delete");

        cartMapper.delete(vo);
    }

    public int getTotalPrice() {
        log.info("getTotalPrice" );

        return cartMapper.getTotalPrice();

    }
}
