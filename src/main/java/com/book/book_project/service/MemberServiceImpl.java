package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.*;
import com.book.book_project.entity.repository.AddresRepository;
import com.book.book_project.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

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
        member.setRole("user");
        member.setFromSocial("N");
        member.setSuspend("N");
        member.setRole("USER");
        memberRepository.save(member.dtoToEntity(member));
    }


    // select * from tbl_member where userid = #{userid}
    //회원 정보 가져 오기
    @Override
    public MemberEntity memberInfo(String userid) {
        return memberRepository.findById(userid).orElse(null);
    }

    //회원 구매,주문 갯수 가져오기
    @Override
    public Long countJoinedRecordsByUserId(String userid) {
        return memberRepository.countJoinedRecordsByUserId(userid);
    }

    @Override
    public Page<MemberEntity> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    //닉네임
    @Override
    @Transactional
    public MemberDTO nickname(String userid, String nickname) throws Exception {
        Optional<MemberEntity> optionalMember = memberRepository.findById(userid);

        System.out.println("impl" + optionalMember);

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
    @Override
    public void memberPasswordModify(MemberDTO member) {
        MemberEntity memberEntity =memberRepository.findById(member.getUserid()).get();
        memberEntity.setPassword(pwdEncoder.encode(member.getPassword()));
       memberRepository.save(memberEntity);
    }

    //회원정보 수정
    @Override
    public void modifyMember(String userid,String username, String nickname,String telno, String interest) {
//        member.dtoToEntity(member);
//       // memberEntity.setPassword(pwdEncoder.encode(member.getPassword()));
//         //memberRepository.membermodify(member.dtoToEntity());
//        member.setRole("USER");
//        member.setFromSocial("N");
//        member.setMemberclass("bronze");
     //   MemberEntity memberEntity = member.dtoToEntity(member);
      //  memberRepository.save(memberEntity);  // 변경 내용을 데이터베이스에 저장
        memberRepository.membermodify(userid,username, nickname, telno, interest);
    }

    //주소 검색
    @Override
    public Page<AddressEntity> addrSearch(int pageNum, int postNum, String addrSearch) {
        PageRequest pageRequest = PageRequest.of(pageNum-1, postNum, Sort.by(Sort.Direction.ASC, "zipcode"));
        //System.out.println(addresRepository.findByRoadContainingOrBuildingContaining(addrSearch, addrSearch, pageRequest).getContent());
        return addresRepository.findByRoadContainingOrBuildingContaining(addrSearch, addrSearch, pageRequest);
    }

    //아이디 찾기
    public String idSearch(MemberDTO member){
        return memberRepository.findByTelno(member.getTelno(), member.getUsername(), member.getBirthday())
                .map(MemberEntity::getUserid).orElse("ID_NOT_FOUND");
    }

    //리뷰 갯수 가져오기
    @Override
    public long countReviewsByUserId(String userid){
        return memberRepository.countReviewsByUserId(userid);
    }

    //아이디 중복 확인
    @Override
    public int idCheck(String userid) {
        return memberRepository.findById(userid).isEmpty()?0:1;
    }


    //비밀번호찾기
    public String pwSearch(MemberDTO member){


        return memberRepository.findById(member.getUserid())
                .map(MemberEntity::getPassword).orElse("PW_NOT_FOUND");
    }

    //마지막 로그인 날짜 기록
    @Override
    public void lastloginUpdate(MemberDTO member){
        MemberEntity memberEntity = memberRepository.findById(member.getUserid()).get();
        memberEntity.setLastlogindate(member.getLastlogindate());
        memberRepository.save(memberEntity);
    }
    //마지막 로그아웃 날짜 기록
    @Override
    public void lastlogoutUpdate(MemberDTO member){
        MemberEntity memberEntity = memberRepository.findById(member.getUserid()).get();
        memberEntity.setLastlogoutdate(member.getLastlogoutdate());
        memberRepository.save(memberEntity);
    }

    //회원 정지
    @Override
    public void stop(List<String> userids) {
        for(String userid : userids){
        MemberEntity memberEntity = memberRepository.findById(userid).get();
            if(memberEntity.getSuspend().equals("Y")){
                memberEntity.setSuspend("N");
            }else if(memberEntity.getSuspend().equals("N")){
                memberEntity.setSuspend("Y");
            }

        memberRepository.save(memberEntity);
        }
    }

    //회원 정지 해제
    @Override
    public void unstop(List<String> userids){
        for(String userid : userids){
            MemberEntity memberEntity = memberRepository.findById(userid).get();
            memberEntity.setSuspend("N");
            memberRepository.save(memberEntity);
        }
    }

    //social 회원 수
    @Override
    public int socialcount() {
        int social = socialcount();
        System.out.println(social);
        return memberRepository.socialcount();
    }

    //일반 회원 수 불러오기
    @Override
    public int normalcount(){
        int normal = normalcount();
        return memberRepository.normalcount();
    }

    //일반 회원 나이대 별 수 불러오기
    @Override
    public List<Map<String, Integer>> memberage(){
        List<Map<String, Integer>> list = memberRepository.memberage();

        return list;
    }
    @Override
    public MemberEntity findById(String userid) {
        return memberRepository.findById(userid).orElse(null);
    }






}
