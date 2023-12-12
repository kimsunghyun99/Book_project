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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refundseq;

    @Column(name="refundreason", length = 2000, nullable = false)
    private String refundreason;

    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name="purchaseinfoseq")
    private PurchaseInfoEntity purchaseinfoseq;


}
