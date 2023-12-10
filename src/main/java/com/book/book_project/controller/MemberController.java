package com.book.book_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    //회원 등록 화면 보기
    @GetMapping("/member/signup")
    public void getSignup() {}

    //로그인 화면 보기
    @GetMapping("/member/login")
    private void getLogin() {}

    //마이페이지 화면
    @GetMapping("/member/mypage")
    private void getMyPage() {}

    //아이디 찾기 화면
    @GetMapping("/member/idSearch")
    private void getIdSearch() {}

    //패스워드 찾기 화면
    @GetMapping("/member/pwSearch")
    private void getPwSearch() {}

    //패스워드 변경 화면
    @GetMapping("/member/pwModify")
    private void getPwModify() {}

    //회원 정보 수정 화면
    @GetMapping("/member/memberInfoModify")
    private void getMemberInfoModify() {}

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
