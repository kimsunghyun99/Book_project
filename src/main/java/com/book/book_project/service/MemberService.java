package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import org.springframework.stereotype.Service;


public interface MemberService {

    public String searchId(MemberDTO member);
}
