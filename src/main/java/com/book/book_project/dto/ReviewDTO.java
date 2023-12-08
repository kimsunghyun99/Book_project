package com.book.book_project.dto;

import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;
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
            private Long reviewseq;
            private ProductEntity bookid;
            private MemberEntity userid;
            private String reviewer;
            private String reviewcontent;
            private Timestamp reviewregdate;

    public ReviewDTO(ReviewEntity reviewEntity) {

        this.reviewseq = reviewEntity.getReviewseq();
        this.bookid = reviewEntity.getBookid();
        this.userid = reviewEntity.getUserid();
        this.reviewer = reviewEntity.getReviewer();
        this.reviewcontent = reviewEntity.getReviewcontent();
        this.reviewregdate = reviewEntity.getReviewregdate();
    }

    //Entity --> DTO
    public ReviewEntity dtoToEntity(ReviewDTO dto) {

        ReviewEntity entity = ReviewEntity.builder()
                .reviewseq(dto.getReviewseq())
                .bookid(dto.getBookid())
                .userid(dto.getUserid())
                .reviewer(dto.getReviewer())
                .reviewcontent(dto.getReviewcontent())
                .reviewregdate(dto.getReviewregdate())
                .build();
        return entity;
    }

}



