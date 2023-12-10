package com.book.book_project.entity.repository;

import com.book.book_project.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    //장바구니 추가

    //장바구니 삭제

}
