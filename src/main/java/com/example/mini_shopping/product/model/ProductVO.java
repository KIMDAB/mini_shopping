package com.example.mini_shopping.product.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductVO {
    private int num;
    private String name;
    private String content;
    private int price;
    private String user_id;

    private String img_name;
    private MultipartFile file;
}
