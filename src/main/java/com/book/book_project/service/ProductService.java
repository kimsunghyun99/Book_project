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
    public ProductDTO view(String bookid) throws Exception;

    // bookname, cover 가져오기 -> 나중에 interests 토대로 가져올 예정
    public List<ProductDTO> productlist() throws Exception;

}
