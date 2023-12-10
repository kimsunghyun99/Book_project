package com.book.book_project.service;

import com.book.book_project.dto.ReviewInterface;

import java.util.List;

public interface ReviewService {


    //리뷰 목록 보기
    public List<ReviewInterface> replyView(ReviewInterface reply) throws Exception;

    //리뷰 등록
    public void replyRegistry(ReviewInterface reply) throws Exception;


}
