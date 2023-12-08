package com.book.book_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

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
    private Long purchase_name;

    @Column(name = "purchase_telno", length = 10, nullable = true)
    private String purchase_telno;

    @Column(name = "userid", length = 10, nullable = false)
    private String userid;

}