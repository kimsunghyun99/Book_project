package com.book.book_project.dto;

import com.book.book_project.entity.FavoritesEntity;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesDTO {
    private int favoriteseq;
    private Timestamp favoritedate;
    private String favoritecheck;
    private MemberEntity userid;
    private ProductEntity bookid;

public FavoritesDTO(FavoritesEntity favoritesEntity) {

        this.favoriteseq = favoritesEntity.getFavoriteseq();
        this.favoritedate = favoritesEntity.getFavoritedate();
        this.favoritecheck = favoritesEntity.getFavoritecheck();
        this.userid = favoritesEntity.getUserid();
        this.bookid = favoritesEntity.getBookid();
    }

    //Entity --> DTO
public FavoritesEntity dtoToEntity(FavoritesDTO dto) {

        FavoritesEntity entity = FavoritesEntity.builder()
                .favoriteseq(dto.getFavoriteseq())
                .favoritedate(dto.getFavoritedate())
                .favoritecheck(dto.getFavoritecheck())
                .userid(dto.getUserid())
                .bookid(dto.getBookid())
                .build();
        return entity;
    }

}


