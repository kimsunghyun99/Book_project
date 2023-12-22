package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.dto.UnMemberDTO;
import com.book.book_project.entity.UnMemberEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UnMemberService {

    public UnMemberDTO unmemberInfo(String receivertelno);

    public int unmemberpurchasenum(int unmemberseq);
}
