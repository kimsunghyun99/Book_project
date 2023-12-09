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
}
