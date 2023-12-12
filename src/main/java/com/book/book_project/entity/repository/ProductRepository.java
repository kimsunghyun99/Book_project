package com.book.book_project.entity.repository;

import com.book.book_project.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    //책 목록 보기
    public Page<ProductEntity> findByBookidContainingOrBooknameContainingOrCategorynumberContainingOrIsbnContainingOrPublisher
    (String keyword1,String keyword2,String keyword3, String keyword4, String keyword5,Pageable pageable);



}
