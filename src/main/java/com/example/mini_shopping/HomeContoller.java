package com.example.mini_shopping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeContoller {

    @GetMapping("/")
    public String home(){

        log.info("index page");

        return "index";
    }
}
