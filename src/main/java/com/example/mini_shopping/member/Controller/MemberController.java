package com.example.mini_shopping.member.Controller;

import com.example.mini_shopping.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;
}
