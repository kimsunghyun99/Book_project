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

@Entity(name="buyerinfo")
@Table(name="tbl_buyerinfo")
public class BuyerInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buyerseq;

    @Column(name = "receiverdetailaddr", length = 100, nullable = true)
    private String receiverdetailaddr;

    @Column(name = "receiverzipcode", length = 10, nullable = true)
    private String receiverzipcode;

    @Column(name = "receiveraddr", length = 50, nullable = true)
    private String receiveraddr;

    @Column(name = "receivername", length = 10, nullable = true)
    private String receivername;

    @Column(name = "receivertelno", length = 50, nullable = true)
    private String receivertelno;


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="userid", nullable = false)
    private MemberEntity userid;

}