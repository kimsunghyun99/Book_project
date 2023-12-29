package com.book.book_project.service;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.CategoryEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

   // interest값을 토대로 항목들가져오기
    public Page<ProductEntity> list(int pageNum, int postNum,  String keyword , List<Integer> categorynumbers) throws Exception;


    // interest 안고른경우 항목들 가져오기
 public Page<ProductEntity> list1(int pageNum, int postNum, String keyword) throws Exception;





    // interest에 해당하는 책 개수 세기
//    public int bookcount(String interest) throws Exception;


 // interest를 토대로 categorynumber 가져오기
     public List<Integer> getCateNumber(String interest)throws Exception;




    //상세 내용 보기
    public ProductDTO view(String bookid) throws Exception;



    // 회원별 관심사에 맞는거 꺼내기 depth3
    public List<ProductEntity> productlist(String interest) throws Exception;


    // bookname, cover 가져오기 --> 비회원, 소셜로그인을 위함
    public List<ProductEntity> productlist1() throws Exception;

    // 상품목록에서 큰 분류로  보고싶은거 꺼내기 depth2
    public List<ProductEntity> productlist2(String interest) throws Exception;



    ProductEntity findById(String bookid);
}
