package com.book.book_project.service;

import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    //게시물 목록 보기
    public Page<ProductEntity> list(int pageNum, int postNum, String keyword) throws Exception;

    //상세 내용 보기
    public ProductDTO view(int bookid) throws Exception;

//    //리뷰 목록 보기
//    public List<ReviewInterface> ReviewView(ReviewInterface review) throws Exception;
//
//    //리뷰 등록
//    public void ReviewRegistry(ReviewInterface review) throws Exception;
//
//    //리뷰 수정
//    public void ReviewUpdate(ReviewInterface review) throws Exception;
//
//    //리뷰 삭제
//    public void ReviewDelete(ReviewInterface review) throws Exception;






}
