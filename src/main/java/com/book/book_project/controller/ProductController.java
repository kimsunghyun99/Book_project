package com.book.book_project.controller;

import com.book.book_project.dto.NewsDTO;
import com.book.book_project.dto.ProductDTO;
import com.book.book_project.service.NewsService;
import com.book.book_project.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final NewsService newsService;

    // main화면 보기
    @GetMapping("/product/main")
    public void getMain(Model model) throws Exception {

        // bookname, cover 가져오기 -> 나중에 interests 토대로 가져올 예정
        List<ProductDTO> productDTOList = service.productlist();
        model.addAttribute("productList", productDTOList);



        List<NewsDTO> newsDTOList = newsService.crawlNews();
        model.addAttribute("newsList", newsDTOList);






    }

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
