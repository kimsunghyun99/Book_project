package com.book.book_project.controller;

import org.springframework.ui.Model;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.AddressEntity;
import com.book.book_project.service.MemberService;
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
    public void getSignup() {
    }

    //회원 등록 하기
    @ResponseBody
    @PostMapping("/member/signup")
    public Map<String, String> postSignup(MemberDTO member) throws Exception {

        String inputPassword = member.getPassword();
        String pwd = pwdEncoder.encode(inputPassword); //단방향 암호화
        member.setPassword(pwd);
        member.setLastpwdate(Timestamp.valueOf(LocalDateTime.now()));
        member.setMemberclass("bronze");
        member.setRole("USER");
        member.setFromSocial("N");
        service.memberInfoRegistry(member);

        Map<String, String> data = new HashMap<>();
        data.put("message", "GOOD");
        data.put("username", URLEncoder.encode(member.getUsername(), "UTF-8"));

        return data;
    }

    //패스워드 변경 화면
    @GetMapping("/member/pwModify")
    private void getPwModify() {
    }


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
    @PostMapping("/member/idSearch")
    private String postIdSearch(MemberDTO member){
        String userid = service.idSearch(member);

    }
}