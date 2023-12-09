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

@Entity(name="favorites")
@Table(name="tbl_favorites")
public class FavoritesEntity {

    @Id
    @Column(name="favoriteseq")
    private Long favoriteseq;

    @Column(name="favoritedate", nullable=true)
    private Timestamp favoritedate;

    @Column(name="favoritecheck", nullable=true)
    private String favoritecheck;


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="userid", nullable = false)
    private MemberEntity userid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="bookid", nullable = false)
    private ProductEntity bookid;
}