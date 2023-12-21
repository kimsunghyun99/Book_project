package com.book.book_project.service;

import com.book.book_project.dto.UnMemberDTO;
import org.springframework.stereotype.Service;

@Service
public interface UnMemberService {

    public UnMemberDTO unmemberInfo(String temppassword);

    public int unmemberpurchasenum(int unmemberseq);
}
