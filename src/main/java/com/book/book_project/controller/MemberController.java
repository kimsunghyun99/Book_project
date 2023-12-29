package com.book.book_project.controller;

import com.book.book_project.dto.DeliverAddrDTO;
import com.book.book_project.entity.*;
import com.book.book_project.entity.repository.BuyerInfoRepository;
import com.book.book_project.entity.repository.MemberRepository;
import com.book.book_project.entity.repository.PurchaseInfoRepository;
import com.book.book_project.entity.repository.PurchaseStatusRepository;
import com.book.book_project.service.DeliveryService;
import com.book.book_project.dto.*;
import com.book.book_project.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.book.book_project.util.PageUtil;
import jakarta.servlet.http.HttpSession;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {


    @Autowired
    MemberService service;

    @Autowired
    ProductService productService;

    @Autowired
    DeliveryService deliveryService;


    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    @Autowired
    UnMemberService unMemberService;

    @Autowired
    MemberService memberService;

    private final BuyerInfoService buyerInfoService;

    @Autowired
    private PurchaseInfoRepository purchaseInfoRepository;

    @Autowired
    private PurchaseStatusRepository purchaseStatusRepository;

    @Autowired
    private BuyerInfoRepository buyerInfoRepository;


    //회원 등록 화면 보기
    @GetMapping("/member/signup")
    public void getSignup() {}

    //회원 등록 하기
    @ResponseBody
    @PostMapping("/member/signup")
    public Map<String, String> postSignup(MemberDTO member,DeliverAddrDTO deliverAddr) throws Exception {

        System.out.println("MemberDTO"+ member.toString());
        System.out.println("deliverAddr"+ deliverAddr.toString());

        String inputPassword = member.getPassword();
        String pwd = pwdEncoder.encode(inputPassword); //단방향 암호화
        member.setPassword(pwd);
        member.setLastpwdate(Timestamp.valueOf(LocalDateTime.now()));
        service.memberInfoRegistry(member);

        System.out.println("member"+member.getUserid());

        deliverAddr.setName(member.getUsername());
        deliverAddr.setUserid(member.dtoToEntity(member));

        deliveryService.memberaddrInfoRegistry(deliverAddr);

        Map<String, String> data = new HashMap<>();
        data.put("message", "GOOD");
        data.put("username", URLEncoder.encode(member.getUsername(), "UTF-8"));

        return data;
    }

    //패스워드 변경 화면
    @GetMapping("/member/pwModify")
    public void getPwModify() {}


    //회원 패스워드 변경
    @ResponseBody
    @PostMapping("/member/pwModify")
    public String postPwModify(@RequestParam("old_password") String old_password,
                               @RequestParam("new_password") String new_password,HttpSession session) throws Exception {

        String userid = (String)session.getAttribute("userid");

        //패스워드가 올바르게 들어 왔는지 확인
        if(!pwdEncoder.matches(old_password, service.memberInfo(userid).getPassword())) {
            return "{\"message\":\"PASSWORD_NOT_FOUND\"}";
        }

        //신규 패스워드로 업데이트
        MemberDTO member = new MemberDTO();
        member.setUserid(userid);
        member.setPassword(new_password);
        member.setLastpwdate(Timestamp.valueOf(LocalDateTime.now()));
        service.memberPasswordModify(member);

        return "{\"message\":\"GOOD\"}";
    }



    //회원 정보 수정 화면
    @GetMapping("/member/memberInfoModify")
    public void getMemberInfoModify(HttpSession session, Model model ) throws Exception {

        String userid = (String)session.getAttribute("userid");
        model.addAttribute("memberInfo", service.memberInfo(userid));
        List<DeliveryAddrEntity> deliveryAddrEntityList=deliveryService.list(userid);

        model.addAttribute("list", deliveryAddrEntityList);
    }



    //회원 정보 수정 하기 , 주소 추가, 삭제
    @ResponseBody
    @PostMapping("/member/memberInfoModify")
    public String postMemberInfoModify( HttpSession session, @RequestBody DeliverAddrDTO deliverAddrDTO, @RequestParam("option") String option)throws Exception {

        MemberEntity memberEntity = new MemberEntity();
        String userid = (String)session.getAttribute("userid");
        memberEntity.setUserid(userid);
        deliverAddrDTO.setUserid(memberEntity);


//        System.out.println("주소");
//        System.out.println(deliverAddrDTO.getDeliveryseq());
//        System.out.println(deliverAddrDTO.getAddr());
//        System.out.println(deliverAddrDTO.getZipcode());
//        System.out.println("컨트롤러1");
//        System.out.println(option);
//        System.out.println("구분");
//        System.out.println(deliverAddrDTO.getTelno());


        int deleteseq = (deliverAddrDTO.getDeliveryseq());


        switch(option) {

            case "I" : deliveryService.adddeliveraddr(deliverAddrDTO); // 회원 배송지 등록
                break;
            case "D" : deliveryService.deletedeliveraddr(deleteseq); // 회원 배송지 삭제 완료
                break;
        }

        return "{\"message\":\"GOOD\"}";

    }


    //회원 정보만 수정 하기
    @ResponseBody
    @PostMapping("/member/memberInfoModify1")
    public String postMemberInfoModify( @RequestBody MemberDTO member, HttpSession session, @RequestParam("option") String option)throws Exception {


        String userid = (String)session.getAttribute("userid");
        member.setUserid(userid);
        //member.setPassword(service.memberInfo(userid).getPassword());


        System.out.println("컨트롤러1");

//        System.out.println(member.getUsername());
//        System.out.println(member.getNickname());
//        System.out.println(member.getTelno());


        String username = member.getUsername();
        String nickname = member.getNickname();
        String telno = member.getTelno();


        if ("U".equals(option)) {
            service.modifyMember(userid,username, nickname,telno); // 회원 기본정보 수정
        }

        return "{\"message\":\"GOOD\"}";

    }



    //주소 검색
    @GetMapping("/member/addrSearch")
    public void getAddrsearch(@RequestParam("addrSearch") String addrSearch,
                              @RequestParam("page") int pageNum, Model model) {

        int postNum = 5; //한 화면에 보여지는 게시물 행의 갯수
        int startPoint = (pageNum - 1) * postNum + 1; //페이지 시작 게시물 번호
        int endPoint = pageNum * postNum;
        int pageListCount = 5; //화면 하단에 보여지는 페이지리스트의 페이지 갯수


        Page<AddressEntity> list = service.addrSearch(pageNum, postNum, addrSearch);
        int totalCount = (int) list.getTotalElements();

        PageUtil page = new PageUtil();

        model.addAttribute("list", list);

        model.addAttribute("pageList", page.getPageAddress(pageNum, postNum, pageListCount, totalCount, addrSearch));

    }

// 회원가입 시 아이디체크
    @ResponseBody
    @PostMapping("/member/idCheck")
    public int postIdCheck(@RequestBody String userid) throws Exception {
        int result = service.idCheck(userid);
        return result;

    }



    //로그인 화면 보기
    @GetMapping("/member/login")
    public void getLogin() {}

    //로그인
    @PostMapping("/member/login")
    public void postLogin() {}

    //로그인 (23-12-11)
    @ResponseBody
    @PostMapping("/member/loginCheck")
    public String postLogin(MemberDTO member) {

        //아이디 존재 여부 확인
        if(service.idCheck(member.getUserid()) == 0) {
            return "{\"message\":\"ID_NOT_FOUND\"}";
        }

        //비밀번호가 올바르게 들어왔는지 정확도 여부 확인
        if(!pwdEncoder.matches(member.getPassword(), service.memberInfo(member.getUserid()).getPassword())){
            //잘못된 패스워드 일 경우
            return "{\"message\":\"PASSWORD_NOT_FOUND\"}";
        }

        return "{\"message\":\"GOOD\"}";

    }

    //마이페이지 화면 (23-12-11)
    @GetMapping("/member/mypage")
    public void getMyPage(HttpSession session, Model model) {
        String userid = (String)session.getAttribute("userid");
        model.addAttribute("memberInfo", service.memberInfo(userid));//회원정보 불러오기
//        model.addAttribute("favoriteInfo", service.findFavoritesByUserId(userid)); // 즐겨찾기 불러오기
//        model.addAttribute("countJoinedRecordsByUserId", service.countJoinedRecordsByUserId(userid));//구매,주문 목록 갯수 구하기
    }

    //아이디 찾기 화면
    @GetMapping("/member/idSearch")
    public void getIdSearch() {}

    //아이디 찾기
    @PostMapping("/member/idSearch")
    public String postIdSearch(MemberDTO member){
        String userid = service.idSearch(member);

        return "{\"data\":\"" + userid + "\"}";
    }

    //패스워드 찾기 화면
    @GetMapping("/member/pwSearch")
    public void getPwSearch() {}


    //회원 구매내역 조회 화면
    @Transactional
    @PostMapping(value = "/member/memberPurchaseList")
    public ResponseEntity<String> getMemberPurchaseList(
            HttpSession session,
            @RequestBody Map<String, Object> requestData
    ) throws Exception {

//
//        String amount = requestData.get("amount").toString();
//        String merchant_uid = requestData.get("merchant_uid").toString();
//        String dateString = requestData.get("date").toString();
//        String quantity  = requestData.get("bookquntity").toString();


        String amount = requestData.get("amount") != null ? requestData.get("amount").toString() : "";
        System.out.println("1" + amount);
        String merchant_uid = requestData.get("merchant_uid") != null ? requestData.get("merchant_uid").toString() : "";
        System.out.println("2" + merchant_uid);
        String dateString = requestData.get("date") != null ? requestData.get("date").toString() : "";
        System.out.println("3" + dateString);
        String quantity = requestData.get("bookquntity") != null ? requestData.get("bookquntity").toString() : "";
        System.out.println("4" + quantity);
        String bookid = requestData.get("bookid") != null ? requestData.get("bookid").toString() : "";
        System.out.println("5" + bookid);
        String receiveraddr = requestData.get("address") != null ? requestData.get("address").toString() : "";
        System.out.println("6" + receiveraddr);
        String receiverdetailaddr = requestData.get("detailaddr") != null ? requestData.get("detailaddr").toString() : "";
        System.out.println("7" + receiverdetailaddr);
        String receiverzipcode = requestData.get("postcode") != null ? requestData.get("postcode").toString() : "";
        System.out.println("8" + receiverzipcode);
        String userid = requestData.get("userid") != null ? requestData.get("userid").toString() : "";
        System.out.println("9" + userid);
        String receivertelno = requestData.get("receivertelno") != null ? requestData.get("receivertelno").toString() : "";
        System.out.println("10" + receivertelno);
        String receivername = requestData.get("username") != null ? requestData.get("username").toString() : "";
        System.out.println("11" + receivername);





//        String bookid = requestData.get("bookid").toString();
//        String receiveraddr = requestData.get("address").toString();
//        String receiverdetailaddr = requestData.get("detailaddr").toString();
//        String receiverzipcode = requestData.get("postcode").toString();
//        String userid = requestData.get("userid").toString();
//        String receivertelno = requestData.get("telno").toString();
//        String receivername = requestData.get("username").toString();


        ProductEntity productEntity = productService.findById(bookid);
        System.out.println("///////////" + productEntity );

        MemberEntity memberEntity = memberService.findById(userid);

        BuyerInfoEntity buyerInfo = new BuyerInfoEntity();

        buyerInfo.setUserid(memberEntity);
        buyerInfo.setReceiveraddr(receiveraddr);
        buyerInfo.setReceiverdetailaddr(receiverdetailaddr);
        buyerInfo.setReceiverzipcode(receiverzipcode);
        buyerInfo.setReceivertelno(receivertelno);
        buyerInfo.setReceivername(receivername);

        buyerInfoRepository.save(buyerInfo);


        // 여기서 dateString을 Date로 변환
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date date = dateFormat.parse(dateString);

        int buyerseq = buyerInfo.getBuyerseq();

        BuyerInfoEntity buyerInfoEntity = buyerInfoService.findByBuyerseq(buyerseq);
        PurchaseStatusEntity statusEntity = purchaseStatusRepository.findByStatusseq(1);

        PurchaseInfoEntity purchaseInfo = new PurchaseInfoEntity();
        purchaseInfo.setBookid(productEntity);
        purchaseInfo.setPurchasedate(new java.sql.Timestamp(date.getTime()));
        purchaseInfo.setTotalprice(Integer.parseInt(amount));
        purchaseInfo.setPurchaseinfonumber(Integer.parseInt(merchant_uid));
        purchaseInfo.setVolume(Integer.parseInt(quantity));
        purchaseInfo.setBuyerseq(buyerInfoEntity);
        purchaseInfo.setStatusseq(statusEntity);

        purchaseInfoRepository.save(purchaseInfo);



        // 나머지 로직 수행
        // ...

        // 성공했을 경우 응답
        return ResponseEntity.ok("Success");
    }





    //비회원 구매내역 조회 화면
//    @GetMapping("/member/unMemberPurchaseList")
//    public void getUnMemberPurchaseList(Model model, HttpSession session,PurchaseInfoService purchaseInfoService) throws Exception {
//        UnMemberEntity unmemberseq = (UnMemberEntity)
//        List<UnMemberEntity> unMemberEntityList=unMemberService.unMemberInfo(unmembertelno);
//
//        List<PurchaseInfoEntity> purchaseInfoList = new ArrayList<>();
//
//        for(UnMemberEntity unMemberEntity:unMemberEntityList){
//            UnMemberEntity unmembertelno = unMemberEntity;
//            List<PurchaseInfoEntity> purchaseList = purchaseInfoService.unMemberPurchaseList(unmembertelno);
//            purchaseInfoList.addAll(purchaseList);
//            model.addAttribute("purchaseList",purchaseInfoList);
//        }
//    }


    //비회원 로그인 화면
    //비회원 로그인 화면 (23-12-12)
    @GetMapping("/member/unMemberLogin")
    public void getUnMemberLogin() {}

    //비회원 로그인 (23-12-12)
    @PostMapping("/member/unMemberLogin")
    public void postUnMemberLogin() {}

    //비회원 로그인 (23-12-12)
    @ResponseBody
    @PostMapping("/member/unMemberLoginCheck")
    public String postUnMemberLogin(UnMemberDTO unMember) {
        //전화번호 존재 여부 확인
        if(unMemberService.unmemberInfo(unMember.getReceivertelno()).getUnmemberseq() == 0) {
            return "{\"message\":\"receivertelno_NOT_FOUND\"}";
        }
        //비밀번호가 올바르게 들어왔는지 정확도 여부 확인
        if(!pwdEncoder.matches(unMember.getTemppassword(), unMemberService.unmemberInfo(unMember.getReceivertelno()).getTemppassword())){
            //잘못된 패스워드 일 경우
            return "{\"message\":\"PASSWORD_NOT_FOUND\"}";
        }
        //구매번호 존재 여부 확인
        if(unMemberService.unmemberpurchasenum(unMember.getUnmemberseq()) == 0) {
            return "{\"message\":\"ID_NOT_FOUND\"}";
        }

        return "{\"message\":\"GOOD\"}";
    }



}
