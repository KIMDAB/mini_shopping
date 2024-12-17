package com.example.mini_shopping.product.service;

import com.example.mini_shopping.product.mapper.ProductMapper;
import com.example.mini_shopping.product.model.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<ProductVO> selectAll(int page, int limit) {
        log.info("product selectAll");

        int offset = (page -1) * limit;
        log.info("offset:{}", offset);
        log.info("limit:{}", limit);

        return productMapper.selectAll(offset, limit);
    }

    public int getListCnt() {
        log.info("getListCnt");

        return productMapper.getListCnt();
    }

    public List<ProductVO> search( String searchWord, int page, int limit) {
        log.info("search");
        int offset = (page -1) * limit;
        log.info("offset:{}", offset);
        log.info("limit:{}", limit);

        return productMapper.search("%"+searchWord+"%", offset, limit);
    }

    public int getsearchCnt(String searchWord) {
        log.info("getsearchCnt");
        log.info("searchWord:{}", searchWord);

        return productMapper.getsearchCnt("%"+searchWord+"%");
    }

    private static final String UPLOAD_DIR = "src/main/resources/static/uploadimgPath/"; // 이미지 업로드 경로

    public void addProduct(ProductVO vo) throws IOException {

        log.info("product insertOK");
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

        // 데이터베이스에 상품 정보 저장
        productMapper.insertOK(vo);
    }

    public ProductVO selectOne(int num) {
        log.info("product selectOne");

        return productMapper.selectOne(num);
    }

    public int updateOK(ProductVO vo)throws IOException  {
        log.info("product updateOK");

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


        return productMapper.updateOK(vo);
    }

    public int deleteOK(ProductVO vo) {
        log.info("product deleteOK");

        return productMapper.deleteOK(vo);
    }

}
