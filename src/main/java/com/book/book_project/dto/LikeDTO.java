package com.book.book_project.dto;

import com.book.book_project.entity.LikeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    private Long likeSeq;
    private Timestamp likeDate;
    private String likeCheck;
    private String userId;
    private Long bookId;

public LikeDTO(LikeEntity likeEntity) {

        this.likeSeq = likeEntity.getLikeseq();
        this.likeDate = likeEntity.getLikedate();
        this.likeCheck = likeEntity.getLikecheck();
        this.userId = likeEntity.getUserid();
        this.bookId = likeEntity.getBookid();
    }

    //Entity --> DTO
public LikeEntity dtoToEntity(LikeDTO dto) {

        LikeEntity entity = LikeEntity.builder()
                .likeseq(dto.getLikeSeq())
                .likeDate(dto.getLikeDate())
                .likeCheck(dto.getLikeCheck())
                .userId(dto.getUserId())
                .bookId(dto.getBookId())
                .build();
        return entity;
    }

}


