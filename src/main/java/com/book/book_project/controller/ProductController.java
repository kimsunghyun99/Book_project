package com.book.book_project.controller;

import com.book.book_project.dto.NewsDTO;
import com.book.book_project.dto.ProductDTO;
import com.book.book_project.entity.*;
import com.book.book_project.service.NewsService;
import com.book.book_project.service.ProductService;
import com.book.book_project.dto.CartDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.dto.ReviewInterfaceImpl;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.repository.CartRepository;
import com.book.book_project.entity.repository.MemberRepository;
import com.book.book_project.entity.repository.ProductRepository;
import com.book.book_project.service.*;
import com.book.book_project.util.PageUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final NewsService newsService;
    private final MemberService memberService;
    private final ReviewService reviewService;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartService cartService;
    private  final CartRepository cartRepository;
    private final DeliveryService deliveryService;

    // main화면 보기
    @GetMapping("/product/main")
    public void getMain(Model model) throws Exception {
       // System.out.println("시작");
       //  bookid, bookname, cover 가져오기 -> 나중에 interests 토대로 가져올 예정
        List<ProductEntity> productDTOList = service.productlist();
        model.addAttribute("productList", productDTOList);




        List<NewsDTO> newsDTOList = newsService.crawlNews();
        List<ProductEntity> bookList=productRepository.getProductList();
        model.addAttribute("bookListId", bookList.get(0).getBookid()); // 이거 null -> 수ㅡ정필요
        model.addAttribute("newsList", newsDTOList);






    }

    @GetMapping("/product/productInfo")
    public void getProductInfo(@RequestParam("page") int pageNum,
                               @RequestParam("bookid") ProductEntity bookid,
                               Model model,
                               HttpSession session) throws Exception {

        //상품정보를 불러와야함.
        String bookId= String.valueOf(bookid.getBookid());

        int postNum = 10; //한 화면에 보여지는 게시물 행의 갯수
        int pageListCount = 5; //화면 하단에 보여지는 페이지리스트의 페이지 갯수
        System.out.println("ProductEntity1: "+bookid.getClass().getName());
        System.out.println("ProductEntity2: "+bookid.getBookid());

        PageUtil page = new PageUtil();
        Page<ReviewEntity> list = reviewService.list(bookid, pageNum, postNum);
        int totalCount = (int)list.getTotalElements();
        if(session.getAttribute("userid")!=null){
            String userid = (String) session.getAttribute("userid");
            String nickname = memberService.memberInfo(userid).getNickname();
            model.addAttribute("nickname", nickname);
        }
        model.addAttribute("view", service.view(bookId));
        model.addAttribute("list", list);
        model.addAttribute("totalElement", totalCount);
        model.addAttribute("postNum", postNum);
        model.addAttribute("page", pageNum);
        model.addAttribute("pageList", page.getPageList(pageNum, postNum, pageListCount,totalCount, bookId));
    }


    @GetMapping("/product/productList")
    public void getProductList(HttpSession session, Model model) throws Exception{
        String bookid = (String) session.getAttribute("bookid");
        model.addAttribute("view", service.view(bookid));
    }



    // 장바구니에 저장된 상품 보기
    @GetMapping("/product/shoppingBasket")
    public void getShoppingBasket(Model model, HttpSession session) throws Exception{

        String userid = (String)session.getAttribute("userid");
        if(userid!=null){
            List<CartDTO> cartList=cartRepository.findByUserid(userid);

            model.addAttribute("list",cartList);
        }

    }


    // 장바구니로 상품 이동
    @ResponseBody
    @PostMapping("/product/shoppingBasket")
    public int postShoppingBasket(@RequestBody CartEntity cartEntity, HttpSession session) throws Exception{

        String userid = (String)session.getAttribute("userid");
        String bookid = cartEntity.getBookid().getBookid();


        if(cartService.bCartQuantity(userid,bookid) == 0 ){
            cartService.bCartInsert(userid,bookid);
        }
        else{
            cartService.bCartUpdate(userid,bookid);
        }
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


    // 결제화면
    @GetMapping("/product/payment")
    public void getPayment(@RequestParam("bookid") String bookid, Model model, HttpSession session) throws Exception {


        String userid = (String)session.getAttribute("userid");

        // userid에 대한 주소지 다 꺼내기
        model.addAttribute("delverylist", deliveryService.list(userid));
        //bookid 에 대한 정보
        model.addAttribute("view", service.view(bookid));





    }







}
