package com.book.book_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    // main화면 보기
    @GetMapping("/product/main")
    public void getSignup() {}

    @GetMapping("/product/productInfo")
    public void getReview(){}
}
