package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.dto.UnMemberDTO;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.UnMemberPurchaseInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UnMemberService {

    //비회원 정보 가져오기
    List<UnMemberEntity> findByTemppassword(String temppassword);

    //비회원 구매내역 가져오기
    List<UnMemberPurchaseInfoEntity> unmemberList(String receivertelno);
}
