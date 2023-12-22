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
public class ProductEntity  {

    @Id
    @Column(name="bookid", length=100, nullable = false)
    private String bookid; //도서번호 isbn String

    @Column(name="bookname", length=100, nullable = false)
    private String bookname;    //책이름 title String

    @Column(name="publisher", length=50, nullable = false)
    private String publisher;   //출판사 String

     @Column(name="author", length=50, nullable = false)
    private String author;   //작가 String

    @Column(name="price", nullable = false)
    private int price;  //정가 priceStandard

    @Column(name = "pricesales", nullable=true)
    private int pricesales;//할인가 priceSales int

    @Column(name="stock", nullable = true)
    private String stock;  //재고 stockStatus - String

    @Column(name="description", length=2000, nullable = false)
    private String description; //설명 String

    @Column(name="cover", length=200, nullable = true)
    private String cover;  //표지이미지 String

    @Column(name="regdate", nullable = false)
    private Timestamp regdate;  //등록일

    @Column(name="publicationdate", nullable = false)
    private String publicationdate;  //출판일 pubdate String

    @Column(name="salespoint", nullable = true)
    private int salespoint;   //판매지수 salsePoint int

    //FK 분류이름
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categorynumber" ,nullable = false)
    private CategoryEntity categorynumber;



    public String getIdAsString() {
        return String.valueOf(this.getBookid());
    }

    public ProductEntity(String bookid) {
        this.bookid = bookid;
    }


}
