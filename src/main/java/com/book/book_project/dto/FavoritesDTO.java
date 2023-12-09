package com.book.book_project.dto;

import com.book.book_project.entity.LikeEntity;
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
    private Long likeseq;
    private Timestamp likedate;
    private String likecheck;
    private MemberEntity userid;
    private ProductEntity bookid;

public FavoritesDTO(LikeEntity likeEntity) {

        this.likeseq = likeEntity.getLikeseq();
        this.likedate = likeEntity.getLikedate();
        this.likecheck = likeEntity.getLikecheck();
        this.userid = likeEntity.getUserid();
        this.bookid = likeEntity.getBookid();
    }

    //Entity --> DTO
public LikeEntity dtoToEntity(FavoritesDTO dto) {

        LikeEntity entity = LikeEntity.builder()
                .likeseq(dto.getLikeseq())
                .likedate(dto.getLikedate())
                .likecheck(dto.getLikecheck())
                .userid(dto.getUserid())
                .bookid(dto.getBookid())
                .build();
        return entity;
    }

}


