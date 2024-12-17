package com.example.mini_shopping.product.mapper;

import com.example.mini_shopping.product.model.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectAll(int offset, int limit);

    int getListCnt();

    int insertOK(ProductVO vo);

    ProductVO selectOne(int num);

    int updateOK(ProductVO vo);

    int deleteOK(ProductVO vo);

    List<ProductVO> search( String searchWord, int limit, int offset);

    int getsearchCnt(String searchWord);
}
