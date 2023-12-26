package com.book.book_project.service;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.entity.CartEntity;
import com.book.book_project.entity.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {


    // 장바구니에 있는 해당 상품 개수 세기
//    public int bCartQuantity(String userid,String bookid);


    // 장바구니 추가 // 아무것도 없을 시
    public void bCartInsert(String userid, String bookid);


    // 장바구니 업뎃 // 있을 시
    public void bCartUpdate(String userid, String bookid);


    // 장바구니 몇개의 종류 있는지 세기
    public int bCartCount(String userid);

    // 장바구니에 담긴 상품 목록보기
//    public List<CartDTO> bCartView(String userid);


}
