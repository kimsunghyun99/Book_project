package com.book.book_project.controller;

import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.service.ProductService;
import com.book.book_project.util.PageUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ReviewService  reviewService;

    // main화면 보기
    @GetMapping("/product/main")
    public void getMain() {
    }

    @GetMapping("/product/productInfo")
    public void getProductInfo(@RequestParam("bookid") int bookid, @RequestParam("page") int pageNum,
                               @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
                               Model model) throws Exception {

        model.addAttribute("view", service.view(bookid));
        model.addAttribute("page", pageNum);
        model.addAttribute("keyword", keyword);
    }

    @GetMapping("/product/favoritesList")
    public void getFavoritesList() {
    }

    @GetMapping("/product/productList")
    public void getProductList() {
    }

    @GetMapping("/product/shoppingBasket")
    public void getShoppingBasket() {
    }


    //리뷰 처리
    @ResponseBody
    @PostMapping("/product/review")
    public List<ReviewInterface> postReview(ReviewInterface review, @RequestParam("option") String option) throws Exception {

        switch (option) {

            case "I":
                reviewService.ReviewRegistry(review); //리뷰 등록
                break;
            case "U":
                reviewService.ReviewUpdate(review); //리뷰 수정
                break;
            case "D":
                reviewService.ReviewDelete(review); //리뷰 삭제
                break;
        }

        return reviewService.ReviewView(review);
    }

}