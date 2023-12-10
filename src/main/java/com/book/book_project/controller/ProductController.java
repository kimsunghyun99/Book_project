package com.book.book_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    // main화면 보기
    @GetMapping("/product/main")
    public void getMain() {}

    @GetMapping("/product/productInfo")
    public void getReview(){}

    @GetMapping("/product/favoritesList")
    public void getFavoritesList(){}

    @GetMapping("/product/productList")
    public void getProductList(){}

    @GetMapping("/product/shoppingBasket")
    public void getShoppingBasket(){}
}
