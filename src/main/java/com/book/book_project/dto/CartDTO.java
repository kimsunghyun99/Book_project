package com.book.book_project.dto;

import com.book.book_project.entity.CartEntity;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {

    private MemberEntity userid;
    private ProductEntity bookid;
    private Timestamp cartregdate;





//Entity --> DTO

    public CartDTO(CartEntity cartEntity) {

        this.userid =cartEntity.getUserid();
        this.bookid =cartEntity.getBookid();
        this.cartregdate =cartEntity.getCartregdate();
    }


    //DTO --> Entity

    public CartEntity dtoToEntity(CartDTO dto)  {

        CartEntity cartEntity = CartEntity.builder()
                .userid(dto.getUserid())
                .bookid(dto.getBookid())
                .cartregdate(dto.getCartregdate())
                .build();
        return cartEntity;


    }


}
