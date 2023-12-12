package com.book.book_project.service;

import com.book.book_project.controller.MemberController;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.AddressEntity;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.repository.AddresRepository;
import com.book.book_project.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    //회원정보 수정
    public void modifyMember(MemberDTO member) {
        MemberEntity memberEntity =memberRepository.findById(member.getUserid()).get();
        memberEntity.setPassword(pwdEncoder.encode(member.getPassword()));
        memberRepository.save(memberEntity);
    }

    //주소 검색
    @Override
    public Page<AddressEntity> addrSearch(int pageNum, int postNum, String addrSearch) {
        PageRequest pageRequest = PageRequest.of(pageNum-1, postNum, Sort.by(Sort.Direction.ASC, "zipcode"));
        System.out.println(addresRepository.findByRoadContainingOrBuildingContaining(addrSearch, addrSearch, pageRequest).getContent());
        return addresRepository.findByRoadContainingOrBuildingContaining(addrSearch, addrSearch, pageRequest);
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
