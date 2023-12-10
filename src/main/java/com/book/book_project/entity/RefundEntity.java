package com.book.book_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="refund")
@Table(name="tbl_refund")
public class RefundEntity {
    @Id
    @Column(name="refundseq", nullable = false)
    private int refundseq;

    @Column(name="refundreason", length = 2000, nullable = false)
    private String refundreason;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="purchaseinfoseq")
    private PurchaseInfoEntity purchaseinfoseq;


}
