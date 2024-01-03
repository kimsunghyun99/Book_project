package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.dto.UnMemberDTO;
import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.UnMemberPurchaseInfoEntity;
import com.book.book_project.entity.repository.UnMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnMemberServiceImpl implements UnMemberService{

    private final UnMemberRepository unMemberRepository;

    //비회원 정보 가져오기
    @Override
    public List<UnMemberEntity> findByTemppassword(String temppassword){
        return unMemberRepository.findByTemppassword(temppassword);
    }

    // 비회원 전화번호 가져오기
    @Override
    public List<UnMemberEntity> findByReceivertelno(String receivertelno){
        return unMemberRepository.findByReceivertelno(receivertelno);
    }

    //비회원 구매 목록 가져오기
    @Override
    public List<Map<String, String>> unmempurchaseList(String receivertelno){
        List<Map<String, String>> list = unMemberRepository.unmempurchaseList(receivertelno);
        return list;
    }

    public UnMemberEntity findByUnmemberseq(int unmemerseq) {
        return unMemberRepository.findByUnmemberseq(unmemerseq);
    }
}
