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

    //비회원 정보 조회
    @Override
    public UnMemberDTO unMemberInfo(String unmembertelno) {
        return unMemberRepository.findById(unmembertelno).map(unmember -> new UnMemberDTO(unmember)).get();
    }

}
