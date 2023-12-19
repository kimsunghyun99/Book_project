package com.book.book_project.service;

import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {
    //상세 내용 보기
    public ProductDTO view(String bookid) throws Exception;

//    // bookid 모두 가져오기
//    public List<ProductEntity> productAll throws Exception;
//


}
