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
import java.util.Optional;

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

    //닉네임
    @Override
    public MemberDTO nickname(String userid, String nickname) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(userid);

        if (optionalMember.isPresent()) {
            MemberEntity member = optionalMember.get();
            member.setNickname(nickname); // 닉네임 설정
            memberRepository.save(member); // 변경된 회원 정보 저장
            return new MemberDTO(member);
        } else {
            return null;
        }
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



    //아이디 찾기
    public String idSearch(MemberDTO member){
        return memberRepository.idFindByTelno(member.getTelno(), member.getUsername(), member.getBirthday())
                .map(MemberEntity::getUserid).orElse("ID_NOT_FOUND");
    }
    
    //비밀번호찾기
    public String pwSearch(MemberDTO member){


        return memberRepository.findById(member.getUserid())
                .map(MemberEntity::getPassword).orElse("PW_NOT_FOUND");
    }
}
