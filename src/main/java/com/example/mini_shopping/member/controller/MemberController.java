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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/member/list")
    public String view(Model model, @RequestParam(defaultValue = "1")int cpage,
                       @RequestParam(defaultValue = "10")int pageBlcok, MemberVO vo){
        log.info("회원 목록");
        List<MemberVO> list = memberService.selectAll(cpage,pageBlcok);
        log.info("list:{}", list);

        int totalcnt= memberService.getPageCnt();
        int totalRow = (int)Math.ceil((double) totalcnt/pageBlcok);

        model.addAttribute("list", list);
        model.addAttribute("cpage", cpage);
        model.addAttribute("totalRow", totalRow);

        return "member/list";
    }

    @GetMapping("/member/detail")
    public String detail(Model model, MemberVO vo){
        log.info("member detail");

        MemberVO vo2 = memberService.selectOne(vo);
        log.info("vo2:{}", vo2);

        model.addAttribute("vo2", vo2);

        return "member/detail";
    }

    @GetMapping("/member/insert")
    public String insert(){
        log.info("member insert");

        return "member/insert";
    }

    @PostMapping("/member/insertOK")
    public String insertOK(MemberVO vo){
        log.info("회원 등록..OK");

        int result = memberService.insertOK(vo);
        log.info("result:{}", result);
        if (result ==1){
            return "redirect:/member/list";
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
            return "redirect:/member/detail?num="+vo.getNum();
        }else{
            return "redirect:/member/update?num="+vo.getNum();
        }
    }

    @GetMapping("/member/deleteOK")
    public String deleteOK(MemberVO vo, @RequestParam int num){
        log.info("member delete");
        log.info("vo:{}", vo);

        int result = memberService.deleteOK(num);
        return "redirect:/member/list";
    }

    @GetMapping("/idCheck")
    @ResponseBody
    public Map<String, String> idCheck(@RequestParam  String Id, MemberVO vo){

        log.info("idCheck");

        MemberVO vo2 = memberService.idCheck(vo);
        Map<String, String> map = new HashMap<String, String>();
        if(vo2 != null){
            map.put("result", "이미 존재하는 아이디입니다");
        }else{
            map.put("result", "사용가능한 아이디입니다");
        }

        return map;
    }

    @GetMapping("/loginOK")
    public String loginOK(MemberVO vo){
        log.info("loginOK");

        MemberVO vo2 = memberService.loginOK(vo);
        log.info("vo2:{}", vo2);

        return "";
    }
    @GetMapping("/login")
    public String login(){
        log.info("login page");

        return "member/login";
    }



}//
