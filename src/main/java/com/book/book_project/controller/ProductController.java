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
        }

        model.addAttribute("memberInfo", memberService.memberInfo(userid));
        // userid에 대한 주소지 다 꺼내기
        model.addAttribute("deliverylist", deliveryService.list(userid));
        //bookid 에 대한 정보
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




}
