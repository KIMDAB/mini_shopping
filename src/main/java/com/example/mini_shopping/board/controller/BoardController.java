package com.example.mini_shopping.board.controller;

import com.example.mini_shopping.board.model.BoardVO;
import com.example.mini_shopping.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/board/list")
    public String list(Model model, @RequestParam(defaultValue = "1")int cpage,
                       @RequestParam(defaultValue = "10")int pageBlock){
        log.info(" board list");

        List<BoardVO> list = boardService.selectAll(cpage, pageBlock);
        int totallists= boardService.getListCnt();
        int totalPages = (int)Math.ceil((double)totallists/pageBlock);
        log.info("list:{}", list);
        model.addAttribute("list", list);

        return "board/list";
    }

    @GetMapping("/board/insert")
    public String insert(){
        log.info("board insert");

        return "board/insert";
    }

    @PostMapping("/board/insertOK")
    public String insertOK(BoardVO vo){
        log.info("board insertOK");

        int result = boardService.insertOK(vo);
        if (result ==1){
            return "member/list";
        }else{
            return "board/insert";
        }
    }

    @GetMapping("/board/update")
    public String update(Model model){
        log.info("board update");

        return "board/update";
    }

    @PostMapping("/board/updateOK")
    public String updateOK(BoardVO vo){
        log.info("board updateOK");

        int result = boardService.updateOK(vo);

        if (result ==1){
            return "board/detail?num="+ vo.getNum();
        }else{
            return "board/update?num"+ vo.getNum();
        }
    }

    @PostMapping("/board/deleteOK")
    public String deleteOK(BoardVO vo){
        log.info("board delete");

        int result = boardService.deleteOK(vo);

        return "board/list";
    }
}
