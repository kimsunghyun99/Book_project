package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import org.springframework.stereotype.Service;


public interface MemberService {

    //회원 가입
    public void memberInfoRegistry(MemberDTO member);

    //회원 정보 가져 오기
    public MemberDTO memberInfo(String userid);

    //패스워드 수정
    public void memberPasswordModify(MemberDTO member);

    public void modifyMember(MemberDTO member);

    public String searchId(MemberDTO member);
}
