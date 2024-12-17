package com.example.mini_shopping.product.mapper;

import com.example.mini_shopping.product.model.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectAll(@Param("offset") int offset,
                              @Param("limit") int limit);

    int getListCnt();

    int insertOK(ProductVO vo);

    ProductVO selectOne(int num);

    int updateOK(ProductVO vo);

    int deleteOK(ProductVO vo);

    List<ProductVO> search(@Param("searchWord") String searchWord,
                           @Param("offset")int offset,
                           @Param("limit")int limit);

    int getsearchCnt(String searchWord);
}
