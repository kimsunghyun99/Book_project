package com.book.book_project.dto;

import com.book.book_project.entity.CartEntity;
import com.book.book_project.entity.MemberEntity;

import java.sql.Timestamp;

public class CartDTO {

    private String cartid;
    private MemberEntity userid;
    private String bookid;
    private int cartvolume;
    private Timestamp cartregdate; // 생일

//Entity --> DTO

    public CartDTO(CartEntity cartEntity) {

        this.cartid =cartEntity.getCartid();
        this.userid =cartEntity.getUserid();
        this.bookid =cartEntity.getBookid();
        this.cartvolume =cartEntity.getCartvolume();
        this.cartregdate =cartEntity.getCartregdate();
    }


    //DTO --> Entity

    public CartEntity dtotoEntity(CartDTO dto)  {

        CartEntity cartEntity = CartEntity.builder()
                .cartid(dto.getCartid())
                .userid(dto.getUserid())
                .bookid(dto.getBookid())
                .cartvolume(dto.getCartvolume())
                .cartregdate(dto.getCartregdate())
                .build();
        return cartEntity;


    }
}
