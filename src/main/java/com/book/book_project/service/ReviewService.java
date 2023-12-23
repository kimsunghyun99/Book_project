package com.book.book_project.service;

import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.dto.ReviewInterfaceImpl;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ReviewService {



  public Page<ReviewEntity> list(ProductEntity bookid, int pageNum, int postNum) throws Exception;

    //리뷰 목록 보기
    public List<ReviewInterface> reviewView(ReviewInterfaceImpl review) throws Exception;

    //리뷰 등록
    public void reviewRegistry(ReviewInterfaceImpl review) throws Exception;

    //리뷰 수정
    public void reviewUpdate(ReviewInterfaceImpl review) throws Exception;

    //리뷰 삭제
    public void reviewDelete(ReviewInterfaceImpl review) throws Exception;


}
