package com.book.book_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(name = "reviewer", length = 20, nullable = false)
    private String reviewer;

    @Column(name = "reviewcontent", length = 100, nullable = false)
    private String reviewcontent;

    @Column(name = "reviewregdate", nullable = true)
    private Timestamp reviewregdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="userid", nullable = false)
    private MemberEntity userid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="bookid", nullable = false)
    private ProductEntity bookid;

}