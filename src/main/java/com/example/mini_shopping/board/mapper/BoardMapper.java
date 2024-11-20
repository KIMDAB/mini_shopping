package com.example.mini_shopping.board.mapper;

import com.example.mini_shopping.board.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<BoardVO> selectAll(int cpage, int totalRow);

    public int getListCnt();

    int insertOK(BoardVO vo);

    int updateOK(BoardVO vo);

    int deleteOK(BoardVO vo);
}
