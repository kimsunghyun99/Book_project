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
@Entity(name="purchasedetail")
@Table(name="tbl_purchasedetail")
public class PurchaseDetailEntity {

    @Id
    @Column(name="purchasedetailseq", nullable=false)
    private int purchasedetailseq;

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
    @JoinColumn(name="unmembertelno", nullable = false)
    private UnMemberEntity unmembertelno;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="purchaseseq", nullable = false)
    private RefundEntity purchaseseq;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="statusseq", nullable = false)
    private PurchaseStatusEntity statusseq;






}
