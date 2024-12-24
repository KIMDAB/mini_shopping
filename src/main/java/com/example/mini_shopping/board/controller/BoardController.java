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
    public String list(Model model, @RequestParam(defaultValue = "1")int page,
                       @RequestParam(defaultValue = "10")int limit){
        log.info(" board list");

        List<BoardVO> list = boardService.selectAll(page, limit);
        log.info("list:{}", list);

        int totalRow= boardService.getListCnt();
        int totalCnt = (int)Math.ceil((double)totalRow/limit);

        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("totalCnt", totalCnt);

        return "board/list";
    }

    @GetMapping("/board/search")
    public String search(Model model, @RequestParam String searchWord,
                         @RequestParam String searchKey,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "10") int limit){
        log.info("/board/search");

        List<BoardVO> list = boardService.search(searchKey, searchWord, page, limit);

        int searchTotalRow =boardService.searchGetListCnt(searchKey, searchWord);
        int searchTotalCnt = (int) Math.ceil((double) searchTotalRow/limit);

        log.info("searchKey:{}", searchKey);
        log.info("searchWord:{}", searchWord);

        model.addAttribute("searchKey", searchKey);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("page", page);
        model.addAttribute("searchTotalCnt", searchTotalCnt);
        model.addAttribute("list", list);

        log.info("게시판 {}에 대한 {}검색", searchKey,searchWord);

        return "board/list";
    }

    @GetMapping("/board/detail")
    public String detail(Model model, BoardVO vo){
        log.info("board detail page");

        BoardVO vo2 = boardService.selectOne(vo);

        model.addAttribute("vo2", vo2);

        return "board/detail";
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
            return "redirect:/board/list";
        }else{
            return "error";
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
            return "redirect:/board/detail?num="+ vo.getNum();
        }else{
            return "redirect:/board/update?num"+ vo.getNum();
        }
    }

    @GetMapping("/board/deleteOK")
    public String deleteOK(BoardVO vo){
        log.info("board delete");

        int result = boardService.deleteOK(vo);
        if (result==1){
            return "redirect:/board/list";
        }else{
            return "error";
        }
    }
}
