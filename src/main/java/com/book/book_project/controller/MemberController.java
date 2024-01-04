package com.book.book_project.controller;

import com.book.book_project.dto.DeliverAddrDTO;
import com.book.book_project.entity.*;
import com.book.book_project.entity.repository.*;
import com.book.book_project.service.DeliveryService;
import com.book.book_project.dto.*;
import com.book.book_project.service.*;
import org.springframework.boot.web.servlet.server.Session;
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

import java.lang.reflect.Member;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

//    @Autowired
//    LikeService likeService;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    @Autowired
    UnMemberService unMemberService;

    @Autowired
    MemberService memberService;

    private final BuyerInfoService buyerInfoService;
    private final PurchaseInfoRepository purchaseInfoRepository;
    private final BuyerInfoRepository buyerInfoRepository;
    private final ProductRepository productRepository;
    private final PurchaseStatusRepository purchaseStatusRepository;
    private final PurchaseInfoService purchaseInfoService;
    private final PurchaseStatusService purchaseStatusService;
    private final RefundService refundService;
    private final RefundRepository refundRepository;
    private final UnMemberRepository unMemberRepository;
    private final UnMemeberPurchaseInfoRepository unMemeberPurchaseInfoRepository;





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

        String username = member.getUsername();
        String nickname = member.getNickname();
        String telno = member.getTelno();
        String interest = member.getInterest();

        if ("U".equals(option)) {
            service.modifyMember(userid,username, nickname,telno, interest); // 회원 기본정보 수정
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
        service.lastloginUpdate(member);
        return "{\"message\":\"GOOD\"}";

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
    @GetMapping("/member/memberPurchaseList")
    public void getMemberPurchaseList(Model model, HttpSession session,PurchaseInfoService purchaseInfoService) throws Exception {

        String userid = (String) session.getAttribute("userid");
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserid(userid);

        List<BuyerInfoEntity> buyerInfo=buyerInfoService.buyerInfo(memberEntity); // -> userid에 대한 받는이 주소, 집코드, 주소, 이름, 번호가 담김
        List<PurchaseInfoEntity> purchaseInfoList = new ArrayList<>();
        List<String> BookNameList = new ArrayList<>();
        List<String> BookIdList = new ArrayList<>();
        List<String> StatusList = new ArrayList<>();

        Collections.reverse(buyerInfo);

        for(int i =0; i<buyerInfo.size(); i++) {

            BuyerInfoEntity buyerInfoEntity = buyerInfoRepository.findById(buyerInfo.get(i).getBuyerseq()).orElse(null);
            PurchaseInfoEntity purchaseInfoEntity =  purchaseInfoRepository.findByBuyerseq(buyerInfoEntity); // buyerseq 값 정의
            purchaseInfoList.add(purchaseInfoEntity);

            String bookid  = String.valueOf(purchaseInfoEntity.getBookid().getBookid());
            String bookname = productRepository.getBookName(bookid);
            String statusseq =  String.valueOf(purchaseInfoEntity.getStatusseq().getStatusseq());
           String statusname = purchaseStatusService.getStatusName(statusseq);

            BookIdList.add(bookid);
            StatusList.add(statusname);
            BookNameList.add(bookname);

        }
        model.addAttribute("bookids", BookIdList);
        model.addAttribute("booknames", BookNameList);
        model.addAttribute("purchaseInfo",purchaseInfoList);
        model.addAttribute("statusList", StatusList);
//        System.out.println("purchaseInfolist 실험 : " + purchaseInfoList.get(0));
    }


    //회원 구매내역 교환,환불, 취소 처리 , 철회처리
    @PostMapping("/member/memberPurchaseList")
    @ResponseBody
    public String postMemberPurchaseList(@RequestBody RefundDTO refundDTO, @RequestParam("option") String option)throws Exception {

        // 교환일경우
        if(option.equals("e")) {
            int statsseq = 6;
            refundService.ExchangeRegistry(refundDTO);
            purchaseInfoService.updateStatusseq(statsseq,refundDTO.getPurchaseinfonumber().getPurchaseinfonumber());

        // 환불일 경우
        }else if(option.equals("r")){
            int statsseq = 7;
            refundService.RefundRegistry(refundDTO);
            purchaseInfoService.updateStatusseq(statsseq,refundDTO.getPurchaseinfonumber().getPurchaseinfonumber());
        }
        else if(option.equals("c")) {
            int statsseq = 10;
            System.out.println(refundDTO.getPurchaseinfonumber().getPurchaseinfonumber());
            purchaseInfoService.updateStatusseq(statsseq, refundDTO.getPurchaseinfonumber().getPurchaseinfonumber());
        }
        else if(option.equals("w")) {

        }
        else if(option.equals("d")) {
            int statsseq = 11;
            purchaseInfoService.updateStatusseq(statsseq, refundDTO.getPurchaseinfonumber().getPurchaseinfonumber());
        }
        // 교환 또는 환불 신청해놓고 그걸 취소하는 경우 -> 배송완료로 변경 , refund 값 삭제
        else if(option.equals("cer")) {
            int statsseq = 5;
            purchaseInfoService.updateStatusseq(statsseq, refundDTO.getPurchaseinfonumber().getPurchaseinfonumber());
            refundService.delete(refundDTO.getPurchaseinfonumber().getPurchaseinfonumber());
        }

        return "{\"message\":\"GOOD\"}";
    }

    //비회원 구매내역 조회 화면
    @GetMapping("/member/unMemberPurchaseList")
    public String getUnMemberPurchaseList(@RequestParam("receivertelno") String receivertelno,Model model) throws Exception {
        List<Map<String, String>> list = unMemberRepository.unmempurchaseList(receivertelno);

        List<Map<String, Object>> purchaseList = new ArrayList<>();

        for(Map<String, String> map : list){
            Map<String, Object> purchaseListMap = new HashMap<>();
            purchaseListMap.put("purchasedate", map.get("purchasedate"));
            purchaseListMap.put("unmemberpurchaseinfoseq", map.get("unmemberpurchaseinfoseq"));
            purchaseListMap.put("statusseq", map.get("statusseq"));
            purchaseListMap.put("total_price", map.get("total_price"));
            purchaseListMap.put("bookname", map.get("bookname"));
            purchaseListMap.put("statusname", map.get("statusname"));

            purchaseList.add(purchaseListMap);
        }
        model.addAttribute("purchaseList", purchaseList);
        return "/member/unMemberPurchaseList";
    }

    //비회원 로그인 화면
    @GetMapping("/member/unMemberLogin")
    public void getUnMemberLogin() {}

    //비회원 로그인 (23-12-12)
    @PostMapping("/member/unMemberLogin")
    public void postUnMemberLogin() {}

    //비회원 로그인 (23-12-12)
    @ResponseBody
    @PostMapping("/member/unMemberLoginCheck")
    public String postUnMemberLogin(UnMemberDTO unMember, HttpSession session) {
        //아이디 존재 여부 확인
        if(unMemberService.findByReceivertelno(unMember.getReceivertelno()) == null) {
            return "{\"message\":\"Receivertelno_NOT_FOUND\"}";
        }

        //비밀번호가 올바르게 들어왔는지 정확도 여부 확인
        if (unMemberService.findByTemppassword(unMember.getTemppassword()) == null) {
            return "{\"message\":\"PASSWORD_NOT_FOUND\"}";
        }

        session.setAttribute("receivertelno", unMember.getReceivertelno());
        System.out.println(session);
        return "{\"message\":\"GOOD\"}";

    }

    @Transactional
    @PostMapping(value = "/member/paymentInfo")
    public ResponseEntity<String> postPaymentInfo(
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
        String finalpoint = requestData.get("finalpoint") != null ? requestData.get("finalpoint").toString() : "";
        System.out.println("12" + finalpoint);
        String totalpoint = requestData.get("totalpoint") != null ? requestData.get("totalpoint").toString() : "";
        System.out.println("13" + totalpoint);


//        String bookid = requestData.get("bookid").toString();
//        String receiveraddr = requestData.get("address").toString();
//        String receiverdetailaddr = requestData.get("detailaddr").toString();
//        String receiverzipcode = requestData.get("postcode").toString();
//        String userid = requestData.get("userid").toString();
//        String receivertelno = requestData.get("telno").toString();
//        String receivername = requestData.get("username").toString();


        ProductEntity productEntity = productService.findById(bookid);
        System.out.println("///////////" + productEntity);

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

        MemberEntity memberinfo = new MemberEntity();

        int originalPoint = memberService.getPoint(userid);
        int currentPoint = originalPoint + Integer.parseInt(finalpoint);
        memberinfo.setPoint(currentPoint);


        // 나머지 로직 수행
        // ...

        int finalpointValue = Integer.parseInt(finalpoint);
        int totalpointValue = Integer.parseInt(totalpoint);

        boolean finalpointSuccess = (finalpointValue > 0);
        boolean totalpointSuccess = (totalpointValue > 0);

        System.out.println(finalpointValue);


        System.out.println("finalpointSuccess" + finalpointSuccess);
        System.out.println("totalpointSuccess" + totalpointSuccess);

        if (finalpointSuccess) {
            System.out.println("1번");
            boolean updateSuccess = memberService.finalupdatePoint(userid, Integer.parseInt(finalpoint),Integer.parseInt(totalpoint) );
           // boolean totalupdateSuccess = memberService.totalupdatePoint(userid, Integer.parseInt(totalpoint));
            if (updateSuccess) {
                System.out.println("2번");
                int updatedPoint = memberService.getPoint(userid);


                System.out.println("여기탔어요" + updatedPoint);
//                if (totalupdateSuccess) {
//                    System.out.println("5번");
//                    int totaledPoint = memberService.getPoint(userid);
//                    return ResponseEntity.ok("포인트 업데이트 성공! 현재 포인트: " + totaledPoint);
//                } else {
//                    System.out.println("6번");
//                    return ResponseEntity.badRequest().body("사용자가 존재하지 않아 포인트 업데이트 실패!");
//                }
            }

            } else {
                System.out.println("실패했음. 다시해야함");
                return ResponseEntity.ok("Fail");
            }
            // 성공했을 경우 응답
        return ResponseEntity.ok("Success");
        }

    @Transactional
    @PostMapping(value = "/member/unmemberpaymentInfo")
    public ResponseEntity<String> postunmemberpaymentInfo(
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
        String receivertelno = requestData.get("receivertelno") != null ? requestData.get("receivertelno").toString() : "";
        System.out.println("10" + receivertelno);
        String receivername = requestData.get("username") != null ? requestData.get("username").toString() : "";
        System.out.println("11" + receivername);
        String password = requestData.get("Pw") != null ? requestData.get("Pw").toString() : "";
        System.out.println("12" + password);
        String buyertelno = requestData.get("buyerTel") != null ? requestData.get("buyerTel").toString() : "";
        System.out.println("13" + buyertelno);






//        String bookid = requestData.get("bookid").toString();
//        String receiveraddr = requestData.get("address").toString();
//        String receiverdetailaddr = requestData.get("detailaddr").toString();
//        String receiverzipcode = requestData.get("postcode").toString();
//        String userid = requestData.get("userid").toString();
//        String receivertelno = requestData.get("telno").toString();
//        String receivername = requestData.get("username").toString();


        ProductEntity productEntity = productService.findById(bookid);
        System.out.println("///////////" + productEntity );

//        MemberEntity memberEntity = memberService.findById(userid);

        UnMemberEntity unMemberEntity = new UnMemberEntity();

        unMemberEntity.setTemppassword(password);
        unMemberEntity.setBuyertelno(buyertelno);
        unMemberEntity.setAddr(receiveraddr);
        unMemberEntity.setDetailaddr(receiverdetailaddr);
        unMemberEntity.setZipcode(receiverzipcode);
        unMemberEntity.setReceivertelno(receivertelno);
        unMemberEntity.setReceivername(receivername);

        unMemberRepository.save(unMemberEntity);


        // 여기서 dateString을 Date로 변환
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date date = dateFormat.parse(dateString);

        int unmemberseq = unMemberEntity.getUnmemberseq();

        UnMemberEntity unMember = unMemberService.findByUnmemberseq(unmemberseq);
        PurchaseStatusEntity statusEntity = purchaseStatusRepository.findByStatusseq(1);

        UnMemberPurchaseInfoEntity unmemberPurchaseInfo = new UnMemberPurchaseInfoEntity();

        unmemberPurchaseInfo.setBookid(productEntity);
        unmemberPurchaseInfo.setPurchasedate(new java.sql.Timestamp(date.getTime()));
        unmemberPurchaseInfo.setTotalprice(Integer.parseInt(amount));
        unmemberPurchaseInfo.setUnmemberpurchaseinfoseq(Integer.parseInt(merchant_uid));
        unmemberPurchaseInfo.setUnmemberseq(unMember);
        unmemberPurchaseInfo.setStatusseq(statusEntity);
        unmemberPurchaseInfo.setVolume(Integer.parseInt(quantity));

        unMemeberPurchaseInfoRepository.save(unmemberPurchaseInfo);



        // 나머지 로직 수행
        // ...

        // 성공했을 경우 응답
        return ResponseEntity.ok("Success");
    }


}
