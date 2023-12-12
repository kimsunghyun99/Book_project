package com.book.book_project.dto;

import com.book.book_project.entity.CategoryEntity;
import com.book.book_project.entity.ProductEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private int bookid; //도서번호
    private String bookname;    //책이름
    private String author;
    private String publisher;   //출판사
    private int price;  //원가
    private int stock;  //재고
    private String description; //설명
    private String orgimg;  //원본 책 표지 이미지
    private String storedimg; //저장된 책 표지 이미지
    private Timestamp regdate;  //등록일
    private String isbn;    //책 고유번호
    private String status;  //판매 상태
    private Timestamp publicationdate;  //출판일
    private float grade;    //평점
    private int sale;   //할인율
    private long filesize;   //파일크기
    private CategoryEntity categorynumber;    //분류 이름


    //Entity -> DTO
    public ProductDTO (ProductEntity productEntity) {
        this.bookid = productEntity.getBookid();
        this.bookname = productEntity.getBookname();
        this.author = productEntity.getAuthor();
        this.publisher = productEntity.getPublisher();
        this.price = productEntity.getPrice();
        this.stock = productEntity.getStock();
        this.description = productEntity.getDescription();
        this.orgimg = productEntity.getOrgimg();
        this.storedimg = productEntity.getStoredimg();
        this.regdate = productEntity.getRegdate();
        this.isbn = productEntity.getIsbn();
        this.status = productEntity.getStatus();
        this.publicationdate = productEntity.getPublicationdate();
        this.grade = productEntity.getGrade();
        this.sale = productEntity.getSale();
        this.filesize = productEntity.getFilesize();
        this.categorynumber = productEntity.getCategorynumber();
    }

    //DTO -> Entity
    public ProductEntity dtoToEntity(ProductDTO dto) {
        ProductEntity productEntity = ProductEntity.builder()
                .bookid(dto.getBookid())
                .bookname(dto.getBookname())
                .publisher(dto.getPublisher())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .description(dto.getDescription())
                .orgimg(dto.getOrgimg())
                .storedimg(dto.getStoredimg())
                .regdate(dto.getRegdate())
                .isbn(dto.getIsbn())
                .status(dto.getStatus())
                .publicationdate(dto.getPublicationdate())
                .grade(dto.getGrade())
                .sale(dto.getSale())
                .filesize(dto.getFilesize())
                .categorynumber(dto.getCategorynumber())
                .build();

        return productEntity;
    }

}
