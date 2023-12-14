package com.book.book_project.controller;

import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.service.FavoritesService;
import com.book.book_project.service.FavoritesService;
import com.book.book_project.service.MemberService;
import com.book.book_project.service.ProductService;
import com.book.book_project.service.ReviewService;
import com.book.book_project.util.PageUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final MemberService memberService;
    private final ReviewService reviewService;

    // main화면 보기
    @GetMapping("/product/main")
    public void getMain() {}

    @GetMapping("/product/productInfo")
    public void getProductInfo(@RequestParam("bookid") int bookid, @RequestParam("page") int pageNum,
                               @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
                               Model model) throws Exception {

        model.addAttribute("view", service.view(bookid));
        model.addAttribute("page", pageNum);
        model.addAttribute("keyword", keyword);
    }

    @GetMapping("/product/favoritesList")
    public void getFavoritesList(HttpSession session, Model model) throws Exception {
        String userid = (String)session.getAttribute("userid");

    }

    @GetMapping("/product/productList")
    public void getProductList(){}

    @GetMapping("/product/shoppingBasket")
    public void getShoppingBasket(){}


    //닉네임 창

    @GetMapping("/product/nickname")
    public void getNickname(){}
    @ResponseBody
    @PostMapping("/product/nickname")
    public String postNickname(@RequestParam("nickname") String nickname) throws Exception {
        String userid = "aa@aa.com";
        memberService.nickname(userid,nickname);
        return "{\"message\":\"GOOD\"}";
    }


    //리뷰 처리
    @ResponseBody
    @PostMapping("/product/review")
    public List<ReviewInterface> postReview(ReviewInterface review, @RequestParam("option") String option) throws Exception {

        switch (option) {

            case "I":
                reviewService.reviewRegistry(review); //리뷰 등록
                break;
            case "U":
                reviewService.reviewUpdate(review); //리뷰 수정
                break;
            case "D":
                reviewService.reviewDelete(review); //리뷰 삭제
                break;
        }

        return reviewService.reviewView(review);
    }
}
