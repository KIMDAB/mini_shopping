package com.example.mini_shopping.product.service;

import com.example.mini_shopping.product.mapper.ProductMapper;
import com.example.mini_shopping.product.model.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<ProductVO> selectAll(int cpage, int pageBlock) {
        log.info("product selectAll");

        int totalRow = (cpage -1) *pageBlock;

        return productMapper.selectAll(cpage, totalRow);
    }

    public int getListCnt() {
        log.info("getListCnt");

        return productMapper.getListCnt();
    }

    public int insertOK(ProductVO vo) {
        log.info("product insertOK");

        return productMapper.insertOK(vo);
    }

    public ProductVO selectOne(ProductVO vo) {
        log.info("product selectOne");

        return productMapper.selectOne(vo);
    }

    public int updateOK(ProductVO vo) {
        log.info("product updateOK");

        return productMapper.updateOK(vo);
    }

    public int deleteOK(ProductVO vo) {
        log.info("product deleteOK");

        return productMapper.deleteOK(vo);
    }
}
