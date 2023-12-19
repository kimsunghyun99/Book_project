package com.book.book_project.controller;

import com.book.book_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    // main화면 보기
    @GetMapping("/product/main")
    public void getMain() {}

    @GetMapping("/product/productInfo")
    public void getProductInfo(@RequestParam("bookid") String bookid, @RequestParam("page") int pageNum,
                               @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
                               Model model) throws Exception {

        model.addAttribute("view", service.view(bookid));
        model.addAttribute("page", pageNum);
        model.addAttribute("keyword", keyword);
    }

    @GetMapping("/product/favoritesList")
    public void getFavoritesList(){}

    @GetMapping("/product/productList")
    public void getProductList(){}

    @GetMapping("/product/shoppingBasket")
    public void getShoppingBasket(){}
}

    //댓글 처리
