package com.book.book_project.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="cart")
@Table(name="tbl_cart")
@IdClass(CartEntityID.class)
public class CartEntity {
    // FK 만들기
    // FK 읽어올 때 Eager, lazy 두가지 타입이 있음
    // Eager는 부모키가 있는 테이블부터 검사해서 부모키가 제대로 되어 있는지 확인하고 자식키를 읽음 --> 정확도는 높지만 성능이 저하
    // Lazy는 자식키가 있는 테이블만 읽음 -> 정확도는 떨어지지만 성능이 향상
    @Id
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="userid", nullable=false)
    private MemberEntity userid;

    @Id
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="bookid", nullable=false)
    private ProductEntity bookid;

    @Column(name="cartregdate", nullable=false)
    private Timestamp cartregdate;


}
