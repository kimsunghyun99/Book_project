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


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="userid", nullable = false)
    private MemberEntity userid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="bookid", nullable = false)
    private ProductEntity bookid;
}