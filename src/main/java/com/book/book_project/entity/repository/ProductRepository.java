package com.book.book_project.entity.repository;

import com.book.book_project.dto.ProductDTO;
import com.book.book_project.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    //책 목록 보기
    Page<ProductEntity> findByBookidContainingOrBooknameContainingOrCategorynumberContainingOrPublisher
    (String keyword1,String keyword2,String keyword3, String keyword4,Pageable pageable);


//    List<Object> findById(ProductEntity bookid);

    //bookid 가져 오기
    @Query(value="select bookid from tbl_product", nativeQuery=true)
    String getBookid();


    // bookname, cover 가져오기 -> 나중에 interests 토대로 가져올 예정
    @Query(value="select bookid, bookname, cover from tbl_product", nativeQuery = true)
    List<ProductDTO> productlist();

}
