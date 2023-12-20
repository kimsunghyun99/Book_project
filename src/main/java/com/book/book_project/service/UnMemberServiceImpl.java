package com.book.book_project.service;

import com.book.book_project.dto.UnMemberDTO;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.repository.UnMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnMemberServiceImpl implements UnMemberService{

    private final UnMemberRepository unMemberRepository;

    //비회원 정보 불러오기
    @Override
    public UnMemberDTO unmemberInfo(String temppassword) {
        return unMemberRepository.findById(temppassword).map(unmember -> new UnMemberDTO(unmember)).get();
    }

    //비회원 주문 번호 불러오기
    @Override
    public int unmemberpurchasenum(int unmemberseq) {
        return unMemberRepository.findByUnmemberseq(unmemberseq);
    }
}
