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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String bookid; //도서번호

    @Column(name="bookname", length=100, nullable = false)
    private String bookname;    //책이름

    @Column(name="publisher", length=50, nullable = false)
    private String publisher;   //출판사

     @Column(name="author", length=50, nullable = false)
    private String author;   //작가

    @Column(name="price", nullable = false)
    private int price;  //원가

    @Column(name = "pricesales", nullable=true)
    private int pricesales;//할인가

    @Column(name="stock", nullable = false)
    private int stock;  //재고

    @Column(name="description", length=2000, nullable = false)
    private String description; //설명

    @Column(name="cover", length=200, nullable = true)
    private String cover;  //표지이미지

    @Column(name="regdate", nullable = false)
    private Timestamp regdate;  //등록일

    @Column(name="isbn", length=100, nullable = false)
    private String isbn;    //책 고유번호

    @Column(name="status", length=100, nullable = false)
    private String status;  //판매상태

    @Column(name="publicationdate", nullable = false)
    private Timestamp publicationdate;  //출판일

    @Column(name="salespoint", nullable = true)
    private int salespoint;   //판매지수

    //FK 분류이름
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categorynumber" ,nullable = false)
    private CategoryEntity categorynumber;

}
