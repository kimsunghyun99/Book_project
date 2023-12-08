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

@Entity(name="like")
@Table(name="tbl_like")
public class LikeEntity {

    @Id
    @Column(name="likeseq")
    private Long likeseq;

    @Column(name="likedate", nullable=true)
    private Timestamp likedate;

    @Column(name="likecheck", nullable=true)
    private String likecheck;

    @Column(name="userid", length=20, nullable=false)
    private String userid;

    @Column(name="bookid", length=10, nullable=true)
    private Long bookid;



}