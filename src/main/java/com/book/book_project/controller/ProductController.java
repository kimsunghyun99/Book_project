package com.book.book_project.controller;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.CartEntity;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import com.book.book_project.entity.repository.MemberRepository;
import com.book.book_project.entity.repository.ProductRepository;
import com.book.book_project.service.*;
import com.book.book_project.service.FavoritesService;
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
    private final MemberRepository memberRepository;
    private final CartService cartService;

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

        String userid = (String)session.getAttribute("userid");

        MemberEntity memberEntity = memberRepository.findById(userid).orElse(null);
        ProductEntity productEntity = productRepository.findById(bookid).orElse(null);;

        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserid(memberEntity);
        cartDTO.setBookid(productEntity);

        int postNum = 5; //한 화면에 보여지는 게시물 행의 갯수
        int pageListCount = 5; //화면 하단에 보여지는 페이지리스트의 페이지 갯수

        PageUtil page = new PageUtil();
//        Page<ReviewEntity> list = service.list(pageNum, postNum);
//        int totalCount = (int)list.getTotalElements();
        String nickname = memberService.memberInfo(userid).getNickname();


        model.addAttribute("nickname", nickname);
        model.addAttribute("view", service.view(bookid));
        model.addAttribute("bCartQuantity", cartService.bCartQuantity(userid,bookid));  // 장바구니에 있는 해당 상품 개수 세기
     //   model.addAttribute("bCartCount", service.bCartCount)   // 안 만듬


//        model.addAttribute("list", service.list(pageNum,postNum));
//        model.addAttribute("totalElement", totalCount);
//        model.addAttribute("postNum", postNum);
//        model.addAttribute("page", pageNum);
//        model.addAttribute("pageList", page.getPageList(pageNum, postNum, pageListCount,totalCount));

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



    // 장바구니에 저장된 상품 보기
    @GetMapping("/product/shoppingBasket")
    public void getShoppingBasket(Model model, HttpSession session) throws Exception{



        // 비회원일 경우 ( seession 이 존재하느냐를 따져서 해야함 -> 일단 회원만 되게 설정)
        String userid = (String)session.getAttribute("userid")==null?"":(String)session.getAttribute("email");

        model.addAttribute("pCartCount", cartService.bCartCount(userid));
        model.addAttribute("list", cartService.bCartView(userid));
        System.out.println(cartService.bCartView(userid).get(0)); // -> list 길이 0 나옴 -> 값 못가져온단소리

    }


    // 장바구니로 상품 이동
    @ResponseBody
    @PostMapping("/product/shoppingBasket")
    public int postShoppingBasket(@RequestBody CartDTO cartDTO, HttpSession session) throws Exception{

        String userid = (String)session.getAttribute("userid");
        String bookid = cartDTO.getBookid().getIdAsString();
        int cartvolume = cartDTO.getCartvolume();


        if(cartService.bCartQuantity(userid,bookid) == 0 )
            cartService.bCartInsert(userid,bookid,cartvolume);
        else
            cartService.bCartUpdate(userid,bookid,cartvolume);

       return cartService.bCartCount(userid);


    }


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
    @PostMapping("/product/review")
    public List<ReviewInterface> postReview(@RequestBody ReviewInterface review, @RequestParam("option") String option) throws Exception {


        System.out.println(review.getReviewer());
        System.out.println(option);
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

            case "L":
                reviewService.reviewView(review); // 리뷰 목록 조회
                break;
        }

        return reviewService.reviewView(review);
    }
}
