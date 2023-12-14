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
    public List<ReviewInterface> reviewView(ReviewInterface review) throws Exception{
        return reviewRepository.reviewView(review.getReviewseq());
    }

    //리뷰 등록
    @Override
    public void reviewRegistry(ReviewInterface review) throws Exception {
        ProductEntity productEntity = productRepository.findById(review.getBookid().getBookid()).get();
        MemberEntity memberEntity = memberRepository.findById(review.getUserid().getUserid()).get();

        System.out.println(productEntity);
        System.out.println(memberEntity);
        System.out.println(review.getReviewseq());

        ReviewEntity reviewEntity = ReviewEntity.builder()
                .reviewseq(review.getReviewseq())
                .bookid(productEntity)
                .userid(memberEntity)
                .reviewer(review.getReviewer())
                .reviewcontent(review.getReviewcontent())
                .reviewregdate(new Timestamp(System.currentTimeMillis()))
                .build();
        reviewRepository.save(reviewEntity);
    }

    //리뷰 수정
    @Override
    public void reviewUpdate(ReviewInterface review) throws Exception{
        ReviewEntity reviewEntity = reviewRepository.findById(review.getReviewseq()).get();
        reviewEntity.setReviewcontent(review.getReviewcontent());
        reviewRepository.save(reviewEntity);
    }

    //리뷰 삭제
    @Override
    public void reviewDelete(ReviewInterface review) throws Exception{
        ReviewEntity reviewEntity = reviewRepository.findById(review.getReviewseq()).get();
        reviewRepository.delete(reviewEntity);
    }
}
