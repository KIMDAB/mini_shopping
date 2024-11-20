package com.example.mini_shopping.board.service;

import com.example.mini_shopping.board.mapper.BoardMapper;
import com.example.mini_shopping.board.model.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public List<BoardVO> selectAll(int cpage, int pageBlock) {
        log.info("board selectAll");

        int totalRow = (cpage -1)*pageBlock;

        return boardMapper.selectAll(cpage,totalRow);
    }

    public int getListCnt() {
        log.info(" list cnt ");
        return boardMapper.getListCnt();
    }

    public int insertOK(BoardVO vo) {
        log.info("board insertOK");

        return boardMapper.insertOK(vo);
    }

    public int updateOK(BoardVO vo) {
        log.info("board updateOK");

        return boardMapper.updateOK(vo);
    }

    public int deleteOK(BoardVO vo) {
        log.info("board deleteOK");

        return boardMapper.deleteOK(vo);
    }
}
