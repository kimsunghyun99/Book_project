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
@Entity(name="purchaseinfo")
@Table(name="tbl_purchaseinfo")
public class PurchaseInfoEntity {

    @Id
    @Column(name="purchaseinfonumber", nullable=false)
    private int purchaseinfonumber;

    @Column(name="deliverystatus",length =200, nullable=false)
    private String deliverystatus;

    @Column(name="purchasedate", nullable=false)
    private Timestamp purchasedate;

    @Column(name="volume", nullable=false)
    private int volume;

    @Column(name="totalPrice", nullable=false)
    private int totalprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="bookid", nullable = false)
    private ProductEntity bookid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="buyerseq", nullable = false)
    private BuyerInfoEntity buyerseq;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="statusseq", nullable = false)
    private PurchaseStatusEntity statusseq;






}
