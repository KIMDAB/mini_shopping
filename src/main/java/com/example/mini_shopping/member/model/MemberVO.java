package com.example.mini_shopping.member.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class MemberVO {
    private int num;
    private String id;
    private String pw;
    private String name;
    private String tel;
    private String email;

}
