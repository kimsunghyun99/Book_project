package com.book.book_project.dto;

import com.book.book_project.entity.CategoryEntity;
import com.book.book_project.entity.ProductEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private String bookid; //도서번호
    private String bookname;    //책이름
    private String publisher;   //출판사
    private String author;//작가
    private int price;  //원가
    private String stock;  //재고
    private String description; //설명
    private String cover;  //표지 이미지
    private Timestamp regdate;  //등록일
    private String publicationdate;  //출판일
    private int salespoint;//판매지수

    private CategoryEntity categorynumber;    //분류 이름


    //Entity -> DTO
    public ProductDTO (ProductEntity productEntity) {
        this.bookid = productEntity.getBookid();
        this.bookname = productEntity.getBookname();
        this.publisher = productEntity.getPublisher();
        this.author=productEntity.getAuthor();
        this.price = productEntity.getPrice();
        this.stock = productEntity.getStock();
        this.description = productEntity.getDescription();
        this.cover=productEntity.getCover();
        this.regdate = productEntity.getRegdate();
        this.publicationdate = productEntity.getPublicationdate();
        this.salespoint =productEntity.getSalespoint();
        this.categorynumber = productEntity.getCategorynumber();
    }

    //DTO -> Entity
    public ProductEntity dtoToEntity(ProductDTO dto) {

        return ProductEntity.builder()
                .bookid(dto.getBookid())
                .bookname(dto.getBookname())
                .publisher(dto.getPublisher())
                .author(dto.getAuthor())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .description(dto.getDescription())
                .cover(dto.getCover())
                .regdate(dto.getRegdate())
                .publicationdate(dto.getPublicationdate())
                .salespoint(dto.getSalespoint())
                .categorynumber(dto.getCategorynumber())
                .build();
    }



}
