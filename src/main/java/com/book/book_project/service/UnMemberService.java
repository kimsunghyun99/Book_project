package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.dto.UnMemberDTO;
import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.UnMemberPurchaseInfoEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UnMemberService {

    //비회원 정보 가져오기
    List<UnMemberEntity> findByTemppassword(String temppassword);

    // 비회원 전화번호 가져오기
    List<UnMemberEntity> findByReceivertelno(String receivertelno);

    //비회원 구매 목록 가져오기
    List<Map<String, String>> unmempurchaseList(String receivertelno);

    public UnMemberEntity findByUnmemberseq(int unmemberseq);
}
