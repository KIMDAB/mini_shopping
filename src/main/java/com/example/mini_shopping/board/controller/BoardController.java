package com.example.mini_shopping.board.controller;

import com.example.mini_shopping.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    BoardService boardService;
}
