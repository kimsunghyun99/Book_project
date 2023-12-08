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

@Entity(name="review")
@Table(name="tbl_reveiw")
public class ReviewEntity {

    @Id
    @Column(name = "reviewseq")
    private Long reviewseq;

    @Column(name = "bookid", nullable = false)
    private Long bookid;

    @Column(name = "userid",length = 20, nullable = false)
    private String userid;

    @Column(name = "reviewer", length = 20, nullable = false)
    private String reviewer;

    @Column(name = "reviewcontent", length = 100, nullable = false)
    private String reviewcontent;

    @Column(name = "reviewregdate", nullable = true)
    private Timestamp reviewregdate;


}