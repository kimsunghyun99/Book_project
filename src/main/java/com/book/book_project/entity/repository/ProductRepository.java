package com.book.book_project.entity.repository;

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
    (String keyword1,String keyword2,int keyword3, String keyword4, Pageable pageable);

//    List<Object> findById(ProductEntity bookid);

//    //bookid 가져 오기
//    @Query(value="select * from tbl_product", nativeQuery=true)
//    String getBookid();
//
//    //
    @Query(value="select * from tbl_product", nativeQuery=true)
    List<ProductEntity> getProductList();

}
