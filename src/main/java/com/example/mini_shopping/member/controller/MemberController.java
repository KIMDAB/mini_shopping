package com.example.mini_shopping.member.controller;

import com.example.mini_shopping.member.model.MemberVO;
import com.example.mini_shopping.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

        int totalRow= memberService.getPageCnt();
        int totalcnt = (int)Math.ceil((double) totalRow/pageBlcok);

        model.addAttribute("list", list);
        model.addAttribute("cpage", cpage);
        model.addAttribute("totalcnt", totalcnt);

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
    public String insertOK(MemberVO vo)throws IllegalStateException, IOException {
        log.info("회원 등록..OK");


        int result = memberService.insertOK(vo);
        log.info("result:{}", result);
        if (result ==1){
            return "redirect:/";
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
    public String updateOK(Model model, MemberVO vo )throws IllegalStateException, IOException {
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
    public String idCheck(@RequestParam  String id){

        log.info("idCheck");
        log.info("id:{}", id);

       boolean isExist = memberService.idCheck(id);
       return isExist ? "해당 아이디가 존재합니다" : "사용가능한 아이디입니다";
    }




    @GetMapping("/user/findbyPw")
    public String findbyPw(){
        log.info("findbyPw");

        return "user/findbyPw";
    }
    @PostMapping("/user/findbyPwOK")
    public String findbyPwOK(@RequestParam String name, @RequestParam String email,
                             @RequestParam String id, Model model ){
        log.info("findbyPwOK");

       String findbyPw=  memberService.findbyPwOK(id, name, email);
       log.info("findbyPw:{}", findbyPw);
       model.addAttribute("findbyPw", findbyPw);

        return "user/succecefindbyPw";
    }
    @GetMapping("/user/findbyId")
    public String findbyId(){
        log.info("findbyPw");

        return "/user/findbyId";
    }
    @PostMapping("/user/findbyIdOK")
    public String findbyIdOK(@RequestParam String name, @RequestParam String email,
                              Model model){
        log.info("findbyIdOK");

       String findbyId = memberService.findbyIdOK(name, email);

        log.info("findbyId:{}", findbyId);
        model.addAttribute("findbyId", findbyId);

        if (findbyId == null){
            return "";
        }

        return "user/succecefindbyId";
    }




}//
