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

    public List<BoardVO> selectAll(int page, int limit) {
        log.info("board selectAll");

        int offset = (page -1) * limit;

        return boardMapper.selectAll(offset, limit);
    }

    public int getListCnt() {
        log.info(" list cnt ");
        return boardMapper.getListCnt();
    }
    public List<BoardVO> search(String searchKey, String searchWord, int page, int limit) {
        log.info("board search");

        int offset = (page-1) * limit;
        log.info("offset:{}", offset);

        return boardMapper.search("%"+searchWord+"%", searchKey, offset, limit);
    }

    public int searchGetListCnt(String searchWord, String searchKey) {
        log.info("searchGetListCnt");

        return boardMapper.searchGetListCnt("%"+searchWord+"%", searchKey);
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

    public BoardVO selectOne(BoardVO vo) {
        log.info("board selectOne");

        return boardMapper.selectOne(vo);
    }


}
