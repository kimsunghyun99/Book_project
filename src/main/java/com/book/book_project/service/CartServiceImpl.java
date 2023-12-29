package com.book.book_project.service;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.CartEntity;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.repository.CartRepository;
import com.book.book_project.entity.repository.MemberRepository;
import com.book.book_project.entity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public
class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;


    @Override
    public void cartRegistry(CartEntity cartEntity) {
        cartRepository.save(cartEntity);
    }

    @Override
    public List<CartEntity> cartList(String userid) {
        return cartRepository.findByUserid(userid);
    }

    @Override
    public void delete(CartEntity cartEntity) {
        cartRepository.delete(cartEntity);
    }

    //회원 별 장바구니 갯수 구하기
    @Override
    public int usercartcount(String userid){
        return cartRepository.usercartcount(userid);
    }
}
