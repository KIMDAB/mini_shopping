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

    @Value("${file.dir}")
    private String realPath;

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
    public String insertOK(MemberVO vo)throws IllegalStateException, IOException{
        log.info("회원 등록..OK");

        // 스프링프레임워크에서 사용하던 리얼패스사용불가.
        // String realPath = context.getRealPath("resources/upload_img");

        // @Value("${file.dir}")로 획득한 절대경로 사용해야함.
        log.info(realPath);

        String originName = vo.getFile().getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName.length() == 0) {// 넘어온 파일이 없을때 default.png 할당
            vo.setProfile("default.png");
        } else {
            // 중복이미지 이름을 배제하기위한 처리
            String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));
            log.info("save_name:{}", save_name);
            vo.setProfile(save_name);

            File f = new File(realPath, save_name);
            vo.getFile().transferTo(f);

            //// create thumbnail image/////////
            BufferedImage original_buffer_img = ImageIO.read(f);
            BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = thumb_buffer_img.createGraphics();
            graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null);

            File thumb_file = new File(realPath, "thumb_" + save_name);

            ImageIO.write(thumb_buffer_img, save_name.substring(save_name.lastIndexOf(".") + 1), thumb_file);
        }


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

        // 스프링프레임워크에서 사용하던 리얼패스사용불가.
        // String realPath = context.getRealPath("resources/upload_img");

        // @Value("${file.dir}")로 획득한 절대경로 사용해야함.
        log.info(realPath);

        String originName = vo.getFile().getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName.length() == 0) {// 넘어온 파일이 없을때 default.png 할당
            vo.setProfile("default.png");
        } else {
            // 중복이미지 이름을 배제하기위한 처리
            String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));
            log.info("save_name:{}", save_name);
            vo.setProfile(save_name);

            File f = new File(realPath, save_name);
            vo.getFile().transferTo(f);

            //// create thumbnail image/////////
            BufferedImage original_buffer_img = ImageIO.read(f);
            BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = thumb_buffer_img.createGraphics();
            graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null);

            File thumb_file = new File(realPath, "thumb_" + save_name);

            ImageIO.write(thumb_buffer_img, save_name.substring(save_name.lastIndexOf(".") + 1), thumb_file);
        }

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
    public String findbyPwOK(MemberVO vo){
        log.info("findbyPwOK");

        memberService.findbyPwOK(vo);

        return "redirect:/member/login";
    }
    @GetMapping("/user/findbyId")
    public String findbyId(){
        log.info("findbyPw");

        return "/user/findbyId";
    }
    @PostMapping("/user/findbyIdOK")
    public String findbyIdOK(MemberVO vo){
        log.info("findbyPwOK");

        memberService.findbyIdOK(vo);

        return "redirect:/member/login";
    }




}//
