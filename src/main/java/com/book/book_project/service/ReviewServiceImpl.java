package com.book.book_project.service;

import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.dto.ReviewInterfaceImpl;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import com.book.book_project.entity.repository.MemberRepository;
import com.book.book_project.entity.repository.ProductRepository;
import com.book.book_project.entity.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
//
//    //리뷰 목록 보기
//    @Override
//    public List<ReviewInterface> reviewView(ReviewInterface review) throws Exception{
//        return reviewRepository.reviewView(review.getBookid());
//    }
//
//    //리뷰 등록
//    @Override
//    public void reviewRegistry(ReviewInterface review) throws Exception {
//
//        System.out.println("1. : " + review);
//        ProductEntity productEntity = productRepository.findById(review.getBookid()).get();
//        System.out.println("2. : " + review);
//        MemberEntity memberEntity = memberRepository.findById(review.getUserid()).get();
//        System.out.println("3. : " + review);
//
//        System.out.println(productEntity);
//        System.out.println(memberEntity);
//        System.out.println(review.getReviewseq());
//
//        ReviewEntity reviewEntity = ReviewEntity.builder()
//                .bookid(productEntity)
//                .userid(memberEntity)
//                .reviewer(review.getReviewer())
//                .reviewcontent(review.getReviewcontent())
//                .reviewregdate(new Timestamp(System.currentTimeMillis()))
//                .build();
//        reviewRepository.save(reviewEntity);
//    }


    @Override
    public Page<ReviewEntity> list(int pageNum, int postNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, postNum, Sort.by(Sort.Direction.DESC, "reviewseq"));
        return reviewRepository.findAll(pageRequest);
    }

        //리뷰 목록 보기
    @Override
    public List<ReviewInterface> reviewView(ReviewInterfaceImpl review) throws Exception{
        return reviewRepository.reviewView(review.getBookid());
    }

    // 리뷰 등록
    @Override
    public void reviewRegistry(ReviewInterfaceImpl review) throws Exception {
        ProductEntity productEntity = productRepository.findById(review.getBookid()).orElse(null);
        MemberEntity memberEntity = memberRepository.findById(review.getUserid()).orElse(null);

        if (productEntity == null || memberEntity == null) {
            throw new Exception("ProductEntity 또는 MemberEntity를 찾을 수 없습니다.");
        }

        System.out.println(productEntity);
        System.out.println(memberEntity);
        System.out.println(review.getReviewseq());

        ReviewEntity reviewEntity = ReviewEntity.builder()
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
    public void reviewUpdate(ReviewInterfaceImpl review) throws Exception{
        ReviewEntity reviewEntity = reviewRepository.findById(review.getReviewseq()).get();
        reviewEntity.setReviewcontent(review.getReviewcontent());
        reviewRepository.save(reviewEntity);
    }

    //리뷰 삭제
    @Override
    public void reviewDelete(ReviewInterfaceImpl review) throws Exception{
        ReviewEntity reviewEntity = reviewRepository.findById(review.getReviewseq()).get();
        reviewRepository.delete(reviewEntity);
    }
}
