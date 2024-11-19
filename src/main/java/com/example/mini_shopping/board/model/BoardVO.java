package com.example.mini_shopping.board.model;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardVO {
    private int num;
    private String title;
    private String content;
    private String writer;
    private Date wdate;
}
