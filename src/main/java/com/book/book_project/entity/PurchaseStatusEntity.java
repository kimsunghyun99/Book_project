package com.book.book_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="purchasestatus")

@Table(name="tbl_purchasestatus")
public class PurchaseStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusseq;

    @Column(name="statusname", length=50 ,nullable=false)
    private String statusname;

}
