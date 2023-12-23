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
@Entity(name="unmemberpurchaseinfo")
@Table(name="tbl_unmemberpurchaseinfo")
public class UnMemberPurchaseInfoEntity {
    @Id
    @Column(name="unmember_purseq", nullable=false)
    private int unmemberpurchaseinfoseq;

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
    @JoinColumn(name="unmemberseq", nullable = false)
    private UnMemberEntity unmemberseq;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="statusseq", nullable = false)
    private PurchaseStatusEntity statusseq;

}
