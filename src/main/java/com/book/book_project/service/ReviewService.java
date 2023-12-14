package com.book.book_project.service;

import com.book.book_project.dto.ReviewInterface;

import java.util.List;

public interface ReviewService {



    //리뷰 목록 보기
    public List<ReviewInterface> reviewView(ReviewInterface review) throws Exception;

    //리뷰 등록
    public void reviewRegistry(ReviewInterface review) throws Exception;

    //리뷰 수정
    public void reviewUpdate(ReviewInterface review) throws Exception;

    //리뷰 삭제
    public void reviewDelete(ReviewInterface review) throws Exception;


}
