package com.book.book_project.service;

import com.book.book_project.controller.MemberController;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.repository.AddresRepository;
import com.book.book_project.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AddresRepository addresRepository;
    private final BCryptPasswordEncoder pwdEncoder;

    //회원 가입
    @Override
    public void memberInfoRegistry(MemberDTO member) {
        member.setRegdate(Timestamp.valueOf(LocalDateTime.now()));
        member.setLastpwdate(Timestamp.valueOf(LocalDateTime.now()));
        member.setPwcheck(1);
        member.setFromSocial("N");
        memberRepository.save(member.dtoToEntity(member));
    }


    // select * from tbl_member where userid = #{userid}
    //회원 정보 가져 오기
    @Override
    public MemberDTO memberInfo(String userid) {
        return memberRepository.findById(userid).map(member -> new MemberDTO(member)).get();
    }


    // update tbl_member set password = #{password}, lastpwdate= #{lastpwdate} where userid = #{userid}
    //패스워드 수정
    public void memberPasswordModify(MemberDTO member) {
        MemberEntity memberEntity =memberRepository.findById(member.getUserid()).get();
        memberEntity.setPassword(pwdEncoder.encode(member.getPassword()));
        memberRepository.save(memberEntity);
    }

    // update tbl_member set password = #{password}, lastpwdate= #{lastpwdate} where userid = #{userid}
    //패스워드 수정
    public void modifyMember(MemberDTO member) {
        MemberEntity memberEntity =memberRepository.findById(member.getUserid()).get();
        memberEntity.setPassword(pwdEncoder.encode(member.getPassword()));
        memberRepository.save(memberEntity);
    }



    public String searchId(MemberDTO member){
        return memberRepository.findByTelnoOrEmail(member.getTelno(), member.getEmail(), member.getBirthday(), member.getUsername())
                .map(MemberEntity::getUserid).orElse("ID_NOT_FOUND");
    }
}
