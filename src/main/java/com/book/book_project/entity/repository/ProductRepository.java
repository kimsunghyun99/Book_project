package com.book.book_project.entity.repository;

import com.book.book_project.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    //책 목록 보기
    public Page<ProductEntity> findByBookidContainingOrBooknameContainingOrCategoryseqContainingOrIsbnContainingOrPublisher
    (String keyword1,String keyword2,String keyword3, String keyword4, String keyword5,Pageable pageable);


//    List<Object> findById(ProductEntity bookid);

    //bookid 가져 오기
    @Query(value="select bookid from tbl_product", nativeQuery=true)
    public Long getBookid();
}
