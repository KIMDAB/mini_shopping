package com.example.mini_shopping.board.mapper;

import com.example.mini_shopping.board.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<BoardVO> selectAll(int offset, int limit);

    public int getListCnt();

    List<BoardVO> search(@Param("searchWord") String searchWord,
                         @Param("searchKey") String searchKey,
                         @Param("offset") int offset,
                         @Param("limit") int limit);

    int searchGetListCnt(@Param("searchWord") String searchWord,
                         @Param("searchKey") String searchKey);

    int insertOK(BoardVO vo);

    int updateOK(BoardVO vo);

    int deleteOK(BoardVO vo);

    BoardVO selectOne(BoardVO vo);


}
