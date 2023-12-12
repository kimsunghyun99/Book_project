package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.AddressEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface MemberService {

    //회원 가입
    void memberInfoRegistry(MemberDTO member);

    //회원 정보 가져 오기
    MemberDTO memberInfo(String userid);

    //패스워드 수정
    void memberPasswordModify(MemberDTO member);

    void modifyMember(MemberDTO member);

    //아이디 찾기
    String idSearch(MemberDTO member);

    //비밀번호 찾기
    String pwSearch(MemberDTO member);

    //주소 검색
    public Page<AddressEntity> addrSearch(int pageNum, int postNum, String addrSearch);
}
