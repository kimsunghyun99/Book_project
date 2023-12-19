package com.book.book_project.service;

import com.book.book_project.dto.BuyerInfoDTO;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.dto.PurchaseInfoDTO;
import com.book.book_project.entity.AddressEntity;
import org.springframework.data.domain.Page;
import com.book.book_project.entity.FavoritesEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MemberService {

    //회원 가입
    public void memberInfoRegistry(MemberDTO member);

    //회원 정보 가져 오기
    public MemberDTO memberInfo(String userid);

    //패스워드 수정
    public void memberPasswordModify(MemberDTO member);

    // 회원정보 수정
    public void modifyMember(String userid,String username, String nickname,String telno);



    //아이디 찾기
    public String idSearch(MemberDTO member);

    //비밀번호 찾기
    String pwSearch(MemberDTO member);

//    //구매자정보 가져오기
//    BuyerInfoDTO buyerAndPurchaseInfo(String userid);

    //주소 검색
    public Page<AddressEntity> addrSearch(int pageNum, int postNum, String addrSearch);



    //아이디 중복 확인
    public int idCheck(String userid);

    //회원 구매, 주문 목록 갯수 구하기
    public Long countJoinedRecordsByUserId(String userid);

    //즐겨찾기 정보 가져오기
    public List<FavoritesEntity> findFavoritesByUserId(String userid);





}
