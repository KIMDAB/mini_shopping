package com.example.mini_shopping.product.mapper;

import com.example.mini_shopping.product.model.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectAll(int cpage, int totalRow);

    int getListCnt();

    int insertOK(ProductVO vo);

    ProductVO selectOne(ProductVO vo);

    int updateOK(ProductVO vo);

    int deleteOK(ProductVO vo);
}
