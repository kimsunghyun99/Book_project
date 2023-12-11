package com.book.book_project.service;

import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import com.book.book_project.entity.repository.MemberRepository;
import com.book.book_project.entity.repository.ProductRepository;
import com.book.book_project.entity.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    //리뷰 목록 보기
    @Override
    public List<ReviewInterface> ReviewView(ReviewInterface review) throws Exception{
        return reviewRepository.reviewView(review.getReviewseq());
    }

    //리뷰 등록 
    @Override
    public void ReviewRegistry(ReviewInterface Review) throws Exception {
        ProductEntity productEntity = productRepository.findById(Review.getBookid().getBookid()).get();
        MemberEntity memberEntity = memberRepository.findById(Review.getUserid().getUserid()).get();

        ReviewEntity reviewEntity = ReviewEntity.builder()
                .reviewseq(Review.getReviewseq())
                .bookid(productEntity)
                .userid(memberEntity)
                .reviewer(Review.getReviewer())
                .reviewcontent(Review.getReviewcontent())
                .reviewregdate(new Timestamp(System.currentTimeMillis()))
                .build();
        reviewRepository.save(reviewEntity);
    }

    //리뷰 수정
    @Override
    public void ReviewUpdate(ReviewInterface Review) throws Exception{
        ReviewEntity ReviewEntity = reviewRepository.findById(Review.getReviewseq()).get();
        ReviewEntity.setReviewcontent(Review.getReviewcontent());
        reviewRepository.save(ReviewEntity);
    }

    //리뷰 삭제
    @Override
    public void ReviewDelete(ReviewInterface Review) throws Exception{
        ReviewEntity ReviewEntity = reviewRepository.findById(Review.getReviewseq()).get();
        reviewRepository.delete(ReviewEntity);
    }
}
