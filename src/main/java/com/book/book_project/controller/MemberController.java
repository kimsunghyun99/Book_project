package com.book.book_project.controller;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.service.MemberService;
import jakarta.servlet.http.HttpSession;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
    private BCryptPasswordEncoder pwdEncoder;


    //회원 등록 화면 보기
    @GetMapping("/member/signup")
    public void getSignup() {}

    //회원 등록 하기
    @ResponseBody
    @PostMapping("/member/signup")
    public Map<String,String> postSignup(MemberDTO member) throws Exception {

        String inputPassword = member.getPassword();
        String pwd = pwdEncoder.encode(inputPassword); //단방향 암호화
        member.setPassword(pwd);
        member.setLastpwdate(Timestamp.valueOf(LocalDateTime.now()));
        service.memberInfoRegistry(member);

        Map<String,String> data = new HashMap<>();
        data.put("message", "GOOD");
        data.put("username", URLEncoder.encode(member.getUsername(),"UTF-8"));

        return data;
    }

    //패스워드 변경 화면
    @GetMapping("/member/pwModify")
    private void getPwModify() {}


    //회원 패스워드 변경
    @ResponseBody
    @PostMapping("/member/pwModify")
    public String postPwModify(@RequestParam("old_password") String old_password,
                               @RequestParam("new_password") String new_password, HttpSession session) throws Exception {

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
    public void getMemberInfoModify() {}


    @ResponseBody
    @PostMapping("/member/memberInfoModify")
    public String postMemberInfoModify(MemberDTO member, HttpSession session) throws Exception {
        member.setUserid((String)session.getAttribute("userid"));
        service.modifyMember(member);


        return "{\"message\":\"GOOD\"}";

    }





    //마이페이지 화면
    @GetMapping("/member/mypage")
    private void getMyPage() {}






    //로그인 화면 보기
    @GetMapping("/member/login")
    private void getLogin() {}



    //아이디 찾기 화면
    @GetMapping("/member/idSearch")
    private void getIdSearch() {}

    @PostMapping("/member/idSearch")
    private String postIdSearch(MemberDTO member){
        String userid = service.idSearch(member);

        return "{\"data\":\"" + userid + "\"}";
    }

    //패스워드 찾기 화면
    @GetMapping("/member/pwSearch")
    private void getPwSearch() {}



    //구매내역 조회 화면
    @GetMapping("/member/memberPurchaseList")
    private void getMemberPurchaseList() {}

    //비회원 로그인 화면
    @GetMapping("/member/unMemberLogin")
    private void getUnMemberLogin() {}

    //비회원 구매내역 조회 화면
    @GetMapping("/member/unMemberPurchaseList")
    private void getUnMemberPurchasesList() {}

}
