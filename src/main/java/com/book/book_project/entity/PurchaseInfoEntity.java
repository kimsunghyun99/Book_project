package com.book.book_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name="purchaseinfo")
@Table(name="tbl_purchaseinfo")
public class PurchaseInfoEntity {

    @Id
    @Column(name = "purchase_seq")
    private Long purchase_seq;

    @Column(name = "purchase_addr", length = 100, nullable = true)
    private String purchase_addr;

    @Column(name = "purchase_zipcode", length = 10, nullable = true)
    private Long purchase_zipcode;

    @Column(name = "purchase_detail_addr", length = 20, nullable = true)
    private String purchase_detail_addr;

    @Column(name = "purchase_name", length = 10, nullable = true)
    private String purchase_name;

    @Column(name = "purchase_telno", length = 10, nullable = true)
    private String purchase_telno;


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="userid", nullable = false)
    private MemberEntity userid;



}