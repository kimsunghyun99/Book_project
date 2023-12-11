package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.repository.AddresRepository;
import com.book.book_project.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

public String searchId(MemberDTO member){
    return memberRepository.findByTelnoOrEmail(member.getTelno(), member.getEmail(), member.getBirthday(), member.getUsername())
            .map(MemberEntity::getUserid).orElse("ID_NOT_FOUND");
}
}
