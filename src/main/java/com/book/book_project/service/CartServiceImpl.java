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
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

//    @Override
//    //// 장바구니에 있는 해당 상품 개수 세기 1개의 상품이 몇개있는지
//    public int bCartQuantity(String userid,String bookid) {
//        return cartRepository.bCartQuantity(userid, bookid);
//    }
    @Override
    // 장바구니 추가 // 아무것도 없을 시
    public void bCartInsert(String userid, String bookid){

        ProductEntity productEntity =productRepository.findById(bookid).get();
        MemberEntity memberEntity = memberRepository.findById(userid).get();

        CartEntity cartEntity = CartEntity.builder()
                .userid(memberEntity)
                .bookid(productEntity)
                .cartregdate((Timestamp.valueOf(LocalDateTime.now())))
                .build();
        cartRepository.save(cartEntity);

    };

    @Override
    // 장바구니 업뎃 // 있을 시
    public void bCartUpdate(String userid, String bookid){

        ProductEntity productEntity =productRepository.findById(bookid).get();
        MemberEntity memberEntity = memberRepository.findById(userid).get();
        CartEntity cartEntity = cartRepository.findByBookidAndUserid(productEntity, memberEntity);

        cartEntity.setCartregdate((Timestamp.valueOf(LocalDateTime.now())));

        cartRepository.save(cartEntity);
    };



    // 장바구니 몇개의 종류 있는지 세기
    @Override
    public int bCartCount(String userid) {
       return cartRepository.bCartCount(userid);
    }


    // 장바구니에 담긴 상품 목록보기
//    @Override
//    public List<CartDTO> bCartView(String userid) {
//       // MemberEntity memberEntity = new MemberEntity();
//        return cartRepository.bCartView(userid);
//    };





}
