package com.book.book_project.service;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.entity.CartEntity;
import com.book.book_project.entity.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    void cartRegistry(CartEntity cartEntity);

    List<CartEntity> cartList(String userid);

    void delete(CartEntity cartEntity);

    //회원 별 장바구니 갯수 구하기
    public int usercartcount(String userid);
}
