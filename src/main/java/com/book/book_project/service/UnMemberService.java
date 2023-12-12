package com.book.book_project.service;

import com.book.book_project.dto.UnMemberDTO;
import org.springframework.stereotype.Service;

@Service
public interface UnMemberService {

    //비회원 정보
    public UnMemberDTO unMemberInfo(String unmembertelno);
}
