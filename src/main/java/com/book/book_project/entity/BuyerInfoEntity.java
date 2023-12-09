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
public class BuyerInfoEntity {

    @Id
    @Column(name = "purchaseseq")
    private Long purchaseseq;

    @Column(name = "purchaseaddr", length = 100, nullable = true)
    private String purchaseaddr;

    @Column(name = "purchasezipcode", length = 10, nullable = true)
    private Long purchasezipcode;

    @Column(name = "purchasedetailaddr", length = 20, nullable = true)
    private String purchasedetailaddr;

    @Column(name = "purchasename", length = 10, nullable = true)
    private String purchasename;

    @Column(name = "purchasetelno", length = 10, nullable = true)
    private String purchasetelno;


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="userid", nullable = false)
    private MemberEntity userid;


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="unmembertelno", nullable = false)
    private UnMemberEntity unmembertelno;



}