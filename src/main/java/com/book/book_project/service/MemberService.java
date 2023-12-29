package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.AddressEntity;
import com.book.book_project.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface MemberService {

    //회원 가입
    public void memberInfoRegistry(MemberDTO member);

    //회원 정보 가져 오기
    public MemberEntity memberInfo(String userid);

    MemberDTO nickname(String userid, String nickname) throws Exception;

    //패스워드 수정
    public void memberPasswordModify(MemberDTO member);

    // 회원정보 수정
    public void modifyMember(String userid,String username, String nickname,String telno, String interest);

    //리뷰 갯수 가져오기
    public long countReviewsByUserId(String userid);

    //마지막 로그인 날짜 기록
    public void lastloginUpdate(MemberDTO member);

    //마지막 로그아웃 날짜 기록
    public void lastlogoutUpdate(MemberDTO member);

    //아이디 찾기
    public String idSearch(MemberDTO member);

    //비밀번호 찾기
    String pwSearch(MemberDTO member);

    //주소 검색
    public Page<AddressEntity> addrSearch(int pageNum, int postNum, String addrSearch);

    //아이디 중복 확인
    public int idCheck(String userid);

    //회원 구매, 주문 목록 갯수 구하기
    public Long countJoinedRecordsByUserId(String userid);

    //전체 회원 목록 불러오기
    public Page<MemberEntity> findAll(Pageable pageable);

    //회원 정지
    public void stop(List<String> userids);

    //회원 정지 해제
    public void unstop(List<String> userids);

    //social 회원 수
    public int socialcount();

    //일반 회원 수
    public int normalcount();

    //일반 회원 나이대 별 회원 수 불러오기
    public List<Map<String, Integer>> memberage();

}
