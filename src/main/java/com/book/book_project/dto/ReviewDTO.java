package com.book.book_project.dto;

import com.book.book_project.entity.LikeEntity;
import com.book.book_project.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
            private Long reviewSeq;
            private Long bookId;
            private String userId;
            private String reviewer;
            private String reviewContent;
            private Timestamp reviewRegDate;

    public ReviewDTO(ReviewEntity reviewEntity) {

        this.reviewSeq = reviewEntity.getReviewseq();
        this.bookId = reviewEntity.getBookid();
        this.userId = reviewEntity.getUserid();
        this.reviewer = reviewEntity.getReviewer();
        this.reviewContent = reviewEntity.getReviewcontent();
        this.reviewRegDate = reviewEntity.getReviewregdate();
    }

    //Entity --> DTO
    public ReviewEntity dtoToEntity(ReviewDTO dto) {

        ReviewEntity entity = ReviewEntity.builder()
                .reviewseq(dto.getReviewSeq())
                .bookId(dto.getBookId())
                .userId(dto.getUserId())
                .reviewer(dto.getReviewer())
                .reviewContent(dto.getReviewContent())
                .reviewRegDate(dto.getReviewRegDate())
                .build();
        return entity;
    }

}



