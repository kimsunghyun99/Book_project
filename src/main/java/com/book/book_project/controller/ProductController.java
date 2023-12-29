package com.book.book_project.controller;

import com.book.book_project.dto.NewsDTO;
import com.book.book_project.dto.ProductDTO;
import com.book.book_project.entity.*;
import com.book.book_project.entity.repository.CategoryRepository;
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
import com.book.book_project.util.PageUtil2;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private final CategoryRepository categoryRepository;

    // main화면 보기
    @GetMapping("/product/main")
    public void getMain(Model model ,HttpSession session) throws Exception {


        String userid = (String)session.getAttribute("userid") != null?  (String)session.getAttribute("userid") : "";

        if(userid != "") {
            String interest = memberService.memberInfo(userid).getInterest();
            List<ProductEntity> productDTOList = service.productlist(interest);
            model.addAttribute("productList", productDTOList);
        } else {
            List<ProductEntity> productDTOList1 = service.productlist1();
            model.addAttribute("productList", productDTOList1);

        }

        List<NewsDTO> newsDTOList = newsService.crawlNews();
        List<ProductEntity> bookList=productRepository.getProductList();
        model.addAttribute("bookListId", bookList.get(0).getBookid());
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
    public void getProductList(@RequestParam(name = "category", required = false, defaultValue = "all") String interest, @RequestParam("page") int pageNum,@RequestParam(name="keyword",defaultValue="",required=false) String keyword, Model model) throws Exception{



        int postNum = 21; //한 화면에 보여지는 게시물 행의 갯수
//        int startPoint = (pageNum-1) * postNum+1; //페이지 시작 게시물 번호
//        int endPoint = pageNum*postNum;
        int pageListCount = 5; //화면 하단에 보여지는 페이지리스트의 페이지 갯수


        PageUtil2 page = new PageUtil2();


        if(!"all".equals(interest)) {

            List<Integer> categorynumbers = service.getCateNumber(interest);
            System.out.println("categorynumber = "+categorynumbers);

            Page<ProductEntity> list = service.list(pageNum, postNum, keyword, categorynumbers);
            System.out.println("list.size = "+list);
            int totalCount = (int)list.getTotalElements();

            model.addAttribute("pageList", page.getPageList(pageNum, postNum, pageListCount,totalCount,interest));
            model.addAttribute("productList", list);

        }else {
            // all인경우
            Page<ProductEntity> list = service.list1(pageNum, postNum, keyword);
            int totalCount = (int)list.getTotalElements();

            model.addAttribute("pageList", page.getPageList(pageNum, postNum, pageListCount,totalCount,interest));
            model.addAttribute("productList", list);
        }


//        switch(interest) {
//            case "all":
//                List<ProductEntity> productDTOList2 = service.productlist1();
//                model.addAttribute("productList", productDTOList2);
//                break;
//
//            case "OS/Networking":
//                List<ProductEntity> productDTOList3 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList3);
//                break;
//            case "프로그래밍 개발/방법론":
//                List<ProductEntity> productDTOList4 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList4);
//                break;
//            case "프로그래밍 언어":
//                List<ProductEntity> productDTOList5 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList5);
//                break;
//            case "활용능력":
//                List<ProductEntity> productDTOList6 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList6);
//                break;
//            case "e비즈니스/창업":
//                List<ProductEntity> productDTOList7 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList7);
//                break;
//            case "오피스(엑셀/파워포인트)":
//                List<ProductEntity> productDTOList8 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList8);
//                break;
//            case "웹디자인/홈페이지":
//                List<ProductEntity> productDTOList9 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList9);
//                break;
//            case "그래픽/멀티미디어":
//                List<ProductEntity> productDTOList10 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList10);
//                break;
//            case "컴퓨터 공학":
//                List<ProductEntity> productDTOList11 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList11);
//                break;
//            case "스마트폰/태블릿/SNS":
//                List<ProductEntity> productDTOList12 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList12);
//                break;
//            case "모바일 프로그래밍":
//                List<ProductEntity> productDTOList13 = service.productlist2(interest);
//                model.addAttribute("productList", productDTOList13);
//                break;
//
//        }

    }



    // 장바구니에 저장된 상품 보기
    @GetMapping("/product/shoppingBasket")
    public void getShoppingBasket(Model model, HttpSession session) throws Exception{

        String userid = (String) session.getAttribute("userid");
        System.out.println("userid = "+userid);
        if(userid!=null){
            List<CartEntity> cartEntity=cartService.cartList(userid);
            List<ProductEntity> list = new ArrayList<>();
            for(int i=0; i<cartEntity.size(); i++){
                ProductEntity productEntity=new ProductEntity();
                String  bookid = String.valueOf(cartEntity.get(i).getBookid().getBookid());
                System.out.println("bookid = "+bookid);
                productEntity = service.findById(bookid);
                System.out.println("bookname = "+productEntity.getBookname());
                System.out.println("cover = "+productEntity.getCover());
                System.out.println("author = "+productEntity.getAuthor());
                System.out.println("price = "+productEntity.getPrice());
                list.add(productEntity);
                System.out.println("listsize = "+ list.size());
                System.out.println("list.bookname = "+list.get(0).getBookname());
                System.out.println("list.cover = "+list.get(0).getCover());
                System.out.println("list.author = "+list.get(0).getAuthor());
                System.out.println("list.price = "+list.get(0).getPrice());
            }
            model.addAttribute("list",list);
            model.addAttribute("session", userid);
        }

    }


    // 장바구니로 상품 이동
    @ResponseBody
    @PostMapping("/product/shoppingBasket")
    public String postShoppingBasket(@RequestBody Map<String, String> bookid, HttpSession session) throws Exception{
        CartEntity cartEntity=new CartEntity();
        String userid = (String) session.getAttribute("userid");

        MemberEntity memberEntity = memberRepository.findById(userid).orElse(null);
        ProductEntity productEntity = productRepository.findById(bookid.get("bookid")).orElse(null);

        Timestamp cartregdate = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        System.out.println(productEntity.getBookid());

        cartEntity.setUserid(memberEntity);
        cartEntity.setBookid(productEntity);
        cartEntity.setCartregdate(Timestamp.valueOf(sdf.format(cartregdate)));

        cartService.cartRegistry(cartEntity);
        return "{\"message\":\"GOOD\"}";
    }

    //장바구니에서 상품 삭제
    @ResponseBody
    @PostMapping("/product/delete")
    public boolean postDelete(@RequestBody Map<String, Object> bookids, HttpSession session){
        List<String> bookidList = (List<String>) bookids.get("bookids");
        String userid = (String) session.getAttribute("userid");
        CartEntity cartEntity = new CartEntity();
        MemberEntity memberEntity = new MemberEntity();
        ProductEntity productEntity = new ProductEntity();
        memberEntity.setUserid(userid);
        cartEntity.setUserid(memberEntity);
        for(int i=0; i<bookidList.size(); i++){
            productEntity.setBookid(bookidList.get(i));
            cartEntity.setBookid(productEntity);
            cartService.delete(cartEntity);
        }

        return true;
    }


    //닉네임 창

    @GetMapping("/product/nickname")
    public void getNickname(){}
    @ResponseBody
    @PostMapping("/product/nickname")
    public String postNickname(HttpSession session, @RequestParam("nickname") String nickname, Model model) throws Exception {
        String userid = (String)session.getAttribute("userid");
        System.out.println("userid = "+ userid);
        session.setAttribute("nickname", nickname);
        memberService.nickname(userid,nickname);
        model.addAttribute("nicknameview" + memberService.nickname(userid,nickname));
        return "{\"data\":\"GOOD\"}";
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
    public void getPayment(
            @RequestParam(value = "bookid", required = false) String bookid,
            @RequestParam(value = "quantity", required = false) Integer  quantity,
            Model model, HttpSession session) throws Exception {

        String userid = (String)session.getAttribute("userid");

        if(session.getAttribute("productDTOList")!=null) {
            List<ProductDTO> productlist = (List<ProductDTO>) session.getAttribute("productDTOList");
            model.addAttribute("view", productlist);
        }
        // 모델에 productDTOList 추가
        else {
            List<ProductDTO> productlist = new ArrayList<>();

            ProductDTO productDTO = new ProductDTO();
            ProductEntity productEntity = service.findById(bookid);
            productDTO.setQuantity(quantity);
            productDTO.setBookname(productEntity.getBookname());
            productDTO.setSalespoint(productEntity.getSalespoint());
            productDTO.setBookid(productEntity.getBookid());
            productDTO.setAuthor(productEntity.getAuthor());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setCover(productEntity.getCover());
            productlist.add(productDTO);
            model.addAttribute("view", productlist);

            model.addAttribute("memberInfo", memberService.memberInfo(userid));
            // userid에 대한 주소지 다 꺼내기
            model.addAttribute("deliverylist", deliveryService.list(userid));
            //bookid 에 대한 정보
        }

    }

    @PostMapping("/product/payment")
    public ResponseEntity<?> postPayment(@RequestBody Map<String, List<Map<String, Object>>> payload, HttpSession session) {
        List<Map<String, Object>> items = payload.get("items");
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Map<String, Object> item : items) {
            String bookid = (String) item.get("bookid");
            int quantity = (Integer) item.get("quantity");

            ProductEntity productEntity = service.findById(bookid);
            if (productEntity != null) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setAuthor(productEntity.getAuthor());
                productDTO.setSalespoint(productEntity.getSalespoint());
                productDTO.setCover(productEntity.getCover());
                productDTO.setBookname(productEntity.getBookname());
                productDTO.setPrice(productEntity.getPrice());
                productDTO.setBookid(productEntity.getBookid());
                productDTO.setQuantity(quantity);

                productDTOList.add(productDTO);
            }
        }

        // 여기서 적절한 리다이렉트 경로를 설정하세요.
        session.setAttribute("productDTOList", productDTOList);

        // 클라이언트에 리다이렉션 URL 전송
        return ResponseEntity.ok(Map.of("redirectUrl", "/product/payment"));
    }

    @GetMapping("/product/unMemberPayment")
    public void getUnMemberPayment(
            @RequestParam(value = "bookid", required = false) String bookid,
            @RequestParam(value = "quantity", required = false) Integer  quantity,
            Model model, HttpSession session) throws Exception {

        if(session.getAttribute("productDTOList")!=null) {
            List<ProductDTO> productlist = (List<ProductDTO>) session.getAttribute("productDTOList");
            model.addAttribute("view", productlist);
        }
        // 모델에 productDTOList 추가
        else {
            List<ProductDTO> productlist = new ArrayList<>();

            ProductDTO productDTO = new ProductDTO();
            ProductEntity productEntity = service.findById(bookid);
            productDTO.setQuantity(quantity);
            productDTO.setBookname(productEntity.getBookname());
            productDTO.setSalespoint(productEntity.getSalespoint());
            productDTO.setBookid(productEntity.getBookid());
            productDTO.setAuthor(productEntity.getAuthor());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setCover(productEntity.getCover());
            productlist.add(productDTO);
            model.addAttribute("view", productlist);
        }
    }
    @PostMapping("/product/unMemberPayment")
    public ResponseEntity<?> postUnMemberPayment(@RequestBody Map<String, List<Map<String, Object>>> payload, HttpSession session) {
        List<Map<String, Object>> items = payload.get("items");
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Map<String, Object> item : items) {
            String bookid = (String) item.get("bookid");
            int quantity = (Integer) item.get("quantity");

            ProductEntity productEntity = service.findById(bookid);
            if (productEntity != null) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setAuthor(productEntity.getAuthor());
                productDTO.setSalespoint(productEntity.getSalespoint());
                productDTO.setCover(productEntity.getCover());
                productDTO.setBookname(productEntity.getBookname());
                productDTO.setPrice(productEntity.getPrice());
                productDTO.setBookid(productEntity.getBookid());
                productDTO.setQuantity(quantity);

                productDTOList.add(productDTO);
            }
        }

        // 여기서 적절한 리다이렉트 경로를 설정하세요.
        session.setAttribute("productDTOList", productDTOList);

        // 클라이언트에 리다이렉션 URL 전송
        return ResponseEntity.ok(Map.of("redirectUrl", "/product/unMemberPayment"));
    }




}
