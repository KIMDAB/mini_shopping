package com.example.mini_shopping.product.controller;

import com.example.mini_shopping.member.model.MemberVO;
import com.example.mini_shopping.member.service.MemberService;
import com.example.mini_shopping.product.model.ProductVO;
import com.example.mini_shopping.product.service.ProductService;
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
public class ProdectContoller {

    @Autowired
    ProductService productService;
    @Autowired
    private MemberService memberService;

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
    public String insertOK(ProductVO vo){
        log.info("product insertOK");

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
    public String updateOK(ProductVO vo){
        log.info("product updateOK");

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
