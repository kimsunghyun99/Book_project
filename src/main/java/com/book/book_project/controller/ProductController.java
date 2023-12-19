package com.book.book_project.controller;

import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.dto.ReviewInterfaceImpl;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import com.book.book_project.entity.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    // main화면 보기
    @GetMapping("/product/main")
    public void getMain(Model model) {
        List<ProductEntity> bookList=productRepository.getProductList();
        model.addAttribute("bookListId", bookList.get(0).getBookid()); // 이거 null -> 수ㅡ정필요

        System.out.println(bookList.get(0).getBookid());
    }

    @GetMapping("/product/productInfo")
    public void getProductInfo(@RequestParam("page") int pageNum,
                               @RequestParam("bookid") String bookid,
                               Model model,
                               HttpSession session) throws Exception {

        int postNum = 5; //한 화면에 보여지는 게시물 행의 갯수
        int pageListCount = 5; //화면 하단에 보여지는 페이지리스트의 페이지 갯수

        PageUtil page = new PageUtil();
        Page<ReviewEntity> list = reviewService.list(pageNum, postNum);
        int totalCount = (int)list.getTotalElements();
        String userid = (String)session.getAttribute("userid");
        String nickname = memberService.memberInfo(userid).getNickname();


        model.addAttribute("nickname", nickname);
        model.addAttribute("view", service.view(bookid));
        model.addAttribute("list", reviewService.list(pageNum,postNum));
        model.addAttribute("totalElement", totalCount);
        model.addAttribute("postNum", postNum);
        model.addAttribute("page", pageNum);
        model.addAttribute("pageList", page.getPageList(pageNum, postNum, pageListCount,totalCount));



    }

    @GetMapping("/product/favoritesList")
    public void getFavoritesList(HttpSession session, Model model) throws Exception {
        String userid = (String)session.getAttribute("userid");

    }

    @GetMapping("/product/productList")
    public void getProductList(HttpSession session, Model model) throws Exception{
        String bookid = (String) session.getAttribute("bookid");
        model.addAttribute("view", service.view(bookid));
    }

    @GetMapping("/product/shoppingBasket")
    public void getShoppingBasket(){}


    //닉네임 창

    @GetMapping("/product/nickname")
    public void getNickname(){}
    @ResponseBody
    @PostMapping("/product/nickname")
    public String postNickname(HttpSession session, @RequestParam("nickname") String nickname, Model model) throws Exception {
        String userid = (String)session.getAttribute("userid");
        session.setAttribute("nickname", nickname);
        memberService.nickname(userid,nickname);
        model.addAttribute("nicknameview" + memberService.nickname(userid,nickname));
        return "{\"message\":\"GOOD\"}";
    }




    //리뷰 처리
    @ResponseBody
    @PostMapping(value = "/product/review")
    public List<ReviewInterface> postReview(@RequestBody ReviewInterfaceImpl review, @RequestParam("option") String option) throws Exception {

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
