package com.example.mini_shopping.member.controller;

import com.example.mini_shopping.member.model.MemberVO;
import com.example.mini_shopping.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/member/list")
    public String view(Model model, @RequestParam(defaultValue = "1")int cpage,
                       @RequestParam(defaultValue = "10")int pageBlcok){
        log.info("회원 목록");
        List<MemberVO> list = memberService.selectAll(cpage,pageBlcok);
        log.info("list:{}", list);

        return "member/list";
    }

    @GetMapping("/member/insert")
    public String insert(){

        return "member/insert";
    }

    @PostMapping("/member/insertOK")
    public String insertOK(MemberVO vo){
        log.info("회원 등록..OK");

        int result = memberService.insertOK(vo);
        if (result ==1){
            return "member/view";
        }else {
            return "member/insert";
        }
    }
    @GetMapping("/member/update")
    public String update(){
        log.info("member update");

        return "member/update";
    }

    @PostMapping("/member/updateOK")
    public String updateOK(Model model, MemberVO vo){
        log.info("member updateOK");
        int result = memberService.updateOK(vo);

        model.addAttribute("result", result);
        if (result ==1){
            return "member/detail?num="+vo.getNum();
        }else{
            return "member/update?num="+vo.getNum();
        }
    }


}
