package com.example.mini_shopping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeContoller {

    @GetMapping({"/", "/home", ""})
    public String home(HttpSession session, Model model){
        log.info("homepage");

        String id = (String) session.getAttribute("id");
        log.info("id:{}", id);

        model.addAttribute("session", session);
        model.addAttribute("id", id);


        return "index";
    }


}
