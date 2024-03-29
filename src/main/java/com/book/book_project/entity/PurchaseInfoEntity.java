package com.book.book_project.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(name = "imp_key")
    private String imp_key;

    @Column(name = "imp_secret")
    private String imp_secret;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="statusseq", nullable = false)
    private PurchaseStatusEntity statusseq;


    @JsonCreator
    public PurchaseInfoEntity(@JsonProperty("purchaseinfonumber") int purchaseinfonumber) {
        this.purchaseinfonumber = purchaseinfonumber;
    }



}
