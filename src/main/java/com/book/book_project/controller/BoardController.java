package com.book.book_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // main화면 보기
    @GetMapping("/board/main")
    public void getSignup() {}
}
