package com.book.book_project.controller;

import com.book.book_project.dto.DeliverAddrDTO;
import com.book.book_project.service.AddressService;
import com.book.book_project.service.DeliveryService;
import com.nimbusds.openid.connect.sdk.claims.Address;
import org.springframework.ui.Model;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.dto.UnMemberDTO;
import com.book.book_project.entity.repository.UnMemberRepository;
import com.book.book_project.service.LikeService;
import com.book.book_project.entity.AddressEntity;
import com.book.book_project.service.MemberService;
import com.book.book_project.service.UnMemberService;
import com.book.book_project.util.PageUtil;
import jakarta.servlet.http.HttpSession;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {


    @Autowired
    MemberService service;

    @Autowired
    DeliveryService deliveryService;


    @Autowired
    LikeService likeService;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    @Autowired
    UnMemberService unMemberService;

    @Autowired




    @GetMapping("/member/login")
    public void getLogin() {

    }


    //회원 등록 화면 보기
    @GetMapping("/member/signup")
    public void getSignup() {
    }

    //회원 등록 하기
    @ResponseBody
    @PostMapping("/member/signup")
    public Map<String, String> postSignup(MemberDTO member,DeliverAddrDTO deliverAddr) throws Exception {

        String inputPassword = member.getPassword();
        String pwd = pwdEncoder.encode(inputPassword); //단방향 암호화
        member.setPassword(pwd);
        member.setLastpwdate(Timestamp.valueOf(LocalDateTime.now()));
        service.memberInfoRegistry(member);


        deliverAddr.setName(member.getUsername());
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
                               @RequestParam("new_password") String new_password) throws Exception {
       // HTTP session 파라미터 잠시 삭제
        System.out.println("controller1");
      //  String userid = (String)session.getAttribute("userid");

        //패스워드가 올바르게 들어 왔는지 확인
//        if(!pwdEncoder.matches(old_password, service.memberInfo(userid).getPassword())) {
//            return "{\"message\":\"PASSWORD_NOT_FOUND\"}";
//        }

        System.out.println("controller2");


        //신규 패스워드로 업데이트
        MemberDTO member = new MemberDTO();

        member.setPassword(new_password);
        member.setLastpwdate(Timestamp.valueOf(LocalDateTime.now()));
        service.memberPasswordModify(member);

        return "{\"message\":\"GOOD\"}";
    }



    //회원 정보 수정 화면
    @GetMapping("/member/memberInfoModify")
    public void getMemberInfoModify() {}


    //회원 정보 수정 하기
    @ResponseBody
    @PostMapping("/member/memberInfoModify")
    public String postMemberInfoModify(MemberDTO member, HttpSession session) throws Exception {
        member.setUserid((String)session.getAttribute("userid"));
        service.modifyMember(member);


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

    //로그인 화면 보기
    @GetMapping("/member/login")
    public void getLogin() {}

    //로그인
    @PostMapping("/member/login")
    public void postLogin() {}

    //로그인 (23-12-11)
    @ResponseBody
    @PostMapping("/member/loginCheck")
    public String postLogin(MemberDTO member, HttpSession session) {

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
        model.addAttribute("member", service.memberInfo(userid));//회원정보 불러오기
        model.addAttribute("favoriteInfo", service.findFavoritesByUserId(userid)); // 즐겨찾기 불러오기
        model.addAttribute("countJoinedRecordsByUserId", service.countJoinedRecordsByUserId(userid));//구매,주문 목록 갯수 구하기
    }

    //아이디 찾기 화면
    @GetMapping("/member/idSearch")
    public void getIdSearch() {}

    @PostMapping("/member/idSearch")
    public String postIdSearch(MemberDTO member){
        String userid = service.idSearch(member);

        return "{\"data\":\"" + userid + "\"}";
    }

    //패스워드 찾기 화면
    @GetMapping("/member/pwSearch")
    public void getPwSearch() {}

    //회원 정보 수정 화면
    @GetMapping("/member/memberInfoModify")
    public void getMemberInfoModify() {}

    //구매내역 조회 화면
    @GetMapping("/member/memberPurchaseList")
    public void getMemberPurchaseList() {}

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
        //구매번호 존재 여부 확인
//        if() {
//            return "{\"message\":\"ID_NOT_FOUND\"}";
//        }

        //비밀번호가 올바르게 들어왔는지 정확도 여부 확인
        if(!pwdEncoder.matches(unMember.getTemppassword(), unMemberService.unMemberInfo(unMember.getTemppassword()).getTemppassword())){
            //잘못된 패스워드 일 경우
            return "{\"message\":\"PASSWORD_NOT_FOUND\"}";
        }

        return "{\"message\":\"GOOD\"}";
    }

    //비회원 구매내역 조회 화면
    @GetMapping("/member/unMemberPurchaseList")
    public void getUnMemberPurchasesList() {}

}
