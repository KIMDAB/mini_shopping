package com.example.mini_shopping.product.controller;

import com.example.mini_shopping.member.model.MemberVO;
import com.example.mini_shopping.member.service.MemberService;
import com.example.mini_shopping.product.model.ProductVO;
import com.example.mini_shopping.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class ProdectContoller {

    @Autowired
    ProductService productService;

    @Value("${file.dir}")
    private String realPath;




    @GetMapping("/product/list")
    public String list(Model model, @RequestParam(defaultValue = "1")int cpage,
                       @RequestParam(defaultValue = "10")int pageBlock){
        log.info("product list");

        List<ProductVO> list = productService.selectAll(cpage,pageBlock);
        log.info("list:{}", list);

        int totalPages = productService.getListCnt();
        int totalCnt = (int)Math.ceil((double) totalPages/pageBlock);

        model.addAttribute("list", list);
        model.addAttribute("cpage", cpage);
        model.addAttribute("totalCnt", totalCnt);

        return "product/list";
    }

    @GetMapping("/product/insert")
    public String insert(){
        log.info("product insert");

        return "product/insert";
    }

    @PostMapping("/product/insertOK")
    public String insertOK(ProductVO vo)throws IllegalStateException, IOException {
        log.info("product insertOK");

        // 스프링프레임워크에서 사용하던 리얼패스사용불가.
        // String realPath = context.getRealPath("resources/upload_img");

        // @Value("${file.dir}")로 획득한 절대경로 사용해야함.
        log.info(realPath);

        String originName = vo.getFile().getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName.length() == 0) {// 넘어온 파일이 없을때 default.png 할당
            vo.setImg_name("default.png");
        } else {
            // 중복이미지 이름을 배제하기위한 처리
            String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));
            log.info("save_name:{}", save_name);
            vo.setImg_name(save_name);

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



        int result = productService.insertOK(vo);

        if (result ==1){
            return "redirect:/product/list";
        }else{
            return "product/insert";
        }
    }

    @GetMapping("/product/update")
    public String update(Model model, ProductVO vo){
        log.info("product update");

        ProductVO vo2 = productService.selectOne(vo);
        log.info("vo2:{}", vo2);

        model.addAttribute("vo2", vo2);

        return "product/update";
    }

    @PostMapping("/product/updateOK")
    public String updateOK(ProductVO vo)throws IllegalStateException, IOException{
        log.info("product updateOK");

        // 스프링프레임워크에서 사용하던 리얼패스사용불가.
        // String realPath = context.getRealPath("resources/upload_img");

        // @Value("${file.dir}")로 획득한 절대경로 사용해야함.
        log.info(realPath);

        String originName = vo.getFile().getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName.length() == 0) {// 넘어온 파일이 없을때 default.png 할당
            vo.setImg_name("default.png");
        } else {
            // 중복이미지 이름을 배제하기위한 처리
            String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));
            log.info("save_name:{}", save_name);
            vo.setImg_name(save_name);

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


        int result = productService.updateOK(vo);

        if (result == 1){
            return "product/detail?num="+vo.getNum();
        }else{
            return "product/update?num="+vo.getNum();
        }
    }

    @PostMapping("/product/deleteOK")
    public String deleteOK(ProductVO vo){
        log.info("product deleteOK");

        int result = productService.deleteOK(vo);
        if (result==1) {
            return "product/list";
        }else{
            return "product/detail?num="+vo.getNum();
        }
    }

    @GetMapping("/product/detail")
    public String detail(Model model, ProductVO vo){
        log.info("product detail");

        log.info("vo:{}", vo);

        ProductVO vo2 = productService.selectOne(vo);
        log.info("vo2:{}", vo2);

        model.addAttribute("vo2", vo2);

        return "product/detail";
    }



}
