package com.example.mini_shopping.product.controller;

import com.example.mini_shopping.product.model.ProductVO;
import com.example.mini_shopping.product.service.ProductService;
import jakarta.servlet.http.HttpSession;
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
    public String list(Model model, @RequestParam(defaultValue = "1")int page,
                       @RequestParam(defaultValue = "8")int limit){
        log.info("product list");

        List<ProductVO> list = productService.selectAll(page, limit);
        log.info("list:{}", list);

        int totalPages = productService.getListCnt();
        int totalCnt = (int)Math.ceil((double) totalPages/limit);

        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("totalCnt", totalCnt);

        return "product/list";
    }

    @GetMapping("/product/search")
    public String search(Model model, @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "8") int limit,
                         @RequestParam String searchWord){
        log.info("search");

        List<ProductVO> list = productService.search( searchWord, page, limit);

        int totalPages = productService.getsearchCnt(searchWord);
        int searchtotalCnt = (int)Math.ceil((double) totalPages/limit);

        model.addAttribute("list", list);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("page", page);
        model.addAttribute("searchtotalCnt", searchtotalCnt);

        return "product/list";
    }

    @GetMapping("/product/insert")
    public String insert( HttpSession session, Model model){
        log.info("product insert");

        String id = (String) session.getAttribute("id");
        log.info("id:{}", id);

        model.addAttribute("session", session);
        model.addAttribute("id", id);

        return "product/insert";
    }

    @PostMapping("/product/insertOK")
    public String insertOK(ProductVO vo, Model model)throws IllegalStateException, IOException {
        log.info("product insertOK");

        try {
            // 상품 추가 및 이미지 업로드
            productService.addProduct(vo);
            model.addAttribute("message", "상품이 성공적으로 등록되었습니다.");
        } catch (IOException e) {
            model.addAttribute("error", "파일 업로드에 실패했습니다.");
        }

        return "redirect:/product/list"; // 성공 시 결과 페이지
    }

    @GetMapping("/product/update")
    public String update(Model model, ProductVO vo, @RequestParam int num){
        log.info("product update");

        ProductVO vo2 = productService.selectOne(num);
        log.info("vo2:{}", vo2);

        model.addAttribute("vo2", vo2);

        return "product/update";
    }

    @PostMapping("/product/updateOK")
    public String updateOK(ProductVO vo)throws IllegalStateException, IOException{
        log.info("product updateOK");

        int result = productService.updateOK(vo);

        if (result == 1){
            return "product/detail?num="+vo.getNum();
        }else{
            return "product/update?num="+vo.getNum();
        }
    }

    @GetMapping("/product/deleteOK")
    public String deleteOK(ProductVO vo){
        log.info("product deleteOK");

        int result = productService.deleteOK(vo);
        if (result==1) {
            return "redirect:/product/list";
        }else{
            return "product/detail?num="+vo.getNum();
        }
    }

    @GetMapping("/product/detail")
    public String detail(Model model, ProductVO vo, @RequestParam int num){
        log.info("product detail");

        log.info("vo:{}", vo);

        ProductVO vo2 = productService.selectOne(num);
        log.info("vo2:{}", vo2);

        model.addAttribute("vo2", vo2);

        return "product/detail";
    }



}
