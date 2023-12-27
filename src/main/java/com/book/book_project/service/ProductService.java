package com.book.book_project.service;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    //게시물 목록 보기
    public Page<ProductEntity> list(int pageNum, int postNum, String keyword) throws Exception;

    //상세 내용 보기
    public ProductDTO view(String bookid) throws Exception;



    // bookname, cover 가져오기 -> 나중에 interests 토대로 가져올 예정
    public List<ProductEntity> productlist(String interest) throws Exception;


    // bookname, cover 가져오기 --> 비회원, 소셜로그인을 위함
    public List<ProductEntity> productlist1() throws Exception;




    ProductEntity findById(String bookid);
}
