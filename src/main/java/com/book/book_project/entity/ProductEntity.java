package com.book.book_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="product")
@Table(name="tbl_product")
public class ProductEntity {

    @Id
    @Column(name="bookid", nullable = false)
    private int bookid; //도서번호

    @Column(name="bookname", length=100, nullable = false)
    private String bookname;    //책이름

    @Column(name="publisher", length=100, nullable = false)
    private String publisher;   //출판사

    @Column(name="price", nullable = false)
    private int price;  //원가

    @Column(name="stock", nullable = false)
    private int stock;  //재고

    @Column(name="description", length=2000, nullable = false)
    private String description; //설명

    @Column(name="orgimg", length=200, nullable = true)
    private String orgimg;  //원본책표지이미지

    @Column(name="storedimg", length=200, nullable = true)
    private String storedimg;   //저장된책표지이미지

    @Column(name="regdate", nullable = false)
    private Timestamp regdate;  //등록일

    @Column(name="isbn", length=100, nullable = false)
    private String isbn;    //책 고유번호

    @Column(name="status", length=100, nullable = false)
    private String status;  //판매상태

    @Column(name="publicationdate", nullable = false)
    private Timestamp publicationdate;  //출판일

    @Column(name="grade", nullable = true)
    private float grade;    //평점

    @Column(name="sale", nullable = true)
    private int sale;   //할인율

    @Column(name="filesize", nullable = false)
    private long filesize;   //파일크기

    //FK 분류이름
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categorynumber" ,nullable = false)
    private CategoryEntity categorynumber;
}
