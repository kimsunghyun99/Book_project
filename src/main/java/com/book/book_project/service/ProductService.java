package com.book.book_project.service;

import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    public Page<ReviewEntity> list(int pageNum, int postNum) throws Exception;

    //상세 내용 보기
    public ProductDTO view(int bookid) throws Exception;







}
