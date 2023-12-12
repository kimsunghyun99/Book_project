package com.book.book_project.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="deliveraddr")
@Table(name="tbl_deliveraddr")
public class DeliveryAddrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryseq;

    // FK 만들기
    // FK 읽어올 때 Eager, lazy 두가지 타입이 있음
    // Eager는 부모키가 있는 테이블부터 검사해서 부모키가 제대로 되어 있는지 확인하고 자식키를 읽음 --> 정확도는 높지만 성능이 저하
    // Lazy는 자식키가 있는 테이블만 읽음 -> 정확도는 떨어지지만 성능이 향상
    @ManyToOne(fetch =FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="userid", nullable=false)
    private MemberEntity userid;

    @Column(name="addr", length=200, nullable=false)
    private String addr;

    @Column(name="detailaddr", length=200, nullable=false)
    private String detailaddr;

    @Column(name="zipcode", nullable=false)
    private int zipcode;

    @Column(name="name", length=50, nullable=false)
    private String name;

    @Column(name="memo",length=250, nullable=true)
    private String memo;

    @Column(name="telno", length=15, nullable=false)
    private String telno;



}
